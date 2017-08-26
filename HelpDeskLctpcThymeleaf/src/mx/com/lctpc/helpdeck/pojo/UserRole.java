package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
@Table( name = "USER_ROLE",  schema="APPLICATION_MANAGER" )
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, property = "usroId")
public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * USRO_ID NOT NULL NUMBER USRO_USER_ID NOT NULL NUMBER USRO_ROLE_ID NOT
	 * NULL NUMBER USRO_ACTIVE NOT NULL NUMBER(1) USRO_CREATED_DATE NOT NULL
	 * DATE USRO_CREATED_BY NOT NULL VARCHAR2(150) USRO_UPDATE_DATE DATE
	 * USRO_UPDATE_BY VARCHAR2(150)
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "USRO_ID" )
	@SequenceGenerator( name = "USRO_ID", sequenceName = "SQ_USRO_ID", allocationSize = 1 )
	@Column( name = "USRO_ID" )
	private BigDecimal	g_usroId;
	@ManyToOne
	@JoinColumn( name = "USRO_USER_ID" )
	private User		g_usroUserId;
	@ManyToOne
	@JoinColumn( name = "USRO_ROLE_ID" )
	@JsonUnwrapped
	private Rol			g_usroRolId;
	@Column( name = "USRO_ACTIVE" )
	private boolean		g_usroActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "USRO_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_usroCreatedDate;
	@Column( name = "USRO_CREATED_BY", insertable = true, updatable = false )
	private String		g_usroCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "USRO_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_usroUpdateDate;
	@Column( name = "USRO_UPDATE_BY", insertable = true, updatable = true )
	private String		g_usroUpdateBy;

	/**
	 * @return the usroId
	 */
	public BigDecimal getUsroId() {
		return this.g_usroId;
	}

	/**
	 * @param p_usroId
	 *            the usroId to set
	 */
	public void setUsroId( BigDecimal p_usroId ) {
		this.g_usroId = p_usroId;
	}

	/**
	 * @return the usroUserId
	 */
	public User getUsroUserId() {
		return this.g_usroUserId;
	}

	/**
	 * @param p_usroUserId
	 *            the usroUserId to set
	 */
	public void setUsroUserId( User p_usroUserId ) {
		this.g_usroUserId = p_usroUserId;
	}

	/**
	 * @return the usroRolId
	 */
	public Rol getUsroRolId() {
		return this.g_usroRolId;
	}

	/**
	 * @param p_usroRolId
	 *            the usroRolId to set
	 */
	public void setUsroRolId( Rol p_usroRolId ) {
		this.g_usroRolId = p_usroRolId;
	}

	/**
	 * @return the usroActive
	 */
	public boolean getUsroActive() {
		return this.g_usroActive;
	}

	/**
	 * @param p_usroActive
	 *            the usroActive to set
	 */
	public void setUsroActive( boolean p_usroActive ) {
		this.g_usroActive = p_usroActive;
	}

	/**
	 * @return the usroCreatedDate
	 */
	public Timestamp getUsroCreatedDate() {
		return this.g_usroCreatedDate;
	}

	/**
	 * @param p_usroCreatedDate
	 *            the usroCreatedDate to set
	 */
	public void setUsroCreatedDate( Timestamp p_usroCreatedDate ) {
		this.g_usroCreatedDate = p_usroCreatedDate;
	}

	/**
	 * @return the usroCreatedBy
	 */
	public String getUsroCreatedBy() {
		return this.g_usroCreatedBy;
	}

	/**
	 * @param p_usroCreatedBy
	 *            the usroCreatedBy to set
	 */
	public void setUsroCreatedBy( String p_usroCreatedBy ) {
		this.g_usroCreatedBy = p_usroCreatedBy;
	}

	/**
	 * @return the usroUpdateDate
	 */
	public Timestamp getUsroUpdateDate() {
		return this.g_usroUpdateDate;
	}

	/**
	 * @param p_usroUpdateDate
	 *            the usroUpdateDate to set
	 */
	public void setUsroUpdateDate( Timestamp p_usroUpdateDate ) {
		this.g_usroUpdateDate = p_usroUpdateDate;
	}

	/**
	 * @return the usroUpdateBy
	 */
	public String getUsroUpdateBy() {
		return this.g_usroUpdateBy;
	}

	/**
	 * @param p_usroUpdateBy
	 *            the usroUpdateBy to set
	 */
	public void setUsroUpdateBy( String p_usroUpdateBy ) {
		this.g_usroUpdateBy = p_usroUpdateBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRole [g_usroId=" + this.g_usroId + ", g_usroUserId=" + this.g_usroUserId + ", g_usroRolId=" + this.g_usroRolId + ", g_usroActive=" + this.g_usroActive + ", g_usroCreatedDate=" + this.g_usroCreatedDate
				+ ", g_usroCreatedBy=" + this.g_usroCreatedBy + ", g_usroUpdateDate=" + this.g_usroUpdateDate + ", g_usroUpdateBy=" + this.g_usroUpdateBy + "]";
	}

	
	
	
}
