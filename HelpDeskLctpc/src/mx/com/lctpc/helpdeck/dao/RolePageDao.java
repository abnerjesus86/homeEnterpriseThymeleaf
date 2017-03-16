package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.RolePage;

public interface RolePageDao {
	public void save(RolePage p_rolePage);
	public void update(RolePage p_rolePage);
	public List<RolePage> findAllRolePage();
	public RolePage findRolePageById(BigDecimal p_ropaId);
	public List<RolePage> findRolesPagesActiveFromApplicationById( BigDecimal p_appId );
	public void delete(RolePage p_rolePage);
}
