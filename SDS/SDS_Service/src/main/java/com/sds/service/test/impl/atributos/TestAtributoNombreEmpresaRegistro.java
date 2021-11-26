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

public class TestAtributoNombreEmpresaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoNombreEmpresaRegistro() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRegistroNombreEmpresaVacio(final Registro datosEntradaRegistroNombreEmpresaVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroNombreEmpresaVacio.getDatosEmpresa().getNombreEmpresa(), Funcionalidad.REGISTRAR, Atributo.NOMBRE);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_VACIO + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaRegistroNombreEmpresaVacio.getDatosEmpresa().getNombreEmpresa(),
				Constantes.NOMBRE_EMPRESA);
	}



	public DatosPruebaAtributos getTestRegistroNombreEmpresaAlfanumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroNombreEmpresaAlfanumericoCaracteresEspeciales) {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaRegistroNombreEmpresaAlfanumericoCaracteresEspeciales.getDatosEmpresa().getNombreEmpresa(),
						Funcionalidad.REGISTRAR, Atributo.NOMBRE);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroNombreEmpresaAlfanumericoCaracteresEspeciales.getDatosEmpresa().getNombreEmpresa(),
				Constantes.NOMBRE_EMPRESA);
	}


	public DatosPruebaAtributos getTestRegistroNombreEmpresaAlfanumericoMenor3(
			final Registro datosEntradaRegistroNombreEmpresaAlfanumericoMenor3) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroNombreEmpresaAlfanumericoMenor3.getDatosEmpresa().getNombreEmpresa(), Funcionalidad.REGISTRAR,
				Atributo.NOMBRE, 3);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_MENOR_QUE_3 + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaRegistroNombreEmpresaAlfanumericoMenor3.getDatosEmpresa().getNombreEmpresa(), Constantes.NOMBRE_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroNombreEmpresaAlfanumericoMayor56(
			final Registro datosEntradaRegistroNombreEmpresaAlfanumericoMayor56) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroNombreEmpresaAlfanumericoMayor56.getDatosEmpresa().getNombreEmpresa(), Funcionalidad.REGISTRAR,
				Atributo.NOMBRE, 56);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_MAYOR_QUE_56 + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_SER_MAYOR_QUE_56;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_56, Constantes.ERROR,
				datosEntradaRegistroNombreEmpresaAlfanumericoMayor56.getDatosEmpresa().getNombreEmpresa(), Constantes.NOMBRE_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroNombreEmpresaCorrectoAlfanumerico(final Registro datosEntradaRegistroNombreEmpresa) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaRegistroNombreEmpresa.getDatosEmpresa().getNombreEmpresa());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroNombreEmpresa.getDatosEmpresa().getNombreEmpresa(), Constantes.NOMBRE_EMPRESA);

	}
}
