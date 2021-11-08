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
import com.sds.service.common.Constantes;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.UsuarioNoEncontrado;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginServiceTest {

	@Autowired
	LoginService loginService;

	@Test
	public void LoginService_loginUserUsuarioContraseñaVacio()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.USUARIO_CONTRASENA_VACIOS_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test
	public void LoginService_loginUserUsuarioVacio()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.USUARIO_VACIO_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test
	public void LoginService_loginUserContrasenaVacia()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.CONTRASENA_VACIA_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_VACIO.name());
	}

	@Test(expected = UsuarioNoEncontrado.class)
	public void LoginService_loginUserUsuarioNoExiste()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.USUARIO_NO_EXISTE);

		loginService.loginUser(login);
	}

	@Test(expected = PasswordIncorrectoException.class)
	public void LoginService_loginUserContrasenaIncorrecta()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.CONTRASENA_INCORRECTA);

		loginService.loginUser(login);
	}

	@Test
	public void LoginService_loginUserLoginOkConToken()
			throws UsuarioNoEncontrado, PasswordIncorrectoException, IOException, ParseException {

		final Login login = generateLogin(Constantes.URL_JSON_LOGIN_DATA, Constantes.USUARIO_CONTRASENA_CORRECTOS);

		final String respuesta = loginService.loginUser(login);

		assertNotNull(respuesta);
	}

	private Login generateLogin(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonUsuarioContrasenaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Login login = new Login();
		login.setPasswdUsuario(jsonUsuarioContrasenaVacios.get(Constantes.PASSWD_USUARIO).toString());
		login.setUsuario(jsonUsuarioContrasenaVacios.get(Constantes.USUARIO).toString());

		return login;

	}

}
