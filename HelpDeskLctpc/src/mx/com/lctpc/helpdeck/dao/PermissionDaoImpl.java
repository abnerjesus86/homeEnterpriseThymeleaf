package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lctpc.helpdeck.pojo.Permission;

@Transactional
@Repository
public class PermissionDaoImpl implements PermissionDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Permission> findAllPermisions() {
		// TODO Auto-generated method stub
		Query<Permission> l_query = getSession().createQuery("FROM Permission", Permission.class);
		return l_query.getResultList();
	}

	@Override
	public void savePermission( Permission p_per ) {
		// TODO Auto-generated method stub
		getSession().save(p_per);
	}

	@Override
	public void updatePermission( Permission p_per ) {
		// TODO Auto-generated method stub
		getSession().update(p_per);
	}

	@Override
	public Permission findPermissionById( BigDecimal p_perId ) {
		// TODO Auto-generated method stub
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Permission> l_crtQuery = l_builder.createQuery(Permission.class);
		Root<Permission> l_rootUser = l_crtQuery.from(Permission.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_prmnId"), p_perId));

		return (Permission) getSession().createQuery(l_crtQuery).getSingleResult();
	}

	@Override
	public void deletePermission( Permission p_per ) {
		// TODO Auto-generated method stub
		getSession().update(p_per);
	}

	@Override
	public List<Permission> findAllPermissionsActive() {
		// TODO Auto-generated method stub
		Query<Permission> l_query = getSession().createQuery("FROM Permission perm WHERE perm.g_prmnActive = :p", Permission.class);
		l_query.setParameter("p", true);
		return l_query.getResultList();
		
	}
	
	

}
