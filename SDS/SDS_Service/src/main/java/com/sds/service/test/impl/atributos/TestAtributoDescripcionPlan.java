package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.PlanEntity;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAtributos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfabetico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoDescripcionPlan {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDescripcionPlan() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestDescripcionPlanVacio(final PlanEntity datosEntradaDescripcionPlanVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaDescripcionPlanVacio.getDescripPlan(), Funcionalidad.GESTION_PLANES, Atributo.DESCRIP_PLAN);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PLAN_VACIO + " - "
				+ Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaDescripcionPlanVacio.getDescripPlan(),
				Constantes.DESCRIPCION_PLAN);
	}

	public DatosPruebaAtributos getTestDescripcionPlanAlfabeticoCaracteresEspeciales(
			final PlanEntity datosEntradaDescripcionPlanCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaDescripcionPlanCaracteresEspeciales.getDescripPlan(),
						Funcionalidad.GESTION_PLANES, Atributo.DESCRIP_PLAN);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PLAN_ALBAFETICO_INCORRECTO + " - "
				+ Mensajes.DESCRIPCION_PLAN_PUEDE_CONTENER_LETRAS_Y_ESPACIOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaDescripcionPlanCaracteresEspeciales.getDescripPlan(), Constantes.DESCRIPCION_PLAN);
	}

	public DatosPruebaAtributos getTestDescripcionPlanAlfabeticoMenor3(
			final PlanEntity datosEntradaDescripcionPlanAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaDescripcionPlanAlfabeticoMenor3.getDescripPlan(), Funcionalidad.GESTION_PLANES,
				Atributo.DESCRIP_PLAN, 3);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PLAN_MENOR_QUE_3 + " - "
				+ Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaDescripcionPlanAlfabeticoMenor3.getDescripPlan(), Constantes.DESCRIPCION_PLAN);
	}

	public DatosPruebaAtributos getTestDescripcionPlanAlfabeticoCorrecto(final PlanEntity datosEntradaDescripcionPlan) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaDescripcionPlan.getDescripPlan());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO, datosEntradaDescripcionPlan.getDescripPlan(),
				Constantes.DESCRIPCION_PLAN);

	}
}
