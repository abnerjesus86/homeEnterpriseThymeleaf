package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.SecretQuestionDao;
import mx.com.lctpc.helpdeck.pojo.SecretQuestion;

@Service
public class SecretQuestionService {

	@Autowired
	private SecretQuestionDao secretQuestionDao;

	public List<SecretQuestion> findAllSecretQuestion() {
		return secretQuestionDao.findAllSecretQuestion();
	}

	public void saveOrUpdateSecretQuestion( SecretQuestion p_scq ) {
		
		if (p_scq.getSequId() == null || findSecretQuestionById(p_scq.getSequId()) == null) {

			secretQuestionDao.saveSecretQuestion(p_scq);
		} else {

			secretQuestionDao.updateSecretQuestion(p_scq);
		}
	}

	public SecretQuestion findSecretQuestionById( BigDecimal p_scqId ) {
		return secretQuestionDao.findSecretQuestionById(p_scqId);
	}
	
	public void deleteSecretQuestion(SecretQuestion p_scq){
		p_scq.setSequActive(false);
		secretQuestionDao.deleteSecretQuestion(p_scq);
	}
	
}
