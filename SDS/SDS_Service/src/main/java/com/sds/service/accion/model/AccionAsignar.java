package com.sds.service.accion.model;

import com.sds.model.AccionEntity;
import com.sds.model.FuncionalidadEntity;
import com.sds.model.RolEntity;

public class AccionAsignar {

	public AccionEntity accion;
	public RolEntity rol;
	public FuncionalidadEntity funcionalidad;
	public String usuario;

	public AccionAsignar() {
	}

	public AccionAsignar(final AccionEntity accion, final RolEntity rol, final FuncionalidadEntity funcionalidad,
			final String usuario) {
		super();
		this.accion = accion;
		this.rol = rol;
		this.funcionalidad = funcionalidad;
		this.usuario = usuario;
	}

	public AccionEntity getAccion() {
		return accion;
	}

	public void setAccion(final AccionEntity accion) {
		this.accion = accion;
	}

	public RolEntity getRol() {
		return rol;
	}

	public void setRol(final RolEntity rol) {
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public FuncionalidadEntity getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(final FuncionalidadEntity funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	@Override
	public String toString() {
		return "AccionAsignar [accion=" + accion + ", rol=" + rol + ", funcionalidad=" + funcionalidad + ", usuario="
				+ usuario + "]";
	}

}
