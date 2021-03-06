
package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.lctpc.helpdeck.pojo.AEntities;
import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.Owner;
import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.pojo.PageEntity;
import mx.com.lctpc.helpdeck.pojo.Permission;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.RolePage;
import mx.com.lctpc.helpdeck.pojo.SecretQuestion;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.VEmp;
import mx.com.lctpc.helpdeck.pojo.SelectList;
import mx.com.lctpc.helpdeck.service.ApplicationService;
import mx.com.lctpc.helpdeck.service.EntityService;
import mx.com.lctpc.helpdeck.service.OwnerService;
import mx.com.lctpc.helpdeck.service.PageService;
import mx.com.lctpc.helpdeck.service.PermissionService;
import mx.com.lctpc.helpdeck.service.PlatformService;
import mx.com.lctpc.helpdeck.service.RolService;
import mx.com.lctpc.helpdeck.service.RolePageService;
import mx.com.lctpc.helpdeck.service.SecretQuestionService;
import mx.com.lctpc.helpdeck.service.UsersService;

@RestController
@RequestMapping("/api/v1.0/")
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
	
	@Autowired
	private PlatformService platformService;
	
	@Autowired
	private RolePageService rolPagService;
	
	@Autowired
	private OwnerService ownerService;
	

	@RequestMapping( value = "/getJsonUsers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<User>> showListAllUsers() {
		List<User> users = userService.findAll();
		
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping( value = "/getJsonEmp/{p_comp}/{p_emp}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VEmp> showEmp(@PathVariable( "p_comp" )String p_comp, @PathVariable( "p_emp" ) String p_emp) {
		VEmp l_emp = userService.findEmp(p_comp, p_emp);
		
		if (l_emp == null) {
			return new ResponseEntity<VEmp>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}

		return new ResponseEntity<VEmp>(l_emp, HttpStatus.OK);
	}
	
	@GetMapping( value = "/getJsonUser/{p_userId}",  produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<User> showListAllUser( @PathVariable( "p_userId" ) BigDecimal p_usrId) {
		User users = userService.findUserById(p_usrId);

		if (users == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);// You many decide to return HttpStatus.NOT_FOUND
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
	
	@RequestMapping( value = "/getJsonAppsForSelect", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json" )
	public ResponseEntity<Map<String, List<SelectList>>> showAppsForSelect() {
		
		List<Application> l_apps = appService.findAllApplication();
		
		if (l_apps.isEmpty()) {
			return new ResponseEntity<Map<String, List<SelectList>>>(HttpStatus.NO_CONTENT);// You many decide to
																								// return
																								// HttpStatus.NOT_FOUND
		}
		List<SelectList> l_lst = new ArrayList<SelectList>();
		for (Application l_app : l_apps) {			
			l_lst.add(new SelectList(l_app.getAppnId().toPlainString(), l_app.getAppnName()));
		}
		
		Map<String, List<SelectList>> l_map = new HashMap<String, List<SelectList>>();
		l_map.put("data", l_lst);
		return new ResponseEntity<Map<String, List<SelectList>>>(l_map, HttpStatus.OK);
		
	}
	
	@RequestMapping( value = "/getJsonPlatformForSelect", method = { RequestMethod.GET }, produces = "application/json" )
	public ResponseEntity<Map<String, List<SelectList>>> showPlatformForSelect() {
		
		Map<BigDecimal, String> l_platf = platformService.findAllPlatformActive();
		
		if (l_platf.isEmpty()) {
			return new ResponseEntity<Map<String, List<SelectList>>>(HttpStatus.NO_CONTENT);// You many decide to
																								// return
																								// HttpStatus.NOT_FOUND
		}
		List<SelectList> l_lst = new ArrayList<SelectList>();
		
		for ( Entry<BigDecimal, String> l_plft : l_platf.entrySet()) {			
			l_lst.add(new SelectList(l_plft.getKey().toPlainString(), l_plft.getValue()));
		}
		
		Map<String, List<SelectList>> l_map = new HashMap<String, List<SelectList>>();
		l_map.put("data", l_lst);
		return new ResponseEntity<Map<String, List<SelectList>>>(l_map, HttpStatus.OK);
		
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
	
	@RequestMapping( value = "/getJsonRolesActive", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Rol>>> showJsonRolesActive() {
		List<Rol> l_roles = rolService.findRolesActive();
		Map<String, List<Rol>> l_map = new HashMap<String, List<Rol>>();
		if (l_roles.isEmpty()) {
			return new ResponseEntity<Map<String, List<Rol>>>(HttpStatus.NOT_ACCEPTABLE);// You many decide to return HttpStatus.NOT_FOUND
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
	public ResponseEntity<Map<String, List<Rol>>> showJsonRolesApps( @PathVariable( "p_appId" ) BigDecimal p_appId ) {

		List<Rol> l_roleApps = appService.findRoleFromApplicationById(p_appId);

		Map<String, List<Rol>> l_map = new HashMap<String, List<Rol>>();

		if (l_roleApps.isEmpty()) {
			return new ResponseEntity<Map<String, List<Rol>>>(HttpStatus.NO_CONTENT);// You many decide to
																									// return
																									// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_roleApps);
		return new ResponseEntity<Map<String, List<Rol>>>(l_map, HttpStatus.OK);
	}
	
	@RequestMapping( value = { "/getJsonPagesApps/{p_appId}" }, method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Page>>> showJsonPagesApps( @PathVariable( "p_appId" ) BigDecimal p_appId ) {
		
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
	
	@RequestMapping( value = "/getJsonPermissionActive/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Permission>>> showJsonPermissionActive(  ) {
		List<Permission> l_lstPerm = permService.findAllPermissionsActive();
		Map<String, List<Permission>> l_map = new HashMap<String, List<Permission>>();
		if (l_lstPerm.isEmpty()) {
			return new ResponseEntity<Map<String, List<Permission>>>(HttpStatus.NO_CONTENT);// You many decide to return
																							// HttpStatus.NOT_FOUND
		}

		l_map.put("data", l_lstPerm);
		return new ResponseEntity<Map<String, List<Permission>>>(l_map, HttpStatus.OK);

	}
	
	@RequestMapping( value = "/getJsonPermissionRolPageActive/{p_appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Map<String, Object>>>> showJsonPermissionRolPage( @PathVariable( "p_appId" ) BigDecimal p_appId ) {
		
		List<Permission> l_lstPerm = permService.findAllPermissionsActive();
		List<Page> l_lstPage = pagService.findPageFromApplicationById(p_appId);
		List<Rol> l_roleApps = appService.findRoleFromApplicationById(p_appId);
		//List<Perm> l_lstPermTotal = new ArrayList<Perm>();
		//Map<String, List<Perm>> l_map = new HashMap<String, List<Perm>>();
		List<Map<String, Object>> l_m = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> l_map = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Object> l_mapColum = new HashMap<String, Object>();
		List<Map<String, Object>> l_lstColums = new ArrayList<Map<String, Object>>();
		
		//l_mapColum.put("title","Page Id");
		//l_mapColum.put("data","pageId");
		//l_lstColums.add(l_mapColum);
		//l_mapColum = new HashMap<String, Object>();
		l_mapColum.put("title","Page Name");
		l_mapColum.put("data","pageDisplay");
		l_mapColum.put("rowspan",2);
		l_mapColum.put("colspan",1);
		l_mapColum.put("nivel",0);
		l_lstColums.add(l_mapColum);
		//l_mapColum = new HashMap<String, Object>();
		//l_mapColum.put("title","Entity Id");
		//l_mapColum.put("data","enttId");
		//l_lstColums.add(l_mapColum);
		l_mapColum = new HashMap<String, Object>();
		l_mapColum.put("title","Entity Name");
		l_mapColum.put("data","enttName");
		l_mapColum.put("rowspan",2);
		l_mapColum.put("colspan",1);
		l_mapColum.put("nivel",0);
		l_lstColums.add(l_mapColum);
		
		for(Rol l_rol: l_roleApps){
			
			l_mapColum = new HashMap<String, Object>();
			l_mapColum.put("title", l_rol.getRoleName());
			l_mapColum.put("data", "roleId_"+l_rol.getRoleId()+"."+"permission");
			//l_mapColum.put("data", "roleId_"+l_rol.getAproRoleId().getRoleId()+"."+"roleId_"+l_rol.getAproRoleId().getRoleId());
			l_mapColum.put("rowspan",1);
			l_mapColum.put("colspan",l_lstPerm.size());
			l_mapColum.put("nivel",0);
			l_mapColum.put("render",true);
			l_lstColums.add(l_mapColum);
			
			/*
			for(Permission l_perm : l_lstPerm ){
				l_mapColum = new HashMap<String, Object>();
				l_mapColum.put("title", "Prmn Id");
				l_mapColum.put("data", "roleId_"+l_rol.getAproRoleId().getRoleId()+".prmnId_"+l_perm.getPrmnId() );
				l_mapColum.put("rowspan",1);
				l_mapColum.put("colspan",1);
				l_mapColum.put("nivel",1);
				l_lstColums.add(l_mapColum);
				
				l_mapColum = new HashMap<String, Object>();
				l_mapColum.put("title", l_perm.getPrmnName());
				l_mapColum.put("data", "roleId_"+l_rol.getAproRoleId().getRoleId()+".prmnName_"+l_perm.getPrmnId() );
				l_mapColum.put("rowspan",1);
				l_mapColum.put("colspan",1);
				l_mapColum.put("nivel",1);
				l_lstColums.add(l_mapColum);
			}
			*/
		}
		
		//Armado del data
		for(Page l_pag : l_lstPage){
			
			if(l_pag.getPageEntities().isEmpty()){
				Map<String, Object> l_p = new HashMap<String, Object>();
				l_p.put( "pageId", l_pag.getPageId());
				l_p.put( "pageDisplay", l_pag.getPageDisplay());
				l_p.put( "paenId", null);
				l_p.put( "enttId", null );
				l_p.put( "enttName", null );
				
				for(Rol l_rol: l_roleApps){
					Map<String, Object> l_rp = new HashMap<String, Object>();
					List<Map<String, Object>> l_permsss = new ArrayList<Map<String, Object>>();
					for(Permission l_perm : l_lstPerm){
						Map<String, Object> l_pms = new HashMap<String, Object>();
						l_pms.put("prmnId", l_perm.getPrmnId());
						l_pms.put("prmnName", l_perm.getPrmnName());
						
						l_permsss.add(l_pms);
					}
					l_rp.put("permission", l_permsss);
					
					l_rp.put("countPermission", l_lstPerm.size());
					l_rp.put( "roleId_"+l_rol.getRoleId(), l_rol.getRoleId() );
					l_rp.put( "roleName_"+l_rol.getRoleId(), l_rol.getRoleName() );
					l_p.put("roleId_"+l_rol.getRoleId(), l_rp);
					
				}
				l_m.add(l_p);
			}else{
				for(PageEntity l_paen : l_pag.getPageEntities()){
					Map<String, Object> l_p = new HashMap<String, Object>();
					l_p.put( "pageId", l_pag.getPageId());
					l_p.put( "pageDisplay", l_pag.getPageDisplay());
					l_p.put( "paenId", l_paen.getPaenId());
					l_p.put( "enttId", l_paen.getPaenEnttId().getEnttId() );
					l_p.put( "enttName", l_paen.getPaenEnttId().getEnttName() );
					
					for(Rol l_rol: l_roleApps){
						Map<String, Object> l_rp = new HashMap<String, Object>();
						List<Map<String, Object>> l_permsss = new ArrayList<Map<String, Object>>();
						for(Permission l_perm : l_lstPerm){
							Map<String, Object> l_pms = new HashMap<String, Object>();
							l_pms.put("prmnId", l_perm.getPrmnId());
							l_pms.put("prmnName", l_perm.getPrmnName());
							
							l_permsss.add(l_pms);
						}
						l_rp.put("permission", l_permsss);
						
						l_rp.put("countPermission", l_lstPerm.size());
						l_rp.put( "roleId_"+l_rol.getRoleId(), l_rol.getRoleId() );
						l_rp.put( "roleName_"+l_rol.getRoleId(), l_rol.getRoleName() );
						
						l_p.put("roleId_"+l_rol.getRoleId(), l_rp);
					}
					l_m.add(l_p);
				}
			}

		}

		l_map.put("data", l_m);
		l_map.put("columns", l_lstColums);
		
		return new ResponseEntity<Map<String, List<Map<String, Object>>>>(l_map, HttpStatus.OK);

	}
	
	@RequestMapping( value = "/getJsonPermissionRolPageActive2/{p_appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<Map<String, Object>>>> showJsonPermissionRolPage2( @PathVariable( "p_appId" ) BigDecimal p_appId ) {
		
		List<Permission> l_lstPerm = permService.findAllPermissionsActive();
		List<Page> l_lstPage = pagService.findPageFromApplicationById(p_appId);
		List<Rol> l_roleApps = appService.findRoleFromApplicationById(p_appId);
		List<Map<String, Object>> l_m = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> l_map = new HashMap<String, List<Map<String, Object>>>();
		
		List<RolePage> l_lstAssignedPermissions = rolPagService.findRolesPagesActiveFromApplicationById(p_appId);
		
		//Armado del data
		for(Page l_pag : l_lstPage){
			Map<String, Object> l_p = new HashMap<String, Object>();
			l_p.put( "pageId", l_pag.getPageId());
			l_p.put( "pageDisplay", l_pag.getPageDisplay());
			List<Map<String, Object>> l_roles = new ArrayList<Map<String, Object>>();
			for(Rol l_rol: l_roleApps){
				Map<String, Object> l_mRol = new HashMap<String, Object>();
				l_mRol.put( "roleId", l_rol.getRoleId() );
				l_mRol.put( "roleName", l_rol.getRoleName() );
				
				List<Map<String, Object>> l_paEs = new ArrayList<Map<String, Object>>();
				for(PageEntity l_paen : l_pag.getPageEntities()){
					Map<String, Object> l_paE = new HashMap<String, Object>();
					l_paE.put( "paenId", l_paen.getPaenId());
					l_paE.put( "enttId", l_paen.getPaenEnttId().getEnttId() );
					l_paE.put( "enttName", l_paen.getPaenEnttId().getEnttName() );
					
					List<Map<String, Object>> l_permsss = new ArrayList<Map<String, Object>>();
					for(Permission l_perm : l_lstPerm){
						Map<String, Object> l_pms = new HashMap<String, Object>();
						l_pms.put("prmnId", l_perm.getPrmnId());
						l_pms.put("prmnName", l_perm.getPrmnName());
						
						l_permsss.add(l_pms);
					}
					l_paE.put("permission", l_permsss);
					
					l_paEs.add(l_paE);
				}
				l_mRol.put("entity", l_paEs);
				
				l_roles.add(l_mRol);
				
			}
			l_p.put("roles", l_roles);
			l_m.add(l_p);
			
		}
		List<Map<String, Object>> l_mAssignedPermissions = new ArrayList<Map<String, Object>>();
		for(RolePage l_assigPerm : l_lstAssignedPermissions){
			Map<String, Object> l_aP = new HashMap<String, Object>();
			l_aP.put("ropaId", l_assigPerm.getRopaId());
			l_aP.put("ropaPaenId", l_assigPerm.getRopaPaenId().getPaenId());
			l_aP.put("ropaPrmnId", l_assigPerm.getRopaPrmnId().getPrmnId());
			l_aP.put("ropaRoleId", l_assigPerm.getRopaRoleId().getRoleId());
			l_aP.put("ropaActive", l_assigPerm.isRopaActive());
			l_mAssignedPermissions.add(l_aP);
		}
		
		l_map.put("data", l_m);
		l_map.put("assignedPermission", l_mAssignedPermissions);
		
		return new ResponseEntity<Map<String, List<Map<String, Object>>>>(l_map, HttpStatus.OK);

	}
	
	@RequestMapping( value = "/getJsonOwnerForSelect", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, List<SelectList>>> showJsonOwnerForSelect() {
		List<Owner> l_lstOw = ownerService.findOwnersActive();
		if (l_lstOw.isEmpty()) {
			return new ResponseEntity<Map<String, List<SelectList>>>(HttpStatus.NO_CONTENT);// You many decide to return
																						// HttpStatus.NOT_FOUND
		}
		List<SelectList> l_lst = new ArrayList<SelectList>();
		for (Owner l_ow : l_lstOw) {			
			l_lst.add(new SelectList(l_ow.getOwnrId().toPlainString(), l_ow.getOwnrName()));
		}
		
		Map<String, List<SelectList>> l_map = new HashMap<String, List<SelectList>>();
		l_map.put("data", l_lst);
		
		return new ResponseEntity<Map<String, List<SelectList>>>(l_map, HttpStatus.OK);
		
	}
	
}
