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

import mx.com.lctpc.helpdeck.pojo.AEntities;

@Transactional
@Repository
public class EntityDaoImpl implements EntityDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<AEntities> findAllEntity() {
		// TODO Auto-generated method stub
		Query<AEntities> l_query = getSession().createQuery("FROM AEntities", AEntities.class);
		return l_query.getResultList();
	}

	@Override
	public void saveEntity( AEntities p_ent ) {
		// TODO Auto-generated method stub
		getSession().save(p_ent);
	}

	@Override
	public void updateEntity( AEntities p_ent ) {
		// TODO Auto-generated method stub
		getSession().update(p_ent);
	}

	@Override
	public AEntities findEntityById( BigDecimal p_entId ) {
		// TODO Auto-generated method stub
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<AEntities> l_crtQuery = l_builder.createQuery(AEntities.class);
		Root<AEntities> l_rootUser = l_crtQuery.from(AEntities.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_enttId"), p_entId));

		return (AEntities) getSession().createQuery(l_crtQuery).getSingleResult();
	}

	@Override
	public void deleteEntity( AEntities p_ent ) {
		// TODO Auto-generated method stub
		getSession().update(p_ent);
	}

}
