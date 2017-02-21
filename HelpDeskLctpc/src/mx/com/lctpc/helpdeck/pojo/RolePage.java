package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "ROLE_PAGE" )
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, property = "ropaId")
public class RolePage implements Serializable {
	/*
	 	ROPA_ID NUMBER
		ROPA_PAEN_ID NUMBER (10)
		ROPA_ROLE_ID NUMBER
		ROPA_PRMN_ID NUMBER
		ROPA_ACTIVE NUMBER (1)
		ROPA_CREATED_DATE DATE
		ROPA_CREATED_BY VARCHAR2 (150 BYTE)
		ROPA_UPDATE_DATE DATE
		ROPA_UPDATE_BY VARCHAR2 (150 BYTE) 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ROPA_ID" )
	@SequenceGenerator( name = "ROPA_ID", sequenceName = "SQ_ROPA_ID", allocationSize = 1 )
	@Column( name = "ROPA_ID" )
	private BigDecimal	g_ropaId;
	/*@Column( name = "ROPA_ROLE_ID" )*/
	@Transient
	private Rol			g_ropaRoleId;
	/*@Column( name = "ROPA_PRMN_ID" )*/
	@Transient
	private Permission	g_ropaPrmnId;
	/*@Column ( name = "ROPA_PAEN_ID" )*/
	@Transient
	private PageEntity g_ropaPaenId;
	@Column( name = "ROPA_ACTIVE" )
	private boolean		g_ropaActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ROPA_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_ropaCreatedDate;
	@Column( name = "ROPA_CREATED_BY", insertable = true, updatable = false )
	private String		g_ropaCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ROPA_UPDATE_DATE", insertable = true, updatable = true  )
	private Timestamp	g_ropaUpdateDate;
	@Column( name = "ROPA_UPDATE_BY", insertable = true, updatable = true  )
	private String		g_ropaUpdateBy;
	
	/**
	 * @return the ropaId
	 */
	public BigDecimal getRopaId() {
		return this.g_ropaId;
	}
	/**
	 * @param p_ropaId the ropaId to set
	 */
	public void setRopaId( BigDecimal p_ropaId ) {
		this.g_ropaId = p_ropaId;
	}
	/**
	 * @return the ropaRoleId
	 */
	public Rol getRopaRoleId() {
		return this.g_ropaRoleId;
	}
	/**
	 * @param p_ropaRoleId the ropaRoleId to set
	 */
	public void setRopaRoleId( Rol p_ropaRoleId ) {
		this.g_ropaRoleId = p_ropaRoleId;
	}
	/**
	 * @return the ropaPrmnId
	 */
	public Permission getRopaPrmnId() {
		return this.g_ropaPrmnId;
	}
	/**
	 * @param p_ropaPrmnId the ropaPrmnId to set
	 */
	public void setRopaPrmnId( Permission p_ropaPrmnId ) {
		this.g_ropaPrmnId = p_ropaPrmnId;
	}
	/**
	 * @return the ropaPaenId
	 */
	public PageEntity getRopaPaenId() {
		return this.g_ropaPaenId;
	}
	/**
	 * @param p_ropaPaenId the ropaPaenId to set
	 */
	public void setRopaPaenId( PageEntity p_ropaPaenId ) {
		this.g_ropaPaenId = p_ropaPaenId;
	}
	/**
	 * @return the ropaActive
	 */
	public boolean isRopaActive() {
		return this.g_ropaActive;
	}
	/**
	 * @param p_ropaActive the ropaActive to set
	 */
	public void setRopaActive( boolean p_ropaActive ) {
		this.g_ropaActive = p_ropaActive;
	}
	/**
	 * @return the ropaCreatedDate
	 */
	public Timestamp getRopaCreatedDate() {
		return this.g_ropaCreatedDate;
	}
	/**
	 * @param p_ropaCreatedDate the ropaCreatedDate to set
	 */
	public void setRopaCreatedDate( Timestamp p_ropaCreatedDate ) {
		this.g_ropaCreatedDate = p_ropaCreatedDate;
	}
	/**
	 * @return the ropaCreatedBy
	 */
	public String getRopaCreatedBy() {
		return this.g_ropaCreatedBy;
	}
	/**
	 * @param p_ropaCreatedBy the ropaCreatedBy to set
	 */
	public void setRopaCreatedBy( String p_ropaCreatedBy ) {
		this.g_ropaCreatedBy = p_ropaCreatedBy;
	}
	/**
	 * @return the ropaUpdateDate
	 */
	public Timestamp getRopaUpdateDate() {
		return this.g_ropaUpdateDate;
	}
	/**
	 * @param p_ropaUpdateDate the ropaUpdateDate to set
	 */
	public void setRopaUpdateDate( Timestamp p_ropaUpdateDate ) {
		this.g_ropaUpdateDate = p_ropaUpdateDate;
	}
	/**
	 * @return the ropaUpdateBy
	 */
	public String getRopaUpdateBy() {
		return this.g_ropaUpdateBy;
	}
	/**
	 * @param p_ropaUpdateBy the ropaUpdateBy to set
	 */
	public void setRopaUpdateBy( String p_ropaUpdateBy ) {
		this.g_ropaUpdateBy = p_ropaUpdateBy;
	}
	

	

}
