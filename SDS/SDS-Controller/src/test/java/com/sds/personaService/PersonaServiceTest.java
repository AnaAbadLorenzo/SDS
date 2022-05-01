package com.sds.personaService;

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
import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.Persona;
import com.sds.service.persona.model.PersonaAnadir;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonaServiceTest {

	@Autowired
	PersonaService personaService;

	@Autowired
	UsuarioService usuarioService;

	@Test
	public void PersonaService_buscarPersona() throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.BUSCAR_PERSONA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarPersonaDniVacio() throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.DNIP_VACIO_DATA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarPersonaNombrePVacio()
			throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.NOMBREPERSONA_VACIO_DATA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarPersonaApellidosPVacio()
			throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.APELLIDOSPERSONA_VACIOS_DATA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarPersonaFechaNacPVacio()
			throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.FECHANACPERSONA_VACIA_DATA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarPersonaDireccionPVacio()
			throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.DIRECCIONPERSONA_VACIA_DATA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarPersonaTelefonoPVacio()
			throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.TELEFONOPERSONA_VACIO_DATA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarPersonaEmailPVacio() throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.EMAIL_VACIO_DATA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarPersonaParametrosVacios()
			throws IOException, ParseException, java.text.ParseException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.PARAMETROS_PERSONA_VACIOS_DATA);
		final PersonaEntity personaEntity = persona.getPersona();

		final ReturnBusquedas<PersonaEntity> personaEncontrada = personaService.buscarPersona(personaEntity.getDniP(),
				personaEntity.getNombreP(), personaEntity.getApellidosP(), personaEntity.getFechaNacP(),
				personaEntity.getDireccionP(), personaEntity.getTelefonoP(), personaEntity.getEmailP(), 0, 1);

		assertNotNull(personaEncontrada.getListaBusquedas());
	}

	@Test
	public void PersonaService_buscarTodos() throws IOException, ParseException {

		final ReturnBusquedas<PersonaEntity> personas = personaService.buscarTodos(0, 3);

		assertNotNull(personas.getListaBusquedas());
	}

	@Test
	public void PersonaService_guardarPersona() throws IOException, ParseException, java.text.ParseException,
			PersonaYaExisteException, UsuarioYaExisteException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, UsuarioNoEncontradoException, PersonaNoExisteException,
			UsuarioAsociadoPersonaException, EmpresaNoEncontradaException, EmpresaYaExisteException {
		final PersonaAnadir persona = generatePersonaAñadir(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.GUARDAR_PERSONA);

		String respuesta = StringUtils.EMPTY;

		respuesta = personaService.añadirPersona(persona);

		assertEquals(respuesta, Constantes.OK);

		final ReturnBusquedas<PersonaEntity> personaBD = personaService.buscarPersona(
				persona.getPersonaEntity().getDniP(), persona.getPersonaEntity().getNombreP(),
				persona.getPersonaEntity().getApellidosP(), persona.getPersonaEntity().getFechaNacP(),
				persona.getPersonaEntity().getDireccionP(), persona.getPersonaEntity().getTelefonoP(),
				persona.getPersonaEntity().getEmailP(), 0, 1);

		final ReturnBusquedas<UsuarioEntity> usuarioBD = usuarioService
				.buscarUsuario(persona.getUsuarioEntity().getUsuario(), persona.getUsuarioEntity().getRol(), 0, 1);

		usuarioService.deleteUsuario(usuarioBD.getListaBusquedas().get(0));
		personaService.deletePersona(personaBD.getListaBusquedas().get(0));
	}

	@Test
	public void PersonaService_guardarPersonaVacia() throws IOException, ParseException, java.text.ParseException,
			PersonaYaExisteException, UsuarioYaExisteException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, EmpresaNoEncontradaException, EmpresaYaExisteException {

		final PersonaAnadir persona = generatePersonaAñadir(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.PARAMETROS_PERSONA_VACIOS_DATA);
		String respuesta = StringUtils.EMPTY;

		respuesta = personaService.añadirPersona(persona);

		assertEquals(respuesta, CodeMessageErrors.PERSONA_VACIO.name());
	}

	@Test(expected = PersonaYaExisteException.class)
	public void PersonaService_guardarPersonaYaExiste() throws IOException, ParseException, java.text.ParseException,
			PersonaYaExisteException, UsuarioYaExisteException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, EmpresaNoEncontradaException, EmpresaYaExisteException {

		final PersonaAnadir persona = generatePersonaAñadir(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.PERSONA_YA_EXISTE);

		personaService.añadirPersona(persona);

	}

	@Test(expected = UsuarioYaExisteException.class)
	public void PersonaService_guardarPersonaUsuarioNoExiste()
			throws IOException, ParseException, java.text.ParseException, PersonaYaExisteException,
			UsuarioYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaNoEncontradaException, EmpresaYaExisteException {

		final PersonaAnadir persona = generatePersonaAñadir(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.USUARIO_YA_EXISTE);

		personaService.añadirPersona(persona);

	}

	@Test
	public void PersonaService_eliminarPersonaCorrecto() throws IOException, ParseException, java.text.ParseException,
			PersonaYaExisteException, UsuarioYaExisteException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, PersonaNoExisteException, UsuarioAsociadoPersonaException,
			UsuarioNoEncontradoException, EmpresaNoEncontradaException, EmpresaYaExisteException {

		UsuarioEntity user = new UsuarioEntity();

		final PersonaAnadir persona = generatePersonaAñadir(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.GUARDAR_PERSONA);

		personaService.añadirPersona(persona);

		final Persona personaEliminar = new Persona(persona.getUsuario(), persona.getPersonaEntity());
		final String respuesta = personaService.eliminarPersona(personaEliminar);

		assertEquals(respuesta, Constantes.OK);

		final ReturnBusquedas<UsuarioEntity> usuario = usuarioService.buscarUsuariosEliminados(0, 5);

		for (int i = 0; i < usuario.getListaBusquedas().size(); i++) {
			if (usuario.getListaBusquedas().get(i).getDniUsuario().equals(persona.getPersonaEntity().getDniP())) {
				user = usuario.getListaBusquedas().get(i);
			}
		}

		usuarioService.deleteUsuario(user);
		personaService.deletePersona(persona.getPersonaEntity());

	}

	@Test(expected = PersonaNoExisteException.class)
	public void PersonaService_eliminarPersonaNoExiste()
			throws IOException, ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException,
			PersonaNoExisteException, UsuarioAsociadoPersonaException, LogAccionesNoGuardadoException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.PERSONA_NO_EXISTE);

		personaService.eliminarPersona(persona);

	}

	@Test
	public void PersonaService_modificarPersonaCorrecto() throws IOException, ParseException, java.text.ParseException,
			PersonaYaExisteException, UsuarioYaExisteException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, PersonaNoExisteException, UsuarioNoEncontradoException,
			UsuarioAsociadoPersonaException, EmpresaNoEncontradaException, EmpresaYaExisteException {

		final PersonaAnadir persona = generatePersonaAñadir(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.GUARDAR_PERSONA);

		personaService.añadirPersona(persona);

		final Persona personaModificar = new Persona(persona.getUsuario(), persona.getPersonaEntity());
		personaModificar.getPersona().setApellidosP("Apellidos_Modificados");

		final String respuesta = personaService.modificarPersona(personaModificar);

		assertEquals(respuesta, Constantes.OK);

		final ReturnBusquedas<UsuarioEntity> usuario = usuarioService
				.buscarUsuario(persona.getUsuarioEntity().getUsuario(), persona.getUsuarioEntity().getRol(), 0, 1);

		usuarioService.deleteUsuario(usuario.getListaBusquedas().get(0));
		personaService.deletePersona(persona.getPersonaEntity());

	}

	@Test(expected = PersonaNoExisteException.class)
	public void PersonaService_modificarPersonaNoExiste() throws IOException, ParseException, java.text.ParseException,
			LogExcepcionesNoGuardadoException, PersonaNoExisteException, LogAccionesNoGuardadoException {

		final Persona persona = generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.PERSONA_NO_EXISTE);

		personaService.modificarPersona(persona);

	}

	@Test
	public void PersonaService_buscarTodosEliminados() throws IOException, ParseException {
		final ReturnBusquedas<PersonaEntity> personasEliminadas = personaService.buscarPersonasEliminadas(0, 3);

		assertNotNull(personasEliminadas.getListaBusquedas());
	}

	private Persona generatePersona(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonPersona = new Util().getDatosJson(fichero, nombrePrueba);

		final Persona persona = new Persona();
		final PersonaEntity personaEntity = new PersonaEntity();

		final EmpresaEntity empresaEntity = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);

		persona.setUsuario(CommonUtilities.coalesce(jsonPersona.get(Constantes.USER).toString(), StringUtils.EMPTY));

		personaEntity.setDniP(CommonUtilities.coalesce(jsonPersona.get(Constantes.DNIP).toString(), StringUtils.EMPTY));
		personaEntity.setNombreP(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.NOMBREP).toString(), StringUtils.EMPTY));
		personaEntity.setApellidosP(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.APELLIDOSP).toString(), StringUtils.EMPTY));

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		final Date date;
		if (jsonPersona.get(Constantes.FECHANACP).toString().equals(StringUtils.EMPTY)) {
			date = formato.parse("0000-00-00");
		} else {
			date = formato.parse(
					CommonUtilities.coalesce(jsonPersona.get(Constantes.FECHANACP).toString(), StringUtils.EMPTY));
		}

		personaEntity.setFechaNacP(date);

		personaEntity.setDireccionP(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.DIRECCIONP).toString(), StringUtils.EMPTY));
		personaEntity
				.setEmailP(CommonUtilities.coalesce(jsonPersona.get(Constantes.EMAILP).toString(), StringUtils.EMPTY));
		personaEntity.setTelefonoP(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.TELEFONOP).toString(), StringUtils.EMPTY));

		personaEntity.setEmpresa(empresaEntity);
		persona.setPersona(personaEntity);

		return persona;

	}

	private PersonaAnadir generatePersonaAñadir(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonPersona = new Util().getDatosJson(fichero, nombrePrueba);

		final PersonaAnadir persona = new PersonaAnadir();
		final PersonaEntity personaEntity = new PersonaEntity();

		final UsuarioEntity usuarioEntity = new UsuarioEntity();

		final EmpresaEntity empresaEntity = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);
		final RolEntity rolEntity = new RolEntity(2, "usuario",
				"Contendrá a todos los usuarios registrados de la aplicación", 0);

		persona.setUsuario(CommonUtilities.coalesce(jsonPersona.get(Constantes.USER).toString(), StringUtils.EMPTY));

		personaEntity.setDniP(CommonUtilities.coalesce(jsonPersona.get(Constantes.DNIP).toString(), StringUtils.EMPTY));
		personaEntity.setNombreP(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.NOMBREP).toString(), StringUtils.EMPTY));
		personaEntity.setApellidosP(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.APELLIDOSP).toString(), StringUtils.EMPTY));

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		final Date date;
		if (jsonPersona.get(Constantes.FECHANACP).toString().equals(StringUtils.EMPTY)) {
			date = formato.parse("0000-00-00");
		} else {
			date = formato.parse(
					CommonUtilities.coalesce(jsonPersona.get(Constantes.FECHANACP).toString(), StringUtils.EMPTY));
		}

		personaEntity.setFechaNacP(date);

		personaEntity.setDireccionP(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.DIRECCIONP).toString(), StringUtils.EMPTY));
		personaEntity
				.setEmailP(CommonUtilities.coalesce(jsonPersona.get(Constantes.EMAILP).toString(), StringUtils.EMPTY));
		personaEntity.setTelefonoP(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.TELEFONOP).toString(), StringUtils.EMPTY));
		personaEntity.setBorradoP(0);

		personaEntity.setEmpresa(empresaEntity);
		persona.setPersonaEntity(personaEntity);

		usuarioEntity.setDniUsuario(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.USUARIO_DNI).toString(), StringUtils.EMPTY));
		usuarioEntity.setUsuario(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		usuarioEntity.setPasswdUsuario(
				CommonUtilities.coalesce(jsonPersona.get(Constantes.PASSWD_USUARIO).toString(), StringUtils.EMPTY));

		usuarioEntity.setBorradoUsuario(0);
		usuarioEntity.setRol(rolEntity);

		persona.setUsuarioEntity(usuarioEntity);

		return persona;

	}

}