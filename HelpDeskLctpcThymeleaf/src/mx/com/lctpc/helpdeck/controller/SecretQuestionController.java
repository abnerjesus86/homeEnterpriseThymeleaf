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

import mx.com.lctpc.helpdeck.pojo.SecretQuestion;
import mx.com.lctpc.helpdeck.service.SecretQuestionService;

@Controller
public class SecretQuestionController {
	
	@Autowired
	private SecretQuestionService secretQuestionService;
	
	@RequestMapping( "/secretQuestions" )
	public String showApplications( Model model ) {
		
		List<SecretQuestion> l_scqs = secretQuestionService.findAllSecretQuestion();
		model.addAttribute("sctQs", l_scqs);
		return "secretQuestions";
	}

	@RequestMapping( "/secretQuestionForm" )
	public String showApplicationForm( Model model ) {
		SecretQuestion l_scQ = new SecretQuestion();
		model.addAttribute("sctQ", l_scQ);

		return "secretQuestionForm";
	}

	@RequestMapping( value = "/secretQuestionForm/save", method = RequestMethod.POST )
	public String showAppFormSave( @ModelAttribute( "sctQ" ) SecretQuestion p_scQ, Model model ) {
		secretQuestionService.saveOrUpdateSecretQuestion(p_scQ);
		return "redirect:/catalogManager";
	}
	
	@RequestMapping( "/secretQuestionForm/{sequId}/update" )
	public String showUpdateApp( Model model, @PathVariable( "sequId" ) BigDecimal p_sequId ) {

		SecretQuestion l_scQ = secretQuestionService.findSecretQuestionById(p_sequId);
		model.addAttribute("sctQ", l_scQ);

		return "secretQuestionForm";
	}
	
	@RequestMapping( value="/secretQuestionForm/{sequId}/delete")
	@ResponseBody
	public ResponseEntity<String> showDeleteSecretQuestion( Model model, @PathVariable( "sequId" ) BigDecimal p_sequId ) {
		SecretQuestion l_scq = secretQuestionService.findSecretQuestionById(p_sequId);
		secretQuestionService.deleteSecretQuestion(l_scq);
		
		return new ResponseEntity<String>("ok",HttpStatus.OK); //ResponseEntity<String>
	}
	
}
