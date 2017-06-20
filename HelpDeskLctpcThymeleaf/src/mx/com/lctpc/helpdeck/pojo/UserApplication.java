package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name="USER_APPLICATION",  schema="APPLICATION_MANAGER" )
@JsonIgnoreProperties(value = { "usapUserId"})
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, property = "usapId")
public class UserApplication implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 	USAP_ID           NOT NULL NUMBER        
		USAP_APNS_ID      NOT NULL NUMBER        
		USAP_USER_ID      NOT NULL NUMBER        
		USAP_ACTIVE       NOT NULL NUMBER(1)     
		USAP_CREATED_DATE NOT NULL DATE          
		USAP_CREATED_BY   NOT NULL VARCHAR2(150) 
		USAP_UPDATE_DATE           DATE          
		USAP_UPDATE_BY             VARCHAR2(150) 
	 */
	@Id
	@GeneratedValue
	@Column(name="USAP_ID")
	private BigDecimal	g_usapId;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="USAP_APPN_ID")
	@JsonUnwrapped
	private Application	g_usapAppnId;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="USAP_USER_ID")
	private User		g_usapUserId;
	@Column(name="USAP_ACTIVE")
	private boolean		g_usapActive;
	@Column(name="USAP_CREATED_DATE")
	private Timestamp	g_usapCreatedDate;
	@Column(name="USAP_CREATED_BY")
	private String		g_usapCreatedBy;
	@Column(name="USAP_UPDATE_DATE")
	private Timestamp	g_usapUpdateDate;
	@Column(name="USAP_UPDATE_BY")
	private String		g_usapUpdateBy;

	/**
	 * @return the usapId
	 */
	public BigDecimal getUsapId() {
		return this.g_usapId;
	}

	/**
	 * @param p_usapId
	 *            the usapId to set
	 */
	public void setUsapId( BigDecimal p_usapId ) {
		this.g_usapId = p_usapId;
	}

	/**
	 * @return the usapAppnId
	 */
	public Application getUsapAppnId() {
		return this.g_usapAppnId;
	}

	/**
	 * @param p_usapAppnId the usapAppnId to set
	 */
	public void setUsapAppnId( Application p_usapAppnId ) {
		this.g_usapAppnId = p_usapAppnId;
	}

	/**
	 * @return the usapUserId
	 */
	public User getUsapUserId() {
		return this.g_usapUserId;
	}

	/**
	 * @param p_usapUserId
	 *            the usapUserId to set
	 */
	public void setUsapUserId( User p_usapUserId ) {
		this.g_usapUserId = p_usapUserId;
	}

	/**
	 * @return the usapActive
	 */
	public boolean isUsapActive() {
		return this.g_usapActive;
	}

	/**
	 * @param p_usapActive
	 *            the usapActive to set
	 */
	public void setUsapActive( boolean p_usapActive ) {
		this.g_usapActive = p_usapActive;
	}

	/**
	 * @return the usapCreatedDate
	 */
	public Timestamp getUsapCreatedDate() {
		return this.g_usapCreatedDate;
	}

	/**
	 * @param p_usapCreatedDate
	 *            the usapCreatedDate to set
	 */
	public void setUsapCreatedDate( Timestamp p_usapCreatedDate ) {
		this.g_usapCreatedDate = p_usapCreatedDate;
	}

	/**
	 * @return the usapCreatedBy
	 */
	public String getUsapCreatedBy() {
		return this.g_usapCreatedBy;
	}

	/**
	 * @param p_usapCreatedBy
	 *            the usapCreatedBy to set
	 */
	public void setUsapCreatedBy( String p_usapCreatedBy ) {
		this.g_usapCreatedBy = p_usapCreatedBy;
	}

	/**
	 * @return the usapUdpateDate
	 */
	public Timestamp getUsapUpdateDate() {
		return this.g_usapUpdateDate;
	}

	/**
	 * @param p_usapUdpateDate
	 *            the usapUdpateDate to set
	 */
	public void setUsapUdpateDate( Timestamp p_usapUpdateDate ) {
		this.g_usapUpdateDate = p_usapUpdateDate;
	}

	/**
	 * @return the usapUpdateBy
	 */
	public String getUsapUpdateBy() {
		return this.g_usapUpdateBy;
	}

	/**
	 * @param p_usapUpdateBy
	 *            the usapUpdateBy to set
	 */
	public void setUsapUpdateBy( String p_usapUpdateBy ) {
		this.g_usapUpdateBy = p_usapUpdateBy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserApplication [g_usapId=" + this.g_usapId + ", g_usapAppnId=" + this.g_usapAppnId + ", g_usapUserId="
				+ this.g_usapUserId + ", g_usapActive=" + this.g_usapActive + ", g_usapCreatedDate="
				+ this.g_usapCreatedDate + ", g_usapCreatedBy=" + this.g_usapCreatedBy + ", g_usapUpdateDate="
				+ this.g_usapUpdateDate + ", g_usapUpdateBy=" + this.g_usapUpdateBy + "]";
	}

}
