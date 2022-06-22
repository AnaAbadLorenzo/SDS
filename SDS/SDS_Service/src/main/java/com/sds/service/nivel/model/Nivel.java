package com.sds.service.nivel.model;

import com.sds.model.NivelEntity;

public class Nivel {

	private String usuario;
	private NivelEntity nivel;

	public Nivel() {
		super();
	}

	public Nivel(final String usuario, final NivelEntity nivel) {
		super();
		this.usuario = usuario;
		this.nivel = nivel;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public NivelEntity getNivel() {
		return nivel;
	}

	public void setNivel(final NivelEntity nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "Nivel [usuario=" + usuario + ", nivel=" + nivel + "]";
	}

}
