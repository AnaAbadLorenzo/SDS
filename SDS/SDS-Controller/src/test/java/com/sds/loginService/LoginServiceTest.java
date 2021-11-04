package com.sds.loginService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.UsuarioNoEncontrado;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.util.CodeMessageErrors;
import com.sds.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginServiceTest {

	private static final String URL_JSON_DATA = "classpath:LoginServiceData.json";
	private static final String USUARIO_CONTRASENA_VACIOS_DATA = "usuarioContrasenaVacios";
	private static final String USUARIO_VACIO_DATA = "usuarioVacio";
	private static final String CONTRASENA_VACIA_DATA = "contrasenaVacia";
	private static final String USUARIO_NO_EXISTE = "usuarioNoExiste";
	private static final String CONTRASENA_INCORRECTA = "contrasenaIncorrecta";
	private static final String USUARIO_CONTRASENA_CORRECTOS = "usuarioContrasenaCorrectos";

	private static final String PASSWD_USUARIO = "passwdUsuario";
	private static final String USUARIO = "usuario";

	@Autowired
	LoginService loginService;

	@Test
	public void LoginService_loginUserUsuarioContraseñaVacio()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, USUARIO_CONTRASENA_VACIOS_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test
	public void LoginService_loginUserUsuarioVacio()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, USUARIO_VACIO_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test
	public void LoginService_loginUserContrasenaVacia()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, CONTRASENA_VACIA_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test(expected = UsuarioNoEncontrado.class)
	public void LoginService_loginUserUsuarioNoExiste()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, USUARIO_NO_EXISTE);

		loginService.loginUser(login);
	}

	@Test(expected = PasswordIncorrectoException.class)
	public void LoginService_loginUserContrasenaIncorrecta()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, CONTRASENA_INCORRECTA);

		loginService.loginUser(login);
	}

	@Test
	public void LoginService_loginUserLoginOkConToken()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, USUARIO_CONTRASENA_CORRECTOS);

		final String respuesta = loginService.loginUser(login);

		assertNotNull(respuesta);
	}

	private Login generateLogin(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonUsuarioContrasenaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Login login = new Login();
		login.setPasswdUsuario(jsonUsuarioContrasenaVacios.get(PASSWD_USUARIO).toString());
		login.setUsuario(jsonUsuarioContrasenaVacios.get(USUARIO).toString());

		return login;

	}

}
