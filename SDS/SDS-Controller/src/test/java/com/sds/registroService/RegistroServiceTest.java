package com.sds.registroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.controller.common.CommonUtilities;
import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.Constantes;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.registro.RegistroService;
import com.sds.service.registro.model.Registro;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistroServiceTest {

	@Autowired
	RegistroService registroService;

	@Test
	public void RegistroService_registroPersonaUsuarioEmpresaVacio() throws IOException, ParseException,
			UsuarioYaExisteException, PersonaYaExisteException, EmpresaYaExisteException, java.text.ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA,
				Constantes.REGISTRO_PERSONA_USUARIO_EMPRESA_VACIOS_DATA);

		final String respuesta = registroService.registrar(registro);

		assertEquals(respuesta, CodeMessageErrors.REGISTRO_VACIO.name());
	}

	@Test
	public void RegistroService_registroPersonaVacio() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException, java.text.ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.PERSONA_VACIO_DATA);

		final String respuesta = registroService.registrar(registro);

		assertEquals(respuesta, CodeMessageErrors.REGISTRO_VACIO.name());
	}

	@Test
	public void RegistroService_registroUsuarioVacio() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException, java.text.ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.USUARIO_VACIO_DATA);

		final String respuesta = registroService.registrar(registro);

		assertEquals(respuesta, CodeMessageErrors.REGISTRO_VACIO.name());
	}

	@Test(expected = PersonaYaExisteException.class)
	public void RegistroService_registroPersonaYaExiste() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException, java.text.ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.PERSONA_YA_EXISTE);

		registroService.registrar(registro);
	}

	@Test(expected = EmpresaYaExisteException.class)
	public void RegistroService_registroEmpresaYaExiste() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException, java.text.ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.EMPRESA_YA_EXISTE);

		registroService.registrar(registro);
	}

	@Test(expected = UsuarioYaExisteException.class)
	public void RegistroService_registroUsuarioYaExiste() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException, java.text.ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.USUARIO_YA_EXISTE);

		registroService.registrar(registro);
	}

	@Test
	public void RegistroService_registroOk() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException, java.text.ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA,
				Constantes.REGISTRO_PERSONA_USUARIO_EMPRESA_CORRECTOS);

		final String respuesta = registroService.registrar(registro);

		assertNotNull(respuesta);
	}

	private Registro generateRegistro(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonCargaDatos = new Util().getDatosJson(fichero, nombrePrueba);

		final Registro registro = new Registro();
		final PersonaEntity persona = new PersonaEntity();
		final UsuarioEntity usuario = new UsuarioEntity();
		final EmpresaEntity empresa = new EmpresaEntity();

		persona.setDniP(CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.DNIP).toString(), StringUtils.EMPTY));
		persona.setNombreP(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.NOMBREP).toString(), StringUtils.EMPTY));
		persona.setApellidosP(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.APELLIDOSP).toString(), StringUtils.EMPTY));

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");

		final Date date;
		if (jsonCargaDatos.get(Constantes.FECHANACP).toString().equals(StringUtils.EMPTY)) {
			date = formato.parse("0000-00-00");
		} else {
			date = formato.parse(
					CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.FECHANACP).toString(), StringUtils.EMPTY));
		}

		persona.setFechaNacP(date);

		persona.setDireccionP(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.DIRECCIONP).toString(), StringUtils.EMPTY));
		persona.setEmailP(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.EMAILP).toString(), StringUtils.EMPTY));
		persona.setTelefonoP(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.TELEFONOP).toString(), StringUtils.EMPTY));

		usuario.setUsuario(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		usuario.setPasswdUsuario(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.PASSWD_USUARIO).toString(), StringUtils.EMPTY));

		empresa.setCifEmpresa(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.CIF_EMPRESA).toString(), StringUtils.EMPTY));
		empresa.setNombreEmpresa(
				CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.NOMBRE_EMPRESA).toString(), StringUtils.EMPTY));
		empresa.setDireccionEmpresa(CommonUtilities
				.coalesce(jsonCargaDatos.get(Constantes.DIRECCION_EMPRESA).toString(), StringUtils.EMPTY));
		empresa.setTelefonoEmpresa(CommonUtilities.coalesce(jsonCargaDatos.get(Constantes.TELEFONO_EMPRESA).toString(),
				StringUtils.EMPTY));

		registro.setDatosPersona(persona);
		registro.setDatosUsuario(usuario);
		registro.setDatosEmpresa(empresa);

		return registro;

	}

}
