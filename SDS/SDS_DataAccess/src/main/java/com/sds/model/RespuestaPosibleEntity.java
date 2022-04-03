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
@Table(name = "respuesta_posible")
public class RespuestaPosibleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_respuesta")
	private Integer idRespuesta;

	@Column(name = "texto_respuesta")
	private String textoRespuesta;

	@Column(name = "fecha_respuesta")
	private Date fechaRespuesta;

	@Column(name = "borrado_respuesta")
	private Integer borradoRespuesta;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_procedimiento_usuario_proceso", referencedColumnName = "id_procedimiento_usuario_proceso")
	private ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso;

	public RespuestaPosibleEntity() {
		super();
	}

	public RespuestaPosibleEntity(final Integer idRespuesta, final String textoRespuesta, final Date fechaRespuesta,
			final Integer borradoRespuesta, final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso) {
		super();
		this.idRespuesta = idRespuesta;
		this.textoRespuesta = textoRespuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.borradoRespuesta = borradoRespuesta;
		this.procedimientoUsuarioProceso = procedimientoUsuarioProceso;
	}

	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(final Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(final String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(final Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public Integer getBorradoRespuesta() {
		return borradoRespuesta;
	}

	public void setBorradoRespuesta(final Integer borradoRespuesta) {
		this.borradoRespuesta = borradoRespuesta;
	}

	public ProcedimientoUsuarioProcesoEntity getProcedimientoUsuarioProceso() {
		return procedimientoUsuarioProceso;
	}

	public void setProcedimientoUsuarioProceso(final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso) {
		this.procedimientoUsuarioProceso = procedimientoUsuarioProceso;
	}

	@Override
	public String toString() {
		return "RespuestaPosibleEntity [idRespuesta=" + idRespuesta + ", textoRespuesta=" + textoRespuesta
				+ ", fechaRespuesta=" + fechaRespuesta + ", borradoRespuesta=" + borradoRespuesta
				+ ", procedimientoUsuarioProceso=" + procedimientoUsuarioProceso + "]";
	}

}
