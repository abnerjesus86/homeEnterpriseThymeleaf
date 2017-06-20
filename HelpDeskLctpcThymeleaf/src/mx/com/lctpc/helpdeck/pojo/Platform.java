package mx.com.lctpc.helpdeck.pojo;

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

@Entity
@Table( name = "PLATFORM", schema = "APPLICATION_MANAGER" )
public class Platform {
	
	/*
	PLFM_ID NUMBER (10)
	PLFM_DVLP_ID NUMBER (10)
	PLFM_ACTIVE NUMBER (1)
	PLFM_CREATED_DATE DATE
	PLFM_CREATED_BY VARCHAR2 (150 BYTE)
	PLFM_UPDATE_DATE DATE
	PLFM_UPDATE_BY VARCHAR2 (150 BYTE)
	*/
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PLFM_SEQ" )
	@SequenceGenerator( name = "PLFM_SEQ", sequenceName = "SQ_PLFM_ID", allocationSize = 1 )
	@Column( name = "PLFM_ID" )
	private BigDecimal g_plfmId;
	@ManyToOne
	@JoinColumn( name = "PLFM_DVLP_ID")
	private Development g_plfmDvlpId;
	@Column( name = "PLFM_ACTIVE" )
	private boolean		g_plfmActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PLFM_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_plfmCreatedDate;
	@Column( name = "PLFM_CREATED_BY", insertable = true, updatable = false )
	private String		g_plfmCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PLFM_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_plfmUdpateDate;
	@Column( name = "PLFM_UPDATE_BY", insertable = true, updatable = true )
	private String		g_plfmUpdateBy;
	/**
	 * @return the plfmId
	 */
	public BigDecimal getPlfmId() {
		return this.g_plfmId;
	}
	/**
	 * @param p_plfmId the plfmId to set
	 */
	public void setPlfmId( BigDecimal p_plfmId ) {
		this.g_plfmId = p_plfmId;
	}
	/**
	 * @return the plfmDvlpId
	 */
	public Development getPlfmDvlpId() {
		return this.g_plfmDvlpId;
	}
	/**
	 * @param p_plfmDvlpId the plfmDvlpId to set
	 */
	public void setPlfmDvlpId( Development p_plfmDvlpId ) {
		this.g_plfmDvlpId = p_plfmDvlpId;
	}
	/**
	 * @return the plfmActive
	 */
	public boolean isPlfmActive() {
		return this.g_plfmActive;
	}
	/**
	 * @param p_plfmActive the plfmActive to set
	 */
	public void setPlfmActive( boolean p_plfmActive ) {
		this.g_plfmActive = p_plfmActive;
	}
	/**
	 * @return the plfmCreatedDate
	 */
	public Timestamp getPlfmCreatedDate() {
		return this.g_plfmCreatedDate;
	}
	/**
	 * @param p_plfmCreatedDate the plfmCreatedDate to set
	 */
	public void setPlfmCreatedDate( Timestamp p_plfmCreatedDate ) {
		this.g_plfmCreatedDate = p_plfmCreatedDate;
	}
	/**
	 * @return the plfmCreatedBy
	 */
	public String getPlfmCreatedBy() {
		return this.g_plfmCreatedBy;
	}
	/**
	 * @param p_plfmCreatedBy the plfmCreatedBy to set
	 */
	public void setPlfmCreatedBy( String p_plfmCreatedBy ) {
		this.g_plfmCreatedBy = p_plfmCreatedBy;
	}
	/**
	 * @return the plfmUdpateDate
	 */
	public Timestamp getPlfmUdpateDate() {
		return this.g_plfmUdpateDate;
	}
	/**
	 * @param p_plfmUdpateDate the plfmUdpateDate to set
	 */
	public void setPlfmUdpateDate( Timestamp p_plfmUdpateDate ) {
		this.g_plfmUdpateDate = p_plfmUdpateDate;
	}
	/**
	 * @return the plfmUpdateBy
	 */
	public String getPlfmUpdateBy() {
		return this.g_plfmUpdateBy;
	}
	/**
	 * @param p_plfmUpdateBy the plfmUpdateBy to set
	 */
	public void setPlfmUpdateBy( String p_plfmUpdateBy ) {
		this.g_plfmUpdateBy = p_plfmUpdateBy;
	}
	
	
}
