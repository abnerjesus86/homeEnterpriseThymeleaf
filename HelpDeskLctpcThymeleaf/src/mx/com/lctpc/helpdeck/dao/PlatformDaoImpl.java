package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lctpc.helpdeck.pojo.Platform;

@Transactional
@Repository
public class PlatformDaoImpl implements PlatformDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<Platform> findAllPlatform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<BigDecimal, String> findAllPlatformActive() {
		Map<BigDecimal, String> l_map  = new LinkedHashMap<BigDecimal,String>();
		@SuppressWarnings( "unchecked" )
		Query<Object[]> l_query = getSession().createNativeQuery("SELECT "+
														  "PLFM_ID, "+
														  "DVLP_NAME ||' '|| TECH_NAME ||' '|| TECH_VERSION ||' '|| TECH_API  "+
														"FROM PLATFORM "+
														"INNER JOIN PLATFORM_TECHNOLOGY "+
														"ON PLTE_PLFM_ID = PLFM_ID "+
														"AND PLTE_ACTIVE = 1 "+
														"INNER JOIN DEVELOPMENT "+
														"ON PLFM_DVLP_ID = DVLP_ID "+
														"INNER JOIN TECHNOLOGY "+
														"ON PLTE_TECH_ID = TECH_ID "+
														"ORDER BY PLFM_ID ASC");
		
		for(Object[] l_data : l_query.getResultList()){
			l_map.put(new BigDecimal(l_data[0].toString()), l_data[1].toString());
		}
		
		return l_map;
	}

	@Override
	public void savePlatform( Platform p_plfmId ) {
		// TODO Auto-generated method stub
		getSession().save(p_plfmId);
	}

	@Override
	public void updatePlatform( Platform p_plfmId ) {
		// TODO Auto-generated method stub
		getSession().update(p_plfmId);
	}

	@Override
	public Platform findPlatformById( BigDecimal p_plfmId ) {
		// TODO Auto-generated method stub
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Platform> l_crtQuery = l_builder.createQuery(Platform.class);
		Root<Platform> l_rootPlatform = l_crtQuery.from(Platform.class);
		l_crtQuery.select(l_rootPlatform);
		l_crtQuery.where(l_builder.equal(l_rootPlatform.get("g_plfmId"), p_plfmId));

		return (Platform) getSession().createQuery(l_crtQuery).getSingleResult();
		
	}

}
