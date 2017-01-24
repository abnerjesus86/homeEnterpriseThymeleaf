package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.lctpc.helpdeck.pojo.AEntities;
import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.ApplicationRole;
import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.pojo.PageEntity;
import mx.com.lctpc.helpdeck.pojo.Permission;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.SecretQuestion;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserApplication;
import mx.com.lctpc.helpdeck.pojo.UserRole;
import mx.com.lctpc.helpdeck.pojo.SelectList;
import mx.com.lctpc.helpdeck.service.ApplicationService;
import mx.com.lctpc.helpdeck.service.EntityService;
import mx.com.lctpc.helpdeck.service.PageService;
import mx.com.lctpc.helpdeck.service.PermissionService;
import mx.com.lctpc.helpdeck.service.RolService;
import mx.com.lctpc.helpdeck.service.SecretQuestionService;
import mx.com.lctpc.helpdeck.service.UsersService;

@RestController
public class JsonController {

	@Autowired
	private UsersService userService;

	@Autowired
	private ApplicationService appService;

	@Autowired
	private RolService rolService;

	@Autowired
	private PageService pagService;

	@Autowired
	private PermissionService permService;

	@Autowired
	private SecretQuestionService secretQuestionService;

	@Autowired
	private EntityService entityService;

	@RequestMapping( value = "/getJsonUsers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<User>> showListAllUsers() {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping( value = "/getJsonUsers2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<User>>> showListAllUsersWithData() {
		List<User> users = userService.findAll();
		Map<String, List<User>> l_map = new HashMap<String, List<User>>();

		if (users.isEmpty()) {
			return new ResponseEntity<Map<String, List<User>>>(HttpStatus.NO_CONTENT);// You many decide to return
																						// HttpStatus.NOT_FOUND
		}
		l_map.put("data", users);
		return new ResponseEntity<Map<String, List<User>>>(l_map, HttpStatus.OK);
	}

	@RequestMapping( value = "/getJsonUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<User> showListAllUser() {
		User users = userService.findUserById(new BigDecimal(15));

		if (users == null) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<User>(users, HttpStatus.OK);
	}

	@RequestMapping( value = "/getJsonApps", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json" )
	public ResponseEntity<Map<String, List<Application>>> showAppsTable() {
		List<Application> l_apps = appService.findAllApplication();
		Map<String, List<Application>> l_map = new HashMap<String, List<Application>>();
		if (l_apps.isEmpty()) {
			return new ResponseEntity<Map<String, List<Application>>>(HttpStatus.NO_CONTENT);// You many decide to
																								// return
																								// HttpStatus.NOT_FOUND
		}
		l_map.put("data", l_apps);
		return new ResponseEntity<Map<String, List<Application>>>(l_map, HttpStatus.OK);
	}

	@RequestMapping( value = { "/getJsonUserRoles/{p_IdUser}" }, method = { RequestMethod.GET }, produces = "application/json" )
	public ResponseEntity<Map<String, List<UserRole>>> showJsonUserRoles( @PathVariable( "p_IdUser" ) BigDecimal p_userId ) {

		List<UserRole> l_userRoles = userService.findRolesFromUserById(p_userId);

		/*
		 * for(UserRole l_ur: l_userRoles){
		 * System.out.println(l_ur);
		 * }
		 */
		Map<String, List<UserRole>> l_map = new HashMap<String, List<UserRole>>();

		if (l_userRoles.isEmpty()) {
			return new ResponseEntity<Map<String, List<UserRole>>>(HttpStatus.NO_CONTENT);// You many decide to return
																							// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_userRoles);
		return new ResponseEntity<Map<String, List<UserRole>>>(l_map, HttpStatus.OK);
	}

	@RequestMapping( value = "/getJsonUserApps/{p_IdUser}", method = { RequestMethod.GET }, produces = "application/json" )
	public ResponseEntity<Map<String, List<UserApplication>>> showJsonUserApps( @PathVariable( "p_IdUser" ) BigDecimal p_userId ) {

		List<UserApplication> l_userApps = userService.findApplicationFromUserById(p_userId);

		Map<String, List<UserApplication>> l_map = new HashMap<String, List<UserApplication>>();

		if (l_userApps.isEmpty()) {
			return new ResponseEntity<Map<String, List<UserApplication>>>(HttpStatus.NO_CONTENT);// You many decide to
																									// return
																									// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_userApps);
		return new ResponseEntity<Map<String, List<UserApplication>>>(l_map, HttpStatus.OK);
	}

	@RequestMapping( value = "/getJsonRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Rol>>> showJsonRoles() {
		List<Rol> l_roles = rolService.findAll();
		Map<String, List<Rol>> l_map = new HashMap<String, List<Rol>>();
		if (l_roles.isEmpty()) {
			return new ResponseEntity<Map<String, List<Rol>>>(HttpStatus.NO_CONTENT);// You many decide to return
																						// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_roles);
		return new ResponseEntity<Map<String, List<Rol>>>(l_map, HttpStatus.OK);

	}

	@RequestMapping( value = "/getJsonPages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Page>>> showJsonPages() {
		List<Page> l_pages = pagService.findAllPage();
		
		if (l_pages.isEmpty()) {
			return new ResponseEntity<Map<String, List<Page>>>(HttpStatus.NO_CONTENT);// You many decide to return
																						// HttpStatus.NOT_FOUND
		}
		Map<String, List<Page>> l_map = new HashMap<String, List<Page>>();
		l_map.put("data", l_pages);
		return new ResponseEntity<Map<String, List<Page>>>(l_map, HttpStatus.OK);

	}
	
	@RequestMapping( value = "/getJsonPagesForSelect", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<SelectList>>> showJsonPagesForSelect() {
		List<Page> l_pages = pagService.findAllPage();
		if (l_pages.isEmpty()) {
			return new ResponseEntity<Map<String, List<SelectList>>>(HttpStatus.NO_CONTENT);// You many decide to return
																						// HttpStatus.NOT_FOUND
		}
		List<SelectList> l_lst = new ArrayList<SelectList>();
		for (Page l_pag : l_pages) {			
			l_lst.add(new SelectList(l_pag.getPageId().toPlainString(), l_pag.getPageDisplay()));
		}
		
		Map<String, List<SelectList>> l_map = new HashMap<String, List<SelectList>>();
		l_map.put("data", l_lst);
		return new ResponseEntity<Map<String, List<SelectList>>>(l_map, HttpStatus.OK);

	}
	@RequestMapping( value = "/getJsonPermisisons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Permission>>> showJsonPermission() {
		List<Permission> l_permissions = permService.findAllPermisions();
		Map<String, List<Permission>> l_map = new HashMap<String, List<Permission>>();
		if (l_permissions.isEmpty()) {
			return new ResponseEntity<Map<String, List<Permission>>>(HttpStatus.NO_CONTENT);// You many decide to return
																							// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_permissions);
		return new ResponseEntity<Map<String, List<Permission>>>(l_map, HttpStatus.OK);

	}

	@RequestMapping( value = "/getJsonSecretQuestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<SecretQuestion>>> showJsonsecretQuestions() {
		List<SecretQuestion> l_secretQuestions = secretQuestionService.findAllSecretQuestion();
		Map<String, List<SecretQuestion>> l_map = new HashMap<String, List<SecretQuestion>>();
		if (l_secretQuestions.isEmpty()) {
			return new ResponseEntity<Map<String, List<SecretQuestion>>>(HttpStatus.NO_CONTENT);// You many decide to
																								// return
																								// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_secretQuestions);
		return new ResponseEntity<Map<String, List<SecretQuestion>>>(l_map, HttpStatus.OK);

	}

	@RequestMapping( value = "/getJsonEntities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<AEntities>>> showJsonEntities() {
		List<AEntities> l_entities = entityService.findAllEntity();
		Map<String, List<AEntities>> l_map = new HashMap<String, List<AEntities>>();
		if (l_entities.isEmpty()) {
			return new ResponseEntity<Map<String, List<AEntities>>>(HttpStatus.NO_CONTENT);// You many decide to return
																							// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_entities);
		return new ResponseEntity<Map<String, List<AEntities>>>(l_map, HttpStatus.OK);

	}

	@RequestMapping( value = "/getJsonEntitiesForSelect", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<SelectList>>> showJsonEntitiesForSelect() {
		List<AEntities> l_entities = entityService.findAllEntity();
		List<SelectList> l_lst = new ArrayList<SelectList>();
		
		for (AEntities l_ent : l_entities) {			
			l_lst.add(new SelectList(l_ent.getEnttId().toPlainString(), l_ent.getEnttName()));
			
		}
		if (l_entities.isEmpty()) {
			return new ResponseEntity<Map<String, List<SelectList>>>(HttpStatus.NO_CONTENT);// You many decide to return
																							// HttpStatus.NOT_FOUND
		}
		Map<String, List<SelectList>> l_map = new HashMap<String, List<SelectList>>();
		l_map.put("data", l_lst);
		return new ResponseEntity<Map<String, List<SelectList>>>(l_map, HttpStatus.OK);

	}

	@RequestMapping( value = "/getJsonEntitiesActiveFromPage/{pagId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<PageEntity>>> showJsonEntitiesActiveFromPage( @PathVariable( "pagId" ) BigDecimal p_pagId ) {
		List<PageEntity> l_pagEnts = pagService.findEntitiesActiveFromPage(p_pagId);
		Map<String, List<PageEntity>> l_map = new HashMap<String, List<PageEntity>>();
		if (l_pagEnts.isEmpty()) {
			return new ResponseEntity<Map<String, List<PageEntity>>>(HttpStatus.NO_CONTENT);// You many decide to return
																							// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_pagEnts);
		return new ResponseEntity<Map<String, List<PageEntity>>>(l_map, HttpStatus.OK);

	}

	@RequestMapping( value = { "/getJsonRolesApps/{p_appId}" }, method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<ApplicationRole>>> showJsonRolesApps( @PathVariable( "p_appId" ) BigDecimal p_appId ) {

		List<ApplicationRole> l_roleApps = appService.findRoleFromApplicationById(p_appId);

		Map<String, List<ApplicationRole>> l_map = new HashMap<String, List<ApplicationRole>>();

		if (l_roleApps.isEmpty()) {
			return new ResponseEntity<Map<String, List<ApplicationRole>>>(HttpStatus.NO_CONTENT);// You many decide to
																									// return
																									// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_roleApps);
		return new ResponseEntity<Map<String, List<ApplicationRole>>>(l_map, HttpStatus.OK);
	}
	
	@RequestMapping( value = { "/getJsonPagesApps/{p_appId}" }, method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Page>>> showJsonPagesApps( @PathVariable( "p_appId" ) BigDecimal p_appId ) {

		/*Application l_app = appService.findApplicationById(p_appId);
		System.out.println("paso..");
		Map<String, List<Page>> l_map = new HashMap<String, List<Page>>();

		if (l_app == null) {
			return new ResponseEntity<Map<String, List<Page>>>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		System.out.println("paso 2...");
		if(l_app.getPages().isEmpty()){
			return new ResponseEntity<Map<String, List<Page>>>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		System.out.println("paso 3...");
		l_map.put("data", l_app.getPages());*/
		
		Application l_app = appService.findApplicationById(p_appId);
		if (l_app == null) {
			return new ResponseEntity<Map<String, List<Page>>>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		Map<String, List<Page>> l_map = new HashMap<String, List<Page>>();
		List<Page> l_lst = pagService.findPageFromApplicationById(p_appId);
		if(l_lst.isEmpty()){
			return new ResponseEntity<Map<String, List<Page>>>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		l_map.put("data", l_lst);
		return new ResponseEntity<Map<String, List<Page>>>(l_map, HttpStatus.OK);
	}

}
