package com.sds.service.test.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.sds.service.registro.model.Registro;
import com.sds.service.test.TestRegistrarService;
import com.sds.service.test.impl.atributos.TestAtributoApellidosPersonaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoCifEmpresaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoContrasenaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoDireccionEmpresaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoDireccionPersonaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoDniPersonaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoEmailPersonaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoFechaNacimientoPersonaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoNombreEmpresaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoNombrePersonaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoTelefonoEmpresaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoTelefonoPersonaRegistro;
import com.sds.service.test.impl.atributos.TestAtributoUsuarioRegistro;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestRegistrarServiceImpl implements TestRegistrarService {

	private final TestAtributoDniPersonaRegistro testAtributoDniPersonaRegistro;
	private final TestAtributoNombrePersonaRegistro testAtributoNombrePersonaRegistro;
	private final TestAtributoApellidosPersonaRegistro testAtributoApellidosPersonaRegistro;
	private final TestAtributoDireccionPersonaRegistro testAtributoDireccionPersonaRegistro;
	private final TestAtributoFechaNacimientoPersonaRegistro testAtributoFechaNacimientoPersonaRegistro;
	private final TestAtributoEmailPersonaRegistro testAtributoEmailPersonaRegistro;
	private final TestAtributoTelefonoPersonaRegistro testAtributoTelefonoPersonaRegistro;
	private final TestAtributoUsuarioRegistro testAtributoUsuarioRegistro;
	private final TestAtributoContrasenaRegistro testAtributoContrasenaRegistro;
	private final TestAtributoCifEmpresaRegistro testAtributoCifEmpresaRegistro;
	private final TestAtributoNombreEmpresaRegistro testAtributoNombreEmpresaRegistro;
	private final TestAtributoDireccionEmpresaRegistro testAtributoDireccionEmpresaRegistro;
	private final TestAtributoTelefonoEmpresaRegistro testAtributoTelefonoEmpresaRegistro;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	RolRepository rolRepository;

	public TestRegistrarServiceImpl() {
		testAtributoDniPersonaRegistro = new TestAtributoDniPersonaRegistro();
		testAtributoNombrePersonaRegistro = new TestAtributoNombrePersonaRegistro();
		testAtributoApellidosPersonaRegistro = new TestAtributoApellidosPersonaRegistro();
		testAtributoDireccionPersonaRegistro = new TestAtributoDireccionPersonaRegistro();
		testAtributoFechaNacimientoPersonaRegistro = new TestAtributoFechaNacimientoPersonaRegistro();
		testAtributoEmailPersonaRegistro = new TestAtributoEmailPersonaRegistro();
		testAtributoTelefonoPersonaRegistro = new TestAtributoTelefonoPersonaRegistro();
		testAtributoUsuarioRegistro = new TestAtributoUsuarioRegistro();
		testAtributoContrasenaRegistro = new TestAtributoContrasenaRegistro();
		testAtributoCifEmpresaRegistro = new TestAtributoCifEmpresaRegistro();
		testAtributoNombreEmpresaRegistro = new TestAtributoNombreEmpresaRegistro();
		testAtributoDireccionEmpresaRegistro = new TestAtributoDireccionEmpresaRegistro();
		testAtributoTelefonoEmpresaRegistro = new TestAtributoTelefonoEmpresaRegistro();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		this.validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDniP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroDniPersonaVacio = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIP_VACIO_DATA);
		final Registro datosEntradaRegistroDniPersonaAlfanumericoEnhe = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ENHE_DATA);
		final Registro datosEntradaRegistroDniPersonaAlfanumericoAcentos = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ACENTOS_DATA);
		final Registro datosEntradaRegistroDniPersonaAlfanumericoCaracteresEspeciales = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP,
				Constantes.DNIPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroDniPersonaAlfanumericoEspacios = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ESPACIOS_DATA);
		final Registro datosEntradaRegistroDniPersonaAlfanumericoMenor9 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIP_ALFANUMERICO_MENOR_9_DATA);
		final Registro datosEntradaRegistroDniPersonaAlfanumericoMayor9 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIP_ALFANUMERICO_MAYOR_9_DATA);
		final Registro datosEntradaRegistroDniPersonaAlfabetico = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFABETICO_DATA);
		final Registro datosEntradaRegistroDniPersonaAlfanumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_DATA);
		final Registro datosEntradaRegistroDniPersonaNumerico = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(
				testAtributoDniPersonaRegistro.getTestRegistroDniPersonaVacio(datosEntradaRegistroDniPersonaVacio));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro
				.getTestRegistroDniPersonaAlfanumericoEnhe(datosEntradaRegistroDniPersonaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro
				.getTestRegistroDniPersonaAlfanumericoAcentos(datosEntradaRegistroDniPersonaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro.getTestRegistroDniPAlfanumericoCaracteresEspeciales(
				datosEntradaRegistroDniPersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro
				.getTestRegistroDniPAlfanumericoEspacios(datosEntradaRegistroDniPersonaAlfanumericoEspacios));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro
				.getTestRegistroDniPAlfanumericoMenor9(datosEntradaRegistroDniPersonaAlfanumericoMenor9));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro
				.getTestRegistroDniPAlfanumericoMayor9(datosEntradaRegistroDniPersonaAlfanumericoMayor9));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro
				.getTestRegistroDniPCorrectoAlfanumerico(datosEntradaRegistroDniPersonaAlfabetico));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro
				.getTestRegistroDniPCorrectoAlfanumerico(datosEntradaRegistroDniPersonaAlfanumerico));
		datosPruebaAtributos.add(testAtributoDniPersonaRegistro
				.getTestRegistroDniPCorrectoAlfanumerico(datosEntradaRegistroDniPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroNombrePersonaVacio = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_VACIO_DATA);
		final Registro datosEntradaRegistroNombrePersonaAlfanumericoCaracteresEspeciales = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREP,
				Constantes.NOMBREPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroNombrePersonaAlfanumericoMenor3 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_ALFANUMERICO_MENOR_3_DATA);
		final Registro datosEntradaRegistroNombrePersonaAlfanumericoMayor56 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_ALFANUMERICO_MAYOR_56_DATA);
		final Registro datosEntradaRegistroNombrePersonaAlfabetico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_ALFABETICO_DATA);
		final Registro datosEntradaRegistroNombrePNumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoNombrePersonaRegistro
				.getTestRegistroNombrePersonaVacio(datosEntradaRegistroNombrePersonaVacio));
		datosPruebaAtributos
				.add(testAtributoNombrePersonaRegistro.getTestRegistroNombrePAlfanumericoCaracteresEspeciales(
						datosEntradaRegistroNombrePersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombrePersonaRegistro
				.getTestRegistroNombrePAlfanumericoMenor3(datosEntradaRegistroNombrePersonaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoNombrePersonaRegistro
				.getTestRegistroNombrePAlfanumericoMayor56(datosEntradaRegistroNombrePersonaAlfanumericoMayor56));
		datosPruebaAtributos.add(testAtributoNombrePersonaRegistro
				.getTestRegistroNombrePCorrectoAlfabetico(datosEntradaRegistroNombrePersonaAlfabetico));
		datosPruebaAtributos.add(
				testAtributoNombrePersonaRegistro.getTestRegistroNombrePNumerico(datosEntradaRegistroNombrePNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoApellidosP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroApellidosPersonaVacio = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_APELLIDOSP, Constantes.APELLIDOSPERSONA_VACIOS_DATA);
		final Registro datosEntradaRegistroApellidosPersonaAlfanumericoCaracteresEspeciales = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_APELLIDOSP,
						Constantes.APELLIDOSPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroApellidosPersonaAlfanumericoMenor3 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_APELLIDOSP,
				Constantes.APELLIDOSPERSONA_ALFANUMERICO_MENOR_3_DATA);
		final Registro datosEntradaRegistroApellidosePersonaAlfanumericoMayor128 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_APELLIDOSP,
				Constantes.APELLIDOSPERSONA_ALFANUMERICO_MAYOR_128_DATA);
		final Registro datosEntradaRegistroApellidosPersonaNumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_APELLIDOSP, Constantes.APELLIDOSPERSONA_NUMERICO_DATA);
		final Registro datosEntradaRegistroApellidosPersonaAlfabetico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_APELLIDOSP, Constantes.APELLIDOSPERSONA_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoApellidosPersonaRegistro
				.getTestRegistroApellidosPersonaVacio(datosEntradaRegistroApellidosPersonaVacio));
		datosPruebaAtributos
				.add(testAtributoApellidosPersonaRegistro.getTestRegistroApellidosPAlfanumericoCaracteresEspeciales(
						datosEntradaRegistroApellidosPersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoApellidosPersonaRegistro
				.getTestRegistroApellidosPAlfanumericoMenor3(datosEntradaRegistroApellidosPersonaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoApellidosPersonaRegistro.getTestRegistroApellidosPAlfanumericoMayor128(
				datosEntradaRegistroApellidosePersonaAlfanumericoMayor128));
		datosPruebaAtributos.add(testAtributoApellidosPersonaRegistro
				.getTestRegistroAppellidosPNumerico(datosEntradaRegistroApellidosPersonaNumerico));
		datosPruebaAtributos.add(testAtributoApellidosPersonaRegistro
				.getTestRegistroApellidosPCorrectoAlfabetico(datosEntradaRegistroApellidosPersonaAlfabetico));
		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDireccionP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroDireccionPersonaVacio = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONP, Constantes.DIRECCIONPERSONA_VACIA_DATA);
		final Registro datosEntradaRegistroDireccionPersonaAlfanumericoMenor3 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONP,
				Constantes.DIRECCIONPERSONA_ALFANUMERICA_MENOR_3_DATA);
		final Registro datosEntradaRegistroDireccionPersonaAlfanumericoMayor128 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONP,
				Constantes.DIRECCIONPERSONA_ALFANUMERICA_MAYOR_128_DATA);
		final Registro datosEntradaRegistroDireccionPersonaAlfabetico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONP, Constantes.DIRECCIONPERSONA_ALFABETICO_DATA);
		final Registro datosEntradaRegistroDireccionPersonaAlfanumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONP, Constantes.DIRECCIONPERSONA_ALFANUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoDireccionPersonaRegistro
				.getTestRegistroDireccionPersonaVacio(datosEntradaRegistroDireccionPersonaVacio));
		datosPruebaAtributos.add(testAtributoDireccionPersonaRegistro.getTestRegistroDireccionPersonaAlfanumericoMenor3(
				datosEntradaRegistroDireccionPersonaAlfanumericoMenor3));
		datosPruebaAtributos
				.add(testAtributoDireccionPersonaRegistro.getTestRegistroDireccionPersonaAlfanumericoMayor128(
						datosEntradaRegistroDireccionPersonaAlfanumericoMayor128));
		datosPruebaAtributos.add(testAtributoDireccionPersonaRegistro
				.getTestRegistroDireccionPCorrectoAlfanumerico(datosEntradaRegistroDireccionPersonaAlfabetico));
		datosPruebaAtributos.add(testAtributoDireccionPersonaRegistro
				.getTestRegistroDireccionPCorrectoAlfanumerico(datosEntradaRegistroDireccionPersonaAlfanumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTelefonoP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroTelefonoPersonaVacio = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_VACIO_DATA);
		final Registro datosEntradaRegistroTelefonoPersonaNumericoEnhe = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ENHE_DATA);
		final Registro datosEntradaRegistroTelefonoPersonaNumericoAcentos = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ACENTOS_DATA);
		final Registro datosEntradaRegistroTelefonoPersonaNumericoCaracteresEspeciales = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP,
				Constantes.TELEFONOPERSONA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroTelefonoPersonaNumericoEspacios = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ESPACIOS_DATA);
		final Registro datosEntradaRegistroTelefonoPersonaNumericoMenor9 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_MENOR_9_DATA);
		final Registro datosEntradaRegistroTelefonoPersonaNumericoMayor9 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_MAYOR_9_DATA);
		final Registro datosEntradaRegistroTelefonoPersonaNumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoTelefonoPersonaRegistro
				.getTestRegistroTelefonoPersonaVacio(datosEntradaRegistroTelefonoPersonaVacio));
		datosPruebaAtributos.add(testAtributoTelefonoPersonaRegistro
				.getTestRegistroTelefonoPersonaNumericoEnhe(datosEntradaRegistroTelefonoPersonaNumericoEnhe));
		datosPruebaAtributos.add(testAtributoTelefonoPersonaRegistro
				.getTestRegistroTelefonoPersonaNumericoAcentos(datosEntradaRegistroTelefonoPersonaNumericoAcentos));
		datosPruebaAtributos
				.add(testAtributoTelefonoPersonaRegistro.getTestRegistroTelefonoPNumericoCaracteresEspeciales(
						datosEntradaRegistroTelefonoPersonaNumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoTelefonoPersonaRegistro
				.getTestRegistroTelefonoPNumericoEspacios(datosEntradaRegistroTelefonoPersonaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoTelefonoPersonaRegistro
				.getTestRegistroTelefonoPNumericoMenor9(datosEntradaRegistroTelefonoPersonaNumericoMenor9));
		datosPruebaAtributos.add(testAtributoTelefonoPersonaRegistro
				.getTestRegistroTelefonoPNumericoMayor9(datosEntradaRegistroTelefonoPersonaNumericoMayor9));
		datosPruebaAtributos.add(testAtributoTelefonoPersonaRegistro
				.getTestRegistroTelefonoPersonaCorrectoNumerico(datosEntradaRegistroTelefonoPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoEmailP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroEmailPersonaVacio = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP, Constantes.EMAIL_VACIO_DATA);
		final Registro datosEntradaRegistroEmailPersonaAlfanumericoEnhe = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ENHE_DATA);
		final Registro datosEntradaRegistroEmailPersonaAlfanumericoAcentos = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ACENTOS_DATA);
		final Registro datosEntradaRegistroEmailPersonaAlfaNumericoEspacios = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ESPACIOS_DATA);
		final Registro datosEntradaRegistroEmailPersonaAlfaNumericoMenor4 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_MENOR_4_DATA);
		final Registro datosEntradaRegistroEmailPersonaAlfaNumericoMayor128 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_MAYOR_128_DATA);
		final Registro datosEntradaRegistroEmailPersonaFormatoCorrecto = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP, Constantes.EMAILPERSONA_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoEmailPersonaRegistro
				.getTestRegistroEmailPersonaVacio(datosEntradaRegistroEmailPersonaVacio));
		datosPruebaAtributos.add(testAtributoEmailPersonaRegistro
				.getTestRegistroEmailPersonaAlfanumericoEnhe(datosEntradaRegistroEmailPersonaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoEmailPersonaRegistro
				.getTestRegistroEmailPersonaAlfanumericoAcentos(datosEntradaRegistroEmailPersonaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoEmailPersonaRegistro
				.getTestRegistroEmailPAlfanumericoEspacios(datosEntradaRegistroEmailPersonaAlfaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoEmailPersonaRegistro
				.getTestRegistroEmailPAlfanumericoMenor4(datosEntradaRegistroEmailPersonaAlfaNumericoMenor4));
		datosPruebaAtributos.add(testAtributoEmailPersonaRegistro
				.getTestRegistroEmailPAlfanumericoMayor128(datosEntradaRegistroEmailPersonaAlfaNumericoMayor128));
		datosPruebaAtributos.add(testAtributoEmailPersonaRegistro
				.getTestRegistroEmailPCorrectoAlfanumerico(datosEntradaRegistroEmailPersonaFormatoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaNacP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroFechaNacimientoPersonaVacio = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_VACIA_DATA);
		final Registro datosEntradaRegistroFechaNacimientoPersonaNumericoEnhe = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ENHE_DATA);
		final Registro datosEntradaRegistroFechaNacimientoPersonaNumericoAcentos = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ACENTOS_DATA);
		final Registro datosEntradaRegistroFechaNacimientoPersonaNumericoCaracteresEspeciales = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP,
						Constantes.FECHANACPERSONA_NUMERICA_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroFechaNacimientoPersonaNumericoEspacios = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ACENTOS_DATA);
		final Registro datosEntradaRegistroFechaNacimientoPersonaNumericoMenor8 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_MENOR_8_DATA);
		final Registro datosEntradaRegistroFechaNacimientoPersonaNumericoMayor8 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_MAYOR_8_DATA);
		final Registro datosEntradaRegistroFechaNacimientoPersonaNumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP, Constantes.FECHANACIMIENTOPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoFechaNacimientoPersonaRegistro
				.getTestRegistroFechaNacimientoPersonaVacio(datosEntradaRegistroFechaNacimientoPersonaVacio));
		datosPruebaAtributos
				.add(testAtributoFechaNacimientoPersonaRegistro.getTestRegistroFechaNacimientoPersonaNumericoEnhe(
						datosEntradaRegistroFechaNacimientoPersonaNumericoEnhe));
		datosPruebaAtributos
				.add(testAtributoFechaNacimientoPersonaRegistro.getTestRegistroFechaNacimientoPersonaNumericoAcentos(
						datosEntradaRegistroFechaNacimientoPersonaNumericoAcentos));
		datosPruebaAtributos.add(
				testAtributoFechaNacimientoPersonaRegistro.getTestRegistroFechaNacimientoPNumericoCaracteresEspeciales(
						datosEntradaRegistroFechaNacimientoPersonaNumericoCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoFechaNacimientoPersonaRegistro.getTestRegistroFechaNacimientoPAlfanumericoEspacios(
						datosEntradaRegistroFechaNacimientoPersonaNumericoEspacios));
		datosPruebaAtributos
				.add(testAtributoFechaNacimientoPersonaRegistro.getTestRegistroFechaNacimientoPAlfanumericoMenor8(
						datosEntradaRegistroFechaNacimientoPersonaNumericoMenor8));
		datosPruebaAtributos
				.add(testAtributoFechaNacimientoPersonaRegistro.getTestRegistroFechaNacimientoPAlfanumericoMayor8(
						datosEntradaRegistroFechaNacimientoPersonaNumericoMayor8));
		datosPruebaAtributos
				.add(testAtributoFechaNacimientoPersonaRegistro.getTestRegistroFechaNacimientoPersonaCorrectoNumerico(
						datosEntradaRegistroFechaNacimientoPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuario()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroUsuarioVacio = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_VACIO_DATA);
		final Registro datosEntradaRegistroUsuarioAlfanumericoEnhe = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ENHE_DATA);
		final Registro datosEntradaRegistroUsuarioAlfanumericoAcentos = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ACENTOS_DATA);
		final Registro datosEntradaRegistroUsuarioAlfanumericoCaracteresEspeciales = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO,
				Constantes.USUARIO_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroUsuarioAlfanumericoEspacios = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ESPACIOS_DATA);
		final Registro datosEntradaRegistroUsuarioAlfanumericoMenor3 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_MENOR_3_DATA);
		final Registro datosEntradaRegistroUsuarioAlfanumericoMayor45 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_MAYOR_45_DATA);
		final Registro datosEntradaRegistroUsuarioAlfabetico = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFABETICO_DATA);
		final Registro datosEntradaRegistroUsuarioAlfanumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_DATA);
		final Registro datosEntradaRegistroUsuarioNumerico = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO, Constantes.USUARIO_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoUsuarioRegistro.getTestRegistroUsuarioVacio(datosEntradaRegistroUsuarioVacio));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro
				.getTestRegistroUsuarioAlfanumericoEnhe(datosEntradaRegistroUsuarioAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro
				.getTestRegistroUsuarioAlfanumericoAcentos(datosEntradaRegistroUsuarioAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro.getTestRegistroUsuarioAlfanumericoCaracteresEspeciales(
				datosEntradaRegistroUsuarioAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro
				.getTestRegistroUsuarioAlfanumericoEspacios(datosEntradaRegistroUsuarioAlfanumericoEspacios));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro
				.getTestRegistroUsuarioAlfanumericoMenor3(datosEntradaRegistroUsuarioAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro
				.getTestRegistroUsuarioAlfanumericoMayor45(datosEntradaRegistroUsuarioAlfanumericoMayor45));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro
				.getTestRegistroUsuarioCorrectoAlfanumerico(datosEntradaRegistroUsuarioAlfabetico));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro
				.getTestRegistroUsuarioCorrectoAlfanumerico(datosEntradaRegistroUsuarioAlfanumerico));
		datosPruebaAtributos.add(testAtributoUsuarioRegistro
				.getTestRegistroUsuarioCorrectoAlfanumerico(datosEntradaRegistroUsuarioNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoContrasena()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroContrasenaVacio = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_VACIA_DATA);
		final Registro datosEntradaRegistroContrasenaAlfanumericoEnhe = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ENHE_DATA);
		final Registro datosEntradaRegistroContrasenaAlfanumericoAcentos = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ACENTOS_DATA);
		final Registro datosEntradaRegistroContrasenaAlfanumericoCaracteresEspeciales = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA,
				Constantes.CONTRASENA_ALFANUMERICA_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroContrasenaAlfanumericoEspacios = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ESPACIOS_DATA);
		final Registro datosEntradaRegistroContrasenaAlfanumericoMenor3 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_MENOR_3_DATA);
		final Registro datosEntradaRegistroContrasenaAlfanumericoMayor45 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_MAYOR_45_DATA);
		final Registro datosEntradaRegistroContrasenaAlfabetico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFABETICA_DATA);
		final Registro datosEntradaRegistroContrasenaAlfanumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_DATA);
		final Registro datosEntradaLoginContrasenaNumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_NUMERICA_DATA);

		datosPruebaAtributos.add(
				testAtributoContrasenaRegistro.getTestRegistroContrasenaVacio(datosEntradaRegistroContrasenaVacio));
		datosPruebaAtributos.add(testAtributoContrasenaRegistro
				.getTestRegistroContrasenaAlfanumericoAcentos(datosEntradaRegistroContrasenaAlfanumericoAcentos));
		datosPruebaAtributos
				.add(testAtributoContrasenaRegistro.getTestRegistroContrasenaAlfanumericoCaracteresEspeciales(
						datosEntradaRegistroContrasenaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoContrasenaRegistro
				.getTestRegistroContrasenaAlfanumericoEnhe(datosEntradaRegistroContrasenaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoContrasenaRegistro
				.getTestRegistroContrasenaAlfanumericoEspacios(datosEntradaRegistroContrasenaAlfanumericoEspacios));
		datosPruebaAtributos.add(testAtributoContrasenaRegistro
				.getTestRegistroContrasenaAlfanumericoMenor3(datosEntradaRegistroContrasenaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoContrasenaRegistro
				.getTestRegistroContrasenaAlfanumericoMayor45(datosEntradaRegistroContrasenaAlfanumericoMayor45));
		datosPruebaAtributos.add(testAtributoContrasenaRegistro
				.getTestRegistroContrasenaCorrectoAlfanumerico(datosEntradaRegistroContrasenaAlfabetico));
		datosPruebaAtributos.add(testAtributoContrasenaRegistro
				.getTestRegistroContrasenaCorrectoAlfanumerico(datosEntradaRegistroContrasenaAlfanumerico));
		datosPruebaAtributos.add(testAtributoContrasenaRegistro
				.getTestRegistroContrasenaCorrectoAlfanumerico(datosEntradaLoginContrasenaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoCifEmpresa()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroCifEmpresaVacio = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_VACIO_DATA);
		final Registro datosEntradaRegistroCifEmpresaAlfanumericoEnhe = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFAUMERICO_ENHE_DATA);
		final Registro datosEntradaRegistroCifEmpresaAlfanumericoAcentos = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_ACENTOS_DATA);
		final Registro datosEntradaRegistroCifEmpresaAlfanumericoCaracteresEspeciales = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA,
				Constantes.CIFEMPRESA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroCifEmpresaAlfanumericoEspacios = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_ESPACIOS_DATA);
		final Registro datosEntradaRegistroCifEmpresaAlfanumericoMenor9 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_MENOR_9_DATA);
		final Registro datosEntradaRegistroCifEmpresaAlfanumericoMayor9 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_MAYOR_9_DATA);
		final Registro datosEntradaRegistroCifEmpresaAlfabetico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFABETICO_DATA);
		final Registro datosEntradaRegistroCifEmpresaAlfanumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_DATA);
		final Registro datosEntradaRegistroCifEmpresaNumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_NUMERICO_DATA);

		datosPruebaAtributos.add(
				testAtributoCifEmpresaRegistro.getTestRegistroCifEmpresaVacio(datosEntradaRegistroCifEmpresaVacio));
		datosPruebaAtributos.add(testAtributoCifEmpresaRegistro
				.getTestRegistroCifEmpresaAlfanumericoEnhe(datosEntradaRegistroCifEmpresaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoCifEmpresaRegistro
				.getTestRegistroCifEmpresaAlfanumericoAcentos(datosEntradaRegistroCifEmpresaAlfanumericoAcentos));
		datosPruebaAtributos
				.add(testAtributoCifEmpresaRegistro.getTestRegistroCifEmpresaAlfanumericoCaracteresEspeciales(
						datosEntradaRegistroCifEmpresaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoCifEmpresaRegistro
				.getTestRegistroCifEmpresaAlfanumericoEspacios(datosEntradaRegistroCifEmpresaAlfanumericoEspacios));
		datosPruebaAtributos.add(testAtributoCifEmpresaRegistro
				.getTestRegistroCifEmpresaAlfanumericoMenor9(datosEntradaRegistroCifEmpresaAlfanumericoMenor9));
		datosPruebaAtributos.add(testAtributoCifEmpresaRegistro
				.getTestRegistroCifEmpresaAlfanumericoMayor9(datosEntradaRegistroCifEmpresaAlfanumericoMayor9));
		datosPruebaAtributos.add(testAtributoCifEmpresaRegistro
				.getTestRegistroCifEmpresaCorrectoAlfanumerico(datosEntradaRegistroCifEmpresaAlfabetico));
		datosPruebaAtributos.add(testAtributoCifEmpresaRegistro
				.getTestRegistroCifEmpresaCorrectoAlfanumerico(datosEntradaRegistroCifEmpresaAlfanumerico));
		datosPruebaAtributos.add(testAtributoCifEmpresaRegistro
				.getTestRegistroCifEmpresaCorrectoAlfanumerico(datosEntradaRegistroCifEmpresaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreEmpresa()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroNombreEmpresaVacio = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREEMPRESA, Constantes.NOMBREEMPRESA_VACIO_DATA);
		final Registro datosEntradaRegistroNombreEmpresaAlfanumericoCaracteresEspeciales = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREEMPRESA,
				Constantes.NOMBREEMPRESA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroNombreEmpresaAlfanumericoMenor3 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREEMPRESA,
				Constantes.NOMBREEMPRESA_ALFANUMERICO_MENOR_3_DATA);
		final Registro datosEntradaRegistroNombreEmpresaAlfanumericoMayor56 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREEMPRESA,
				Constantes.NOMBREEMPRESA_ALFANUMERICO_MAYOR_56_DATA);
		final Registro datosEntradaRegistroNombreEmpresaAlfabetico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREEMPRESA, Constantes.NOMBREEMPRESA_ALFABETICO_DATA);
		final Registro datosEntradaRegistroNombreEmpresaNumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREEMPRESA, Constantes.NOMBREEMPRESA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoNombreEmpresaRegistro
				.getTestRegistroNombreEmpresaVacio(datosEntradaRegistroNombreEmpresaVacio));
		datosPruebaAtributos
				.add(testAtributoNombreEmpresaRegistro.getTestRegistroNombreEmpresaAlfanumericoCaracteresEspeciales(
						datosEntradaRegistroNombreEmpresaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombreEmpresaRegistro
				.getTestRegistroNombreEmpresaAlfanumericoMenor3(datosEntradaRegistroNombreEmpresaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoNombreEmpresaRegistro
				.getTestRegistroNombreEmpresaAlfanumericoMayor56(datosEntradaRegistroNombreEmpresaAlfanumericoMayor56));
		datosPruebaAtributos.add(testAtributoNombreEmpresaRegistro
				.getTestRegistroNombreEmpresaCorrectoAlfabetico(datosEntradaRegistroNombreEmpresaAlfabetico));
		datosPruebaAtributos.add(testAtributoNombreEmpresaRegistro
				.getTestRegistroNombreEmpresaNumerico(datosEntradaRegistroNombreEmpresaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDireccionEmpresa()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroDireccionEmpresaVacio = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONEMPRESA, Constantes.DIRECCIONEMPRESA_VACIO_DATA);
		final Registro datosEntradaRegistroDireccionEmpresaAlfanumericoMenor3 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONEMPRESA,
				Constantes.DIRECCIONEMPRESA_ALFANUMERICO_MENOR_3_DATA);
		final Registro datosEntradaRegistroDireccionEmpresaAlfanumericoMayor128 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONEMPRESA,
				Constantes.DIRECCIONEMPRESA_ALFANUMERICO_MAYOR_128_DATA);
		final Registro datosEntradaRegistroDireccionEmpresaAlfabetico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONEMPRESA, Constantes.DIRECCIONEMPRESA_ALFABETICO_DATA);
		final Registro datosEntradaRegistroDireccionEmpresaAlfanumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONEMPRESA,
				Constantes.DIRECCIONEMPRESA_ALFANUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoDireccionEmpresaRegistro
				.getTestRegistroDireccionEmpresaVacio(datosEntradaRegistroDireccionEmpresaVacio));
		datosPruebaAtributos.add(testAtributoDireccionEmpresaRegistro.getTestRegistroDireccionEmpresaAlfanumericoMenor3(
				datosEntradaRegistroDireccionEmpresaAlfanumericoMenor3));
		datosPruebaAtributos
				.add(testAtributoDireccionEmpresaRegistro.getTestRegistroDireccionEmpresaAlfanumericoMayor128(
						datosEntradaRegistroDireccionEmpresaAlfanumericoMayor128));
		datosPruebaAtributos.add(testAtributoDireccionEmpresaRegistro
				.getTestRegistroDireccionEmpresaCorrectoAlfanumerico(datosEntradaRegistroDireccionEmpresaAlfabetico));
		datosPruebaAtributos.add(testAtributoDireccionEmpresaRegistro
				.getTestRegistroDireccionEmpresaCorrectoAlfanumerico(datosEntradaRegistroDireccionEmpresaAlfanumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTelefonoEmpresa()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final Registro datosEntradaRegistroTelefonoEmpresaVacio = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_VACIO_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaNumericoEnhe = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_NUMERICO_ENHE_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaNumericoAcentos = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_ACENTOS_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaNumericoCaracteresEspeciales = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaNumericoEspacios = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_ESPACIOS_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaNumericoMenor9 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_MENOR_9_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaNumericoMayor9 = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_MAYOR_9_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaAlfabetico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_ALFABETICO_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaAlfanumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_ALFANUMERICO_DATA);
		final Registro datosEntradaRegistroTelefonoEmpresaNumerico = generarJSON.generateRegistro(
				Constantes.URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoTelefonoEmpresaRegistro
				.getTestRegistroTelefonoEmpresaVacio(datosEntradaRegistroTelefonoEmpresaVacio));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresaRegistro
				.getTestRegistroTelefonoEmpresaNumericoEnhe(datosEntradaRegistroTelefonoEmpresaNumericoEnhe));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresaRegistro.getTestRegistroTelefonoEmpresaAlfanumericoEspacios(
				datosEntradaRegistroTelefonoEmpresaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresaRegistro
				.getTestRegistroTelefonoEmpresaNumericoAcentos(datosEntradaRegistroTelefonoEmpresaNumericoAcentos));
		datosPruebaAtributos
				.add(testAtributoTelefonoEmpresaRegistro.getTestRegistroTelefonoEmpresaNumericoCaracteresEspeciales(
						datosEntradaRegistroTelefonoEmpresaNumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresaRegistro
				.getTestRegistroTelefonoEmpresaNumericoMenor9(datosEntradaRegistroTelefonoEmpresaNumericoMenor9));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresaRegistro
				.getTestRegistroTelefonoEmpresaNumericoMayor9(datosEntradaRegistroTelefonoEmpresaNumericoMayor9));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresaRegistro
				.getTestRegistroTelefonoEmpresaCorrectoNumerico(datosEntradaRegistroTelefonoEmpresaAlfabetico));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresaRegistro
				.getTestRegistroTelefonoEmpresaCorrectoNumerico(datosEntradaRegistroTelefonoEmpresaAlfanumerico));
		datosPruebaAtributos.add(testAtributoTelefonoPersonaRegistro
				.getTestRegistroTelefonoPersonaCorrectoNumerico(datosEntradaRegistroTelefonoEmpresaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRegistro()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final Registro datosEntradaRegistroPersonaExiste = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ACCIONES, Constantes.PERSONA_YA_EXISTE);
		final Registro datosEntradaRegistroUsuarioExiste = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ACCIONES, Constantes.USUARIO_YA_EXISTE);
		final Registro datosEntradaRegistroEmpresaExiste = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ACCIONES, Constantes.EMPRESA_YA_EXISTE);
		final Registro datosEntradaRegistroPersonaVacia = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ACCIONES, Constantes.PERSONA_VACIO_DATA);
		final Registro datosEntradaRegistroUsuarioVacio = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ACCIONES, Constantes.USUARIO_VACIO_DATA);

		final Registro datosEntradaRegistroCorrecto = generarJSON
				.generateRegistro(Constantes.URL_JSON_REGISTRAR_ACCIONES, Constantes.REGISTRO_CORRECTO);

		datosPruebaAcciones.add(getTestRegistroPersonaYaExiste(datosEntradaRegistroPersonaExiste));
		datosPruebaAcciones.add(getTestRegistroUsuarioYaExiste(datosEntradaRegistroUsuarioExiste));
		datosPruebaAcciones.add(getTestRegistroEmpresaYaExiste(datosEntradaRegistroEmpresaExiste));
		datosPruebaAcciones.add(getTestRegistroPersonaVacia(datosEntradaRegistroPersonaVacia));
		datosPruebaAcciones.add(getTestRegistroUsuarioVacio(datosEntradaRegistroUsuarioVacio));

		datosPruebaAcciones.add(getTestRegistroCorrecto(datosEntradaRegistroCorrecto));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestRegistroPersonaYaExiste(final Registro datosEntradaRegistroPersonaYaExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = existeRegistro(datosEntradaRegistroPersonaYaExiste);

		final String resultadoEsperado = CodigosMensajes.REGISTRO_PERSONA_EXISTE + " - "
				+ Mensajes.REGISTRO_PERSONA_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PERSONA_YA_EXISTE, Constantes.ERROR,
				getValorRegistro(datosEntradaRegistroPersonaYaExiste));

	}

	private DatosPruebaAcciones getTestRegistroUsuarioYaExiste(final Registro datosEntradaRegistroUsuarioYaExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = existeRegistro(datosEntradaRegistroUsuarioYaExiste);

		final String resultadoEsperado = CodigosMensajes.REGISTRO_USUARIO_EXISTE + " - "
				+ Mensajes.REGISTRO_USUARIO_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.USUARIO_YA_EXISTE, Constantes.ERROR,
				getValorRegistro(datosEntradaRegistroUsuarioYaExiste));
	}

	private DatosPruebaAcciones getTestRegistroEmpresaYaExiste(final Registro datosEntradaRegistroEmpresaYaExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = existeRegistro(datosEntradaRegistroEmpresaYaExiste);

		final String resultadoEsperado = CodigosMensajes.REGISTRO_EMPRESA_EXISTE + " - "
				+ Mensajes.REGISTRO_EMPRESA_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.EMPRESA_YA_EXISTE, Constantes.ERROR,
				getValorRegistro(datosEntradaRegistroEmpresaYaExiste));
	}

	private DatosPruebaAcciones getTestRegistroPersonaVacia(final Registro datosEntradaRegistroPersonaVacia)
			throws java.text.ParseException {

		final String resultadoObtenido = existeRegistro(datosEntradaRegistroPersonaVacia);

		final String resultadoEsperado = CodigosMensajes.REGISTRO_PERSONA_VACIA + " - "
				+ Mensajes.REGISTRO_PERSONA_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PERSONA_VACIA, Constantes.ERROR, getValorRegistro(datosEntradaRegistroPersonaVacia));
	}

	private DatosPruebaAcciones getTestRegistroUsuarioVacio(final Registro datosEntradaRegistroUsuarioVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = existeRegistro(datosEntradaRegistroUsuarioVacio);

		final String resultadoEsperado = CodigosMensajes.REGISTRO_USUARIO_VACIO + " - "
				+ Mensajes.REGISTRO_USUARIO_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.USUARIO_VACIO, Constantes.ERROR, getValorRegistro(datosEntradaRegistroUsuarioVacio));
	}

	private DatosPruebaAcciones getTestRegistroCorrecto(final Registro datosEntradaRegistroCorrecto)
			throws java.text.ParseException {

		final String resultadoObtenido = existeRegistro(datosEntradaRegistroCorrecto);

		final String resultadoEsperado = CodigosMensajes.REGISTRO_CORRECTO + " - " + Mensajes.REGISTRO_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.REGISTRO_CORRECTO, Constantes.EXITO, getValorRegistro(datosEntradaRegistroCorrecto));
	}

	private String existeRegistro(final Registro registro) throws java.text.ParseException {

		if (!validaciones.comprobarPersonaBlank(registro.getDatosPersona())) {
			return CodigosMensajes.REGISTRO_PERSONA_VACIA + " - " + Mensajes.REGISTRO_PERSONA_VACIA;
		} else if (!validaciones.comprobarUsuarioBlank(registro.getDatosUsuario())) {
			return CodigosMensajes.REGISTRO_USUARIO_VACIO + " - " + Mensajes.REGISTRO_USUARIO_VACIO;
		} else {
			final Optional<PersonaEntity> persona = personaRepository.findById(registro.getDatosPersona().getDniP());

			if (!persona.isPresent()) {
				final UsuarioEntity usuario = usuarioRepository.findByUsuario(registro.getDatosUsuario().getUsuario());

				if (usuario == null) {
					if (registro.getDatosEmpresa() != null) {
						if (registro.getDatosEmpresa().getIdEmpresa() == null) {
							if (validaciones.comprobarEmpresaBlank(registro.getDatosEmpresa())) {
								final EmpresaEntity empresa = empresaRepository
										.findByCif(registro.getDatosEmpresa().getCifEmpresa());

								if (empresa != null) {
									return CodigosMensajes.REGISTRO_EMPRESA_EXISTE + " - "
											+ Mensajes.REGISTRO_EMPRESA_YA_EXISTE;
								} else {
									registro.getDatosEmpresa().setBorradoEmpresa(0);
									empresaRepository.saveAndFlush(registro.getDatosEmpresa());
									registro.getDatosPersona().setEmpresa(registro.getDatosEmpresa());
								}
							}
						} else {
							final Optional<EmpresaEntity> empresa = empresaRepository
									.findById(registro.getDatosEmpresa().getIdEmpresa());

							if (empresa.isPresent()) {
								registro.setDatosEmpresa(empresa.get());
								registro.getDatosPersona().setEmpresa(registro.getDatosEmpresa());
							} else {
								return CodigosMensajes.EMPRESA_NO_EXISTE + " - " + Mensajes.EMPRESA_NO_EXISTE;
							}

						}
					}
					registro.getDatosPersona().setBorradoP(0);
					registro.getDatosPersona().setUsuario(null);
					personaRepository.saveAndFlush(registro.getDatosPersona());

					final RolEntity rol = rolRepository.findByRolName(Constantes.USUARIO);
					registro.getDatosUsuario().setRol(rol);
					registro.getDatosUsuario().setPersona(registro.getDatosPersona());
					registro.getDatosUsuario().setDniUsuario(registro.getDatosPersona().getDniP());
					registro.getDatosUsuario().setBorradoUsuario(0);
					usuarioRepository.saveAndFlush(registro.getDatosUsuario());

					usuarioRepository.deleteUsuario(registro.getDatosUsuario().getDniUsuario());
					personaRepository.deletePersona(registro.getDatosPersona().getDniP());
					empresaRepository.deleteEmpresa(registro.getDatosEmpresa().getCifEmpresa());

					return CodigosMensajes.REGISTRO_CORRECTO + " - " + Mensajes.REGISTRO_CORRECTO;

				} else {
					return CodigosMensajes.REGISTRO_USUARIO_EXISTE + " - " + Mensajes.REGISTRO_USUARIO_YA_EXISTE;
				}
			} else {
				return CodigosMensajes.REGISTRO_PERSONA_EXISTE + " - " + Mensajes.REGISTRO_PERSONA_YA_EXISTE;
			}
		}
	}

	private Map<String, String> getValorRegistro(final Registro registro) {

		final Map<String, String> valor = new HashMap<>();
		valor.put(Constantes.DNIP, registro.getDatosPersona().getDniP());
		valor.put(Constantes.NOMBREP, registro.getDatosPersona().getNombreP());
		valor.put(Constantes.APELLIDOSP, registro.getDatosPersona().getApellidosP());
		valor.put(Constantes.FECHANACP, registro.getDatosPersona().getFechaNacP().toString());
		valor.put(Constantes.DIRECCIONP, registro.getDatosPersona().getDireccionP());
		valor.put(Constantes.EMAILP, registro.getDatosPersona().getEmailP());
		valor.put(Constantes.TELEFONOP, registro.getDatosPersona().getTelefonoP());
		valor.put(Constantes.USUARIO, registro.getDatosUsuario().getUsuario());
		valor.put(Constantes.PASSWD_USUARIO, registro.getDatosUsuario().getPasswdUsuario());
		valor.put(Constantes.CIF_EMPRESA, registro.getDatosEmpresa().getCifEmpresa());
		valor.put(Constantes.NOMBRE_EMPRESA, registro.getDatosEmpresa().getNombreEmpresa());
		valor.put(Constantes.DIRECCION_EMPRESA, registro.getDatosEmpresa().getDireccionEmpresa());
		valor.put(Constantes.TELEFONO_EMPRESA, registro.getDatosEmpresa().getTelefonoEmpresa());

		return valor;
	}

}
