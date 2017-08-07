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

import mx.com.lctpc.helpdeck.pojo.ApplicationRole;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.UserRole;

@Transactional
@Repository
public class RolDaoImpl implements RolDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Rol> findAllRoles() {
		// TODO Auto-generated method stub
		Query<Rol> l_query = getSession().createQuery("FROM Rol", Rol.class);
		return l_query.getResultList();
	}

	@Override
	public void saveRol( Rol p_rol ) {
		// TODO Auto-generated method stub
		getSession().save(p_rol);
	}

	@Override
	public void updateRo( Rol p_rol ) {
		// TODO Auto-generated method stub
		getSession().update(p_rol);
	}

	@Override
	public Rol findRolById( BigDecimal p_rol ) {
		// TODO Auto-generated method stub
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Rol> l_crtQuery = l_builder.createQuery(Rol.class);
		Root<Rol> l_rootUser = l_crtQuery.from(Rol.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_roleId"), p_rol));

		return (Rol) getSession().createQuery(l_crtQuery).getSingleResult();

	}

	@Override
	public List<ApplicationRole> findApplicationFromRolById( BigDecimal p_rol ) {
		// TODO Auto-generated method stub
		Query<ApplicationRole> l_queryUserRole = getSession().createQuery(
				"select rolApps from Rol rol join rol.g_roleApplication rolApps where rol.g_roleId = :p_rolId",
				ApplicationRole.class).setParameter("p_rolId", p_rol);

		return l_queryUserRole.getResultList();
	}

	@Override
	public List<UserRole> findUserFromRolById( BigDecimal p_rol ) {
		Query<UserRole> l_queryUserRole = getSession().createQuery(
				"select rolUser from Rol rol join rol.g_roleUsers rolUser where rol.g_roleId = :p_rolId",
				UserRole.class).setParameter("p_rolId", p_rol);

		return l_queryUserRole.getResultList();
	}

	@Override
	public List<Rol> findRolesActive() {
		// TODO Auto-generated method stub
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Rol> l_crtQuery = l_builder.createQuery(Rol.class);
		Root<Rol> l_rootUser = l_crtQuery.from(Rol.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_roleActive"), true));

		return (List<Rol>) getSession().createQuery(l_crtQuery).getResultList();
		
	}

	
	
	
	/*
	@Override
	public List<Rol> findAllRoles() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("Select * from application_manager.ROLE", new RowMapper<Rol>() {

			@Override
			public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Rol l_rol = new Rol();
				l_rol.setRoleId(rs.getBigDecimal("ROLE_ID"));
				l_rol.setRoleName(rs.getString("ROLE_NAME"));
				l_rol.setRoleDescription(rs.getString("ROLE_DESCRIPTION"));
				l_rol.setRoleActive(rs.getBoolean("ROLE_ACTIVE"));
				l_rol.setRoleCreatedDate(rs.getTimestamp("ROLE_CREATED_DATE"));
				l_rol.setRoleCreatedBy(rs.getString("ROLE_CREATED_BY"));
				l_rol.setRoleUdpateDate(rs.getTimestamp("ROLE_UDPATE_DATE"));
				l_rol.setRoleUpdateBy(rs.getString("ROLE_UPDATE_BY"));
				
				return l_rol;
			}
			
			
		});

	}

	@Override
	public boolean saveRol( Rol p_rol ) {
		// TODO Auto-generated method stub

		MapSqlParameterSource l_paramMap = new MapSqlParameterSource();
		l_paramMap.addValue("v0", p_rol.getRoleId());
		l_paramMap.addValue("v1", p_rol.getRoleName());
		l_paramMap.addValue("v2", p_rol.getRoleDescription());
		l_paramMap.addValue("v3", p_rol.getRoleActive());
		l_paramMap.addValue("v4", p_rol.getRoleCreatedDate());
		l_paramMap.addValue("v5", p_rol.getRoleCreatedBy());
		l_paramMap.addValue("v6", p_rol.getRoleUdpateDate());
		l_paramMap.addValue("v7", p_rol.getRoleUpdateBy());

		return jdbcTemplate.update("INSERT INTO " + "ROLES ( ROLE_ID," + "    ROLE_NAME," + "    ROLE_DESCRIPTION,"
				+ "    ROLE_ACTIVE," + "    ROLE_CREATED_DATE," + "    ROLE_CREATED_BY," + "    ROLE_UDPATE_DATE,"
				+ "    ROLE_UPDATE_BY" + "  )  VALUES ( :v0, :v1, :v2, :v3, :v4, :v5, :v6, :v7 )", l_paramMap) == 1;

	}

	@Override
	public boolean updateRo( Rol p_rol ) {
		// TODO Auto-generated method stub
		
		return false;
	}
	*/
}
