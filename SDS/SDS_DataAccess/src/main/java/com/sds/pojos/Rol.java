package com.sds.pojos;

import java.util.Set;

public class Rol extends GenericPojo {

	private static final long serialVersionUID = 1L;

	public Integer idRol;
	public String rolName;
	public String rolDescription;

	private Set<Accion> acciones;
	private Set<Funcionalidad> funcionalidades;
	private Set<Usuario> usuarios;

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(final Integer idRol) {
		this.idRol = idRol;
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

	public Set<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(final Set<Accion> acciones) {
		this.acciones = acciones;
	}

	public Set<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(final Set<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(final Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", rolName=" + rolName + ", acciones=" + acciones + ", funcionalidades="
				+ funcionalidades + ", usuarios=" + usuarios + "]";
	}

}
