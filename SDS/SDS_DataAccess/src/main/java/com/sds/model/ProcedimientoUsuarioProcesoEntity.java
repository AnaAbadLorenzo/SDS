package com.sds.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "procedimientousuarioproceso")
public class ProcedimientoUsuarioProcesoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_procedimiento_usuario_proceso")
	private Integer idProcedimientoUsuarioProceso;

	@Column(name = "fecha_procedimiento_usuario_proceso")
	private Date fechaProcedimientoUsuarioProceso;

	@Column(name = "borrado_procedimiento_usuario_proceso")
	private Integer borradoProcedimientoUsuarioProceso;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimientoUsuarioProceso")
	private final Set<RespuestaPosibleEntity> respuestas = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimientoUsuarioProc")
	private final Set<ProcesoEntity> procesos = new HashSet<>();

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_evidencia", referencedColumnName = "id_evidencia")
	private EvidenciaEntity evidencia;

	public ProcedimientoUsuarioProcesoEntity() {
		super();
	}

	public ProcedimientoUsuarioProcesoEntity(final Integer idProcedimientoUsuarioProceso,
			final Date fechaProcedimientoUsuarioProceso, final Integer borradoProcedimientoUsuarioProceso,
			final EvidenciaEntity evidencia) {
		super();
		this.idProcedimientoUsuarioProceso = idProcedimientoUsuarioProceso;
		this.fechaProcedimientoUsuarioProceso = fechaProcedimientoUsuarioProceso;
		this.borradoProcedimientoUsuarioProceso = borradoProcedimientoUsuarioProceso;
		this.evidencia = evidencia;
	}

	public Integer getIdProcedimientoUsuarioProceso() {
		return idProcedimientoUsuarioProceso;
	}

	public void setIdProcedimientoUsuarioProceso(final Integer idProcedimientoUsuarioProceso) {
		this.idProcedimientoUsuarioProceso = idProcedimientoUsuarioProceso;
	}

	public Date getFechaProcedimientoUsuarioProceso() {
		return fechaProcedimientoUsuarioProceso;
	}

	public void setFechaProcedimientoUsuarioProceso(final Date fechaProcedimientoUsuarioProceso) {
		this.fechaProcedimientoUsuarioProceso = fechaProcedimientoUsuarioProceso;
	}

	public Integer getBorradoProcedimientoUsuarioProceso() {
		return borradoProcedimientoUsuarioProceso;
	}

	public void setBorradoProcedimientoUsuarioProceso(final Integer borradoProcedimientoUsuarioProceso) {
		this.borradoProcedimientoUsuarioProceso = borradoProcedimientoUsuarioProceso;
	}

	public Set<RespuestaPosibleEntity> getRespuestas() {
		return respuestas;
	}

	public Set<ProcesoEntity> getProcesos() {
		return procesos;
	}

	public EvidenciaEntity getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(final EvidenciaEntity evidencia) {
		this.evidencia = evidencia;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuarioProcesoEntity [idProcedimientoUsuarioProceso=" + idProcedimientoUsuarioProceso
				+ ", fechaProcedimientoUsuarioProceso=" + fechaProcedimientoUsuarioProceso
				+ ", borradoProcedimientoUsuarioProceso=" + borradoProcedimientoUsuarioProceso + ", respuestas="
				+ respuestas + ", procesos=" + procesos + ", evidencia=" + evidencia + "]";
	}

}
