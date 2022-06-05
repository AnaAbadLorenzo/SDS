package com.sds.service.proceso.model;

import com.sds.model.ProcesoEntity;

public class Proceso {

	private String usuario;
	private ProcesoEntity proceso;

	public Proceso() {
		super();
	}

	public Proceso(final String usuario, final ProcesoEntity proceso) {
		super();
		this.usuario = usuario;
		this.proceso = proceso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ProcesoEntity getProceso() {
		return proceso;
	}

	public void setProceso(final ProcesoEntity proceso) {
		this.proceso = proceso;
	}

	@Override
	public String toString() {
		return "Proceso [usuario=" + usuario + ", proceso=" + proceso + "]";
	}

}
