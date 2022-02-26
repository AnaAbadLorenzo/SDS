package com.sds.service.funcionalidad.model;

public class FuncionalidadBuscar {

	private String nombreFuncionalidad;
	private String descripFuncionalidad;
	private int inicio;
	private int tamanhoPagina;

	public FuncionalidadBuscar() {

	}

	public FuncionalidadBuscar(final String nombreFuncionalidad, final String descripFuncionalidad) {
		super();
		this.nombreFuncionalidad = nombreFuncionalidad;
		this.descripFuncionalidad = descripFuncionalidad;
	}

	public FuncionalidadBuscar(final String nombreFuncionalidad, final String descripFuncionalidad, final int inicio,
			final int tamanhoPagina) {
		super();
		this.nombreFuncionalidad = nombreFuncionalidad;
		this.descripFuncionalidad = descripFuncionalidad;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getNombreFuncionalidad() {
		return nombreFuncionalidad;
	}

	public void setNombreFuncionalidad(final String nombreFuncionalidad) {
		this.nombreFuncionalidad = nombreFuncionalidad;
	}

	public String getDescripFuncionalidad() {
		return descripFuncionalidad;
	}

	public void setDescripFuncionalidad(final String descripFuncionalidad) {
		this.descripFuncionalidad = descripFuncionalidad;
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
		return "FuncionalidadBuscar [nombreFuncionalidad=" + nombreFuncionalidad + ", descripFuncionalidad="
				+ descripFuncionalidad + ", inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
