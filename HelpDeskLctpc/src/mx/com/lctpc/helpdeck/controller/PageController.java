package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.lctpc.helpdeck.dao.PageDao;
import mx.com.lctpc.helpdeck.pojo.AEntities;
import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.pojo.PageEntity;
import mx.com.lctpc.helpdeck.service.EntityService;
import mx.com.lctpc.helpdeck.service.PageService;

@Controller
public class PageController {
	@Autowired
	private PageService pageService;
	
	@Autowired
	private EntityService entityService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(List.class, "pageEntities", new CustomCollectionEditor(List.class) {
			protected Object convertElement(Object element) {
				if (element instanceof PageEntity) {
					System.out.println("Converting from PageEnt to Ent: " + element);
					return ((PageEntity)element).getPaenEnttId();
				}
				if (element instanceof String) {
					//Transformar al dato dessea para guardar
					System.out.println("Looking up staff for id " + element + ": " );
					
					/*String l_ids[] = element.toString().split("\\|");
					PageEntity l_pagEnt = new PageEntity();
					if(l_ids[0].equals("NEW")){
						BigDecimal l_idEnt =  new BigDecimal(l_ids[1]);
						AEntities l_ent = entityService.findEntityById(l_idEnt);
						l_pagEnt.setPaenEnttId(l_ent);
						
					}else{
						BigDecimal l_idPagEnt =  new BigDecimal(l_ids[0]);
						l_pagEnt = pageService.findPageEntityById(l_idPagEnt);
						
					}

					return l_pagEnt;*/
					return element;
				}
				if (element instanceof AEntities) {
					System.out.println("Converting from AEntities to AEntities: " + element + " : " +((AEntities)element).getEnttId() );
					BigDecimal l_d = ((AEntities)element).getEnttId();
					
					return null;
					/*return ((AEntities)element).getEnttId().toPlainString();*/
				}
				if (element instanceof BigDecimal) {
					/*AEntities l_ent = entityService.findEntityById( (BigDecimal) element);
					System.out.println("Looking up AEntity BigDecimal from String : " + element +" : "+l_ent);
					return l_ent;
					41	6	1
					42	6	2
					43	6	4*/
					System.out.println("Looking up AEntity BigDecimal from String : " + element);
					BigDecimal ld= new BigDecimal(element.toString());
					if(ld.intValue() == 1){
						return pageService.findPageEntityById(new BigDecimal(41));
					}else if(ld.intValue() == 2){
						return pageService.findPageEntityById(new BigDecimal(42));
					}else if(ld.intValue() == 4){
						return pageService.findPageEntityById(new BigDecimal(43));
					}
					
					//System.out.println("Looking up AEntity BigDecimal from String : " + element);
					//return ld.toPlainString();
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
		System.out.println("save pageForm "+ p_pag.getPageEntities());
		
		pageService.saveOrUpdatePage(p_pag);
		return "redirect:/catalogManager";
	}
	
	@RequestMapping( "/pageForm/{pageId}/update" )
	public String showUpdatePage( Model p_model, @PathVariable( "pageId" ) BigDecimal p_pagId ) {
		
		Page l_page = pageService.findPageById(p_pagId);
		List<PageEntity> l_lstPagEnt = pageService.findEntitiesActiveFromPage(p_pagId);
		l_page.setPageEntities(l_lstPagEnt);
		//System.out.println("Page desde pagEntity con ID " + l_page.getPageEntities() );
		/*List<AEntities> l_lstEnt = new ArrayList<AEntities>();
		for(PageEntity l_ent : pageService.findEntitiesActiveFromPage(p_pagId)){
			l_lstEnt.add(l_ent.getPaenEnttId());
			System.out.println("save " + l_ent.getPaenEnttId());
		}
		
		l_page.setEntities(l_lstEnt);*/
		System.out.println("Page desde pagEntity con ID " + l_page.getPageEntities() );
		p_model.addAttribute("entitiesList", entityService.findAllEntity() );
		p_model.addAttribute("page", l_page);
		p_model.addAttribute("pagesMasterList", pageService.findAllPage());
		//p_model.addAttribute("entitiesList", pageService.findEntitiesActiveLeftJoinFromPage(p_pagId) );
		return "pageForm";
	}
	
	@RequestMapping( value="/pageForm/{pageId}/delete")
	@ResponseBody
	public ResponseEntity<String> showDeletePage( Model model, @PathVariable( "pageId" ) BigDecimal p_pageId ) {
		Page l_page = pageService.findPageById(p_pageId);
		pageService.deletePage(l_page);
		
		return new ResponseEntity<String>("ok",HttpStatus.OK); //ResponseEntity<String>
	}
	
}
