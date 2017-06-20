package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.UrlRolesBean;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserRole;

public interface SecurityLctDao {
	public User getUser(String p_userName);
	public List<UserRole> getRoleAssigned(BigDecimal p_userId);
	public List<UrlRolesBean> getPageRoles(BigDecimal p_appnId);
}
