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

	@Override
	public String toString() {
		return "Registro [datosPersona_Nombre=" + datosPersona.getNombreP() + ", datosPersona_Apellidos="
				+ datosPersona.getApellidosP() + ", datosPersona_DNI=" + datosPersona.getDniP()
				+ ", datosPersona_Email=" + datosPersona.getEmailP() + ", datosPersona_Telefono="
				+ datosPersona.getTelefonoP() + ", datosPersona_FechaNacimiento=" + datosPersona.getFechaNacP()
				+ ", datosUsuario_DNI=" + datosUsuario.getDniUsuario() + ", datosUsuario_Password="
				+ datosUsuario.getPasswdUsuario() + ", datosUsuario_Password=" + datosUsuario.getUsuario()
				+ ", datosEmpresa_CIF=" + datosEmpresa.getCifEmpresa() + ", datosEmpresa_Direccion="
				+ datosEmpresa.getDireccionEmpresa() + ", datosEmpresa_Nombre=" + datosEmpresa.getNombreEmpresa()
				+ ", datosEmpresa_Nombre=" + datosEmpresa.getNombreEmpresa() + ", datosEmpresa_Telefono="
				+ datosEmpresa.getTelefonoEmpresa() + "]";
	}

}
