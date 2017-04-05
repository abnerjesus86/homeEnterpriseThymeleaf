package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

@Entity
@Table( name = "\"PASSWORD\"", schema = "APPLICATION_MANAGER" )
public class Password implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * PSWD_ID NOT NULL NUMBER
	 * PSWD_USER_ID NOT NULL NUMBER(10)
	 * PSWD_PASSWORD NOT NULL VARCHAR2(250)
	 * PSWD_ACTIVE NOT NULL NUMBER
	 * PSWD_CREATED_DATE NOT NULL DATE
	 * PSWD_CREATED_BY NOT NULL VARCHAR2(150)
	 * PSWD_UPDATE_DATE DATE
	 * PSWD_UPDATE_BY VARCHAR2(150)
	 */

	@Id
	@Column( name = "PSWD_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PSWD_SEQ" )
	@SequenceGenerator( name = "PSWD_SEQ", sequenceName = "SQ_PSWD_ID", allocationSize = 1 )
	private BigDecimal	g_pswdId;
	@ManyToOne
	@JoinColumn(name="PSWD_USER_ID")
	private User		g_pswdUserId;
	@Column( name = "PSWD_PASSWORD" )
	private String		g_pswdPassword;
	@Column( name = "PSWD_ACTIVE" )
	private boolean		g_pswdActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PSWD_CREATED_DATE", insertable = true, updatable = false )
	private Date		g_pswdCreatedDate;
	@Column( name = "PSWD_CREATED_BY", insertable = true, updatable = false )
	private String		g_pswdCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PSWD_UPDATE_DATE", insertable = true, updatable = true )
	private Date		g_pswdUpdateDate;
	@Column( name = "PSWD_UPDATE_BY", insertable = true, updatable = true )
	private String		g_pswdUpdateBy;

	/**
	 * @return the pswdId
	 */
	public BigDecimal getPswdId() {
		return this.g_pswdId;
	}

	/**
	 * @param p_pswdId
	 *            the pswdId to set
	 */
	public void setPswdId( BigDecimal p_pswdId ) {
		this.g_pswdId = p_pswdId;
	}

	/**
	 * @return the pswdUserId
	 */
	public User getPswdUserId() {
		return this.g_pswdUserId;
	}

	/**
	 * @param p_pswdUserId
	 *            the pswdUserId to set
	 */
	public void setPswdUserId( User p_pswdUserId ) {
		this.g_pswdUserId = p_pswdUserId;
	}

	/**
	 * @return the pswdPassword
	 */
	public String getPswdPassword() {
		return this.g_pswdPassword;
	}

	/**
	 * @param p_pswdPassword
	 *            the pswdPassword to set
	 */
	public void setPswdPassword( String p_pswdPassword ) {
		this.g_pswdPassword = p_pswdPassword;
	}

	/**
	 * @return the pswdActive
	 */
	public boolean isPswdActive() {
		return this.g_pswdActive;
	}

	/**
	 * @param p_pswdActive
	 *            the pswdActive to set
	 */
	public void setPswdActive( boolean p_pswdActive ) {
		this.g_pswdActive = p_pswdActive;
	}

	/**
	 * @return the pswdCreatedDate
	 */
	public Date getPswdCreatedDate() {
		return this.g_pswdCreatedDate;
	}

	/**
	 * @param p_pswdCreatedDate
	 *            the pswdCreatedDate to set
	 */
	public void setPswdCreatedDate( Date p_pswdCreatedDate ) {
		this.g_pswdCreatedDate = p_pswdCreatedDate;
	}

	/**
	 * @return the pswdCreatedBy
	 */
	public String getPswdCreatedBy() {
		return this.g_pswdCreatedBy;
	}

	/**
	 * @param p_pswdCreatedBy
	 *            the pswdCreatedBy to set
	 */
	public void setPswdCreatedBy( String p_pswdCreatedBy ) {
		this.g_pswdCreatedBy = p_pswdCreatedBy;
	}

	/**
	 * @return the pswdUpdateDate
	 */
	public Date getPswdUpdateDate() {
		return this.g_pswdUpdateDate;
	}

	/**
	 * @param p_pswdUpdateDate
	 *            the pswdUpdateDate to set
	 */
	public void setPswdUpdateDate( Date p_pswdUpdateDate ) {
		this.g_pswdUpdateDate = p_pswdUpdateDate;
	}

	/**
	 * @return the pswdUpdateBy
	 */
	public String getPswdUpdateBy() {
		return this.g_pswdUpdateBy;
	}

	/**
	 * @param p_pswdUpdateBy
	 *            the pswdUpdateBy to set
	 */
	public void setPswdUpdateBy( String p_pswdUpdateBy ) {
		this.g_pswdUpdateBy = p_pswdUpdateBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.g_pswdId == null) ? 0 : this.g_pswdId.hashCode());
		result = prime * result + ((this.g_pswdPassword == null) ? 0 : this.g_pswdPassword.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Password))
			return false;
		Password other = (Password) obj;
		if (this.g_pswdId == null) {
			if (other.g_pswdId != null)
				return false;
		} else if (!this.g_pswdId.equals(other.g_pswdId))
			return false;
		if (this.g_pswdPassword == null) {
			if (other.g_pswdPassword != null)
				return false;
		} else if (!this.g_pswdPassword.equals(other.g_pswdPassword))
			return false;
		return true;
	}

	
	
	
}
