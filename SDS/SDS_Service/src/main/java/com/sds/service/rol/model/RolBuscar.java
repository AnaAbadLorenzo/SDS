package com.sds.service.rol.model;

public class RolBuscar {

	public String rolName;
	public String rolDescription;

	public RolBuscar() {

	}

	public RolBuscar(final String rolName, final String rolDescription) {
		this.rolName = rolName;
		this.rolDescription = rolDescription;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(final String rolName) {
		this.rolName = rolName;
	}

	public String getRolDescription() {
		return rolDescription;
	}

	public void setRolDescription(final String rolDescription) {
		this.rolDescription = rolDescription;
	}

}
