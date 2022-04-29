package com.sds.model.compositekey;

import java.io.Serializable;
import java.util.Objects;

public class RolAccionFuncionalidadKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idRol;
	private Integer idAccion;
	private Integer idFuncionalidad;

	public RolAccionFuncionalidadKey() {

	}

	public RolAccionFuncionalidadKey(final Integer idRol, final Integer idAccion, final Integer idFuncionalidad) {
		super();
		this.idRol = idRol;
		this.idAccion = idAccion;
		this.idFuncionalidad = idFuncionalidad;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(idAccion, idFuncionalidad, idRol);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RolAccionFuncionalidadKey other = (RolAccionFuncionalidadKey) obj;
		return Objects.equals(idAccion, other.idAccion) && Objects.equals(idFuncionalidad, other.idFuncionalidad)
				&& Objects.equals(idRol, other.idRol);
	}

}
