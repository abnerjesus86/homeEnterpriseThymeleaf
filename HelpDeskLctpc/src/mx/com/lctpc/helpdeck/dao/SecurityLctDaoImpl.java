package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.RolePage;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserRole;

@Repository
@Transactional
public class SecurityLctDaoImpl implements SecurityLctDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public User getUser( String p_userName ) {
		// TODO Auto-generated method stub
		Query<User> l_query = getSession().createQuery("FROM User usr "
				+ " WHERE usr.g_userUsername = :p_userName AND usr.g_userActive = :isActive", User.class)
				.setParameter(":p_userName", p_userName)
				.setParameter("isActive", true);
		List<User> l_lstUser = l_query.getResultList();
		
		if(l_lstUser.size() == 1){
			return l_lstUser.get(0);
		}else if(l_lstUser.size() > 1){
			System.out.println("El usuario tiene mas 2 cuantas activa...");
			return null;
		}else{
			return null;
		}

	}

	@Override
	public List<UserRole> getRoleAssigned( BigDecimal p_userId ) {
		// TODO Auto-generated method stub
		Query<UserRole> l_queryUserRole = getSession().createQuery(
				"select usrRol from UserRole usrRol where usrRol.g_usroUserId.g_userId = :p_UserId"
				,UserRole.class).setParameter("p_UserId", p_userId);
		
		return l_queryUserRole.getResultList();
	}

	@Override
	public List<RolePage> getPageRoles(BigDecimal p_appnId) {
		// TODO Auto-generated method stub
		
		Query<RolePage> l_queryRolesPages = getSession().createQuery(
				"select perm from RolePage perm JOIN perm.g_ropaRoleId rol where rol.g_roleAppnId.g_appnId = :p_appId and perm.g_ropaActive = :isActive",
				RolePage.class).
				setParameter("p_appId", p_appnId).
				setParameter("isActive", true);
		
		return l_queryRolesPages.getResultList();

	}
	
}
