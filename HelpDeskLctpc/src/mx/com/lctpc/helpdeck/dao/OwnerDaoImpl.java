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

import mx.com.lctpc.helpdeck.pojo.Owner;
import mx.com.lctpc.helpdeck.pojo.PageEntity;

@Transactional
@Repository
public class OwnerDaoImpl implements OwnerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<Owner> findAllOwners() {
		// TODO Auto-generated method stub
		Query<Owner> l_query = getSession().createQuery("FROM Owner", Owner.class);
		return l_query.getResultList();
	}

	@Override
	public Owner findOwnerById(BigDecimal p_ownrId) {
		// TODO Auto-generated method stub
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Owner> l_crtQuery = l_builder.createQuery(Owner.class);
		Root<Owner> l_rootUser = l_crtQuery.from(Owner.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_ownrId"), p_ownrId));

		return (Owner) getSession().createQuery(l_crtQuery).getSingleResult();
	}

	@Override
	public List<Owner> findOwnersActive() {
		// TODO Auto-generated method stub
		Query<Owner> l_queryPageEntity = getSession().createQuery("SELECT ow FROM Owner ow where ow.g_ownrActive = :p", Owner.class)
				.setParameter("p", true)
				;
		return l_queryPageEntity.getResultList();
	}

	@Override
	public void save( Owner p_Ownr ) {
		// TODO Auto-generated method stub
		getSession().save(p_Ownr);
	}

	@Override
	public void update( Owner p_Ownr ) {
		// TODO Auto-generated method stub
		getSession().update(p_Ownr);
	}

	@Override
	public void delete( Owner p_Ownr ) {
		// TODO Auto-generated method stub
		getSession().update(p_Ownr);
	}

}
