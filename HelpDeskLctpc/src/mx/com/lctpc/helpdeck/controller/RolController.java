package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.ApplicationRole;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.service.ApplicationRoleService;
import mx.com.lctpc.helpdeck.service.ApplicationService;
import mx.com.lctpc.helpdeck.service.RolService;

@Controller
public class RolController {
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private ApplicationService appService;
	
	@Autowired
	private ApplicationRoleService appRolService;
	
	@RequestMapping("/roles")
	public String showRoles(Model model, @ModelAttribute("resultado") String p_resultado ){
		//List<Rol> roles = rolService.findAll();
		Rol l_rol = new Rol();
		model.addAttribute("rol", l_rol);
		
		return "roles";
	}
	
	@RequestMapping("/rolFormulario")
	public String showRolForm(Model model){
		model.addAttribute("rol", new Rol());
		
		return "rolForm";
	}
	
	@RequestMapping(value="/rolFormulario/save", method=RequestMethod.POST )
	public String showRolFormSave(@ModelAttribute("rol") Rol p_rol, Model model ){
		rolService.saveOrUpdateRol(p_rol);
		
		return "redirect:/roles";
	}
	
	@RequestMapping( "/rolFormulario/{rolId}/update" )
	public String showUpdateRol( Model model, @PathVariable( "rolId" ) BigDecimal p_rolId ) {

		Rol l_rol = rolService.findRolById(p_rolId);
		model.addAttribute("rol", l_rol);
/*
		List<ApplicationRole> l_appRoles = rolService.findApplicationFromRolById(p_rolId);
		model.addAttribute("appAsigned", l_appRoles);
		
		List<UserRole> l_userRoles = rolService.findUserFromRolById(p_rolId);
		model.addAttribute("usersAsigned", l_userRoles);
*/		
		return "rolForm";
	}
	
	@RequestMapping( value = "/appWizard/roles/save/{appId}", method= RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE  )
	@ResponseBody
	public ResponseEntity<String> showAppWizardSave( @RequestBody Rol p_rol, @PathVariable( "appId" ) BigDecimal p_appId ) {
		
		boolean l_isCreate = p_rol.getRoleId() == null;
		
		if(p_appId == null){
			return new ResponseEntity<String>("Not exist application",HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
				
		Application l_app = appService.findApplicationById(p_appId);
		ApplicationRole l_appRol = new ApplicationRole();

		p_rol.setRoleCreatedBy("BENITEZ.ABNER");
		p_rol.setRoleUpdateBy("BENITEZ.ABNER");
		rolService.saveOrUpdateRol(p_rol);
		
		if(l_isCreate){
			l_appRol.setAproCreatedBy("BENITEZ.ABNER");
			l_appRol.setAproUpdateBy("BENITEZ.ABNER");
			l_appRol.setAproRoleId(p_rol);
			l_appRol.setAproAppnId(l_app);
			
			appRolService.saveOrUpdate(l_appRol);
		}
		
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	
	@RequestMapping( value = "/appWizard/roles/save", method= RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE  )
	@ResponseBody
	public ResponseEntity<String> showAppWizardSave( @RequestBody Rol p_rol ) {
		p_rol.setRoleCreatedBy("BENITEZ.ABNER");
		p_rol.setRoleUpdateBy("BENITEZ.ABNER");
		rolService.saveOrUpdateRol(p_rol);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
}
