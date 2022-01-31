package com.sds.model.compositekey;

import java.io.Serializable;

public class RolAccionFuncionalidadKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idRol;
	private Integer idAccion;
	private Integer idFuncionalidad;

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(final Integer idRol) {
		this.idRol = idRol;
	}

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(final Integer idAccion) {
		this.idAccion = idAccion;
	}

	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(final Integer idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

}
