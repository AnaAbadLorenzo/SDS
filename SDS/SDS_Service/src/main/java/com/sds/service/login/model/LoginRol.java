package com.sds.service.login.model;

public class LoginRol {

	private String tokenUsuario;
	private String rolUsuario;
	private String usuario;

	public LoginRol() {

	}

	public LoginRol(final String tokenUsuario, final String rolUsuario, final String usuario) {
		super();
		this.tokenUsuario = tokenUsuario;
		this.rolUsuario = rolUsuario;
		this.usuario = usuario;
	}

	public String getTokenUsuario() {
		return tokenUsuario;
	}

	public void setTokenUsuario(final String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}

	public String getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(final String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

}
