package mx.com.lctpc.helpdeck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.ApplicationRoleDao;
import mx.com.lctpc.helpdeck.pojo.ApplicationRole;

@Service
public class ApplicationRoleService {
	
	@Autowired
	private ApplicationRoleDao appRolDao;
	
	public void saveOrUpdate(ApplicationRole p_appRol){
		appRolDao.saveApplicationRol(p_appRol);
	}
	
	
}
