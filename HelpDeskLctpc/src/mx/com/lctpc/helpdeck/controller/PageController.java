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
import org.springframework.web.bind.annotation.RequestParam;
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

	@InitBinder
	protected void initBinder( HttpServletRequest request, ServletRequestDataBinder binder ) throws Exception {
		binder.registerCustomEditor(List.class, "pageEntities", new CustomCollectionEditor(List.class) {
			protected Object convertElement( Object element ) {
				if (element instanceof PageEntity) {
					System.out.println("Converting from PageEnt to Ent: " + element);
					return ((PageEntity) element).getPaenEnttId();
				}
				if (element instanceof String) {
					// Transformar al dato dessea para guardar
					System.out.println("Looking up staff for id " + element + ": ");

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
					System.out.println("Converting from AEntities to AEntities: " + element + " : " + ((AEntities) element).getEnttId());
					// BigDecimal l_d = ((AEntities) element).getEnttId();

					return null;
					/*
					 * return ((AEntities)element).getEnttId().toPlainString();
					 */
				}
				if (element instanceof BigDecimal) {
					/*
					 * AEntities l_ent = entityService.findEntityById(
					 * (BigDecimal) element); System.out.
					 * println("Looking up AEntity BigDecimal from String : " +
					 * element +" : "+l_ent); return l_ent; 41 6 1 42 6 2 43 6 4
					 */
					System.out.println("Looking up AEntity BigDecimal from String : " + element);
					// BigDecimal ld = new BigDecimal(element.toString());

					// System.out.println("Looking up AEntity BigDecimal from
					// String : " + element);
					// return ld.toPlainString();
				}
				System.out.println("Don't know what to do with: " + element.getClass());
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

		return "pageForm";
	}

	@RequestMapping( value = "/pageForm/save", method = RequestMethod.POST )
	public String showPageFormSave( @ModelAttribute( "page" ) Page p_pag, Model model ) {
		System.out.println("save pageForm " + p_pag.getPageEntities());

		pageService.saveOrUpdatePage(p_pag);
		return "redirect:/catalogManager";
	}

	@RequestMapping( "/pageForm/{pageId}/update" )
	public String showUpdatePage( Model p_model, @PathVariable( "pageId" ) BigDecimal p_pagId ) {

		Page l_page = pageService.findPageById(p_pagId);
		// List<PageEntity> l_lstPagEnt = pageService.findEntitiesActiveFromPage(p_pagId);
		// l_page.setPageEntities(l_lstPagEnt);
		// System.out.println("Page desde pagEntity con ID " +
		// l_page.getPageEntities() );
		/*
		 * List<AEntities> l_lstEnt = new ArrayList<AEntities>(); for(PageEntity
		 * l_ent : pageService.findEntitiesActiveFromPage(p_pagId)){
		 * l_lstEnt.add(l_ent.getPaenEnttId()); System.out.println("save " +
		 * l_ent.getPaenEnttId()); }
		 * l_page.setEntities(l_lstEnt);
		 */
		System.out.println("Page desde pagEntity con ID " + l_page.getPageEntities());
		p_model.addAttribute("page", l_page);
		p_model.addAttribute("pagesMasterList", pageService.findAllPage());
		p_model.addAttribute("entitiesList", entityService.findAllEntity());
		// p_model.addAttribute("entitiesList",
		// pageService.findEntitiesActiveLeftJoinFromPage(p_pagId) );
		return "pageForm";
	}

	@RequestMapping( value = { "/pageForm/{pageId}/delete"} )
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
		Application l_appn =  appService.findApplicationById(p_appId);
		
		//l_page.getApplications().add(l_appn);
		pageService.deletePageFromApplicationById(l_page, l_appn);

		return new ResponseEntity<String>("ok", HttpStatus.OK); // ResponseEntity<String>
	}
	

	@RequestMapping( value = "/appWizard/page/save/{appId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> jsonUpdatePage(@RequestBody Page p_page, @PathVariable( "appId" ) BigDecimal p_appId ){

		if(p_appId == null){
			return new ResponseEntity<>("not exit Applicacion", HttpStatus.NO_CONTENT);
		}
		
		Application l_pag = appService.findApplicationById(p_appId);
		
		if(l_pag == null){
			return new ResponseEntity<>("not exit Applicacion", HttpStatus.NO_CONTENT);
		}
		
		List<PageEntity> l_pageEnt = p_page.getPageEntities();
		System.out.println("valor de entity " + l_pageEnt.size());
		
		p_page.setPageCreatedBy("BENITEZ.ABNER");
		p_page.setPageUpdateBy("BENITEZ.ABNER");
		
		pageService.saveOrUpdatePage(p_page);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping( value = "/appWizard/page/save/{appId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> jsonCreatePage(@RequestBody Page p_page, @PathVariable( "appId" ) BigDecimal p_appId ){
		
		List<PageEntity> l_lst = p_page.getPageEntities();
		p_page.setPageEntities(null);
		
		p_page.setPageCreatedBy("BEITEZ.ABNER");
		p_page.setPageUpdateBy("BEITEZ.ABNER");
		
		pageService.saveOrUpdatePage(p_page);
		
		for(PageEntity l_pE : l_lst){
			
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
