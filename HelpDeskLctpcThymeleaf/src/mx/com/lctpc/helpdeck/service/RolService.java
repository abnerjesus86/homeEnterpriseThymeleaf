package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.RolDao;
import mx.com.lctpc.helpdeck.pojo.ApplicationRole;
import mx.com.lctpc.helpdeck.pojo.Rol;
import mx.com.lctpc.helpdeck.pojo.UserRole;

@Service
public class RolService {

	@Autowired
	private RolDao rolDao;

	public List<Rol> findAll() {
		return rolDao.findAllRoles();
	}
	
	public List<Rol> findRolesActive() {
		return rolDao.findRolesActive();
	}
	
	public void saveOrUpdateRol( Rol p_rol ) {

		if (p_rol.getRoleId() == null || findRolById(p_rol.getRoleId()) == null) {
			
			rolDao.saveRol(p_rol);
		} else {
			rolDao.updateRo(p_rol);
		}

	}

	public Rol findRolById( BigDecimal p_rolId ) {
		return rolDao.findRolById(p_rolId);
	}

	public List<ApplicationRole> findApplicationFromRolById( BigDecimal p_rolId ) {
		return rolDao.findApplicationFromRolById(p_rolId);
	}

	public List<UserRole> findUserFromRolById( BigDecimal p_rolId ) {
		return rolDao.findUserFromRolById(p_rolId);
	}

}
