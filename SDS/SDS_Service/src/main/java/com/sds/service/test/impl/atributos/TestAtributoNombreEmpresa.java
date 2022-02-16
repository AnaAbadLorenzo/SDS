package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.EmpresaEntity;
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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosNumerico;

public class TestAtributoNombreEmpresa {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosNumerico validacionesAtributosNumerico;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoNombreEmpresa() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosNumerico = new ValidacionesAtributosNumerico();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestNombreEmpresaVacio(final EmpresaEntity datosEntradaNombreEmpresaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaNombreEmpresaVacio.getNombreEmpresa(), Funcionalidad.GESTION_EMPRESAS, Atributo.NOMBRE);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaNombreEmpresaVacio.getNombreEmpresa(),
				Constantes.NOMBRE_EMPRESA);
	}

	public DatosPruebaAtributos getTestNombreEmpresaAlfanumericoCaracteresEspeciales(
			final EmpresaEntity datosEntradaNombreEmpresaAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaNombreEmpresaAlfanumericoCaracteresEspeciales.getNombreEmpresa(),
						Funcionalidad.GESTION_EMPRESAS, Atributo.NOMBRE);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaNombreEmpresaAlfanumericoCaracteresEspeciales.getNombreEmpresa(),
				Constantes.NOMBRE_EMPRESA);
	}

	public DatosPruebaAtributos getTestNombreEmpresaAlfanumericoMenor3(
			final EmpresaEntity datosEntradaNombreEmpresaAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaNombreEmpresaAlfanumericoMenor3.getNombreEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.NOMBRE, 3);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_MENOR_QUE_3 + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaNombreEmpresaAlfanumericoMenor3.getNombreEmpresa(), Constantes.NOMBRE_EMPRESA);
	}

	public DatosPruebaAtributos getTestNombreEmpresaAlfanumericoMayor56(
			final EmpresaEntity datosEntradaNombreEmpresaAlfanumericoMayor56) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaNombreEmpresaAlfanumericoMayor56.getNombreEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.NOMBRE, 56);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_MAYOR_QUE_56 + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_SER_MAYOR_QUE_56;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_56, Constantes.ERROR,
				datosEntradaNombreEmpresaAlfanumericoMayor56.getNombreEmpresa(), Constantes.NOMBRE_EMPRESA);
	}

	public DatosPruebaAtributos getTestNombreEmpresaNumerico(final EmpresaEntity datosEntradaNombreEmpresaNumerico) {

		final String resultadoObtenido = validacionesAtributosNumerico.comprobarAtributoNumerico(
				datosEntradaNombreEmpresaNumerico.getNombreEmpresa(), Funcionalidad.GESTION_EMPRESAS, Atributo.NOMBRE);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
				+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO, Constantes.ERROR, datosEntradaNombreEmpresaNumerico.getNombreEmpresa(),
				Constantes.NOMBRE_EMPRESA);
	}

	public DatosPruebaAtributos getTestNombreEmpresaCorrectoAlfabetico(final EmpresaEntity datosEntradaNombreEmpresa) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaNombreEmpresa.getNombreEmpresa());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NOMBRE_ALFABETICO_CORRECTO, Constantes.EXITO,
				datosEntradaNombreEmpresa.getNombreEmpresa(), Constantes.NOMBRE_EMPRESA);

	}
}
