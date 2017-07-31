package mx.com.lctpc.helpdeck.dao;

import java.util.List;

import mx.com.lctpc.helpdeck.pojo.VEmp;

public interface VEmpDao {
	
	public List<VEmp> findAllEmps();
	public List<VEmp> findEmpByName(String p_Name);
	public VEmp findEmp(String p_Compania, String p_Emp);
	
}
