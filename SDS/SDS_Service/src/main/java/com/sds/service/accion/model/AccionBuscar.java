package com.sds.service.accion.model;

public class AccionBuscar {

	public String nombreAccion;
	public String descripAccion;
	public int inicio;
	public int tamanhoPagina;

	public AccionBuscar() {

	}

	public AccionBuscar(final String nombreAccion, final String descripAccion, final int inicio,
			final int tamanhoPagina) {
		super();
		this.nombreAccion = nombreAccion;
		this.descripAccion = descripAccion;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public AccionBuscar(final String nombreAccion, final String descripAccion) {
		super();
		this.nombreAccion = nombreAccion;
		this.descripAccion = descripAccion;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(final String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getDescripAccion() {
		return descripAccion;
	}

	public void setDescripAccion(final String descripAccion) {
		this.descripAccion = descripAccion;
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
		return "AccionBuscar [nombreAccion=" + nombreAccion + ", descripAccion=" + descripAccion + ", inicio=" + inicio
				+ ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
