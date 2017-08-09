package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.lctpc.helpdeck.pojo.AEntities;
import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.pojo.PageEntity;
import mx.com.lctpc.helpdeck.service.ApplicationService;
import mx.com.lctpc.helpdeck.service.EntityService;
import mx.com.lctpc.helpdeck.service.PageService;

@Controller
public class PageController {
	@Autowired
	private PageService pageService;

	@Autowired
	private EntityService entityService;

	@Autowired
	private ApplicationService appService;

	/*@InitBinder*/
	protected void initBinder( HttpServletRequest request, ServletRequestDataBinder binder ) throws Exception {
		binder.registerCustomEditor(List.class, "pageEntities", new CustomCollectionEditor(List.class) {
			protected Object convertElement( Object element ) {
				if (element instanceof PageEntity) {
					return ((PageEntity) element).getPaenEnttId();
				}
				if (element instanceof String) {
					// Transformar al dato dessea para guardar
					String l_ids[] = element.toString().split("\\|");
					PageEntity l_pagEnt = new PageEntity();
					if (l_ids[0].equals("NEW")) {
						BigDecimal l_idEnt = new BigDecimal(l_ids[1]);
						AEntities l_ent = entityService.findEntityById(l_idEnt);
						l_pagEnt.setPaenEnttId(l_ent);
						if (request.getParameter("pageId") != null) {
							BigDecimal l_pageId = new BigDecimal(request.getParameter("pageId"));
							l_pagEnt.setPaenPageId(pageService.findPageById(l_pageId));
						}
					} else {
						BigDecimal l_idPagEnt = new BigDecimal(l_ids[0]);
						l_pagEnt = pageService.findPageEntityById(l_idPagEnt);

					}

					return l_pagEnt;

				}
				if (element instanceof AEntities) {
					// BigDecimal l_d = ((AEntities) element).getEnttId();

					return null;
					/*
					 * return ((AEntities)element).getEnttId().toPlainString();
					 */
				}
				if (element instanceof BigDecimal) {
					
					
				}
				return null;
			}
		});
	}

	@RequestMapping( "/pages" )
	public String showPages( Model p_model ) {
		List<Page> l_pages = pageService.findAllPage();
		p_model.addAttribute("pages", l_pages);
		return "pages";
	}

	@RequestMapping( "/pageForm" )
	public String showPageForm( Model p_model ) {

		Page l_pag = new Page();
		p_model.addAttribute("page", l_pag);
		p_model.addAttribute("pagesMasterList", pageService.findAllPage());
		p_model.addAttribute("entitiesList", entityService.findAllEntity());

		return "fragments/pageForm";
	}

	@RequestMapping( value = "/pageForm/save", method = RequestMethod.POST )
	public String showPageFormSave( @ModelAttribute( "page" ) Page p_pag, Model model ) {
		//obtener la pagina actual de la base de datos (pagina que sera actualizada)
		Page l_pagCurrent = pageService.findPageById(p_pag.getPageId());
		
		// Cambiar por el metodo de obtener los pageEntity activas y no todas.
		List<PageEntity> l_lstCurrent = l_pagCurrent.getPageEntities(); 
		
		pageService.saveOrUpdatePage(p_pag);

		// Proceso de actualizacion de pageEntity cuando todavia no exite ninguna entidad relacionada a la pagina
		List<PageEntity> l_lstNew = new ArrayList<PageEntity>();
		if (l_lstCurrent.isEmpty()) {

			for (PageEntity l_pE : p_pag.getPageEntities()) {
				l_pE.setPaenId(null);
				l_pE.setPaenPageId(p_pag);
				l_pE.setPaenActive(true);
				l_pE.setPaenCreatedBy("BENITEZ.ABNER");
				l_pE.setPaenUpdateBy("BENITEZ.ABNER");

				pageService.savePageEntity(l_pE);
			}

		} else {// proceso para cuando ya existen entidades asociadas a la pagina

			for (PageEntity l_pg : p_pag.getPageEntities()) { // Recore PageEntity enviados desde el front-End para su revision.
				
				//Traer la PageEntity activa si es que existe, se obtiene el PageEntity ya que el front-End retorno
				// en el objeto PageEntity el valor del objeto AEntity por eso se obtiene el valor real de PageEntity
				PageEntity l_pgNew = pageService.findPageEntity(p_pag.getPageId(), l_pg.getPaenEnttId().getEnttId());

				// Caso cuando existen entidad ya relaccionadas y se agrega una nueva.
				if (l_pgNew == null) {
					l_pg.setPaenId(null); //Se estable en null por que desde el front-End trae el Id de AEntity
					l_pg.setPaenPageId(p_pag);
					l_pg.setPaenActive(true);
					l_pg.setPaenCreatedBy("BENITEZ.ABNER");
					l_pg.setPaenUpdateBy("BENITEZ.ABNER");

					pageService.savePageEntity(l_pg);

					l_lstNew.add(l_pg);
				} else
					l_lstNew.add(l_pgNew);
			}

			// Proceso para obtener las entidades a borrar o desactivar; 
			// Se removeran las entidades nuevas de la lista actual(l_lstCurrent), con esto se quedaran solo las 
			// entidades a borrar o que se quitaron del dual_select del front-end
			l_lstCurrent.removeAll(l_lstNew);

			// Desactivar o borrar entidades del array actual
			for (PageEntity l_pE : l_lstCurrent) {
				l_pE.setPaenActive(false);
				l_pE.setPaenUpdateBy("BENITEZ.ABNER");
				pageService.deletePageEntity(l_pE);
			}

		}

		return "redirect:/catalogManager";
	}

	@RequestMapping( "/pageForm/{pageId}/update" )
	public String showUpdatePage( Model p_model, @PathVariable( "pageId" ) BigDecimal p_pagId ) {

		Page l_page = pageService.findPageById(p_pagId);

		List<BigDecimal> l_lstEnt = new ArrayList<BigDecimal>();
		for (PageEntity l_ent : l_page.getPageEntities()) {
			l_lstEnt.add(l_ent.getPaenEnttId().getEnttId());

		}

		p_model.addAttribute("page", l_page);
		p_model.addAttribute("pagesMasterList", pageService.findAllPage());
		p_model.addAttribute("entitiesList", entityService.findAllEntity());
		p_model.addAttribute("pagEntList", l_lstEnt);
		// p_model.addAttribute("entitiesList",
		// pageService.findEntitiesActiveLeftJoinFromPage(p_pagId) );
		return "fragments/pageForm";
	}

	@RequestMapping( value = { "/pageForm/{pageId}/delete" } )
	@ResponseBody
	public ResponseEntity<String> showDeletePage( Model model, @PathVariable( "pageId" ) BigDecimal p_pageId ) {
		Page l_page = pageService.findPageById(p_pageId);
		pageService.deletePage(l_page);

		return new ResponseEntity<String>("ok", HttpStatus.OK); // ResponseEntity<String>
	}

	@RequestMapping( value = { "/appWizard/page/delete/{pageId}/{appId}" } )
	@ResponseBody
	public ResponseEntity<String> jsonDeletePageFromApplication( Model model, @PathVariable( "pageId" ) BigDecimal p_pageId, @PathVariable( "appId" ) BigDecimal p_appId ) {
		Page l_page = pageService.findPageById(p_pageId);
		Application l_appn = appService.findApplicationById(p_appId);

		// l_page.getApplications().add(l_appn);
		pageService.deletePageFromApplicationById(l_page, l_appn);

		return new ResponseEntity<String>("ok", HttpStatus.OK); // ResponseEntity<String>
	}

	@RequestMapping( value = "/appWizard/page/save/{appId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<String> jsonUpdatePage( @RequestBody Page p_page, @PathVariable( "appId" ) BigDecimal p_appId ) {
		Page l_pagCurrent = pageService.findPageById(p_page.getPageId());
		List<PageEntity> l_lstCurrent = l_pagCurrent.getPageEntities(); // Cambiar por el metodo de obtener los
																		// pageEntity activas y no todas.

		if (p_appId == null) {
			return new ResponseEntity<>("not exit Applicacion", HttpStatus.NO_CONTENT);
		}

		Application l_pag = appService.findApplicationById(p_appId);

		if (l_pag == null) {
			return new ResponseEntity<>("not exit Applicacion", HttpStatus.NO_CONTENT);
		}

		p_page.setPageCreatedBy("BENITEZ.ABNER");
		p_page.setPageUpdateBy("BENITEZ.ABNER");

		pageService.saveOrUpdatePage(p_page);

		// Proceso de actualizacion de pageEntity cuando todavia no exite ninguna entidad relacionada a la pagina
		List<PageEntity> l_lstNew = new ArrayList<PageEntity>();
		if (l_lstCurrent.isEmpty()) {

			for (PageEntity l_pE : p_page.getPageEntities()) {

				l_pE.setPaenPageId(p_page);
				l_pE.setPaenActive(true);
				l_pE.setPaenCreatedBy("BENITEZ.ABNER");
				l_pE.setPaenUpdateBy("BENITEZ.ABNER");

				pageService.savePageEntity(l_pE);
			}

		} else {// proceso para cuando ya existen entidades asociadas a la pagina

			for (PageEntity l_pg : p_page.getPageEntities()) { // Recore PageEntity enviados desde el front-End para su
																// revision.
				
				//Obetenr los pageEntity que fueron enviadas por el JSON ya creando la case PageEntity
				PageEntity l_pgNew = l_pg.getPaenId() != null ? pageService.findPageEntityById(l_pg.getPaenId()) : null;

				// Caso cuando existen entidad ya relaccionadas y se agrega una nueva.
				if (l_pgNew == null) {
					l_pg.setPaenPageId(p_page);
					l_pg.setPaenActive(true);
					l_pg.setPaenCreatedBy("BENITEZ.ABNER");
					l_pg.setPaenUpdateBy("BENITEZ.ABNER");

					pageService.savePageEntity(l_pg);

					l_lstNew.add(l_pg);
				} else
					l_lstNew.add(l_pgNew);
			}

			// Proceso para obtener las entidades a borrar o desactivar
			l_lstCurrent.removeAll(l_lstNew);

			// Desactivar o borrar entidades del array actual
			for (PageEntity l_pE : l_lstCurrent) {
				l_pE.setPaenActive(false);
				l_pE.setPaenUpdateBy("BENITEZ.ABNER");
				pageService.deletePageEntity(l_pE);
			}

		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping( value = "/appWizard/page/save/{appId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<String> jsonCreatePage( @RequestBody Page p_page, @PathVariable( "appId" ) BigDecimal p_appId ) {

		List<PageEntity> l_lst = p_page.getPageEntities();
		p_page.setPageEntities(null);

		p_page.setPageCreatedBy("BEITEZ.ABNER");
		p_page.setPageUpdateBy("BEITEZ.ABNER");

		pageService.saveOrUpdatePage(p_page);

		for (PageEntity l_pE : l_lst) {

			l_pE.setPaenPageId(p_page);
			l_pE.setPaenActive(true);
			l_pE.setPaenCreatedBy("BENITEZ.ABNER");
			l_pE.setPaenUpdateBy("BENITEZ.ABNER");

			pageService.savePageEntity(l_pE);
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping( value = "/appWizard/pageEntity/save/{pageId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<String> jsonCreatePageEntity( @RequestBody PageEntity p_pagEnt, @PathVariable( "pageId" ) BigDecimal p_pageId ) {

		// boolean l_isCreate = p_pagEnt.getPaenId() == null;

		if (p_pageId == null) {
			return new ResponseEntity<>("not exit entity on page", HttpStatus.NO_CONTENT);
		}

		Page l_pag = pageService.findPageById(p_pageId);
		p_pagEnt.setPaenPageId(l_pag);
		p_pagEnt.setPaenCreatedBy("BEITEZ.ABNER");
		p_pagEnt.setPaenUpdateBy("BEITEZ.ABNER");
		pageService.savePageEntity(p_pagEnt);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping( value = "/appWizard/pageEntity/{pagEntId}/delete", method = RequestMethod.DELETE )
	@ResponseBody
	public ResponseEntity<String> jsonDeleteEntityFromPageByIdPageEntity( @PathVariable( "pagEntId" ) BigDecimal p_pagEntId ) {

		PageEntity l_pagEnt = pageService.findPageEntityById(p_pagEntId);

		if (l_pagEnt == null)
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND

		pageService.deletePageEntity(l_pagEnt);
		return new ResponseEntity<String>("ok", HttpStatus.OK); // ResponseEntity<String>
	}
}
