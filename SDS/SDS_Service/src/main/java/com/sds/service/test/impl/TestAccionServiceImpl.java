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

import com.sds.model.AccionEntity;
import com.sds.model.RolAccionFuncionalidadEntity;
import com.sds.repository.AccionRepository;
import com.sds.repository.RolAccionFuncionalidadRepository;
import com.sds.repository.RolRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestAccionService;
import com.sds.service.test.impl.atributos.TestAtributoAccionDescription;
import com.sds.service.test.impl.atributos.TestAtributoAccionName;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestAccionServiceImpl implements TestAccionService {

	private final TestAtributoAccionName testAtributoAccionName;
	private final TestAtributoAccionDescription testAtributoAccionDescription;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	RolAccionFuncionalidadRepository rolAccionFuncionalidadRepository;

	@Autowired
	AccionRepository accionRepository;

	@Autowired
	RolRepository rolRepository;

	public TestAccionServiceImpl() {
		testAtributoAccionName = new TestAtributoAccionName();
		testAtributoAccionDescription = new TestAtributoAccionDescription();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoAccionName()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final AccionEntity datosEntradaAccionNameVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCION_NAME_VACIO_DATA);
		final AccionEntity datosEntradaAccionNameCaracteresEspeciales = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME,
				Constantes.ACCIONNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final AccionEntity datosEntradaAccionNameAlfabeticoEspacios = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_ALFABETICO_ESPACIOS_DATA);
		final AccionEntity datosEntradaAccionNameAlfabeticoMenor3 = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_ALFABETICO_MENOR_3_DATA);
		final AccionEntity datosEntradaAccionNameAlfabeticoMayor48 = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_ALFABETICO_MAYOR_48_DATA);
		final AccionEntity datosEntradaAccionNameNumerico = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_NUMERICO_DATA);
		final AccionEntity datosEntradaAccionNameAlfabetico = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoAccionName.getTestAccionNameVacio(datosEntradaAccionNameVacio));
		datosPruebaAtributos.add(testAtributoAccionName
				.getTestAccionNameAlfabeticoCaracteresEspeciales(datosEntradaAccionNameCaracteresEspeciales));
		datosPruebaAtributos.add(
				testAtributoAccionName.getTestAccionNameAlfabeticoEspacios(datosEntradaAccionNameAlfabeticoEspacios));
		datosPruebaAtributos
				.add(testAtributoAccionName.getTestAccionNameAlfabeticoMenor3(datosEntradaAccionNameAlfabeticoMenor3));
		datosPruebaAtributos.add(
				testAtributoAccionName.getTestAccionNameAlfabeticoMayor48(datosEntradaAccionNameAlfabeticoMayor48));
		datosPruebaAtributos.add(testAtributoAccionName.getTestAccionNameNumerico(datosEntradaAccionNameNumerico));
		datosPruebaAtributos
				.add(testAtributoAccionName.getTestAccionNameCorrectoAlfabetico(datosEntradaAccionNameAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoAccionNameBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final AccionEntity datosEntradaAccionNameCaracteresEspeciales = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME,
				Constantes.ACCIONNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final AccionEntity datosEntradaAccionNameAlfabeticoEspacios = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_ALFABETICO_ESPACIOS_DATA);
		final AccionEntity datosEntradaAccionNameAlfabeticoMayor48 = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_ALFABETICO_MAYOR_48_DATA);
		final AccionEntity datosEntradaAccionNameNumerico = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_NUMERICO_DATA);
		final AccionEntity datosEntradaAccionNameAlfabetico = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME, Constantes.ACCIONNAME_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoAccionName
				.getTestAccionNameAlfabeticoCaracteresEspeciales(datosEntradaAccionNameCaracteresEspeciales));
		datosPruebaAtributos.add(
				testAtributoAccionName.getTestAccionNameAlfabeticoEspacios(datosEntradaAccionNameAlfabeticoEspacios));
		datosPruebaAtributos.add(
				testAtributoAccionName.getTestAccionNameAlfabeticoMayor48(datosEntradaAccionNameAlfabeticoMayor48));
		datosPruebaAtributos.add(testAtributoAccionName.getTestAccionNameNumerico(datosEntradaAccionNameNumerico));
		datosPruebaAtributos
				.add(testAtributoAccionName.getTestAccionNameCorrectoAlfabetico(datosEntradaAccionNameAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoAccionDescription()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final AccionEntity datosEntradaAccionDescriptionVacio = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION, Constantes.ACCION_DESCRIPTION_VACIO_DATA);
		final AccionEntity datosEntradaAccionDescriptionCaracteresEspeciales = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION,
				Constantes.ACCIONDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final AccionEntity datosEntradaAccionDescriptionAlfabeticoMenor3 = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION,
				Constantes.ACCIONDESCRIPTION_ALFABETICO_MENOR_3_DATA);
		final AccionEntity datosEntradaAccionDescriptionAlfabetico = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION, Constantes.ACCIONDESCRIPTION_ALFABETICO_DATA);
		final AccionEntity datosEntradaAccionDescriptionNumerico = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION, Constantes.ACCIONDESCRIPTION_NUMERICO_DATA);
		datosPruebaAtributos
				.add(testAtributoAccionDescription.getTestAccionDescriptionVacio(datosEntradaAccionDescriptionVacio));
		datosPruebaAtributos.add(testAtributoAccionDescription.getTestAccionDescriptionAlfabeticoCaracteresEspeciales(
				datosEntradaAccionDescriptionCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoAccionDescription
				.getTestAccionDescriptionAlfabeticoMenor3(datosEntradaAccionDescriptionAlfabeticoMenor3));
		datosPruebaAtributos.add(
				testAtributoAccionDescription.getTestAccionDescriptionNumerico(datosEntradaAccionDescriptionNumerico));
		datosPruebaAtributos.add(testAtributoAccionDescription
				.getTestAccionDescriptionAlfabeticoCorrecto(datosEntradaAccionDescriptionAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoAccionDescriptionBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final AccionEntity datosEntradaAccionDescriptionCaracteresEspeciales = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION,
				Constantes.ACCIONDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final AccionEntity datosEntradaAccionDescriptionAlfabetico = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION, Constantes.ACCIONDESCRIPTION_ALFABETICO_DATA);
		final AccionEntity datosEntradaAccionDescriptionNumerico = generarJSON.generarAccion(
				Constantes.URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION, Constantes.ACCIONDESCRIPTION_NUMERICO_DATA);

		datosPruebaAtributos.add(testAtributoAccionDescription.getTestAccionDescriptionAlfabeticoCaracteresEspeciales(
				datosEntradaAccionDescriptionCaracteresEspeciales));
		datosPruebaAtributos.add(
				testAtributoAccionDescription.getTestAccionDescriptionNumerico(datosEntradaAccionDescriptionNumerico));
		datosPruebaAtributos.add(testAtributoAccionDescription
				.getTestAccionDescriptionAlfabeticoCorrecto(datosEntradaAccionDescriptionAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesAccionBuscar()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final AccionEntity datosEntradaBuscarAccionCorrecto = generarJSON.generarAccion(Constantes.URL_JSON_ACCION_DATA,
				Constantes.BUSCAR_ACCION);
		final AccionEntity datosEntradaBuscarAccionNameVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_VACIO_DATA);
		final AccionEntity datosEntradaBuscarAccionDescriptionVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_DESCRIPTION_VACIO_DATA);
		final AccionEntity datosEntradaBuscarAccionNameDescriptionVacios = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_DESCRIPTION_VACIOS);

		datosPruebaAcciones.add(getTestBuscarAccion(datosEntradaBuscarAccionCorrecto));
		datosPruebaAcciones.add(getTestBuscarAccion(datosEntradaBuscarAccionNameVacio));
		datosPruebaAcciones.add(getTestBuscarAccion(datosEntradaBuscarAccionDescriptionVacio));
		datosPruebaAcciones.add(getTestBuscarAccion(datosEntradaBuscarAccionNameDescriptionVacios));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesAccionGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final AccionEntity datosEntradaGuardarAccionCorrecto = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.GUARDAR_ACCION);
		final AccionEntity datosEntradaGuardarAccionNameVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_VACIO_DATA);
		final AccionEntity datosEntradaGuardarAccionDescriptionVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_DESCRIPTION_VACIO_DATA);
		final AccionEntity datosEntradaGuardarAccionNameDescriptionVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_DESCRIPTION_VACIOS);
		final AccionEntity datosEntradaGuardarAccionYaExiste = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_YA_EXISTE);

		datosPruebaAcciones.add(getTestGuardarAccionCorrecto(datosEntradaGuardarAccionCorrecto));
		datosPruebaAcciones.add(getTestGuardarAccionNameVacio(datosEntradaGuardarAccionNameVacio));
		datosPruebaAcciones.add(getTestGuardarAccionDescriptionVacio(datosEntradaGuardarAccionDescriptionVacio));
		datosPruebaAcciones
				.add(getTestGuardarAccionNameDescriptionVacio(datosEntradaGuardarAccionNameDescriptionVacio));
		datosPruebaAcciones.add(getTestGuardarAccionYaExiste(datosEntradaGuardarAccionYaExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesAccionEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final AccionEntity datosEntradaEliminarAccionCorrecto = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ELIMINAR_ACCION);
		final AccionEntity datosEntradaEliminarAccionNoExiste = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ELIMINAR_ACCION);
		final AccionEntity datosEntradaEliminarAccionAsociadaRolFuncionalidad = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD);

		datosPruebaAcciones.add(getTestEliminarAccionCorrecto(datosEntradaEliminarAccionCorrecto));
		datosPruebaAcciones.add(getTestEliminarAccionNoExiste(datosEntradaEliminarAccionNoExiste));
		datosPruebaAcciones
				.add(getTestEliminarAccionAsociadaRolFuncionalidad(datosEntradaEliminarAccionAsociadaRolFuncionalidad));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesAccionModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final AccionEntity datosEntradaModificarAccionCorrecto = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.MODIFICAR_ACCION);
		final AccionEntity datosEntradaGuardarAccionNameVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_VACIO_DATA);
		final AccionEntity datosEntradaModificarAccionDescriptionVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_DESCRIPTION_VACIO_DATA);
		final AccionEntity datosEntradaModificarAccionNameDescriptionVacio = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_DESCRIPTION_VACIOS);
		final AccionEntity datosEntradaModificarAccionNoExiste = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.MODIFICAR_ACCION);

		datosPruebaAcciones.add(getTestModificarAccionCorrecto(datosEntradaModificarAccionCorrecto));
		datosPruebaAcciones.add(getTestModificarAccionNameVacio(datosEntradaGuardarAccionNameVacio));
		datosPruebaAcciones.add(getTestModificarAccionDescriptionVacio(datosEntradaModificarAccionDescriptionVacio));
		datosPruebaAcciones
				.add(getTestModificarAccionNameDescriptionVacios(datosEntradaModificarAccionNameDescriptionVacio));
		datosPruebaAcciones.add(getTestModificarAccionNoExiste(datosEntradaModificarAccionNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesAccionReactivar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final AccionEntity datosEntradaReactivarAccionCorrecto = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.REACTIVAR_ACCION_CORRECTO);
		final AccionEntity datosEntradaReactivarAccionNoExiste = generarJSON
				.generarAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NO_EXISTE);

		datosPruebaAcciones.add(getTestReactivarAccionCorrecto(datosEntradaReactivarAccionCorrecto));
		datosPruebaAcciones.add(getTestReactivarAccionNoExiste(datosEntradaReactivarAccionNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarAccion(final AccionEntity datosEntradaAccionBuscarAccion) {

		final String resultadoObtenido = buscarAccion(datosEntradaAccionBuscarAccion);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_ACCION_CORRECTO + " - "
				+ Mensajes.BUSCAR_ACCION_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO, getValorAccion(datosEntradaAccionBuscarAccion));

	}

	private DatosPruebaAcciones getTestGuardarAccionCorrecto(final AccionEntity datosEntradaAccionGuardarAccion) {

		final String resultadoObtenido = guardarAccion(datosEntradaAccionGuardarAccion);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_ACCION_CORRECTO + " - "
				+ Mensajes.GUARDAR_ACCION_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_ACCION_CORRECTO, Constantes.EXITO,
				getValorAccion(datosEntradaAccionGuardarAccion));

	}

	private DatosPruebaAcciones getTestGuardarAccionYaExiste(
			final AccionEntity datosEntradaAccionGuardarAccionYaExiste) {

		final String resultadoObtenido = guardarAccion(datosEntradaAccionGuardarAccionYaExiste);

		final String resultadoEsperado = CodigosMensajes.ACCION_YA_EXISTE + " - " + Mensajes.ACCION_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_YA_EXISTE, Constantes.ERROR,
				getValorAccion(datosEntradaAccionGuardarAccionYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarAccionNameVacio(
			final AccionEntity datosEntradaAccionGuardarAccionNameVacio) {

		final String resultadoObtenido = guardarAccion(datosEntradaAccionGuardarAccionNameVacio);

		final String resultadoEsperado = CodigosMensajes.ACCION_NAME_VACIO + " - "
				+ Mensajes.ACCION_NAME_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_NAME_VACIO, Constantes.ERROR,
				getValorAccion(datosEntradaAccionGuardarAccionNameVacio));

	}

	private DatosPruebaAcciones getTestGuardarAccionDescriptionVacio(
			final AccionEntity datosEntradaAccionGuardarAccionDescriptionVacio) {

		final String resultadoObtenido = guardarAccion(datosEntradaAccionGuardarAccionDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.ACCION_DESCRIPTION_VACIO + " - "
				+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_DESCRIPTION_VACIO, Constantes.ERROR,
				getValorAccion(datosEntradaAccionGuardarAccionDescriptionVacio));

	}

	private DatosPruebaAcciones getTestGuardarAccionNameDescriptionVacio(
			final AccionEntity datosEntradaAccionGuardarAccionNameDescriptionVacio) {

		final String resultadoObtenido = guardarAccion(datosEntradaAccionGuardarAccionNameDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.ACCION_VACIA + " - " + Mensajes.ACCION_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_VACIA, Constantes.ERROR,
				getValorAccion(datosEntradaAccionGuardarAccionNameDescriptionVacio));

	}

	private DatosPruebaAcciones getTestModificarAccionCorrecto(
			final AccionEntity datosEntradaAccionModificarAccionCorrecto) {

		final String resultadoObtenido = modificarAccion(datosEntradaAccionModificarAccionCorrecto);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_ACCION_CORRECTO + " - "
				+ Mensajes.MODIFICAR_ACCION_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_ACCION_CORRECTO, Constantes.EXITO,
				getValorAccion(datosEntradaAccionModificarAccionCorrecto));

	}

	private DatosPruebaAcciones getTestModificarAccionNoExiste(
			final AccionEntity datosEntradaAccionModificarAccionNoExiste) {

		final String resultadoObtenido = modificarAccionNoExiste(datosEntradaAccionModificarAccionNoExiste);

		final String resultadoEsperado = CodigosMensajes.ACCION_NO_EXISTE + " - " + Mensajes.ACCION_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_NO_EXISTE, Constantes.ERROR,
				getValorAccion(datosEntradaAccionModificarAccionNoExiste));

	}

	private DatosPruebaAcciones getTestModificarAccionNameVacio(
			final AccionEntity datosEntradaAccionModificarAccionNameVacio) {

		final String resultadoObtenido = modificarAccion(datosEntradaAccionModificarAccionNameVacio);

		final String resultadoEsperado = CodigosMensajes.ACCION_NAME_VACIO + " - "
				+ Mensajes.ACCION_NAME_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_NAME_VACIO, Constantes.ERROR,
				getValorAccion(datosEntradaAccionModificarAccionNameVacio));

	}

	private DatosPruebaAcciones getTestModificarAccionDescriptionVacio(
			final AccionEntity datosEntradaAccionModificarAccionDescriptionVacio) {

		final String resultadoObtenido = modificarAccion(datosEntradaAccionModificarAccionDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.ACCION_DESCRIPTION_VACIO + " - "
				+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_DESCRIPTION_VACIO, Constantes.ERROR,
				getValorAccion(datosEntradaAccionModificarAccionDescriptionVacio));

	}

	private DatosPruebaAcciones getTestModificarAccionNameDescriptionVacios(
			final AccionEntity datosEntradaAccionModificarAccionNameDescriptionVacio) {

		final String resultadoObtenido = modificarAccion(datosEntradaAccionModificarAccionNameDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.ACCION_VACIA + " - " + Mensajes.ACCION_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_VACIA, Constantes.ERROR,
				getValorAccion(datosEntradaAccionModificarAccionNameDescriptionVacio));

	}

	private DatosPruebaAcciones getTestEliminarAccionCorrecto(
			final AccionEntity datosEntradaAccionEliminarAccionCorrecto) {

		final String resultadoObtenido = eliminarAccion(datosEntradaAccionEliminarAccionCorrecto);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_ACCION_CORRECTO + " - "
				+ Mensajes.ELIMINAR_ACCION_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_ACCION_CORRECTO, Constantes.EXITO,
				getValorAccion(datosEntradaAccionEliminarAccionCorrecto));

	}

	private DatosPruebaAcciones getTestEliminarAccionAsociadaRolFuncionalidad(
			final AccionEntity datosEntradaAccionEliminarAccionAsociadaRolFuncionalidad) {

		final String resultadoObtenido = eliminarAccionAsociadaRolFuncionalidad(
				datosEntradaAccionEliminarAccionAsociadaRolFuncionalidad);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD + " - "
				+ Mensajes.ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD, Constantes.ERROR,
				getValorAccion(datosEntradaAccionEliminarAccionAsociadaRolFuncionalidad));

	}

	private DatosPruebaAcciones getTestEliminarAccionNoExiste(
			final AccionEntity datosEntradaAccionEliminarAccionNoExiste) {

		final String resultadoObtenido = eliminarAccionNoExiste(datosEntradaAccionEliminarAccionNoExiste);

		final String resultadoEsperado = CodigosMensajes.ACCION_NO_EXISTE + " - " + Mensajes.ACCION_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_NO_EXISTE, Constantes.ERROR,
				getValorAccion(datosEntradaAccionEliminarAccionNoExiste));

	}

	private DatosPruebaAcciones getTestReactivarAccionCorrecto(
			final AccionEntity datosEntradaAccionReactivarAccionCorrecto) {

		final String resultadoObtenido = reactivarAccion(datosEntradaAccionReactivarAccionCorrecto);

		final String resultadoEsperado = CodigosMensajes.REACTIVAR_ACCION_CORRECTO + " - "
				+ Mensajes.ACCION_REACTIVADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.REACTIVAR_ACCION_CORRECTO, Constantes.EXITO,
				getValorAccion(datosEntradaAccionReactivarAccionCorrecto));

	}

	private DatosPruebaAcciones getTestReactivarAccionNoExiste(
			final AccionEntity datosEntradaAccionReactivarAccionNoExiste) {

		final String resultadoObtenido = reactivarAccionNoExiste(datosEntradaAccionReactivarAccionNoExiste);

		final String resultadoEsperado = CodigosMensajes.ACCION_NO_EXISTE + " - " + Mensajes.ACCION_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_NO_EXISTE, Constantes.EXITO,
				getValorAccion(datosEntradaAccionReactivarAccionNoExiste));

	}

	private String buscarAccion(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		final List<AccionEntity> accionBD = accionRepository.findAccion(accion.getNombreAccion(),
				accion.getDescripAccion());

		resultado = CodigosMensajes.BUSCAR_ACCION_CORRECTO + " - " + Mensajes.BUSCAR_ACCION_CORRECTO;

		return resultado;
	}

	private String guardarAccion(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreAccionBlank(accion.getNombreAccion())
				&& !validaciones.comprobarDescripcionAccionBlank(accion.getDescripAccion())) {
			resultado = CodigosMensajes.ACCION_VACIA + " - " + Mensajes.ACCION_VACIA;
		} else if (!validaciones.comprobarNombreAccionBlank(accion.getNombreAccion())) {
			resultado = CodigosMensajes.ACCION_NAME_VACIO + " - " + Mensajes.ACCION_NAME_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripcionAccionBlank(accion.getDescripAccion())) {
			resultado = CodigosMensajes.ACCION_DESCRIPTION_VACIO + " - "
					+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_VACIO;
		} else {
			final AccionEntity accionBD = accionRepository.findAccionByName(accion.getNombreAccion());

			if (accionBD != null) {
				resultado = CodigosMensajes.ACCION_YA_EXISTE + " - " + Mensajes.ACCION_YA_EXISTE;
			} else {
				accionRepository.saveAndFlush(accion);
				resultado = CodigosMensajes.GUARDAR_ACCION_CORRECTO + " - " + Mensajes.GUARDAR_ACCION_CORRECTO;

				final AccionEntity nuevaAccion = accionRepository.findAccionByName(accion.getNombreAccion());

				accionRepository.deleteAccion(nuevaAccion.getIdAccion());
			}
		}

		return resultado;
	}

	private String modificarAccion(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreAccionBlank(accion.getNombreAccion())
				&& !validaciones.comprobarDescripcionAccionBlank(accion.getDescripAccion())) {
			resultado = CodigosMensajes.ACCION_VACIA + " - " + Mensajes.ACCION_VACIA;
		} else if (!validaciones.comprobarNombreAccionBlank(accion.getNombreAccion())) {
			resultado = CodigosMensajes.ACCION_NAME_VACIO + " - " + Mensajes.ACCION_NAME_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripcionAccionBlank(accion.getDescripAccion())) {
			resultado = CodigosMensajes.ACCION_DESCRIPTION_VACIO + " - "
					+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_VACIO;
		} else {
			accionRepository.saveAndFlush(accion);

			final AccionEntity accionBuscar = accionRepository.findAccionByName(accion.getNombreAccion());
			accionBuscar.setNombreAccion("Accion modificada");
			accionRepository.saveAndFlush(accionBuscar);
			resultado = CodigosMensajes.MODIFICAR_ACCION_CORRECTO + " - " + Mensajes.MODIFICAR_ACCION_CORRECTO;
			accionRepository.deleteAccion(accionBuscar.getIdAccion());

		}

		return resultado;
	}

	private String modificarAccionNoExiste(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreAccionBlank(accion.getNombreAccion())
				&& !validaciones.comprobarDescripcionAccionBlank(accion.getDescripAccion())) {
			resultado = CodigosMensajes.ACCION_VACIA + " - " + Mensajes.ACCION_VACIA;
		} else if (!validaciones.comprobarNombreAccionBlank(accion.getNombreAccion())) {
			resultado = CodigosMensajes.ACCION_NAME_VACIO + " - " + Mensajes.ACCION_NAME_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripcionAccionBlank(accion.getDescripAccion())) {
			resultado = CodigosMensajes.ACCION_DESCRIPTION_VACIO + " - "
					+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_VACIO;
		} else {
			final AccionEntity accionBuscar = accionRepository.findAccionByName(accion.getNombreAccion());

			if (accionBuscar == null) {
				resultado = CodigosMensajes.ACCION_NO_EXISTE + " - " + Mensajes.ACCION_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String reactivarAccion(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		accionRepository.saveAndFlush(accion);

		final AccionEntity accionBuscar = accionRepository.findAccionByName(accion.getNombreAccion());
		accionBuscar.setBorradoAccion(0);
		accionRepository.saveAndFlush(accionBuscar);
		resultado = CodigosMensajes.REACTIVAR_ACCION_CORRECTO + " - " + Mensajes.ACCION_REACTIVADA_CORRECTAMENTE;
		accionRepository.deleteAccion(accionBuscar.getIdAccion());

		return resultado;
	}

	private String reactivarAccionNoExiste(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		final AccionEntity accionBuscar = accionRepository.findAccionByName(accion.getNombreAccion());

		if (accionBuscar == null) {
			resultado = CodigosMensajes.ACCION_NO_EXISTE + " - " + Mensajes.ACCION_NO_EXISTE;
		}

		return resultado;
	}

	private String eliminarAccion(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		accionRepository.saveAndFlush(accion);

		final AccionEntity accionBuscar = accionRepository.findAccionByName(accion.getNombreAccion());
		accionBuscar.setBorradoAccion(1);
		accionRepository.saveAndFlush(accionBuscar);
		resultado = CodigosMensajes.ELIMINAR_ACCION_CORRECTO + " - " + Mensajes.ELIMINAR_ACCION_CORRECTO;
		accionRepository.deleteAccion(accionBuscar.getIdAccion());

		return resultado;
	}

	private String eliminarAccionAsociadaRolFuncionalidad(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		accionRepository.saveAndFlush(accion);

		final AccionEntity accionBuscar = accionRepository.findAccionByName(accion.getNombreAccion());

		final RolAccionFuncionalidadEntity rolAccionFuncionalidad = new RolAccionFuncionalidadEntity(
				accionBuscar.getIdAccion(), 2, 2);
		rolAccionFuncionalidadRepository.saveAndFlush(rolAccionFuncionalidad);

		if (accionBuscar != null) {
			final List<RolAccionFuncionalidadEntity> rolAccionFuncionalidadBD = rolAccionFuncionalidadRepository
					.findAll();

			for (int i = 0; i < rolAccionFuncionalidadBD.size(); i++) {
				if (rolAccionFuncionalidadBD.get(i).getIdAccion().equals(accionBuscar.getIdAccion())) {
					resultado = CodigosMensajes.ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD + " - "
							+ Mensajes.ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD;
				}
			}
		}

		rolAccionFuncionalidadRepository.deleteRolAccionFuncionalidad(accionBuscar.getIdAccion(), 2, 2);
		accionRepository.deleteAccion(accionBuscar.getIdAccion());
		return resultado;
	}

	private String eliminarAccionNoExiste(final AccionEntity accion) {
		String resultado = StringUtils.EMPTY;

		final AccionEntity accionBuscar = accionRepository.findAccionByName(accion.getNombreAccion());

		if (accionBuscar == null) {
			resultado = CodigosMensajes.ACCION_NO_EXISTE + " - " + Mensajes.ACCION_NO_EXISTE;
		}
		return resultado;
	}

	private Map<String, String> getValorAccion(final AccionEntity accion) {

		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.ACCION_NAME, accion.getNombreAccion());
		valor.put(Constantes.ACCION_DESCRIPTION, accion.getDescripAccion());

		return valor;
	}
}
