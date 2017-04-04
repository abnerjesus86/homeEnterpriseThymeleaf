package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.SecurityLctDao;
import mx.com.lctpc.helpdeck.pojo.RolePage;
import mx.com.lctpc.helpdeck.pojo.UrlRolesBean;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserRole;

@Service
public class SecurityLctService {

	@Autowired
	private SecurityLctDao secLctDao;

	@Autowired
	private UrlCache urlCache;

	public User getUser( String p_User ) {
		return secLctDao.getUser(p_User);
	}

	public List<UserRole> getRoleAssigned( BigDecimal p_userId ) {
		
		return secLctDao.getRoleAssigned(p_userId);
	}

	public void getPageRoles(BigDecimal p_appnId) {
		
		
		List<UrlRolesBean> l_lst = secLctDao.getPageRoles(p_appnId);
		
		for(UrlRolesBean l_rp : l_lst ){
			System.out.println("Url "+ l_rp.getUrl());
			System.out.println("Role "+ l_rp.getRole());
		}
		
		urlCache.mapUrlToRole(l_lst);
		
		return ;
	}
}
