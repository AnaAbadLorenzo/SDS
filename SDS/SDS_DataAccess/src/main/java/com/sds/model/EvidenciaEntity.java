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
import javax.persistence.Table;

@Entity
@Table(name = "evidencia")
public class EvidenciaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evidencia")
	private Integer idEvidencia;

	@Column(name = "fecha_evidencia")
	private Date fechaEvidencia;

	@Column(name = "borrado_evidencia")
	private Integer borradoEvidencia;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_procedimiento_usuario_proceso", referencedColumnName = "id_procedimiento_usuario_proceso")
	private ProcedimientoUsuarioProcesoEntity procedimientosUsuarioProceso;

	public EvidenciaEntity() {
		super();
	}

	public EvidenciaEntity(final Integer idEvidencia, final Date fechaEvidencia, final Integer borradoEvidencia) {
		super();
		this.idEvidencia = idEvidencia;
		this.fechaEvidencia = fechaEvidencia;
		this.borradoEvidencia = borradoEvidencia;
	}

	public Integer getIdEvidencia() {
		return idEvidencia;
	}

	public void setIdEvidencia(final Integer idEvidencia) {
		this.idEvidencia = idEvidencia;
	}

	public Date getFechaEvidencia() {
		return fechaEvidencia;
	}

	public void setFechaEvidencia(final Date fechaEvidencia) {
		this.fechaEvidencia = fechaEvidencia;
	}

	public Integer getBorradoEvidencia() {
		return borradoEvidencia;
	}

	public void setBorradoEvidencia(final Integer borradoEvidencia) {
		this.borradoEvidencia = borradoEvidencia;
	}

	public ProcedimientoUsuarioProcesoEntity getProcedimientosUsuarioProceso() {
		return procedimientosUsuarioProceso;
	}

	public void setProcedimientosUsuarioProceso(final ProcedimientoUsuarioProcesoEntity procedimientosUsuarioProceso) {
		this.procedimientosUsuarioProceso = procedimientosUsuarioProceso;
	}

	@Override
	public String toString() {
		return "EvidenciaEntity [idEvidencia=" + idEvidencia + ", fechaEvidencia=" + fechaEvidencia
				+ ", borradoEvidencia=" + borradoEvidencia + "]";
	}

}
