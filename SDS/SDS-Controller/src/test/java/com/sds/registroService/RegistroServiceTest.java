package com.sds.registroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.service.common.Constantes;
import com.sds.service.exception.EmpresaYaExisteException;
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
			UsuarioYaExisteException, PersonaYaExisteException, EmpresaYaExisteException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA,
				Constantes.REGISTRO_PERSONA_USUARIO_EMPRESA_VACIOS_DATA);

		final String respuesta = registroService.registrar(registro);

		assertEquals(respuesta, CodeMessageErrors.REGISTRO_VACIO.name());
	}

	@Test
	public void RegistroService_registroPersonaVacio() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.PERSONA_VACIO_DATA);

		final String respuesta = registroService.registrar(registro);

		assertEquals(respuesta, CodeMessageErrors.REGISTRO_VACIO.name());
	}

	@Test
	public void RegistroService_registroUsuarioVacio() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.USUARIO_VACIO_DATA);

		final String respuesta = registroService.registrar(registro);

		assertEquals(respuesta, CodeMessageErrors.REGISTRO_VACIO.name());
	}

	@Test(expected = PersonaYaExisteException.class)
	public void RegistroService_registroPersonaYaExiste() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.PERSONA_YA_EXISTE);

		registroService.registrar(registro);
	}

	@Test(expected = UsuarioYaExisteException.class)
	public void RegistroService_registroUsuarioYaExiste() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.USUARIO_YA_EXISTE);

		registroService.registrar(registro);
	}

	@Test(expected = EmpresaYaExisteException.class)
	public void RegistroService_registroEmpresaYaExiste() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA, Constantes.EMPRESA_YA_EXISTE);

		registroService.registrar(registro);
	}

	@Test
	public void RegistroService_registroOk() throws IOException, ParseException, UsuarioYaExisteException,
			PersonaYaExisteException, EmpresaYaExisteException {

		final Registro registro = generateRegistro(Constantes.URL_JSON_REGISTRAR_DATA,
				Constantes.REGISTRO_PERSONA_USUARIO_EMPRESA_CORRECTOS);

		final String respuesta = registroService.registrar(registro);

		assertNotNull(respuesta);
	}

	private Registro generateRegistro(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonPersonaUsuarioEmpresaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Registro registro = new Registro();
		registro.getDatosPersona().setDniP(jsonPersonaUsuarioEmpresaVacios.get(Constantes.DNIP).toString());
		registro.getDatosPersona().setNombreP(jsonPersonaUsuarioEmpresaVacios.get(Constantes.NOMBREP).toString());
		registro.getDatosPersona().setApellidosP(jsonPersonaUsuarioEmpresaVacios.get(Constantes.APELLIDOSP).toString());

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		try {
			date = formato.parse(jsonPersonaUsuarioEmpresaVacios.get(Constantes.FECHANACP).toString());
		} catch (final java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		registro.getDatosPersona().setFechaNacP(date);
		registro.getDatosPersona().setDireccionP(jsonPersonaUsuarioEmpresaVacios.get(Constantes.DIRECCIONP).toString());
		registro.getDatosPersona().setEmailP(jsonPersonaUsuarioEmpresaVacios.get(Constantes.EMAILP).toString());
		registro.getDatosPersona().setTelefonoP(jsonPersonaUsuarioEmpresaVacios.get(Constantes.TELEFONOP).toString());

		registro.getDatosUsuario().setUsuario(jsonPersonaUsuarioEmpresaVacios.get(Constantes.USUARIO).toString());
		registro.getDatosUsuario()
				.setPasswdUsuario(jsonPersonaUsuarioEmpresaVacios.get(Constantes.PASSWD_USUARIO).toString());

		registro.getDatosEmpresa()
				.setCifEmpresa(jsonPersonaUsuarioEmpresaVacios.get(Constantes.CIF_EMPRESA).toString());
		registro.getDatosEmpresa()
				.setNombreEmpresa(jsonPersonaUsuarioEmpresaVacios.get(Constantes.NOMBRE_EMPRESA).toString());
		registro.getDatosEmpresa()
				.setDireccionEmpresa(jsonPersonaUsuarioEmpresaVacios.get(Constantes.DIRECCION_EMPRESA).toString());
		registro.getDatosEmpresa()
				.setTelefonoEmpresa(jsonPersonaUsuarioEmpresaVacios.get(Constantes.TELEFONO_EMPRESA).toString());

		return registro;

	}

}
