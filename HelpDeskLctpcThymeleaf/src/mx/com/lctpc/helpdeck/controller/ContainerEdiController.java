package mx.com.lctpc.helpdeck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.com.lctpc.helpdeck.pojo.ContainerEdi;
import mx.com.lctpc.helpdeck.service.ContainerEdiService;

@Controller
public class ContainerEdiController {
	
	@Autowired
	private ContainerEdiService g_containerEdiService; 
	
	
	@RequestMapping("/monitoreoEdi")
	public String showMonitoreoEdi(Model model,
			@ModelAttribute("resultado") String p_resultado){
		
		ContainerEdi l_cntrEdi =  new ContainerEdi(); 
		model.addAttribute("containerEdi", l_cntrEdi);
		model.addAttribute("resultado", p_resultado);
		
		return "monitoreoEdi";
	}
	
	@RequestMapping(value="/monitoreoEdi/executeCoarri", method=RequestMethod.POST)
	public String showMonitoreoEdiExecuteCoarri(@ModelAttribute("containerEdi") ContainerEdi p_cntrForm,
			Model p_model, RedirectAttributes ra){
		
		/*
		p_model.addAttribute("p_cntrForm", p_cntrForm);
		*/
		System.out.println("Fecga Ingreso: " + p_cntrForm.getFinalDate());
		
		if(g_containerEdiService.executeCoarri(p_cntrForm)){
			ra.addFlashAttribute("resultado", "Ejecucion correcta del Coarri");
		}
		else{
			ra.addFlashAttribute("resultado", "Error al generar el archivo coarri");
		}
		
		
		return "redirect:/monitoreoEdi";		
		
	}
	
	
}
