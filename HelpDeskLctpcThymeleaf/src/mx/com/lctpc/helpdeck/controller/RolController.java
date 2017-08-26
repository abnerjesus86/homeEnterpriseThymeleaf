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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserRole;
import mx.com.lctpc.helpdeck.service.RolService;
import mx.com.lctpc.helpdeck.service.UsersService;

@Controller
@RequestMapping("/api/v1.0/")
public class RolController {
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private UsersService usersService;
	
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
	
	@RequestMapping( value = "/appWizard/roles/save", method= RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE  )
	@ResponseBody
	public ResponseEntity<String> showAppWizardUpdate( @RequestBody Rol p_rol ) {
		
		p_rol.setRoleCreatedBy("BENITEZ.ABNER");
		p_rol.setRoleUpdateBy("BENITEZ.ABNER");
		rolService.saveOrUpdateRol(p_rol);
	
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	
	@RequestMapping( value = "/appWizard/roles/save", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE  )
	@ResponseBody
	public ResponseEntity<String> showAppWizardSave( @RequestBody Rol p_rol ) {
		//p_rol.setRoleCreatedBy("BENITEZ.ABNER");
		p_rol.setRoleUpdateBy("BENITEZ.ABNER");
		rolService.saveOrUpdateRol(p_rol);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	
	@PostMapping( value = "/userRole/{userId}/{roleId}" )
	@ResponseBody
	public ResponseEntity<String> createUserRol( @PathVariable( "userId" ) BigDecimal p_userId, @PathVariable( "roleId" ) BigDecimal p_roleId ) {
		
		User l_user = usersService.findUserById(p_userId);
		
		if (l_user == null) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		
		Rol l_rol = rolService.findRolById(p_roleId);
		
		if (l_rol == null) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		
		if(!usersService.existsRolesUserByIds(p_userId, p_roleId).isEmpty()){
			return new ResponseEntity<String>("The user already has Role assigned, Role active.",HttpStatus.NOT_ACCEPTABLE);// You many decide to return HttpStatus.NOT_FOUND
		}
		
		UserRole l_assignRol = new UserRole();
		l_assignRol.setUsroCreatedBy("BENITEZ.ABNER");
		l_assignRol.setUsroUpdateBy("BENITEZ.ABNER");
		l_assignRol.setUsroUserId(l_user);
		l_assignRol.setUsroRolId( l_rol );
		l_assignRol.setUsroActive(true);
		
		rolService.saveOrUpdateUserRole(l_assignRol);

		return new ResponseEntity<String>("ok", HttpStatus.CREATED); // ResponseEntity<String>
	}
	
}
