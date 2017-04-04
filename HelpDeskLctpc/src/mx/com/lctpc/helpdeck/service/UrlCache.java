package mx.com.lctpc.helpdeck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.com.lctpc.helpdeck.pojo.RolePage;

@Component
public class UrlCache {
	
	HashMap<String, List<String>> urlRoles=new HashMap<String, List<String>>();

	public HashMap<String, List<String>> getUrlRoles() {
		return this.urlRoles;
	}

	public void setUrlRoles(HashMap<String, List<String>> urlRoles) {
		this.urlRoles = urlRoles;
	}
	
	public List<String> getUrlRoles(String key) {
		return urlRoles.get(key);
	}
	
	public void mapUrlToRole(List<RolePage> roleActions){
		String dbUrl=null;
		for (RolePage rolesPage : roleActions) {
			
			dbUrl=rolesPage.getRopaPaenId().getPaenPageId().getPageUrl();
			
			if(this.urlRoles.containsKey(dbUrl)){
				
				List<String> roles=this.urlRoles.get(dbUrl);
				
				roles.add(rolesPage.getRopaRoleId().getRoleName() );
				
			}else{
				
				List<String> roles=new ArrayList<String>();
				roles.add(rolesPage.getRopaRoleId().getRoleName());
				this.urlRoles.put(dbUrl, roles);
				
			}
		}
	}
}
