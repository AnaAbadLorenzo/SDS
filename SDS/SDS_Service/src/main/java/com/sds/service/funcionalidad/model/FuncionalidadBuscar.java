package com.sds.service.funcionalidad.model;

public class FuncionalidadBuscar {

	private String nombreFuncionalidad;
	private String descripFuncionalidad;

	public FuncionalidadBuscar() {

	}

	public FuncionalidadBuscar(final String nombreFuncionalidad, final String descripFuncionalidad) {
		super();
		this.nombreFuncionalidad = nombreFuncionalidad;
		this.descripFuncionalidad = descripFuncionalidad;
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

}
