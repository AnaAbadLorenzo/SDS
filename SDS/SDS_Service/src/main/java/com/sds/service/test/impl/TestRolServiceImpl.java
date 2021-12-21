package com.sds.service.test.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.sds.model.RolEntity;
import com.sds.service.common.Constantes;
import com.sds.service.test.TestRolService;
import com.sds.service.test.impl.atributos.TestAtributoRolDescription;
import com.sds.service.test.impl.atributos.TestAtributoRolName;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestRolServiceImpl implements TestRolService {

	private final TestAtributoRolName testAtributoRolName;
	private final TestAtributoRolDescription testAtributoRolDescription;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	public TestRolServiceImpl() {
		testAtributoRolName = new TestAtributoRolName();
		testAtributoRolDescription = new TestAtributoRolDescription();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoRolName() throws IOException, ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<DatosPruebaAtributos>();

		final RolEntity datosEntradaRolNameVacio = generarJSON.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME,
				Constantes.ROL_NAME_VACIO_DATA);
		final RolEntity datosEntradaRolNameCaracteresEspeciales = generarJSON.generarRol(
				Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME, Constantes.ROLNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final RolEntity datosEntradaRolNameEspacios = generarJSON.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME,
				Constantes.ROLNAME_ALFABETICO_ESPACIOS_DATA);
		final RolEntity datosEntradaRolNameAlfabeticoMenor3 = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME, Constantes.ROLNAME_ALFABETICO_MENOR_3_DATA);
		final RolEntity datosEntradaRolNameAlfabeticoMayor32 = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME, Constantes.ROLNAME_ALFABETICO_MAYOR_32_DATA);
		final RolEntity datosEntradaRolNameNumerico = generarJSON.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME,
				Constantes.ROLNAME_NUMERICO_DATA);
		final RolEntity datosEntradaRolNameAlfabetico = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME, Constantes.ROLNAME_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoRolName.getTestRolNameVacio(datosEntradaRolNameVacio));
		datosPruebaAtributos.add(testAtributoRolName
				.getTestRolNameAlfabeticoCaracteresEspeciales(datosEntradaRolNameCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoRolName.getTestRolNameAlfabeticoEspacios(datosEntradaRolNameEspacios));
		datosPruebaAtributos
				.add(testAtributoRolName.getTestRolNameAlfabeticoMenor3(datosEntradaRolNameAlfabeticoMenor3));
		datosPruebaAtributos
				.add(testAtributoRolName.getTestRolNameAlfabeticoMayor32(datosEntradaRolNameAlfabeticoMayor32));
		datosPruebaAtributos.add(testAtributoRolName.getTestRolNameNumerico(datosEntradaRolNameNumerico));
		datosPruebaAtributos.add(testAtributoRolName.getTestRolNameCorrectoAlfabetico(datosEntradaRolNameAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoRolDescription() throws IOException, ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<DatosPruebaAtributos>();

		final RolEntity datosEntradaRolDescriptionVacio = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION, Constantes.ROL_DESCRIPTION_VACIO_DATA);
		final RolEntity datosEntradaRolDescriptionCaracteresEspeciales = generarJSON.generarRol(
				Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION,
				Constantes.ROLDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final RolEntity datosEntradaRolDescriptionAlfabeticoMenor3 = generarJSON.generarRol(
				Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION, Constantes.ROLDESCRIPTION_ALFABETICO_MENOR_3_DATA);
		final RolEntity datosEntradaRolDescriptionAlfabetico = generarJSON.generarRol(
				Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION, Constantes.ROLDESCRIPTION_ALFABETICO_DATA);
		final RolEntity datosEntradaRolDescriptionNumerico = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION, Constantes.ROLDESCRIPTION_NUMERICO_DATA);
		datosPruebaAtributos
				.add(testAtributoRolDescription.getTestRolDescriptionVacio(datosEntradaRolDescriptionVacio));
		datosPruebaAtributos.add(testAtributoRolDescription
				.getTestRolDescriptionAlfabeticoCaracteresEspeciales(datosEntradaRolDescriptionCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoRolDescription
				.getTestRolDescriptionAlfabeticoMenor3(datosEntradaRolDescriptionAlfabeticoMenor3));
		datosPruebaAtributos
				.add(testAtributoRolDescription.getTestRolDescriptionNumerico(datosEntradaRolDescriptionNumerico));
		datosPruebaAtributos.add(testAtributoRolDescription
				.getTestRolDescriptionAlfabeticoCorrecto(datosEntradaRolDescriptionAlfabetico));

		return datosPruebaAtributos;
	}

}
