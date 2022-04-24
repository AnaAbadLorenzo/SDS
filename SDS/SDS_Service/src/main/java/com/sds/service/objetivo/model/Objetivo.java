package com.sds.service.objetivo.model;

import com.sds.model.ObjetivoEntity;

public class Objetivo {

	private String usuario;
	private ObjetivoEntity objetivo;

	public Objetivo() {
		super();
	}

	public Objetivo(final String usuario, final ObjetivoEntity objetivo) {
		super();
		this.usuario = usuario;
		this.objetivo = objetivo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ObjetivoEntity getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(final ObjetivoEntity objetivo) {
		this.objetivo = objetivo;
	}

	@Override
	public String toString() {
		return "Objetivo [usuario=" + usuario + ", objetivo=" + objetivo + "]";
	}

}
