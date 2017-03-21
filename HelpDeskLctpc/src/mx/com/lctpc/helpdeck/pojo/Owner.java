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

@Entity
@Table( name = "OWNER", schema = "APPLICATION_MANAGER" )
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
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "APPS_SEQ" )
	@SequenceGenerator( name = "APPS_SEQ", sequenceName = "SQ_APNS_ID", allocationSize = 1 )
	private BigDecimal	g_OwnrId;
	@Column( name = "OWNR_NAME" )
	private String		g_OwnrName;
	@Column( name = "OWNR_LOGO" )
	private String		g_OwnrLogo;
	@Column( name = "OWNR_ACTIVE" )
	private boolean		g_OwnrActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "OWNR_CREATED_DATE", insertable = true, updatable = false )
	private Date		g_OwnrCreatedDate;
	@Column( name = "OWNR_CREATED_BY", insertable = true, updatable = false )
	private String		g_OwnrCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "OWNR_UPDATE_DATE", insertable = true, updatable = true )
	private Date		g_OwnrUpdateDate;
	@Column( name = "OWNR_UPDATE_BY", insertable = true, updatable = true )
	private String		g_OwnrUpdateBy;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "g_appnOwnrId")
	private List<Application> g_appns = new ArrayList<Application>();
	
	/**
	 * @return the ownrId
	 */
	public BigDecimal getOwnrId() {
		return this.g_OwnrId;
	}
	/**
	 * @param p_ownrId the ownrId to set
	 */
	public void setOwnrId( BigDecimal p_ownrId ) {
		this.g_OwnrId = p_ownrId;
	}
	/**
	 * @return the ownrName
	 */
	public String getOwnrName() {
		return this.g_OwnrName;
	}
	/**
	 * @param p_ownrName the ownrName to set
	 */
	public void setOwnrName( String p_ownrName ) {
		this.g_OwnrName = p_ownrName;
	}
	/**
	 * @return the ownrLogo
	 */
	public String getOwnrLogo() {
		return this.g_OwnrLogo;
	}
	/**
	 * @param p_ownrLogo the ownrLogo to set
	 */
	public void setOwnrLogo( String p_ownrLogo ) {
		this.g_OwnrLogo = p_ownrLogo;
	}
	/**
	 * @return the ownrActive
	 */
	public boolean isOwnrActive() {
		return this.g_OwnrActive;
	}
	/**
	 * @param p_ownrActive the ownrActive to set
	 */
	public void setOwnrActive( boolean p_ownrActive ) {
		this.g_OwnrActive = p_ownrActive;
	}
	/**
	 * @return the ownrCreatedDate
	 */
	public Date getOwnrCreatedDate() {
		return this.g_OwnrCreatedDate;
	}
	/**
	 * @param p_ownrCreatedDate the ownrCreatedDate to set
	 */
	public void setOwnrCreatedDate( Date p_ownrCreatedDate ) {
		this.g_OwnrCreatedDate = p_ownrCreatedDate;
	}
	/**
	 * @return the ownrCreatedBy
	 */
	public String getOwnrCreatedBy() {
		return this.g_OwnrCreatedBy;
	}
	/**
	 * @param p_ownrCreatedBy the ownrCreatedBy to set
	 */
	public void setOwnrCreatedBy( String p_ownrCreatedBy ) {
		this.g_OwnrCreatedBy = p_ownrCreatedBy;
	}
	/**
	 * @return the ownrUpdateDate
	 */
	public Date getOwnrUpdateDate() {
		return this.g_OwnrUpdateDate;
	}
	/**
	 * @param p_ownrUpdateDate the ownrUpdateDate to set
	 */
	public void setOwnrUpdateDate( Date p_ownrUpdateDate ) {
		this.g_OwnrUpdateDate = p_ownrUpdateDate;
	}
	/**
	 * @return the ownrUpdateBy
	 */
	public String getOwnrUpdateBy() {
		return this.g_OwnrUpdateBy;
	}
	/**
	 * @param p_ownrUpdateBy the ownrUpdateBy to set
	 */
	public void setOwnrUpdateBy( String p_ownrUpdateBy ) {
		this.g_OwnrUpdateBy = p_ownrUpdateBy;
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
