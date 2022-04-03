package com.sds.model.compositekey;

import java.io.Serializable;

public class NivelKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idObjetivo;
	private Integer idProceso;

	public NivelKey() {

	}

	public NivelKey(final Integer idObjetivo, final Integer idProceso) {
		super();
		this.idObjetivo = idObjetivo;
		this.idProceso = idProceso;
	}

	public Integer getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(final Integer idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	@Override
	public String toString() {
		return "NivelKey [idObjetivo=" + idObjetivo + ", idProceso=" + idProceso + "]";
	}

}
