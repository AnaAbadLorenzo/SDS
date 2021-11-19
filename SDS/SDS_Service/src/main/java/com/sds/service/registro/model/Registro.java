package com.sds.service.registro.model;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.UsuarioEntity;

public class Registro {

	private PersonaEntity datosPersona;
	private UsuarioEntity datosUsuario;
	private EmpresaEntity datosEmpresa;

	public PersonaEntity getDatosPersona() {
		return datosPersona;
	}

	public void setDatosPersona(final PersonaEntity persona) {
		this.datosPersona = persona;
	}

	public UsuarioEntity getDatosUsuario() {
		return datosUsuario;
	}

	public void setDatosUsuario(final UsuarioEntity usuario) {
		this.datosUsuario = usuario;
	}

	public EmpresaEntity getDatosEmpresa() {
		return datosEmpresa;
	}

	public void setDatosEmpresa(final EmpresaEntity empresa) {
		this.datosEmpresa = empresa;
	}

}
