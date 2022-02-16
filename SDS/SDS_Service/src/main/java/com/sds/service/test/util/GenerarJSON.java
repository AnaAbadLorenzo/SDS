package com.sds.service.test.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.sds.model.AccionEntity;
import com.sds.model.EmpresaEntity;
import com.sds.model.FuncionalidadEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.login.model.Login;
import com.sds.service.login.model.RecuperarPass;
import com.sds.service.registro.model.Registro;
import com.sds.service.util.Util;

public class GenerarJSON {

	public Login generateLogin(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonUsuarioContrasenaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Login login = new Login();
		login.setPasswdUsuario(jsonUsuarioContrasenaVacios.get(Constantes.PASSWD_USUARIO).toString());
		login.setUsuario(jsonUsuarioContrasenaVacios.get(Constantes.USUARIO).toString());

		return login;

	}

	public RecuperarPass generateRecuperarPass(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonRecuperarPass = new Util().getDatosJson(fichero, nombrePrueba);

		final RecuperarPass recuperarPass = new RecuperarPass();
		recuperarPass.setUsuario(jsonRecuperarPass.get(Constantes.USUARIO).toString());
		recuperarPass.setEmailUsuario(jsonRecuperarPass.get(Constantes.EMAILP).toString());

		return recuperarPass;

	}

	public Registro generateRegistro(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonRegistroVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Registro registro = new Registro();
		final PersonaEntity persona = new PersonaEntity();
		final UsuarioEntity usuario = new UsuarioEntity();
		final EmpresaEntity empresa = new EmpresaEntity();
		boolean acentos = false;
		boolean caracEspeciales = false;

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String date = CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.FECHANACP).toString(),
				StringUtils.EMPTY);
		Date fecha = null;
		java.sql.Date fechaSql = null;

		for (int i = 0; i < date.length(); i++) {

			final String letra = date.charAt(i) + "";

			final Pattern patronAcentos = Pattern.compile(Constantes.PATRON_ACENTOS);
			final Matcher comprobacionAcentos = patronAcentos.matcher(letra);
			final Pattern patronEspeciales = Pattern.compile(Constantes.PATRON_CARACTERES_ESPECIALES);
			final Matcher comprobacionEspeciales = patronEspeciales.matcher(letra);

			if (comprobacionAcentos.matches()) {
				acentos = true;
			}

			if (comprobacionEspeciales.matches()) {
				caracEspeciales = true;
			}
		}

		if (date == "" || date.contains(Constantes.ENHE) || acentos || caracEspeciales
				|| date.contains(Constantes.ESPACIO) || date.length() < 8 || date.length() > 10) {
			fecha = sdf.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
		} else {
			fecha = sdf.parse(date);
			fechaSql = new java.sql.Date(fecha.getTime());

		}

		persona.setDniP(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.DNIP).toString(), StringUtils.EMPTY));
		persona.setNombreP(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.NOMBREP).toString(), StringUtils.EMPTY));
		persona.setApellidosP(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.APELLIDOSP).toString(), StringUtils.EMPTY));
		persona.setFechaNacP(fechaSql);
		persona.setDireccionP(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.DIRECCIONP).toString(), StringUtils.EMPTY));
		persona.setEmailP(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.EMAILP).toString(), StringUtils.EMPTY));
		persona.setTelefonoP(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.TELEFONOP).toString(), StringUtils.EMPTY));

		usuario.setDniUsuario(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.DNIP).toString(), StringUtils.EMPTY));
		usuario.setUsuario(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		usuario.setPasswdUsuario(CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.PASSWD_USUARIO).toString(),
				StringUtils.EMPTY));

		final RolEntity rol = new RolEntity(2, "usuario", "Contendra a todos los usuarios registrados de la aplicacion",
				0);

		usuario.setRol(rol);
		persona.setUsuario(usuario);

		empresa.setCifEmpresa(
				CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.CIF_EMPRESA).toString(), StringUtils.EMPTY));
		empresa.setNombreEmpresa(CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.NOMBRE_EMPRESA).toString(),
				StringUtils.EMPTY));
		empresa.setDireccionEmpresa(CommonUtilities
				.coalesce(jsonRegistroVacios.get(Constantes.DIRECCION_EMPRESA).toString(), StringUtils.EMPTY));
		empresa.setTelefonoEmpresa(CommonUtilities
				.coalesce(jsonRegistroVacios.get(Constantes.TELEFONO_EMPRESA).toString(), StringUtils.EMPTY));

		registro.setDatosPersona(persona);
		registro.setDatosUsuario(usuario);
		registro.setDatosEmpresa(empresa);

		return registro;
	}

	public RolEntity generarRol(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonRolVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final RolEntity rol = new RolEntity();

		if (jsonRolVacio.get(Constantes.ROL_ID).toString().equals(StringUtils.EMPTY)) {
			rol.setIdRol(0);
		} else {
			rol.setIdRol(Integer.parseInt(jsonRolVacio.get(Constantes.ROL_ID).toString()));
		}
		rol.setRolName(CommonUtilities.coalesce(jsonRolVacio.get(Constantes.ROL_NAME).toString(), StringUtils.EMPTY));
		rol.setRolDescription(
				CommonUtilities.coalesce(jsonRolVacio.get(Constantes.ROL_DESCRIPTION).toString(), StringUtils.EMPTY));

		rol.setBorradoRol(0);

		return rol;

	}

	public AccionEntity generarAccion(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonRolVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final AccionEntity accion = new AccionEntity();

		if (jsonRolVacio.get(Constantes.ACCION_ID).toString().equals(StringUtils.EMPTY)) {
			accion.setIdAccion(0);
		} else {
			accion.setIdAccion(Integer.parseInt(jsonRolVacio.get(Constantes.ACCION_ID).toString()));
		}
		accion.setNombreAccion(
				CommonUtilities.coalesce(jsonRolVacio.get(Constantes.ACCION_NAME).toString(), StringUtils.EMPTY));
		accion.setDescripAccion(CommonUtilities.coalesce(jsonRolVacio.get(Constantes.ACCION_DESCRIPTION).toString(),
				StringUtils.EMPTY));

		accion.setBorradoAccion(0);

		return accion;

	}

	public FuncionalidadEntity generarFuncionalidad(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonFuncionalidadVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final FuncionalidadEntity funcionalidad = new FuncionalidadEntity();

		if (jsonFuncionalidadVacio.get(Constantes.FUNCIONALIDAD_ID).toString().equals(StringUtils.EMPTY)) {
			funcionalidad.setIdFuncionalidad(0);
		} else {
			funcionalidad.setIdFuncionalidad(
					Integer.parseInt(jsonFuncionalidadVacio.get(Constantes.FUNCIONALIDAD_ID).toString()));
		}
		funcionalidad.setNombreFuncionalidad(CommonUtilities
				.coalesce(jsonFuncionalidadVacio.get(Constantes.FUNCIONALIDAD_NAME).toString(), StringUtils.EMPTY));
		funcionalidad.setDescripFuncionalidad(CommonUtilities.coalesce(
				jsonFuncionalidadVacio.get(Constantes.FUNCIONALIDAD_DESCRIPTION).toString(), StringUtils.EMPTY));

		funcionalidad.setBorradoFuncionalidad(0);

		return funcionalidad;

	}

	public UsuarioEntity generateUsuario(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonUsuarioVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final UsuarioEntity usuario = new UsuarioEntity();

		usuario.setDniUsuario(
				CommonUtilities.coalesce(jsonUsuarioVacio.get(Constantes.USUARIO_DNI).toString(), StringUtils.EMPTY));
		usuario.setUsuario(
				CommonUtilities.coalesce(jsonUsuarioVacio.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		usuario.setPasswdUsuario(CommonUtilities.coalesce(jsonUsuarioVacio.get(Constantes.PASSWD_USUARIO).toString(),
				StringUtils.EMPTY));

		usuario.setBorradoUsuario(0);

		final RolEntity rol = new RolEntity(2, "usuario", "Contendra a todos los usuarios registrados de la aplicacion",
				0);

		usuario.setRol(rol);

		return usuario;

	}

	public PersonaEntity generatePersona(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonPersonaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final EmpresaEntity empresa = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);
		final PersonaEntity persona = new PersonaEntity();
		boolean acentos = false;
		boolean caracEspeciales = false;

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String date = CommonUtilities.coalesce(jsonPersonaVacios.get(Constantes.FECHANACP).toString(),
				StringUtils.EMPTY);
		Date fecha = null;
		java.sql.Date fechaSql = null;

		for (int i = 0; i < date.length(); i++) {

			final String letra = date.charAt(i) + "";

			final Pattern patronAcentos = Pattern.compile(Constantes.PATRON_ACENTOS);
			final Matcher comprobacionAcentos = patronAcentos.matcher(letra);
			final Pattern patronEspeciales = Pattern.compile(Constantes.PATRON_CARACTERES_ESPECIALES);
			final Matcher comprobacionEspeciales = patronEspeciales.matcher(letra);

			if (comprobacionAcentos.matches()) {
				acentos = true;
			}

			if (comprobacionEspeciales.matches()) {
				caracEspeciales = true;
			}
		}

		if (date == "" || date.contains(Constantes.ENHE) || acentos || caracEspeciales
				|| date.contains(Constantes.ESPACIO) || date.length() < 8 || date.length() > 10) {
			fecha = sdf.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
		} else {
			fecha = sdf.parse(date);
			fechaSql = new java.sql.Date(fecha.getTime());

		}

		persona.setDniP(CommonUtilities.coalesce(jsonPersonaVacios.get(Constantes.DNIP).toString(), StringUtils.EMPTY));
		persona.setNombreP(
				CommonUtilities.coalesce(jsonPersonaVacios.get(Constantes.NOMBREP).toString(), StringUtils.EMPTY));
		persona.setApellidosP(
				CommonUtilities.coalesce(jsonPersonaVacios.get(Constantes.APELLIDOSP).toString(), StringUtils.EMPTY));
		persona.setFechaNacP(fechaSql);
		persona.setDireccionP(
				CommonUtilities.coalesce(jsonPersonaVacios.get(Constantes.DIRECCIONP).toString(), StringUtils.EMPTY));
		persona.setEmailP(
				CommonUtilities.coalesce(jsonPersonaVacios.get(Constantes.EMAILP).toString(), StringUtils.EMPTY));
		persona.setTelefonoP(
				CommonUtilities.coalesce(jsonPersonaVacios.get(Constantes.TELEFONOP).toString(), StringUtils.EMPTY));
		persona.setBorradoP(0);
		persona.setEmpresa(empresa);

		return persona;
	}

	public EmpresaEntity generateEmpresa(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonEmpresaVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final EmpresaEntity empresa = new EmpresaEntity();

		empresa.setCifEmpresa(
				CommonUtilities.coalesce(jsonEmpresaVacio.get(Constantes.CIF_EMPRESA).toString(), StringUtils.EMPTY));
		empresa.setNombreEmpresa(CommonUtilities.coalesce(jsonEmpresaVacio.get(Constantes.NOMBRE_EMPRESA).toString(),
				StringUtils.EMPTY));
		empresa.setDireccionEmpresa(CommonUtilities
				.coalesce(jsonEmpresaVacio.get(Constantes.DIRECCION_EMPRESA).toString(), StringUtils.EMPTY));
		empresa.setTelefonoEmpresa(CommonUtilities
				.coalesce(jsonEmpresaVacio.get(Constantes.TELEFONO_EMPRESA).toString(), StringUtils.EMPTY));
		empresa.setBorradoEmpresa(0);

		return empresa;

	}

}
