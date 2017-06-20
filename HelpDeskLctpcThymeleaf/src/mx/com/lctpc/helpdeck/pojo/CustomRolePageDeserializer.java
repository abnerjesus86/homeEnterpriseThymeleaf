package mx.com.lctpc.helpdeck.pojo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomRolePageDeserializer extends StdDeserializer<RolePage> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomRolePageDeserializer(){
		this(null);
	}
	
	public CustomRolePageDeserializer( Class<?> p_vc ) {
		super(p_vc);
	}

	@Override
	public RolePage deserialize( JsonParser p_p, DeserializationContext p_ctxt ) throws IOException, JsonProcessingException {
		
		JsonNode node = p_p.getCodec().readTree(p_p);
		BigDecimal l_ropaRoleId = node.get("ropaRoleId").isNull() ? null : node.get("ropaRoleId").decimalValue();
		BigDecimal l_ropaPrmnId = node.get("ropaPrmnId").isNull() ? null : node.get("ropaPrmnId").decimalValue();
		BigDecimal l_ropaPaenId = node.get("ropaPaenId").isNull() ? null : node.get("ropaPaenId").decimalValue();
		BigDecimal l_ropaId = node.get("ropaId").isNull()  ? null : node.get("ropaId").decimalValue();
		boolean l_ropaActive = node.get("ropaActive").booleanValue();
		
		Rol l_rol = new Rol();
		l_rol.setRoleId(l_ropaRoleId);
		
		Permission l_perm = new Permission();
		l_perm.setPrmnId(l_ropaPrmnId);
		
		PageEntity l_pagEnt = new PageEntity();
		l_pagEnt.setPaenId(l_ropaPaenId);
		
		RolePage l_rolePage = new RolePage();
		l_rolePage.setRopaId(l_ropaId);
		l_rolePage.setRopaRoleId(l_rol);
		l_rolePage.setRopaPrmnId(l_perm);
		l_rolePage.setRopaPaenId(l_pagEnt);
		l_rolePage.setRopaActive(l_ropaActive);
		
		return l_rolePage;
	}

}
