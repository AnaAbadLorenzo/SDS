package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.ProcesoEntity;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAtributos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspecialesSinSignosPuntuacion;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosTextoSignosPuntuacion;

public class TestAtributoDescripcionProceso {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspecialesSinSignosPuntuacion validacionesAtributosCaracteresEspecialesSinSignosPuntuacion;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosTextoSignosPuntuacion validacionesAtributosTextoSignosPuntuacion;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDescripcionProceso() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspecialesSinSignosPuntuacion = new ValidacionesAtributosCaracteresEspecialesSinSignosPuntuacion();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosTextoSignosPuntuacion = new ValidacionesAtributosTextoSignosPuntuacion();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestDescripcionProcesoVacio(final ProcesoEntity datosEntradaDescripcionProcesoVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaDescripcionProcesoVacio.getDescripProceso(), Funcionalidad.GESTION_PROCESOS,
				Atributo.DESCRIP_PROCESO);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCESO_VACIO + " - "
				+ Mensajes.DESCRIPCION_PROCESO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaDescripcionProcesoVacio.getDescripProceso(),
				Constantes.DESCRIP_PROCESO);
	}

	public DatosPruebaAtributos getTestDescripcionProcesoAlfanumericoCaracteresEspeciales(
			final ProcesoEntity datosEntradaDescripcionProcesoAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspecialesSinSignosPuntuacion
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaDescripcionProcesoAlfanumericoCaracteresEspeciales.getDescripProceso(),
						Funcionalidad.GESTION_PROCESOS, Atributo.DESCRIP_PROCESO);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCESO_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DESCRIPCION_PROCESO_PUEDE_CONTENER_LETRAS_NUMEROS_SIGNOS_PUNTUACION_Y_ESPACIOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaDescripcionProcesoAlfanumericoCaracteresEspeciales.getDescripProceso(),
				Constantes.DESCRIP_PROCESO);
	}

	public DatosPruebaAtributos getTestDescripcionProcesoAlfanumericoMenor3(
			final ProcesoEntity datosEntradaDescripcionProcesoAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaDescripcionProcesoAlfanumericoMenor3.getDescripProceso(), Funcionalidad.GESTION_PROCESOS,
				Atributo.DESCRIP_PROCESO, 3);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCESO_MENOR_QUE_3 + " - "
				+ Mensajes.DESCRIPCION_PROCESO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaDescripcionProcesoAlfanumericoMenor3.getDescripProceso(), Constantes.DESCRIP_PROCESO);
	}

	public DatosPruebaAtributos getTestDescripcionProcesoAlfanumericoCorrecto(
			final ProcesoEntity datosEntradaDescripcionProceso) {

		final String resultadoObtenido = validacionesAtributosTextoSignosPuntuacion
				.comprobarTextoSignosPuntuacion(datosEntradaDescripcionProceso.getDescripProceso());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_SIGNOS_PUNTUACION, Constantes.EXITO,
				datosEntradaDescripcionProceso.getDescripProceso(), Constantes.DESCRIP_PROCESO);

	}
}
