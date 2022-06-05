package com.sds.service.proceso.model;

import java.util.Date;

public class ProcesoBuscar {

	private String nombreProceso;
	private String descripProceso;
	private Date fechaProceso;
	private int inicio;
	private int tamanhoPagina;

	public ProcesoBuscar() {
		super();
	}

	public ProcesoBuscar(final String nombreProceso, final String descripProceso, final Date fechaProceso,
			final int inicio, final int tamanhoPagina) {
		super();
		this.nombreProceso = nombreProceso;
		this.descripProceso = descripProceso;
		this.fechaProceso = fechaProceso;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(final String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getDescripProceso() {
		return descripProceso;
	}

	public void setDescripProceso(final String descripProceso) {
		this.descripProceso = descripProceso;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(final Date fechaProceso) {
		this.fechaProceso = fechaProceso;
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
		return "ProcesoBuscar [nombreProceso=" + nombreProceso + ", descripProceso=" + descripProceso
				+ ", fechaProceso=" + fechaProceso + ", inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
