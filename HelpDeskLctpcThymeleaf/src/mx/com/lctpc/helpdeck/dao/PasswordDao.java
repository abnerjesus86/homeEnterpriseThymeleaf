package mx.com.lctpc.helpdeck.dao;

public interface PasswordDao {
	public String resetPassword(String p_username, String p_passwordNew, boolean p_reqNewPass)throws Exception;
	public String changePassword(String p_username, String p_passwordCurrent, String p_passwordNew)throws Exception;
}
