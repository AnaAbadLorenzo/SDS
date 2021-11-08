package com.sds.controller.test.model;

import java.util.List;

import com.sds.service.test.model.DatosPruebaAtributos;

public class RespuestaTestAtributos {

	private String funcionalidad;
	private String accion;
	private List<DatosPruebaAtributos> datosPruebaAtributos;

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

	public List<DatosPruebaAtributos> getDatosPruebaAtributos() {
		return datosPruebaAtributos;
	}

	public void setDatosPruebaAtributos(final List<DatosPruebaAtributos> datosPruebaAtributos) {
		this.datosPruebaAtributos = datosPruebaAtributos;
	}

}
