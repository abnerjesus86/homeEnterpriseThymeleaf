package mx.com.lctpc.helpdeck.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lctpc.helpdeck.pojo.ApplicationRole;

@Transactional
@Repository
public class ApplicationRoleDaoImpl implements ApplicationRoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveApplicationRol( ApplicationRole p_appRol ) {
		// TODO Auto-generated method stub
		getSession().save(p_appRol);
	}
	
	

}
