package com.sds.procesoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.model.ObjetivoEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoProcedimientoEntity;
import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.NivelRepository;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.PlanRepository;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcedimientoUsuarioProcesoRepository;
import com.sds.repository.ProcedimientoUsuarioRepository;
import com.sds.repository.ProcesoProcedimientoRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.ProcesoRespuestaPosibleRepository;
import com.sds.repository.RespuestaPosibleRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NivelYaExisteException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.ProcedimientoAsociadoProcesoException;
import com.sds.service.exception.ProcesoAsociadoObjetivoException;
import com.sds.service.exception.ProcesoAsociadoRespuestaPosibleException;
import com.sds.service.exception.ProcesoAsociadoUsuarioProcedimientoException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoYaExisteException;
import com.sds.service.proceso.ProcesoService;
import com.sds.service.proceso.model.Proceso;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcesoServiceTest {

	@Autowired
	ProcesoService procesoService;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ProcedimientoUsuarioProcesoRepository procedimientoUsuarioProcesoRepository;

	@Autowired
	ProcedimientoUsuarioRepository procedimientoUsuarioRepository;

	@Autowired
	ProcesoProcedimientoRepository procesoProcedimientoRepository;

	@Autowired
	RespuestaPosibleRepository respuestaPosibleRepository;

	@Autowired
	ProcesoRespuestaPosibleRepository procesoRespuestaPosibleRepository;

	@Autowired
	NivelRepository nivelRepository;

	@Test
	public void ProcesoService_buscarProceso() throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.BUSCAR_PROCESO);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_buscarProcesoNombreVacio() throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_buscarProcesoDescripVacia()
			throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_buscarProcesoFechaVacia() throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_buscarProcesoDatosVacios() throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_guardarProceso()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoNoExisteException, ObjetivoNoExisteException, NivelYaExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);

		String respuesta = StringUtils.EMPTY;
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		procesoService.deleteProceso(procesoEncontrado.getListaBusquedas().get(0));
	}

	@Test
	public void ProcesoService_guardarProcesoNombreProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcesoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);
	}

	@Test
	public void ProcesoService_guardarProcesoDescripcionProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcesoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_guardarProcesoFechaProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcesoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_guardarProcesoDatosProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcesoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test(expected = ProcesoYaExisteException.class)
	public void ProcesoService_guardarProcesoYaExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ObjetivoNoExisteException, NivelYaExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_YA_EXISTE);

		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		procesoService.anadirProceso(proceso);

		try {
			procesoService.anadirProceso(proceso);
		} catch (final ProcesoYaExisteException procesoYaExisteException) {
			throw new ProcesoYaExisteException(CodeMessageErrors.PROCESO_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PROCESO_YA_EXISTE_EXCEPTION.getCodigo()));
		} finally {

			final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
					proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0,
					1);

			procesoService.deleteProceso(procesoEncontrado.getListaBusquedas().get(0));
		}
	}

	@Test(expected = FechaAnteriorFechaActualException.class)
	public void PlanService_guardarProcesoFechaProcesoAnteriorActual()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoYaExisteException,
			ProcesoNoExisteException, ObjetivoNoExisteException, NivelYaExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		procesoService.anadirProceso(proceso);

	}

	@Test
	public void ProcesoService_modificarProceso() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoYaExisteException, ProcesoNoExisteException, ProcesoAsociadoUsuarioProcedimientoException,
			ObjetivoNoExisteException, NivelYaExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.MODIFICAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		String respuesta = StringUtils.EMPTY;
		procesoService.anadirProceso(proceso);

		final ReturnBusquedas<ProcesoEntity> procesoModificar = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);
		procesoModificar.getListaBusquedas().get(0).setDescripProceso("Descripción proceso");

		proceso.setProceso(procesoModificar.getListaBusquedas().get(0));

		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(Constantes.OK, respuesta);

		procesoService.deleteProceso(procesoModificar.getListaBusquedas().get(0));
	}

	@Test
	public void ProcesoService_modificarProcesoNombreProcesoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoNoExisteException,
			ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException, NivelYaExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);
	}

	@Test
	public void ProcesoService_modificarProcesoDescripcionProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException, NivelYaExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_modificarProcesoFechaProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException, NivelYaExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_modificarProcesoDatosProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException, NivelYaExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test(expected = ProcesoNoExisteException.class)
	public void ProcesoService_modificarProcesoNoExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, ProcesoYaExisteException, FechaAnteriorFechaActualException,
			ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException, NivelYaExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);

		procesoService.modificarProceso(proceso);

	}

	@Test(expected = ProcesoAsociadoUsuarioProcedimientoException.class)
	public void ProcesoService_modificarProcesoAsociadoUsuarioProcedimiento() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, ProcesoYaExisteException, FechaAnteriorFechaActualException,
			ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException, NivelYaExisteException {

		final String resultado = StringUtils.EMPTY;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		plan.setObjetivo(objetivoBDNuevo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
				"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rolEntity = new RolEntity(2, "usuario",
				"Contendrá a todos los usuarios registrados de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rolEntity);
		personaRepository.saveAndFlush(persona);
		usuarioRepository.saveAndFlush(usuario);
		procedimientoRepository.saveAndFlush(procedimiento);

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		objetivosProceso.add(objetivoBDNuevo);
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		nivelesProceso.add(1);
		proceso.setNivel(nivelesProceso);
		procesoService.anadirProceso(proceso);

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimiento.getNombreProcedimiento());
		final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), resultado);

		final ProcedimientoUsuarioEntity procedimientoUsuario = new ProcedimientoUsuarioEntity(0, new Date(), 0,
				procedimiento, usuario);
		procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);

		final RespuestaPosibleEntity respuestaPosible = new RespuestaPosibleEntity("Respuesta posible", 0);
		respuestaPosibleRepository.saveAndFlush(respuestaPosible);
		final RespuestaPosibleEntity respuestaPosibleEncontrada = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioEncontrado = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(usuario, procedimientoEncontrado);
		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso = new ProcedimientoUsuarioProcesoEntity(
				new Date(), 0, respuestaPosibleEncontrada, procesoEncontrado.get(0),
				procedimientoUsuarioEncontrado.get(0));

		procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProceso);

		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
				.findProcedimientoUsuarioProceso(procedimientoUsuarioEncontrado.get(0), procesoEncontrado.get(0));

		try {
			procesoService.modificarProceso(new Proceso(proceso.getUsuario(), procesoEncontrado.get(0)));
		} catch (final ProcesoAsociadoUsuarioProcedimientoException procesoAsociadoUsuarioProcedimientoException) {
			throw new ProcesoAsociadoUsuarioProcedimientoException(
					CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo()));
		} finally {
			procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
					procedimientoUsuarioProcesoBD.getIdProcedimientoUsuarioProceso());
			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioEncontrado.get(0).getIdProcedimientoUsuario());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleEncontrada.getIdRespuesta());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());
		}

	}

	@Test
	public void ProcesoService_eliminarProcesoCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcedimientoAsociadoProcesoException,
			ProcesoAsociadoRespuestaPosibleException, ProcesoAsociadoUsuarioProcedimientoException,
			ProcesoAsociadoObjetivoException, ObjetivoNoExisteException, NivelYaExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.ELIMINAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		proceso.setNivel(nivelesProceso);
		procesoService.anadirProceso(proceso);

		final ReturnBusquedas<ProcesoEntity> procesoEliminar = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		procesoEliminar.getListaBusquedas().get(0).setBorradoProceso(1);
		final String respuesta = procesoService
				.eliminaProceso(new Proceso(proceso.getUsuario(), procesoEliminar.getListaBusquedas().get(0)));

		procesoService.deleteProceso(procesoEliminar.getListaBusquedas().get(0));

		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = ProcesoNoExisteException.class)
	public void ProcesoService_eliminarProcesoNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException,
			ProcedimientoAsociadoProcesoException, ProcesoAsociadoRespuestaPosibleException,
			ProcesoAsociadoObjetivoException, ObjetivoNoExisteException, NivelYaExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);

		procesoService.eliminaProceso(proceso);

	}

	@Test(expected = ProcedimientoAsociadoProcesoException.class)
	public void ProcesoService_eliminarProcesoProcedimientoAsociado()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException,
			ProcedimientoAsociadoProcesoException, ProcesoAsociadoRespuestaPosibleException,
			ProcesoAsociadoObjetivoException, ObjetivoNoExisteException, NivelYaExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA,
				Constantes.PROCESO_ASOCIADO_PROCEDIMIENTO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		procesoService.anadirProceso(proceso);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
				"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
		procedimientoRepository.saveAndFlush(procedimiento);
		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);
		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimiento.getNombreProcedimiento());

		final ProcesoProcedimientoEntity procesoProcedimientoEntity = new ProcesoProcedimientoEntity(
				procesoEncontrado.getListaBusquedas().get(0).getIdProceso(),
				procedimientoEncontrado.getIdProcedimiento(), 3);
		procesoProcedimientoRepository.saveAndFlush(procesoProcedimientoEntity);

		try {
			procesoService
					.eliminaProceso(new Proceso(proceso.getUsuario(), procesoEncontrado.getListaBusquedas().get(0)));
		} catch (final ProcedimientoAsociadoProcesoException procedimientoAsociadoProcesoException) {
			throw new ProcedimientoAsociadoProcesoException(
					CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()));
		} finally {
			procesoProcedimientoRepository.deleteProcesoProcedimiento(
					procesoEncontrado.getListaBusquedas().get(0).getIdProceso(),
					procedimientoEncontrado.getIdProcedimiento());
			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
			procesoRepository.deleteProceso(procesoEncontrado.getListaBusquedas().get(0).getIdProceso());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
		}
	}

	@Test(expected = ProcesoAsociadoRespuestaPosibleException.class)
	public void ProcesoService_eliminarProcesoRespuestaPosibleAsociada()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException,
			ProcedimientoAsociadoProcesoException, ProcesoAsociadoRespuestaPosibleException,
			ProcesoAsociadoObjetivoException, ObjetivoNoExisteException, NivelYaExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA,
				Constantes.PROCESO_ASOCIADO_RESPUESTA_POSIBLE);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		procesoService.anadirProceso(proceso);
		final RespuestaPosibleEntity respuestaPosibleEntity = new RespuestaPosibleEntity("Esta es la respuesta posible",
				0);
		respuestaPosibleRepository.saveAndFlush(respuestaPosibleEntity);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);
		final List<RespuestaPosibleEntity> respuestaPosibleEncontrada = respuestaPosibleRepository
				.findRespuestaPosible(respuestaPosibleEntity.getTextoRespuesta());

		final ProcesoRespuestaPosibleEntity procesoRespuestaPosibleEntity = new ProcesoRespuestaPosibleEntity(
				procesoEncontrado.getListaBusquedas().get(0).getIdProceso(),
				respuestaPosibleEncontrada.get(0).getIdRespuesta(), new Date());
		procesoRespuestaPosibleRepository.saveAndFlush(procesoRespuestaPosibleEntity);

		try {
			procesoService
					.eliminaProceso(new Proceso(proceso.getUsuario(), procesoEncontrado.getListaBusquedas().get(0)));
		} catch (final ProcesoAsociadoRespuestaPosibleException procesoAsociadoRespuestaPosibleException) {
			throw new ProcesoAsociadoRespuestaPosibleException(
					CodeMessageErrors.PROCESO_ASOCIADO_RESPUESTA_POSIBLE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_ASOCIADO_RESPUESTA_POSIBLE_EXCEPTION.getCodigo()));
		} finally {
			procesoRespuestaPosibleRepository.deleteProcesoRespuestaPosible(
					respuestaPosibleEncontrada.get(0).getIdRespuesta(),
					procesoEncontrado.getListaBusquedas().get(0).getIdProceso());
			;
			procesoRepository.deleteProceso(procesoEncontrado.getListaBusquedas().get(0).getIdProceso());
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleEncontrada.get(0).getIdRespuesta());
		}
	}

	@Test(expected = ProcesoAsociadoUsuarioProcedimientoException.class)
	public void ProcesoService_eliminarProcesoProcedimientoUsuarioAsociado()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException,
			ProcedimientoAsociadoProcesoException, ProcesoAsociadoRespuestaPosibleException,
			ProcesoAsociadoObjetivoException, ObjetivoNoExisteException, NivelYaExisteException {

		final String resultado = StringUtils.EMPTY;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
				"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rolEntity = new RolEntity(2, "usuario",
				"Contendrá a todos los usuarios registrados de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rolEntity);
		personaRepository.saveAndFlush(persona);
		usuarioRepository.saveAndFlush(usuario);
		procedimientoRepository.saveAndFlush(procedimiento);

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		procesoService.anadirProceso(proceso);

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimiento.getNombreProcedimiento());
		final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), resultado);
		final RespuestaPosibleEntity respuestaPosible = new RespuestaPosibleEntity("Respuesta posible", 0);
		respuestaPosibleRepository.saveAndFlush(respuestaPosible);
		final RespuestaPosibleEntity respuestaPosibleEncontrada = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

		final ProcedimientoUsuarioEntity procedimientoUsuario = new ProcedimientoUsuarioEntity(0, new Date(), 0,
				procedimiento, usuario);
		procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);

		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioEncontrado = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(usuario, procedimientoEncontrado);
		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso = new ProcedimientoUsuarioProcesoEntity(
				new Date(), 0, respuestaPosibleEncontrada, procesoEncontrado.get(0),
				procedimientoUsuarioEncontrado.get(0));

		procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProceso);

		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
				.findProcedimientoUsuarioProceso(procedimientoUsuarioEncontrado.get(0), procesoEncontrado.get(0));

		try {
			procesoService.eliminaProceso(new Proceso(proceso.getUsuario(), procesoEncontrado.get(0)));
		} catch (final ProcesoAsociadoUsuarioProcedimientoException procesoAsociadoUsuarioProcedimientoException) {
			throw new ProcesoAsociadoUsuarioProcedimientoException(
					CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo()));
		} finally {
			procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
					procedimientoUsuarioProcesoBD.getIdProcedimientoUsuarioProceso());
			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioEncontrado.get(0).getIdProcedimientoUsuario());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleEncontrada.getIdRespuesta());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());
		}
	}

	@Test(expected = ProcesoAsociadoObjetivoException.class)
	public void ProcesoService_eliminarProcesoObjetivoAsociado()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException,
			ProcedimientoAsociadoProcesoException, ProcesoAsociadoRespuestaPosibleException,
			ProcesoAsociadoObjetivoException, ObjetivoNoExisteException, NivelYaExisteException {

		final String resultado = StringUtils.EMPTY;

		final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_ASOCIADO_OBJETIVO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		objetivosProceso.add(objetivoBDNuevo);
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		nivelesProceso.add(1);
		proceso.setNivel(nivelesProceso);
		procesoService.anadirProceso(proceso);

		final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), resultado);

		try {
			procesoService.eliminaProceso(new Proceso(proceso.getUsuario(), procesoEncontrado.get(0)));
		} catch (final ProcesoAsociadoObjetivoException procesoAsociadoObjetivoException) {
			throw new ProcesoAsociadoObjetivoException(
					CodeMessageErrors.PROCESO_ASOCIADO_OBJETIVO_EXCEPTION.getCodigo(), CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.PROCESO_ASOCIADO_OBJETIVO_EXCEPTION.getCodigo()));
		} finally {
			nivelRepository.deleteNivel(objetivoBDNuevo.getIdObjetivo(), procesoEncontrado.get(0).getIdProceso());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());

		}
	}

	@Test
	public void ProcesoService_reactivarProcesoCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoNoExisteException, ObjetivoNoExisteException, NivelYaExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.REACTIVAR_PROCESO);
		proceso.getProceso().setBorradoProceso(1);
		proceso.getProceso().setFechaProceso(new Date());
		final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		proceso.setObjetivos(new ArrayList<>(objetivoBDNuevo.getIdObjetivo()));
		proceso.setNivel(new ArrayList<>(1));
		procesoService.anadirProceso(proceso);

		final ReturnBusquedas<ProcesoEntity> procesoReactivar = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		procesoReactivar.getListaBusquedas().get(0).setBorradoProceso(0);

		final String respuesta = procesoService
				.reactivarProceso(new Proceso(proceso.getUsuario(), procesoReactivar.getListaBusquedas().get(0)));

		procesoService.deleteProceso(procesoReactivar.getListaBusquedas().get(0));

		assertEquals(Constantes.OK, respuesta);

		objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());

	}

	@Test(expected = ProcesoNoExisteException.class)
	public void ProcesoService_reactivarProcesoNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ObjetivoNoExisteException, java.text.ParseException, ProcesoNoExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);

		procesoService.reactivarProceso(proceso);

	}

	private Proceso generateProceso(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonProcesoVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Proceso proceso = new Proceso();
		final ProcesoEntity procesoEntity = new ProcesoEntity();

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		final Date date;
		if (jsonProcesoVacios.get(Constantes.FECHA_PROCESO).toString().equals(StringUtils.EMPTY)) {
			date = formato.parse("0000-00-00");
		} else {
			date = formato.parse(CommonUtilities.coalesce(jsonProcesoVacios.get(Constantes.FECHA_PROCESO).toString(),
					StringUtils.EMPTY));
		}

		if (new String((jsonProcesoVacios.get(Constantes.PROCESO_ID).toString()).getBytes("UTF-8"))
				.equals(StringUtils.EMPTY)) {
			procesoEntity.setIdProceso(0);
		} else {
			procesoEntity.setIdProceso(Integer
					.parseInt(new String((jsonProcesoVacios.get(Constantes.PROCESO_ID).toString()).getBytes("UTF-8"))));
		}
		procesoEntity.setNombreProceso(CommonUtilities.coalesce(
				new String((jsonProcesoVacios.get(Constantes.NOMBRE_PROCESO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		procesoEntity.setDescripProceso(CommonUtilities.coalesce(
				new String((jsonProcesoVacios.get(Constantes.DESCRIP_PROCESO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		procesoEntity.setFechaProceso(date);
		procesoEntity.setBorradoProceso(0);

		proceso.setUsuario(
				CommonUtilities.coalesce(jsonProcesoVacios.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		proceso.setProceso(procesoEntity);

		return proceso;

	}

}
