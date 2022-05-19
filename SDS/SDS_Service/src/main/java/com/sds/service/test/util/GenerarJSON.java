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
import com.sds.model.NoticiasEntity;
import com.sds.model.ObjetivoEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RespuestaPosibleEntity;
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
		login.setPasswdUsuario(
				new String((jsonUsuarioContrasenaVacios.get(Constantes.PASSWD_USUARIO).toString()).getBytes("UTF-8")));
		login.setUsuario(
				new String((jsonUsuarioContrasenaVacios.get(Constantes.USUARIO).toString()).getBytes("UTF-8")));

		return login;

	}

	public RecuperarPass generateRecuperarPass(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonRecuperarPass = new Util().getDatosJson(fichero, nombrePrueba);

		final RecuperarPass recuperarPass = new RecuperarPass();
		recuperarPass.setUsuario(new String((jsonRecuperarPass.get(Constantes.USUARIO).toString()).getBytes("UTF-8")));
		recuperarPass
				.setEmailUsuario(new String((jsonRecuperarPass.get(Constantes.EMAILP).toString()).getBytes("UTF-8")));

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
		final String date = CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.FECHANACP).toString()).getBytes("UTF-8")),
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

		if (date.equals("") || date.contains(Constantes.ENHE) || acentos || caracEspeciales
				|| date.contains(Constantes.ESPACIO) || date.length() < 8 || date.length() > 10) {
			fecha = sdf.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
		} else {
			fecha = sdf.parse(date);
			fechaSql = new java.sql.Date(fecha.getTime());

		}

		persona.setDniP(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.DNIP).toString()).getBytes("UTF-8")), StringUtils.EMPTY));
		persona.setNombreP(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.NOMBREP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setApellidosP(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.APELLIDOSP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setFechaNacP(fechaSql);
		persona.setDireccionP(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.DIRECCIONP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setEmailP(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.EMAILP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setTelefonoP(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.TELEFONOP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		usuario.setDniUsuario(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.DNIP).toString()).getBytes("UTF-8")), StringUtils.EMPTY));
		usuario.setUsuario(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.USUARIO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		usuario.setPasswdUsuario(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.PASSWD_USUARIO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		final RolEntity rol = new RolEntity(2, "usuario", "Contendra a todos los usuarios registrados de la aplicacion",
				0);

		usuario.setRol(rol);
		persona.setUsuario(usuario);

		empresa.setCifEmpresa(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.CIF_EMPRESA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		empresa.setNombreEmpresa(CommonUtilities.coalesce(jsonRegistroVacios.get(Constantes.NOMBRE_EMPRESA).toString(),
				StringUtils.EMPTY));
		empresa.setDireccionEmpresa(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.DIRECCION_EMPRESA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		empresa.setTelefonoEmpresa(CommonUtilities.coalesce(
				new String((jsonRegistroVacios.get(Constantes.TELEFONO_EMPRESA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		registro.setDatosPersona(persona);
		registro.setDatosUsuario(usuario);
		registro.setDatosEmpresa(empresa);

		if (fichero.equals(Constantes.URL_JSON_REGISTRAR_ACCIONES)
				|| fichero.equals(Constantes.URL_JSON_REGISTRAR_DATA)) {
			registro.setSeleccionarEmpresa(CommonUtilities.coalesce(
					new String((jsonRegistroVacios.get(Constantes.SELECCIONAR_EMPRESA).toString()).getBytes("UTF-8")),
					StringUtils.EMPTY));
		}

		return registro;
	}

	public RolEntity generarRol(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonRolVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final RolEntity rol = new RolEntity();

		if (new String((jsonRolVacio.get(Constantes.ROL_ID).toString()).getBytes("UTF-8")).equals(StringUtils.EMPTY)) {
			rol.setIdRol(0);
		} else {
			rol.setIdRol(
					Integer.parseInt(new String((jsonRolVacio.get(Constantes.ROL_ID).toString()).getBytes("UTF-8"))));
		}
		rol.setRolName(CommonUtilities.coalesce(
				new String((jsonRolVacio.get(Constantes.ROL_NAME).toString()).getBytes("UTF-8")), StringUtils.EMPTY));
		rol.setRolDescription(CommonUtilities.coalesce(
				new String((jsonRolVacio.get(Constantes.ROL_DESCRIPTION).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		rol.setBorradoRol(0);

		return rol;

	}

	public AccionEntity generarAccion(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonRolVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final AccionEntity accion = new AccionEntity();

		if (new String((jsonRolVacio.get(Constantes.ACCION_ID).toString()).getBytes("UTF-8"))
				.equals(StringUtils.EMPTY)) {
			accion.setIdAccion(0);
		} else {
			accion.setIdAccion(Integer
					.parseInt(new String((jsonRolVacio.get(Constantes.ACCION_ID).toString()).getBytes("UTF-8"))));
		}
		accion.setNombreAccion(CommonUtilities.coalesce(
				new String((jsonRolVacio.get(Constantes.ACCION_NAME).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		accion.setDescripAccion(CommonUtilities.coalesce(
				new String((jsonRolVacio.get(Constantes.ACCION_DESCRIPTION).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		accion.setBorradoAccion(0);

		return accion;

	}

	public FuncionalidadEntity generarFuncionalidad(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonFuncionalidadVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final FuncionalidadEntity funcionalidad = new FuncionalidadEntity();

		if (new String((jsonFuncionalidadVacio.get(Constantes.FUNCIONALIDAD_ID).toString()).getBytes("UTF-8"))
				.equals(StringUtils.EMPTY)) {
			funcionalidad.setIdFuncionalidad(0);
		} else {
			funcionalidad.setIdFuncionalidad(Integer.parseInt(new String(
					(jsonFuncionalidadVacio.get(Constantes.FUNCIONALIDAD_ID).toString()).getBytes("UTF-8"))));
		}
		funcionalidad.setNombreFuncionalidad(CommonUtilities.coalesce(
				new String((jsonFuncionalidadVacio.get(Constantes.FUNCIONALIDAD_NAME).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		funcionalidad.setDescripFuncionalidad(CommonUtilities.coalesce(new String(
				(jsonFuncionalidadVacio.get(Constantes.FUNCIONALIDAD_DESCRIPTION).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		funcionalidad.setBorradoFuncionalidad(0);

		return funcionalidad;

	}

	public UsuarioEntity generateUsuario(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonUsuarioVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final UsuarioEntity usuario = new UsuarioEntity();

		usuario.setDniUsuario(CommonUtilities.coalesce(
				new String((jsonUsuarioVacio.get(Constantes.USUARIO_DNI).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		usuario.setUsuario(CommonUtilities.coalesce(
				new String((jsonUsuarioVacio.get(Constantes.USUARIO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		usuario.setPasswdUsuario(CommonUtilities.coalesce(
				new String((jsonUsuarioVacio.get(Constantes.PASSWD_USUARIO).toString()).getBytes("UTF-8")),
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
		final String date = CommonUtilities.coalesce(
				new String((jsonPersonaVacios.get(Constantes.FECHANACP).toString()).getBytes("UTF-8")),
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

		if (date.equals("") || date.contains(Constantes.ENHE) || acentos || caracEspeciales
				|| date.contains(Constantes.ESPACIO) || date.length() < 8 || date.length() > 10) {
			fecha = sdf.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
		} else {
			fecha = sdf.parse(date);
			fechaSql = new java.sql.Date(fecha.getTime());

		}

		persona.setDniP(CommonUtilities.coalesce(
				new String((jsonPersonaVacios.get(Constantes.DNIP).toString()).getBytes("UTF-8")), StringUtils.EMPTY));
		persona.setNombreP(CommonUtilities.coalesce(
				new String((jsonPersonaVacios.get(Constantes.NOMBREP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setApellidosP(CommonUtilities.coalesce(
				new String((jsonPersonaVacios.get(Constantes.APELLIDOSP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setFechaNacP(fechaSql);
		persona.setDireccionP(CommonUtilities.coalesce(
				new String((jsonPersonaVacios.get(Constantes.DIRECCIONP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setEmailP(CommonUtilities.coalesce(
				new String((jsonPersonaVacios.get(Constantes.EMAILP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setTelefonoP(CommonUtilities.coalesce(
				new String((jsonPersonaVacios.get(Constantes.TELEFONOP).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		persona.setBorradoP(0);
		persona.setEmpresa(empresa);

		return persona;
	}

	public EmpresaEntity generateEmpresa(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonEmpresaVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final EmpresaEntity empresa = new EmpresaEntity();

		empresa.setCifEmpresa(CommonUtilities.coalesce(
				new String((jsonEmpresaVacio.get(Constantes.CIF_EMPRESA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		empresa.setNombreEmpresa(CommonUtilities.coalesce(
				new String((jsonEmpresaVacio.get(Constantes.NOMBRE_EMPRESA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		empresa.setDireccionEmpresa(CommonUtilities.coalesce(
				new String((jsonEmpresaVacio.get(Constantes.DIRECCION_EMPRESA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		empresa.setTelefonoEmpresa(CommonUtilities.coalesce(
				new String((jsonEmpresaVacio.get(Constantes.TELEFONO_EMPRESA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		empresa.setBorradoEmpresa(0);

		return empresa;

	}

	public NoticiasEntity generarNoticia(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		Boolean acentos = Boolean.FALSE;
		Boolean caracEspeciales = Boolean.FALSE;

		final JSONObject jsonNoticiaVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final NoticiasEntity noticia = new NoticiasEntity();

		if (new String((jsonNoticiaVacio.get(Constantes.NOTICIA_ID).toString()).getBytes("UTF-8"))
				.equals(StringUtils.EMPTY)) {
			noticia.setIdNoticia(0);
		} else {
			noticia.setIdNoticia(Integer.parseInt(CommonUtilities.coalesce(
					new String((jsonNoticiaVacio.get(Constantes.NOTICIA_ID).toString()).getBytes("UTF-8")),
					StringUtils.EMPTY)));
		}
		noticia.setTituloNoticia(CommonUtilities.coalesce(
				new String((jsonNoticiaVacio.get(Constantes.TITULO_NOTICIA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		noticia.setTextoNoticia(CommonUtilities.coalesce(
				new String((jsonNoticiaVacio.get(Constantes.TEXTO_NOTICIA).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String date = CommonUtilities.coalesce(
				new String((jsonNoticiaVacio.get(Constantes.FECHA_NOTICIA).toString()).getBytes("UTF-8")),
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

		if (date.equals("") || date.contains(Constantes.ENHE) || acentos || caracEspeciales
				|| date.contains(Constantes.ESPACIO) || date.length() < 8 || date.length() > 10) {
			fecha = sdf.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
		} else {
			fecha = sdf.parse(date);
			fechaSql = new java.sql.Date(fecha.getTime());

		}

		noticia.setFechaNoticia(fechaSql);

		return noticia;

	}

	public ObjetivoEntity generarObjetivo(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonObjetivoVacio = new Util().getDatosJson(fichero, nombrePrueba);

		final ObjetivoEntity objetivo = new ObjetivoEntity();

		if (new String((jsonObjetivoVacio.get(Constantes.OBJETIVO_ID).toString()).getBytes("UTF-8"))
				.equals(StringUtils.EMPTY)) {
			objetivo.setIdObjetivo(0);
		} else {
			objetivo.setIdObjetivo(Integer.parseInt(
					new String((jsonObjetivoVacio.get(Constantes.OBJETIVO_ID).toString()).getBytes("UTF-8"))));
		}
		objetivo.setNombreObjetivo(CommonUtilities.coalesce(
				new String((jsonObjetivoVacio.get(Constantes.NOMBRE_OBJETIVO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		objetivo.setDescripObjetivo(CommonUtilities.coalesce(
				new String((jsonObjetivoVacio.get(Constantes.DESCRIPCION_OBJETIVO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		objetivo.setBorradoObjetivo(0);

		return objetivo;

	}

	public RespuestaPosibleEntity generarRespuestaPosible(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonRespuestaPosibleVacia = new Util().getDatosJson(fichero, nombrePrueba);

		final RespuestaPosibleEntity respuestaPosible = new RespuestaPosibleEntity();

		boolean acentos = false;
		boolean caracEspeciales = false;

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String date = CommonUtilities.coalesce(new String(
				(jsonRespuestaPosibleVacia.get(Constantes.FECHA_RESPUESTA_POSIBLE).toString()).getBytes("UTF-8")),
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

		if (date.equals("") || date.contains(Constantes.ENHE) || acentos || caracEspeciales
				|| date.contains(Constantes.ESPACIO) || date.length() < 8 || date.length() > 10) {
			fecha = sdf.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
		} else {
			fecha = sdf.parse(date);
			fechaSql = new java.sql.Date(fecha.getTime());

		}

		if (new String((jsonRespuestaPosibleVacia.get(Constantes.RESPUESTA_POSIBLE_ID).toString()).getBytes("UTF-8"))
				.equals(StringUtils.EMPTY)) {
			respuestaPosible.setIdRespuesta(0);
		} else {
			respuestaPosible.setIdRespuesta(Integer.parseInt(new String(
					(jsonRespuestaPosibleVacia.get(Constantes.RESPUESTA_POSIBLE_ID).toString()).getBytes("UTF-8"))));
		}
		respuestaPosible.setTextoRespuesta(CommonUtilities.coalesce(new String(
				(jsonRespuestaPosibleVacia.get(Constantes.TEXTO_RESPUESTA_POSIBLE).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));

		respuestaPosible.setBorradoRespuesta(0);

		return respuestaPosible;

	}

}
