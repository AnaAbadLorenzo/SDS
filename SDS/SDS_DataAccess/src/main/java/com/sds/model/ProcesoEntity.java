package com.sds.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "proceso")
public class ProcesoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proceso")
	private Integer idProceso;

	@Column(name = "nombre_proceso")
	private String nombreProceso;

	@Column(name = "descrip_proceso")
	private String descripProceso;

	@Column(name = "fecha_proceso")
	private Date fechaProceso;

	@Column(name = "borrado_proceso")
	private Integer borradoProceso;

	@OneToOne(cascade = CascadeType.ALL)
	private ProcedimientoEntity procedimiento;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_procedimiento_usuario_proceso", referencedColumnName = "id_procedimiento_usuario_proceso")
	private ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProc;

	public ProcesoEntity() {
		super();
	}

	public ProcesoEntity(final Integer idProceso, final String nombreProceso, final String descripProceso,
			final Date fechaProceso, final Integer borradoProceso, final ProcedimientoEntity procedimiento,
			final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProc) {
		super();
		this.idProceso = idProceso;
		this.nombreProceso = nombreProceso;
		this.descripProceso = descripProceso;
		this.fechaProceso = fechaProceso;
		this.borradoProceso = borradoProceso;
		this.procedimiento = procedimiento;
		this.procedimientoUsuarioProc = procedimientoUsuarioProc;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(final String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getDescripProceso() {
		return descripProceso;
	}

	public void setDescripProceso(final String descripProceso) {
		this.descripProceso = descripProceso;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(final Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Integer getBorradoProceso() {
		return borradoProceso;
	}

	public void setBorradoProceso(final Integer borradoProceso) {
		this.borradoProceso = borradoProceso;
	}

	public ProcedimientoEntity getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(final ProcedimientoEntity procedimiento) {
		this.procedimiento = procedimiento;
	}

	public ProcedimientoUsuarioProcesoEntity getProcedimientoUsuarioProc() {
		return procedimientoUsuarioProc;
	}

	public void setProcedimientoUsuarioProc(final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProc) {
		this.procedimientoUsuarioProc = procedimientoUsuarioProc;
	}

	@Override
	public String toString() {
		return "ProcesoEntity [idProceso=" + idProceso + ", nombreProceso=" + nombreProceso + ", descripProceso="
				+ descripProceso + ", fechaProceso=" + fechaProceso + ", borradoProceso=" + borradoProceso
				+ ", procedimiento=" + procedimiento + ", procedimientoUsuarioProc=" + procedimientoUsuarioProc + "]";
	}

}
