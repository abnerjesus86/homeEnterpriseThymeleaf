package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.com.lctpc.helpdeck.pojo.AccountInformation;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserApplication;
import mx.com.lctpc.helpdeck.pojo.UserRole;
import mx.com.lctpc.helpdeck.service.PasswordService;
import mx.com.lctpc.helpdeck.service.UsersService;

@RestController
@RequestMapping("/api/v1.0/user")
public class UserController {

	@Autowired
	private UsersService userService;
	
	@Autowired
	private PasswordService passService;
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, List<User>>> getListAllUsersWithData() {
		List<User> users = userService.findAll();
		Map<String, List<User>> l_map = new HashMap<String, List<User>>();
		
		if (users.isEmpty()) {
			return new ResponseEntity<Map<String, List<User>>>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		
		l_map.put("data", users);
		return new ResponseEntity<Map<String, List<User>>>(l_map, HttpStatus.OK);
	}
	
	@GetMapping( value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<User> getUser( @PathVariable( "userId" ) BigDecimal p_userId ) {
		
		User l_user = userService.findUserById(p_userId);
		
		if( l_user == null)
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		
		return new ResponseEntity<User>(l_user, HttpStatus.OK );
	}
	
	@RequestMapping( value = "/", method = {RequestMethod.POST}/*, consumes = MediaType.APPLICATION_JSON_VALUE*/ )
	public ResponseEntity<User> saveUserForm( @RequestBody User p_user, @RequestParam("uploadfileImageUser") MultipartFile p_uploadfileImage ) {
		
		p_user.setUserCreatedBy( SecurityContextHolder.getContext().getAuthentication().getName() );
		
		AccountInformation l_acount = new AccountInformation();
		
		if( !p_user.getAccountInf().equals(l_acount) ){
					
			if(p_user.getAccountInf().getAcinUserId() == null)
				p_user.getAccountInf().setAcinCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			else
				p_user.getAccountInf().setAcinUpdateBy(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		else
			p_user.setAccountInf(null);
		
		userService.saveOrUpdateUser(p_user);
		
		return new ResponseEntity<User>(p_user,HttpStatus.CREATED );
		//return new ResponseEntity<User>(HttpStatus.OK );
		//return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping( value = "/", method = {RequestMethod.PUT}/*, consumes = MediaType.APPLICATION_JSON_VALUE*/ )
	public ResponseEntity<User> updateUserForm( @RequestBody User p_user ) {
		//((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
		
		p_user.setUserUpdateBy(SecurityContextHolder.getContext().getAuthentication().getName());
		
		AccountInformation l_acount = new AccountInformation();
		
		if( !p_user.getAccountInf().equals(l_acount) ){
						
			if(p_user.getAccountInf().getAcinUserId() == null){
				p_user.getAccountInf().setAcinCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				p_user.getAccountInf().setAcinUserId(p_user.getUserId());
			}else
				p_user.getAccountInf().setAcinUpdateBy(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		else
			p_user.setAccountInf(null);
		
		userService.saveOrUpdateUser(p_user);
		
		return new ResponseEntity<User>(p_user, HttpStatus.OK );
		//return new ResponseEntity<User>(HttpStatus.OK );
	}

	@DeleteMapping( value = "/{userId}" )
	public ResponseEntity<String> deleteUser( Model model, @PathVariable( "userId" ) BigDecimal p_userId ) {
		
		User l_user = userService.findUserById(p_userId);
		
		if (l_user == null) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);// You many decide to return  HttpStatus.NOT_FOUND
		}
		
		userService.deleteUser(l_user);

		return new ResponseEntity<String>("ok", HttpStatus.OK); // ResponseEntity<String>
	}
	
	@GetMapping( value = { "/rolesAssigned/{p_IdUser}" }, produces = "application/json" )
	public ResponseEntity<Map<String, List<UserRole>>> getUserRolesAssignedActive( @PathVariable( "p_IdUser" ) BigDecimal p_userId ) {

		List<UserRole> l_userRoles = userService.findRolesFromUserById(p_userId);

		Map<String, List<UserRole>> l_map = new HashMap<String, List<UserRole>>();

		if (l_userRoles.isEmpty()) {
			
			return new ResponseEntity<Map<String, List<UserRole>>>(HttpStatus.NOT_FOUND);// You many decide to return
																							// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_userRoles);
		return new ResponseEntity<Map<String, List<UserRole>>>(l_map, HttpStatus.OK);
	}
	
	@GetMapping( value = { "/appAssigned/{p_IdUser}" }, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<UserApplication>>> getUserAppsAssignedActive( @PathVariable( "p_IdUser" ) BigDecimal p_userId ) {

		List<UserApplication> l_userApps = userService.findApplicationFromUserById(p_userId);

		Map<String, List<UserApplication>> l_map = new HashMap<String, List<UserApplication>>();

		if (l_userApps.isEmpty()) {
			return new ResponseEntity<Map<String, List<UserApplication>>>(HttpStatus.NOT_FOUND);// You many decide to return HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_userApps);
		return new ResponseEntity<Map<String, List<UserApplication>>>(l_map, HttpStatus.OK);
	}
	
	@PostMapping(value="/reset")
	public ResponseEntity<String> resetPassword(@RequestParam(value = "username") String p_username, 
												@RequestParam(value = "passNew", required=false) String p_passNew, 
												@RequestParam(value = "reqNewPass", required=false) boolean p_reqNewPass){
		
		String l_res = null;
		try{
			l_res = passService.resetPassword(p_username, p_passNew, p_reqNewPass);
			return new ResponseEntity<String>(l_res, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping(value="/reset" )
	public ResponseEntity<String> resetPasswordPut(	@RequestParam(value = "username") String p_username, 
													@RequestParam(value = "passNew", required=false) String p_passNew, 
													@RequestParam(value = "reqNewPass", required=false) boolean p_reqNewPass){
		System.out.println("Valor del boolean "+p_reqNewPass);
		String l_res = null;
		try{
			l_res = passService.resetPassword(p_username, p_passNew, p_reqNewPass);
			return new ResponseEntity<String>(l_res, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping( value="/changePass", method = RequestMethod.POST )
	public ResponseEntity<String> changePassword(@RequestParam(value = "username") String p_username, 
													@RequestParam(value = "passCurr", required=false ) String p_passCurrent,
													@RequestParam(value = "passNew", required=false ) String p_passNew
													){
		
		String l_res = null;
		try{
			l_res = passService.changePassword(p_username, p_passCurrent, p_passNew);
			return new ResponseEntity<String>(l_res, HttpStatus.OK);
		}catch(Exception ex){
			
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
}
