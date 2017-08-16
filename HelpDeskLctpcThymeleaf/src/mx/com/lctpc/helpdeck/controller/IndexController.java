package mx.com.lctpc.helpdeck.controller;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.lctpc.helpdeck.pojo.AccountInformation;
import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.VEmp;
import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.service.UsersService;

@Controller
public class IndexController {

	@Autowired
	private UsersService usersService;

	@RequestMapping( "/" )
	public String showIndex() {
		
		return "indexMain";
	}
	
	@RequestMapping( "/login" )
	public String login(){
		
		return "login";
	}
	
	@RequestMapping( "/denied" )
	public String denied(){
		
		return "404";
	}
	
	@RequestMapping( "/appWizards" )
	public String showAppWizards1( Model p_model, @RequestParam("id") BigDecimal p_id ) {
		
		return "application-wizards";
	}
	
	@RequestMapping( "/appWizards/{appId}" )
	public String showAppWizards( Model p_model,  @PathVariable( "appId" ) BigDecimal p_appId ) {
		
		return "application-wizards";
	}
	
	@RequestMapping( "/exampleTemplate" )
	public String showEjemplo( Model model ) {
		User l_user = new User();
		AccountInformation l_accInf = new AccountInformation();

		l_user.setAccountInf(l_accInf);

		model.addAttribute("user", l_user);

		return "exampleTemplate";
	}

	@RequestMapping( "/users" )
	public String showUsers( Model model, @ModelAttribute( "resultado" ) String p_resultado ) {

		List<User> users = usersService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("resultado1", p_resultado);

		return "admin_user";
	}

	@RequestMapping( "/catalogManager" )
	public String showCatalog() {

		return "catalogos";
	}

	@RequestMapping( "/applicationWizard" )
	public String showAppWizard( Model p_model ) {
		Application l_app = new Application();
		
		p_model.addAttribute("appn", l_app);
		p_model.addAttribute("rol", new Rol());
		p_model.addAttribute("page", new Page());
		return "application-wizard";
	}

	@RequestMapping( value = "/userFormulario", method = RequestMethod.GET )
	public String showUserForm( Model model ) {
		User l_user = new User();
		AccountInformation l_accInf = new AccountInformation();
		l_user.setAccountInf(l_accInf);

		model.addAttribute("user", l_user);
		System.out.println("emtrp en el GET sin parametros...");
		return "fragments/userForm";
	}
	
	/*@RequestMapping( value = "/userFormulario/{userId}", method = RequestMethod.GET )
	public String showUpdateUser( Model model, @PathVariable( "userId" ) BigDecimal p_userId ) {
		User l_user = usersService.findUserById(p_userId);
		
		model.addAttribute("user", l_user);

		return "fragments/userForm";
	}*/
	
	@RequestMapping( value = "/userFormulario/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<User> showUpdateUser( @PathVariable( "userId" ) BigDecimal p_userId ) {
		
		User l_user = usersService.findUserById(p_userId);
		
		if( l_user == null)
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		
		return new ResponseEntity<User>(l_user, HttpStatus.OK );
	}
	
	@RequestMapping( value = "/userFormulario", method = {RequestMethod.POST, RequestMethod.PUT}, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<User> showUserFormSave( @RequestBody User p_user, Model model ) {
		boolean l_isNew = p_user.getUserId() == null ;
		System.out.println("Entro al guardado..."+ p_user);
		AccountInformation l_accInf = p_user.getAccountInf();
		l_accInf.setUser(p_user);
		p_user.setAccountInf(l_accInf);
		
		usersService.saveOrUpdateUser(p_user);
		
		return new ResponseEntity<User>(p_user, l_isNew ? HttpStatus.CREATED : HttpStatus.OK );
	}

	@RequestMapping( value = "/userFormulario/{userId}", method = RequestMethod.DELETE )
	@ResponseBody
	public ResponseEntity<String> showDeleteUser( Model model, @PathVariable( "userId" ) BigDecimal p_userId ) {
		
		User l_user = usersService.findUserById(p_userId);
		
		if (l_user == null) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);// You many decide to return
																						// HttpStatus.NOT_FOUND
		}
		
		usersService.deleteUser(l_user);

		return new ResponseEntity<String>("ok", HttpStatus.OK); // ResponseEntity<String>
	}

}
