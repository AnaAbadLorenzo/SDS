package com.sds.service.accion.model;

public class PermisosFuncionalidadAccion {

	private String rol;
	private String funcionalidadAccion;
	private String tienePermiso;

	public PermisosFuncionalidadAccion() {
		super();
	}

	public PermisosFuncionalidadAccion(final String rol, final String funcionalidadAccion, final String tienePermiso) {
		super();
		this.rol = rol;
		this.funcionalidadAccion = funcionalidadAccion;
		this.tienePermiso = tienePermiso;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(final String rol) {
		this.rol = rol;
	}

	public String getFuncionalidadAccion() {
		return funcionalidadAccion;
	}

	public void setFuncionalidadAccion(final String funcionalidadAccion) {
		this.funcionalidadAccion = funcionalidadAccion;
	}

	public String getTienePermiso() {
		return tienePermiso;
	}

	public void setTienePermiso(final String tienePermiso) {
		this.tienePermiso = tienePermiso;
	}

	@Override
	public String toString() {
		return "PermisosFuncionalidadAccion [rol=" + rol + ", funcionalidadAccion=" + funcionalidadAccion
				+ ", tienePermiso=" + tienePermiso + "]";
	}

}
