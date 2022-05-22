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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoNombrePlan {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoNombrePlan() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestNombrePlanVacio(final PlanEntity datosEntradaNombrePlanVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaNombrePlanVacio.getNombrePlan(), Funcionalidad.GESTION_PLANES, Atributo.NOMBRE_PLAN);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PLAN_VACIO + " - "
				+ Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaNombrePlanVacio.getNombrePlan(),
				Constantes.NOMBRE_PLAN);
	}

	public DatosPruebaAtributos getTestNombrePlanAlfabeticoCaracteresEspeciales(
			final PlanEntity datosEntradaNombrePlanAlfabeticoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaNombrePlanAlfabeticoCaracteresEspeciales.getNombrePlan(),
						Funcionalidad.GESTION_PLANES, Atributo.NOMBRE_PLAN);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PLAN_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.NOMBRE_PLAN_SOLO_PUEDE_CONTENER_LETRAS_Y_ESPACIOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaNombrePlanAlfabeticoCaracteresEspeciales.getNombrePlan(), Constantes.NOMBRE_PLAN);
	}

	public DatosPruebaAtributos getTestNombrePlanAlfabeticoMenor3(
			final PlanEntity datosEntradaNombrePlanAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaNombrePlanAlfabeticoMenor3.getNombrePlan(), Funcionalidad.GESTION_PLANES,
				Atributo.NOMBRE_PLAN, 3);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PLAN_MENOR_QUE_3 + " - "
				+ Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaNombrePlanAlfabeticoMenor3.getNombrePlan(), Constantes.NOMBRE_PLAN);
	}

	public DatosPruebaAtributos getTestNombrePlanAlfabeticoMayor48(
			final PlanEntity datosEntradaNombrePlanAlfabeticoMayor48) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaNombrePlanAlfabeticoMayor48.getNombrePlan(), Funcionalidad.GESTION_PLANES,
				Atributo.NOMBRE_PLAN, 48);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PLAN_MAYOR_QUE_48 + " - "
				+ Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_MAYOR_QUE_48;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_48, Constantes.ERROR,
				datosEntradaNombrePlanAlfabeticoMayor48.getNombrePlan(), Constantes.NOMBRE_PLAN);
	}

	public DatosPruebaAtributos getTestNombrePlanCorrectoAlfabetico(final PlanEntity datosEntradaPlan) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaPlan.getNombrePlan());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO, datosEntradaPlan.getNombrePlan(),
				Constantes.NOMBRE_PLAN);

	}
}
