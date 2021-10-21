package com.sds.pojos;

import java.util.Set;

public class Funcionalidad extends GenericPojo {

	private static final long serialVersionUID = 1L;

	public Integer idFuncionalidad;
	public String nombreFuncionalidad;
	public String descripFuncionalidad;
	public int borradoFuncionalidad;

	private Set<Accion> acciones;
	private Set<Rol> roles;

	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(final Integer idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

	public String getNombreFuncionalidad() {
		return nombreFuncionalidad;
	}

	public void setNombreFuncionalidad(final String nombreFuncionalidad) {
		this.nombreFuncionalidad = nombreFuncionalidad;
	}

	public String getDescripFuncionalidad() {
		return descripFuncionalidad;
	}

	public void setDescripFuncionalidad(final String descripFuncionalidad) {
		this.descripFuncionalidad = descripFuncionalidad;
	}

	public int getBorradoFuncionalidad() {
		return borradoFuncionalidad;
	}

	public void setBorradoFuncionalidad(final int borradoFuncionalidad) {
		this.borradoFuncionalidad = borradoFuncionalidad;
	}

	public Set<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(final Set<Accion> acciones) {
		this.acciones = acciones;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(final Set<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Funcionalidad [idFuncionalidad=" + idFuncionalidad + ", nombreFuncionalidad=" + nombreFuncionalidad
				+ ", descripFuncionalidad=" + descripFuncionalidad + ", borradoFuncionalidad=" + borradoFuncionalidad
				+ ", acciones=" + acciones + ", roles=" + roles + "]";
	}

}
