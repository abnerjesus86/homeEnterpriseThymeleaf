package mx.com.lctpc.helpdeck.dao;

/*import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;*/
import org.springframework.stereotype.Component;

import mx.com.lctpc.helpdeck.pojo.ContainerEdi;

@Component("containerEdiDao")
public class ContainerEdiDaoImpl implements ContainerEdiDao {
	
	/*private NamedParameterJdbcTemplate g_jdbcTemplate;
	
	@Autowired
	private void setDataSource(DataSource p_dataSource){
		this.g_jdbcTemplate = new NamedParameterJdbcTemplate(p_dataSource);
	}
	*/
	@Override
	public boolean executeCoarri( ContainerEdi p_CntrEdi ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean executeCodeco( ContainerEdi p_CntrEdi ) {
		// TODO Auto-generated method stub
		return false;
	}

}
