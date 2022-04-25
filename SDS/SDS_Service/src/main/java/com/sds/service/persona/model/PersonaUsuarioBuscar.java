package com.sds.service.persona.model;

public class PersonaUsuarioBuscar {

	private String usuario;
	private int inicio;
	private int tamanhoPagina;

	public PersonaUsuarioBuscar() {
		super();
	}

	public PersonaUsuarioBuscar(final String usuario, final int inicio, final int tamanhoPagina) {
		super();
		this.usuario = usuario;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(final int inicio) {
		this.inicio = inicio;
	}

	public int getTamanhoPagina() {
		return tamanhoPagina;
	}

	public void setTamanhoPagina(final int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	@Override
	public String toString() {
		return "PersonaUsuarioBuscar [usuario=" + usuario + ", inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina
				+ "]";
	}

}
