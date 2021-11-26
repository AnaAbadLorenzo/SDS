package com.sds.service.test.impl.atributos;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.registro.model.Registro;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAtributos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoNombrePersonaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoNombrePersonaRegistro() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRegistroNombrePersonaVacio(final Registro datosEntradaRegistroNombrePersonaVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroNombrePersonaVacio.getDatosPersona().getNombreP(), Funcionalidad.REGISTRAR, Atributo.NOMBRE);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_VACIO + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaRegistroNombrePersonaVacio.getDatosPersona().getNombreP(),
				Constantes.NOMBREP);
	}



	public DatosPruebaAtributos getTestRegistroNombrePAlfanumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroNombrePAlfanumericoCaracteresEspeciales) {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaRegistroNombrePAlfanumericoCaracteresEspeciales.getDatosPersona().getNombreP(),
						Funcionalidad.REGISTRAR, Atributo.NOMBRE);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroNombrePAlfanumericoCaracteresEspeciales.getDatosPersona().getNombreP(),
				Constantes.NOMBREP);
	}


	public DatosPruebaAtributos getTestRegistroNombrePAlfanumericoMenor3(
			final Registro datosEntradaRegistroNombrePersonaAlfanumericoMenor3) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroNombrePersonaAlfanumericoMenor3.getDatosPersona().getNombreP(), Funcionalidad.REGISTRAR,
				Atributo.NOMBRE, 3);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_MENOR_QUE_3 + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaRegistroNombrePersonaAlfanumericoMenor3.getDatosPersona().getNombreP(), Constantes.NOMBREP);
	}

	public DatosPruebaAtributos getTestRegistroNombrePAlfanumericoMayor56(
			final Registro datosEntradaRegistroNombrePAlfanumericoMayor56) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroNombrePAlfanumericoMayor56.getDatosPersona().getNombreP(), Funcionalidad.REGISTRAR,
				Atributo.NOMBRE, 56);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_MAYOR_QUE_56 + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_SER_MAYOR_QUE_56;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_56, Constantes.ERROR,
				datosEntradaRegistroNombrePAlfanumericoMayor56.getDatosPersona().getNombreP(), Constantes.NOMBREP);
	}

	public DatosPruebaAtributos getTestRegistroNombrePCorrectoAlfanumerico(final Registro datosEntradaRegistroNombreP) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaRegistroNombreP.getDatosPersona().getNombreP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroNombreP.getDatosPersona().getNombreP(), Constantes.NOMBREP);

	}
}
