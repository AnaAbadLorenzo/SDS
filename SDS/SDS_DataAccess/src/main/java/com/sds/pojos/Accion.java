package com.sds.pojos;

import java.util.Set;

public class Accion extends GenericPojo {

	private static final long serialVersionUID = 1L;

	public Integer idAccion;
	public String nombreAccion;
	public String descripAccion;
	public int borradoAccion;

	private Set<Rol> roles;
	private Set<Funcionalidad> funcionalidades;

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(final Integer idAccion) {
		this.idAccion = idAccion;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(final String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getDescripAccion() {
		return descripAccion;
	}

	public void setDescripAccion(final String descripAccion) {
		this.descripAccion = descripAccion;
	}

	public int getBorradoAccion() {
		return borradoAccion;
	}

	public void setBorradoAccion(final int borradoAccion) {
		this.borradoAccion = borradoAccion;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(final Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(final Set<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	@Override
	public String toString() {
		return "Accion [idAccion=" + idAccion + ", nombreAccion=" + nombreAccion + ", descripAccion=" + descripAccion
				+ ", borradoAccion=" + borradoAccion + ", roles=" + roles + ", funcionalidades=" + funcionalidades
				+ "]";
	}

}
