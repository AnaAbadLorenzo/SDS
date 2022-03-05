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
@Table(name = "logacciones")
@NamedQueries({
		@NamedQuery(name = "LogAccionesEntity.findByUsuario", query = "SELECT l FROM LogAccionesEntity l WHERE l.usuario =: usuario"),
		@NamedQuery(name = "LogAccionesEntity.findByAccion", query = "SELECT l FROM LogAccionesEntity l WHERE l.accion =: accion"),
		@NamedQuery(name = "LogAccionesEntity.findByUsuarioAccion", query = "SELECT l FROM LogAccionesEntity l WHERE l.usuario =: usuario AND l.accion =: accion") })
public class LogAccionesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_logAcciones")
	private Integer idLogAcciones;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "accion")
	private String accion;

	@Column(name = "datos")
	private String datos;

	@Column(name = "fecha")
	private Date fecha;

	public LogAccionesEntity() {
		super();
	}

	public LogAccionesEntity(final Integer idLogAcciones, final String usuario, final String accion, final String datos,
			final Date fecha) {
		super();
		this.idLogAcciones = idLogAcciones;
		this.usuario = usuario;
		this.accion = accion;
		this.datos = datos;
		this.fecha = fecha;
	}

	public Integer getIdLogAcciones() {
		return idLogAcciones;
	}

	public void setIdLogAcciones(final Integer idLogAcciones) {
		this.idLogAcciones = idLogAcciones;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(final String accion) {
		this.accion = accion;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(final String datos) {
		this.datos = datos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "LogAccionesEntity [idLogAcciones=" + idLogAcciones + ", usuario=" + usuario + ", accion=" + accion
				+ ", datos=" + datos + ", fecha=" + fecha + "]";
	}

}
