package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.ApplicationRole;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.UserRole;

public interface RolDao {
	public List<Rol> findAllRoles();
	public void saveRol(Rol p_rol);
	public void updateRo(Rol p_rol);
	public Rol findRolById(BigDecimal p_rol);
	public List<ApplicationRole> findApplicationFromRolById(BigDecimal p_rol);
	public List<UserRole> findUserFromRolById(BigDecimal p_rol);
}
