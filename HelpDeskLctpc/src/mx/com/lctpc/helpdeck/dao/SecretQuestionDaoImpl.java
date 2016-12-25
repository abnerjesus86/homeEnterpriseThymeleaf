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

import mx.com.lctpc.helpdeck.pojo.SecretQuestion;

@Transactional
@Repository
public class SecretQuestionDaoImpl implements SecretQuestionDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<SecretQuestion> findAllSecretQuestion() {
		// TODO Auto-generated method stub
		Query<SecretQuestion> l_query = getSession().createQuery("FROM SecretQuestion", SecretQuestion.class);
		return l_query.getResultList();
	}

	@Override
	public void saveSecretQuestion( SecretQuestion p_scq ) {
		// TODO Auto-generated method stub
		getSession().save(p_scq);
	}

	@Override
	public void updateSecretQuestion( SecretQuestion p_scq ) {
		// TODO Auto-generated method stub
		getSession().update(p_scq);
	}

	@Override
	public SecretQuestion findSecretQuestionById( BigDecimal p_scqId ) {
		// TODO Auto-generated method stub
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<SecretQuestion> l_crtQuery = l_builder.createQuery(SecretQuestion.class);
		Root<SecretQuestion> l_rootUser = l_crtQuery.from(SecretQuestion.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_sequId"), p_scqId));

		return (SecretQuestion) getSession().createQuery(l_crtQuery).getSingleResult();
	}

	@Override
	public void deleteSecretQuestion( SecretQuestion p_scq ) {
		// TODO Auto-generated method stub
		getSession().update(p_scq);
	}

}
