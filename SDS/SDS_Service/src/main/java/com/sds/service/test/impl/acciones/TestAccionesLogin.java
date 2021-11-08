package com.sds.service.test.impl.acciones;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.UsuarioNoEncontrado;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.util.CodeMessageErrors;

public class TestAccionesLogin {

	@Autowired
	LoginService loginService;

	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;

	public TestAccionesLogin() {
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
	}

	public DatosPruebaAcciones getTestLoginUsuarioNoExiste(final Login datosEntradaLoginUsuarioNoExiste) {

		final String resultadoObtenido = existeUsuario(datosEntradaLoginUsuarioNoExiste);

		final String resultadoEsperado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, Constantes.USUARIO);

	}

	private String existeUsuario(final Login login) {

		String resultado = StringUtils.EMPTY;

		try {
			final String resultadoUsuario = loginService.loginUser(login);

			if (CodeMessageErrors.LOGIN_VACIO.name().equals(resultadoUsuario)) {
				resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
			}
		} catch (final UsuarioNoEncontrado userNotFound) {
			resultado = CodigosMensajes.LOGIN_CORRECTO + " - " + Mensajes.LOGIN_CORRECTO;
		} catch (final PasswordIncorrectoException passwordIncorrecto) {
			resultado = CodigosMensajes.LOGIN_CONTRASENA_INCORRECTO + " - " + Mensajes.CONTRASENA_INCORRECTA;
		}

		return resultado;
	}

}
