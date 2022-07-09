package com.sds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "evidencia")
@NamedQueries({
		@NamedQuery(name = "EvidenciaEntity.findEvidenciaByIdProcedimientoUsuarioProceso", query = "SELECT e FROM EvidenciaEntity e WHERE e.procedimientosUsuarioProceso.idProcedimientoUsuarioProceso =: idProcedimientoUsuarioProceso") })
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

	@Column(name = "ruta_evidencia")
	private String rutaEvidencia;

	@OneToOne
	@JoinColumn(name = "id_procedimiento_usuario_proceso", referencedColumnName = "id_procedimiento_usuario_proceso", updatable = false)
	private ProcedimientoUsuarioProcesoEntity procedimientosUsuarioProceso;

	public EvidenciaEntity() {
		super();
	}

	public EvidenciaEntity(final Integer idEvidencia, final Date fechaEvidencia, final Integer borradoEvidencia,
			final String nombreFichero, final String rutaEvidencia) {
		super();
		this.idEvidencia = idEvidencia;
		this.fechaEvidencia = fechaEvidencia;
		this.borradoEvidencia = borradoEvidencia;
		this.nombreFichero = nombreFichero;
		this.rutaEvidencia = rutaEvidencia;
	}

	public EvidenciaEntity(final Date fechaEvidencia, final Integer borradoEvidencia, final String nombreFichero,
			final String rutaEvidencia) {
		super();
		this.fechaEvidencia = fechaEvidencia;
		this.borradoEvidencia = borradoEvidencia;
		this.nombreFichero = nombreFichero;
		this.rutaEvidencia = rutaEvidencia;
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

	public String getRutaEvidencia() {
		return rutaEvidencia;
	}

	public void setRutaEvidencia(final String rutaEvidencia) {
		this.rutaEvidencia = rutaEvidencia;
	}

	@Override
	public String toString() {
		return "EvidenciaEntity [idEvidencia=" + idEvidencia + ", fechaEvidencia=" + fechaEvidencia
				+ ", borradoEvidencia=" + borradoEvidencia + ", nombreFichero=" + nombreFichero + ", rutaEvidencia="
				+ rutaEvidencia + ", procedimientosUsuarioProceso=" + procedimientosUsuarioProceso + "]";
	}

}
