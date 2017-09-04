package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lctpc.helpdeck.pojo.AccountInformation;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserApplication;
import mx.com.lctpc.helpdeck.pojo.UserRole;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<User> findAllUsers() {
		
		//List<User> l_lstUsers = getSession().createQuery("FROM User").getResultList();
		Query<User> l_query = getSession().createQuery("FROM User",User.class);
		return l_query.getResultList();
	}

	@Override
	public void saveUser( User p_user ) {
		
		getSession().saveOrUpdate(p_user);
	}
	
	@Override
	public void persistsUser( User p_user ) {
		
		Session l_session = sessionFactory.openSession();//getSession();
		Transaction tx2 = l_session.beginTransaction();
		
		AccountInformation l_acount = p_user.getAccountInf();
		p_user.setAccountInf(null);
		
		l_session.persist(p_user);
		
		if( l_acount != null ){			
			l_acount.setAcinUserId(p_user.getUserId());
			p_user.setAccountInf(l_acount);
		}

		tx2.commit();
		l_session.close();
	}
	
	@Override
	public void mergeUser( User p_user ) {
		// TODO Auto-generated method stub
		getSession().merge(p_user);
	}
	
	@Override
	public User findUserById( BigDecimal p_userId ) {
		
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<User> l_crtQuery = l_builder.createQuery(User.class);
		Root<User> l_rootUser = l_crtQuery.from(User.class); 
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal( l_rootUser.get("g_userId"), p_userId));
		
		return (User)getSession().createQuery(l_crtQuery).getSingleResult();
	}

	@Override
	public List<UserRole> findRolesFromUserById( BigDecimal p_userId ) {
		
		Query<UserRole> l_queryUserRole = getSession().createQuery(
				"select usrRol from UserRole usrRol where usrRol.g_usroUserId.g_userId = :p_UserId and usrRol.g_usroActive = true"
				,UserRole.class).setParameter("p_UserId", p_userId);
		
		return l_queryUserRole.getResultList();

	}
	
	@Override
	public List<UserRole> existsRolesUserByIds( BigDecimal p_userId, BigDecimal p_roleId ) {
		
		Query<UserRole> l_queryUserRole = getSession().createQuery(
				"select usrRol from UserRole usrRol where usrRol.g_usroUserId.g_userId = :p_UserId and usrRol.g_usroRolId.g_roleId = :p_roleId and usrRol.g_usroActive =  true"
				,UserRole.class).setParameter("p_UserId", p_userId).setParameter("p_roleId", p_roleId);
		
		return l_queryUserRole.getResultList();

	}
	@Override
	public List<UserApplication> existsAppnsUserByIds( BigDecimal p_userId, BigDecimal p_appId ){
		Query<UserApplication> l_queryUserApp = getSession().createQuery(
				"select usrApp from UserApplication usrApp where usrApp.g_usapUserId.g_userId = :p_UserId and usrApp.g_usapAppnId.g_appnId = :p_appId and usrApp.g_usapActive = true "
				,UserApplication.class).setParameter("p_UserId", p_userId).setParameter("p_appId", p_appId);
		
		return l_queryUserApp.getResultList();
	}
	
	@Override
	public List<UserApplication> findApplicationFromUserById( BigDecimal p_userId ) {
		// TODO Auto-generated method stub
		
		Query<UserApplication> l_queryUserAplicaction = getSession().createQuery(
				"select usrApp from User usr join usr.g_userApplications usrApp where usr.g_userId = :p_UserId and usrApp.g_usapActive = true "
				,UserApplication.class).setParameter("p_UserId", p_userId);
		
		return l_queryUserAplicaction.getResultList();
	}

	@Override
	public void updateUser( User p_user ) {
		/*if(p_user.getAccountInf().getAcinUserId() != null){
			
		}*/
		//getSession().update(p_user.getAccountInf());
		//getSession().persist(p_user);
		getSession().update(p_user);
	}

	@Override
	public void deleteUser( User p_user ) {
		// TODO Auto-generated method stub
		getSession().update(p_user);
	}

	
	/*
	 * private NamedParameterJdbcTemplate jdbcTemplate;
	 * 
	 * @Autowired private void setDataSource( DataSource p_dataSource ) {
	 * this.jdbcTemplate = new NamedParameterJdbcTemplate(p_dataSource); }
	 * 
	 * @Override public List<User> findAllUsers() { // TODO Auto-generated
	 * method stub return
	 * jdbcTemplate.query("SELECT * FROM application_manager.USERS", new
	 * RowMapper<User>() {
	 * 
	 * @Override public User mapRow( ResultSet rs, int rowNum ) throws
	 * SQLException { // TODO Auto-generated method stub User l_user = new
	 * User(); l_user.setUserId(rs.getBigDecimal("USER_ID"));
	 * l_user.setUserEmesCompany(rs.getString("USER_EMES_COMPANY"));
	 * l_user.setUserEmesId(rs.getInt("USER_EMES_ID"));
	 * l_user.setUserUsername(rs.getString("USER_USERNAME"));
	 * l_user.setUserActive(rs.getBoolean("USER_ACTIVE"));
	 * l_user.setUserCreatedDate(rs.getTimestamp("USER_CREATED_DATE"));
	 * l_user.setUserCreatedBy(rs.getString("USER_CREATED_BY"));
	 * l_user.setUserUpdateDate(rs.getTimestamp("USER_UDPATE_DATE"));
	 * l_user.setUserUpdateBy(rs.getString("USER_UPDATE_BY"));
	 * 
	 * return l_user; }
	 * 
	 * }); }
	 * 
	 * @Override public boolean saveUser( User p_user ) { // TODO Auto-generated
	 * method stub MapSqlParameterSource l_paramMap = new
	 * MapSqlParameterSource(); l_paramMap.addValue("v0", p_user.getUserId());
	 * l_paramMap.addValue("v1", p_user.getUserEmesCompany());
	 * l_paramMap.addValue("v2", p_user.getUserEmesId());
	 * l_paramMap.addValue("v3", p_user.getUserUsername());
	 * l_paramMap.addValue("v4", p_user.getUserActive());
	 * l_paramMap.addValue("v5", p_user.getUserCreatedDate());
	 * l_paramMap.addValue("v6", p_user.getUserCreatedBy());
	 * l_paramMap.addValue("v7", p_user.getUserUpdateDate());
	 * l_paramMap.addValue("v8", p_user.getUserUpdateBy());
	 * 
	 * return jdbcTemplate.update(
	 * "INSERT INTO USERS( USER_ID, USER_EMES_COMPANY, USER_EMES_ID, USER_USERNAME, "
	 * + "USER_ACTIVE, " + "USER_CREATED_DATE, " + "USER_CREATED_BY, " +
	 * "USER_UDPATE_DATE, " + "USER_UPDATE_BY ) " +
	 * "VALUES ( :v0, :v1, :v2, :v3, :v4, :v5, :v6, :v7, :v8 )", l_paramMap) ==
	 * 1;
	 * 
	 * }
	 * 
	 * @Override public User findUserById( BigDecimal p_userId ) { // TODO
	 * Auto-generated method stub MapSqlParameterSource l_paramMap = new
	 * MapSqlParameterSource(); l_paramMap.addValue("p_Id", p_userId); User
	 * l_user = jdbcTemplate.
	 * queryForObject("SELECT * FROM application_manager.users WHERE USER_ID = :p_Id "
	 * , l_paramMap, new RowMapper<User>() {
	 * 
	 * @Override public User mapRow( ResultSet rs, int rowNum ) throws
	 * SQLException { // TODO Auto-generated method stub User l_user = new
	 * User(); l_user.setUserId(rs.getBigDecimal("USER_ID"));
	 * l_user.setUserEmesCompany(rs.getString("USER_EMES_COMPANY"));
	 * l_user.setUserEmesId(rs.getInt("USER_EMES_ID"));
	 * l_user.setUserUsername(rs.getString("USER_USERNAME"));
	 * l_user.setUserActive(rs.getBoolean("USER_ACTIVE"));
	 * l_user.setUserCreatedDate(rs.getTimestamp("USER_CREATED_DATE"));
	 * l_user.setUserCreatedBy(rs.getString("USER_CREATED_BY"));
	 * l_user.setUserUpdateDate(rs.getTimestamp("USER_UDPATE_DATE"));
	 * l_user.setUserUpdateBy(rs.getString("USER_UPDATE_BY"));
	 * 
	 * return l_user; }
	 * 
	 * } ); return l_user; }
	 * 
	 * @Override public List<UserRole> findRolesFromUserById( BigDecimal
	 * p_userId ) { // TODO Auto-generated method stub
	 * 
	 * MapSqlParameterSource l_paramMap = new MapSqlParameterSource();
	 * l_paramMap.addValue("p_UserId", p_userId); User l_user = new User();
	 * l_user.setUserId(p_userId);
	 * 
	 * return jdbcTemplate.
	 * query("SELECT  R.ROLE_ID, R.ROLE_NAME, R.ROLE_DESCRIPTION, R.ROLE_ACTIVE, "
	 * +
	 * "R.ROLE_CREATED_DATE, R.ROLE_CREATED_BY, R.ROLE_UDPATE_DATE, R.ROLE_UPDATE_BY, "
	 * +
	 * "UR.USRO_ID, UR.USRO_USER_ID, UR.USRO_ROLE_ID, UR.USRO_ACTIVE, UR.USRO_CREATED_DATE, "
	 * + "UR.USRO_CREATED_BY, UR.USRO_UDPATE_DATE, UR.USRO_UPDATE_BY " +
	 * "FROM    USER_ROLE UR INNER JOIN ROLES R ON UR.USRO_ROLE_ID = R.ROLE_ID "
	 * + "WHERE   UR.USRO_USER_ID = :p_UserId ", l_paramMap, new
	 * RowMapper<UserRole>() {
	 * 
	 * @Override public UserRole mapRow( ResultSet rs, int rowNum ) throws
	 * SQLException { // TODO Auto-generated method stub Rol l_rol = new Rol();
	 * l_rol.setRoleId(rs.getBigDecimal("ROLE_ID"));
	 * l_rol.setRoleName(rs.getString("ROLE_NAME"));
	 * l_rol.setRoleDescription(rs.getString("ROLE_DESCRIPTION"));
	 * l_rol.setRoleActive(rs.getBoolean("ROLE_ACTIVE"));
	 * l_rol.setRoleCreatedDate(rs.getTimestamp("ROLE_CREATED_DATE"));
	 * l_rol.setRoleCreatedBy(rs.getString("ROLE_CREATED_BY"));
	 * l_rol.setRoleUdpateDate(rs.getTimestamp("ROLE_UDPATE_DATE"));
	 * l_rol.setRoleUpdateBy(rs.getString("ROLE_UPDATE_BY"));
	 * 
	 * UserRole l_userRole = new UserRole(); l_userRole.setUsroId(
	 * rs.getBigDecimal("USRO_ID") ); l_userRole.setUsroUserId(l_user);
	 * l_userRole.setUsroRolId(l_rol);
	 * l_userRole.setUsroActive(rs.getBoolean("USRO_ACTIVE"));
	 * l_userRole.setUsroCreatedDate(rs.getTimestamp("USRO_CREATED_DATE"));
	 * l_userRole.setUsroCreatedBy(rs.getString("USRO_CREATED_BY"));
	 * l_userRole.setUsroUdpateDate(rs.getTimestamp("USRO_UDPATE_DATE"));
	 * l_userRole.setUsroUpdateBy(rs.getString("USRO_UPDATE_BY"));
	 * 
	 * 
	 * return l_userRole; }
	 * 
	 * }); }
	 * 
	 * @Override public List<UserApplication> findApplicationFromUserById(
	 * BigDecimal p_userId ) { // TODO Auto-generated method stub return null; }
	 * 
	 */

}
