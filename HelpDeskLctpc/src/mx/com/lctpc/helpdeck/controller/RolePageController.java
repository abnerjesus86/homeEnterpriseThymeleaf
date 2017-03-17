package mx.com.lctpc.helpdeck.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.lctpc.helpdeck.pojo.RolePage;
import mx.com.lctpc.helpdeck.service.RolePageService;

@Controller
public class RolePageController {
	
	@Autowired
	private RolePageService rolPagService;
	
	@RequestMapping( value = "/appWizard/assignedPermissions/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<String> jsonAssignedPermissionsSave( @RequestBody RolePage p_rolePage ){
		
		p_rolePage.setRopaCreatedBy("BENITEZ.ABNER");
		p_rolePage.setRopaUpdateBy("BENITEZ.ABNER");
		
		rolPagService.saveOrUpdate(p_rolePage);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping( value = "/appWizard/assignedPermission/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<String> jsonAssignedPermissionSave( @RequestBody List<RolePage> p_rolePage ){
		
		if(p_rolePage.isEmpty())
			return new ResponseEntity<String>("not exit entity on page", HttpStatus.NO_CONTENT);
		
		for(RolePage l_perm : p_rolePage){
			
			if(l_perm.getRopaId() == null){
				l_perm.setRopaCreatedBy("BENITEZ.ABNER");
				l_perm.setRopaUpdateBy("BENITEZ.ABNER");
				rolPagService.saveOrUpdate(l_perm);
			}else if(l_perm.getRopaId() != null && !l_perm.isRopaActive()){
				l_perm.setRopaUpdateBy("BENITEZ.ABNER");
				rolPagService.delete(l_perm);
			}
			
		}//Fin de la lista RolePage
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	@RequestMapping( value = "/appWizard/assignedPermission/delete/{roleId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<String> jsonAssignedPermissionDelete( @PathVariable("roleId") BigDecimal p_roleId ){
		
		RolePage l_rolePage = rolPagService.findRolePageById(p_roleId);
		l_rolePage.setRopaUpdateBy("BENITEZ.ABNER");
		l_rolePage.setRopaActive(false);
		rolPagService.delete(l_rolePage);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	
}

class RolePageWrapper {

    private List<RolePage> g_rolePage;

	/**
	 * @return the rolePage
	 */
	public List<RolePage> getRolePage() {
		return this.g_rolePage;
	}

	/**
	 * @param p_rolePage the rolePage to set
	 */
	public void setRolePage( List<RolePage> p_rolePage ) {
		this.g_rolePage = p_rolePage;
	}
    
}



