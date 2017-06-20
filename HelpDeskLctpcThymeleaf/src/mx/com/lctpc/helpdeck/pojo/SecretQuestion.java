package mx.com.lctpc.helpdeck.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table( name = "SECRET_QUESTION", schema = "APPLICATION_MANAGER" )
public class SecretQuestion {
//SEQU_ID           NOT NULL NUMBER        
//SEQU_QUESTION     NOT NULL VARCHAR2(150) 
//SEQU_ACTIVE                NUMBER(1)     
//SEQU_CREATED_DATE NOT NULL DATE          
//SEQU_CREATED_BY   NOT NULL VARCHAR2(150) 
//SEQU_UPDATE_DATE           DATE          
//SEQU_UPDATE_BY             VARCHAR2(150) 
	
	@Id
	@Column( name = "SEQU_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQU_SEQ" )
	@SequenceGenerator( name = "SEQU_SEQ", sequenceName = "SQ_SEQU_ID", allocationSize = 1 )
	private BigDecimal g_sequId;
	@Column( name = "SEQU_QUESTION" )
	private String g_sequQuestion;
	@Column( name = "SEQU_ACTIVE" )
	private boolean g_sequActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "SEQU_CREATED_DATE", insertable = true, updatable = false )
	private Date g_sequCreatedDate;
	@Column( name = "SEQU_CREATED_BY", insertable = true, updatable = false )
	private String g_sequCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "SEQU_UPDATE_DATE", insertable = true, updatable = true )
	private Date g_sequUpdateDate;
	@Column( name = "SEQU_UPDATE_BY", insertable = true, updatable = true )
	private String g_sequUpdateBy;
	/**
	 * @return the sequId
	 */
	public BigDecimal getSequId() {
		return this.g_sequId;
	}
	/**
	 * @param p_sequId the sequId to set
	 */
	public void setSequId( BigDecimal p_sequId ) {
		this.g_sequId = p_sequId;
	}
	/**
	 * @return the sequQuestion
	 */
	public String getSequQuestion() {
		return this.g_sequQuestion;
	}
	/**
	 * @param p_sequQuestion the sequQuestion to set
	 */
	public void setSequQuestion( String p_sequQuestion ) {
		this.g_sequQuestion = p_sequQuestion;
	}
	/**
	 * @return the sequActive
	 */
	public boolean isSequActive() {
		return this.g_sequActive;
	}
	/**
	 * @param p_sequActive the sequActive to set
	 */
	public void setSequActive( boolean p_sequActive ) {
		this.g_sequActive = p_sequActive;
	}
	/**
	 * @return the sequCreatedDate
	 */
	public Date getSequCreatedDate() {
		return this.g_sequCreatedDate;
	}
	/**
	 * @param p_sequCreatedDate the sequCreatedDate to set
	 */
	public void setSequCreatedDate( Date p_sequCreatedDate ) {
		this.g_sequCreatedDate = p_sequCreatedDate;
	}
	/**
	 * @return the sequCreatedBy
	 */
	public String getSequCreatedBy() {
		return this.g_sequCreatedBy;
	}
	/**
	 * @param p_sequCreatedBy the sequCreatedBy to set
	 */
	public void setSequCreatedBy( String p_sequCreatedBy ) {
		this.g_sequCreatedBy = p_sequCreatedBy;
	}
	/**
	 * @return the sequUpdateDate
	 */
	public Date getSequUpdateDate() {
		return this.g_sequUpdateDate;
	}
	/**
	 * @param p_sequUpdateDate the sequUpdateDate to set
	 */
	public void setSequUpdateDate( Date p_sequUpdateDate ) {
		this.g_sequUpdateDate = p_sequUpdateDate;
	}
	/**
	 * @return the sequUpdateBy
	 */
	public String getSequUpdateBy() {
		return this.g_sequUpdateBy;
	}
	/**
	 * @param p_sequUpdateBy the sequUpdateBy to set
	 */
	public void setSequUpdateBy( String p_sequUpdateBy ) {
		this.g_sequUpdateBy = p_sequUpdateBy;
	}
	
	
	
}
