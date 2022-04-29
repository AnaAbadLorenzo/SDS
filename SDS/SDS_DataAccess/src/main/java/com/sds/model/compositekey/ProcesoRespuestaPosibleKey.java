package com.sds.model.compositekey;

import java.io.Serializable;
import java.util.Objects;

public class ProcesoRespuestaPosibleKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idProceso;
	private Integer idRespuesta;

	public ProcesoRespuestaPosibleKey() {
		super();
	}

	public ProcesoRespuestaPosibleKey(final Integer idProceso, final Integer idRespuesta) {
		super();
		this.idProceso = idProceso;
		this.idRespuesta = idRespuesta;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(final Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	@Override
	public String toString() {
		return "ProcesoRespuestaPosibleKey [idProceso=" + idProceso + ", idRespuesta=" + idRespuesta + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProceso, idRespuesta);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProcesoRespuestaPosibleKey other = (ProcesoRespuestaPosibleKey) obj;
		return Objects.equals(idProceso, other.idProceso) && Objects.equals(idRespuesta, other.idRespuesta);
	}

}
