package com.sds.service.accion.model;

import com.sds.model.AccionEntity;
import com.sds.model.FuncionalidadEntity;
import com.sds.model.RolEntity;

public class PermisosFuncionalidadAccion {

	private RolEntity rol;
	private FuncionalidadEntity funcionalidad;
	private AccionEntity accion;
	private String tienePermiso;

	public PermisosFuncionalidadAccion() {
		super();
	}

	public PermisosFuncionalidadAccion(final RolEntity rol, final FuncionalidadEntity funcionalidad,
			final AccionEntity accion, final String tienePermiso) {
		super();
		this.rol = rol;
		this.funcionalidad = funcionalidad;
		this.accion = accion;
		this.tienePermiso = tienePermiso;
	}

	public RolEntity getRol() {
		return rol;
	}

	public void setRol(final RolEntity rol) {
		this.rol = rol;
	}

	public FuncionalidadEntity getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(final FuncionalidadEntity funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public AccionEntity getAccion() {
		return accion;
	}

	public void setAccion(final AccionEntity accion) {
		this.accion = accion;
	}

	public String getTienePermiso() {
		return tienePermiso;
	}

	public void setTienePermiso(final String tienePermiso) {
		this.tienePermiso = tienePermiso;
	}

	@Override
	public String toString() {
		return "PermisosFuncionalidadAccion [rol=" + rol + ", funcionalidad=" + funcionalidad + ", accion=" + accion
				+ ", tienePermiso=" + tienePermiso + "]";
	}

}
