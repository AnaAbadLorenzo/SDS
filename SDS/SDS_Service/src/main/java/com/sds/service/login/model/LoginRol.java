package com.sds.service.login.model;

public class LoginRol {

	private String tokenUsuario;
	private String rolUsuario;

	public LoginRol() {

	}

	public LoginRol(final String tokenUsuario, final String rolUsuario) {
		super();
		this.tokenUsuario = tokenUsuario;
		this.rolUsuario = rolUsuario;
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

}
