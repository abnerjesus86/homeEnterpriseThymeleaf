package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserApplication;
import mx.com.lctpc.helpdeck.pojo.UserRole;

public interface UserDao {
	public List<User> findAllUsers();
	public void saveUser(User p_user);
	public void updateUser(User p_user);
	public User findUserById(BigDecimal p_userId);
	public List<UserRole> findRolesFromUserById(BigDecimal p_userId);
	public List<UserApplication> findApplicationFromUserById(BigDecimal p_userId);
	public void deleteUser(User p_user);
	
}
