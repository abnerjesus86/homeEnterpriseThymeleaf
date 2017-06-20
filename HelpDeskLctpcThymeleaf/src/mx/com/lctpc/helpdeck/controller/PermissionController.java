package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;
import java.util.List;

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

import mx.com.lctpc.helpdeck.pojo.Permission;
import mx.com.lctpc.helpdeck.service.PermissionService;

@Controller
public class PermissionController {
	
	@Autowired
	private PermissionService permService;
	
	@RequestMapping("/permissions")
	public String showPermission(Model model){
		List<Permission> l_perms = permService.findAllPermisions();
		model.addAttribute("perms", l_perms);
		return "permissions";
	}
	
	@RequestMapping("/permForm")
	public String showPermissionForm(Model model){
		Permission l_perm = new Permission();
		model.addAttribute("perm", l_perm);
		return "permissionForm";
	}
	
	@RequestMapping(value="/permForm/save", method=RequestMethod.POST)
	public String showPermFormSave(@ModelAttribute("perm") Permission p_perm, Model model ){
		permService.saveOrUpdatePermission(p_perm);
		return "redirect:/catalogManager";
	}
	
	@RequestMapping("/permForm/{permId}/update")
	public String showPermUpdateApp(Model model, @PathVariable("permId") BigDecimal p_permId ){
		Permission l_perm = permService.findPermissionById(p_permId);
		model.addAttribute("perm", l_perm);
		return "permissionForm";
	}
	
	@RequestMapping( value="/permForm/{permId}/delete")
	@ResponseBody
	public ResponseEntity<String> showDeletePerm( Model model, @PathVariable( "permId" ) BigDecimal p_permId ) {
		System.out.println("Entro al borrar...");
		
		Permission l_perm = permService.findPermissionById(p_permId);
		
		permService.deletePermission(l_perm);;
		
		//return "redirect:/exampleTemplate";
		return new ResponseEntity<String>("ok",HttpStatus.OK); //ResponseEntity<String>
	}
	
}
