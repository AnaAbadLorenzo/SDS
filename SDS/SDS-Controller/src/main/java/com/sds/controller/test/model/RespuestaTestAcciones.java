package com.sds.controller.test.model;

import java.util.List;

import com.sds.service.test.model.DatosPruebaAcciones;

public class RespuestaTestAcciones {

	private String funcionalidad;
	private String accion;
	private List<DatosPruebaAcciones> datosPruebaAcciones;

	public String getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(final String funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(final String accion) {
		this.accion = accion;
	}

	public List<DatosPruebaAcciones> getDatosPruebaAcciones() {
		return datosPruebaAcciones;
	}

	public void setDatosPruebaAcciones(final List<DatosPruebaAcciones> datosPruebaAcciones) {
		this.datosPruebaAcciones = datosPruebaAcciones;
	}

}
