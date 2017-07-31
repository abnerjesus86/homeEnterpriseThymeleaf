package mx.com.lctpc.helpdeck.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lctpc.helpdeck.pojo.VEmp;

@Transactional
@Repository
public class VEmpDaoImpl implements VEmpDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<VEmp> findAllEmps() {
		Query<VEmp> l_query = getSession().createQuery("FROM VEmp", VEmp.class);
		
		return l_query.getResultList();
	}

	@Override
	public List<VEmp> findEmpByName( String p_Name ) {
		
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<VEmp> l_crtQuery = l_builder.createQuery(VEmp.class);
		Root<VEmp> l_rootEmp = l_crtQuery.from(VEmp.class); 
		l_crtQuery.select(l_rootEmp);
		
		l_crtQuery.where(l_builder.like( l_rootEmp.get("g_nombre"), "%" + p_Name.toUpperCase() + "%" ) );
		
		return getSession().createQuery(l_crtQuery).getResultList();
		
	}

	@Override
	public VEmp findEmp( String p_Compania, String p_Emp ) {
		
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<VEmp> l_crtQuery = l_builder.createQuery(VEmp.class);
		Root<VEmp> l_rootEmp = l_crtQuery.from(VEmp.class);
		
		//Declaracion de filtros para la consulta
		List<Predicate> l_filters = new ArrayList<Predicate>();
		l_filters.add(l_builder.equal(l_rootEmp.get("g_compania"),  p_Compania));
		l_filters.add(l_builder.equal(l_rootEmp.get("g_empleado"),  p_Emp));
		
		l_crtQuery.select(l_rootEmp).where( l_filters.toArray(new Predicate[]{}) );
		
		return getSession().createQuery(l_crtQuery).getSingleResult();
	}

}
