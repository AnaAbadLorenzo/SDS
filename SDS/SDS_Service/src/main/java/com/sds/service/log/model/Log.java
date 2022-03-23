package com.sds.service.log.model;

import java.util.Date;

public class Log {

	private String usuario;
	private Date fechaInicio;
	private Date fechaFin;
	private int inicio;
	private int tamanhoPagina;

	public Log() {

	}

	public Log(final String usuario, final Date fechaInicio, final Date fechaFin, final int inicio,
			final int tamanhoPagina) {
		super();
		this.usuario = usuario;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(final Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(final int inicio) {
		this.inicio = inicio;
	}

	public int getTamanhoPagina() {
		return tamanhoPagina;
	}

	public void setTamanhoPagina(final int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	@Override
	public String toString() {
		return "Log [usuario=" + usuario + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", inicio="
				+ inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
