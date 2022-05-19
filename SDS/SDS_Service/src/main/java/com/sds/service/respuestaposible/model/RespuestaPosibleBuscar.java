package com.sds.service.respuestaposible.model;

import java.util.Date;

public class RespuestaPosibleBuscar {

	private String textoRespuesta;
	private Date fechaRespuesta;
	private int inicio;
	private int tamanhoPagina;

	public RespuestaPosibleBuscar() {
		super();
	}

	public RespuestaPosibleBuscar(final String textoRespuesta, final int inicio, final int tamanhoPagina) {
		super();
		this.textoRespuesta = textoRespuesta;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(final String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
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
		return "RespuestaPosibleBuscar [textoRespuesta=" + textoRespuesta + ", fechaRespuesta=" + fechaRespuesta
				+ ", inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
