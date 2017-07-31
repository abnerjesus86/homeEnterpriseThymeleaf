package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name="\"V_EMP\"", schema ="\"PSW\"")
public class VEmp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column( name = "COMPANIA" )
	private String g_compania;
	@Column( name = "EMPLEADO" )
	private String g_empleado;
	@Column( name = "NOMBRE" )
	private String g_nombre;
	@Column( name = "DEPARTAMENTO" )
	private String g_departamento;
	@Column( name = "NOM_DEPARTAMENTO" )
	private String g_nomDepartamento;
	@Column( name = "PUESTO" )
	private String g_puesto;
	@Column( name = "NOM_PUESTO" )
	private String g_nomPuesto;
	@Column( name = "ACTIVO" )
	private String g_activo;
	@Column( name = "FECHA_INGRESO" )
	private Date g_fechaIngreso;
	@Column( name = "MOTIVO" )
	private String g_motivo;
	/**
	 * @return the compania
	 */
	public String getCompania() {
		return this.g_compania;
	}
	/**
	 * @param p_compania the compania to set
	 */
	public void setCompania( String p_compania ) {
		this.g_compania = p_compania;
	}
	/**
	 * @return the empleado
	 */
	public String getEmpleado() {
		return this.g_empleado;
	}
	/**
	 * @param p_empleado the empleado to set
	 */
	public void setEmpleado( String p_empleado ) {
		this.g_empleado = p_empleado;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return this.g_nombre;
	}
	/**
	 * @param p_nombre the nombre to set
	 */
	public void setNombre( String p_nombre ) {
		this.g_nombre = p_nombre;
	}
	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return this.g_departamento;
	}
	/**
	 * @param p_departamento the departamento to set
	 */
	public void setDepartamento( String p_departamento ) {
		this.g_departamento = p_departamento;
	}
	/**
	 * @return the nomDepartamento
	 */
	public String getNomDepartamento() {
		return this.g_nomDepartamento;
	}
	/**
	 * @param p_nomDepartamento the nomDepartamento to set
	 */
	public void setNomDepartamento( String p_nomDepartamento ) {
		this.g_nomDepartamento = p_nomDepartamento;
	}
	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return this.g_puesto;
	}
	/**
	 * @param p_puesto the puesto to set
	 */
	public void setPuesto( String p_puesto ) {
		this.g_puesto = p_puesto;
	}
	/**
	 * @return the nomPuesto
	 */
	public String getNomPuesto() {
		return this.g_nomPuesto;
	}
	/**
	 * @param p_nomPuesto the nomPuesto to set
	 */
	public void setNomPuesto( String p_nomPuesto ) {
		this.g_nomPuesto = p_nomPuesto;
	}
	/**
	 * @return the activo
	 */
	public String getActivo() {
		return this.g_activo;
	}
	/**
	 * @param p_activo the activo to set
	 */
	public void setActivo( String p_activo ) {
		this.g_activo = p_activo;
	}
	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return this.g_fechaIngreso;
	}
	/**
	 * @param p_fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso( Date p_fechaIngreso ) {
		this.g_fechaIngreso = p_fechaIngreso;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return this.g_motivo;
	}
	/**
	 * @param p_motivo the motivo to set
	 */
	public void setMotivo( String p_motivo ) {
		this.g_motivo = p_motivo;
	}
	
	
}
