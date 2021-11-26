package com.sds.service.test.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.sds.model.PersonaEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.Constantes;
import com.sds.service.login.model.Login;
import com.sds.service.registro.model.Registro;
import com.sds.service.util.Util;

public class GenerarJSON {

	public Login generateLogin(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonUsuarioContrasenaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Login login = new Login();
		login.setPasswdUsuario(jsonUsuarioContrasenaVacios.get(Constantes.PASSWD_USUARIO).toString());
		login.setUsuario(jsonUsuarioContrasenaVacios.get(Constantes.USUARIO).toString());

		return login;

	}
	
	public Registro generateRegistro(final String fichero, final String nombrePrueba) throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonRegistroVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Registro registro = new Registro();
		final PersonaEntity persona = new PersonaEntity();
		final UsuarioEntity usuario = new UsuarioEntity();
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = jsonRegistroVacios.get(Constantes.FECHANACP).toString();
		Date fecha = sdf.parse(date);
		
		persona.setDniP(jsonRegistroVacios.get(Constantes.DNIP).toString());
		persona.setNombreP(jsonRegistroVacios.get(Constantes.NOMBREP).toString());
		persona.setApellidosP(jsonRegistroVacios.get(Constantes.APELLIDOSP).toString());
		persona.setFechaNacP(fecha);
		persona.setDireccionP(jsonRegistroVacios.get(Constantes.DIRECCIONP).toString());
		persona.setEmailP(jsonRegistroVacios.get(Constantes.EMAILP).toString());
		persona.setTelefonoP(jsonRegistroVacios.get(Constantes.TELEFONOP).toString());
		
		usuario.setDniUsuario(jsonRegistroVacios.get(Constantes.DNIP).toString());
		usuario.setUsuario(jsonRegistroVacios.get(Constantes.USUARIO).toString());
		usuario.setPasswdUsuario(jsonRegistroVacios.get(Constantes.PASSWD_USUARIO).toString());
		
	
		registro.setDatosPersona(persona);

		return registro;
	}
}
