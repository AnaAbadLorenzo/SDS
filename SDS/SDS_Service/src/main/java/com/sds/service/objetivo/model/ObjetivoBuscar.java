package com.sds.service.objetivo.model;

public class ObjetivoBuscar {

	private String usuario;
	private String nombreObjetivo;
	private String descripObjetivo;
	private int inicio;
	private int tamanhoPagina;

	public ObjetivoBuscar() {
		super();
	}

	public ObjetivoBuscar(final String usuario, final String nombreObjetivo, final String descripObjetivo,
			final int inicio, final int tamanhoPagina) {
		super();
		this.usuario = usuario;
		this.nombreObjetivo = nombreObjetivo;
		this.descripObjetivo = descripObjetivo;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getNombreObjetivo() {
		return nombreObjetivo;
	}

	public void setNombreObjetivo(final String nombreObjetivo) {
		this.nombreObjetivo = nombreObjetivo;
	}

	public String getDescripObjetivo() {
		return descripObjetivo;
	}

	public void setDescripObjetivo(final String descripObjetivo) {
		this.descripObjetivo = descripObjetivo;
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
		return "ObjetivoBuscar [usuario=" + usuario + ", nombreObjetivo=" + nombreObjetivo + ", descripObjetivo="
				+ descripObjetivo + "]";
	}

}
