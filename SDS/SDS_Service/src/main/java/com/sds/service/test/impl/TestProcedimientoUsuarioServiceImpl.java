package com.sds.service.test.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.sds.model.ObjetivoEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.PlanRepository;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcedimientoUsuarioProcesoRepository;
import com.sds.repository.ProcedimientoUsuarioRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.RespuestaPosibleRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestProcedimientoUsuarioService;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

public class TestProcedimientoUsuarioServiceImpl implements TestProcedimientoUsuarioService {

	@Autowired
	ProcedimientoUsuarioRepository procedimientoUsuarioRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	ProcedimientoUsuarioProcesoRepository procedimientoUsuarioProcesoRepository;

	@Autowired
	RespuestaPosibleRepository respuestaPosibleRepository;

	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	public TestProcedimientoUsuarioServiceImpl() {
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoUsuarioEntity datosEntradaBuscarProcedimientoUsuarioCorrecto = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.BUSCAR_PROCEDIMIENTOUSUARIO);
		final ProcedimientoUsuarioEntity datosEntradaBuscarProcedimientoUsuarioPuntuacionVacia = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaBuscarProcedimientoUsuarioUsuarioVacio = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaBuscarProcedimientoUsuarioProcedimientoVacio = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PROCEDIMIENTO_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaBuscarProcedimientoUsuarioDatosVacios = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.DATOS_PROCEDIMIENTOUSUARIO_VACIO);

		datosPruebaAcciones.add(getTestBuscarProcedimientoUsuario(datosEntradaBuscarProcedimientoUsuarioCorrecto));
		datosPruebaAcciones
				.add(getTestBuscarProcedimientoUsuario(datosEntradaBuscarProcedimientoUsuarioPuntuacionVacia));
		datosPruebaAcciones.add(getTestBuscarProcedimientoUsuario(datosEntradaBuscarProcedimientoUsuarioUsuarioVacio));
		datosPruebaAcciones
				.add(getTestBuscarProcedimientoUsuario(datosEntradaBuscarProcedimientoUsuarioProcedimientoVacio));
		datosPruebaAcciones.add(getTestBuscarProcedimientoUsuario(datosEntradaBuscarProcedimientoUsuarioDatosVacios));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoUsuarioEntity datosEntradaGuardarProcedimientoUsuarioCorrecto = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.GUARDAR_PROCEDIMIENTOUSUARIO);
		final ProcedimientoUsuarioEntity datosEntradaGuardarProcedimientoUsuarioPuntuacionVacia = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaGuardarProcedimientoUsuarioUsuarioVacio = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaGuardarProcedimientoUsuarioProcedimientoVacio = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PROCEDIMIENTO_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaGuardarProcedimientoUsuarioDatosVacios = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.DATOS_PROCEDIMIENTOUSUARIO_VACIO);

		datosPruebaAcciones
				.add(getTestGuardarProcedimientoUsuarioCorrecto(datosEntradaGuardarProcedimientoUsuarioCorrecto));
		datosPruebaAcciones.add(getTestGuardarPuntuacionProcedimientoUsuarioVacia(
				datosEntradaGuardarProcedimientoUsuarioPuntuacionVacia));
		datosPruebaAcciones.add(
				getTestGuardarUsuarioProcedimientoUsuarioVacio(datosEntradaGuardarProcedimientoUsuarioUsuarioVacio));
		datosPruebaAcciones.add(getTestGuardarProcedimientoProcedimientoUsuarioVacio(
				datosEntradaGuardarProcedimientoUsuarioProcedimientoVacio));
		datosPruebaAcciones
				.add(getTestGuardarDatosProcedimientoUsuarioVacios(datosEntradaGuardarProcedimientoUsuarioDatosVacios));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoUsuarioEntity datosEntradaEliminarProcedimientoUsuarioCorrecto = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.ELIMINAR_PROCEDIMIENTOUSUARIO);
		final ProcedimientoUsuarioEntity datosEntradaEliminarProcedimientoUsuarioNoExiste = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PROCEDIMIENTOUSUARIO_NO_EXISTE);
		final ProcedimientoUsuarioEntity datosEntradaEliminarProcedimientoUsuarioAsociadoProceso = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PROCEDIMIENTOUSUARIO_ASOCIADO_PROCESO);

		datosPruebaAcciones
				.add(getTestEliminarProcedimientoUsuarioCorrecto(datosEntradaEliminarProcedimientoUsuarioCorrecto));
		datosPruebaAcciones
				.add(getTestEliminarProcedimientoUsuarioNoExiste(datosEntradaEliminarProcedimientoUsuarioNoExiste));
		datosPruebaAcciones.add(getTestEliminarProcedimientoUsuarioAsociadoProceso(
				datosEntradaEliminarProcedimientoUsuarioAsociadoProceso));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoUsuarioEntity datosEntradaModificarProcedimientoUsuarioCorrecto = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.MODIFICAR_PROCEDIMIENTOUSUARIO);
		final ProcedimientoUsuarioEntity datosEntradaModificarProcedimientoUsuarioNoExiste = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PROCEDIMIENTOUSUARIO_NO_EXISTE);
		final ProcedimientoUsuarioEntity datosEntradaModificarProcedimientoUsuarioPuntuacionVacia = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaModificarProcedimientoUsuarioUsuarioVacio = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaModificarProcedimientoUsuarioProcedimientoVacio = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.PROCEDIMIENTO_PROCEDIMIENTOUSUARIO_VACIO);
		final ProcedimientoUsuarioEntity datosEntradaModificarProcedimientoUsuarioDatosVacios = generarJSON
				.generarProcedimientoUsuario(Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA,
						Constantes.DATOS_PROCEDIMIENTOUSUARIO_VACIO);

		datosPruebaAcciones
				.add(getTestModificarProcedimientoUsuarioCorrecto(datosEntradaModificarProcedimientoUsuarioCorrecto));
		datosPruebaAcciones
				.add(getTestModificarProcedimientoUsuarioNoExiste(datosEntradaModificarProcedimientoUsuarioNoExiste));
		datosPruebaAcciones.add(getTestModificarPuntuacionProcedimientoUsuarioVacia(
				datosEntradaModificarProcedimientoUsuarioPuntuacionVacia));
		datosPruebaAcciones.add(getTestModificarUsuarioProcedimientoUsuarioVacio(
				datosEntradaModificarProcedimientoUsuarioUsuarioVacio));
		datosPruebaAcciones.add(getTestModificarProcedimientoProcedimientoUsuarioVacio(
				datosEntradaModificarProcedimientoUsuarioProcedimientoVacio));
		datosPruebaAcciones.add(
				getTestModificarDatosProcedimientoUsuarioVacios(datosEntradaModificarProcedimientoUsuarioDatosVacios));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarProcedimientoUsuario(
			final ProcedimientoUsuarioEntity datosEntradaAccionBuscarProcedimientoUsuario) {

		final String resultadoObtenido = buscarProcedimientoUsuario(datosEntradaAccionBuscarProcedimientoUsuario);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_PROCEDIMIENTOUSUARIO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTOUSUARIO_BUSCADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO,
				getValorProcedimientoUsuario(datosEntradaAccionBuscarProcedimientoUsuario));

	}

	private DatosPruebaAcciones getTestGuardarProcedimientoUsuarioCorrecto(
			final ProcedimientoUsuarioEntity datosEntradaAccionGuardarProcedimientoUsuario)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimientoUsuario(datosEntradaAccionGuardarProcedimientoUsuario);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_PROCEDIMIENTOUSUARIO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTOUSUARIO_GUARDADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_PROCEDIMIENTOUSUARIO_CORRECTO, Constantes.EXITO,
				getValorProcedimientoUsuario(datosEntradaAccionGuardarProcedimientoUsuario));

	}

	private DatosPruebaAcciones getTestGuardarPuntuacionProcedimientoUsuarioVacia(
			final ProcedimientoUsuarioEntity datosEntradaAccionGuardarPuntuacionProcedimientoUsuarioVacia)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimientoUsuario(
				datosEntradaAccionGuardarPuntuacionProcedimientoUsuarioVacia);

		final String resultadoEsperado = CodigosMensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_VACIA + " - "
				+ Mensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimientoUsuario(datosEntradaAccionGuardarPuntuacionProcedimientoUsuarioVacia));

	}

	private DatosPruebaAcciones getTestGuardarUsuarioProcedimientoUsuarioVacio(
			final ProcedimientoUsuarioEntity datosEntradaAccionGuardarUsuarioProcedimientoUsuarioVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimientoUsuario(
				datosEntradaAccionGuardarUsuarioProcedimientoUsuarioVacio);

		final String resultadoEsperado = CodigosMensajes.USUARIO_PROCEDIMIENTO_USUARIO_VACIO + " - "
				+ Mensajes.USUARIO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimientoUsuario(datosEntradaAccionGuardarUsuarioProcedimientoUsuarioVacio));

	}

	private DatosPruebaAcciones getTestGuardarProcedimientoProcedimientoUsuarioVacio(
			final ProcedimientoUsuarioEntity datosEntradaAccionGuardarProcedimientoProcedimientoUsuarioVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimientoUsuario(
				datosEntradaAccionGuardarProcedimientoProcedimientoUsuarioVacio);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_VACIO + " - "
				+ Mensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimientoUsuario(datosEntradaAccionGuardarProcedimientoProcedimientoUsuarioVacio));

	}

	private DatosPruebaAcciones getTestGuardarDatosProcedimientoUsuarioVacios(
			final ProcedimientoUsuarioEntity datosEntradaAccionGuardarDatosProcedimientoUsuarioVacios)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimientoUsuario(
				datosEntradaAccionGuardarDatosProcedimientoUsuarioVacios);

		final String resultadoEsperado = CodigosMensajes.DATOS_PROCEDIMIENTOUSUARIO_VACIOS + " - "
				+ Mensajes.DATOS_PROCEDIMIENTOUSUARIO_VACIOS;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimientoUsuario(datosEntradaAccionGuardarDatosProcedimientoUsuarioVacios));

	}

	private DatosPruebaAcciones getTestModificarProcedimientoUsuarioCorrecto(
			final ProcedimientoUsuarioEntity datosEntradaAccionModificarProcedimientoUsuario)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimientoUsuario(datosEntradaAccionModificarProcedimientoUsuario);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_PROCEDIMIENTOUSUARIO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTOUSUARIO_MODIFICADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_PROCEDIMIENTOUSUARIO_CORRECTO, Constantes.EXITO,
				getValorProcedimientoUsuario(datosEntradaAccionModificarProcedimientoUsuario));

	}

	private DatosPruebaAcciones getTestModificarProcedimientoUsuarioNoExiste(
			final ProcedimientoUsuarioEntity datosEntradaAccionModificarProcedimientoUsuarioNoExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimientoUsuarioNoExiste(
				datosEntradaAccionModificarProcedimientoUsuarioNoExiste);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTOUSUARIO_NO_EXISTE + " - "
				+ Mensajes.PROCEDIMIENTOUSUARIO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCEDIMIENTOUSUARIO_NO_EXISTE, Constantes.EXITO,
				getValorProcedimientoUsuario(datosEntradaAccionModificarProcedimientoUsuarioNoExiste));

	}

	private DatosPruebaAcciones getTestModificarPuntuacionProcedimientoUsuarioVacia(
			final ProcedimientoUsuarioEntity datosEntradaAccionModificarPuntuacionProcedimientoUsuarioVacia)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimientoUsuario(
				datosEntradaAccionModificarPuntuacionProcedimientoUsuarioVacia);

		final String resultadoEsperado = CodigosMensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_VACIA + " - "
				+ Mensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimientoUsuario(datosEntradaAccionModificarPuntuacionProcedimientoUsuarioVacia));

	}

	private DatosPruebaAcciones getTestModificarUsuarioProcedimientoUsuarioVacio(
			final ProcedimientoUsuarioEntity datosEntradaAccionModificarUsuarioProcedimientoUsuarioVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimientoUsuario(
				datosEntradaAccionModificarUsuarioProcedimientoUsuarioVacio);

		final String resultadoEsperado = CodigosMensajes.USUARIO_PROCEDIMIENTO_USUARIO_VACIO + " - "
				+ Mensajes.USUARIO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimientoUsuario(datosEntradaAccionModificarUsuarioProcedimientoUsuarioVacio));

	}

	private DatosPruebaAcciones getTestModificarProcedimientoProcedimientoUsuarioVacio(
			final ProcedimientoUsuarioEntity datosEntradaAccionModificarProcedimientoProcedimientoUsuarioVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimientoUsuario(
				datosEntradaAccionModificarProcedimientoProcedimientoUsuarioVacio);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_VACIO + " - "
				+ Mensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimientoUsuario(datosEntradaAccionModificarProcedimientoProcedimientoUsuarioVacio));

	}

	private DatosPruebaAcciones getTestModificarDatosProcedimientoUsuarioVacios(
			final ProcedimientoUsuarioEntity datosEntradaAccionModificarDatosProcedimientoUsuarioVacios)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimientoUsuario(
				datosEntradaAccionModificarDatosProcedimientoUsuarioVacios);

		final String resultadoEsperado = CodigosMensajes.DATOS_PROCEDIMIENTOUSUARIO_VACIOS + " - "
				+ Mensajes.DATOS_PROCEDIMIENTOUSUARIO_VACIOS;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimientoUsuario(datosEntradaAccionModificarDatosProcedimientoUsuarioVacios));

	}

	private DatosPruebaAcciones getTestEliminarProcedimientoUsuarioCorrecto(
			final ProcedimientoUsuarioEntity datosEntradaAccionEliminarProcedimientoUsuario)
			throws java.text.ParseException {

		final String resultadoObtenido = eliminarProcedimientoUsuario(datosEntradaAccionEliminarProcedimientoUsuario);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_PROCEDIMIENTOUSUARIO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTOUSUARIO_ELIMINADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_PROCEDIMIENTOUSUARIO_CORRECTO, Constantes.EXITO,
				getValorProcedimientoUsuario(datosEntradaAccionEliminarProcedimientoUsuario));

	}

	private DatosPruebaAcciones getTestEliminarProcedimientoUsuarioNoExiste(
			final ProcedimientoUsuarioEntity datosEntradaAccionEliminarProcedimientoUsuarioNoExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = eliminarProcedimientoUsuarioNoExiste(
				datosEntradaAccionEliminarProcedimientoUsuarioNoExiste);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTOUSUARIO_NO_EXISTE + " - "
				+ Mensajes.PROCEDIMIENTOUSUARIO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCEDIMIENTOUSUARIO_NO_EXISTE, Constantes.EXITO,
				getValorProcedimientoUsuario(datosEntradaAccionEliminarProcedimientoUsuarioNoExiste));

	}

	private DatosPruebaAcciones getTestEliminarProcedimientoUsuarioAsociadoProceso(
			final ProcedimientoUsuarioEntity datosEntradaAccionEliminarProcedimientoUsuarioAsociadoProceso)
			throws java.text.ParseException {

		final String resultadoObtenido = eliminarProcedimientoUsuarioAsociadoProceso(
				datosEntradaAccionEliminarProcedimientoUsuarioAsociadoProceso);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTOUSUARIO_ASOCIADO_PROCESO + " - "
				+ Mensajes.PROCEDIMIENTOUSUARIO_ASOCIADO_PROCESO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCEDIMIENTOUSUARIO_ASOCIADO_PROCESO, Constantes.EXITO,
				getValorProcedimientoUsuario(datosEntradaAccionEliminarProcedimientoUsuarioAsociadoProceso));

	}

	public String buscarProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuarioEntity) {
		String resultado = StringUtils.EMPTY;

		java.sql.Date fechaSql;
		fechaSql = new java.sql.Date(procedimientoUsuarioEntity.getFechaProcedimientoUsuario().getTime());
		final String fecha = fechaSql.toString();

		procedimientoUsuarioRepository.findProcedimientoUsuario(fecha, procedimientoUsuarioEntity.getUsuario(),
				procedimientoUsuarioEntity.getProcedimiento());

		resultado = CodigosMensajes.BUSCAR_PROCEDIMIENTOUSUARIO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTOUSUARIO_BUSCADO_CORRECTAMENTE;

		return resultado;
	}

	private String guardarProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuario)
			throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones
				.comprobarPuntuacionProcedimientoUsuarioBlank(procedimientoUsuario.getPuntuacionProcedimientoUsuario())
				&& !validaciones
						.comprobarUsuarioProcedimientoUsuarioBlank(procedimientoUsuario.getUsuario().getUsuario())
				&& !validaciones.comprobarProcedimientoProcedimientoUsuarioBlank(
						procedimientoUsuario.getProcedimiento().getIdProcedimiento())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarPuntuacionProcedimientoUsuarioBlank(
				procedimientoUsuario.getPuntuacionProcedimientoUsuario())) {
			resultado = CodigosMensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_VACIA + " - "
					+ Mensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIA;
		} else if (!!validaciones
				.comprobarUsuarioProcedimientoUsuarioBlank(procedimientoUsuario.getUsuario().getUsuario())) {
			resultado = CodigosMensajes.USUARIO_PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.USUARIO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarProcedimientoProcedimientoUsuarioBlank(
				procedimientoUsuario.getProcedimiento().getIdProcedimiento())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else {

			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe",
					format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, null);
			final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", 0);
			personaRepository.saveAndFlush(persona);
			usuarioRepository.saveAndFlush(usuario);

			final UsuarioEntity usuarioBD = usuarioRepository.findByUsuario(usuario.getUsuario());

			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);
			final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
					"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
			procedimientoRepository.saveAndFlush(procedimiento);
			final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());

			procedimientoUsuario.setFechaProcedimientoUsuario(new Date());
			procedimientoUsuario.setBorradoProcedimientoUsuario(0);
			procedimientoUsuario.setUsuario(usuarioBD);
			procedimientoUsuario.setProcedimiento(procedimientoEncontrado);

			procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);

			resultado = CodigosMensajes.GUARDAR_PROCEDIMIENTOUSUARIO_CORRECTO + " - "
					+ Mensajes.PROCEDIMIENTOUSUARIO_GUARDADO_CORRECTAMENTE;

			final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBDNuevo = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
							procedimientoUsuario.getProcedimiento());

			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());

		}

		return resultado;

	}

	private String modificarProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuario)
			throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones
				.comprobarPuntuacionProcedimientoUsuarioBlank(procedimientoUsuario.getPuntuacionProcedimientoUsuario())
				&& !validaciones
						.comprobarUsuarioProcedimientoUsuarioBlank(procedimientoUsuario.getUsuario().getUsuario())
				&& !validaciones.comprobarProcedimientoProcedimientoUsuarioBlank(
						procedimientoUsuario.getProcedimiento().getIdProcedimiento())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarPuntuacionProcedimientoUsuarioBlank(
				procedimientoUsuario.getPuntuacionProcedimientoUsuario())) {
			resultado = CodigosMensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_VACIA + " - "
					+ Mensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIA;
		} else if (!!validaciones
				.comprobarUsuarioProcedimientoUsuarioBlank(procedimientoUsuario.getUsuario().getUsuario())) {
			resultado = CodigosMensajes.USUARIO_PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.USUARIO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarProcedimientoProcedimientoUsuarioBlank(
				procedimientoUsuario.getProcedimiento().getIdProcedimiento())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else {
			final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
							procedimientoUsuario.getProcedimiento());

			if (procedimientoUsuarioBD.isEmpty()) {

				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe",
						format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, null);
				final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", 0);
				personaRepository.saveAndFlush(persona);
				usuarioRepository.saveAndFlush(usuario);

				final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
				objetivoRepository.saveAndFlush(objetivo);
				final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
				plan.setObjetivo(objetivo);
				planRepository.saveAndFlush(plan);
				final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
						"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
				procedimientoRepository.saveAndFlush(procedimiento);
				final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
						.findProcedimientoByName(procedimiento.getNombreProcedimiento());

				final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
				final ObjetivoEntity objetivoBDNuevo = objetivoRepository
						.findObjetivoByName(objetivo.getNombreObjetivo());

				procedimientoUsuarioBD.get(0)
						.setPuntuacionProcedimientoUsuario(procedimientoUsuario.getPuntuacionProcedimientoUsuario());
				procedimientoUsuarioBD.get(0)
						.setBorradoProcedimientoUsuario(procedimientoUsuario.getBorradoProcedimientoUsuario());
				procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuarioBD.get(0));

				resultado = CodigosMensajes.MODIFICAR_PROCEDIMIENTOUSUARIO_CORRECTO + " - "
						+ Mensajes.PROCEDIMIENTOUSUARIO_MODIFICADO_CORRECTAMENTE;

				final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBDNuevo = procedimientoUsuarioRepository
						.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
								procedimientoUsuario.getProcedimiento());

				procedimientoUsuarioRepository
						.deleteProcedimientoUsuario(procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario());
				procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
				planRepository.deletePlan(planBDNuevo.getIdPlan());
				objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
				usuarioRepository.deleteUsuario(usuario.getDniUsuario());
				personaRepository.deletePersona(persona.getDniP());
			}

		}

		return resultado;

	}

	private String modificarProcedimientoUsuarioNoExiste(final ProcedimientoUsuarioEntity procedimientoUsuario)
			throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones
				.comprobarPuntuacionProcedimientoUsuarioBlank(procedimientoUsuario.getPuntuacionProcedimientoUsuario())
				&& !validaciones
						.comprobarUsuarioProcedimientoUsuarioBlank(procedimientoUsuario.getUsuario().getUsuario())
				&& !validaciones.comprobarProcedimientoProcedimientoUsuarioBlank(
						procedimientoUsuario.getProcedimiento().getIdProcedimiento())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarPuntuacionProcedimientoUsuarioBlank(
				procedimientoUsuario.getPuntuacionProcedimientoUsuario())) {
			resultado = CodigosMensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_VACIA + " - "
					+ Mensajes.PUNTUACION_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIA;
		} else if (!!validaciones
				.comprobarUsuarioProcedimientoUsuarioBlank(procedimientoUsuario.getUsuario().getUsuario())) {
			resultado = CodigosMensajes.USUARIO_PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.USUARIO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarProcedimientoProcedimientoUsuarioBlank(
				procedimientoUsuario.getProcedimiento().getIdProcedimiento())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_VACIO + " - "
					+ Mensajes.PROCEDIMIENTO_PROCEDIMIENTO_USUARIO_NO_PUEDE_SER_VACIO;
		} else {
			final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
							procedimientoUsuario.getProcedimiento());

			if (procedimientoUsuarioBD.isEmpty()) {
				resultado = CodigosMensajes.PROCEDIMIENTOUSUARIO_NO_EXISTE + " - "
						+ Mensajes.PROCEDIMIENTOUSUARIO_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String eliminarProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuario)
			throws java.text.ParseException {
		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
						procedimientoUsuario.getProcedimiento());
		String resultado = StringUtils.EMPTY;

		if (procedimientoUsuarioBD.isEmpty()) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe",
					format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, null);
			final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", 0);
			personaRepository.saveAndFlush(persona);
			usuarioRepository.saveAndFlush(usuario);

			final UsuarioEntity usuarioBD = usuarioRepository.findByUsuario(usuario.getUsuario());

			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);
			final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
					"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
			procedimientoRepository.saveAndFlush(procedimiento);
			final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());

			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			procedimientoUsuario.setFechaProcedimientoUsuario(new Date());
			procedimientoUsuario.setBorradoProcedimientoUsuario(0);
			procedimientoUsuario.setUsuario(usuarioBD);
			procedimientoUsuario.setProcedimiento(procedimientoEncontrado);
			procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);

			final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBDNuevo = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
							procedimientoUsuario.getProcedimiento());

			procedimientoUsuarioBDNuevo.get(0).setBorradoProcedimientoUsuario(1);
			procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuarioBD.get(0));

			resultado = CodigosMensajes.ELIMINAR_PROCEDIMIENTOUSUARIO_CORRECTO + " - "
					+ Mensajes.PROCEDIMIENTOUSUARIO_ELIMINADO_CORRECTAMENTE;

			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());

		}

		return resultado;
	}

	private String eliminarProcedimientoUsuarioNoExiste(final ProcedimientoUsuarioEntity procedimientoUsuario) {
		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
						procedimientoUsuario.getProcedimiento());
		String resultado = StringUtils.EMPTY;

		if (procedimientoUsuarioBD.isEmpty()) {
			resultado = CodigosMensajes.PROCEDIMIENTOUSUARIO_NO_EXISTE + " - "
					+ Mensajes.PROCEDIMIENTOUSUARIO_NO_EXISTE;
		}
		return resultado;
	}

	private String eliminarProcedimientoUsuarioAsociadoProceso(final ProcedimientoUsuarioEntity procedimientoUsuario)
			throws java.text.ParseException {
		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
						procedimientoUsuario.getProcedimiento());
		String resultado = StringUtils.EMPTY;

		if (procedimientoUsuarioBD.isEmpty()) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe",
					format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, null);
			final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", 0);
			personaRepository.saveAndFlush(persona);
			usuarioRepository.saveAndFlush(usuario);

			final UsuarioEntity usuarioBD = usuarioRepository.findByUsuario(usuario.getUsuario());

			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);
			final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
					"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
			procedimientoRepository.saveAndFlush(procedimiento);
			final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());
			final ProcesoEntity proceso = new ProcesoEntity("Nombre proceso", "Descrip proceso", new Date(), 0);
			procesoRepository.saveAndFlush(proceso);
			final ProcesoEntity procesoEncontrado = procesoRepository.findProcesoByName(proceso.getNombreProceso());
			final RespuestaPosibleEntity respuestaPosible = new RespuestaPosibleEntity("Texto", 0);
			respuestaPosibleRepository.saveAndFlush(respuestaPosible);
			final RespuestaPosibleEntity respuestaPosibleEncontrada = respuestaPosibleRepository
					.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			procedimientoUsuario.setFechaProcedimientoUsuario(new Date());
			procedimientoUsuario.setBorradoProcedimientoUsuario(0);
			procedimientoUsuario.setUsuario(usuarioBD);
			procedimientoUsuario.setProcedimiento(procedimientoEncontrado);
			procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);

			final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBDNuevo = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoUsuario.getUsuario(),
							procedimientoUsuario.getProcedimiento());

			final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso = new ProcedimientoUsuarioProcesoEntity(
					new Date(), 0, respuestaPosibleEncontrada, procesoEncontrado, procedimientoUsuarioBDNuevo.get(0));
			procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProceso);

			final List<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
					.findProcedimientoUsuarioProcesoByIdProcedimientoUsuarioAndIdProceso(
							procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario(),
							procesoEncontrado.getIdProceso());

			resultado = CodigosMensajes.PROCEDIMIENTOUSUARIO_ASOCIADO_PROCESO + " - "
					+ Mensajes.PROCEDIMIENTOUSUARIO_ASOCIADO_PROCESO;

			procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
					procedimientoUsuarioProcesoBD.get(0).getIdProcedimientoUsuarioProceso());
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario());
			procesoRepository.deleteProceso(procesoEncontrado.getIdProceso());
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleEncontrada.getIdRespuesta());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());

		}

		return resultado;
	}

	private Map<String, String> getValorProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuario) {
		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO,
				procedimientoUsuario.getPuntuacionProcedimientoUsuario().toString());
		valor.put(Constantes.USUARIO, procedimientoUsuario.getUsuario().getUsuario());
		valor.put(Constantes.PROCEDIMIENTO, procedimientoUsuario.getProcedimiento().getNombreProcedimiento());

		return valor;

	}

}
