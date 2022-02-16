package com.sds.service.util.validaciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.sds.model.AccionEntity;
import com.sds.model.EmpresaEntity;
import com.sds.model.FuncionalidadEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.login.model.Login;
import com.sds.service.login.model.RecuperarPass;
import com.sds.service.registro.model.Registro;
import com.sds.service.usuario.model.UsuarioModificar;

public class Validaciones {

	public boolean comprobarLoginBlank(final Login login) {
		if (StringUtils.isBlank(login.getUsuario()) || StringUtils.isBlank(login.getPasswdUsuario())) {
			return false;
		}
		return true;
	}

	public boolean comprobarRegistroBlank(final Registro registro) throws ParseException {
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

	public boolean comprobarPersonaBlank(final PersonaEntity persona) throws ParseException {
		if (StringUtils.isBlank(persona.getDniP()) || StringUtils.isBlank(persona.getNombreP())
				|| StringUtils.isBlank(persona.getApellidosP()) || StringUtils.isBlank(persona.getDireccionP())
				|| StringUtils.isBlank(persona.getTelefonoP()) || StringUtils.isBlank(persona.getEmailP())) {
			return false;

		} else {
			final SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
			Date date = null;
			date = formato.parse("0000-00-00");
			if (persona.getFechaNacP().equals(date)) {
				return false;
			}
		}

		return true;
	}

	public Boolean comprobarDniPersonaBlank(final String dniPersona) {
		if (StringUtils.isBlank(dniPersona)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarNombrePersonaBlank(final String nombreP) {
		if (StringUtils.isBlank(nombreP)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarApellidosPersonaBlank(final String apellidosP) {
		if (StringUtils.isBlank(apellidosP)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarDireccionPersonaBlank(final String direccionP) {
		if (StringUtils.isBlank(direccionP)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarTelefonoPersonaBlank(final String telefonoP) {
		if (StringUtils.isBlank(telefonoP)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarEmailBlank(final String emailP) {
		if (StringUtils.isBlank(emailP)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarFechaNacPersonaBlank(final String fechaNacP) throws ParseException {
		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date fechaSql;

		Date date = null;
		date = formato.parse("0000-00-00");
		fechaSql = new java.sql.Date(date.getTime());
		final String fecha = fechaSql.toString();

		if (fechaNacP.equals(fecha)) {
			return false;

		} else {
			return true;
		}
	}

	public boolean comprobarUsuarioBlank(final UsuarioEntity usuario) {
		if (StringUtils.isBlank(usuario.getUsuario()) || StringUtils.isBlank(usuario.getPasswdUsuario())) {
			return false;
		}

		return true;
	}

	public boolean comprobarPasswdUsuarioBlank(final String passwdUsuario) {
		if (StringUtils.isBlank(passwdUsuario)) {
			return false;
		}

		return true;
	}

	public boolean comprobarUsuarioBlank(final String usuario) {
		if (StringUtils.isBlank(usuario)) {
			return false;
		}

		return true;
	}

	public boolean comprobarEmailUsuarioBlank(final String emailUsuario) {
		if (StringUtils.isBlank(emailUsuario)) {
			return false;
		}

		return true;
	}

	public boolean comprobarRecuperarPassBlank(final RecuperarPass recuperarPass) {
		if (StringUtils.isBlank(recuperarPass.getUsuario()) || StringUtils.isBlank(recuperarPass.getEmailUsuario())) {
			return false;
		}

		return true;
	}

	public boolean comprobarEmpresaBlank(final EmpresaEntity empresa) {
		if (StringUtils.isBlank(empresa.getCifEmpresa()) || StringUtils.isBlank(empresa.getNombreEmpresa())
				|| StringUtils.isBlank(empresa.getDireccionEmpresa())
				|| StringUtils.isBlank(empresa.getTelefonoEmpresa())) {
			return false;
		}

		return true;
	}

	public boolean comprobarCifEmpresa(final String cifEmpresa) {
		if (StringUtils.isBlank(cifEmpresa)) {
			return false;
		}
		return true;
	}

	public boolean comprobarNombreEmpresa(final String nombreEmpresa) {
		if (StringUtils.isBlank(nombreEmpresa)) {
			return false;
		}
		return true;
	}

	public boolean comprobarDireccionEmpresa(final String direccionEmpresa) {
		if (StringUtils.isBlank(direccionEmpresa)) {
			return false;
		}
		return true;
	}

	public boolean comprobarTelefonoEmpresa(final String telefonoEmpresa) {
		if (StringUtils.isBlank(telefonoEmpresa)) {
			return false;
		}
		return true;
	}

	public boolean comprobarRolBlank(final RolEntity rol) {
		if (StringUtils.isBlank(rol.getRolName()) || StringUtils.isBlank(rol.getRolDescription())) {
			return false;
		}

		return true;
	}

	public boolean comprobarNombreRolBlank(final String rolName) {
		if (StringUtils.isBlank(rolName)) {
			return false;
		}

		return true;
	}

	public boolean comprobarDescriptionRolBlank(final String rolDescription) {
		if (StringUtils.isBlank(rolDescription)) {
			return false;
		}

		return true;
	}

	public boolean comprobarLogExcepcionesBlank(final LogExcepcionesEntity logExcepciones) {
		if (StringUtils.isBlank(logExcepciones.getTipoExcepcion())
				|| StringUtils.isBlank(logExcepciones.getDescripcionExcepcion())) {
			return false;
		}

		return true;
	}

	public boolean comprobarLogAccionesBlank(final LogAccionesEntity logAcciones) {
		if (StringUtils.isBlank(logAcciones.getAccion()) || StringUtils.isBlank(logAcciones.getDatos())) {
			return false;
		}

		return true;
	}

	public boolean comprobarAccionBlank(final AccionEntity accion) {
		if (StringUtils.isBlank(accion.getNombreAccion()) || StringUtils.isBlank(accion.getDescripAccion())) {
			return false;
		} else {
			return true;
		}
	}

	public boolean comprobarNombreAccionBlank(final String nombreAccion) {
		if (StringUtils.isBlank(nombreAccion)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean comprobarDescripcionAccionBlank(final String descripcionAccion) {
		if (StringUtils.isBlank(descripcionAccion)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarFuncionalidadBlank(final FuncionalidadEntity funcionalidad) {
		if (StringUtils.isBlank(funcionalidad.getNombreFuncionalidad())
				|| StringUtils.isBlank(funcionalidad.getDescripFuncionalidad())) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarNombreFuncionalidadBlank(final String nombreFuncionalidad) {
		if (StringUtils.isBlank(nombreFuncionalidad)) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean comprobarDescripcionFuncionalidadBlank(final String descripFuncionalidad) {
		if (StringUtils.isBlank(descripFuncionalidad)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean comprobarUsuarioModificarBlank(final UsuarioModificar usuarioModificar) throws ParseException {
		if (!comprobarUsuarioBlank(usuarioModificar.getUsuario().getUsuarioEntity())
				|| !comprobarRolBlank(usuarioModificar.getRolEntity())) {
			return false;
		}

		return true;
	}
}
