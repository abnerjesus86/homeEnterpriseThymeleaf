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

import mx.com.lctpc.helpdeck.pojo.AEntities;
import mx.com.lctpc.helpdeck.service.EntityService;

@Controller
public class EntityController {
	
	@Autowired
	private EntityService entityService;

	@RequestMapping( "/entities" )
	public String showApplications( Model model ) {
		List<AEntities> l_entts = entityService.findAllEntity();
		model.addAttribute("entts", l_entts);
		return "entities";
	}

	@RequestMapping( "/entityForm" )
	public String showApplicationForm( Model model ) {
		AEntities l_ent = new AEntities();
		model.addAttribute("entt", l_ent);

		return "entityForm";
	}

	@RequestMapping( value = "/entityForm/save", method = RequestMethod.POST )
	public String showAppFormSave( @ModelAttribute( "entt" ) AEntities p_ent, Model model ) {
		entityService.saveOrUpdateEntity(p_ent);
		return "redirect:/catalogManager";
	}
	
	@RequestMapping( "/entityForm/{enttId}/update" )
	public String showUpdateApp( Model model, @PathVariable( "enttId" ) BigDecimal p_enttId ) {

		AEntities l_ent = entityService.findEntityById(p_enttId);
		model.addAttribute("entt", l_ent);

		return "entityForm";
	}
	
	@RequestMapping( value="/entityForm/{enttId}/delete")
	@ResponseBody
	public ResponseEntity<String> showDeleteEntity( Model model, @PathVariable( "enttId" ) BigDecimal p_enttId ) {
		System.out.println("Entro al borrar...");
		
		AEntities l_ent = entityService.findEntityById(p_enttId);
		
		entityService.deleteEntity(l_ent);
		
		return new ResponseEntity<String>("ok",HttpStatus.OK); //ResponseEntity<String>
	}
	
}
