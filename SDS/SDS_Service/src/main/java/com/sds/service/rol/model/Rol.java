package com.sds.service.rol.model;

import com.sds.model.RolEntity;

public class Rol {

	private String usuario;
	private RolEntity rol;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public RolEntity getRol() {
		return rol;
	}

	public void setRol(final RolEntity rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Rol [usuario=" + usuario + ", rol_Nombre=" + rol.getRolName() + ", rol_Descripcion="
				+ rol.getRolDescription() + "]";
	}

}
