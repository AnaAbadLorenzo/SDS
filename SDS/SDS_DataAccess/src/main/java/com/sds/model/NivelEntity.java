package com.sds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sds.model.compositekey.NivelKey;

@Entity
@IdClass(NivelKey.class)
@Table(name = "nivel")
@NamedQueries({
		@NamedQuery(name = "NivelEntity.findNivelByIdProceso", query = "SELECT n FROM NivelEntity n WHERE n.idProceso =: idProceso"),
		@NamedQuery(name = "NivelEntity.findNivelByIdObjetivo", query = "SELECT n FROM NivelEntity n WHERE n.idObjetivo =: idObjetivo "),
		@NamedQuery(name = "NivelEntity.findNivel", query = "SELECT n FROM NivelEntity n WHERE n.idObjetivo =: idObjetivo AND n.idProceso =: idProceso") })
public class NivelEntity {

	@Id
	@Column(name = "id_objetivo")
	private Integer idObjetivo;

	@Id
	@Column(name = "id_proceso")
	private Integer idProceso;

	@Column(name = "nivel")
	private Integer nivel;

	@Column(name = "fecha_nivel")
	private Date fechaNivel;

	public NivelEntity() {
		super();
	}

	public NivelEntity(final Integer idObjetivo, final Integer idProceso, final Integer nivel, final Date fechaNivel) {
		super();
		this.idObjetivo = idObjetivo;
		this.idProceso = idProceso;
		this.nivel = nivel;
		this.fechaNivel = fechaNivel;
	}

	public Integer getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(final Integer idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(final Integer nivel) {
		this.nivel = nivel;
	}

	public Date getFechaNivel() {
		return fechaNivel;
	}

	public void setFechaNivel(final Date fechaNivel) {
		this.fechaNivel = fechaNivel;
	}

	@Override
	public String toString() {
		return "NivelEntity [idObjetivo=" + idObjetivo + ", idProceso=" + idProceso + ", nivel=" + nivel
				+ ", fechaNivel=" + fechaNivel + "]";
	}

}
