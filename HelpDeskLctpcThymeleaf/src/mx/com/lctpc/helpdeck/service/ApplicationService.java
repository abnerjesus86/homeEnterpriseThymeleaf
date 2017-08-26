package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.ApplicationDao;
import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.UserApplication;
import mx.com.lctpc.helpdeck.pojo.UserRole;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationDao appDao;
	
	public void saveOrUpdate(Application p_app){
		
		if(p_app.getAppnId() == null || findApplicationById(p_app.getAppnId())==null){

			appDao.saveApplication(p_app);
		}else{

			appDao.updateApplication(p_app);
		}
	}
	
	public List<Application> findAllApplication(){
		return appDao.findAllApplications();
	}
	
	public Application findApplicationById(BigDecimal p_appId ){
		return appDao.findApplicationById(p_appId);
	}
	
	/*public List<ApplicationRole> findRoleFromApplicationById(BigDecimal p_appId){
		return appDao.findRoleFromApplicationById(p_appId);
	}*/
	
	public List<Rol> findRoleFromApplicationById(BigDecimal p_appId){
		return appDao.findRoleFromApplicationById(p_appId);
	}
	
	public List<UserApplication> findUserFromApplicationById(BigDecimal p_appId){
		return appDao.findUserFromApplicationById(p_appId);
	}
	
	public void deleteApplication(Application p_app){
		p_app.setAppnActive(false);
		appDao.deleteApplication(p_app);
	}
	
	public Map<BigDecimal, String> findPlatform(){
		return appDao.findPlatform();
	}
	
	public void saveOrUpdateUserApplication( UserApplication p_appn ){
		if (p_appn.getUsapId() == null || findApplicationById(p_appn.getUsapId()) == null) {
			appDao.saveUserApplication(p_appn);
		} else {
			appDao.updateUserApplication(p_appn);
		}

	}
	
}
