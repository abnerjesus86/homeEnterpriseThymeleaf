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

import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.UserApplication;

@Transactional
@Repository
public class ApplicationDaoImpl implements ApplicationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Application> findAllApplications() {

		Query<Application> l_query = getSession().createQuery("FROM Application", Application.class);
		return l_query.getResultList();
	}

	@Override
	public void saveApplication( Application p_app ) {

		getSession().save(p_app);
	}

	@Override
	public void updateApplication( Application p_app ) {
		getSession().update(p_app);
	}

	@Override
	public Application findApplicationById( BigDecimal p_appId ) {
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Application> l_crtQuery = l_builder.createQuery(Application.class);
		Root<Application> l_rootUser = l_crtQuery.from(Application.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_appnId"), p_appId));

		return (Application) getSession().createQuery(l_crtQuery).getSingleResult();

	}

/*	@Override
	public List<ApplicationRole> findRoleFromApplicationById( BigDecimal p_appId ) {
		Query<ApplicationRole> l_queryUserRole = getSession().createQuery(
				"select appRoles from Application app join app.g_appnRoles appRoles where app.g_appnId = :p_appId",
				ApplicationRole.class).setParameter("p_appId", p_appId);

		return l_queryUserRole.getResultList();
	}*/
	
	@Override
	public List<Rol> findRoleFromApplicationById( BigDecimal p_appId ) {
		Query<Rol> l_queryUserRole = getSession().createQuery(
				"select rol from Rol rol join rol.g_roleAppnId app where app.g_appnId = :p_appId",
				Rol.class).setParameter("p_appId", p_appId);

		return l_queryUserRole.getResultList();
	}
	
	
	@Override
	public List<UserApplication> findUserFromApplicationById( BigDecimal p_appId ) {
		Query<UserApplication> l_queryUserRole = getSession().createQuery(
				"select appUser from Application app join app.g_userApplications appUser where app.g_appnId = :p_appId",
				UserApplication.class).setParameter("p_appId", p_appId);
		
		return l_queryUserRole.getResultList();
	}
	
	@Override
	public void deleteApplication( Application p_app ) {
		getSession().update(p_app);
	}

	@Override
	public Map<BigDecimal, String> findPlatform() {
		Map<BigDecimal, String> l_map  = new LinkedHashMap<BigDecimal,String>();
		/*Query<Object[]> l_query = getSession().createSQLQuery("SELECT "+
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
														"ORDER BY PLFM_ID ASC");*/
		
		@SuppressWarnings( "unchecked" )
		Query<Object[]> l_query = getSession().createNativeQuery("SELECT "+
				  "PLFM_ID, "+
				  "DVLP_NAME ||' '|| TECH_NAME ||' '|| TECH_VERSION ||' '|| TECH_API  AS NAME "+
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

	/*
	 * private NamedParameterJdbcTemplate jdbcTemplate;
	 * 
	 * @Autowired private void setDataSource( DataSource p_dataSource ) {
	 * this.jdbcTemplate = new NamedParameterJdbcTemplate(p_dataSource); }
	 * 
	 * @Override public List<Application> findAllApplications() { // TODO
	 * Auto-generated method stub return
	 * jdbcTemplate.query("SELECT * FROM application_manager.APPLICATIONS", new
	 * RowMapper<Application>() {
	 * 
	 * @Override public Application mapRow( ResultSet rs, int rowNum ) throws
	 * SQLException { // TODO Auto-generated method stub Application l_app = new
	 * Application(); l_app.setAppnId(rs.getBigDecimal("APNS_ID"));
	 * l_app.setAppnName(rs.getString("APNS_NAME"));
	 * l_app.setAppnDescription(rs.getString("APNS_DESCRIPTION"));
	 * l_app.setAppnUrl(rs.getString("APNS_URL"));
	 * l_app.setAppnActive(rs.getBoolean("USER_ACTIVE"));
	 * l_app.setAppnCreatedDate(rs.getTimestamp("APNS_CREATED_DATE"));
	 * l_app.setAppnCreatedBy(rs.getString("APNS_CREATED_BY"));
	 * l_app.setAppnUpdateDate(rs.getTimestamp("APNS_UDPATE_DATE"));
	 * l_app.setAppnUpdateBy(rs.getString("APNS_UPDATE_BY"));
	 * 
	 * return l_app; }
	 * 
	 * }); }
	 * 
	 * @Override public boolean saveApplication( Application p_app ) { // TODO
	 * Auto-generated method stub MapSqlParameterSource l_paramMap = new
	 * MapSqlParameterSource(); l_paramMap.addValue("v0", p_app.getAppnId());
	 * l_paramMap.addValue("v1", p_app.getAppnName()); l_paramMap.addValue("v2",
	 * p_app.getAppnDescription()); l_paramMap.addValue("v3",
	 * p_app.getAppnUrl()); l_paramMap.addValue("v4", p_app.isAppnActive());
	 * l_paramMap.addValue("v5", p_app.getAppnCreatedDate());
	 * l_paramMap.addValue("v6", p_app.getAppnCreatedBy());
	 * l_paramMap.addValue("v7", p_app.getAppnUpdateDate());
	 * l_paramMap.addValue("v8", p_app.getAppnUpdateBy());
	 * 
	 * return jdbcTemplate.update("INSERT INTO APPLICATIONS ( " +
	 * " APNS_ID, APNS_NAME, APNS_DESCRIPTION, APNS_URL, APNS_ACTIVE, APNS_CREATED_DATE, "
	 * + " APNS_CREATED_BY, APNS_UDPATE_DATE, APNS_UPDATE_BY ) " +
	 * " VALUES ( :v0, :v1, :v2, :v3, :v4, :v5, :v6, :v7, :v8 )", l_paramMap) ==
	 * 1; }
	 * 
	 * @Override public void updateApplication( Application p_app ) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public Application findApplicationById( BigDecimal p_appId ) {
	 * // TODO Auto-generated method stub MapSqlParameterSource l_paramMap = new
	 * MapSqlParameterSource(); l_paramMap.addValue("p_Id", p_appId);
	 * Application l_user = jdbcTemplate.queryForObject(
	 * "SELECT * FROM application_manager.APPLICATIONS WHERE APNS_ID = :p_Id ",
	 * l_paramMap, new RowMapper<Application>() {
	 * 
	 * @Override public Application mapRow( ResultSet rs, int rowNum ) throws
	 * SQLException { // TODO Auto-generated method stub Application l_app = new
	 * Application(); l_app.setAppnId(rs.getBigDecimal("APNS_ID"));
	 * l_app.setAppnName(rs.getString("APNS_NAME"));
	 * l_app.setAppnDescription(rs.getString("APNS_DESCRIPTION"));
	 * l_app.setAppnUrl(rs.getString("APNS_URL"));
	 * l_app.setAppnActive(rs.getBoolean("USER_ACTIVE"));
	 * l_app.setAppnCreatedDate(rs.getTimestamp("APNS_CREATED_DATE"));
	 * l_app.setAppnCreatedBy(rs.getString("APNS_CREATED_BY"));
	 * l_app.setAppnUpdateDate(rs.getTimestamp("APNS_UDPATE_DATE"));
	 * l_app.setAppnUpdateBy(rs.getString("APNS_UPDATE_BY")); return l_app; }
	 * }); return l_user; }
	 * 
	 * @Override public List<ApplicationRole> findRoleFromApplicationById(
	 * BigDecimal p_appId ) { // TODO Auto-generated method stub return null; }
	 */

}
