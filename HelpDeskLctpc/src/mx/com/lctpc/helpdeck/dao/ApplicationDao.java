package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.ApplicationRole;
import mx.com.lctpc.helpdeck.pojo.UserApplication;

public interface ApplicationDao {
	public List<Application> findAllApplications();
	public void saveApplication(Application p_app);
	public void updateApplication(Application p_app);
	public Application findApplicationById(BigDecimal p_appId);
	public List<ApplicationRole> findRoleFromApplicationById(BigDecimal p_appId);
	public List<UserApplication> findUserFromApplicationById(BigDecimal p_appId);
	public void deleteApplication(Application p_app);
	public Map<BigDecimal, String> findPlatform();
	
}
