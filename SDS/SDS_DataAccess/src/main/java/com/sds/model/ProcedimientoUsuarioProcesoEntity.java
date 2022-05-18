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

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_respuesta", referencedColumnName = "id_respuesta")
	private RespuestaPosibleEntity respuestaPosible;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
	private ProcesoEntity proceso;

	@OneToMany(mappedBy = "procedimientosUsuarioProceso")
	private final Set<EvidenciaEntity> evidencia = new HashSet<>();

	public ProcedimientoUsuarioProcesoEntity() {
		super();
	}

	public ProcedimientoUsuarioProcesoEntity(final Integer idProcedimientoUsuarioProceso,
			final Date fechaProcedimientoUsuarioProceso, final Integer borradoProcedimientoUsuarioProceso) {
		super();
		this.idProcedimientoUsuarioProceso = idProcedimientoUsuarioProceso;
		this.fechaProcedimientoUsuarioProceso = fechaProcedimientoUsuarioProceso;
		this.borradoProcedimientoUsuarioProceso = borradoProcedimientoUsuarioProceso;
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

	public RespuestaPosibleEntity getRespuestaPosible() {
		return respuestaPosible;
	}

	public void setRespuestaPosible(final RespuestaPosibleEntity respuestaPosible) {
		this.respuestaPosible = respuestaPosible;
	}

	public Set<EvidenciaEntity> getEvidencia() {
		return evidencia;
	}

	public ProcesoEntity getProceso() {
		return proceso;
	}

	public void setProceso(final ProcesoEntity proceso) {
		this.proceso = proceso;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuarioProcesoEntity [idProcedimientoUsuarioProceso=" + idProcedimientoUsuarioProceso
				+ ", fechaProcedimientoUsuarioProceso=" + fechaProcedimientoUsuarioProceso
				+ ", borradoProcedimientoUsuarioProceso=" + borradoProcedimientoUsuarioProceso + "]";
	}

}
