package com.sds.service.usuario.model;

import com.sds.model.RolEntity;

public class UsuarioBuscar {

	private String dniUsuario;
	private String usuario;
	private RolEntity rol;

	public UsuarioBuscar() {

	}

	public UsuarioBuscar(final String dniUsuario, final String usuario, final RolEntity rol) {
		super();
		this.dniUsuario = dniUsuario;
		this.usuario = usuario;
		this.rol = rol;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(final String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

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

}
