package com.sds.service.persona.model;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.UsuarioEntity;

public class PersonaAnadir {

	private String usuario;
	private PersonaEntity personaEntity;
	private UsuarioEntity usuarioEntity;
	private EmpresaEntity empresaEntity;
	private String seleccionarEmpresa;

	public PersonaAnadir() {

	}

	public PersonaAnadir(final PersonaEntity personaEntity, final UsuarioEntity usuarioEntity,
			final EmpresaEntity empresaEntity) {
		super();
		this.personaEntity = personaEntity;
		this.usuarioEntity = usuarioEntity;
		this.empresaEntity = empresaEntity;
	}

	public PersonaAnadir(final PersonaEntity personaEntity, final UsuarioEntity usuarioEntity) {
		super();
		this.personaEntity = personaEntity;
		this.usuarioEntity = usuarioEntity;
	}

	public PersonaAnadir(final String usuario, final PersonaEntity personaEntity, final UsuarioEntity usuarioEntity) {
		super();
		this.usuario = usuario;
		this.personaEntity = personaEntity;
		this.usuarioEntity = usuarioEntity;
	}

	public PersonaAnadir(final String usuario, final PersonaEntity personaEntity, final UsuarioEntity usuarioEntity,
			final EmpresaEntity empresaEntity, final String seleccionarEmpresa) {
		super();
		this.usuario = usuario;
		this.personaEntity = personaEntity;
		this.usuarioEntity = usuarioEntity;
		this.empresaEntity = empresaEntity;
		this.seleccionarEmpresa = seleccionarEmpresa;
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

	public EmpresaEntity getEmpresaEntity() {
		return empresaEntity;
	}

	public void setEmpresaEntity(final EmpresaEntity empresaEntity) {
		this.empresaEntity = empresaEntity;
	}

	public String getSeleccionarEmpresa() {
		return seleccionarEmpresa;
	}

	public void setSeleccionarEmpresa(final String seleccionarEmpresa) {
		this.seleccionarEmpresa = seleccionarEmpresa;
	}

	@Override
	public String toString() {
		return "PersonaAnadir [usuario=" + usuario + ", personaEntity=" + personaEntity + ", usuarioEntity="
				+ usuarioEntity + ", empresaEntity=" + empresaEntity + ", seleccionarEmpresa=" + seleccionarEmpresa
				+ "]";
	}

}
