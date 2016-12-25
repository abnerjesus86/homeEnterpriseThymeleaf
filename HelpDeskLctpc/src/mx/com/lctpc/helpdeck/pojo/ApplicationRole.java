package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "APPLICATION_ROLE", schema = "APPLICATION_MANAGER" )
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, property = "aproId")
public class ApplicationRole implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * APRO_ID NOT NULL NUMBER APRO_APNS_ID NOT NULL NUMBER APRO_ROLE_ID NOT
	 * NULL NUMBER APRO_ACTIVE NOT NULL NUMBER(1) APRO_CREATED_DATE NOT NULL
	 * DATE APRO_CREATED_BY NOT NULL VARCHAR2(150) APRO_UDPATE_DATE DATE
	 * APRO_UPDATE_BY VARCHAR2(150)
	 */
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "APRL_SEQ" )
	@SequenceGenerator( name = "APRL_SEQ", sequenceName = "SQ_APRO_ID", allocationSize = 1 )
	@Column( name = "APRO_ID" )
	private BigDecimal	g_aproId;
	@ManyToOne( cascade = CascadeType.REFRESH )
	@JoinColumn( name = "APRO_APPN_ID" )
	private Application	g_aproAppnId;
	@ManyToOne( cascade = CascadeType.REFRESH )
	@JoinColumn( name = "APRO_ROLE_ID" )
	@JsonUnwrapped
	private Rol			g_aproRoleId;
	@Column( name = "APRO_ACTIVE" )
	private boolean		g_aproActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "APRO_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_aproCreatedDate;
	@Column( name = "APRO_CREATED_BY", insertable = true, updatable = false )
	private String		g_aproCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "APRO_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_aproUdpateDate;
	@Column( name = "APRO_UPDATE_BY", insertable = true, updatable = true )
	private String		g_aproUpdateBy;

	/**
	 * @return the aproId
	 */
	public BigDecimal getAproId() {
		return this.g_aproId;
	}

	/**
	 * @param p_aproId
	 *            the aproId to set
	 */
	public void setAproId( BigDecimal p_aproId ) {
		this.g_aproId = p_aproId;
	}

	/**
	 * @return the aproAppnId
	 */
	public Application getAproAppnId() {
		return this.g_aproAppnId;
	}

	/**
	 * @param p_aproAppnId
	 *            the aproAppnId to set
	 */
	public void setAproAppnId( Application p_aproAppnId ) {
		this.g_aproAppnId = p_aproAppnId;
	}

	/**
	 * @return the aproRoleId
	 */
	public Rol getAproRoleId() {
		return this.g_aproRoleId;
	}

	/**
	 * @param p_aproRoleId
	 *            the aproRoleId to set
	 */
	public void setAproRoleId( Rol p_aproRoleId ) {
		this.g_aproRoleId = p_aproRoleId;
	}

	/**
	 * @return the aproActive
	 */
	public boolean isAproActive() {
		return this.g_aproActive;
	}

	/**
	 * @param p_aproActive
	 *            the aproActive to set
	 */
	public void setAproActive( boolean p_aproActive ) {
		this.g_aproActive = p_aproActive;
	}

	/**
	 * @return the aproCreatedDate
	 */
	public Timestamp getAproCreatedDate() {
		return this.g_aproCreatedDate;
	}

	/**
	 * @param p_aproCreatedDate
	 *            the aproCreatedDate to set
	 */
	public void setAproCreatedDate( Timestamp p_aproCreatedDate ) {
		this.g_aproCreatedDate = p_aproCreatedDate;
	}

	/**
	 * @return the aproCreatedBy
	 */
	public String getAproCreatedBy() {
		return this.g_aproCreatedBy;
	}

	/**
	 * @param p_aproCreatedBy
	 *            the aproCreatedBy to set
	 */
	public void setAproCreatedBy( String p_aproCreatedBy ) {
		this.g_aproCreatedBy = p_aproCreatedBy;
	}

	/**
	 * @return the aproUdpateDate
	 */
	public Timestamp getAproUdpateDate() {
		return this.g_aproUdpateDate;
	}

	/**
	 * @param p_aproUdpateDate
	 *            the aproUdpateDate to set
	 */
	public void setAproUdpateDate( Timestamp p_aproUdpateDate ) {
		this.g_aproUdpateDate = p_aproUdpateDate;
	}

	/**
	 * @return the aproUpdateBy
	 */
	public String getAproUpdateBy() {
		return this.g_aproUpdateBy;
	}

	/**
	 * @param p_aproUpdateBy
	 *            the aproUpdateBy to set
	 */
	public void setAproUpdateBy( String p_aproUpdateBy ) {
		this.g_aproUpdateBy = p_aproUpdateBy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApplicationRole [g_aproId=" + this.g_aproId + ", g_aproAppnId=" + this.g_aproAppnId + ", g_aproRoleId="
				+ this.g_aproRoleId + ", g_aproActive=" + this.g_aproActive + ", g_aproCreatedDate="
				+ this.g_aproCreatedDate + ", g_aproCreatedBy=" + this.g_aproCreatedBy + ", g_aproUdpateDate="
				+ this.g_aproUdpateDate + ", g_aproUpdateBy=" + this.g_aproUpdateBy + "]";
	}

}
