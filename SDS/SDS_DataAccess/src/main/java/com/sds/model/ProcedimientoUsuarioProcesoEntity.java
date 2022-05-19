package com.sds.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sds.model.compositekey.ProcedimientoUsuarioProcesoKey;

@Entity
@IdClass(ProcedimientoUsuarioProcesoKey.class)
@Table(name = "procedimientousuarioproceso")
public class ProcedimientoUsuarioProcesoEntity {

	@Id
	private Integer idProceso;

	@Id
	private Integer idProcedimientoUsuario;

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

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_procedimiento_usuario", referencedColumnName = "id_procedimiento_usuario")
	private ProcedimientoUsuarioEntity procedimientoUsuario;

	@OneToOne(mappedBy = "procedimientosUsuarioProceso", cascade = CascadeType.ALL)
	private EvidenciaEntity evidencia;

	public ProcedimientoUsuarioProcesoEntity() {
		super();
	}

	public ProcedimientoUsuarioProcesoEntity(final Integer idProceso, final Integer idProcedimientoUsuario,
			final Date fechaProcedimientoUsuarioProceso, final Integer borradoProcedimientoUsuarioProceso) {
		super();
		this.idProceso = idProceso;
		this.idProcedimientoUsuario = idProcedimientoUsuario;
		this.fechaProcedimientoUsuarioProceso = fechaProcedimientoUsuarioProceso;
		this.borradoProcedimientoUsuarioProceso = borradoProcedimientoUsuarioProceso;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getIdProcedimientoUsuario() {
		return idProcedimientoUsuario;
	}

	public void setIdProcedimientoUsuario(final Integer idProcedimientoUsuario) {
		this.idProcedimientoUsuario = idProcedimientoUsuario;
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
		return "ProcedimientoUsuarioProcesoEntity [idProceso=" + idProceso + ", idProcedimientoUsuario="
				+ idProcedimientoUsuario + ", fechaProcedimientoUsuarioProceso=" + fechaProcedimientoUsuarioProceso
				+ ", borradoProcedimientoUsuarioProceso=" + borradoProcedimientoUsuarioProceso + "]";
	}

}
