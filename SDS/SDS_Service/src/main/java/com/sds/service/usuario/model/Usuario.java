package com.sds.service.usuario.model;

import com.sds.model.UsuarioEntity;

public class Usuario {

	private String usuario;
	private UsuarioEntity usuarioEntity;

	public Usuario() {

	}

	public Usuario(final String usuario, final UsuarioEntity usuarioEntity) {
		this.usuario = usuario;
		this.usuarioEntity = usuarioEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(final UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", usuarioEntity=" + usuarioEntity + "]";
	}

}
