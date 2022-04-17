package com.sds.service.noticias.model;

import java.util.Date;

public class NoticiaBuscar {

	private String tituloNoticia;
	private String textoNoticia;
	private Date fechaNoticia;
	private int inicio;
	private int tamanhoPagina;

	public NoticiaBuscar() {
		super();
	}

	public NoticiaBuscar(final String tituloNoticia, final String textoNoticia, final Date fechaNoticia,
			final int inicio, final int tamanhoPagina) {
		super();
		this.tituloNoticia = tituloNoticia;
		this.textoNoticia = textoNoticia;
		this.fechaNoticia = fechaNoticia;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(final String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public String getTextoNoticia() {
		return textoNoticia;
	}

	public void setTextoNoticia(final String textoNoticia) {
		this.textoNoticia = textoNoticia;
	}

	public Date getFechaNoticia() {
		return fechaNoticia;
	}

	public void setFechaNoticia(final Date fechaNoticia) {
		this.fechaNoticia = fechaNoticia;
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
		return "NoticiaBuscar [tituloNoticia=" + tituloNoticia + ", textoNoticia=" + textoNoticia + ", fechaNoticia="
				+ fechaNoticia + ", inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
