package com.sds.service.test.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestRolService;
import com.sds.service.test.impl.atributos.TestAtributoRolDescription;
import com.sds.service.test.impl.atributos.TestAtributoRolName;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestRolServiceImpl implements TestRolService {

	private final TestAtributoRolName testAtributoRolName;
	private final TestAtributoRolDescription testAtributoRolDescription;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public TestRolServiceImpl() {
		testAtributoRolName = new TestAtributoRolName();
		testAtributoRolDescription = new TestAtributoRolDescription();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoRolName()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final RolEntity datosEntradaRolNameVacio = generarJSON.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME,
				Constantes.ROL_NAME_VACIO_DATA);
		final RolEntity datosEntradaRolNameCaracteresEspeciales = generarJSON.generarRol(
				Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME, Constantes.ROLNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final RolEntity datosEntradaRolNameEspacios = generarJSON.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME,
				Constantes.ROLNAME_ALFABETICO_ESPACIOS_DATA);
		final RolEntity datosEntradaRolNameAlfabeticoMenor3 = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME, Constantes.ROLNAME_ALFABETICO_MENOR_3_DATA);
		final RolEntity datosEntradaRolNameAlfabeticoMayor32 = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME, Constantes.ROLNAME_ALFABETICO_MAYOR_32_DATA);
		final RolEntity datosEntradaRolNameNumerico = generarJSON.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME,
				Constantes.ROLNAME_NUMERICO_DATA);
		final RolEntity datosEntradaRolNameAlfabetico = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLNAME, Constantes.ROLNAME_ALFABETICO_DATA);

		datosPruebaAtributos.add(testAtributoRolName.getTestRolNameVacio(datosEntradaRolNameVacio));
		datosPruebaAtributos.add(testAtributoRolName
				.getTestRolNameAlfabeticoCaracteresEspeciales(datosEntradaRolNameCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoRolName.getTestRolNameAlfabeticoEspacios(datosEntradaRolNameEspacios));
		datosPruebaAtributos
				.add(testAtributoRolName.getTestRolNameAlfabeticoMenor3(datosEntradaRolNameAlfabeticoMenor3));
		datosPruebaAtributos
				.add(testAtributoRolName.getTestRolNameAlfabeticoMayor32(datosEntradaRolNameAlfabeticoMayor32));
		datosPruebaAtributos.add(testAtributoRolName.getTestRolNameNumerico(datosEntradaRolNameNumerico));
		datosPruebaAtributos.add(testAtributoRolName.getTestRolNameCorrectoAlfabetico(datosEntradaRolNameAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoRolDescription()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final RolEntity datosEntradaRolDescriptionVacio = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION, Constantes.ROL_DESCRIPTION_VACIO_DATA);
		final RolEntity datosEntradaRolDescriptionCaracteresEspeciales = generarJSON.generarRol(
				Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION,
				Constantes.ROLDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA);
		final RolEntity datosEntradaRolDescriptionAlfabeticoMenor3 = generarJSON.generarRol(
				Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION, Constantes.ROLDESCRIPTION_ALFABETICO_MENOR_3_DATA);
		final RolEntity datosEntradaRolDescriptionAlfabetico = generarJSON.generarRol(
				Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION, Constantes.ROLDESCRIPTION_ALFABETICO_DATA);
		final RolEntity datosEntradaRolDescriptionNumerico = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION, Constantes.ROLDESCRIPTION_NUMERICO_DATA);
		datosPruebaAtributos
				.add(testAtributoRolDescription.getTestRolDescriptionVacio(datosEntradaRolDescriptionVacio));
		datosPruebaAtributos.add(testAtributoRolDescription
				.getTestRolDescriptionAlfabeticoCaracteresEspeciales(datosEntradaRolDescriptionCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoRolDescription
				.getTestRolDescriptionAlfabeticoMenor3(datosEntradaRolDescriptionAlfabeticoMenor3));
		datosPruebaAtributos
				.add(testAtributoRolDescription.getTestRolDescriptionNumerico(datosEntradaRolDescriptionNumerico));
		datosPruebaAtributos.add(testAtributoRolDescription
				.getTestRolDescriptionAlfabeticoCorrecto(datosEntradaRolDescriptionAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRolBuscar()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RolEntity datosEntradaRolBuscarRol = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.BUSCAR_ROL);
		final RolEntity datosEntradaRolbuscarRolRolNameVacio = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ROL_NAME_VACIO_DATA);
		final RolEntity datosEntradaRolbuscarRolNoExiste = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ROL_NO_EXISTE);

		datosPruebaAcciones.add(getTestBuscarRol(datosEntradaRolBuscarRol));
		datosPruebaAcciones.add(getTestBuscarRolRolNameVacio(datosEntradaRolbuscarRolRolNameVacio));
		datosPruebaAcciones.add(getTestBuscarRolNoExiste(datosEntradaRolbuscarRolNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRolGuardar()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RolEntity datosEntradaRolGuardarRol = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.GUARDAR_ROL);
		final RolEntity datosEntradaRolGuardarRolRolNameVacio = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ROL_NAME_VACIO_DATA);
		final RolEntity datosEntradaRolGuardarRolRolDescriptionVacio = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_DESCRIPTION_VACIO_DATA);
		final RolEntity datosEntradaRolGuardarRolNameDescriptionVacio = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_DESCRIPTION_VACIOS);
		final RolEntity datosEntradaRolGuardarRolYaExiste = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ROL_YA_EXISTE);

		datosPruebaAcciones.add(getTestGuardarRol(datosEntradaRolGuardarRol));
		datosPruebaAcciones.add(getTestGuardarRolRolNameVacio(datosEntradaRolGuardarRolRolNameVacio));
		datosPruebaAcciones.add(getTestGuardarRolRolDescriptionVacio(datosEntradaRolGuardarRolRolDescriptionVacio));
		datosPruebaAcciones
				.add(getTestGuardarRolRolNameDescriptionVacio(datosEntradaRolGuardarRolNameDescriptionVacio));
		datosPruebaAcciones.add(getTestGuardarRolYaExiste(datosEntradaRolGuardarRolYaExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRolEliminar()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RolEntity datosEntradaRolEliminarRol = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ELIMINAR_ROL);
		final RolEntity datosEntradaRolEliminarRolNoExiste = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ELIMINAR_ROL_NO_EXISTE);
		final RolEntity datosEntradaRolEliminarRolAsociadoUsuario = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ELIMINAR_ROL_ASOCIADO_USUARIO);

		datosPruebaAcciones.add(getTestEliminarRol(datosEntradaRolEliminarRol));
		datosPruebaAcciones.add(getTestEliminarRolAsociadoUsuario(datosEntradaRolEliminarRolAsociadoUsuario));
		datosPruebaAcciones.add(getTestEliminarRolNoExiste(datosEntradaRolEliminarRolNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRolModificar()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RolEntity datosEntradaRolModificarRol = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.MODIFICAR_ROL);
		final RolEntity datosEntradaRolModificarRolNameVacio = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ROL_NAME_VACIO_DATA);
		final RolEntity datosEntradaRolModificarRolDescriptionVacio = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_DESCRIPTION_VACIO_DATA);
		final RolEntity datosEntradaRolModificarRolNameDescriptionVacio = generarJSON
				.generarRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_DESCRIPTION_VACIOS);
		final RolEntity datosEntradaRolModificarRolNoExiste = generarJSON.generarRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ROL_NO_EXISTE);

		datosPruebaAcciones.add(getTestModificarRol(datosEntradaRolModificarRol));
		datosPruebaAcciones.add(getTestModificarRolNameVacio(datosEntradaRolModificarRolNameVacio));
		datosPruebaAcciones.add(getTestModificarRolDescriptionVacio(datosEntradaRolModificarRolDescriptionVacio));
		datosPruebaAcciones
				.add(getTestModificarRolNameDescriptionVacio(datosEntradaRolModificarRolNameDescriptionVacio));
		datosPruebaAcciones.add(getTestModificarRolNoExiste(datosEntradaRolModificarRolNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarRol(final RolEntity datosEntradaRolbuscarRol) {

		final String resultadoObtenido = buscarRol(datosEntradaRolbuscarRol);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_ROL_CORRECTO + " - " + Mensajes.BUSCAR_ROL_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO, getValorRol(datosEntradaRolbuscarRol));

	}

	private DatosPruebaAcciones getTestBuscarRolRolNameVacio(final RolEntity datosEntradaRolbuscarRolRolNameVacio) {

		final String resultadoObtenido = buscarRol(datosEntradaRolbuscarRolRolNameVacio);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_ROL_NAME_VACIO, Constantes.ERROR,
				getValorRol(datosEntradaRolbuscarRolRolNameVacio));

	}

	private DatosPruebaAcciones getTestBuscarRolNoExiste(final RolEntity datosEntradaRolbuscarRolNoExiste) {

		final String resultadoObtenido = buscarRol(datosEntradaRolbuscarRolNoExiste);

		final String resultadoEsperado = CodigosMensajes.ROL_NO_EXISTE + " - " + Mensajes.ROL_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_ROL_NO_EXISTE, Constantes.ERROR,
				getValorRol(datosEntradaRolbuscarRolNoExiste));

	}

	private DatosPruebaAcciones getTestGuardarRol(final RolEntity datosEntradaRolGuardarRol) {

		final String resultadoObtenido = guardarRol(datosEntradaRolGuardarRol);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_ROL_CORRRECTO + " - " + Mensajes.GUARDAR_ROL_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_ROL_CORRECTO, Constantes.EXITO, getValorRol(datosEntradaRolGuardarRol));

	}

	private DatosPruebaAcciones getTestGuardarRolRolNameVacio(final RolEntity datosEntradaRolGuardarRolRolNameVacio) {

		final String resultadoObtenido = guardarRol(datosEntradaRolGuardarRolRolNameVacio);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ROL_NAME_VACIO, Constantes.ERROR, getValorRol(datosEntradaRolGuardarRolRolNameVacio));

	}

	private DatosPruebaAcciones getTestGuardarRolRolDescriptionVacio(
			final RolEntity datosEntradaRolGuardarRolRolDescriptionVacio) {

		final String resultadoObtenido = guardarRol(datosEntradaRolGuardarRolRolDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.ROL_DESCRIPTION_VACIO + " - "
				+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ROL_DESCRIPTION_VACIO, Constantes.ERROR,
				getValorRol(datosEntradaRolGuardarRolRolDescriptionVacio));
	}

	private DatosPruebaAcciones getTestGuardarRolRolNameDescriptionVacio(
			final RolEntity datosEntradaRolGuardarRolNameDescriptionVacio) {

		final String resultadoObtenido = guardarRol(datosEntradaRolGuardarRolNameDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.ROL_VACIO + " - " + Mensajes.ROL_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ROL_VACIO, Constantes.ERROR,
				getValorRol(datosEntradaRolGuardarRolNameDescriptionVacio));
	}

	private DatosPruebaAcciones getTestGuardarRolYaExiste(final RolEntity datosEntradaRolGuardarRolYaExiste) {

		final String resultadoObtenido = guardarRol(datosEntradaRolGuardarRolYaExiste);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_ROL_YA_EXISTE + " - " + Mensajes.GUARDAR_ROL_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ROL_YA_EXISTE, Constantes.ERROR, getValorRol(datosEntradaRolGuardarRolYaExiste));
	}

	private DatosPruebaAcciones getTestEliminarRol(final RolEntity datosEntradaRolEliminarRol) {

		final String resultadoObtenido = eliminarRol(datosEntradaRolEliminarRol);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_ROL_CORRECTO + " - " + Mensajes.ELIMINAR_ROL_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_ROL_CORRECTO, Constantes.EXITO, getValorRol(datosEntradaRolEliminarRol));
	}

	private DatosPruebaAcciones getTestEliminarRolAsociadoUsuario(
			final RolEntity datosEntradaRolEliminarRolAsociadoUsuario) {

		final String resultadoObtenido = eliminarRolAsociadoUsuario(datosEntradaRolEliminarRolAsociadoUsuario);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_ROL_ASOCIADO_USUARIO + " - "
				+ Mensajes.ELIMINAR_ROL_ASOCIADO_USUARIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_ROL_ASOCIADO_USUARIO, Constantes.EXITO,
				getValorRol(datosEntradaRolEliminarRolAsociadoUsuario));
	}

	private DatosPruebaAcciones getTestEliminarRolNoExiste(final RolEntity datosEntradaRolEliminarRolNoExiste) {

		final String resultadoObtenido = eliminarRolNoExiste(datosEntradaRolEliminarRolNoExiste);

		final String resultadoEsperado = CodigosMensajes.ROL_NO_EXISTE + " - " + Mensajes.ROL_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_ROL_NO_EXISTE, Constantes.EXITO,
				getValorRol(datosEntradaRolEliminarRolNoExiste));
	}

	private DatosPruebaAcciones getTestModificarRol(final RolEntity datosEntradaRolModificarRol) {

		final String resultadoObtenido = modificarRol(datosEntradaRolModificarRol);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_ROL + " - " + Mensajes.MODIFICAR_ROL;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_ROL, Constantes.EXITO, getValorRol(datosEntradaRolModificarRol));
	}

	private DatosPruebaAcciones getTestModificarRolNoExiste(final RolEntity datosEntradaRolModificarRolNoExiste) {

		final String resultadoObtenido = modificarRolNoExiste(datosEntradaRolModificarRolNoExiste);

		final String resultadoEsperado = CodigosMensajes.ROL_NO_EXISTE + " - " + Mensajes.ROL_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_ROL_NO_EXISTE, Constantes.ERROR,
				getValorRol(datosEntradaRolModificarRolNoExiste));
	}

	private DatosPruebaAcciones getTestModificarRolNameVacio(final RolEntity datosEntradaRolModificarRolNameVacio) {

		final String resultadoObtenido = modificarRol(datosEntradaRolModificarRolNameVacio);

		final String resultadoEsperado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ROL_NAME_VACIO, Constantes.ERROR, getValorRol(datosEntradaRolModificarRolNameVacio));
	}

	private DatosPruebaAcciones getTestModificarRolDescriptionVacio(
			final RolEntity datosEntradaRolModificarRolDescriptionVacio) {

		final String resultadoObtenido = modificarRol(datosEntradaRolModificarRolDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.ROL_DESCRIPTION_VACIO + " - "
				+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ROL_DESCRIPTION_VACIO, Constantes.ERROR,
				getValorRol(datosEntradaRolModificarRolDescriptionVacio));
	}

	private DatosPruebaAcciones getTestModificarRolNameDescriptionVacio(
			final RolEntity datosEntradaRolModificarRolNameDescriptionVacio) {

		final String resultadoObtenido = modificarRol(datosEntradaRolModificarRolNameDescriptionVacio);

		final String resultadoEsperado = CodigosMensajes.ROL_VACIO + " - " + Mensajes.ROL_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ROL_VACIO, Constantes.ERROR,
				getValorRol(datosEntradaRolModificarRolNameDescriptionVacio));
	}

	private String buscarRol(final RolEntity rol) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreRolBlank(rol.getRolName())) {
			resultado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;
		} else {
			RolEntity rolUser = null;
			rolUser = rolRepository.findByRolName(rol.getRolName());

			if (rolUser == null) {
				resultado = CodigosMensajes.ROL_NO_EXISTE + " - " + Mensajes.ROL_NO_EXISTE;
			} else {
				resultado = CodigosMensajes.BUSCAR_ROL_CORRECTO + " - " + Mensajes.BUSCAR_ROL_CORRECTO;
			}
		}

		return resultado;
	}

	private String guardarRol(final RolEntity rol) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreRolBlank(rol.getRolName())
				&& !validaciones.comprobarDescriptionRolBlank(rol.getRolDescription())) {
			resultado = CodigosMensajes.ROL_VACIO + " - " + Mensajes.ROL_VACIO;
		} else if (!validaciones.comprobarNombreRolBlank(rol.getRolName())) {
			resultado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescriptionRolBlank(rol.getRolDescription())) {
			resultado = CodigosMensajes.ROL_DESCRIPTION_VACIO + " - " + Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_VACIO;

		} else {
			final RolEntity rolUsuario = rolRepository.findByRolName(rol.getRolName());

			if (rolUsuario == null) {
				rolRepository.saveAndFlush(rol);
				resultado = CodigosMensajes.GUARDAR_ROL_CORRRECTO + " - " + Mensajes.GUARDAR_ROL_CORRECTO;

				final RolEntity rolBD = rolRepository.findByRolName(rol.getRolName());
				rolRepository.deleteRol(rolBD.getIdRol());

			} else {
				resultado = CodigosMensajes.GUARDAR_ROL_YA_EXISTE + " - " + Mensajes.GUARDAR_ROL_YA_EXISTE;
			}

		}

		return resultado;
	}

	private String modificarRol(final RolEntity rol) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreRolBlank(rol.getRolName())
				&& !validaciones.comprobarDescriptionRolBlank(rol.getRolDescription())) {
			resultado = CodigosMensajes.ROL_VACIO + " - " + Mensajes.ROL_VACIO;
		} else if (!validaciones.comprobarNombreRolBlank(rol.getRolName())) {
			resultado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescriptionRolBlank(rol.getRolDescription())) {
			resultado = CodigosMensajes.ROL_DESCRIPTION_VACIO + " - " + Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_VACIO;

		} else {
			final Optional<RolEntity> rolUsuario = rolRepository.findById(rol.getIdRol());

			if (!rolUsuario.isPresent()) {
				rolRepository.saveAndFlush(rol);

				rol.setRolName("Modificacion");
				rol.setRolDescription("Descripcion modificada");
				rol.setBorradoRol(0);
				rolRepository.saveAndFlush(rol);
				resultado = CodigosMensajes.MODIFICAR_ROL + " - " + Mensajes.MODIFICAR_ROL;

				final RolEntity rolBD = rolRepository.findByRolName(rol.getRolName());
				rolRepository.deleteRol(rolBD.getIdRol());
			}

		}

		return resultado;
	}

	private String modificarRolNoExiste(final RolEntity rol) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreRolBlank(rol.getRolName())
				&& !validaciones.comprobarDescriptionRolBlank(rol.getRolDescription())) {
			resultado = CodigosMensajes.ROL_VACIO + " - " + Mensajes.ROL_VACIO;
		} else if (!validaciones.comprobarNombreRolBlank(rol.getRolName())) {
			resultado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescriptionRolBlank(rol.getRolDescription())) {
			resultado = CodigosMensajes.ROL_DESCRIPTION_VACIO + " - " + Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_VACIO;

		} else {
			final Optional<RolEntity> rolUsuario = rolRepository.findById(rol.getIdRol());

			if (!rolUsuario.isPresent()) {
				resultado = CodigosMensajes.ROL_NO_EXISTE + " - " + Mensajes.ROL_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String eliminarRol(final RolEntity rol) {
		final RolEntity rolUsuario = rolRepository.findByRolName(rol.getRolName());
		String resultado = StringUtils.EMPTY;

		if (rolUsuario == null) {
			rolRepository.saveAndFlush(rol);
			final RolEntity rolBD = rolRepository.findByRolName(rol.getRolName());

			rolBD.setBorradoRol(1);
			rolRepository.saveAndFlush(rolBD);

			resultado = CodigosMensajes.ELIMINAR_ROL_CORRECTO + " - " + Mensajes.ELIMINAR_ROL_CORRECTO;

			rolRepository.deleteRol(rolBD.getIdRol());
		}

		return resultado;

	}

	private String eliminarRolAsociadoUsuario(final RolEntity rol) {
		final Optional<RolEntity> rolUsuario = rolRepository.findById(rol.getIdRol());
		String resultado = StringUtils.EMPTY;

		if (rolUsuario != null) {
			final List<UsuarioEntity> usuarios = usuarioRepository.findAll();

			for (final UsuarioEntity usuario : usuarios) {
				if (usuario.getRol().getIdRol().equals(rol.getIdRol())) {
					resultado = CodigosMensajes.ELIMINAR_ROL_ASOCIADO_USUARIO + " - "
							+ Mensajes.ELIMINAR_ROL_ASOCIADO_USUARIO;
				}
			}
		}

		return resultado;

	}

	private String eliminarRolNoExiste(final RolEntity rol) {
		final Optional<RolEntity> rolUsuario = rolRepository.findById(rol.getIdRol());
		String resultado = StringUtils.EMPTY;

		if (!rolUsuario.isPresent()) {
			resultado = CodigosMensajes.ROL_NO_EXISTE + " - " + Mensajes.ROL_NO_EXISTE;
		}

		return resultado;
	}

	private Map<String, String> getValorRol(final RolEntity rol) {

		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.ROL_NAME, rol.getRolName());
		valor.put(Constantes.ROL_DESCRIPTION, rol.getRolDescription());

		return valor;
	}

}
