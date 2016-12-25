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
	
	
}
