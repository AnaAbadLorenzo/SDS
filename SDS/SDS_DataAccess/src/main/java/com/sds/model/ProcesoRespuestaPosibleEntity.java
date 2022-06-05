package com.sds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sds.model.compositekey.ProcesoRespuestaPosibleKey;

@Entity
@IdClass(ProcesoRespuestaPosibleKey.class)
@Table(name = "procesorespuesta_posible")
@NamedQueries({
		@NamedQuery(name = "ProcesoRespuestaPosibleEntity.findProcesoByIdRespuestaPosible", query = "SELECT p FROM ProcesoRespuestaPosibleEntity p WHERE p.idRespuesta =: idRespuesta"),
		@NamedQuery(name = "ProcesoRespuestaPosibleEntity.findRespuestaPosibleByIdProceso", query = "SELECT p FROM ProcesoRespuestaPosibleEntity p WHERE p.idProceso =: idProceso") })
public class ProcesoRespuestaPosibleEntity {

	@Id
	@Column(name = "id_proceso")
	private Integer idProceso;

	@Id
	@Column(name = "id_respuesta")
	private Integer idRespuesta;

	@Column(name = "fecha_respuesta")
	private Date fechaRespuesta;

	public ProcesoRespuestaPosibleEntity() {
		super();
	}

	public ProcesoRespuestaPosibleEntity(final Integer idProceso, final Integer idRespuesta,
			final Date fechaRespuesta) {
		super();
		this.idProceso = idProceso;
		this.idRespuesta = idRespuesta;
		this.fechaRespuesta = fechaRespuesta;
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

	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(final Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	@Override
	public String toString() {
		return "ProcesoRespuestaPosibleEntity [idProceso=" + idProceso + ", idRespuesta=" + idRespuesta
				+ ", fechaRespuesta=" + fechaRespuesta + "]";
	}

}
