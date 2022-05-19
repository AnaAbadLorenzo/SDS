package com.sds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

	@Column(name = "nombre_fichero")
	private String nombreFichero;

	@OneToOne
	@JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso", updatable = false)
	@JoinColumn(name = "id_procedimiento_usuario", referencedColumnName = "id_procedimiento_usuario", updatable = false)
	private ProcedimientoUsuarioProcesoEntity procedimientosUsuarioProceso;

	public EvidenciaEntity() {
		super();
	}

	public EvidenciaEntity(final Integer idEvidencia, final Date fechaEvidencia, final Integer borradoEvidencia,
			final String nombreFichero) {
		super();
		this.idEvidencia = idEvidencia;
		this.fechaEvidencia = fechaEvidencia;
		this.borradoEvidencia = borradoEvidencia;
		this.nombreFichero = nombreFichero;
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

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(final String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	@Override
	public String toString() {
		return "EvidenciaEntity [idEvidencia=" + idEvidencia + ", fechaEvidencia=" + fechaEvidencia
				+ ", borradoEvidencia=" + borradoEvidencia + ", nombreFichero=" + nombreFichero + "]";
	}

}
