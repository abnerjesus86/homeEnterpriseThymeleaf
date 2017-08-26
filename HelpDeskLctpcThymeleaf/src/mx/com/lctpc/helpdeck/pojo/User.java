package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "\"USER\"", schema = "APPLICATION_MANAGER" )
@JsonIgnoreProperties( value = { "userRoles", "userApplications" } )
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId" )
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * BigDecimal USER_ID; String USER_EMES_COMPANY; int USER_EMES_ID; String
	 * USER_USERNAME; short USER_ACTIVE; Timestamp USER_CREATED_DATE; String
	 * USER_CREATED_BY; Timestamp USER_UDPATE_DATE; String USER_UPDATE_BY; ,
	 * schema ="APPLICATION_MANAGER" , g_userCreatedBy
	 */
	
	@Id
	@Column( name = "USER_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "TRAIN_SEQ" )
	@SequenceGenerator( name = "TRAIN_SEQ", sequenceName = "SQ_USER_ID", allocationSize = 1 )
	private BigDecimal				g_userId;
	@Column( name = "USER_EMES_COMPANY" )
	private String					g_userEmesCompany;
	@Column( name = "USER_EMES_ID" )
	private String				g_userEmesId;
	@Column( name = "USER_USERNAME" )
	private String					g_userUsername;
	@Column( name = "USER_NEW_PASSWORD" )
	private boolean g_userNewPassword;
	@Column( name = "USER_ACTIVE" )
	private boolean					g_userActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "USER_CREATED_DATE", insertable = true, updatable = false )
	private Date					g_userCreatedDate;
	@Column( name = "USER_CREATED_BY", insertable = true, updatable = false )
	private String					g_userCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "USER_UPDATE_DATE", insertable = true, updatable = true )
	private Date					g_userUpdateDate;
	@Column( name = "USER_UPDATE_BY", insertable = true, updatable = true )
	private String					g_userUpdateBy;
	@OneToOne( cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@JsonUnwrapped
	private AccountInformation		g_accountInf;
	@OneToMany( mappedBy = "g_usroUserId" )
	private List<UserRole>			g_userRoles	= new ArrayList<UserRole>();
	@OneToMany( mappedBy = "g_usapUserId" )
	private List<UserApplication>	g_userApplications;
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "g_pswdUserId" )
	@Fetch( value = FetchMode.SUBSELECT )
	@Where( clause = "PSWD_ACTIVE = true" )
	private List<Password>			g_passwords	= new ArrayList<Password>();
	
	
	/**
	 * @return the userId
	 */
	public BigDecimal getUserId() {
		return this.g_userId;
	}

	/**
	 * @param p_userId
	 *            the userId to set
	 */
	public void setUserId( BigDecimal p_userId ) {
		this.g_userId = p_userId;
	}

	/**
	 * @return the userEmesCompany
	 */
	public String getUserEmesCompany() {
		return this.g_userEmesCompany;
	}

	/**
	 * @param p_userEmesCompany
	 *            the userEmesCompany to set
	 */
	public void setUserEmesCompany( String p_userEmesCompany ) {
		this.g_userEmesCompany = p_userEmesCompany;
	}

	/**
	 * @return the userEmesId
	 */
	public String getUserEmesId() {
		return this.g_userEmesId;
	}

	/**
	 * @param p_userEmesId
	 *            the userEmesId to set
	 */
	public void setUserEmesId( String p_userEmesId ) {
		this.g_userEmesId = p_userEmesId;
	}

	/**
	 * @return the userUsername
	 */
	public String getUserUsername() {
		return this.g_userUsername;
	}

	/**
	 * @param p_userUsername
	 *            the userUsername to set
	 */
	public void setUserUsername( String p_userUsername ) {
		this.g_userUsername = p_userUsername;
	}

	/**
	 * @return the userNewPassword
	 */
	public boolean isUserNewPassword() {
		return this.g_userNewPassword;
	}

	/**
	 * @param p_userNewPassword the userNewPassword to set
	 */
	public void setUserNewPassword( boolean p_userNewPassword ) {
		this.g_userNewPassword = p_userNewPassword;
	}

	/**
	 * @return the userActive
	 */
	public boolean getUserActive() {
		return this.g_userActive;
	}

	/**
	 * @param p_userActive
	 *            the userActive to set
	 */
	public void setUserActive( boolean p_userActive ) {
		this.g_userActive = p_userActive;
	}

	/**
	 * @return the userCreatedDate
	 */
	public Date getUserCreatedDate() {
		return this.g_userCreatedDate;
	}

	/**
	 * @param p_userCreatedDate
	 *            the userCreatedDate to set
	 */
	public void setUserCreatedDate( Date p_userCreatedDate ) {
		this.g_userCreatedDate = p_userCreatedDate;
	}

	/**
	 * @return the userCreatedBy
	 */
	public String getUserCreatedBy() {
		return this.g_userCreatedBy;
	}

	/**
	 * @param p_userCreatedBy
	 *            the userCreatedBy to set
	 */
	public void setUserCreatedBy( String p_userCreatedBy ) {
		this.g_userCreatedBy = p_userCreatedBy;
	}

	/**
	 * @return the userUpdateBy
	 */
	public String getUserUpdateBy() {
		return this.g_userUpdateBy;
	}

	/**
	 * @param p_userUpdateBy
	 *            the userUpdateBy to set
	 */
	public void setUserUpdateBy( String p_userUpdateBy ) {
		this.g_userUpdateBy = p_userUpdateBy;
	}

	/**
	 * @return the userUpdateDate
	 */
	public Date getUserUpdateDate() {
		return this.g_userUpdateDate;
	}

	/**
	 * @param p_userUpdateDate
	 *            the userUpdateDate to set
	 */
	public void setUserUpdateDate( Date p_userUpdateDate ) {
		this.g_userUpdateDate = p_userUpdateDate;
	}

	/**
	 * @return the accountInf
	 */
	@JsonIgnore
	public AccountInformation getAccountInf() {
		return this.g_accountInf;
	}

	/**
	 * @param p_accountInf
	 *            the accountInf to set
	 */
	@JsonSetter
	public void setAccountInf( AccountInformation p_accountInf ) {
		this.g_accountInf = p_accountInf;
	}

	/**
	 * @return the userRoles
	 */
	public List<UserRole> getUserRoles() {
		return this.g_userRoles;
	}

	/**
	 * @param p_userRoles
	 *            the userRoles to set
	 */
	public void setUserRoles( List<UserRole> p_userRoles ) {
		this.g_userRoles = p_userRoles;
	}

	/**
	 * @return the userApplications
	 */
	public List<UserApplication> getUserApplications() {
		return this.g_userApplications;
	}

	/**
	 * @param p_userApplications
	 *            the userApplications to set
	 */
	public void setUserApplications( List<UserApplication> p_userApplications ) {
		this.g_userApplications = p_userApplications;
	}

	/**
	 * @return the passwords
	 */
	public List<Password> getPasswords() {
		return this.g_passwords;
	}

	/**
	 * @param p_passwords
	 *            the passwords to set
	 */
	public void setPasswords( List<Password> p_passwords ) {
		this.g_passwords = p_passwords;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [g_userId=" + this.g_userId + ", g_userEmesCompany=" + this.g_userEmesCompany + ", g_userEmesId=" + this.g_userEmesId + ", g_userUsername=" + this.g_userUsername + ", g_userNewPassword="
				+ this.g_userNewPassword + ", g_userActive=" + this.g_userActive + ", g_userCreatedDate=" + this.g_userCreatedDate + ", g_userCreatedBy=" + this.g_userCreatedBy + ", g_userUpdateDate="
				+ this.g_userUpdateDate + ", g_userUpdateBy=" + this.g_userUpdateBy + ", g_accountInf=" + this.g_accountInf + "]";
	}


	
	

}
