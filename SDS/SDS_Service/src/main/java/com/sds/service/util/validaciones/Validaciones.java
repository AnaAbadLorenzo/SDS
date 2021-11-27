package com.sds.service.util.validaciones;

import org.apache.commons.lang3.StringUtils;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.login.model.Login;
import com.sds.service.registro.model.Registro;

public class Validaciones {

	public boolean comprobarLoginBlank(final Login login) {
		if (StringUtils.isBlank(login.getUsuario()) || StringUtils.isBlank(login.getPasswdUsuario())) {
			return false;
		}
		return true;
	}

	public boolean comprobarRegistroBlank(final Registro registro) {
		if (!comprobarPersonaBlank(registro.getDatosPersona()) || !comprobarUsuarioBlank(registro.getDatosUsuario())) {
			return false;
		} else {
			if (registro.getDatosEmpresa().getIdEmpresa() == null) {
				if (!comprobarEmpresaBlank(registro.getDatosEmpresa())) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean comprobarPersonaBlank(final PersonaEntity persona) {
		if (StringUtils.isBlank(persona.getDniP()) || StringUtils.isBlank(persona.getNombreP())
				|| StringUtils.isBlank(persona.getApellidosP()) || StringUtils.isBlank(persona.getDireccionP())
				|| StringUtils.isBlank(persona.getTelefonoP()) || StringUtils.isBlank(persona.getEmailP())
				|| persona.getFechaNacP().equals(null)) {
			return false;
		}

		return true;
	}

	private boolean comprobarUsuarioBlank(final UsuarioEntity usuario) {
		if (StringUtils.isBlank(usuario.getUsuario()) || StringUtils.isBlank(usuario.getPasswdUsuario())
				|| usuario.getBorradoUsuario() == null) {
			return false;
		}

		return true;
	}

	public boolean comprobarEmpresaBlank(final EmpresaEntity empresa) {
		if (StringUtils.isBlank(empresa.getCifEmpresa()) || StringUtils.isBlank(empresa.getNombreEmpresa())
				|| StringUtils.isBlank(empresa.getDireccionEmpresa())
				|| StringUtils.isBlank(empresa.getTelefonoEmpresa()) || empresa.getBorradoEmpresa() == null) {
			return false;
		}

		return true;
	}

}
