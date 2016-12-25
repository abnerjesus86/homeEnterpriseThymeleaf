package mx.com.lctpc.helpdeck.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "ACCOUNT_INFORMATION", schema = "APPLICATION_MANAGER" )
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "acinUserId")
public class AccountInformation {
	/*
	 * ACIN_USER_ID NOT NULL NUMBER ACIN_PROFILE_PICTURE NOT NULL VARCHAR2(150)
	 * ACIN_TELEPHONE NUMBER(10) ACIN_LAST_ACCESS NOT NULL DATE
	 * ACIN_ALTERNATE_EMAIL VARCHAR2(150) ACIN_CREATED_DATE NOT NULL DATE
	 * ACIN_CREATED_BY NOT NULL VARCHAR2(150) ACIN_UPDATE_DATE DATE
	 * ACIN_UPDATE_BY VARCHAR2(150)
	 */

	@Id
	@Column( name = "ACIN_USER_ID" )
	@GeneratedValue( generator = "myGeneratorUserId" )
	@GenericGenerator( name = "myGeneratorUserId", strategy = "foreign", parameters = @Parameter( value = "g_user", name = "property" ) )
	private BigDecimal	g_acinUserId;
	@Column( name = "ACIN_PROFILE_PICTURE" )
	private String		g_acinProfilePicture;
	@Column( name = "ACIN_TELEPHONE" )
	private long		g_acinTelephone;
	@Column( name = "ACIN_LAST_ACCESS" )
	private Timestamp	g_acinLastAccess;
	@Column( name = "ACIN_ALTERNATE_EMAIL" )
	private String		g_acinAlternateEmail;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ACIN_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_acinCreatedDate;
	@Column( name = "ACIN_CREATED_BY", insertable = true, updatable = false )
	private String		g_acinCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ACIN_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_acinUpdateDate;
	@Column( name = "ACIN_UPDATE_BY", insertable = true, updatable = true )
	private String		g_acinUpdateBy;
	@OneToOne
	@PrimaryKeyJoinColumn
/*	@OneToOne(cascade = CascadeType.ALL, mappedBy = "g_accountInf")*/
	private User		g_user;

	/**
	 * @return the acinUserId
	 */
	public BigDecimal getAcinUserId() {
		return this.g_acinUserId;
	}

	/**
	 * @param p_acinUserId
	 *            the acinUserId to set
	 */
	public void setAcinUserId( BigDecimal p_acinUserId ) {
		this.g_acinUserId = p_acinUserId;
	}

	/**
	 * @return the acinProfilePicture
	 */
	public String getAcinProfilePicture() {
		return this.g_acinProfilePicture;
	}

	/**
	 * @param p_acinProfilePicture
	 *            the acinProfilePicture to set
	 */
	public void setAcinProfilePicture( String p_acinProfilePicture ) {
		this.g_acinProfilePicture = p_acinProfilePicture;
	}

	/**
	 * @return the acinTelephone
	 */
	public long getAcinTelephone() {
		return this.g_acinTelephone;
	}

	/**
	 * @param p_acinTelephone
	 *            the acinTelephone to set
	 */
	public void setAcinTelephone( long p_acinTelephone ) {
		this.g_acinTelephone = p_acinTelephone;
	}

	/**
	 * @return the acinLastAccess
	 */
	public Timestamp getAcinLastAccess() {
		return this.g_acinLastAccess;
	}

	/**
	 * @param p_acinLastAccess
	 *            the acinLastAccess to set
	 */
	public void setAcinLastAccess( Timestamp p_acinLastAccess ) {
		this.g_acinLastAccess = p_acinLastAccess;
	}

	/**
	 * @return the acinAlternateEmail
	 */
	public String getAcinAlternateEmail() {
		return this.g_acinAlternateEmail;
	}

	/**
	 * @param p_acinAlternateEmail
	 *            the acinAlternateEmail to set
	 */
	public void setAcinAlternateEmail( String p_acinAlternateEmail ) {
		this.g_acinAlternateEmail = p_acinAlternateEmail;
	}

	/**
	 * @return the acinCreatedDate
	 */
	public Timestamp getAcinCreatedDate() {
		return this.g_acinCreatedDate;
	}

	/**
	 * @param p_acinCreatedDate
	 *            the acinCreatedDate to set
	 */
	public void setAcinCreatedDate( Timestamp p_acinCreatedDate ) {
		this.g_acinCreatedDate = p_acinCreatedDate;
	}

	/**
	 * @return the acinCreatedBy
	 */
	public String getAcinCreatedBy() {
		return this.g_acinCreatedBy;
	}

	/**
	 * @param p_acinCreatedBy
	 *            the acinCreatedBy to set
	 */
	public void setAcinCreatedBy( String p_acinCreatedBy ) {
		this.g_acinCreatedBy = p_acinCreatedBy;
	}

	/**
	 * @return the acinUpdateDate
	 */
	public Timestamp getAcinUpdateDate() {
		return this.g_acinUpdateDate;
	}

	/**
	 * @param p_acinUpdateDate
	 *            the acinUpdateDate to set
	 */
	public void setAcinUpdateDate( Timestamp p_acinUpdateDate ) {
		this.g_acinUpdateDate = p_acinUpdateDate;
	}

	/**
	 * @return the acinUpdateBy
	 */
	public String getAcinUpdateBy() {
		return this.g_acinUpdateBy;
	}

	/**
	 * @param p_acinUpdateBy
	 *            the acinUpdateBy to set
	 */
	public void setAcinUpdateBy( String p_acinUpdateBy ) {
		this.g_acinUpdateBy = p_acinUpdateBy;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return this.g_user;
	}

	/**
	 * @param p_user
	 *            the user to set
	 */
	public void setUser( User p_user ) {
		this.g_user = p_user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountInformation [g_acinUserId=" + this.g_acinUserId + ", g_acinProfilePicture="
				+ this.g_acinProfilePicture + ", g_acinTelephone=" + this.g_acinTelephone + ", g_acinLastAccess="
				+ this.g_acinLastAccess + ", g_acinAlternateEmail=" + this.g_acinAlternateEmail + ", g_acinCreatedDate="
				+ this.g_acinCreatedDate + ", g_acinCreatedBy=" + this.g_acinCreatedBy + ", g_acinUpdateDate="
				+ this.g_acinUpdateDate + ", g_acinUpdateBy=" + this.g_acinUpdateBy + "]";
	}

}
