package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.RolEntity;
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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosNumerico;

public class TestAtributoRolDescription {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosNumerico validacionesAtributosNumerico;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoRolDescription() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosNumerico = new ValidacionesAtributosNumerico();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRolDescriptionVacio(final RolEntity datosEntradaRolDescriptionVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRolDescriptionVacio.getRolDescription(), Funcionalidad.GESTION_ROLES,
				Atributo.ROL_DESCRIPTION);

		final String resultadoEsperado = CodigosMensajes.ROL_DESCRIPTION_VACIO + " - "
				+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaRolDescriptionVacio.getRolDescription(),
				Constantes.ROL_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestRolDescriptionAlfabeticoCaracteresEspeciales(
			final RolEntity datosEntradaRolDescriptionCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaRolDescriptionCaracteresEspeciales.getRolDescription(), Funcionalidad.GESTION_ROLES,
						Atributo.ROL_DESCRIPTION);

		final String resultadoEsperado = CodigosMensajes.ROL_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRolDescriptionCaracteresEspeciales.getRolDescription(), Constantes.ROL_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestRolDescriptionAlfabeticoMenor3(
			final RolEntity datosEntradaRolDescriptionAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRolDescriptionAlfabeticoMenor3.getRolDescription(), Funcionalidad.GESTION_ROLES,
				Atributo.ROL_DESCRIPTION, 3);

		final String resultadoEsperado = CodigosMensajes.ROL_DESCRIPTION_MENOR_QUE_3 + " - "
				+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_9, Constantes.ERROR,
				datosEntradaRolDescriptionAlfabeticoMenor3.getRolDescription(), Constantes.ROL_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestRolDescriptionNumerico(final RolEntity datosEntradaRolDescriptionNumerico) {

		final String resultadoObtenido = validacionesAtributosNumerico.comprobarAtributoNumerico(
				datosEntradaRolDescriptionNumerico.getRolDescription(), Funcionalidad.GESTION_ROLES,
				Atributo.ROL_DESCRIPTION);

		final String resultadoEsperado = CodigosMensajes.ROL_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO, Constantes.ERROR, datosEntradaRolDescriptionNumerico.getRolDescription(),
				Constantes.ROL_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestRolDescriptionAlfabeticoCorrecto(final RolEntity datosEntradaRolDescription) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaRolDescription.getRolDescription());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO, datosEntradaRolDescription.getRolDescription(),
				Constantes.ROL_DESCRIPTION);

	}
}
