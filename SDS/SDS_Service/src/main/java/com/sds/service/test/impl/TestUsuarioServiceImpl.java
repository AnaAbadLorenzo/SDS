package com.sds.service.test.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.EmpresaRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestUsuarioService;
import com.sds.service.test.impl.atributos.TestAtributoContrasenaUsuario;
import com.sds.service.test.impl.atributos.TestAtributoDniUsuario;
import com.sds.service.test.impl.atributos.TestAtributoNombreUsuario;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestUsuarioServiceImpl implements TestUsuarioService {

	private final TestAtributoNombreUsuario testAtributoUsuario;
	private final TestAtributoDniUsuario testAtributoDniUsuario;
	private final TestAtributoContrasenaUsuario testAtributoContrasena;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	RolRepository rolRepository;

	public TestUsuarioServiceImpl() {
		testAtributoUsuario = new TestAtributoNombreUsuario();
		testAtributoDniUsuario = new TestAtributoDniUsuario();
		testAtributoContrasena = new TestAtributoContrasenaUsuario();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuarioDniUsuario()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final UsuarioEntity datosEntradaDniUsuarioVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIP_VACIO_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoEnhe = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFANUMERICO_ENHE_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoAcentos = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFANUMERICO_ACENTOS_DATA);

		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoCaracteresEspeciales = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO,
				Constantes.DNIPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);

		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoEspacios = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFANUMERICO_ESPACIOS_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoMenor9 = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIP_ALFANUMERICO_MENOR_9_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoMayor9 = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIP_ALFANUMERICO_MAYOR_9_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfabetico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFABETICO_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumerico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFANUMERICO_DATA);
		final UsuarioEntity datosEntradaDniUsuarioNumerico = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoDniUsuario.getTestDniUsuarioVacio(datosEntradaDniUsuarioVacio));
		datosPruebaAtributos
				.add(testAtributoDniUsuario.getTestDniUsuarioAlfanumericoEnhe(datosEntradaDniUsuarioAlfanumericoEnhe));
		datosPruebaAtributos.add(
				testAtributoDniUsuario.getTestDniUsuarioAlfanumericoAcentos(datosEntradaDniUsuarioAlfanumericoAcentos));

		datosPruebaAtributos.add(testAtributoDniUsuario.getTestDniUsuarioAlfanumericoCaracteresEspeciales(
				datosEntradaDniUsuarioAlfanumericoCaracteresEspeciales));

		datosPruebaAtributos.add(testAtributoDniUsuario
				.getTestDniUsuarioAlfanumericoEspacios(datosEntradaDniUsuarioAlfanumericoEspacios));
		datosPruebaAtributos.add(
				testAtributoDniUsuario.getTestDniUsuarioAlfanumericoMenor9(datosEntradaDniUsuarioAlfanumericoMenor9));
		datosPruebaAtributos.add(
				testAtributoDniUsuario.getTestDniUsuarioAlfanumericoMayor9(datosEntradaDniUsuarioAlfanumericoMayor9));
		datosPruebaAtributos
				.add(testAtributoDniUsuario.getTestDniUsuarioCorrectoAlfanumerico(datosEntradaDniUsuarioAlfabetico));
		datosPruebaAtributos
				.add(testAtributoDniUsuario.getTestDniUsuarioCorrectoAlfanumerico(datosEntradaDniUsuarioAlfanumerico));
		datosPruebaAtributos
				.add(testAtributoDniUsuario.getTestDniUsuarioCorrectoAlfanumerico(datosEntradaDniUsuarioNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuarioDniUsuarioBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoEnhe = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFANUMERICO_ENHE_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoAcentos = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFANUMERICO_ACENTOS_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoCaracteresEspeciales = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO,
				Constantes.DNIPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoEspacios = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFANUMERICO_ESPACIOS_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumericoMayor9 = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIP_ALFANUMERICO_MAYOR_9_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfabetico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFABETICO_DATA);
		final UsuarioEntity datosEntradaDniUsuarioAlfanumerico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_ALFANUMERICO_DATA);
		final UsuarioEntity datosEntradaDniUsuarioNumerico = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO, Constantes.DNIPERSONA_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoDniUsuario.getTestDniUsuarioAlfanumericoEnhe(datosEntradaDniUsuarioAlfanumericoEnhe));
		datosPruebaAtributos.add(
				testAtributoDniUsuario.getTestDniUsuarioAlfanumericoAcentos(datosEntradaDniUsuarioAlfanumericoAcentos));

		datosPruebaAtributos.add(testAtributoDniUsuario.getTestDniUsuarioAlfanumericoCaracteresEspeciales(
				datosEntradaDniUsuarioAlfanumericoCaracteresEspeciales));

		datosPruebaAtributos.add(testAtributoDniUsuario
				.getTestDniUsuarioAlfanumericoEspacios(datosEntradaDniUsuarioAlfanumericoEspacios));

		datosPruebaAtributos.add(
				testAtributoDniUsuario.getTestDniUsuarioAlfanumericoMayor9(datosEntradaDniUsuarioAlfanumericoMayor9));
		datosPruebaAtributos
				.add(testAtributoDniUsuario.getTestDniUsuarioCorrectoAlfanumerico(datosEntradaDniUsuarioAlfabetico));
		datosPruebaAtributos
				.add(testAtributoDniUsuario.getTestDniUsuarioCorrectoAlfanumerico(datosEntradaDniUsuarioAlfanumerico));
		datosPruebaAtributos
				.add(testAtributoDniUsuario.getTestDniUsuarioCorrectoAlfanumerico(datosEntradaDniUsuarioNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuarioNombreUsuario()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final UsuarioEntity datosEntradaUsuarioVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_VACIO_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoEnhe = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_ENHE_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoAcentos = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_ACENTOS_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoCaracteresEspeciales = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO,
				Constantes.USUARIO_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoEspacios = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_ESPACIOS_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoMenor3 = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_MENOR_3_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoMayor45 = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_MAYOR_45_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfabetico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFABETICO_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumerico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_DATA);
		final UsuarioEntity datosEntradaUsuarioNumerico = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoUsuario.getTestUsuarioVacio(datosEntradaUsuarioVacio));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoEnhe(datosEntradaUsuarioAlfanumericoEnhe));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoAcentos(datosEntradaUsuarioAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoUsuario
				.getTestUsuarioAlfanumericoCaracteresEspeciales(datosEntradaUsuarioAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoEspacios(datosEntradaUsuarioAlfanumericoEspacios));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoMenor3(datosEntradaUsuarioAlfanumericoMenor3));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoMayor45(datosEntradaUsuarioAlfanumericoMayor45));
		datosPruebaAtributos.add(testAtributoUsuario.getTestUsuarioCorrectoAlfanumerico(datosEntradaUsuarioAlfabetico));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioCorrectoAlfanumerico(datosEntradaUsuarioAlfanumerico));
		datosPruebaAtributos.add(testAtributoUsuario.getTestUsuarioCorrectoAlfanumerico(datosEntradaUsuarioNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuarioNombreUsuarioBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final UsuarioEntity datosEntradaUsuarioAlfanumericoEnhe = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_ENHE_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoAcentos = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_ACENTOS_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoCaracteresEspeciales = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO,
				Constantes.USUARIO_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoEspacios = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_ESPACIOS_DATA);

		final UsuarioEntity datosEntradaUsuarioAlfanumericoMayor45 = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_MAYOR_45_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfabetico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFABETICO_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumerico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_ALFANUMERICO_DATA);
		final UsuarioEntity datosEntradaUsuarioNumerico = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO, Constantes.USUARIO_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoEnhe(datosEntradaUsuarioAlfanumericoEnhe));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoAcentos(datosEntradaUsuarioAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoUsuario
				.getTestUsuarioAlfanumericoCaracteresEspeciales(datosEntradaUsuarioAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoEspacios(datosEntradaUsuarioAlfanumericoEspacios));

		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioAlfanumericoMayor45(datosEntradaUsuarioAlfanumericoMayor45));
		datosPruebaAtributos.add(testAtributoUsuario.getTestUsuarioCorrectoAlfanumerico(datosEntradaUsuarioAlfabetico));
		datosPruebaAtributos
				.add(testAtributoUsuario.getTestUsuarioCorrectoAlfanumerico(datosEntradaUsuarioAlfanumerico));
		datosPruebaAtributos.add(testAtributoUsuario.getTestUsuarioCorrectoAlfanumerico(datosEntradaUsuarioNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuarioPasswdUsuario()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final UsuarioEntity datosEntradaContrasenaVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_VACIA_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoEnhe = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_ALFANUMERICA_ENHE_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoAcentos = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_ALFANUMERICA_ACENTOS_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoCaracteresEspeciales = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO,
				Constantes.CONTRASENA_ALFANUMERICA_CARACTERES_ESPECIALES_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoEspacios = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_ALFANUMERICA_ESPACIOS_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoMenor3 = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_ALFANUMERICA_MENOR_3_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoMayor45 = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_ALFANUMERICA_MAYOR_45_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfabetico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_ALFABETICA_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumerico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_ALFANUMERICA_DATA);
		final UsuarioEntity datosEntradaContrasenaNumerico = generarJSON.generateUsuario(
				Constantes.URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO, Constantes.CONTRASENA_NUMERICA_DATA);

		datosPruebaAtributos.add(testAtributoContrasena.getTestContrasenaVacio(datosEntradaContrasenaVacio));
		datosPruebaAtributos.add(
				testAtributoContrasena.getTestContrasenaAlfanumericoAcentos(datosEntradaContrasenaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoContrasena.getTestContrasenaAlfanumericoCaracteresEspeciales(
				datosEntradaContrasenaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoContrasena.getTestContrasenaAlfanumericoEnhe(datosEntradaContrasenaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoContrasena
				.getTestContrasenaAlfanumericoEspacios(datosEntradaContrasenaAlfanumericoEspacios));
		datosPruebaAtributos.add(
				testAtributoContrasena.getTestContrasenaAlfanumericoMenor3(datosEntradaContrasenaAlfanumericoMenor3));
		datosPruebaAtributos.add(
				testAtributoContrasena.getTestContrasenaAlfanumericoMayor45(datosEntradaContrasenaAlfanumericoMayor45));
		datosPruebaAtributos
				.add(testAtributoContrasena.getTestContrasenaCorrectoAlfanumerico(datosEntradaContrasenaAlfabetico));
		datosPruebaAtributos
				.add(testAtributoContrasena.getTestContrasenaCorrectoAlfanumerico(datosEntradaContrasenaAlfanumerico));
		datosPruebaAtributos
				.add(testAtributoContrasena.getTestContrasenaCorrectoAlfanumerico(datosEntradaContrasenaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesUsuarioBuscar() throws IOException, ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final UsuarioEntity datosEntradaUsuarioBuscarUsuario = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.BUSCAR_USUARIO);
		final UsuarioEntity datosEntradaUsuarioBuscarNombreUsuarioVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_VACIO_DATA);
		final UsuarioEntity datosEntradaUsuarioBuscarDniUsuarioVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.DNIP_VACIO_DATA);
		final UsuarioEntity datosEntradaUsuarioBuscarUsuarioVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_DNI_NOMBRE_VACIOS);

		datosPruebaAcciones.add(getTestBuscarUsuario(datosEntradaUsuarioBuscarUsuario));
		datosPruebaAcciones.add(getTestBuscarUsuario(datosEntradaUsuarioBuscarNombreUsuarioVacio));
		datosPruebaAcciones.add(getTestBuscarUsuario(datosEntradaUsuarioBuscarDniUsuarioVacio));
		datosPruebaAcciones.add(getTestBuscarUsuario(datosEntradaUsuarioBuscarUsuarioVacio));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesUsuarioEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final UsuarioEntity datosEntradaUsuarioEliminarUsuario = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.ELIMINAR_USUARIO);
		final UsuarioEntity datosEntradaUsuarioEliminarUsuarioNoExiste = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_NO_EXISTE);

		datosPruebaAcciones.add(getTestEliminarUsuario(datosEntradaUsuarioEliminarUsuario));
		datosPruebaAcciones.add(getTestEliminarUsuarioNoExiste(datosEntradaUsuarioEliminarUsuarioNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesUsuarioReactivar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final UsuarioEntity datosEntradaUsuarioReactivarUsuario = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.REACTIVAR_USUARIO_CORRECTO);
		final UsuarioEntity datosEntradaUsuarioReactivarUsuarioNoExiste = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_NO_EXISTE);

		datosPruebaAcciones.add(getTestReactivarUsuario(datosEntradaUsuarioReactivarUsuario));
		datosPruebaAcciones.add(getTestReactivarUsuarioNoExiste(datosEntradaUsuarioReactivarUsuarioNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesUsuarioModificarRol()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RolEntity datosEntradaRolUsuario = generarJSON.generarRol(Constantes.URL_JSON_USUARIO_DATA,
				Constantes.ROL_USUARIO);

		final UsuarioEntity datosEntradaUsuarioModificarRolUsuarioRolNoExiste = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.MODIFICAR_ROL_USUARIO);

		final UsuarioEntity datosEntradaUsuarioModificarUsuarioNoExiste = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_NO_EXISTE);

		final UsuarioEntity datosEntradaUsuarioModificarRolUsuarioCorrecto = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.MODIFICAR_ROL_USUARIO);

		datosPruebaAcciones.add(getTestModificarRolUsuarioRolNoExiste(datosEntradaRolUsuario,
				datosEntradaUsuarioModificarRolUsuarioRolNoExiste));

		datosPruebaAcciones.add(getTestModificarRolUsuarioNoExiste(datosEntradaRolUsuario,
				datosEntradaUsuarioModificarUsuarioNoExiste));

		datosPruebaAcciones.add(
				getTestModificarRolUsuario(datosEntradaRolUsuario, datosEntradaUsuarioModificarRolUsuarioCorrecto));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesUsuarioCambiarContraseña()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();
		final String passwdUsuario = "H7734asd";

		final UsuarioEntity datosEntradaUsuarioModificarPasswdUsuario = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.MODIFICAR_PASSWD_USUARIO);

		final UsuarioEntity datosEntradaUsuarioModificarUsuarioNoExiste = generarJSON
				.generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_NO_EXISTE);

		datosPruebaAcciones
				.add(getTestModificarPasswdUsuarioCorrecto(passwdUsuario, datosEntradaUsuarioModificarPasswdUsuario));

		datosPruebaAcciones
				.add(getTestModificarPasswdUsuarioNoExiste(passwdUsuario, datosEntradaUsuarioModificarUsuarioNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarUsuario(final UsuarioEntity datosEntradaUsuarioBuscarUsuario) {

		final String resultadoObtenido = buscarUsuario(datosEntradaUsuarioBuscarUsuario);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_USUARIO_CORRECTO + " - "
				+ Mensajes.BUSCAR_USUARIO_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO, getValorUsuario(datosEntradaUsuarioBuscarUsuario));

	}

	private DatosPruebaAcciones getTestEliminarUsuario(final UsuarioEntity datosEntradaUsuarioEliminarUsuario)
			throws java.text.ParseException {

		final String resultadoObtenido = eliminarUsuario(datosEntradaUsuarioEliminarUsuario);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_USUARIO_CORRECTO + " - "
				+ Mensajes.ELIMINAR_USUARIO_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_USUARIO_CORRECTO, Constantes.EXITO,
				getValorUsuario(datosEntradaUsuarioEliminarUsuario));

	}

	private DatosPruebaAcciones getTestEliminarUsuarioNoExiste(
			final UsuarioEntity datosEntradaUsuarioEliminarUsuarioNoExiste) {

		final String resultadoObtenido = eliminarUsuarioNoExiste(datosEntradaUsuarioEliminarUsuarioNoExiste);

		final String resultadoEsperado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_USUARIO_NO_EXISTE, Constantes.ERROR,
				getValorUsuario(datosEntradaUsuarioEliminarUsuarioNoExiste));

	}

	private DatosPruebaAcciones getTestModificarRolUsuario(final RolEntity rolUsuario,
			final UsuarioEntity datosEntradaUsuarioModificarRol) throws java.text.ParseException {

		final String resultadoObtenido = modificarRolUsuario(rolUsuario, datosEntradaUsuarioModificarRol);

		final String resultadoEsperado = CodigosMensajes.ROL_USUARIO_MODIFICADO_OK + " - "
				+ Mensajes.ROL_USUARIO_MODIFICADO_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_ROL_USUARIO_OK, Constantes.EXITO,
				getValorUsuario(datosEntradaUsuarioModificarRol));

	}

	private DatosPruebaAcciones getTestModificarRolUsuarioRolNoExiste(final RolEntity rolUsuario,
			final UsuarioEntity datosEntradaUsuarioModificarRolNoExiste) {

		final String resultadoObtenido = modificarRolUsuarioRolNoExiste(rolUsuario,
				datosEntradaUsuarioModificarRolNoExiste);

		final String resultadoEsperado = CodigosMensajes.ROL_NO_EXISTE + " - " + Mensajes.ROL_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_ROL_USUARIO_ROL_NO_EXISTE, Constantes.ERROR,
				getValorUsuario(datosEntradaUsuarioModificarRolNoExiste));

	}

	private DatosPruebaAcciones getTestModificarRolUsuarioNoExiste(final RolEntity rolUsuario,
			final UsuarioEntity datosEntradaUsuarioModificarNoExiste) {

		final String resultadoObtenido = modificarRolUsuarioNoExiste(rolUsuario, datosEntradaUsuarioModificarNoExiste);

		final String resultadoEsperado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_ROL_USUARIO_NO_EXISTE, Constantes.ERROR,
				getValorUsuario(datosEntradaUsuarioModificarNoExiste));

	}

	private DatosPruebaAcciones getTestModificarPasswdUsuarioCorrecto(final String passwdUsuario,
			final UsuarioEntity datosEntradaUsuarioModificarPasswd) throws java.text.ParseException {

		final String resultadoObtenido = cambiarContraseña(passwdUsuario, datosEntradaUsuarioModificarPasswd);

		final String resultadoEsperado = CodigosMensajes.CONTRASEÑA_MODIFICADA_OK + " - "
				+ Mensajes.CONTRASEÑA_USUARIO_MODIFICADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_PASSWD_USUARIO_OK, Constantes.EXITO,
				getValorUsuario(datosEntradaUsuarioModificarPasswd));

	}

	private DatosPruebaAcciones getTestModificarPasswdUsuarioNoExiste(final String passwdUsuario,
			final UsuarioEntity datosEntradaUsuarioModificarPasswdUsuarioNoExiste) {

		final String resultadoObtenido = cambiarContraseñaUsuarioNoEncontrado(passwdUsuario,
				datosEntradaUsuarioModificarPasswdUsuarioNoExiste);

		final String resultadoEsperado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_PASSWD_USUARIO_NO_EXISTE, Constantes.ERROR,
				getValorUsuario(datosEntradaUsuarioModificarPasswdUsuarioNoExiste));

	}

	private DatosPruebaAcciones getTestReactivarUsuario(final UsuarioEntity datosEntradaUsuarioReactivarUsuario)
			throws java.text.ParseException {

		final String resultadoObtenido = reactivarUsuario(datosEntradaUsuarioReactivarUsuario);

		final String resultadoEsperado = CodigosMensajes.REACTIVAR_USUARIO_CORRECTO + " - "
				+ Mensajes.USUARIO_REACTIVADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.REACTIVAR_USUARIO_CORRECTO, Constantes.EXITO,
				getValorUsuario(datosEntradaUsuarioReactivarUsuario));

	}

	private DatosPruebaAcciones getTestReactivarUsuarioNoExiste(
			final UsuarioEntity datosEntradaUsuarioReactivarUsuarioNoExiste) {

		final String resultadoObtenido = reactivarUsuarioNoExiste(datosEntradaUsuarioReactivarUsuarioNoExiste);

		final String resultadoEsperado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.USUARIO_NO_EXISTE, Constantes.ERROR,
				getValorUsuario(datosEntradaUsuarioReactivarUsuarioNoExiste));

	}

	private String buscarUsuario(final UsuarioEntity usuario) {
		String resultado = StringUtils.EMPTY;

		List<UsuarioEntity> user = new ArrayList<>();
		user = usuarioRepository.findUsuario(usuario.getDniUsuario(), usuario.getUsuario(), usuario.getRol());

		resultado = CodigosMensajes.BUSCAR_USUARIO_CORRECTO + " - " + Mensajes.BUSCAR_USUARIO_CORRECTO;

		return resultado;
	}

	private String eliminarUsuario(final UsuarioEntity usuario) throws java.text.ParseException {
		final UsuarioEntity usuarioBD = usuarioRepository.findByDni(usuario.getDniUsuario());
		String resultado = StringUtils.EMPTY;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		if (usuarioBD == null) {
			final PersonaEntity persona = new PersonaEntity(usuario.getDniUsuario(), "Pepe", "Pepe pepe",
					format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0);
			final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);
			persona.setEmpresa(empresa);
			personaRepository.saveAndFlush(persona);

			usuarioRepository.saveAndFlush(usuario);

			usuario.setBorradoUsuario(1);
			usuarioRepository.saveAndFlush(usuario);

			resultado = CodigosMensajes.ELIMINAR_USUARIO_CORRECTO + " - " + Mensajes.ELIMINAR_USUARIO_CORRECTO;

			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(usuario.getDniUsuario());
			empresaRepository.deleteEmpresa(empresa.getCifEmpresa());
			rolRepository.deleteRol(usuario.getRol().getIdRol());

		}

		return resultado;
	}

	private String eliminarUsuarioNoExiste(final UsuarioEntity usuario) {
		final UsuarioEntity usuarioBD = usuarioRepository.findByDni(usuario.getDniUsuario());
		String resultado = StringUtils.EMPTY;

		if (usuarioBD == null) {
			resultado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;
		}

		return resultado;
	}

	private String modificarRolUsuario(final RolEntity rolUsuario, final UsuarioEntity usuario)
			throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		List<RolEntity> rolBD = new ArrayList<>();

		if (!validaciones.comprobarRolBlank(rolUsuario)) {
			resultado = CodigosMensajes.ROL_VACIO + " - " + Mensajes.ROL_VACIO;
		}

		else if (!validaciones.comprobarUsuarioBlank(usuario)) {
			resultado = CodigosMensajes.USUARIO_VACIO + " - " + Mensajes.USUARIO_VACIO;
		}

		else {

			final UsuarioEntity usuarioBD = usuarioRepository.findByDni(usuario.getDniUsuario());

			if (usuarioBD == null) {
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

				final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);

				final PersonaEntity persona = new PersonaEntity(usuario.getDniUsuario(), "Pepe", "Pepe pepe",
						format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, empresa);

				persona.setEmpresa(empresa);
				personaRepository.saveAndFlush(persona);

				usuarioRepository.saveAndFlush(usuario);

				final Optional<RolEntity> rolUser = rolRepository.findById(rolUsuario.getIdRol());

				if (!rolUser.isPresent()) {
					rolRepository.saveAndFlush(rolUsuario);

					rolBD = rolRepository.findRol(rolUsuario.getRolName(), rolUsuario.getRolDescription());

					usuario.setRol(rolBD.get(0));
					usuarioRepository.saveAndFlush(usuario);

					resultado = CodigosMensajes.ROL_USUARIO_MODIFICADO_OK + " - "
							+ Mensajes.ROL_USUARIO_MODIFICADO_CORRECTO;
				}

				usuarioRepository.deleteUsuario(usuario.getDniUsuario());
				personaRepository.deletePersona(persona.getDniP());
				empresaRepository.deleteEmpresa(empresa.getCifEmpresa());
				rolRepository.deleteRol(rolBD.get(0).getIdRol());
			}

		}

		return resultado;
	}

	private String modificarRolUsuarioRolNoExiste(final RolEntity rolUsuario, final UsuarioEntity usuario) {
		String resultado = StringUtils.EMPTY;
		if (!validaciones.comprobarRolBlank(rolUsuario)) {
			resultado = CodigosMensajes.ROL_VACIO + " - " + Mensajes.ROL_VACIO;
		}

		else if (!validaciones.comprobarUsuarioBlank(usuario)) {
			resultado = CodigosMensajes.USUARIO_VACIO + " - " + Mensajes.USUARIO_VACIO;
		}

		else {

			final Optional<RolEntity> rolEntity = rolRepository.findById(rolUsuario.getIdRol());

			if (!rolEntity.isPresent()) {
				resultado = CodigosMensajes.ROL_NO_EXISTE + " - " + Mensajes.ROL_NO_EXISTE;
			}
		}

		return resultado;
	}

	private String modificarRolUsuarioNoExiste(final RolEntity rolUsuario, final UsuarioEntity usuario) {
		String resultado = StringUtils.EMPTY;
		if (!validaciones.comprobarRolBlank(rolUsuario)) {
			resultado = CodigosMensajes.ROL_VACIO + " - " + Mensajes.ROL_VACIO;
		}

		else if (!validaciones.comprobarUsuarioBlank(usuario)) {
			resultado = CodigosMensajes.USUARIO_VACIO + " - " + Mensajes.USUARIO_VACIO;
		}

		else {

			final Optional<RolEntity> rolEntity = rolRepository.findById(rolUsuario.getIdRol());

			if (!rolEntity.isPresent()) {
				rolRepository.saveAndFlush(rolUsuario);
				final UsuarioEntity usuarioEntity = usuarioRepository.findByDni(usuario.getDniUsuario());

				if (usuarioEntity == null) {
					resultado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;
				}
			}

			final List<RolEntity> rol = rolRepository.findRol(rolUsuario.getRolName(), rolUsuario.getRolDescription());
			rolRepository.deleteRol(rol.get(0).getIdRol());
		}

		return resultado;
	}

	private String cambiarContraseña(final String passwdUsuario, final UsuarioEntity usuario)
			throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarPasswdUsuarioBlank(passwdUsuario)) {
			resultado = CodigosMensajes.PASS_USUARIO_VACIO + " - " + Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarUsuarioBlank(usuario)) {
			resultado = CodigosMensajes.USUARIO_VACIO + " - " + Mensajes.USUARIO_VACIO;
		} else {
			final UsuarioEntity usuarioBD = usuarioRepository.findByDni(usuario.getDniUsuario());

			if (usuarioBD == null) {
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

				final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);

				final PersonaEntity persona = new PersonaEntity(usuario.getDniUsuario(), "Pepe", "Pepe pepe",
						format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, empresa);

				persona.setEmpresa(empresa);
				personaRepository.saveAndFlush(persona);

				usuarioRepository.saveAndFlush(usuario);

				usuario.setPasswdUsuario(passwdUsuario);

				usuarioRepository.saveAndFlush(usuario);

				resultado = CodigosMensajes.CONTRASEÑA_MODIFICADA_OK + " - "
						+ Mensajes.CONTRASEÑA_USUARIO_MODIFICADA_CORRECTAMENTE;

				usuarioRepository.deleteUsuario(usuario.getDniUsuario());
				personaRepository.deletePersona(persona.getDniP());
				empresaRepository.deleteEmpresa(empresa.getCifEmpresa());

			}
		}

		return resultado;
	}

	private String cambiarContraseñaUsuarioNoEncontrado(final String passwdUsuario, final UsuarioEntity usuario) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarPasswdUsuarioBlank(passwdUsuario)) {
			resultado = CodigosMensajes.PASS_USUARIO_VACIO + " - " + Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarUsuarioBlank(usuario)) {
			resultado = CodigosMensajes.USUARIO_VACIO + " - " + Mensajes.USUARIO_VACIO;
		} else {
			final UsuarioEntity usuarioBD = usuarioRepository.findByDni(usuario.getDniUsuario());

			if (usuarioBD == null) {
				resultado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;
			}
		}

		return resultado;
	}

	private String reactivarUsuario(final UsuarioEntity usuario) throws java.text.ParseException {
		final UsuarioEntity usuarioBD = usuarioRepository.findByDni(usuario.getDniUsuario());
		String resultado = StringUtils.EMPTY;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		if (usuarioBD == null) {
			final PersonaEntity persona = new PersonaEntity(usuario.getDniUsuario(), "Pepe", "Pepe pepe",
					format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0);
			final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);
			persona.setEmpresa(empresa);
			personaRepository.saveAndFlush(persona);

			usuarioRepository.saveAndFlush(usuario);

			usuario.setBorradoUsuario(0);
			usuarioRepository.saveAndFlush(usuario);

			resultado = CodigosMensajes.REACTIVAR_USUARIO_CORRECTO + " - " + Mensajes.USUARIO_REACTIVADO_CORRECTAMENTE;

			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(usuario.getDniUsuario());
			empresaRepository.deleteEmpresa(empresa.getCifEmpresa());

		}

		return resultado;
	}

	private String reactivarUsuarioNoExiste(final UsuarioEntity usuario) {
		final UsuarioEntity usuarioBD = usuarioRepository.findByDni(usuario.getDniUsuario());
		String resultado = StringUtils.EMPTY;

		if (usuarioBD == null) {
			resultado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;
		}

		return resultado;
	}

	private Map<String, String> getValorUsuario(final UsuarioEntity usuario) {

		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.USUARIO_DNI, usuario.getDniUsuario());
		valor.put(Constantes.USUARIO, usuario.getUsuario());
		valor.put(Constantes.PASSWD_USUARIO, usuario.getPasswdUsuario());
		valor.put(Constantes.ROL_ID, Integer.toString(usuario.getRol().getIdRol()));

		return valor;
	}

}
