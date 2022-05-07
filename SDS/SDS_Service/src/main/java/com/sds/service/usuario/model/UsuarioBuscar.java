package com.sds.service.usuario.model;

import com.sds.model.RolEntity;

public class UsuarioBuscar {

	private String dniUsuario;
	private String usuario;
	private RolEntity rol;
	private int inicio;
	private int tamanhoPagina;

	public UsuarioBuscar() {

	}

	public UsuarioBuscar(final String dniUsuario, final String usuario, final RolEntity rol, final int inicio,
			final int tamanhoPagina) {
		super();
		this.dniUsuario = dniUsuario;
		this.usuario = usuario;
		this.rol = rol;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public UsuarioBuscar(final String usuario, final RolEntity rol, final int inicio, final int tamanhoPagina) {
		super();
		this.usuario = usuario;
		this.rol = rol;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public UsuarioBuscar(final String usuario, final RolEntity rol) {
		super();
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
		return "UsuarioBuscar [dniUsuario=" + dniUsuario + ", usuario=" + usuario + ", rol=" + rol + ", inicio="
				+ inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
