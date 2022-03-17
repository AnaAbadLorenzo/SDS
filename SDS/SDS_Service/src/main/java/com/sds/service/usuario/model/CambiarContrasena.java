package com.sds.service.usuario.model;

public class CambiarContrasena {

	private String usuario;
	private String passwdUsuario;

	public CambiarContrasena() {

	}

	public CambiarContrasena(final String usuario, final String passwdUsuario) {
		super();
		this.usuario = usuario;
		this.passwdUsuario = passwdUsuario;
	}

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
		return "CambiarContrasena [usuario=" + usuario + ", passwdUsuario=" + passwdUsuario + "]";
	}

}
