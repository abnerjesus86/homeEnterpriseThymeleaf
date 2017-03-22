package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "OWNER", schema = "APPLICATION_MANAGER" )
//@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "ownrId" )
@JsonIgnoreProperties( value = { "appns" } )
public class Owner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * OWNR_ID NOT NULL NUMBER(10) OWNR_NAME NOT NULL VARCHAR2(150) OWNR_LOGO
	 * VARCHAR2(250) OWNR_ACTIVE NOT NULL NUMBER(1) OWNR_CREATED_DATE NOT NULL
	 * DATE OWNR_CREATED_BY NOT NULL VARCHAR2(150) OWNR_UPDATE_DATE DATE
	 * OWNR_UPDATE_BY VARCHAR2(150)
	 * 
	 */
	@Id
	@Column( name = "OWNR_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "OWNR_SEQ" )
	@SequenceGenerator( name = "OWNR_SEQ", sequenceName = "SQ_OWNR_ID", allocationSize = 1 )
	private BigDecimal	g_ownrId;
	@Column( name = "OWNR_NAME" )
	private String		g_ownrName;
	@Column( name = "OWNR_LOGO" )
	private String		g_ownrLogo;
	@Column( name = "OWNR_ACTIVE" )
	private boolean		g_ownrActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "OWNR_CREATED_DATE", insertable = true, updatable = false )
	private Date		g_ownrCreatedDate;
	@Column( name = "OWNR_CREATED_BY", insertable = true, updatable = false )
	private String		g_ownrCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "OWNR_UPDATE_DATE", insertable = true, updatable = true )
	private Date		g_ownrUpdateDate;
	@Column( name = "OWNR_UPDATE_BY", insertable = true, updatable = true )
	private String		g_ownrUpdateBy;
	@OneToMany( mappedBy = "g_appnOwnrId")
	private List<Application> g_appns ;//= new ArrayList<Application>();
	
	/**
	 * @return the ownrId
	 */
	public BigDecimal getOwnrId() {
		return this.g_ownrId;
	}
	/**
	 * @param p_ownrId the ownrId to set
	 */
	public void setOwnrId( BigDecimal p_ownrId ) {
		this.g_ownrId = p_ownrId;
	}
	/**
	 * @return the ownrName
	 */
	public String getOwnrName() {
		return this.g_ownrName;
	}
	/**
	 * @param p_ownrName the ownrName to set
	 */
	public void setOwnrName( String p_ownrName ) {
		this.g_ownrName = p_ownrName;
	}
	/**
	 * @return the ownrLogo
	 */
	public String getOwnrLogo() {
		return this.g_ownrLogo;
	}
	/**
	 * @param p_ownrLogo the ownrLogo to set
	 */
	public void setOwnrLogo( String p_ownrLogo ) {
		this.g_ownrLogo = p_ownrLogo;
	}
	/**
	 * @return the ownrActive
	 */
	public boolean isOwnrActive() {
		return this.g_ownrActive;
	}
	/**
	 * @param p_ownrActive the ownrActive to set
	 */
	public void setOwnrActive( boolean p_ownrActive ) {
		this.g_ownrActive = p_ownrActive;
	}
	/**
	 * @return the ownrCreatedDate
	 */
	public Date getOwnrCreatedDate() {
		return this.g_ownrCreatedDate;
	}
	/**
	 * @param p_ownrCreatedDate the ownrCreatedDate to set
	 */
	public void setOwnrCreatedDate( Date p_ownrCreatedDate ) {
		this.g_ownrCreatedDate = p_ownrCreatedDate;
	}
	/**
	 * @return the ownrCreatedBy
	 */
	public String getOwnrCreatedBy() {
		return this.g_ownrCreatedBy;
	}
	/**
	 * @param p_ownrCreatedBy the ownrCreatedBy to set
	 */
	public void setOwnrCreatedBy( String p_ownrCreatedBy ) {
		this.g_ownrCreatedBy = p_ownrCreatedBy;
	}
	/**
	 * @return the ownrUpdateDate
	 */
	public Date getOwnrUpdateDate() {
		return this.g_ownrUpdateDate;
	}
	/**
	 * @param p_ownrUpdateDate the ownrUpdateDate to set
	 */
	public void setOwnrUpdateDate( Date p_ownrUpdateDate ) {
		this.g_ownrUpdateDate = p_ownrUpdateDate;
	}
	/**
	 * @return the ownrUpdateBy
	 */
	public String getOwnrUpdateBy() {
		return this.g_ownrUpdateBy;
	}
	/**
	 * @param p_ownrUpdateBy the ownrUpdateBy to set
	 */
	public void setOwnrUpdateBy( String p_ownrUpdateBy ) {
		this.g_ownrUpdateBy = p_ownrUpdateBy;
	}
	/**
	 * @return the appns
	 */
	public List<Application> getAppns() {
		return this.g_appns;
	}
	/**
	 * @param p_appns the appns to set
	 */
	public void setAppns( List<Application> p_appns ) {
		this.g_appns = p_appns;
	}
	
	
}
