package mx.com.lctpc.helpdeck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.com.lctpc.helpdeck.pojo.RolePage;
import mx.com.lctpc.helpdeck.pojo.UrlRolesBean;

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
	
	public void mapUrlToRole(List<UrlRolesBean> roleActions){
		String dbUrl=null;
		for (UrlRolesBean rolesPage : roleActions) {
			
			dbUrl=rolesPage.getUrl();
			
			if(this.urlRoles.containsKey(dbUrl)){
				
				List<String> roles=this.urlRoles.get(dbUrl);
				
				roles.add(rolesPage.getRole() );
				
			}else{
				
				List<String> roles=new ArrayList<String>();
				roles.add(rolesPage.getRole());
				this.urlRoles.put(dbUrl, roles);
				
			}
		}
	}
}
