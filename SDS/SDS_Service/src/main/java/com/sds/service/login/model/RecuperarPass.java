package com.sds.service.login.model;

public class RecuperarPass {

	private String usuario;
	private String emailUsuario;

	public RecuperarPass() {

	}

	public RecuperarPass(final String usuario, final String emailUsuario) {
		super();
		this.usuario = usuario;
		this.emailUsuario = emailUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(final String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

}
