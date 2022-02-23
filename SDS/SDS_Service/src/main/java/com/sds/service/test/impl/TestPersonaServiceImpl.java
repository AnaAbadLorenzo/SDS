package com.sds.service.test.impl;

import java.io.IOException;
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
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.persona.model.PersonaAnadir;
import com.sds.service.test.TestPersonaService;
import com.sds.service.test.impl.atributos.TestAtributoApellidosPersona;
import com.sds.service.test.impl.atributos.TestAtributoContrasenaUsuario;
import com.sds.service.test.impl.atributos.TestAtributoDireccionPersona;
import com.sds.service.test.impl.atributos.TestAtributoEmailPersona;
import com.sds.service.test.impl.atributos.TestAtributoFechaNacP;
import com.sds.service.test.impl.atributos.TestAtributoNombrePersona;
import com.sds.service.test.impl.atributos.TestAtributoNombreUsuario;
import com.sds.service.test.impl.atributos.TestAtributoPersonaDni;
import com.sds.service.test.impl.atributos.TestAtributoTelefonoPersona;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestPersonaServiceImpl implements TestPersonaService {

	private final TestAtributoPersonaDni testAtributoDniPersona;
	private final TestAtributoNombrePersona testAtributoNombrePersona;
	private final TestAtributoApellidosPersona testAtributoApellidosPersona;
	private final TestAtributoDireccionPersona testAtributoDireccionPersona;
	private final TestAtributoFechaNacP testAtributoFechaNacP;
	private final TestAtributoEmailPersona testAtributoEmailPersona;
	private final TestAtributoTelefonoPersona testAtributoTelefonoPersona;
	private final TestAtributoNombreUsuario testAtributoUsuario;
	private final TestAtributoContrasenaUsuario testAtributoContrasenaUsuario;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	PersonaRepository personaRepository;

	public TestPersonaServiceImpl() {
		testAtributoDniPersona = new TestAtributoPersonaDni();
		testAtributoNombrePersona = new TestAtributoNombrePersona();
		testAtributoApellidosPersona = new TestAtributoApellidosPersona();
		testAtributoDireccionPersona = new TestAtributoDireccionPersona();
		testAtributoFechaNacP = new TestAtributoFechaNacP();
		testAtributoEmailPersona = new TestAtributoEmailPersona();
		testAtributoTelefonoPersona = new TestAtributoTelefonoPersona();
		testAtributoUsuario = new TestAtributoNombreUsuario();
		testAtributoContrasenaUsuario = new TestAtributoContrasenaUsuario();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		this.validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDniP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaDniPersonaVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIP_VACIO_DATA);

		final PersonaEntity datosEntradaDniPersonaAlfanumericoEnhe = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ENHE_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumericoAcentos = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ACENTOS_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumericoCaracteresEspeciales = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP,
				Constantes.DNIPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumericoEspacios = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ESPACIOS_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumericoMenor9 = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIP_ALFANUMERICO_MENOR_9_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumericoMayor9 = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIP_ALFANUMERICO_MAYOR_9_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfabetico = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFABETICO_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumerico = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_DATA);
		final PersonaEntity datosEntradaDniPersonaNumerico = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoDniPersona.getTestDniPersonaVacio(datosEntradaDniPersonaVacio));

		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPersonaAlfanumericoEnhe(datosEntradaDniPersonaAlfanumericoEnhe));
		datosPruebaAtributos.add(
				testAtributoDniPersona.getTestDniPersonaAlfanumericoAcentos(datosEntradaDniPersonaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoDniPersona
				.getTestDniPAlfanumericoCaracteresEspeciales(datosEntradaDniPersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(
				testAtributoDniPersona.getTestDniPAlfanumericoEspacios(datosEntradaDniPersonaAlfanumericoEspacios));
		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPAlfanumericoMenor9(datosEntradaDniPersonaAlfanumericoMenor9));
		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPAlfanumericoMayor9(datosEntradaDniPersonaAlfanumericoMayor9));
		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPCorrectoAlfanumerico(datosEntradaDniPersonaAlfabetico));
		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPCorrectoAlfanumerico(datosEntradaDniPersonaAlfanumerico));
		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPCorrectoAlfanumerico(datosEntradaDniPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDniPBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaDniPersonaAlfanumericoEnhe = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ENHE_DATA);

		final PersonaEntity datosEntradaDniPersonaAlfanumericoAcentos = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ACENTOS_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumericoCaracteresEspeciales = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP,
				Constantes.DNIPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumericoEspacios = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_ESPACIOS_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumericoMayor9 = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIP_ALFANUMERICO_MAYOR_9_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfabetico = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFABETICO_DATA);
		final PersonaEntity datosEntradaDniPersonaAlfanumerico = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_ALFANUMERICO_DATA);
		final PersonaEntity datosEntradaDniPersonaNumerico = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_DNIP, Constantes.DNIPERSONA_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPersonaAlfanumericoEnhe(datosEntradaDniPersonaAlfanumericoEnhe));

		datosPruebaAtributos.add(
				testAtributoDniPersona.getTestDniPersonaAlfanumericoAcentos(datosEntradaDniPersonaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoDniPersona
				.getTestDniPAlfanumericoCaracteresEspeciales(datosEntradaDniPersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(
				testAtributoDniPersona.getTestDniPAlfanumericoEspacios(datosEntradaDniPersonaAlfanumericoEspacios));

		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPAlfanumericoMayor9(datosEntradaDniPersonaAlfanumericoMayor9));
		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPCorrectoAlfanumerico(datosEntradaDniPersonaAlfabetico));
		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPCorrectoAlfanumerico(datosEntradaDniPersonaAlfanumerico));
		datosPruebaAtributos
				.add(testAtributoDniPersona.getTestDniPCorrectoAlfanumerico(datosEntradaDniPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaNombrePersonaVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_VACIO_DATA);
		final PersonaEntity datosEntradaNombrePersonaAlfanumericoCaracteresEspeciales = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP,
				Constantes.NOMBREPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaNombrePersonaAlfanumericoMenor3 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_ALFANUMERICO_MENOR_3_DATA);
		final PersonaEntity datosEntradaNombrePersonaAlfanumericoMayor56 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_ALFANUMERICO_MAYOR_56_DATA);
		final PersonaEntity datosEntradaNombrePersonaAlfabetico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_ALFABETICO_DATA);
		final PersonaEntity datosEntradaNombrePNumerico = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoNombrePersona.getTestNombrePersonaVacio(datosEntradaNombrePersonaVacio));
		datosPruebaAtributos.add(testAtributoNombrePersona.getTestNombrePAlfanumericoCaracteresEspeciales(
				datosEntradaNombrePersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombrePersona
				.getTestNombrePAlfanumericoMenor3(datosEntradaNombrePersonaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoNombrePersona
				.getTestNombrePAlfanumericoMayor56(datosEntradaNombrePersonaAlfanumericoMayor56));
		datosPruebaAtributos
				.add(testAtributoNombrePersona.getTestNombrePCorrectoAlfabetico(datosEntradaNombrePersonaAlfabetico));
		datosPruebaAtributos.add(testAtributoNombrePersona.getTestNombrePNumerico(datosEntradaNombrePNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombrePBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaNombrePersonaAlfanumericoCaracteresEspeciales = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP,
				Constantes.NOMBREPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaNombrePersonaAlfanumericoMayor56 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_ALFANUMERICO_MAYOR_56_DATA);
		final PersonaEntity datosEntradaNombrePersonaAlfabetico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_ALFABETICO_DATA);
		final PersonaEntity datosEntradaNombrePNumerico = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_NOMBREP, Constantes.NOMBREPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoNombrePersona.getTestNombrePAlfanumericoCaracteresEspeciales(
				datosEntradaNombrePersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombrePersona
				.getTestNombrePAlfanumericoMayor56(datosEntradaNombrePersonaAlfanumericoMayor56));
		datosPruebaAtributos
				.add(testAtributoNombrePersona.getTestNombrePCorrectoAlfabetico(datosEntradaNombrePersonaAlfabetico));
		datosPruebaAtributos.add(testAtributoNombrePersona.getTestNombrePNumerico(datosEntradaNombrePNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoApellidosP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaApellidosPersonaVacio = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP, Constantes.APELLIDOSPERSONA_VACIOS_DATA);
		final PersonaEntity datosEntradaApellidosPersonaAlfanumericoCaracteresEspeciales = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP,
				Constantes.APELLIDOSPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaApellidosPersonaAlfanumericoMenor3 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP,
				Constantes.APELLIDOSPERSONA_ALFANUMERICO_MENOR_3_DATA);
		final PersonaEntity datosEntradaApellidosePersonaAlfanumericoMayor128 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP,
				Constantes.APELLIDOSPERSONA_ALFANUMERICO_MAYOR_128_DATA);
		final PersonaEntity datosEntradaApellidosPersonaNumerico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP, Constantes.APELLIDOSPERSONA_NUMERICO_DATA);
		final PersonaEntity datosEntradaApellidosPersonaAlfabetico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP, Constantes.APELLIDOSPERSONA_ALFABETICO_DATA);

		datosPruebaAtributos
				.add(testAtributoApellidosPersona.getTestApellidosPersonaVacio(datosEntradaApellidosPersonaVacio));
		datosPruebaAtributos.add(testAtributoApellidosPersona.getTestApellidosPAlfanumericoCaracteresEspeciales(
				datosEntradaApellidosPersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoApellidosPersona
				.getTestApellidosPAlfanumericoMenor3(datosEntradaApellidosPersonaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoApellidosPersona
				.getTestApellidosPAlfanumericoMayor128(datosEntradaApellidosePersonaAlfanumericoMayor128));
		datosPruebaAtributos
				.add(testAtributoApellidosPersona.getTestApellidosPNumerico(datosEntradaApellidosPersonaNumerico));
		datosPruebaAtributos.add(testAtributoApellidosPersona
				.getTestApellidosPCorrectoAlfabetico(datosEntradaApellidosPersonaAlfabetico));
		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoApellidosPBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaApellidosPersonaAlfanumericoCaracteresEspeciales = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP,
				Constantes.APELLIDOSPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaApellidosePersonaAlfanumericoMayor128 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP,
				Constantes.APELLIDOSPERSONA_ALFANUMERICO_MAYOR_128_DATA);
		final PersonaEntity datosEntradaApellidosPersonaNumerico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP, Constantes.APELLIDOSPERSONA_NUMERICO_DATA);
		final PersonaEntity datosEntradaApellidosPersonaAlfabetico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP, Constantes.APELLIDOSPERSONA_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoApellidosPersona.getTestApellidosPAlfanumericoCaracteresEspeciales(
				datosEntradaApellidosPersonaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoApellidosPersona
				.getTestApellidosPAlfanumericoMayor128(datosEntradaApellidosePersonaAlfanumericoMayor128));
		datosPruebaAtributos
				.add(testAtributoApellidosPersona.getTestApellidosPNumerico(datosEntradaApellidosPersonaNumerico));
		datosPruebaAtributos.add(testAtributoApellidosPersona
				.getTestApellidosPCorrectoAlfabetico(datosEntradaApellidosPersonaAlfabetico));
		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDireccionP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaDireccionPersonaVacio = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP, Constantes.DIRECCIONPERSONA_VACIA_DATA);
		final PersonaEntity datosEntradaDireccionPersonaAlfanumericoMenor3 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP,
				Constantes.DIRECCIONPERSONA_ALFANUMERICA_MENOR_3_DATA);
		final PersonaEntity datosEntradaDireccionPersonaAlfanumericoMayor128 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP,
				Constantes.DIRECCIONPERSONA_ALFANUMERICA_MAYOR_128_DATA);
		final PersonaEntity datosEntradaDireccionPersonaAlfabetico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP, Constantes.DIRECCIONPERSONA_ALFABETICO_DATA);
		final PersonaEntity datosEntradaDireccionPersonaAlfanumerico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP, Constantes.DIRECCIONPERSONA_ALFANUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoDireccionPersona.getTestDireccionPersonaVacio(datosEntradaDireccionPersonaVacio));
		datosPruebaAtributos.add(testAtributoDireccionPersona
				.getTestDireccionPersonaAlfanumericoMenor3(datosEntradaDireccionPersonaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoDireccionPersona
				.getTestDireccionPersonaAlfanumericoMayor128(datosEntradaDireccionPersonaAlfanumericoMayor128));
		datosPruebaAtributos.add(testAtributoDireccionPersona
				.getTestDireccionPCorrectoAlfanumerico(datosEntradaDireccionPersonaAlfabetico));
		datosPruebaAtributos.add(testAtributoDireccionPersona
				.getTestDireccionPCorrectoAlfanumerico(datosEntradaDireccionPersonaAlfanumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDireccionPBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaDireccionPersonaAlfanumericoMayor128 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP,
				Constantes.DIRECCIONPERSONA_ALFANUMERICA_MAYOR_128_DATA);
		final PersonaEntity datosEntradaDireccionPersonaAlfabetico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP, Constantes.DIRECCIONPERSONA_ALFABETICO_DATA);
		final PersonaEntity datosEntradaDireccionPersonaAlfanumerico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP, Constantes.DIRECCIONPERSONA_ALFANUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoDireccionPersona
				.getTestDireccionPersonaAlfanumericoMayor128(datosEntradaDireccionPersonaAlfanumericoMayor128));
		datosPruebaAtributos.add(testAtributoDireccionPersona
				.getTestDireccionPCorrectoAlfanumerico(datosEntradaDireccionPersonaAlfabetico));
		datosPruebaAtributos.add(testAtributoDireccionPersona
				.getTestDireccionPCorrectoAlfanumerico(datosEntradaDireccionPersonaAlfanumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTelefonoP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaTelefonoPersonaVacio = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_VACIO_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoEnhe = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ENHE_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoAcentos = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ACENTOS_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoCaracteresEspeciales = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP,
				Constantes.TELEFONOPERSONA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoEspacios = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ESPACIOS_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoMenor9 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_MENOR_9_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoMayor9 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_MAYOR_9_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumerico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoTelefonoPersona.getTestTelefonoPersonaVacio(datosEntradaTelefonoPersonaVacio));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPersonaNumericoEnhe(datosEntradaTelefonoPersonaNumericoEnhe));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPersonaNumericoAcentos(datosEntradaTelefonoPersonaNumericoAcentos));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPNumericoCaracteresEspeciales(datosEntradaTelefonoPersonaNumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPNumericoEspacios(datosEntradaTelefonoPersonaNumericoEspacios));
		datosPruebaAtributos.add(
				testAtributoTelefonoPersona.getTestTelefonoPNumericoMenor9(datosEntradaTelefonoPersonaNumericoMenor9));
		datosPruebaAtributos.add(
				testAtributoTelefonoPersona.getTestTelefonoPNumericoMayor9(datosEntradaTelefonoPersonaNumericoMayor9));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPersonaCorrectoNumerico(datosEntradaTelefonoPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTelefonoPBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaTelefonoPersonaNumericoEnhe = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ENHE_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoAcentos = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ACENTOS_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoCaracteresEspeciales = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP,
				Constantes.TELEFONOPERSONA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumericoEspacios = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_ESPACIOS_DATA);

		final PersonaEntity datosEntradaTelefonoPersonaNumericoMayor9 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_MAYOR_9_DATA);
		final PersonaEntity datosEntradaTelefonoPersonaNumerico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP, Constantes.TELEFONOPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPersonaNumericoEnhe(datosEntradaTelefonoPersonaNumericoEnhe));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPersonaNumericoAcentos(datosEntradaTelefonoPersonaNumericoAcentos));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPNumericoCaracteresEspeciales(datosEntradaTelefonoPersonaNumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPNumericoEspacios(datosEntradaTelefonoPersonaNumericoEspacios));
		datosPruebaAtributos.add(
				testAtributoTelefonoPersona.getTestTelefonoPNumericoMayor9(datosEntradaTelefonoPersonaNumericoMayor9));
		datosPruebaAtributos.add(testAtributoTelefonoPersona
				.getTestTelefonoPersonaCorrectoNumerico(datosEntradaTelefonoPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoEmailP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaEmailPersonaVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_VACIO_DATA);
		final PersonaEntity datosEntradaEmailPersonaAlfanumericoEnhe = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ENHE_DATA);
		final PersonaEntity datosEntradaEmailPersonaAlfanumericoAcentos = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ACENTOS_DATA);
		final PersonaEntity datosEntradaEmailPersonaAlfaNumericoEspacios = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ESPACIOS_DATA);
		final PersonaEntity datosEntradaEmailPersonaAlfaNumericoMenor4 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_MENOR_4_DATA);
		final PersonaEntity datosEntradaEmailPersonaAlfaNumericoMayor128 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_MAYOR_128_DATA);
		final PersonaEntity datosEntradaEmailPersonaFormatoCorrecto = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAILPERSONA_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoEmailPersona.getTestEmailPersonaVacio(datosEntradaEmailPersonaVacio));
		datosPruebaAtributos.add(
				testAtributoEmailPersona.getTestEmailPersonaAlfanumericoEnhe(datosEntradaEmailPersonaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoEmailPersona
				.getTestEmailPersonaAlfanumericoAcentos(datosEntradaEmailPersonaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoEmailPersona
				.getTestEmailPAlfanumericoEspacios(datosEntradaEmailPersonaAlfaNumericoEspacios));
		datosPruebaAtributos.add(
				testAtributoEmailPersona.getTestEmailPAlfanumericoMenor4(datosEntradaEmailPersonaAlfaNumericoMenor4));
		datosPruebaAtributos.add(testAtributoEmailPersona
				.getTestEmailPAlfanumericoMayor128(datosEntradaEmailPersonaAlfaNumericoMayor128));
		datosPruebaAtributos.add(
				testAtributoEmailPersona.getTestEmailPCorrectoAlfanumerico(datosEntradaEmailPersonaFormatoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoEmailPBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaEmailPersonaAlfanumericoEnhe = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ENHE_DATA);
		final PersonaEntity datosEntradaEmailPersonaAlfanumericoAcentos = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ACENTOS_DATA);
		final PersonaEntity datosEntradaEmailPersonaAlfaNumericoEspacios = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_ESPACIOS_DATA);
		final PersonaEntity datosEntradaEmailPersonaAlfaNumericoMayor128 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAIL_ALFANUMERICO_MAYOR_128_DATA);
		final PersonaEntity datosEntradaEmailPersonaFormatoCorrecto = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_EMAILP, Constantes.EMAILPERSONA_ALFABETICO_DATA);

		datosPruebaAtributos.add(
				testAtributoEmailPersona.getTestEmailPersonaAlfanumericoEnhe(datosEntradaEmailPersonaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoEmailPersona
				.getTestEmailPersonaAlfanumericoAcentos(datosEntradaEmailPersonaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoEmailPersona
				.getTestEmailPAlfanumericoEspacios(datosEntradaEmailPersonaAlfaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoEmailPersona
				.getTestEmailPAlfanumericoMayor128(datosEntradaEmailPersonaAlfaNumericoMayor128));
		datosPruebaAtributos.add(
				testAtributoEmailPersona.getTestEmailPCorrectoAlfanumerico(datosEntradaEmailPersonaFormatoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaNacP()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaFechaNacimientoPersonaVacio = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_VACIA_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoEnhe = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ENHE_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoAcentos = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ACENTOS_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoCaracteresEspeciales = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP,
						Constantes.FECHANACPERSONA_NUMERICA_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoEspacios = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ACENTOS_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoMenor8 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_MENOR_8_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoMayor8 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_MAYOR_8_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumerico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACIMIENTOPERSONA_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoFechaNacP.getTestFechaNacimientoPersonaVacio(datosEntradaFechaNacimientoPersonaVacio));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPersonaNumericoEnhe(datosEntradaFechaNacimientoPersonaNumericoEnhe));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPersonaNumericoAcentos(datosEntradaFechaNacimientoPersonaNumericoAcentos));
		datosPruebaAtributos.add(testAtributoFechaNacP.getTestFechaNacimientoPNumericoCaracteresEspeciales(
				datosEntradaFechaNacimientoPersonaNumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPAlfanumericoEspacios(datosEntradaFechaNacimientoPersonaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPAlfanumericoMenor8(datosEntradaFechaNacimientoPersonaNumericoMenor8));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPAlfanumericoMayor8(datosEntradaFechaNacimientoPersonaNumericoMayor8));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPersonaCorrectoNumerico(datosEntradaFechaNacimientoPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaNacPBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoEnhe = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ENHE_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoAcentos = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ACENTOS_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoCaracteresEspeciales = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP,
						Constantes.FECHANACPERSONA_NUMERICA_CARACTERES_ESPECIALES_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoEspacios = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_ACENTOS_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoMayor8 = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACPERSONA_NUMERICA_MAYOR_8_DATA);
		final PersonaEntity datosEntradaFechaNacimientoPersonaNumerico = generarJSON.generatePersona(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_FECHANACP, Constantes.FECHANACIMIENTOPERSONA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPersonaNumericoEnhe(datosEntradaFechaNacimientoPersonaNumericoEnhe));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPersonaNumericoAcentos(datosEntradaFechaNacimientoPersonaNumericoAcentos));
		datosPruebaAtributos.add(testAtributoFechaNacP.getTestFechaNacimientoPNumericoCaracteresEspeciales(
				datosEntradaFechaNacimientoPersonaNumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPAlfanumericoEspacios(datosEntradaFechaNacimientoPersonaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPAlfanumericoMayor8(datosEntradaFechaNacimientoPersonaNumericoMayor8));
		datosPruebaAtributos.add(testAtributoFechaNacP
				.getTestFechaNacimientoPersonaCorrectoNumerico(datosEntradaFechaNacimientoPersonaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuario()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final UsuarioEntity datosEntradaUsuarioVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_VACIO_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoEnhe = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ENHE_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoAcentos = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ACENTOS_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoCaracteresEspeciales = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO,
				Constantes.USUARIO_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoEspacios = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ESPACIOS_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoMenor3 = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_MENOR_3_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumericoMayor45 = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_MAYOR_45_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfabetico = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFABETICO_DATA);
		final UsuarioEntity datosEntradaUsuarioAlfanumerico = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_DATA);
		final UsuarioEntity datosEntradaUsuarioNumerico = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_ATRIBUTOS_USUARIO, Constantes.USUARIO_NUMERICO_DATA);

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
	public List<DatosPruebaAtributos> getPruebasAtributoContrasena()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final UsuarioEntity datosEntradaContrasenaVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_VACIA_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoEnhe = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ENHE_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoAcentos = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ACENTOS_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoCaracteresEspeciales = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA,
				Constantes.CONTRASENA_ALFANUMERICA_CARACTERES_ESPECIALES_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoEspacios = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_ESPACIOS_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoMenor3 = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_MENOR_3_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumericoMayor45 = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_MAYOR_45_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfabetico = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFABETICA_DATA);
		final UsuarioEntity datosEntradaContrasenaAlfanumerico = generarJSON.generateUsuario(
				Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_ALFANUMERICA_DATA);
		final UsuarioEntity datosEntradaContrasenaNumerico = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA, Constantes.CONTRASENA_NUMERICA_DATA);

		datosPruebaAtributos.add(testAtributoContrasenaUsuario.getTestContrasenaVacio(datosEntradaContrasenaVacio));
		datosPruebaAtributos.add(testAtributoContrasenaUsuario
				.getTestContrasenaAlfanumericoAcentos(datosEntradaContrasenaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoContrasenaUsuario.getTestContrasenaAlfanumericoCaracteresEspeciales(
				datosEntradaContrasenaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoContrasenaUsuario
				.getTestContrasenaAlfanumericoEnhe(datosEntradaContrasenaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoContrasenaUsuario
				.getTestContrasenaAlfanumericoEspacios(datosEntradaContrasenaAlfanumericoEspacios));
		datosPruebaAtributos.add(testAtributoContrasenaUsuario
				.getTestContrasenaAlfanumericoMenor3(datosEntradaContrasenaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoContrasenaUsuario
				.getTestContrasenaAlfanumericoMayor45(datosEntradaContrasenaAlfanumericoMayor45));
		datosPruebaAtributos.add(
				testAtributoContrasenaUsuario.getTestContrasenaCorrectoAlfanumerico(datosEntradaContrasenaAlfabetico));
		datosPruebaAtributos.add(testAtributoContrasenaUsuario
				.getTestContrasenaCorrectoAlfanumerico(datosEntradaContrasenaAlfanumerico));
		datosPruebaAtributos.add(
				testAtributoContrasenaUsuario.getTestContrasenaCorrectoAlfanumerico(datosEntradaContrasenaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPersonaBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final PersonaEntity datosEntradaBuscarPersonaCorrecto = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.BUSCAR_PERSONA);
		final PersonaEntity datosEntradaBuscarPersonaDniVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.DNIP_VACIO_DATA);
		final PersonaEntity datosEntradaBuscarPersonaNombreVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.NOMBREPERSONA_VACIO_DATA);
		final PersonaEntity datosEntradaBuscarPersonaApellidosVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.APELLIDOSPERSONA_VACIOS_DATA);
		final PersonaEntity datosEntradaBuscarPersonaDireccionVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.DIRECCIONPERSONA_VACIA_DATA);
		final PersonaEntity datosEntradaBuscarPersonaFechaNacVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.FECHANACPERSONA_VACIA_DATA);
		final PersonaEntity datosEntradaBuscarPersonaTelefonoVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.TELEFONOPERSONA_VACIO_DATA);
		final PersonaEntity datosEntradaBuscarPersonaEmailVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.EMAIL_VACIO_DATA);
		final PersonaEntity datosEntradaBuscarParametrosPersonaVacios = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.PARAMETROS_PERSONA_VACIOS_DATA);

		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarPersonaCorrecto));
		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarPersonaDniVacio));
		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarPersonaNombreVacio));
		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarPersonaApellidosVacio));
		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarPersonaDireccionVacio));
		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarPersonaFechaNacVacio));
		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarPersonaTelefonoVacio));
		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarPersonaEmailVacio));
		datosPruebaAcciones.add(getTestBuscarPersona(datosEntradaBuscarParametrosPersonaVacios));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPersonaGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final UsuarioEntity datosEntradaUsuario = generarJSON.generateUsuario(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.GUARDAR_USUARIO);

		final EmpresaEntity empresa = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);

		final PersonaEntity datosEntradaAnadirPersonaCorrecto = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.GUARDAR_PERSONA);
		datosEntradaAnadirPersonaCorrecto.setEmpresa(empresa);
		final PersonaEntity datosEntradPersonaNoExiste = generarJSON.generatePersona(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.PERSONA_NO_EXISTE);
		datosEntradPersonaNoExiste.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirPersonaDniVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.DNIP_VACIO_DATA);
		datosEntradaAnadirPersonaDniVacio.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirPersonaNombreVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.NOMBREPERSONA_VACIO_DATA);
		datosEntradaAnadirPersonaNombreVacio.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirPersonaApellidosVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.APELLIDOSPERSONA_VACIOS_DATA);
		datosEntradaAnadirPersonaApellidosVacio.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirPersonaDireccionVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.DIRECCIONPERSONA_VACIA_DATA);
		datosEntradaAnadirPersonaDireccionVacio.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirPersonaFechaNacVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.FECHANACPERSONA_VACIA_DATA);
		datosEntradaAnadirPersonaFechaNacVacio.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirPersonaTelefonoVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.TELEFONOPERSONA_VACIO_DATA);
		datosEntradaAnadirPersonaTelefonoVacio.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirPersonaEmailVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.EMAIL_VACIO_DATA);
		datosEntradaAnadirPersonaEmailVacio.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirParametrosPersonaVacios = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.PARAMETROS_PERSONA_VACIOS_DATA);
		datosEntradaAnadirParametrosPersonaVacios.setEmpresa(empresa);
		final PersonaEntity datosEntradaAnadirPersonaYaExiste = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.PERSONA_YA_EXISTE);
		datosEntradaAnadirPersonaYaExiste.setEmpresa(empresa);

		final UsuarioEntity datosEntradaAnadirUsuarioYaExiste = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_DATA, Constantes.USUARIO_YA_EXISTE);
		final UsuarioEntity datosEntradaUsuarioVacio = generarJSON.generateUsuario(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.USUARIO_VACIO_DATA);
		final UsuarioEntity datosEntradaUsuarioContrasenaVacio = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_DATA, Constantes.CONTRASENA_VACIA_DATA);
		final UsuarioEntity datosEntradaUsuarioParametrosVacios = generarJSON
				.generateUsuario(Constantes.URL_JSON_PERSONA_DATA, Constantes.PARAMETROS_USUARIO_VACIOS);

		datosPruebaAcciones.add(getTestGuardarPersonaCorrecto(
				new PersonaAnadir(datosEntradaAnadirPersonaCorrecto, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaYaExiste(
				new PersonaAnadir(datosEntradaAnadirPersonaYaExiste, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaUsuarioYaExiste(
				new PersonaAnadir(datosEntradPersonaNoExiste, datosEntradaAnadirUsuarioYaExiste)));
		datosPruebaAcciones.add(getTestGuardarPersonaDniPersonaVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaDniVacio, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaNombrePersonaVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaNombreVacio, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaApellidosPersonaVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaApellidosVacio, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaDireccionPersonaVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaDireccionVacio, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaFechaNacPersonaVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaFechaNacVacio, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaTelefonoPersonaVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaTelefonoVacio, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaEmailPersonaVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaEmailVacio, datosEntradaUsuario)));
		datosPruebaAcciones.add(getTestGuardarPersonaParametrosVacios(
				new PersonaAnadir(datosEntradaAnadirParametrosPersonaVacios, datosEntradaUsuarioParametrosVacios)));
		datosPruebaAcciones.add(getTestGuardarPersonaUsuarioVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaCorrecto, datosEntradaUsuarioVacio)));
		datosPruebaAcciones.add(getTestGuardarPersonaPasswdUsuarioVacio(
				new PersonaAnadir(datosEntradaAnadirPersonaCorrecto, datosEntradaUsuarioContrasenaVacio)));

		return datosPruebaAcciones;

	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPersonaEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final PersonaEntity datosEntradaEliminarPersona = generarJSON.generatePersona(Constantes.URL_JSON_PERSONA_DATA,
				Constantes.ELIMINAR_PERSONA);
		final PersonaEntity datosEntradaEliminarPersonaNoExiste = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.PERSONA_NO_EXISTE);

		datosPruebaAcciones.add(getTestEliminarPersona(datosEntradaEliminarPersona));
		datosPruebaAcciones.add(getTestEliminarPersonaNoExiste(datosEntradaEliminarPersonaNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPersonaModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final PersonaEntity datosEntradaModificarPersonaCorrecto = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.GUARDAR_PERSONA);

		final PersonaEntity datosEntradaModificarPersonaNoExiste = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.PERSONA_NO_EXISTE);

		final PersonaEntity datosEntradaAnadirPersonaNombreVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.NOMBREPERSONA_VACIO_DATA);

		final PersonaEntity datosEntradaAnadirPersonaApellidosVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.APELLIDOSPERSONA_VACIOS_DATA);

		final PersonaEntity datosEntradaAnadirPersonaDireccionVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.DIRECCIONPERSONA_VACIA_DATA);

		final PersonaEntity datosEntradaAnadirPersonaFechaNacVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.FECHANACPERSONA_VACIA_DATA);

		final PersonaEntity datosEntradaAnadirPersonaTelefonoVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.TELEFONOPERSONA_VACIO_DATA);

		final PersonaEntity datosEntradaAnadirPersonaEmailVacio = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.EMAIL_VACIO_DATA);

		final PersonaEntity datosEntradaAnadirParametrosPersonaVacios = generarJSON
				.generatePersona(Constantes.URL_JSON_PERSONA_DATA, Constantes.PARAMETROS_PERSONA_VACIOS_DATA);

		datosPruebaAcciones.add(getTestModificarPersona(datosEntradaModificarPersonaCorrecto));

		datosPruebaAcciones.add(getTestModificarPersonaNoExiste(datosEntradaModificarPersonaNoExiste));

		datosPruebaAcciones.add(getTestModificarPersonaNombrePersonaVacio(datosEntradaAnadirPersonaNombreVacio));

		datosPruebaAcciones.add(getTestModificarPersonaApellidosPersonaVacio(datosEntradaAnadirPersonaApellidosVacio));

		datosPruebaAcciones.add(getTestModificarPersonaDireccionPersonaVacio(datosEntradaAnadirPersonaDireccionVacio));

		datosPruebaAcciones.add(getTestModificarPersonaFechaNacPersonaVacio(datosEntradaAnadirPersonaFechaNacVacio));

		datosPruebaAcciones.add(getTestModificarPersonaTelefonoPersonaVacio(datosEntradaAnadirPersonaTelefonoVacio));

		datosPruebaAcciones.add(getTestModificarPersonaEmailPersonaVacio(datosEntradaAnadirPersonaEmailVacio));

		datosPruebaAcciones.add(getTestModificarPersonaParametrosVacios(datosEntradaAnadirParametrosPersonaVacios));

		return datosPruebaAcciones;

	}

	private DatosPruebaAcciones getTestBuscarPersona(final PersonaEntity datosEntradaAccionBuscarPersona) {

		final String resultadoObtenido = buscarPersona(datosEntradaAccionBuscarPersona);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_PERSONA_CORRECTO + " - "
				+ Mensajes.BUSCAR_PERSONA_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO, getValorPersona(datosEntradaAccionBuscarPersona));

	}

	private DatosPruebaAcciones getTestGuardarPersonaCorrecto(final PersonaAnadir datosEntradaPersonaGuardarCorrecto)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarCorrecto);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_PERSONA_CORRECTO + " - "
				+ Mensajes.GUARDAR_PERSONA_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_PERSONA_CORRECTO, Constantes.EXITO,
				getValorPersonaAñadir(datosEntradaPersonaGuardarCorrecto));

	}

	private DatosPruebaAcciones getTestGuardarPersonaYaExiste(
			final PersonaAnadir datosEntradaPersonaGuardarPersonaYaExiste) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarPersonaYaExiste);

		final String resultadoEsperado = CodigosMensajes.PERSONA_YA_EXISTE + " - " + Mensajes.PERSONA_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PERSONA_YA_EXISTE, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarPersonaYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarPersonaUsuarioYaExiste(
			final PersonaAnadir datosEntradaPersonaGuardarPersonaUsuarioYaExiste) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarPersonaUsuarioYaExiste);

		final String resultadoEsperado = CodigosMensajes.USUARIO_YA_EXISTE + " - " + Mensajes.USUARIO_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.USUARIO_YA_EXISTE, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarPersonaUsuarioYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarPersonaDniPersonaVacio(
			final PersonaAnadir datosEntradaPersonaGuardarDniPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarDniPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_VACIO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarDniPersonaVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaNombrePersonaVacio(
			final PersonaAnadir datosEntradaPersonaGuardarNombrePersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarNombrePersonaVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarNombrePersonaVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaApellidosPersonaVacio(
			final PersonaAnadir datosEntradaPersonaGuardarApellidosPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarApellidosPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - "
				+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarApellidosPersonaVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaDireccionPersonaVacio(
			final PersonaAnadir datosEntradaPersonaGuardarDireccionPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarDireccionPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_VACIO + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarDireccionPersonaVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaFechaNacPersonaVacio(
			final PersonaAnadir datosEntradaPersonaGuardarFechaNacPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarFechaNacPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarFechaNacPersonaVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaEmailPersonaVacio(
			final PersonaAnadir datosEntradaPersonaGuardarEmailPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarEmailPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarEmailPersonaVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaTelefonoPersonaVacio(
			final PersonaAnadir datosEntradaPersonaGuardarTelefonoPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarTelefonoPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarTelefonoPersonaVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaUsuarioVacio(
			final PersonaAnadir datosEntradaPersonaGuardarUsuarioVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaGuardarUsuarioVacio);

		final String resultadoEsperado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaGuardarUsuarioVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaPasswdUsuarioVacio(
			final PersonaAnadir datosEntradaPersonaPasswdUsuarioVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaPasswdUsuarioVacio);

		final String resultadoEsperado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersonaAñadir(datosEntradaPersonaPasswdUsuarioVacio));

	}

	private DatosPruebaAcciones getTestGuardarPersonaParametrosVacios(
			final PersonaAnadir datosEntradaPersonaParametrosVacios) throws java.text.ParseException {

		final String resultadoObtenido = guardarPersona(datosEntradaPersonaParametrosVacios);

		final String resultadoEsperado = CodigosMensajes.PERSONA_AÑADIR_VACIA + " - " + Mensajes.PERSONA_AÑADIR_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorPersonaAñadir(datosEntradaPersonaParametrosVacios));

	}

	private DatosPruebaAcciones getTestEliminarPersona(final PersonaEntity datosEntradaPersonaEliminarPersona) {

		final String resultadoObtenido = eliminarPersona(datosEntradaPersonaEliminarPersona);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_PERSONA_CORRECTO + " - "
				+ Mensajes.ELIMINAR_PERSONA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_PERSONA_CORRECTO, Constantes.EXITO,
				getValorPersona(datosEntradaPersonaEliminarPersona));
	}

	private DatosPruebaAcciones getTestEliminarPersonaNoExiste(
			final PersonaEntity datosEntradaPersonaEliminarPersonaNoExiste) {

		final String resultadoObtenido = eliminarPersonaNoExiste(datosEntradaPersonaEliminarPersonaNoExiste);

		final String resultadoEsperado = CodigosMensajes.PERSONA_NO_EXISTE + " - " + Mensajes.PERSONA_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PERSONA_NO_EXISTE, Constantes.ERROR,
				getValorPersona(datosEntradaPersonaEliminarPersonaNoExiste));
	}

	private DatosPruebaAcciones getTestModificarPersona(final PersonaEntity datosEntradaPersonaModificarPersona)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarPersona(datosEntradaPersonaModificarPersona);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_PERSONA_CORRECTO + " - "
				+ Mensajes.MODIFICAR_PERSONA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_PERSONA_CORRECTO, Constantes.EXITO,
				getValorPersona(datosEntradaPersonaModificarPersona));
	}

	private DatosPruebaAcciones getTestModificarPersonaNoExiste(
			final PersonaEntity datosEntradaPersonaModificarNoExiste) throws java.text.ParseException {

		final String resultadoObtenido = modificarPersonaNoExiste(datosEntradaPersonaModificarNoExiste);

		final String resultadoEsperado = CodigosMensajes.PERSONA_NO_EXISTE + " - " + Mensajes.PERSONA_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PERSONA_NO_EXISTE, Constantes.ERROR,
				getValorPersona(datosEntradaPersonaModificarNoExiste));
	}

	private DatosPruebaAcciones getTestModificarPersonaNombrePersonaVacio(
			final PersonaEntity datosEntradaPersonaModificarNombrePersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPersona(datosEntradaPersonaModificarNombrePersonaVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersona(datosEntradaPersonaModificarNombrePersonaVacio));

	}

	private DatosPruebaAcciones getTestModificarPersonaApellidosPersonaVacio(
			final PersonaEntity datosEntradaPersonaModificarApellidosPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPersona(datosEntradaPersonaModificarApellidosPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - "
				+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersona(datosEntradaPersonaModificarApellidosPersonaVacio));

	}

	private DatosPruebaAcciones getTestModificarPersonaDireccionPersonaVacio(
			final PersonaEntity datosEntradaPersonaModificarDireccionPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPersona(datosEntradaPersonaModificarDireccionPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_VACIO + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersona(datosEntradaPersonaModificarDireccionPersonaVacio));

	}

	private DatosPruebaAcciones getTestModificarPersonaFechaNacPersonaVacio(
			final PersonaEntity datosEntradaPersonaModificarFechaNacPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPersona(datosEntradaPersonaModificarFechaNacPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersona(datosEntradaPersonaModificarFechaNacPersonaVacio));

	}

	private DatosPruebaAcciones getTestModificarPersonaEmailPersonaVacio(
			final PersonaEntity datosEntradaPersonaModificarEmailPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPersona(datosEntradaPersonaModificarEmailPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersona(datosEntradaPersonaModificarEmailPersonaVacio));

	}

	private DatosPruebaAcciones getTestModificarPersonaTelefonoPersonaVacio(
			final PersonaEntity datosEntradaPersonaModificarTelefonoPersonaVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPersona(datosEntradaPersonaModificarTelefonoPersonaVacio);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPersona(datosEntradaPersonaModificarTelefonoPersonaVacio));

	}

	private DatosPruebaAcciones getTestModificarPersonaParametrosVacios(
			final PersonaEntity datosEntradaPersonaParametrosVacios) throws java.text.ParseException {

		final String resultadoObtenido = modificarPersona(datosEntradaPersonaParametrosVacios);

		final String resultadoEsperado = CodigosMensajes.PERSONA_AÑADIR_VACIA + " - " + Mensajes.PERSONA_AÑADIR_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorPersona(datosEntradaPersonaParametrosVacios));

	}

	private String buscarPersona(final PersonaEntity persona) {
		String resultado = StringUtils.EMPTY;

		List<PersonaEntity> personaBD = new ArrayList<>();
		java.sql.Date fechaSql;
		fechaSql = new java.sql.Date(persona.getFechaNacP().getTime());
		final String fecha = fechaSql.toString();

		personaBD = personaRepository.findPersona(persona.getDniP(), persona.getNombreP(), persona.getApellidosP(),
				fecha, persona.getDireccionP(), persona.getTelefonoP(), persona.getEmailP(), persona.getEmpresa());

		resultado = CodigosMensajes.BUSCAR_PERSONA_CORRECTO + " - " + Mensajes.BUSCAR_PERSONA_CORRECTO;

		return resultado;
	}

	private String guardarPersona(final PersonaAnadir persona) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarPersonaBlank(persona.getPersonaEntity())
				&& !validaciones.comprobarUsuarioBlank(persona.getUsuarioEntity())) {
			resultado = CodigosMensajes.PERSONA_AÑADIR_VACIA + " - " + Mensajes.PERSONA_AÑADIR_VACIA;
		} else if (!validaciones.comprobarDniPersonaBlank(persona.getPersonaEntity().getDniP())) {
			resultado = CodigosMensajes.DNI_PERSONA_VACIO + " - " + Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombrePersonaBlank(persona.getPersonaEntity().getNombreP())) {
			resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarApellidosPersonaBlank(persona.getPersonaEntity().getApellidosP())) {
			resultado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - " + Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDireccionPersonaBlank(persona.getPersonaEntity().getDireccionP())) {
			resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaNacPersonaBlank(persona.getPersonaEntity().getFechaNacP().toString())) {
			resultado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - " + Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarEmailUsuarioBlank(persona.getPersonaEntity().getEmailP())) {
			resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarTelefonoPersonaBlank(persona.getPersonaEntity().getTelefonoP())) {
			resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarUsuarioBlank(persona.getUsuarioEntity().getUsuario())) {
			resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarPasswdUsuarioBlank(persona.getUsuarioEntity().getPasswdUsuario())) {
			resultado = CodigosMensajes.PASS_USUARIO_VACIO + " - " + Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;
		} else {
			final Optional<PersonaEntity> personaBD = personaRepository.findById(persona.getPersonaEntity().getDniP());

			if (personaBD.isPresent()) {
				resultado = CodigosMensajes.PERSONA_YA_EXISTE + " - " + Mensajes.PERSONA_YA_EXISTE;
			} else {
				final Optional<UsuarioEntity> usuarioBD = usuarioRepository
						.findById(persona.getUsuarioEntity().getDniUsuario());

				if (usuarioBD.isPresent()) {
					resultado = CodigosMensajes.USUARIO_YA_EXISTE + " - " + Mensajes.USUARIO_YA_EXISTE;
				} else {
					personaRepository.saveAndFlush(persona.getPersonaEntity());
					usuarioRepository.saveAndFlush(persona.getUsuarioEntity());

					resultado = CodigosMensajes.GUARDAR_PERSONA_CORRECTO + " - " + Mensajes.GUARDAR_PERSONA_CORRECTO;

					final Optional<PersonaEntity> personaBuscar = personaRepository
							.findById(persona.getPersonaEntity().getDniP());
					final Optional<UsuarioEntity> usuarioBuscar = usuarioRepository
							.findById(persona.getUsuarioEntity().getDniUsuario());

					usuarioRepository.deleteUsuario(usuarioBuscar.get().getDniUsuario());
					usuarioRepository.flush();
					personaRepository.deletePersona(personaBuscar.get().getDniP());
					personaRepository.flush();

				}
			}
		}

		return resultado;
	}

	private String eliminarPersona(final PersonaEntity persona) {
		Optional<PersonaEntity> personaUsuario = personaRepository.findById(persona.getDniP());
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(persona.getDniP());
		String resultado = StringUtils.EMPTY;

		if (!personaUsuario.isPresent()) {
			personaRepository.saveAndFlush(persona);

			if (!usuario.isPresent()) {

				final RolEntity rol = new RolEntity(2, "usuario",
						"Contendrá a todos los usuarios registrados de la aplicación", 0);

				final UsuarioEntity user = new UsuarioEntity(persona.getDniP(), "usuario", "passUsuario", 0, rol);

				usuarioRepository.saveAndFlush(user);

				personaUsuario = personaRepository.findById(persona.getDniP());
				usuario = usuarioRepository.findById(persona.getDniP());

				personaUsuario.get().setBorradoP(1);
				usuario.get().setBorradoUsuario(1);

				personaRepository.saveAndFlush(personaUsuario.get());
				usuarioRepository.saveAndFlush(usuario.get());

				resultado = CodigosMensajes.ELIMINAR_PERSONA_CORRECTO + " - " + Mensajes.ELIMINAR_PERSONA_CORRECTAMENTE;

				usuarioRepository.deleteUsuario(persona.getDniP());
				usuarioRepository.flush();
				personaRepository.deletePersona(persona.getDniP());
				personaRepository.flush();
			}
		}

		return resultado;
	}

	private String eliminarPersonaNoExiste(final PersonaEntity persona) {
		final Optional<PersonaEntity> personaBD = personaRepository.findById(persona.getDniP());
		String resultado = StringUtils.EMPTY;

		if (!personaBD.isPresent()) {
			resultado = CodigosMensajes.PERSONA_NO_EXISTE + " - " + Mensajes.PERSONA_NO_EXISTE;
		}

		return resultado;
	}

	private String modificarPersona(final PersonaEntity persona) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombrePersonaBlank(persona.getNombreP())
				&& !validaciones.comprobarApellidosPersonaBlank(persona.getApellidosP())
				&& !validaciones.comprobarDireccionPersonaBlank(persona.getDireccionP())
				&& !validaciones.comprobarFechaNacPersonaBlank(persona.getFechaNacP().toString())
				&& !validaciones.comprobarEmailUsuarioBlank(persona.getEmailP())
				&& !validaciones.comprobarTelefonoPersonaBlank(persona.getTelefonoP())) {
			resultado = CodigosMensajes.PERSONA_AÑADIR_VACIA + " - " + Mensajes.PERSONA_AÑADIR_VACIA;
		} else if (!validaciones.comprobarNombrePersonaBlank(persona.getNombreP())) {
			resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarApellidosPersonaBlank(persona.getApellidosP())) {
			resultado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - " + Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDireccionPersonaBlank(persona.getDireccionP())) {
			resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaNacPersonaBlank(persona.getFechaNacP().toString())) {
			resultado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - " + Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarEmailUsuarioBlank(persona.getEmailP())) {
			resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarTelefonoPersonaBlank(persona.getTelefonoP())) {
			resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
		} else {
			final Optional<PersonaEntity> personaBD = personaRepository.findById(persona.getDniP());

			if (!personaBD.isPresent()) {
				personaRepository.saveAndFlush(persona);

				final RolEntity rolEntity = new RolEntity(2, "usuario",
						"Contendrá a todos los usuarios de la aplicacion", 0);

				final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "usuario", "passwdUsuario", 0,
						rolEntity);

				usuarioRepository.saveAndFlush(usuario);

				persona.setApellidosP("Lopez");
				persona.setDireccionP("Direccion Modificada");

				personaRepository.saveAndFlush(persona);

				resultado = CodigosMensajes.MODIFICAR_PERSONA_CORRECTO + " - "
						+ Mensajes.MODIFICAR_PERSONA_CORRECTAMENTE;

				usuarioRepository.deleteUsuario(persona.getDniP());
				usuarioRepository.flush();
				personaRepository.deletePersona(persona.getDniP());
				personaRepository.flush();
			}
		}

		return resultado;
	}

	private String modificarPersonaNoExiste(final PersonaEntity persona) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombrePersonaBlank(persona.getNombreP())
				&& !validaciones.comprobarApellidosPersonaBlank(persona.getApellidosP())
				&& !validaciones.comprobarDireccionPersonaBlank(persona.getDireccionP())
				&& !validaciones.comprobarFechaNacPersonaBlank(persona.getFechaNacP().toString())
				&& !validaciones.comprobarEmailUsuarioBlank(persona.getEmailP())
				&& !validaciones.comprobarTelefonoPersonaBlank(persona.getTelefonoP())) {
			resultado = CodigosMensajes.PERSONA_AÑADIR_VACIA + " - " + Mensajes.PERSONA_AÑADIR_VACIA;
		} else if (!validaciones.comprobarNombrePersonaBlank(persona.getNombreP())) {
			resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarApellidosPersonaBlank(persona.getApellidosP())) {
			resultado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - " + Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDireccionPersonaBlank(persona.getDireccionP())) {
			resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaNacPersonaBlank(persona.getFechaNacP().toString())) {
			resultado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - " + Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarEmailUsuarioBlank(persona.getEmailP())) {
			resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarTelefonoPersonaBlank(persona.getTelefonoP())) {
			resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
		} else {
			final Optional<PersonaEntity> personaBD = personaRepository.findById(persona.getDniP());

			if (!personaBD.isPresent()) {
				resultado = CodigosMensajes.PERSONA_NO_EXISTE + " - " + Mensajes.PERSONA_NO_EXISTE;
			}
		}

		return resultado;
	}

	private Map<String, String> getValorPersona(final PersonaEntity persona) {

		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.DNIP, persona.getDniP());
		valor.put(Constantes.NOMBREP, persona.getNombreP());
		valor.put(Constantes.APELLIDOSP, persona.getApellidosP());
		valor.put(Constantes.FECHANACP, persona.getFechaNacP().toString());
		valor.put(Constantes.DIRECCIONP, persona.getDireccionP());
		valor.put(Constantes.EMAILP, persona.getEmailP());
		valor.put(Constantes.TELEFONOP, persona.getTelefonoP());

		return valor;
	}

	private Map<String, String> getValorPersonaAñadir(final PersonaAnadir persona) {

		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.DNIP, persona.getPersonaEntity().getDniP());
		valor.put(Constantes.NOMBREP, persona.getPersonaEntity().getNombreP());
		valor.put(Constantes.APELLIDOSP, persona.getPersonaEntity().getApellidosP());
		valor.put(Constantes.FECHANACP, persona.getPersonaEntity().getFechaNacP().toString());
		valor.put(Constantes.DIRECCIONP, persona.getPersonaEntity().getDireccionP());
		valor.put(Constantes.EMAILP, persona.getPersonaEntity().getEmailP());
		valor.put(Constantes.TELEFONOP, persona.getPersonaEntity().getTelefonoP());
		valor.put(Constantes.DNI_USUARIO, persona.getUsuarioEntity().getDniUsuario());
		valor.put(Constantes.USUARIO, persona.getUsuarioEntity().getUsuario());
		valor.put(Constantes.PASSWD_USUARIO, persona.getUsuarioEntity().getPasswdUsuario());

		return valor;
	}

}
