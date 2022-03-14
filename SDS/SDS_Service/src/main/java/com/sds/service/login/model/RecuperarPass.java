package com.sds.service.login.model;

public class RecuperarPass {

	private String usuario;
	private String emailUsuario;
	private String idioma;

	public RecuperarPass() {

	}

	public RecuperarPass(final String usuario, final String emailUsuario, final String idioma) {
		super();
		this.usuario = usuario;
		this.emailUsuario = emailUsuario;
		this.idioma = idioma;
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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(final String idioma) {
		this.idioma = idioma;
	}

	@Override
	public String toString() {
		return "RecuperarPass [usuario=" + usuario + ", emailUsuario=" + emailUsuario + ", idioma=" + idioma + "]";
	}

}
