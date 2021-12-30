package com.sds.service.usuario.model;

import com.sds.model.UsuarioEntity;

public class Usuario {

	public String usuarioLogg;
	public UsuarioEntity usuarioEntity;

	public String getUsuarioLogg() {
		return usuarioLogg;
	}

	public void setUsuarioLogg(final String usuarioLogg) {
		this.usuarioLogg = usuarioLogg;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(final UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	@Override
	public String toString() {
		return "Usuario [usuarioLogg=" + usuarioLogg + ", usuarioEntity=" + usuarioEntity + "]";
	}

}
