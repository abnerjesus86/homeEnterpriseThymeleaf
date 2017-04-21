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

import mx.com.lctpc.helpdeck.pojo.RolePage;

@Transactional
@Repository
public class RolePageDaoImpl implements RolePageDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save( RolePage p_rolePage ) {
		getSession().save(p_rolePage);
	}

	@Override
	public void update( RolePage p_rolePage ) {
		
		getSession().update(p_rolePage);
	}

	@Override
	public List<RolePage> findAllRolePage() {
		Query<RolePage> l_query =  getSession().createQuery("FROM RolePage", RolePage.class);
		return l_query.getResultList();
	}

	@Override
	public RolePage findRolePageById(BigDecimal p_ropaId) {
		CriteriaBuilder l_builder =  getSession().getCriteriaBuilder();
		CriteriaQuery<RolePage> l_crtQuery = l_builder.createQuery(RolePage.class);
		Root<RolePage> l_rootRolePage = l_crtQuery.from(RolePage.class);
		l_crtQuery.select(l_rootRolePage);
		l_crtQuery.where(l_builder.equal(l_rootRolePage.get("g_ropaId"), p_ropaId));
		
		return (RolePage) getSession().createQuery( l_crtQuery ).getSingleResult();
	}

	@Override
	public List<RolePage> findRolesPagesActiveFromApplicationById( BigDecimal p_appId ) {
		Query<RolePage> l_queryRolesPages = getSession().createQuery(
				"select perm from RolePage perm JOIN perm.g_ropaRoleId rol where rol.g_roleAppnId.g_appnId = :p_appId and perm.g_ropaActive = :isActive",
				RolePage.class).
				setParameter("p_appId", p_appId).
				setParameter("isActive", true);
		
		return l_queryRolesPages.getResultList();
	}
	
	@Override
	public void delete( RolePage p_rolePage ) {
		getSession().update(p_rolePage);
	}

}
