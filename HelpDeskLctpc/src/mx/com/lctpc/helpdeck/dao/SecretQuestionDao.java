package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.SecretQuestion;

public interface SecretQuestionDao {
	public List<SecretQuestion> findAllSecretQuestion();
	public void saveSecretQuestion(SecretQuestion p_scq);
	public void updateSecretQuestion(SecretQuestion p_scq);
	public SecretQuestion findSecretQuestionById(BigDecimal p_scqId);
	public void deleteSecretQuestion(SecretQuestion p_scq);
}
