package com.sds.service.proceso.model;

public class Objetivo {

	private Integer idObjetivo;
	private String nombreObjetivo;

	public Integer getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(final Integer idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public String getNombreObjetivo() {
		return nombreObjetivo;
	}

	public void setNombreObjetivo(final String nombreObjetivo) {
		this.nombreObjetivo = nombreObjetivo;
	}

	public Objetivo(final Integer idObjetivo, final String nombreObjetivo) {
		super();
		this.idObjetivo = idObjetivo;
		this.nombreObjetivo = nombreObjetivo;
	}

	public Objetivo() {
		super();
	}

	@Override
	public String toString() {
		return "Objetivo [idObjetivo=" + idObjetivo + ", nombreObjetivo=" + nombreObjetivo + "]";
	}

}
