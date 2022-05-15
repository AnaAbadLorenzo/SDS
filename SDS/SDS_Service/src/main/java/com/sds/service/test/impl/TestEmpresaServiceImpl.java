package com.sds.service.test.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.repository.EmpresaRepository;
import com.sds.repository.PersonaRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestEmpresaService;
import com.sds.service.test.impl.atributos.TestAtributoCifEmpresa;
import com.sds.service.test.impl.atributos.TestAtributoDireccionEmpresa;
import com.sds.service.test.impl.atributos.TestAtributoNombreEmpresa;
import com.sds.service.test.impl.atributos.TestAtributoTelefonoEmpresa;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestEmpresaServiceImpl implements TestEmpresaService {

	private final TestAtributoCifEmpresa testAtributoCifEmpresa;
	private final TestAtributoNombreEmpresa testAtributoNombreEmpresa;
	private final TestAtributoDireccionEmpresa testAtributoDireccionEmpresa;
	private final TestAtributoTelefonoEmpresa testAtributoTelefonoEmpresa;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	PersonaRepository personaRepository;

	public TestEmpresaServiceImpl() {
		testAtributoCifEmpresa = new TestAtributoCifEmpresa();
		testAtributoNombreEmpresa = new TestAtributoNombreEmpresa();
		testAtributoDireccionEmpresa = new TestAtributoDireccionEmpresa();
		testAtributoTelefonoEmpresa = new TestAtributoTelefonoEmpresa();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		this.validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoCifEmpresa()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final EmpresaEntity datosEntradaCifEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoEnhe = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFAUMERICO_ENHE_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoAcentos = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_ACENTOS_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoCaracteresEspeciales = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA,
				Constantes.CIFEMPRESA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoEspacios = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_ESPACIOS_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoMenor9 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_MENOR_9_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoMayor9 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_MAYOR_9_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumerico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoCifEmpresa.getTestCifEmpresaVacio(datosEntradaCifEmpresaVacio));
		datosPruebaAtributos
				.add(testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoEnhe(datosEntradaCifEmpresaAlfanumericoEnhe));
		datosPruebaAtributos.add(
				testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoAcentos(datosEntradaCifEmpresaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoCaracteresEspeciales(
				datosEntradaCifEmpresaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoCifEmpresa
				.getTestCifEmpresaAlfanumericoEspacios(datosEntradaCifEmpresaAlfanumericoEspacios));
		datosPruebaAtributos.add(
				testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoMenor9(datosEntradaCifEmpresaAlfanumericoMenor9));
		datosPruebaAtributos.add(
				testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoMayor9(datosEntradaCifEmpresaAlfanumericoMayor9));
		datosPruebaAtributos
				.add(testAtributoCifEmpresa.getTestCifEmpresaCorrectoAlfanumerico(datosEntradaCifEmpresaAlfanumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoCifEmpresaBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoEnhe = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFAUMERICO_ENHE_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoAcentos = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_ACENTOS_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoCaracteresEspeciales = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA,
				Constantes.CIFEMPRESA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoEspacios = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_ESPACIOS_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumericoMayor9 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_MAYOR_9_DATA);
		final EmpresaEntity datosEntradaCifEmpresaAlfanumerico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA, Constantes.CIFEMPRESA_ALFANUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoEnhe(datosEntradaCifEmpresaAlfanumericoEnhe));
		datosPruebaAtributos.add(
				testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoAcentos(datosEntradaCifEmpresaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoCaracteresEspeciales(
				datosEntradaCifEmpresaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoCifEmpresa
				.getTestCifEmpresaAlfanumericoEspacios(datosEntradaCifEmpresaAlfanumericoEspacios));
		datosPruebaAtributos.add(
				testAtributoCifEmpresa.getTestCifEmpresaAlfanumericoMayor9(datosEntradaCifEmpresaAlfanumericoMayor9));
		datosPruebaAtributos
				.add(testAtributoCifEmpresa.getTestCifEmpresaCorrectoAlfanumerico(datosEntradaCifEmpresaAlfanumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreEmpresa()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final EmpresaEntity datosEntradaNombreEmpresaVacio = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA, Constantes.NOMBREEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaNombreEmpresaAlfanumericoCaracteresEspeciales = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA,
				Constantes.NOMBREEMPRESA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final EmpresaEntity datosEntradaNombreEmpresaAlfanumericoMenor3 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA,
				Constantes.NOMBREEMPRESA_ALFANUMERICO_MENOR_3_DATA);
		final EmpresaEntity datosEntradaNombreEmpresaAlfanumericoMayor56 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA,
				Constantes.NOMBREEMPRESA_ALFANUMERICO_MAYOR_56_DATA);
		final EmpresaEntity datosEntradaNombreEmpresaAlfabetico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA, Constantes.NOMBREEMPRESA_ALFABETICO_DATA);
		final EmpresaEntity datosEntradaNombreEmpresaNumerico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA, Constantes.NOMBREEMPRESA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoNombreEmpresa.getTestNombreEmpresaVacio(datosEntradaNombreEmpresaVacio));
		datosPruebaAtributos.add(testAtributoNombreEmpresa.getTestNombreEmpresaAlfanumericoCaracteresEspeciales(
				datosEntradaNombreEmpresaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombreEmpresa
				.getTestNombreEmpresaAlfanumericoMenor3(datosEntradaNombreEmpresaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoNombreEmpresa
				.getTestNombreEmpresaAlfanumericoMayor56(datosEntradaNombreEmpresaAlfanumericoMayor56));
		datosPruebaAtributos.add(
				testAtributoNombreEmpresa.getTestNombreEmpresaCorrectoAlfabetico(datosEntradaNombreEmpresaAlfabetico));
		datosPruebaAtributos
				.add(testAtributoNombreEmpresa.getTestNombreEmpresaNumerico(datosEntradaNombreEmpresaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreEmpresaBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final EmpresaEntity datosEntradaNombreEmpresaAlfanumericoCaracteresEspeciales = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA,
				Constantes.NOMBREEMPRESA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final EmpresaEntity datosEntradaNombreEmpresaAlfanumericoMayor56 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA,
				Constantes.NOMBREEMPRESA_ALFANUMERICO_MAYOR_56_DATA);
		final EmpresaEntity datosEntradaNombreEmpresaAlfabetico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA, Constantes.NOMBREEMPRESA_ALFABETICO_DATA);
		final EmpresaEntity datosEntradaNombreEmpresaNumerico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA, Constantes.NOMBREEMPRESA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoNombreEmpresa.getTestNombreEmpresaAlfanumericoCaracteresEspeciales(
				datosEntradaNombreEmpresaAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombreEmpresa
				.getTestNombreEmpresaAlfanumericoMayor56(datosEntradaNombreEmpresaAlfanumericoMayor56));
		datosPruebaAtributos.add(
				testAtributoNombreEmpresa.getTestNombreEmpresaCorrectoAlfabetico(datosEntradaNombreEmpresaAlfabetico));
		datosPruebaAtributos
				.add(testAtributoNombreEmpresa.getTestNombreEmpresaNumerico(datosEntradaNombreEmpresaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDireccionEmpresa()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final EmpresaEntity datosEntradaDireccionEmpresaVacio = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA, Constantes.DIRECCIONEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaDireccionEmpresaAlfanumericoMenor3 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA,
				Constantes.DIRECCIONEMPRESA_ALFANUMERICO_MENOR_3_DATA);
		final EmpresaEntity datosEntradaDireccionEmpresaAlfanumericoMayor128 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA,
				Constantes.DIRECCIONEMPRESA_ALFANUMERICO_MAYOR_128_DATA);
		final EmpresaEntity datosEntradaDireccionEmpresaAlfabetico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA, Constantes.DIRECCIONEMPRESA_ALFABETICO_DATA);
		final EmpresaEntity datosEntradaDireccionEmpresaAlfanumerico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA, Constantes.DIRECCIONEMPRESA_ALFANUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoDireccionEmpresa.getTestDireccionEmpresaVacio(datosEntradaDireccionEmpresaVacio));
		datosPruebaAtributos.add(testAtributoDireccionEmpresa
				.getTestDireccionEmpresaAlfanumericoMenor3(datosEntradaDireccionEmpresaAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoDireccionEmpresa
				.getTestDireccionEmpresaAlfanumericoMayor128(datosEntradaDireccionEmpresaAlfanumericoMayor128));
		datosPruebaAtributos.add(testAtributoDireccionEmpresa
				.getTestDireccionEmpresaCorrectoAlfanumerico(datosEntradaDireccionEmpresaAlfabetico));
		datosPruebaAtributos.add(testAtributoDireccionEmpresa
				.getTestDireccionEmpresaCorrectoAlfanumerico(datosEntradaDireccionEmpresaAlfanumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDireccionEmpresaBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final EmpresaEntity datosEntradaDireccionEmpresaAlfanumericoMayor128 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA,
				Constantes.DIRECCIONEMPRESA_ALFANUMERICO_MAYOR_128_DATA);
		final EmpresaEntity datosEntradaDireccionEmpresaAlfabetico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA, Constantes.DIRECCIONEMPRESA_ALFABETICO_DATA);
		final EmpresaEntity datosEntradaDireccionEmpresaAlfanumerico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA, Constantes.DIRECCIONEMPRESA_ALFANUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoDireccionEmpresa
				.getTestDireccionEmpresaAlfanumericoMayor128(datosEntradaDireccionEmpresaAlfanumericoMayor128));
		datosPruebaAtributos.add(testAtributoDireccionEmpresa
				.getTestDireccionEmpresaCorrectoAlfanumerico(datosEntradaDireccionEmpresaAlfabetico));
		datosPruebaAtributos.add(testAtributoDireccionEmpresa
				.getTestDireccionEmpresaCorrectoAlfanumerico(datosEntradaDireccionEmpresaAlfanumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTelefonoEmpresa()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final EmpresaEntity datosEntradaTelefonoEmpresaVacio = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoEnhe = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_NUMERICO_ENHE_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoAcentos = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_ACENTOS_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoCaracteresEspeciales = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoEspacios = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_ESPACIOS_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoMenor9 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_MENOR_9_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoMayor9 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_MAYOR_9_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumerico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoTelefonoEmpresa.getTestTelefonoEmpresaVacio(datosEntradaTelefonoEmpresaVacio));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaNumericoEnhe(datosEntradaTelefonoEmpresaNumericoEnhe));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaAlfanumericoEspacios(datosEntradaTelefonoEmpresaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaNumericoAcentos(datosEntradaTelefonoEmpresaNumericoAcentos));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa.getTestTelefonoEmpresaNumericoCaracteresEspeciales(
				datosEntradaTelefonoEmpresaNumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaNumericoMenor9(datosEntradaTelefonoEmpresaNumericoMenor9));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaNumericoMayor9(datosEntradaTelefonoEmpresaNumericoMayor9));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaCorrectoNumerico(datosEntradaTelefonoEmpresaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTelefonoEmpresaBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoEnhe = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_NUMERICO_ENHE_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoAcentos = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_ACENTOS_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoCaracteresEspeciales = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_CARACTERES_ESPECIALES_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoEspacios = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_ESPACIOS_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumericoMayor9 = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA,
				Constantes.TELEFONOEMPRESA_NUMERICO_MAYOR_9_DATA);
		final EmpresaEntity datosEntradaTelefonoEmpresaNumerico = generarJSON.generateEmpresa(
				Constantes.URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA, Constantes.TELEFONOEMPRESA_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaNumericoEnhe(datosEntradaTelefonoEmpresaNumericoEnhe));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaAlfanumericoEspacios(datosEntradaTelefonoEmpresaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaNumericoAcentos(datosEntradaTelefonoEmpresaNumericoAcentos));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa.getTestTelefonoEmpresaNumericoCaracteresEspeciales(
				datosEntradaTelefonoEmpresaNumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaNumericoMayor9(datosEntradaTelefonoEmpresaNumericoMayor9));
		datosPruebaAtributos.add(testAtributoTelefonoEmpresa
				.getTestTelefonoEmpresaCorrectoNumerico(datosEntradaTelefonoEmpresaNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesEmpresaBuscar()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		final EmpresaEntity datosEntradaEmpresaBuscarEmpresa = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.BUSCAR_EMPRESA);
		final EmpresaEntity datosEntradaEmpresaBuscarCifEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.CIFEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaBuscarNombreEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.NOMBREEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaBuscarDireccionEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.DIRECCIONEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaBuscarTelefonoEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.TELEFONOEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaBuscarEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.PARAMETROS_EMPRESA_VACIOS);

		datosPruebaAcciones.add(getTestBuscarEmpresa(datosEntradaEmpresaBuscarEmpresa));
		datosPruebaAcciones.add(getTestBuscarEmpresa(datosEntradaEmpresaBuscarCifEmpresaVacio));
		datosPruebaAcciones.add(getTestBuscarEmpresa(datosEntradaEmpresaBuscarNombreEmpresaVacio));
		datosPruebaAcciones.add(getTestBuscarEmpresa(datosEntradaEmpresaBuscarDireccionEmpresaVacio));
		datosPruebaAcciones.add(getTestBuscarEmpresa(datosEntradaEmpresaBuscarTelefonoEmpresaVacio));
		datosPruebaAcciones.add(getTestBuscarEmpresa(datosEntradaEmpresaBuscarEmpresaVacio));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesEmpresaGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		final EmpresaEntity datosEntradaEmpresaGuardarEmpresa = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.GUARDAR_EMPRESA_CORRECTO);
		final EmpresaEntity datosEntradaEmpresaGuardarEmpresaYaExiste = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.EMPRESA_YA_EXISTE);
		final EmpresaEntity datosEntradaEmpresaGuardarCifEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.CIFEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaGuardarNombreEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.NOMBREEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaGuardarDireccionEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.DIRECCIONEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaGuardarTelefonoEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.TELEFONOEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaGuardarEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.PARAMETROS_EMPRESA_VACIOS);

		datosPruebaAcciones.add(getTestGuardarEmpresa(datosEntradaEmpresaGuardarEmpresa));
		datosPruebaAcciones.add(getTestGuardarEmpresaYaExiste(datosEntradaEmpresaGuardarEmpresaYaExiste));
		datosPruebaAcciones.add(getTestGuardarEmpresaCifVacio(datosEntradaEmpresaGuardarCifEmpresaVacio));
		datosPruebaAcciones.add(getTestGuardarEmpresaNombreVacio(datosEntradaEmpresaGuardarNombreEmpresaVacio));
		datosPruebaAcciones.add(getTestGuardarEmpresaDireccionVacio(datosEntradaEmpresaGuardarDireccionEmpresaVacio));
		datosPruebaAcciones.add(getTestGuardarEmpresaTelefonoVacio(datosEntradaEmpresaGuardarTelefonoEmpresaVacio));
		datosPruebaAcciones.add(getTestGuardarEmpresaParametrosVacios(datosEntradaEmpresaGuardarEmpresaVacio));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesEmpresaEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		final EmpresaEntity datosEntradaEmpresaEliminarEmpresa = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.ELIMINAR_EMPRESA_CORRECTO);
		final EmpresaEntity datosEntradaEmpresaEliminarEmpresaNoExiste = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.EMPRESA_NO_EXISTE);
		final EmpresaEntity datosEntradaEmpresaEliminarEmpresaAsociadaPersonas = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.ELIMINAR_EMPRESA_ASOCIADA_PERSONAS);

		datosPruebaAcciones.add(getTestEliminarEmpresaCorrecto(datosEntradaEmpresaEliminarEmpresa));
		datosPruebaAcciones.add(getTestEliminarEmpresaNoExiste(datosEntradaEmpresaEliminarEmpresaNoExiste));
		datosPruebaAcciones
				.add(getTestEliminarEmpresaAsociadaPersonas(datosEntradaEmpresaEliminarEmpresaAsociadaPersonas));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesEmpresaModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		final EmpresaEntity datosEntradaEmpresaModificarEmpresa = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.MODIFICAR_EMPRESA_CORRECTO);
		final EmpresaEntity datosEntradaEmpresaModificarEmpresaNoExiste = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.EMPRESA_NO_EXISTE);
		final EmpresaEntity datosEntradaEmpresaModificarCifEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.CIFEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaModificarNombreEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.NOMBREEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaModificarDireccionEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.DIRECCIONEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaModificarTelefonoEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.TELEFONOEMPRESA_VACIO_DATA);
		final EmpresaEntity datosEntradaEmpresaModificarEmpresaVacio = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.PARAMETROS_EMPRESA_VACIOS);

		datosPruebaAcciones.add(getTestModificarEmpresa(datosEntradaEmpresaModificarEmpresa));
		datosPruebaAcciones.add(getTestModificarEmpresaNoExiste(datosEntradaEmpresaModificarEmpresaNoExiste));
		datosPruebaAcciones.add(getTestModificarEmpresaCifVacio(datosEntradaEmpresaModificarCifEmpresaVacio));
		datosPruebaAcciones.add(getTestModificarEmpresaNombreVacio(datosEntradaEmpresaModificarNombreEmpresaVacio));
		datosPruebaAcciones
				.add(getTestModificarEmpresaDireccionVacio(datosEntradaEmpresaModificarDireccionEmpresaVacio));
		datosPruebaAcciones.add(getTestModificarEmpresaTelefonoVacio(datosEntradaEmpresaModificarTelefonoEmpresaVacio));
		datosPruebaAcciones.add(getTestModificarEmpresaParametrosVacios(datosEntradaEmpresaModificarEmpresaVacio));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesEmpresaReactivar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		final EmpresaEntity datosEntradaEmpresaReactivarEmpresa = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.REACTIVAR_EMPRESA_CORRECTO);
		final EmpresaEntity datosEntradaEmpresaReactivarEmpresaNoExiste = generarJSON
				.generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.EMPRESA_NO_EXISTE);

		datosPruebaAcciones.add(getTestReactivarEmpresaCorrecto(datosEntradaEmpresaReactivarEmpresa));
		datosPruebaAcciones.add(getTestReactivarEmpresaNoExiste(datosEntradaEmpresaReactivarEmpresaNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarEmpresa(final EmpresaEntity datosEntradaEmpresaBuscarEmpresa) {

		final String resultadoObtenido = buscarEmpresa(datosEntradaEmpresaBuscarEmpresa);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_EMPRESA_CORRECTO + " - "
				+ Mensajes.BUSCAR_EMPRESA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO, getValorEmpresa(datosEntradaEmpresaBuscarEmpresa));

	}

	private DatosPruebaAcciones getTestGuardarEmpresa(final EmpresaEntity datosEntradaGuardarEmpresa) {

		final String resultadoObtenido = guardarEmpresa(datosEntradaGuardarEmpresa);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_EMPRESA_CORRECTO + " - "
				+ Mensajes.EMPRESA_GUARDADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_EMPRESA_CORRECTO, Constantes.EXITO,
				getValorEmpresa(datosEntradaGuardarEmpresa));

	}

	private DatosPruebaAcciones getTestGuardarEmpresaYaExiste(final EmpresaEntity datosEntradaGuardarEmpresaYaExiste) {

		final String resultadoObtenido = guardarEmpresa(datosEntradaGuardarEmpresaYaExiste);

		final String resultadoEsperado = CodigosMensajes.EMPRESA_YA_EXISTE + " - " + Mensajes.EMPRESA_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.EMPRESA_YA_EXISTE, Constantes.ERROR,
				getValorEmpresa(datosEntradaGuardarEmpresaYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarEmpresaCifVacio(final EmpresaEntity datosEntradaGuardarEmpresaCifVacio) {

		final String resultadoObtenido = guardarEmpresa(datosEntradaGuardarEmpresaCifVacio);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_VACIO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaGuardarEmpresaCifVacio));

	}

	private DatosPruebaAcciones getTestGuardarEmpresaNombreVacio(
			final EmpresaEntity datosEntradaGuardarEmpresaNombreVacio) {

		final String resultadoObtenido = guardarEmpresa(datosEntradaGuardarEmpresaNombreVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaGuardarEmpresaNombreVacio));

	}

	private DatosPruebaAcciones getTestGuardarEmpresaDireccionVacio(
			final EmpresaEntity datosEntradaGuardarEmpresaDireccionVacio) {

		final String resultadoObtenido = guardarEmpresa(datosEntradaGuardarEmpresaDireccionVacio);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_VACIO + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaGuardarEmpresaDireccionVacio));

	}

	private DatosPruebaAcciones getTestGuardarEmpresaTelefonoVacio(
			final EmpresaEntity datosEntradaGuardarEmpresaTelefonoVacio) {

		final String resultadoObtenido = guardarEmpresa(datosEntradaGuardarEmpresaTelefonoVacio);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaGuardarEmpresaTelefonoVacio));

	}

	private DatosPruebaAcciones getTestGuardarEmpresaParametrosVacios(
			final EmpresaEntity datosEntradaGuardarEmpresaParametrosVacios) {

		final String resultadoObtenido = guardarEmpresa(datosEntradaGuardarEmpresaParametrosVacios);

		final String resultadoEsperado = CodigosMensajes.EMPRESA_VACIA + " - " + Mensajes.EMPRESA_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaGuardarEmpresaParametrosVacios));

	}

	private DatosPruebaAcciones getTestModificarEmpresa(final EmpresaEntity datosEntradaModificarEmpresa) {

		final String resultadoObtenido = modificarEmpresa(datosEntradaModificarEmpresa);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_EMPRESA_CORRECTO + " - "
				+ Mensajes.EMPRESA_MODIFICADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_EMPRESA_CORRECTO, Constantes.EXITO,
				getValorEmpresa(datosEntradaModificarEmpresa));

	}

	private DatosPruebaAcciones getTestModificarEmpresaNoExiste(
			final EmpresaEntity datosEntradaModificarEmpresaNoExiste) {

		final String resultadoObtenido = modificarEmpresaNoExiste(datosEntradaModificarEmpresaNoExiste);

		final String resultadoEsperado = CodigosMensajes.EMPRESA_NO_EXISTE + " - " + Mensajes.EMPRESA_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.EMPRESA_NO_EXISTE, Constantes.ERROR,
				getValorEmpresa(datosEntradaModificarEmpresaNoExiste));

	}

	private DatosPruebaAcciones getTestModificarEmpresaCifVacio(
			final EmpresaEntity datosEntradaGuardarEmpresaCifVacio) {

		final String resultadoObtenido = modificarEmpresa(datosEntradaGuardarEmpresaCifVacio);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_VACIO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaGuardarEmpresaCifVacio));

	}

	private DatosPruebaAcciones getTestModificarEmpresaNombreVacio(
			final EmpresaEntity datosEntradaModificarEmpresaNombreVacio) {

		final String resultadoObtenido = modificarEmpresa(datosEntradaModificarEmpresaNombreVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaModificarEmpresaNombreVacio));

	}

	private DatosPruebaAcciones getTestModificarEmpresaDireccionVacio(
			final EmpresaEntity datosEntradaModificarEmpresaDireccionVacio) {

		final String resultadoObtenido = modificarEmpresa(datosEntradaModificarEmpresaDireccionVacio);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_VACIO + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaModificarEmpresaDireccionVacio));

	}

	private DatosPruebaAcciones getTestModificarEmpresaTelefonoVacio(
			final EmpresaEntity datosEntradaModificarEmpresaTelefonoVacio) {

		final String resultadoObtenido = modificarEmpresa(datosEntradaModificarEmpresaTelefonoVacio);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorEmpresa(datosEntradaModificarEmpresaTelefonoVacio));

	}

	private DatosPruebaAcciones getTestModificarEmpresaParametrosVacios(
			final EmpresaEntity datosEntradaModificarEmpresaParametrosVacios) {

		final String resultadoObtenido = modificarEmpresa(datosEntradaModificarEmpresaParametrosVacios);

		final String resultadoEsperado = CodigosMensajes.EMPRESA_VACIA + " - " + Mensajes.EMPRESA_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorEmpresa(datosEntradaModificarEmpresaParametrosVacios));

	}

	private DatosPruebaAcciones getTestEliminarEmpresaCorrecto(
			final EmpresaEntity datosEntradaEliminarEmpresaCorrecto) {
		final String resultadoObtenido = eliminarEmpresa(datosEntradaEliminarEmpresaCorrecto);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_EMPRESA_CORRECTO + " - "
				+ Mensajes.EMPRESA_ELIMINADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_EMPRESA_CORRECTO, Constantes.EXITO,
				getValorEmpresa(datosEntradaEliminarEmpresaCorrecto));
	}

	private DatosPruebaAcciones getTestEliminarEmpresaNoExiste(
			final EmpresaEntity datosEntradaEliminarEmpresaNoExiste) {
		final String resultadoObtenido = eliminarEmpresaNoExiste(datosEntradaEliminarEmpresaNoExiste);

		final String resultadoEsperado = CodigosMensajes.EMPRESA_NO_EXISTE + " - " + Mensajes.EMPRESA_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.EMPRESA_NO_EXISTE, Constantes.ERROR,
				getValorEmpresa(datosEntradaEliminarEmpresaNoExiste));
	}

	private DatosPruebaAcciones getTestEliminarEmpresaAsociadaPersonas(
			final EmpresaEntity datosEntradaEliminarEmpresaAsociadaPersonas) throws java.text.ParseException {
		final String resultadoObtenido = eliminarEmpresaPersonasAsociadas(datosEntradaEliminarEmpresaAsociadaPersonas);

		final String resultadoEsperado = CodigosMensajes.EMPRESA_ASOCIADA_PERSONAS + " - "
				+ Mensajes.EMPRESA_TIENE_PERSONAS_ASOCIADAS;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.EMPRESA_ASOCIADA_PERSONAS, Constantes.ERROR,
				getValorEmpresa(datosEntradaEliminarEmpresaAsociadaPersonas));
	}

	private DatosPruebaAcciones getTestReactivarEmpresaCorrecto(
			final EmpresaEntity datosEntradaReactivarEmpresaCorrecto) {
		final String resultadoObtenido = reactivarEmpresa(datosEntradaReactivarEmpresaCorrecto);

		final String resultadoEsperado = CodigosMensajes.REACTIVAR_EMPRESA_CORRECTO + " - "
				+ Mensajes.EMPRESA_REACTIVADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.REACTIVAR_EMPRESA_CORRECTO, Constantes.EXITO,
				getValorEmpresa(datosEntradaReactivarEmpresaCorrecto));
	}

	private DatosPruebaAcciones getTestReactivarEmpresaNoExiste(
			final EmpresaEntity datosEntradaReactivarEmpresaNoExiste) {
		final String resultadoObtenido = reactivarEmpresaNoExiste(datosEntradaReactivarEmpresaNoExiste);

		final String resultadoEsperado = CodigosMensajes.EMPRESA_NO_EXISTE + " - " + Mensajes.EMPRESA_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.EMPRESA_NO_EXISTE, Constantes.ERROR,
				getValorEmpresa(datosEntradaReactivarEmpresaNoExiste));
	}

	private String buscarEmpresa(final EmpresaEntity empresa) {
		String resultado = StringUtils.EMPTY;

		empresaRepository.findEmpresa(empresa.getCifEmpresa(), empresa.getNombreEmpresa(),
				empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa());

		resultado = CodigosMensajes.BUSCAR_EMPRESA_CORRECTO + " - " + Mensajes.BUSCAR_EMPRESA_CORRECTAMENTE;

		return resultado;
	}

	private String guardarEmpresa(final EmpresaEntity empresa) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarCifEmpresa(empresa.getCifEmpresa())
				&& !validaciones.comprobarNombreEmpresa(empresa.getNombreEmpresa())
				&& !validaciones.comprobarDireccionEmpresa(empresa.getDireccionEmpresa())
				&& !validaciones.comprobarTelefonoEmpresa(empresa.getTelefonoEmpresa())) {
			resultado = CodigosMensajes.EMPRESA_VACIA + " - " + Mensajes.EMPRESA_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarCifEmpresa(empresa.getCifEmpresa())) {
			resultado = CodigosMensajes.CIF_EMPRESA_VACIO + " - " + Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreEmpresa(empresa.getNombreEmpresa())) {
			resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDireccionEmpresa(empresa.getDireccionEmpresa())) {
			resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarTelefonoEmpresa(empresa.getTelefonoEmpresa())) {
			resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
		} else {
			final EmpresaEntity empresaBD = empresaRepository.findByCif(empresa.getCifEmpresa());

			if (empresaBD == null) {
				empresaRepository.saveAndFlush(empresa);
				resultado = CodigosMensajes.GUARDAR_EMPRESA_CORRECTO + " - " + Mensajes.EMPRESA_GUARDADA_CORRECTAMENTE;

				empresaRepository.deleteEmpresa(empresa.getCifEmpresa());

			} else {
				resultado = CodigosMensajes.EMPRESA_YA_EXISTE + " - " + Mensajes.EMPRESA_YA_EXISTE;
			}
		}

		return resultado;
	}

	private String modificarEmpresa(final EmpresaEntity empresa) {
		String resultado = StringUtils.EMPTY;
		if (!validaciones.comprobarCifEmpresa(empresa.getCifEmpresa())
				&& !validaciones.comprobarNombreEmpresa(empresa.getNombreEmpresa())
				&& !validaciones.comprobarDireccionEmpresa(empresa.getDireccionEmpresa())
				&& !validaciones.comprobarTelefonoEmpresa(empresa.getTelefonoEmpresa())) {
			resultado = CodigosMensajes.EMPRESA_VACIA + " - " + Mensajes.EMPRESA_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarCifEmpresa(empresa.getCifEmpresa())) {
			resultado = CodigosMensajes.CIF_EMPRESA_VACIO + " - " + Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreEmpresa(empresa.getNombreEmpresa())) {
			resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDireccionEmpresa(empresa.getDireccionEmpresa())) {
			resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarTelefonoEmpresa(empresa.getTelefonoEmpresa())) {
			resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
		} else {
			empresaRepository.saveAndFlush(empresa);
			final EmpresaEntity empresaBD = empresaRepository.findByCif(empresa.getCifEmpresa());

			if (empresaBD != null) {
				empresa.setCifEmpresa(empresa.getCifEmpresa());
				empresa.setNombreEmpresa("Modificado");
				empresa.setDireccionEmpresa("Direccion nueva");
				empresa.setTelefonoEmpresa(empresa.getTelefonoEmpresa());

				empresaRepository.saveAndFlush(empresa);

				resultado = CodigosMensajes.MODIFICAR_EMPRESA_CORRECTO + " - "
						+ Mensajes.EMPRESA_MODIFICADA_CORRECTAMENTE;

				empresaRepository.deleteEmpresa(empresa.getCifEmpresa());

			}
		}

		return resultado;
	}

	private String modificarEmpresaNoExiste(final EmpresaEntity empresa) {
		String resultado = StringUtils.EMPTY;
		if (!validaciones.comprobarCifEmpresa(empresa.getCifEmpresa())
				&& !validaciones.comprobarNombreEmpresa(empresa.getNombreEmpresa())
				&& !validaciones.comprobarDireccionEmpresa(empresa.getDireccionEmpresa())
				&& !validaciones.comprobarTelefonoEmpresa(empresa.getTelefonoEmpresa())) {
			resultado = CodigosMensajes.EMPRESA_VACIA + " - " + Mensajes.EMPRESA_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarCifEmpresa(empresa.getCifEmpresa())) {
			resultado = CodigosMensajes.CIF_EMPRESA_VACIO + " - " + Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreEmpresa(empresa.getNombreEmpresa())) {
			resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDireccionEmpresa(empresa.getDireccionEmpresa())) {
			resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarTelefonoEmpresa(empresa.getTelefonoEmpresa())) {
			resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
		} else {
			final EmpresaEntity empresaBD = empresaRepository.findByCif(empresa.getCifEmpresa());

			if (empresaBD != null) {
				empresa.setCifEmpresa(empresa.getCifEmpresa());
				empresa.setNombreEmpresa("Modificado");
				empresa.setDireccionEmpresa("Direccion nueva");
				empresa.setTelefonoEmpresa(empresa.getTelefonoEmpresa());

				empresaRepository.saveAndFlush(empresa);

				resultado = CodigosMensajes.MODIFICAR_EMPRESA_CORRECTO + " - "
						+ Mensajes.EMPRESA_MODIFICADA_CORRECTAMENTE;

			} else {
				resultado = CodigosMensajes.EMPRESA_NO_EXISTE + " - " + Mensajes.EMPRESA_NO_EXISTE;
			}
		}

		return resultado;
	}

	private String eliminarEmpresa(final EmpresaEntity empresa) {
		String resultado = StringUtils.EMPTY;

		empresaRepository.saveAndFlush(empresa);

		final EmpresaEntity empresaBD = empresaRepository.findByCif(empresa.getCifEmpresa());

		if (empresaBD != null) {
			empresa.setIdEmpresa(empresaBD.getIdEmpresa());
			empresa.setBorradoEmpresa(1);
			empresaRepository.saveAndFlush(empresa);

			resultado = CodigosMensajes.ELIMINAR_EMPRESA_CORRECTO + " - " + Mensajes.EMPRESA_ELIMINADA_CORRECTAMENTE;
		}

		empresaRepository.deleteEmpresa(empresa.getCifEmpresa());

		return resultado;
	}

	private String eliminarEmpresaNoExiste(final EmpresaEntity empresa) {
		String resultado = StringUtils.EMPTY;

		final EmpresaEntity empresaBD = empresaRepository.findByCif(empresa.getCifEmpresa());

		if (empresaBD == null) {
			resultado = CodigosMensajes.EMPRESA_NO_EXISTE + " - " + Mensajes.EMPRESA_NO_EXISTE;
		}

		return resultado;
	}

	private String eliminarEmpresaPersonasAsociadas(final EmpresaEntity empresa) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		empresaRepository.saveAndFlush(empresa);

		final PersonaEntity persona = new PersonaEntity("34567654R", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, empresa);

		personaRepository.saveAndFlush(persona);

		final EmpresaEntity empresaBD = empresaRepository.findByCif(empresa.getCifEmpresa());

		if (empresaBD != null) {
			final List<PersonaEntity> personas = personaRepository.findAll();
			for (final PersonaEntity person : personas) {
				if (person.getEmpresa() != null
						&& person.getEmpresa().getCifEmpresa().equals(empresa.getCifEmpresa())) {
					resultado = CodigosMensajes.EMPRESA_ASOCIADA_PERSONAS + " - "
							+ Mensajes.EMPRESA_TIENE_PERSONAS_ASOCIADAS;
				}
			}
		}

		personaRepository.deletePersona(persona.getDniP());
		empresaRepository.deleteEmpresa(empresa.getCifEmpresa());

		return resultado;

	}

	private String reactivarEmpresa(final EmpresaEntity empresa) {
		String resultado = StringUtils.EMPTY;

		empresaRepository.saveAndFlush(empresa);

		final EmpresaEntity empresaBD = empresaRepository.findByCif(empresa.getCifEmpresa());

		if (empresaBD != null) {
			empresa.setIdEmpresa(empresaBD.getIdEmpresa());
			empresa.setBorradoEmpresa(0);
			empresaRepository.saveAndFlush(empresa);

			resultado = CodigosMensajes.REACTIVAR_EMPRESA_CORRECTO + " - " + Mensajes.EMPRESA_REACTIVADA_CORRECTAMENTE;
		}

		empresaRepository.deleteEmpresa(empresa.getCifEmpresa());

		return resultado;
	}

	private String reactivarEmpresaNoExiste(final EmpresaEntity empresa) {
		String resultado = StringUtils.EMPTY;

		final EmpresaEntity empresaBD = empresaRepository.findByCif(empresa.getCifEmpresa());

		if (empresaBD == null) {
			resultado = CodigosMensajes.EMPRESA_NO_EXISTE + " - " + Mensajes.EMPRESA_NO_EXISTE;
		}

		return resultado;
	}

	private Map<String, String> getValorEmpresa(final EmpresaEntity empresa) {

		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.CIF_EMPRESA, empresa.getCifEmpresa());
		valor.put(Constantes.NOMBRE_EMPRESA, empresa.getNombreEmpresa());
		valor.put(Constantes.DIRECCION_EMPRESA, empresa.getDireccionEmpresa());
		valor.put(Constantes.TELEFONO_EMPRESA, empresa.getTelefonoEmpresa());

		return valor;
	}

}
