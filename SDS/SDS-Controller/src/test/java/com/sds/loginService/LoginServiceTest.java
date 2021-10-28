package com.sds.loginService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.util.CodeMessageErrors;
import com.sds.util.Util;

import exception.UserNotFound;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginServiceTest {

	private static final String URL_JSON_DATA = "classpath:LoginServiceData.json";
	private static final String USUARIO_CONTRASENA_VACIOS_DATA = "usuarioContrasenaVacios";
	private static final String USUARIO_VACIO_DATA = "usuarioVacio";
	private static final String CONTRASENA_VACIA_DATA = "contrasenaVacia";

	private static final String PASSWD_USUARIO = "passwdUsuario";
	private static final String USUARIO = "usuario";

	@Autowired
	private LoginService loginService;

	@Test
	public void LoginService_loginUserUsuarioContraseñaVacio() throws UserNotFound, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, USUARIO_CONTRASENA_VACIOS_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_BLANK.name());
	}

	@Test
	public void LoginService_loginUserUsuarioVacio() throws UserNotFound, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, USUARIO_VACIO_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_BLANK.name());
	}

	@Test
	public void LoginService_loginUserContrasenaVacia() throws UserNotFound, IOException, ParseException {

		final Login login = generateLogin(URL_JSON_DATA, CONTRASENA_VACIA_DATA);

		final String respuesta = loginService.loginUser(login);

		assertEquals(respuesta, CodeMessageErrors.LOGIN_BLANK.name());
	}

	private Login generateLogin(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonUsuarioContrasenaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Login login = new Login();
		login.setPasswdUsuario(jsonUsuarioContrasenaVacios.get(PASSWD_USUARIO).toString());
		login.setUsuario(jsonUsuarioContrasenaVacios.get(USUARIO).toString());

		return login;

	}

}
