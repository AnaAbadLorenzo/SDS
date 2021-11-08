package com.sds.service.test.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.sds.service.common.Constantes;
import com.sds.service.login.model.Login;
import com.sds.service.test.TestLoginService;
import com.sds.service.test.impl.acciones.TestAccionesLogin;
import com.sds.service.test.impl.atributos.TestAtributoContrasenaLogin;
import com.sds.service.test.impl.atributos.TestAtributoUsuarioLogin;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.GenerarJSON;

@Service(value = "testLoginService")
public class TestLoginServiceImpl implements TestLoginService {

	private final TestAtributoUsuarioLogin testAtributoUsuarioLogin;
	private final TestAtributoContrasenaLogin testAtributoContrasenaLogin;
	private final TestAccionesLogin testAccionesLogin;
	private final GenerarJSON generarJSON;

	public TestLoginServiceImpl() {
		testAtributoUsuarioLogin = new TestAtributoUsuarioLogin();
		testAtributoContrasenaLogin = new TestAtributoContrasenaLogin();
		testAccionesLogin = new TestAccionesLogin();
		generarJSON = new GenerarJSON();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuario() throws IOException, ParseException {

		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList();

		final Login datosEntradaLoginUsuarioVacio = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_VACIO_DATA);
		final Login datosEntradaLoginUsuarioAlfanumericoEnhe = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ENHE_DATA);
		final Login datosEntradaLoginUsuarioAlfanumericoAcentos = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ACENTOS_DATA);
		final Login datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO,
				Constantes.USUARIO_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final Login datosEntradaLoginUsuarioAlfanumericoEspacios = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ESPACIOS_DATA);
		final Login datosEntradaLoginUsuarioAlfanumericoMenor3 = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_MENOR_3_DATA);
		final Login datosEntradaLoginUsuarioAlfanumericoMayor45 = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_MAYOR_45_DATA);
		final Login datosEntradaLoginUsuarioAlfabetico = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFABETICO_DATA);
		final Login datosEntradaLoginUsuarioAlfanumerico = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_DATA);
		final Login datosEntradaLoginUsuarioNumerico = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_USUARIO, Constantes.USUARIO_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoUsuarioLogin.getTestLoginUsuarioVacio(datosEntradaLoginUsuarioVacio));
		datosPruebaAtributos.add(
				testAtributoUsuarioLogin.getTestLoginUsuarioAlfanumericoEnhe(datosEntradaLoginUsuarioAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoUsuarioLogin
				.getTestLoginUsuarioAlfanumericoAcentos(datosEntradaLoginUsuarioAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoUsuarioLogin.getTestLoginUsuarioAlfanumericoCaracteresEspeciales(
				datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoUsuarioLogin
				.getTestLoginUsuarioAlfanumericoEspacios(datosEntradaLoginUsuarioAlfanumericoEspacios));
		datosPruebaAtributos.add(testAtributoUsuarioLogin
				.getTestLoginUsuarioAlfanumericoMenor3(datosEntradaLoginUsuarioAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoUsuarioLogin
				.getTestLoginUsuarioAlfanumericoMayor45(datosEntradaLoginUsuarioAlfanumericoMayor45));
		datosPruebaAtributos.add(
				testAtributoUsuarioLogin.getTestLoginUsuarioCorrectoAlfanumerico(datosEntradaLoginUsuarioAlfabetico));
		datosPruebaAtributos.add(
				testAtributoUsuarioLogin.getTestLoginUsuarioCorrectoAlfanumerico(datosEntradaLoginUsuarioAlfanumerico));
		datosPruebaAtributos.add(
				testAtributoUsuarioLogin.getTestLoginUsuarioCorrectoAlfanumerico(datosEntradaLoginUsuarioNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoContrasena() throws IOException, ParseException {

		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList();

		final Login datosEntradaLoginContrasenaVacio = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_VACIA_DATA);
		final Login datosEntradaLoginContrasenaAlfanumericoEnhe = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ENHE_DATA);
		final Login datosEntradaLoginContrasenaAlfanumericoAcentos = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ACENTOS_DATA);
		final Login datosEntradaLoginContrasenaAlfanumericoCaracteresEspeciales = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA,
				Constantes.CONTRASENA_ALFANUMERICA_CARACTERES_ESPECIALES_DATA);
		final Login datosEntradaLoginContrasenaAlfanumericoEspacios = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ESPACIOS_DATA);
		final Login datosEntradaLoginContrasenaAlfanumericoMenor3 = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_MENOR_3_DATA);
		final Login datosEntradaLoginContrasenaAlfanumericoMayor45 = generarJSON.generateLogin(
				Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_MAYOR_45_DATA);
		final Login datosEntradaLoginContrasenaAlfabetico = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFABETICA_DATA);
		final Login datosEntradaLoginContrasenaAlfanumerico = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_DATA);
		final Login datosEntradaLoginContrasenaNumerico = generarJSON
				.generateLogin(Constantes.URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_NUMERICA_DATA);

		datosPruebaAtributos
				.add(testAtributoContrasenaLogin.getTestLoginContrasenaVacio(datosEntradaLoginContrasenaVacio));
		datosPruebaAtributos.add(testAtributoContrasenaLogin
				.getTestLoginContrasenaAlfanumericoEnhe(datosEntradaLoginContrasenaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoContrasenaLogin
				.getTestLoginContrasenaAlfanumericoAcentos(datosEntradaLoginContrasenaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoContrasenaLogin.getTestLoginContrasenaAlfanumericoCaracteresEspeciales(
				datosEntradaLoginContrasenaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoContrasenaLogin
				.getTestLoginContrasenaAlfanumericoEspacios(datosEntradaLoginContrasenaAlfanumericoEspacios));
		datosPruebaAtributos.add(testAtributoContrasenaLogin
				.getTestLoginContrasenaAlfanumericoMenor3(datosEntradaLoginContrasenaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoContrasenaLogin
				.getTestLoginContrasenaAlfanumericoMayor45(datosEntradaLoginContrasenaAlfanumericoMayor45));
		datosPruebaAtributos.add(testAtributoContrasenaLogin
				.getTestLoginContrasenaCorrectoAlfanumerico(datosEntradaLoginContrasenaAlfabetico));
		datosPruebaAtributos.add(testAtributoContrasenaLogin
				.getTestLoginContrasenaCorrectoAlfanumerico(datosEntradaLoginContrasenaAlfanumerico));
		datosPruebaAtributos.add(testAtributoContrasenaLogin
				.getTestLoginContrasenaCorrectoAlfanumerico(datosEntradaLoginContrasenaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesLogin() throws IOException, ParseException {

		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		final Login datosEntradaLoginUsuarioNoExiste = generarJSON.generateLogin(Constantes.URL_JSON_LOGIN_ACCIONES,
				Constantes.USUARIO_NO_EXISTE);
//		final Login datosEntradaLoginContrasenaIncorrecta = generarJSON
//				.generateLogin(Constantes.URL_JSON_LOGIN_ACCIONES, Constantes.CONTRASENA_INCORRECTA);
//		final Login datosEntradaLoginCorrecto = generarJSON.generateLogin(Constantes.URL_JSON_LOGIN_ACCIONES,
//				Constantes.LOGIN_CORRECTO);

		datosPruebaAcciones.add(testAccionesLogin.getTestLoginUsuarioNoExiste(datosEntradaLoginUsuarioNoExiste));

		return datosPruebaAcciones;
	}

}
