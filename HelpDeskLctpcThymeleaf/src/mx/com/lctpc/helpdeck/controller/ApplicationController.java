package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.service.ApplicationService;
import mx.com.lctpc.helpdeck.service.OwnerService;
import mx.com.lctpc.helpdeck.service.PageService;

@Controller
public class ApplicationController {

	@Autowired
	private ApplicationService appService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping( "/applications" )
	public String showApplications( Model p_model ) {
		List<Application> l_appns = appService.findAllApplication();
		p_model.addAttribute("appns", l_appns);
		return "applications";
	}
	
	@RequestMapping( "/appForm" )
	public String showApplicationForm( Model p_model ) {
		Application l_app = new Application();
		Map<BigDecimal, String> l_map = appService.findPlatform();
		
		p_model.addAttribute("appn", l_app);
		p_model.addAttribute("platformList", l_map);
		p_model.addAttribute("appnsMasterList", appService.findAllApplication());
		p_model.addAttribute("ownersList", ownerService.findOwnersActive());
		
		return "fragments/applicationForm";
	}

	@RequestMapping( value = "/appForm/save", method = RequestMethod.POST )
	public String showAppFormSave( @ModelAttribute( "appn" ) Application p_app, Model p_model ) {
		appService.saveOrUpdate(p_app);
		
		return "redirect:/catalogManager";
	}
	
	@RequestMapping( "/appForm/{appId}/update" )
	public String showUpdateApp( Model p_model, @PathVariable( "appId" ) BigDecimal p_appId ) {

		Application l_app = appService.findApplicationById(p_appId);
		Map<BigDecimal, String> l_map = appService.findPlatform();
		
		p_model.addAttribute("appn", l_app);
		p_model.addAttribute("platformList", l_map);
		p_model.addAttribute("appnsMasterList", appService.findAllApplication());
		p_model.addAttribute("ownersList", ownerService.findOwnersActive());
		
		return "fragments/applicationForm";
	}
	
	@RequestMapping( value="/appForm/{appId}/delete")
	@ResponseBody
	public ResponseEntity<String> showDeleteApplication( Model model, @PathVariable( "appId" ) BigDecimal p_appId ) {
		Application l_app = appService.findApplicationById(p_appId);
		
		appService.deleteApplication(l_app);
		
		//return "redirect:/exampleTemplate";
		return new ResponseEntity<String>("ok",HttpStatus.OK); //ResponseEntity<String>
	}
	
	@RequestMapping( value = {"/appWizard"} )
	public String showAppWizardNew( Model p_model ) {
		
		Map<BigDecimal, String> l_map = appService.findPlatform();
		
		p_model.addAttribute("platformList", l_map);
		p_model.addAttribute("appnsMasterList", appService.findAllApplication());
		p_model.addAttribute("appn", new Application());
		
		p_model.addAttribute("rol", new Rol());
		p_model.addAttribute("page", new Page());
		
		return "application-wizard";

	}
	
	@RequestMapping( value = {"/appWizard/{appId}"} )
	public String showAppWizard( Model p_model, @PathVariable( "appId" ) BigDecimal p_appId ) {
		Application l_app= null;
		
		if(p_appId == null){
			l_app = new Application();
			p_model.addAttribute("appn", l_app);
		}else{
			l_app = appService.findApplicationById(p_appId);
			p_model.addAttribute("appn", l_app);
			
		}
		
		Map<BigDecimal, String> l_map = appService.findPlatform();
		p_model.addAttribute("platformList", l_map);
		p_model.addAttribute("appnsMasterList", appService.findAllApplication());
		p_model.addAttribute("pagesMasterList", pageService.findAllPage());
		//List<ApplicationRole> l_appRoles = appService.findRoleFromApplicationById(p_appId);
		//p_model.addAttribute("rolesAsigned", l_appRoles);
		
		p_model.addAttribute("rol", new Rol());
		p_model.addAttribute("page", new Page());
		
		return "application-wizard";

	}
	
	
	
	
	
}
