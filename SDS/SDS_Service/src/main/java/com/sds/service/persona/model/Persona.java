package com.sds.service.persona.model;

import com.sds.model.PersonaEntity;

public class Persona {

	public String usuario;
	public PersonaEntity persona;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public PersonaEntity getPersona() {
		return persona;
	}

	public void setPersona(final PersonaEntity persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Persona [usuario=" + usuario + ", persona=" + persona + "]";
	}

}
