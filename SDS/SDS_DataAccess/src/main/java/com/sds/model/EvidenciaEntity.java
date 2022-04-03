package com.sds.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "evidencia")
	private final Set<ProcedimientoUsuarioProcesoEntity> procedimientosUsuarioProcesos = new HashSet<>();

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

	public Set<ProcedimientoUsuarioProcesoEntity> getProcedimientosUsuarioProcesos() {
		return procedimientosUsuarioProcesos;
	}

	@Override
	public String toString() {
		return "EvidenciaEntity [idEvidencia=" + idEvidencia + ", fechaEvidencia=" + fechaEvidencia
				+ ", borradoEvidencia=" + borradoEvidencia + ", procedimientosUsuarioProcesos="
				+ procedimientosUsuarioProcesos + "]";
	}

}
