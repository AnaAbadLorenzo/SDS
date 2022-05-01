package com.sds.service.persona.model;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;

public class PersonaAsociarEmpresa {

	private String usuario;
	private PersonaEntity persona;
	private EmpresaEntity empresa;

	public PersonaAsociarEmpresa() {
		super();
	}

	public PersonaAsociarEmpresa(final String usuario, final PersonaEntity persona, final EmpresaEntity empresa) {
		super();
		this.usuario = usuario;
		this.persona = persona;
		this.empresa = empresa;
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
		return "PersonaAsociarEmpresa [usuario=" + usuario + ", persona=" + persona + ", empresa=" + empresa + "]";
	}

}
