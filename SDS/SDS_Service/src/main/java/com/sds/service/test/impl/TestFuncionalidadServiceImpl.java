package com.sds.service.test.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.FuncionalidadEntity;
import com.sds.model.RolAccionFuncionalidadEntity;
import com.sds.repository.FuncionalidadRepository;
import com.sds.repository.RolAccionFuncionalidadRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestFuncionalidadService;
import com.sds.service.test.impl.atributos.TestAtributoFuncionalidadDescription;
import com.sds.service.test.impl.atributos.TestAtributoFuncionalidadName;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestFuncionalidadServiceImpl implements TestFuncionalidadService {

	private final TestAtributoFuncionalidadName testAtributoFuncionalidadName;
	private final TestAtributoFuncionalidadDescription testAtributoFuncionalidadDescription;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	RolAccionFuncionalidadRepository rolAccionFuncionalidadRepository;

	@Autowired
	FuncionalidadRepository funcionalidadRepository;

	public TestFuncionalidadServiceImpl() {
		testAtributoFuncionalidadName = new TestAtributoFuncionalidadName();
		testAtributoFuncionalidadDescription = new TestAtributoFuncionalidadDescription();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFuncionalidadName()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final FuncionalidadEntity datosEntradaFuncionalidadNameVacio = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDAD_NAME_VACIO_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadNameCaracteresEspeciales = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadNameAlfabeticoMenor3 = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_ALFABETICO_MENOR_3_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadNameAlfabeticoMayor48 = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_ALFABETICO_MAYOR_48_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadNameNumerico = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_NUMERICO_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadNameAlfabetico = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_ALFABETICO_DATA);

		datosPruebaAtributos
				.add(testAtributoFuncionalidadName.getTestFuncionalidadNameVacio(datosEntradaFuncionalidadNameVacio));
		datosPruebaAtributos.add(testAtributoFuncionalidadName.getTestFuncionalidadNameAlfabeticoCaracteresEspeciales(
				datosEntradaFuncionalidadNameCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFuncionalidadName
				.getTestFuncionalidadNameAlfabeticoMenor3(datosEntradaFuncionalidadNameAlfabeticoMenor3));
		datosPruebaAtributos.add(testAtributoFuncionalidadName
				.getTestFuncionalidadNameAlfabeticoMayor48(datosEntradaFuncionalidadNameAlfabeticoMayor48));
		datosPruebaAtributos.add(
				testAtributoFuncionalidadName.getTestFuncionalidadNameNumerico(datosEntradaFuncionalidadNameNumerico));
		datosPruebaAtributos.add(testAtributoFuncionalidadName
				.getTestFuncionalidadNameCorrectoAlfabetico(datosEntradaFuncionalidadNameAlfabetico));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFuncionalidadNameBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final FuncionalidadEntity datosEntradaFuncionalidadNameCaracteresEspeciales = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadNameAlfabeticoMayor48 = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_ALFABETICO_MAYOR_48_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadNameNumerico = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_NUMERICO_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadNameAlfabetico = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME,
				Constantes.FUNCIONALIDADNAME_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoFuncionalidadName.getTestFuncionalidadNameAlfabeticoCaracteresEspeciales(
				datosEntradaFuncionalidadNameCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFuncionalidadName
				.getTestFuncionalidadNameAlfabeticoMayor48(datosEntradaFuncionalidadNameAlfabeticoMayor48));
		datosPruebaAtributos.add(
				testAtributoFuncionalidadName.getTestFuncionalidadNameNumerico(datosEntradaFuncionalidadNameNumerico));
		datosPruebaAtributos.add(testAtributoFuncionalidadName
				.getTestFuncionalidadNameCorrectoAlfabetico(datosEntradaFuncionalidadNameAlfabetico));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFuncionalidadDescription()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final FuncionalidadEntity datosEntradaFuncionalidadDescriptionVacio = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION,
				Constantes.FUNCIONALIDAD_DESCRIPTION_VACIO_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadDescriptionCaracteresEspeciales = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION,
						Constantes.FUNCIONALIDADDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadDescriptionAlfabeticoMenor3 = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION,
						Constantes.FUNCIONALIDADDESCRIPTION_ALFABETICO_MENOR_3_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadDescriptionAlfabetico = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION,
				Constantes.FUNCIONALIDADDESCRIPTION_ALFABETICO_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadDescriptionNumerico = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION,
				Constantes.FUNCIONALIDADDESCRIPTION_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoFuncionalidadDescription
				.getTestFuncionalidadDescriptionVacio(datosEntradaFuncionalidadDescriptionVacio));
		datosPruebaAtributos
				.add(testAtributoFuncionalidadDescription.getTestFuncionalidadDescriptionAlfabeticoCaracteresEspeciales(
						datosEntradaFuncionalidadDescriptionCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFuncionalidadDescription
				.getTestFuncionalidadDescriptionAlfabeticoMenor3(datosEntradaFuncionalidadDescriptionAlfabeticoMenor3));
		datosPruebaAtributos.add(testAtributoFuncionalidadDescription
				.getTestFuncionalidadDescriptionNumerico(datosEntradaFuncionalidadDescriptionNumerico));
		datosPruebaAtributos.add(testAtributoFuncionalidadDescription
				.getTestFuncionalidadDescriptionAlfabeticoCorrecto(datosEntradaFuncionalidadDescriptionAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFuncionalidadDescriptionBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final FuncionalidadEntity datosEntradaFuncionalidadDescriptionCaracteresEspeciales = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION,
						Constantes.FUNCIONALIDADDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadDescriptionAlfabetico = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION,
				Constantes.FUNCIONALIDADDESCRIPTION_ALFABETICO_DATA);
		final FuncionalidadEntity datosEntradaFuncionalidadDescriptionNumerico = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION,
				Constantes.FUNCIONALIDADDESCRIPTION_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoFuncionalidadDescription.getTestFuncionalidadDescriptionAlfabeticoCaracteresEspeciales(
						datosEntradaFuncionalidadDescriptionCaracteresEspeciales));

		datosPruebaAtributos.add(testAtributoFuncionalidadDescription
				.getTestFuncionalidadDescriptionNumerico(datosEntradaFuncionalidadDescriptionNumerico));
		datosPruebaAtributos.add(testAtributoFuncionalidadDescription
				.getTestFuncionalidadDescriptionAlfabeticoCorrecto(datosEntradaFuncionalidadDescriptionAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesFuncionalidadBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final FuncionalidadEntity datosEntradaBuscarFuncionalidadCorrecto = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.BUSCAR_FUNCIONALIDAD);
		final FuncionalidadEntity datosEntradaBuscarFuncionalidadNameVacio = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.FUNCIONALIDAD_NAME_VACIO_DATA);
		final FuncionalidadEntity datosEntradaBuscarFuncionalidadDescriptionVacio = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.FUNCIONALIDAD_DESCRIPTION_VACIO_DATA);
		final FuncionalidadEntity datosEntradaBuscarFuncionalidadNameDescriptionVacios = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
						Constantes.FUNCIONALIDAD_NAME_DESCRIPTION_VACIOS);

		datosPruebaAcciones.add(getTestBuscarFuncionalidad(datosEntradaBuscarFuncionalidadCorrecto));
		datosPruebaAcciones.add(getTestBuscarFuncionalidad(datosEntradaBuscarFuncionalidadNameVacio));
		datosPruebaAcciones.add(getTestBuscarFuncionalidad(datosEntradaBuscarFuncionalidadDescriptionVacio));
		datosPruebaAcciones.add(getTestBuscarFuncionalidad(datosEntradaBuscarFuncionalidadNameDescriptionVacios));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesFuncionalidadGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final FuncionalidadEntity datosEntradaGuardarFuncionalidadCorrecto = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.GUARDAR_FUNCIONALIDAD);
		final FuncionalidadEntity datosEntradaGuardarFuncionalidadNameVacio = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.FUNCIONALIDAD_NAME_VACIO_DATA);
		final FuncionalidadEntity datosEntradaGuardarFuncionalidadDescriptionVacio = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.FUNCIONALIDAD_DESCRIPTION_VACIO_DATA);
		final FuncionalidadEntity datosEntradaGuardarFuncionalidadNameDescriptionVacio = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
						Constantes.FUNCIONALIDAD_NAME_DESCRIPTION_VACIOS);
		final FuncionalidadEntity datosEntradaGuardarFuncionalidadYaExiste = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.FUNCIONALIDAD_YA_EXISTE);

		datosPruebaAcciones.add(getTestGuardarFuncionalidadCorrecto(datosEntradaGuardarFuncionalidadCorrecto));
		datosPruebaAcciones.add(getTestGuardarFuncionalidadNameVacio(datosEntradaGuardarFuncionalidadNameVacio));
		datosPruebaAcciones
				.add(getTestGuardarFuncionalidadDescriptionVacio(datosEntradaGuardarFuncionalidadDescriptionVacio));
		datosPruebaAcciones.add(
				getTestGuardarFuncionalidadNameDescriptionVacio(datosEntradaGuardarFuncionalidadNameDescriptionVacio));
		datosPruebaAcciones.add(getTestGuardarFuncionalidadYaExiste(datosEntradaGuardarFuncionalidadYaExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesFuncionalidadEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final FuncionalidadEntity datosEntradaEliminarFuncionalidadCorrecto = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.ELIMINAR_FUNCIONALIDAD);
		final FuncionalidadEntity datosEntradaEliminarFuncionalidadNoExiste = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.ELIMINAR_FUNCIONALIDAD);
		final FuncionalidadEntity datosEntradaEliminarFuncionalidadAsociadaRolAccion = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION);

		datosPruebaAcciones.add(getTestEliminarFuncionalidadCorrecto(datosEntradaEliminarFuncionalidadCorrecto));
		datosPruebaAcciones.add(getTestEliminarFuncionalidadNoExiste(datosEntradaEliminarFuncionalidadNoExiste));
		datosPruebaAcciones
				.add(getTestEliminarFuncionalidadAsociadaRolAccion(datosEntradaEliminarFuncionalidadAsociadaRolAccion));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesFuncionalidadModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final FuncionalidadEntity datosEntradaModificarFuncionalidadCorrecto = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.MODIFICAR_FUNCIONALIDAD);
		final FuncionalidadEntity datosEntradaModificarFuncionalidadNameVacio = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.FUNCIONALIDAD_NAME_VACIO_DATA);
		final FuncionalidadEntity datosEntradaModificarFuncionalidadDescriptionVacio = generarJSON.generarFuncionalidad(
				Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.FUNCIONALIDAD_DESCRIPTION_VACIO_DATA);
		final FuncionalidadEntity datosEntradaModificarFuncionalidadNameDescriptionVacio = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
						Constantes.FUNCIONALIDAD_NAME_DESCRIPTION_VACIOS);
		final FuncionalidadEntity datosEntradaModificarFuncionalidadNoExiste = generarJSON
				.generarFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA, Constantes.MODIFICAR_FUNCIONALIDAD);

		datosPruebaAcciones.add(getTestModificarFuncionalidadCorrecto(datosEntradaModificarFuncionalidadCorrecto));
		datosPruebaAcciones.add(getTestModificarFuncionalidadNameVacio(datosEntradaModificarFuncionalidadNameVacio));
		datosPruebaAcciones
				.add(getTestModificarFuncionalidadDescriptionVacio(datosEntradaModificarFuncionalidadDescriptionVacio));
		datosPruebaAcciones.add(getTestModificarFuncionalidadNameDescriptionVacios(
				datosEntradaModificarFuncionalidadNameDescriptionVacio));
		datosPruebaAcciones.add(getTestModificarFuncionalidadNoExiste(datosEntradaModificarFuncionalidadNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarFuncionalidad(
			final FuncionalidadEntity datosEntradaAccionBuscarFuncionalidad) {

		final String resultadoObtenido = buscarFuncionalidad(datosEntradaAccionBuscarFuncionalidad);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_FUNCIONALIDAD_CORRECTO + " - "
				+ Mensajes.BUSCAR_FUNCIONALIDAD_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO,
				getValorFuncionalidad(datosEntradaAccionBuscarFuncionalidad));

	}

	private DatosPruebaAcciones getTestGuardarFuncionalidadCorrecto(
			final FuncionalidadEntity datosEntradaAccionGuardarFuncionalidad) {

		final String resultadoObtenido = guardarFuncionalidad(datosEntradaAccionGuardarFuncionalidad);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_FUNCIONALIDAD_CORRECTO + " - "
				+ Mensajes.GUARDAR_FUNCIONALIDAD_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_FUNCIONALIDAD_CORRECTO, Constantes.EXITO,
				getValorFuncionalidad(datosEntradaAccionGuardarFuncionalidad));

	}

	private DatosPruebaAcciones getTestGuardarFuncionalidadYaExiste(
			final FuncionalidadEntity datosEntradaAccionGuardarFuncionalidadYaExiste) {

		final String resultadoObtenido = guardarFuncionalidad(datosEntradaAccionGuardarFuncionalidadYaExiste);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_YA_EXISTE + " - "
				+ Mensajes.FUNCIONALIDAD_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_YA_EXISTE, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionGuardarFuncionalidadYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarFuncionalidadNameVacio(
			final FuncionalidadEntity datosEntradaAccionGuardarFuncionalidadNameVacio) {

		final String resultadoObtenido = guardarFuncionalidad(datosEntradaAccionGuardarFuncionalidadNameVacio);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NAME_VACIA + " - "
				+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_NAME_VACIO, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionGuardarFuncionalidadNameVacio));

	}

	private DatosPruebaAcciones getTestGuardarFuncionalidadDescriptionVacio(
			final FuncionalidadEntity datosEntradaAccionGuardarFuncionalidadDescriptionVacio) {

		final String resultadoObtenido = guardarFuncionalidad(datosEntradaAccionGuardarFuncionalidadDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_VACIA + " - "
				+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_DESCRIPTION_VACIO, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionGuardarFuncionalidadDescriptionVacio));

	}

	private DatosPruebaAcciones getTestGuardarFuncionalidadNameDescriptionVacio(
			final FuncionalidadEntity datosEntradaAccionGuardarFuncionalidadNameDescriptionVacio) {

		final String resultadoObtenido = guardarFuncionalidad(
				datosEntradaAccionGuardarFuncionalidadNameDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_VACIA + " - " + Mensajes.FUNCIONALIDAD_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_VACIA, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionGuardarFuncionalidadNameDescriptionVacio));

	}

	private DatosPruebaAcciones getTestEliminarFuncionalidadCorrecto(
			final FuncionalidadEntity datosEntradaAccionEliminarFuncionalidadCorrecto) {

		final String resultadoObtenido = eliminarFuncionalidad(datosEntradaAccionEliminarFuncionalidadCorrecto);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_FUNCIONALIDAD_CORRECTO + " - "
				+ Mensajes.ELIMINAR_FUNCIONALIDAD_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_FUNCIONALIDAD_CORRECTO, Constantes.EXITO,
				getValorFuncionalidad(datosEntradaAccionEliminarFuncionalidadCorrecto));

	}

	private DatosPruebaAcciones getTestEliminarFuncionalidadAsociadaRolAccion(
			final FuncionalidadEntity datosEntradaAccionEliminarFuncionalidadAsociadaRolAccion) {

		final String resultadoObtenido = eliminarFuncionalidadAsociadaRolAccion(
				datosEntradaAccionEliminarFuncionalidadAsociadaRolAccion);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION + " - "
				+ Mensajes.ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionEliminarFuncionalidadAsociadaRolAccion));

	}

	private DatosPruebaAcciones getTestEliminarFuncionalidadNoExiste(
			final FuncionalidadEntity datosEntradaAccionEliminarFuncionalidadNoExiste) {

		final String resultadoObtenido = eliminarFuncionalidadNoExiste(datosEntradaAccionEliminarFuncionalidadNoExiste);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NO_EXISTE + " - "
				+ Mensajes.FUNCIONALIDAD_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_NO_EXISTE, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionEliminarFuncionalidadNoExiste));

	}

	private DatosPruebaAcciones getTestModificarFuncionalidadCorrecto(
			final FuncionalidadEntity datosEntradaAccionModificarFuncionalidadCorrecto) {

		final String resultadoObtenido = modificarFuncionalidad(datosEntradaAccionModificarFuncionalidadCorrecto);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_FUNCIONALIDAD_CORRECTO + " - "
				+ Mensajes.MODIFICAR_FUNCIONALIDAD_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_FUNCIONALIDAD_CORRECTO, Constantes.EXITO,
				getValorFuncionalidad(datosEntradaAccionModificarFuncionalidadCorrecto));

	}

	private DatosPruebaAcciones getTestModificarFuncionalidadNoExiste(
			final FuncionalidadEntity datosEntradaAccionModificarFuncionalidadNoExiste) {

		final String resultadoObtenido = modificarFuncionalidadNoExiste(
				datosEntradaAccionModificarFuncionalidadNoExiste);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NO_EXISTE + " - "
				+ Mensajes.FUNCIONALIDAD_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_NO_EXISTE, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionModificarFuncionalidadNoExiste));

	}

	private DatosPruebaAcciones getTestModificarFuncionalidadNameVacio(
			final FuncionalidadEntity datosEntradaAccionModificarFuncionalidadNameVacio) {

		final String resultadoObtenido = modificarFuncionalidad(datosEntradaAccionModificarFuncionalidadNameVacio);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NAME_VACIA + " - "
				+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_NAME_VACIO, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionModificarFuncionalidadNameVacio));

	}

	private DatosPruebaAcciones getTestModificarFuncionalidadDescriptionVacio(
			final FuncionalidadEntity datosEntradaAccionModificarFuncionalidadDescriptionVacio) {

		final String resultadoObtenido = modificarFuncionalidad(
				datosEntradaAccionModificarFuncionalidadDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_VACIA + " - "
				+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_DESCRIPTION_VACIO, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionModificarFuncionalidadDescriptionVacio));

	}

	private DatosPruebaAcciones getTestModificarFuncionalidadNameDescriptionVacios(
			final FuncionalidadEntity datosEntradaAccionModificarFuncionalidadNameDescriptionVacio) {

		final String resultadoObtenido = modificarFuncionalidad(
				datosEntradaAccionModificarFuncionalidadNameDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_VACIA + " - " + Mensajes.FUNCIONALIDAD_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_VACIA, Constantes.ERROR,
				getValorFuncionalidad(datosEntradaAccionModificarFuncionalidadNameDescriptionVacio));

	}

	private String buscarFuncionalidad(final FuncionalidadEntity funcionalidad) {

		String resultado = StringUtils.EMPTY;

		final List<FuncionalidadEntity> funcionalidadBD = funcionalidadRepository
				.findFuncionality(funcionalidad.getNombreFuncionalidad(), funcionalidad.getDescripFuncionalidad());

		resultado = CodigosMensajes.BUSCAR_FUNCIONALIDAD_CORRECTO + " - " + Mensajes.BUSCAR_FUNCIONALIDAD_CORRECTO;

		return resultado;
	}

	private String guardarFuncionalidad(final FuncionalidadEntity funcionalidad) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreFuncionalidadBlank(funcionalidad.getNombreFuncionalidad())
				&& !validaciones.comprobarDescripcionFuncionalidadBlank(funcionalidad.getDescripFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_VACIA + " - " + Mensajes.FUNCIONALIDAD_VACIA;
		} else if (!validaciones.comprobarNombreFuncionalidadBlank(funcionalidad.getNombreFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_NAME_VACIA + " - "
					+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarDescripcionFuncionalidadBlank(funcionalidad.getDescripFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_VACIA + " - "
					+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_VACIA;
		} else {
			final FuncionalidadEntity funcionalidadBD = funcionalidadRepository
					.findFuncionalityByName(funcionalidad.getNombreFuncionalidad());

			if (funcionalidadBD != null) {
				resultado = CodigosMensajes.FUNCIONALIDAD_YA_EXISTE + " - " + Mensajes.FUNCIONALIDAD_YA_EXISTE;
			} else {
				funcionalidadRepository.saveAndFlush(funcionalidad);
				resultado = CodigosMensajes.GUARDAR_FUNCIONALIDAD_CORRECTO + " - "
						+ Mensajes.GUARDAR_FUNCIONALIDAD_CORRECTO;

				final FuncionalidadEntity nuevaFuncionalidad = funcionalidadRepository
						.findFuncionalityByName(funcionalidad.getNombreFuncionalidad());

				funcionalidadRepository.deleteFuncionalidad(nuevaFuncionalidad.getIdFuncionalidad());
			}
		}

		return resultado;
	}

	private String eliminarFuncionalidad(final FuncionalidadEntity funcionalidad) {
		String resultado = StringUtils.EMPTY;

		funcionalidadRepository.saveAndFlush(funcionalidad);

		final FuncionalidadEntity funcionalidadBuscar = funcionalidadRepository
				.findFuncionalityByName(funcionalidad.getNombreFuncionalidad());
		funcionalidadBuscar.setBorradoFuncionalidad(1);
		funcionalidadRepository.saveAndFlush(funcionalidadBuscar);
		resultado = CodigosMensajes.ELIMINAR_FUNCIONALIDAD_CORRECTO + " - " + Mensajes.ELIMINAR_FUNCIONALIDAD_CORRECTO;
		funcionalidadRepository.deleteFuncionalidad(funcionalidadBuscar.getIdFuncionalidad());

		return resultado;
	}

	private String eliminarFuncionalidadAsociadaRolAccion(final FuncionalidadEntity funcionalidad) {
		String resultado = StringUtils.EMPTY;

		funcionalidadRepository.saveAndFlush(funcionalidad);

		final FuncionalidadEntity funcionalidadBuscar = funcionalidadRepository
				.findFuncionalityByName(funcionalidad.getNombreFuncionalidad());

		final RolAccionFuncionalidadEntity rolAccionFuncionalidad = new RolAccionFuncionalidadEntity(2,
				funcionalidadBuscar.getIdFuncionalidad(), 2);
		rolAccionFuncionalidadRepository.saveAndFlush(rolAccionFuncionalidad);

		if (funcionalidadBuscar != null) {
			final List<RolAccionFuncionalidadEntity> rolAccionFuncionalidadBD = rolAccionFuncionalidadRepository
					.findAll();

			for (int i = 0; i < rolAccionFuncionalidadBD.size(); i++) {
				if (rolAccionFuncionalidadBD.get(i).getIdFuncionalidad() == funcionalidadBuscar.getIdFuncionalidad()) {
					resultado = CodigosMensajes.ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION + " - "
							+ Mensajes.ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION;
				}
			}
		}

		rolAccionFuncionalidadRepository.deleteRolAccionFuncionalidad(funcionalidadBuscar.getIdFuncionalidad(), 2, 2);
		funcionalidadRepository.deleteFuncionalidad(funcionalidadBuscar.getIdFuncionalidad());
		return resultado;
	}

	private String eliminarFuncionalidadNoExiste(final FuncionalidadEntity funcionalidad) {
		String resultado = StringUtils.EMPTY;

		final FuncionalidadEntity funcionalidadBuscar = funcionalidadRepository
				.findFuncionalityByName(funcionalidad.getNombreFuncionalidad());

		if (funcionalidadBuscar == null) {
			resultado = CodigosMensajes.FUNCIONALIDAD_NO_EXISTE + " - " + Mensajes.FUNCIONALIDAD_NO_EXISTE;
		}
		return resultado;
	}

	private String modificarFuncionalidad(final FuncionalidadEntity funcionalidad) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreFuncionalidadBlank(funcionalidad.getNombreFuncionalidad())
				&& !validaciones.comprobarDescripcionFuncionalidadBlank(funcionalidad.getDescripFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_VACIA + " - " + Mensajes.FUNCIONALIDAD_VACIA;
		} else if (!validaciones.comprobarNombreFuncionalidadBlank(funcionalidad.getNombreFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_NAME_VACIA + " - "
					+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarDescripcionFuncionalidadBlank(funcionalidad.getDescripFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_VACIA + " - "
					+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_VACIA;
		} else {
			funcionalidadRepository.saveAndFlush(funcionalidad);

			final FuncionalidadEntity funcionalidadBuscar = funcionalidadRepository
					.findFuncionalityByName(funcionalidad.getNombreFuncionalidad());
			funcionalidadBuscar.setNombreFuncionalidad("Funcionalidad modificada");
			funcionalidadRepository.saveAndFlush(funcionalidadBuscar);
			resultado = CodigosMensajes.MODIFICAR_FUNCIONALIDAD_CORRECTO + " - "
					+ Mensajes.MODIFICAR_FUNCIONALIDAD_CORRECTO;
			funcionalidadRepository.deleteFuncionalidad(funcionalidadBuscar.getIdFuncionalidad());

		}

		return resultado;
	}

	private String modificarFuncionalidadNoExiste(final FuncionalidadEntity funcionalidad) {
		String resultado = StringUtils.EMPTY;
		if (!validaciones.comprobarNombreFuncionalidadBlank(funcionalidad.getNombreFuncionalidad())
				&& !validaciones.comprobarDescripcionFuncionalidadBlank(funcionalidad.getDescripFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_VACIA + " - " + Mensajes.FUNCIONALIDAD_VACIA;
		} else if (!validaciones.comprobarNombreFuncionalidadBlank(funcionalidad.getNombreFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_NAME_VACIA + " - "
					+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarDescripcionFuncionalidadBlank(funcionalidad.getDescripFuncionalidad())) {
			resultado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_VACIA + " - "
					+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_VACIA;
		} else {
			final FuncionalidadEntity funcionalidadBuscar = funcionalidadRepository
					.findFuncionalityByName(funcionalidad.getNombreFuncionalidad());

			if (funcionalidadBuscar == null) {
				resultado = CodigosMensajes.FUNCIONALIDAD_NO_EXISTE + " - " + Mensajes.FUNCIONALIDAD_NO_EXISTE;
			}

		}

		return resultado;
	}

	private Map<String, String> getValorFuncionalidad(final FuncionalidadEntity funcionalidad) {

		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.FUNCIONALIDAD_NAME, funcionalidad.getNombreFuncionalidad());
		valor.put(Constantes.FUNCIONALIDAD_DESCRIPTION, funcionalidad.getDescripFuncionalidad());

		return valor;
	}
}
