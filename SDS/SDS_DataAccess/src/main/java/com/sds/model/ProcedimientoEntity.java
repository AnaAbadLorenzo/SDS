package com.sds.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "procedimiento")
public class ProcedimientoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_procedimiento")
	private Integer idProcedimiento;

	@Column(name = "nombre_procedimiento")
	private String nombreProcedimiento;

	@Column(name = "descrip_procedimiento")
	private String descripProcedimiento;

	@Column(name = "fecha_procedimiento")
	private Date fechaProcedimiento;

	@Column(name = "borrado_procedimiento")
	private Integer borradoProcedimiento;

	@Column(name = "check_usuario")
	private Boolean checkUsuario;

	@OneToOne(mappedBy = "procedimiento", cascade = CascadeType.ALL)
	private PlanEntity plan;

	public ProcedimientoEntity() {
		super();
	}

	public ProcedimientoEntity(final Integer idProcedimiento, final String nombreProcedimiento,
			final String descripProcedimiento, final Date fechaProcedimiento, final Integer borradoProcedimiento,
			final Boolean checkUsuario) {
		super();
		this.idProcedimiento = idProcedimiento;
		this.nombreProcedimiento = nombreProcedimiento;
		this.descripProcedimiento = descripProcedimiento;
		this.fechaProcedimiento = fechaProcedimiento;
		this.borradoProcedimiento = borradoProcedimiento;
		this.checkUsuario = checkUsuario;
	}

	public Integer getIdProcedimiento() {
		return idProcedimiento;
	}

	public void setIdProcedimiento(final Integer idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
	}

	public String getNombreProcedimiento() {
		return nombreProcedimiento;
	}

	public void setNombreProcedimiento(final String nombreProcedimiento) {
		this.nombreProcedimiento = nombreProcedimiento;
	}

	public String getDescripProcedimiento() {
		return descripProcedimiento;
	}

	public void setDescripProcedimiento(final String descripProcedimiento) {
		this.descripProcedimiento = descripProcedimiento;
	}

	public Date getFechaProcedimiento() {
		return fechaProcedimiento;
	}

	public void setFechaProcedimiento(final Date fechaProcedimiento) {
		this.fechaProcedimiento = fechaProcedimiento;
	}

	public Integer getBorradoProcedimiento() {
		return borradoProcedimiento;
	}

	public void setBorradoProcedimiento(final Integer borradoProcedimiento) {
		this.borradoProcedimiento = borradoProcedimiento;
	}

	public Boolean getCheckUsuario() {
		return checkUsuario;
	}

	public void setCheckUsuario(final Boolean checkUsuario) {
		this.checkUsuario = checkUsuario;
	}

	public PlanEntity getPlan() {
		return plan;
	}

	public void setPlan(final PlanEntity plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "ProcedimientoEntity [idProcedimiento=" + idProcedimiento + ", nombreProcedimiento="
				+ nombreProcedimiento + ", descripProcedimiento=" + descripProcedimiento + ", fechaProcedimiento="
				+ fechaProcedimiento + ", borradoProcedimiento=" + borradoProcedimiento + ", checkUsuario="
				+ checkUsuario + "]";
	}

}
