
package com.sds.service.common;

public class Paginacion {

	private int inicio;
	private int tamanhoPagina;

	public Paginacion() {

	}

	public Paginacion(final int inicio, final int tamanhoPagina) {
		super();
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
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
		return "Paginacion [inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
