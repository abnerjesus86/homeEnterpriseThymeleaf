package mx.com.lctpc.helpdeck.pojo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomRolDeserializer extends StdDeserializer<Rol> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomRolDeserializer(){
		this(null);
	}
	public CustomRolDeserializer( Class<?> p_vc ) {
		super(p_vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rol deserialize( JsonParser p_p, DeserializationContext p_ctxt ) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		System.out.println("entro aqui... " + p_p);
		JsonNode node = p_p.getCodec().readTree(p_p);
		BigDecimal l_id = node.get("roleId").decimalValue();
		Rol l_rol = new Rol();
		l_rol.setRoleId(l_id);
		return l_rol;
	}

}
