package com.sds.service.accion.model;

import com.sds.model.RolAccionFuncionalidadEntity;

public class RolAccionFuncionalidad {

	private RolAccionFuncionalidadEntity rolAccionFuncionalidad;
	private String usuario;

	public RolAccionFuncionalidad() {

	}

	public RolAccionFuncionalidad(final RolAccionFuncionalidadEntity rolAccionFuncionalidad, final String usuario) {
		super();
		this.rolAccionFuncionalidad = rolAccionFuncionalidad;
		this.usuario = usuario;
	}

	public RolAccionFuncionalidadEntity getRolAccionFuncionalidad() {
		return rolAccionFuncionalidad;
	}

	public void setRolAccionFuncionalidad(final RolAccionFuncionalidadEntity rolAccionFuncionalidad) {
		this.rolAccionFuncionalidad = rolAccionFuncionalidad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "RolAccionFuncionalidad [rolAccionFuncionalidad=" + rolAccionFuncionalidad + ", usuario=" + usuario
				+ "]";
	}
}
