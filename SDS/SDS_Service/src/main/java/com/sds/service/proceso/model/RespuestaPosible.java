package com.sds.service.proceso.model;

public class RespuestaPosible {

	private Integer idRespuesta;
	private String textoRespuesta;

	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(final Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(final String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	public RespuestaPosible(final Integer idRespuesta, final String textoRespuesta) {
		super();
		this.idRespuesta = idRespuesta;
		this.textoRespuesta = textoRespuesta;
	}

	public RespuestaPosible() {
		super();
	}

	@Override
	public String toString() {
		return "RespuestaPosible [idRespuesta=" + idRespuesta + ", textoRespuesta=" + textoRespuesta + "]";
	}

}
