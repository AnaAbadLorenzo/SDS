package com.sds.service.respuestaposible.model;

import com.sds.model.RespuestaPosibleEntity;

public class RespuestaPosible {

	private String usuario;
	private RespuestaPosibleEntity respuestaPosibleEntity;

	public RespuestaPosible() {
		super();
	}

	public RespuestaPosible(final String usuario, final RespuestaPosibleEntity respuestaPosibleEntity) {
		super();
		this.usuario = usuario;
		this.respuestaPosibleEntity = respuestaPosibleEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public RespuestaPosibleEntity getRespuestaPosibleEntity() {
		return respuestaPosibleEntity;
	}

	public void setRespuestaPosibleEntity(final RespuestaPosibleEntity respuestaPosibleEntity) {
		this.respuestaPosibleEntity = respuestaPosibleEntity;
	}

	@Override
	public String toString() {
		return "RespuestaPosible [usuario=" + usuario + ", respuestaPosibleEntity=" + respuestaPosibleEntity + "]";
	}
}
