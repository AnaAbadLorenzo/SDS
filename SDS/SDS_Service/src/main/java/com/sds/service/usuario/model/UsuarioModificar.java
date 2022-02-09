package com.sds.service.usuario.model;

import com.sds.model.RolEntity;

public class UsuarioModificar {

	private RolEntity rolEntity;
	private Usuario usuario;

	public UsuarioModificar() {

	}

	public UsuarioModificar(final RolEntity rolEntity, final Usuario usuario) {
		super();
		this.rolEntity = rolEntity;
		this.usuario = usuario;
	}

	public RolEntity getRolEntity() {
		return rolEntity;
	}

	public void setRolEntity(final RolEntity rolEntity) {
		this.rolEntity = rolEntity;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UsuarioModificar [rolEntity=" + rolEntity + ", usuario=" + usuario + "]";
	}

}
