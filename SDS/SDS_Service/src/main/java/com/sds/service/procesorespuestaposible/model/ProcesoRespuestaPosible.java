package com.sds.service.procesorespuestaposible.model;

import com.sds.model.ProcesoRespuestaPosibleEntity;

public class ProcesoRespuestaPosible {

	private String usuario;
	private ProcesoRespuestaPosibleEntity procesoRespuestaPosibleEntity;

	public ProcesoRespuestaPosible() {
		super();
	}

	public ProcesoRespuestaPosible(final String usuario,
			final ProcesoRespuestaPosibleEntity procesoRespuestaPosibleEntity) {
		super();
		this.usuario = usuario;
		this.procesoRespuestaPosibleEntity = procesoRespuestaPosibleEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ProcesoRespuestaPosibleEntity getProcesoRespuestaPosibleEntity() {
		return procesoRespuestaPosibleEntity;
	}

	public void setProcesoRespuestaPosibleEntity(final ProcesoRespuestaPosibleEntity procesoRespuestaPosibleEntity) {
		this.procesoRespuestaPosibleEntity = procesoRespuestaPosibleEntity;
	}

	@Override
	public String toString() {
		return "ProcesoRespuestaPosible [usuario=" + usuario + ", procesoRespuestaPosibleEntity="
				+ procesoRespuestaPosibleEntity + "]";
	}

}
