package com.sds.service.test.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.login.model.Login;
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

		if (jsonRolVacio.get(Constantes.ROL_ID).toString().equals("")) {
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
}
