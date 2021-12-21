package com.sds.service.test.impl.atributos;

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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfabetico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosNumerico;

public class TestAtributoRolName {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosNumerico validacionesAtributosNumerico;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoRolName() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosNumerico = new ValidacionesAtributosNumerico();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRolNameVacio(final RolEntity datosEntradaRolNameVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRolNameVacio.getRolName(), Funcionalidad.GESTION_ROLES, Atributo.ROL_NAME);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaRolNameVacio.getRolName(), Constantes.ROL_NAME);
	}

	public DatosPruebaAtributos getTestRolNameAlfabeticoCaracteresEspeciales(
			final RolEntity datosEntradaRolNameNumericoCaracteresEspeciales) {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaRolNameNumericoCaracteresEspeciales.getRolName(),
						Funcionalidad.GESTION_ROLES, Atributo.ROL_NAME);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ROL_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRolNameNumericoCaracteresEspeciales.getRolName(), Constantes.ROL_NAME);
	}

	public DatosPruebaAtributos getTestRolNameAlfabeticoEspacios(
			final RolEntity datosEntradaRolNameAlfabeticoEspacios) {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRolNameAlfabeticoEspacios.getRolName(), Funcionalidad.GESTION_ROLES, Atributo.ROL_NAME);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ROL_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRolNameAlfabeticoEspacios.getRolName(), Constantes.ROL_NAME);
	}

	public DatosPruebaAtributos getTestRolNameAlfabeticoMenor3(final RolEntity datosEntradaRolNameAlfabeticoMenor3) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRolNameAlfabeticoMenor3.getRolName(), Funcionalidad.GESTION_ROLES, Atributo.ROL_NAME, 3);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_MENOR_QUE_3 + " - "
				+ Mensajes.ROL_NAME_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaRolNameAlfabeticoMenor3.getRolName(), Constantes.ROL_NAME);
	}

	public DatosPruebaAtributos getTestRolNameAlfabeticoMayor32(final RolEntity datosEntradaRolNameAlfabeticoMayor9) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRolNameAlfabeticoMayor9.getRolName(), Funcionalidad.GESTION_ROLES, Atributo.ROL_NAME, 32);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_MAYOR_QUE_32 + " - "
				+ Mensajes.ROL_NAME_NO_PUEDE_SER_MAYOR_QUE_32;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_32, Constantes.ERROR,
				datosEntradaRolNameAlfabeticoMayor9.getRolName(), Constantes.ROL_NAME);
	}

	public DatosPruebaAtributos getTestRolNameNumerico(final RolEntity datosEntradaRolNameNumerico) {

		final String resultadoObtenido = validacionesAtributosNumerico.comprobarAtributoNumerico(
				datosEntradaRolNameNumerico.getRolName(), Funcionalidad.GESTION_ROLES, Atributo.ROL_NAME);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ROL_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO, Constantes.ERROR, datosEntradaRolNameNumerico.getRolName(),
				Constantes.ROL_NAME);
	}

	public DatosPruebaAtributos getTestRolNameCorrectoAlfabetico(final RolEntity datosEntradaRolName) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaRolName.getRolName());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO, datosEntradaRolName.getRolName(),
				Constantes.ROL_NAME);

	}

}
