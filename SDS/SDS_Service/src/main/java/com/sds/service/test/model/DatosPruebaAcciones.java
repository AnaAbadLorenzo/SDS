package com.sds.service.test.model;

import java.util.Map;

public class DatosPruebaAcciones {

	private String prueba;
	private Map<String, String> valor;
	private String resultadoEsperado;
	private String resultadoObtenido;
	private String tipoPrueba;

	public String getPrueba() {
		return prueba;
	}

	public void setPrueba(final String prueba) {
		this.prueba = prueba;
	}

	public Map<String, String> getValor() {
		return valor;
	}

	public void setValor(final Map<String, String> valor) {
		this.valor = valor;
	}

	public String getResultadoEsperado() {
		return resultadoEsperado;
	}

	public void setResultadoEsperado(final String resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}

	public String getResultadoObtenido() {
		return resultadoObtenido;
	}

	public void setResultadoObtenido(final String resultadoObtenido) {
		this.resultadoObtenido = resultadoObtenido;
	}

	public String getTipoPrueba() {
		return tipoPrueba;
	}

	public void setTipoPrueba(final String tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}

}
