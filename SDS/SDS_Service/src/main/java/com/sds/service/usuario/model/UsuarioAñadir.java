package com.sds.service.usuario.model;

import com.sds.model.PersonaEntity;
import com.sds.model.UsuarioEntity;

public class UsuarioAñadir {

	private String usuario;
	private PersonaEntity personaEntity;
	private UsuarioEntity usuarioEntity;

	public UsuarioAñadir() {

	}

	public UsuarioAñadir(final String usuario, final PersonaEntity personaEntity, final UsuarioEntity usuarioEntity) {
		super();
		this.usuario = usuario;
		this.personaEntity = personaEntity;
		this.usuarioEntity = usuarioEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public PersonaEntity getPersonaEntity() {
		return personaEntity;
	}

	public void setPersonaEntity(final PersonaEntity personaEntity) {
		this.personaEntity = personaEntity;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(final UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	@Override
	public String toString() {
		return "UsuarioAñadir [usuario=" + usuario + ", personaEntity=" + personaEntity + ", usuarioEntity="
				+ usuarioEntity + "]";
	}

}
