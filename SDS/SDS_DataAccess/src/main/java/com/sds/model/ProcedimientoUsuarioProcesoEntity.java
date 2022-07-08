package com.sds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "procedimientousuarioproceso")
@NamedQueries({
		@NamedQuery(name = "ProcedimientoUsuarioProcesoEntity.findProcedimientoUsuarioProcesoByIdProceso", query = "SELECT p FROM ProcedimientoUsuarioProcesoEntity p WHERE p.proceso LIKE CONCAT ('%', :proceso, '%')"),
		@NamedQuery(name = "ProcedimientoUsuarioProcesoEntity.findProcedimientoUsuarioProcesoByIdProcedimientoUsuario", query = "SELECT p FROM ProcedimientoUsuarioProcesoEntity p WHERE p.procedimientoUsuario.idProcedimientoUsuario =: idProcedimientoUsuario"),
		@NamedQuery(name = "ProcedimientoUsuarioProcesoEntity.findProcedimientoUsuarioProcesoByIdProcedimientoUsuarioAndIdProceso", query = "SELECT p FROM ProcedimientoUsuarioProcesoEntity p WHERE p.procedimientoUsuario.idProcedimientoUsuario =: idProcedimientoUsuario AND p.proceso.idProceso =: idProceso"),
		@NamedQuery(name = "ProcedimientoUsuarioProcesoEntity.findProcedimientoUsuarioProceso", query = "SELECT p FROM ProcedimientoUsuarioProcesoEntity p WHERE p.procedimientoUsuario LIKE CONCAT ('%', :procedimientoUsuario, '%') AND p.proceso LIKE CONCAT ('%', :proceso, '%')"),
		@NamedQuery(name = "ProcedimientoUsuarioProcesoEntity.findAllProcedimientosUsuariosProcesos", query = "SELECT p FROM ProcedimientoUsuarioProcesoEntity p WHERE p.borradoProcedimientoUsuarioProceso = 0"),
		@NamedQuery(name = "ProcedimientoUsuarioProcesoEntity.numberFindAllProcedimientosUsuariosProcesos", query = "SELECT COUNT(p) FROM ProcedimientoUsuarioProcesoEntity p WHERE p.borradoProcedimientoUsuarioProceso = 0"),
		@NamedQuery(name = "ProcedimientoUsuarioProcesoEntity.findProcesosOfProcedimientoUsuario", query = "SELECT p FROM ProcedimientoUsuarioProcesoEntity p WHERE procedimientoUsuario.idProcedimientoUsuario =: idProcedimientoUsuario") })
public class ProcedimientoUsuarioProcesoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_procedimiento_usuario_proceso")
	private Integer idProcedimientoUsuarioProceso;

	@Column(name = "fecha_procedimiento_usuario_proceso")
	private Date fechaProcedimientoUsuarioProceso;

	@Column(name = "borrado_procedimiento_usuario_proceso")
	private Integer borradoProcedimientoUsuarioProceso;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_respuesta", referencedColumnName = "id_respuesta")
	private RespuestaPosibleEntity respuestaPosible;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
	private ProcesoEntity proceso;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_procedimiento_usuario", referencedColumnName = "id_procedimiento_usuario")
	private ProcedimientoUsuarioEntity procedimientoUsuario;

	@OneToOne(mappedBy = "procedimientosUsuarioProceso")
	private EvidenciaEntity evidencia;

	public ProcedimientoUsuarioProcesoEntity() {
		super();
	}

	public ProcedimientoUsuarioProcesoEntity(final Integer idProcedimientoUsuarioProceso,
			final Date fechaProcedimientoUsuarioProceso, final Integer borradoProcedimientoUsuarioProceso,
			final RespuestaPosibleEntity respuestaPosible, final ProcesoEntity proceso,
			final ProcedimientoUsuarioEntity procedimientoUsuario) {
		super();
		this.idProcedimientoUsuarioProceso = idProcedimientoUsuarioProceso;
		this.fechaProcedimientoUsuarioProceso = fechaProcedimientoUsuarioProceso;
		this.borradoProcedimientoUsuarioProceso = borradoProcedimientoUsuarioProceso;
		this.respuestaPosible = respuestaPosible;
		this.proceso = proceso;
		this.procedimientoUsuario = procedimientoUsuario;
	}

	public ProcedimientoUsuarioProcesoEntity(final Date fechaProcedimientoUsuarioProceso,
			final Integer borradoProcedimientoUsuarioProceso, final RespuestaPosibleEntity respuestaPosible,
			final ProcesoEntity proceso, final ProcedimientoUsuarioEntity procedimientoUsuario) {
		super();
		this.fechaProcedimientoUsuarioProceso = fechaProcedimientoUsuarioProceso;
		this.borradoProcedimientoUsuarioProceso = borradoProcedimientoUsuarioProceso;
		this.respuestaPosible = respuestaPosible;
		this.proceso = proceso;
		this.procedimientoUsuario = procedimientoUsuario;
	}

	public Integer getIdProcedimientoUsuarioProceso() {
		return idProcedimientoUsuarioProceso;
	}

	public void setIdProcedimientoUsuarioProceso(final Integer idProcedimientoUsuarioProceso) {
		this.idProcedimientoUsuarioProceso = idProcedimientoUsuarioProceso;
	}

	public void setEvidencia(final EvidenciaEntity evidencia) {
		this.evidencia = evidencia;
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

	public ProcesoEntity getProceso() {
		return proceso;
	}

	public void setProceso(final ProcesoEntity proceso) {
		this.proceso = proceso;
	}

	public ProcedimientoUsuarioEntity getProcedimientoUsuario() {
		return procedimientoUsuario;
	}

	public void setProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuario) {
		this.procedimientoUsuario = procedimientoUsuario;
	}

	public EvidenciaEntity getEvidencia() {
		return evidencia;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuarioProcesoEntity [idProcedimientoUsuarioProceso=" + idProcedimientoUsuarioProceso
				+ ", fechaProcedimientoUsuarioProceso=" + fechaProcedimientoUsuarioProceso
				+ ", borradoProcedimientoUsuarioProceso=" + borradoProcedimientoUsuarioProceso + ", respuestaPosible="
				+ respuestaPosible + ", proceso=" + proceso + ", procedimientoUsuario=" + procedimientoUsuario
				+ ", evidencia=" + evidencia + "]";
	}

}
