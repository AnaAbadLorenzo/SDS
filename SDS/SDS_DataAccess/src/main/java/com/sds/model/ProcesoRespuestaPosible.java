package com.sds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.sds.model.compositekey.ProcesoRespuestaPosibleKey;

@Entity
@IdClass(ProcesoRespuestaPosibleKey.class)
@Table(name = "procesorespuesta_posible")
public class ProcesoRespuestaPosible {

	@Id
	@Column(name = "id_proceso")
	private Integer idProceso;

	@Id
	@Column(name = "id_respuesta")
	private Integer idRespuesta;

	public ProcesoRespuestaPosible() {
		super();
	}

	public ProcesoRespuestaPosible(final Integer idProceso, final Integer idRespuesta) {
		super();
		this.idProceso = idProceso;
		this.idRespuesta = idRespuesta;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(final Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	@Override
	public String toString() {
		return "ProcesoRespuestaPosible [idProceso=" + idProceso + ", idRespuesta=" + idRespuesta + "]";
	}

}