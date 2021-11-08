package com.sds.service.test.util;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.sds.service.common.Constantes;
import com.sds.service.login.model.Login;
import com.sds.service.util.Util;

public class GenerarJSON {

	public Login generateLogin(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonUsuarioContrasenaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Login login = new Login();
		login.setPasswdUsuario(jsonUsuarioContrasenaVacios.get(Constantes.PASSWD_USUARIO).toString());
		login.setUsuario(jsonUsuarioContrasenaVacios.get(Constantes.USUARIO).toString());

		return login;

	}
}
