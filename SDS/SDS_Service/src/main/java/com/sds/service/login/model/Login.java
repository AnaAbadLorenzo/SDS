package com.sds.service.login.model;

public class Login {

	private String usuario;
	private String passwdUsuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getPasswdUsuario() {
		return passwdUsuario;
	}

	public void setPasswdUsuario(final String passwdUsuario) {
		this.passwdUsuario = passwdUsuario;
	}

	@Override
	public String toString() {
		return "Login [usuario=" + usuario + ", passwdUsuario=" + passwdUsuario + "]";
	}
}
