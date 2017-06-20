package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.Permission;

public interface PermissionDao {
	public List<Permission> findAllPermisions();
	public void savePermission(Permission p_per);
	public void updatePermission(Permission p_per);
	public Permission findPermissionById(BigDecimal p_perId);
	public void deletePermission(Permission p_per);
	public List<Permission> findAllPermissionsActive();
	
	
}
