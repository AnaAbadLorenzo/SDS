package com.sds.service.accion.model;

public class Permiso {
	private String rolName;
	private String accionName;
	private String funcionalidadName;

	public Permiso() {
		super();
	}

	public Permiso(final String rolName, final String accionName, final String funcionalidadName) {
		super();
		this.rolName = rolName;
		this.accionName = accionName;
		this.funcionalidadName = funcionalidadName;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(final String rolName) {
		this.rolName = rolName;
	}

	public String getAccionName() {
		return accionName;
	}

	public void setAccionName(final String accionName) {
		this.accionName = accionName;
	}

	public String getFuncionalidadName() {
		return funcionalidadName;
	}

	public void setFuncionalidadName(final String funcionalidadName) {
		this.funcionalidadName = funcionalidadName;
	}

	@Override
	public String toString() {
		return "Permiso [rolName=" + rolName + ", accionName=" + accionName + ", funcionalidadName=" + funcionalidadName
				+ "]";
	}

}
