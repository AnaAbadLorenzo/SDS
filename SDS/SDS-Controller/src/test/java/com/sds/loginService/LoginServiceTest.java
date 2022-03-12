package com.sds.loginService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

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
import com.sds.service.common.Constantes;
import com.sds.service.exception.EmailNoEncontradoException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.MailNoEnviadoException;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.login.model.LoginRol;
import com.sds.service.login.model.RecuperarPass;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.PersonaAnadir;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginServiceTest {

	@Autowired
	LoginService loginService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PersonaService personaService;

	@Test
	public void LoginService_loginUserUsuarioContraseñaVacio()
			throws UsuarioNoEncontradoException, PasswordIncorrectoException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.USUARIO_CONTRASENA_VACIOS_DATA);

		final LoginRol respuesta = loginService.loginUser(login);

		assertEquals(respuesta.getTokenUsuario(), CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test
	public void LoginService_loginUserUsuarioVacio() throws UsuarioNoEncontradoException, PasswordIncorrectoException,
			IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.USUARIO_VACIO_DATA);

		final LoginRol respuesta = loginService.loginUser(login);

		assertEquals(respuesta.getTokenUsuario(), CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test
	public void LoginService_loginUserContrasenaVacia()
			throws UsuarioNoEncontradoException, PasswordIncorrectoException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.CONTRASENA_VACIA_DATA);

		final LoginRol respuesta = loginService.loginUser(login);

		assertEquals(respuesta.getTokenUsuario(), CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test(expected = UsuarioNoEncontradoException.class)
	public void LoginService_loginUserUsuarioNoExiste()
			throws UsuarioNoEncontradoException, PasswordIncorrectoException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.USUARIO_NO_EXISTE);

		loginService.loginUser(login);
	}

	@Test(expected = PasswordIncorrectoException.class)
	public void LoginService_loginUserContrasenaIncorrecta()
			throws UsuarioNoEncontradoException, PasswordIncorrectoException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.CONTRASENA_INCORRECTA);

		loginService.loginUser(login);
	}

	@Test
	public void LoginService_loginUserLoginOkConToken()
			throws UsuarioNoEncontradoException, PasswordIncorrectoException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.USUARIO_CONTRASENA_CORRECTOS);

		final LoginRol respuesta = loginService.loginUser(login);

		assertNotEquals(respuesta.getRolUsuario(), StringUtils.EMPTY);
	}

	@Test(expected = EmailNoEncontradoException.class)
	public void LoginService_recuperarPassEmailNoEncontrado()
			throws IOException, ParseException, UsuarioNoEncontradoException, MessagingException,
			LogExcepcionesNoGuardadoException, PersonaNoExisteException, EmailNoEncontradoException,
			LogAccionesNoGuardadoException, MailNoEnviadoException, java.text.ParseException, PersonaYaExisteException,
			UsuarioYaExisteException, UsuarioAsociadoPersonaException {

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = format.parse("2000-12-13");

		final EmpresaEntity empresa = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);
		final PersonaEntity persona = new PersonaEntity("11111111A", "Persona", "Apellidos", date, "Direccion",
				"977777777", "anaa1312@gmail.com", 0, empresa);
		final RolEntity rol = new RolEntity(2, "usuario", "Contendrá a todos los usuarios registrados de la aplicación",
				0);
		final UsuarioEntity usuario = new UsuarioEntity("11111111A", "usuario", "usuario", 0, rol);

		final PersonaAnadir personaAñadir = new PersonaAnadir(persona, usuario);

		personaAñadir.setUsuario("usuario");

		personaService.añadirPersona(personaAñadir);

		final RecuperarPass recuperarPass = generateRecuperarPass(Constantes.URL_JSON_RECUPERARPASS_DATA,
				Constantes.EMAIL_NO_ENCONTRADO);

		try {
			loginService.recuperarPasswdUsuario(recuperarPass.getUsuario(), recuperarPass.getEmailUsuario(), "ES");
		} catch (final EmailNoEncontradoException exception) {
			throw new EmailNoEncontradoException(CodeMessageErrors.EMAIL_NO_ENCONTRADO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.EMAIL_NO_ENCONTRADO_EXCEPTION.getCodigo()));
		} finally {
			usuarioService.deleteUsuario(usuario);
			personaService.deletePersona(persona);
		}

	}

	@Test(expected = UsuarioNoEncontradoException.class)
	public void LoginService_recuperarPassUsuarioNoEncontrado()
			throws IOException, ParseException, UsuarioNoEncontradoException, MessagingException,
			LogExcepcionesNoGuardadoException, PersonaNoExisteException, EmailNoEncontradoException,
			LogAccionesNoGuardadoException, MailNoEnviadoException, java.text.ParseException, PersonaYaExisteException,
			UsuarioYaExisteException, UsuarioAsociadoPersonaException {

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = format.parse("2000-12-13");

		final EmpresaEntity empresa = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);
		final PersonaEntity persona = new PersonaEntity("11111111A", "Persona", "Apellidos", date, "Direccion",
				"977777777", "anaa1312@gmail.com", 0, empresa);
		final RolEntity rol = new RolEntity(2, "usuario", "Contendrá a todos los usuarios registrados de la aplicación",
				0);
		final UsuarioEntity usuario = new UsuarioEntity("11111111A", "usuario", "usuario", 0, rol);

		final PersonaAnadir personaAñadir = new PersonaAnadir(persona, usuario);

		personaAñadir.setUsuario("usuario");

		personaService.añadirPersona(personaAñadir);

		final RecuperarPass recuperarPass = generateRecuperarPass(Constantes.URL_JSON_RECUPERARPASS_DATA,
				Constantes.USUARIO_NO_EXISTE);

		try {
			loginService.recuperarPasswdUsuario(recuperarPass.getUsuario(), recuperarPass.getEmailUsuario(), "ES");
		} catch (final UsuarioNoEncontradoException exception) {
			throw new UsuarioNoEncontradoException(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
		} finally {
			usuarioService.deleteUsuario(usuario);
			personaService.deletePersona(persona);
		}

	}

	@Test
	public void LoginService_recuperarPassCorrecto() throws IOException, ParseException, UsuarioNoEncontradoException,
			MessagingException, LogExcepcionesNoGuardadoException, PersonaNoExisteException, EmailNoEncontradoException,
			LogAccionesNoGuardadoException, MailNoEnviadoException, java.text.ParseException, PersonaYaExisteException,
			UsuarioYaExisteException, UsuarioAsociadoPersonaException {

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = format.parse("2000-12-13");

		final EmpresaEntity empresa = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);
		final PersonaEntity persona = new PersonaEntity("11111111A", "Persona", "Apellidos", date, "Direccion",
				"977777777", "anaa1312@gmail.com", 0, empresa);
		final RolEntity rol = new RolEntity(2, "usuario", "Contendrá a todos los usuarios registrados de la aplicación",
				0);
		final UsuarioEntity usuario = new UsuarioEntity("11111111A", "usuario", "usuario", 0, rol);

		final PersonaAnadir personaAñadir = new PersonaAnadir(persona, usuario);

		personaAñadir.setUsuario("usuario");

		personaService.añadirPersona(personaAñadir);

		final RecuperarPass recuperarPass = generateRecuperarPass(Constantes.URL_JSON_RECUPERARPASS_DATA,
				Constantes.RECUPERAR_PASS_CORRECTO);

		final String respuesta = loginService.recuperarPasswdUsuario(recuperarPass.getUsuario(),
				recuperarPass.getEmailUsuario(), "ES");

		assertNotNull(respuesta);

		usuarioService.deleteUsuario(usuario);
		personaService.deletePersona(persona);
	}

	private Login generateLogin(final String fichero, final String nombrePrueba) throws IOException, ParseException {

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

}
