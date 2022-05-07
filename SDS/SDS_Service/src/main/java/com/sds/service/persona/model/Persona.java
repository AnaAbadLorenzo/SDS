package com.sds.service.persona.model;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;

public class Persona {

	public String usuario;
	public PersonaEntity persona;
	public EmpresaEntity empresa;

	public Persona() {

	}

	public Persona(final String usuario, final PersonaEntity persona, final EmpresaEntity empresa) {
		super();
		this.usuario = usuario;
		this.persona = persona;
		this.empresa = empresa;
	}

	public Persona(final String usuario, final PersonaEntity persona) {
		super();
		this.usuario = usuario;
		this.persona = persona;
	}

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

	public EmpresaEntity getEmpresa() {
		return empresa;
	}

	public void setEmpresa(final EmpresaEntity empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Persona [usuario=" + usuario + ", persona=" + persona + ", empresa=" + empresa + "]";
	}

}
