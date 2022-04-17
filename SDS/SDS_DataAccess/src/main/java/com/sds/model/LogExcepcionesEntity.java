package com.sds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "logexcepciones")
@NamedQueries({
		@NamedQuery(name = "LogExcepcionesEntity.findByUsuario", query = "SELECT l FROM LogExcepcionesEntity l WHERE l.usuario =: usuario"),
		@NamedQuery(name = "LogExcepcionesEntity.findLogExcepciones", query = "SELECT l FROM LogExcepcionesEntity l WHERE l.usuario LIKE CONCAT('%', :usuario, '%') AND ((:fechaInicio IS NOT NULL AND :fechaFin IS NOT NULL AND l.fecha BETWEEN :fechaInicio AND :fechaFin) OR (:fechaInicio IS NOT NULL AND :fechaFin IS NULL AND l.fecha >= :fechaInicio) OR (:fechaInicio IS NULL AND :fechaFin IS NOT NULL AND l.fecha <= :fechaFin) OR (:fechaInicio IS NULL AND :fechaFin IS NULL))"),
		@NamedQuery(name = "LogExcepcionesEntity.numberFindLogExcepciones", query = "SELECT COUNT(l) FROM LogExcepcionesEntity l WHERE l.usuario LIKE CONCAT('%', :usuario, '%') AND ((:fechaInicio IS NOT NULL AND :fechaFin IS NOT NULL AND l.fecha BETWEEN :fechaInicio AND :fechaFin) OR (:fechaInicio IS NOT NULL AND :fechaFin IS NULL AND l.fecha >= :fechaInicio) OR (:fechaInicio IS NULL AND :fechaFin IS NOT NULL AND l.fecha <= :fechaFin) OR (:fechaInicio IS NULL AND :fechaFin IS NULL))"),
		@NamedQuery(name = "LogExcepcionesEntity.findAllLogExcepciones", query = "SELECT l FROM LogExcepcionesEntity l"),
		@NamedQuery(name = "LogExcepcionesEntity.numberFindAllLogExcepciones", query = "SELECT COUNT(l) FROM LogExcepcionesEntity l") })
public class LogExcepcionesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_logExcepciones")
	private Integer idLogExcepciones;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "tipo_excepcion")
	private String tipoExcepcion;

	@Column(name = "descripcion_excepcion")
	private String descripcionExcepcion;

	@Column(name = "fecha")
	private Date fecha;

	public LogExcepcionesEntity() {
		super();
	}

	public LogExcepcionesEntity(final Integer idLogExcepciones, final String usuario, final String tipoExcepcion,
			final String descripcionExcepcion, final Date fecha) {
		super();
		this.idLogExcepciones = idLogExcepciones;
		this.usuario = usuario;
		this.tipoExcepcion = tipoExcepcion;
		this.descripcionExcepcion = descripcionExcepcion;
		this.fecha = fecha;
	}

	public Integer getIdLogExcepciones() {
		return idLogExcepciones;
	}

	public void setIdLogExcepciones(final Integer idLogExcepciones) {
		this.idLogExcepciones = idLogExcepciones;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getTipoExcepcion() {
		return tipoExcepcion;
	}

	public void setTipoExcepcion(final String tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
	}

	public String getDescripcionExcepcion() {
		return descripcionExcepcion;
	}

	public void setDescripcionExcepcion(final String descripcionExcepcion) {
		this.descripcionExcepcion = descripcionExcepcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "LogExcepcionesEntity [idLogExcepciones=" + idLogExcepciones + ", usuario=" + usuario
				+ ", tipoExcepcion=" + tipoExcepcion + ", descripcionExcepcion=" + descripcionExcepcion + ", fecha="
				+ fecha + "]";
	}

}
