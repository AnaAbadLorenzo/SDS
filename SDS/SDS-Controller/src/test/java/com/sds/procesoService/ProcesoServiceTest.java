package com.sds.procesoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.sds.model.UsuarioEntity;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.PlanRepository;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcedimientoUsuarioProcesoRepository;
import com.sds.repository.ProcedimientoUsuarioRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
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
	public void ProcesoService_guardarProceso() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, ProcesoYaExisteException, java.text.ParseException,
			FechaAnteriorFechaActualException, ProcesoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);

		String respuesta = StringUtils.EMPTY;
		proceso.getProceso().setFechaProceso(new Date());
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		procesoService.deleteProceso(procesoEncontrado.getListaBusquedas().get(0));
	}

	@Test
	public void ProcesoService_guardarProcesoNombreProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);
	}

	@Test
	public void ProcesoService_guardarProcesoDescripcionProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_guardarProcesoFechaProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_guardarProcesoDatosProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test(expected = ProcesoYaExisteException.class)
	public void ProcesoService_guardarProcesoYaExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, ProcesoYaExisteException, FechaAnteriorFechaActualException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_YA_EXISTE);

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
	public void PlanService_guardarProcesoFechaProcesoAnteriorActual() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, java.text.ParseException,
			FechaAnteriorFechaActualException, ProcesoYaExisteException, ProcesoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);

		try {
			procesoService.anadirProceso(proceso);
		} catch (final FechaAnteriorFechaActualException fechaAnteriorFechaActualException) {
			throw new FechaAnteriorFechaActualException(
					CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()));
		} finally {
			final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
					proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0,
					1);

			procesoService.deleteProceso(procesoEncontrado.getListaBusquedas().get(0));
		}

	}

	@Test
	public void ProcesoService_modificarProceso() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoYaExisteException, ProcesoNoExisteException, ProcesoAsociadoUsuarioProcedimientoException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		proceso.getProceso().setFechaProceso(new Date());
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
	public void ProcesoService_modificarProcesoNombreProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoNoExisteException, ProcesoAsociadoUsuarioProcedimientoException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);
	}

	@Test
	public void ProcesoService_modificarProcesoDescripcionProcesoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_modificarProcesoFechaProcesoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_modificarProcesoDatosProcesoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test(expected = ProcesoNoExisteException.class)
	public void ProcesoService_modificarProcesoNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);

		procesoService.modificarProceso(proceso);

	}

	@Test(expected = ProcesoAsociadoUsuarioProcedimientoException.class)
	public void ProcesoService_modificarProcesoAsociadoUsuarioProcedimiento()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException {

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
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", 0);
		personaRepository.saveAndFlush(persona);
		usuarioRepository.saveAndFlush(usuario);
		procedimientoRepository.saveAndFlush(procedimiento);

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		procesoService.anadirProceso(proceso);

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimiento.getNombreProcedimiento());
		final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), resultado);

		final ProcedimientoUsuarioEntity procedimientoUsuario = new ProcedimientoUsuarioEntity(0, new Date(), 0,
				procedimiento, usuario);
		procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);

		final ProcedimientoUsuarioEntity procedimientoUsuarioEncontrado = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(procedimientoEncontrado, usuario);
		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso = new ProcedimientoUsuarioProcesoEntity(
				procesoEncontrado.get(0).getIdProceso(), procedimientoUsuarioEncontrado.getIdProcedimientoUsuario(),
				new Date(), 0);
		procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProceso);

		try {
			procesoService.modificarProceso(proceso);
		} catch (final ProcesoAsociadoUsuarioProcedimientoException procesoAsociadoUsuarioProcedimientoException) {
			throw new ProcesoAsociadoUsuarioProcedimientoException(
					CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo()));
		} finally {
			procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
					procesoEncontrado.get(0).getIdProceso(),
					procedimientoUsuarioEncontrado.getIdProcedimientoUsuario());
			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioEncontrado.getIdProcedimientoUsuario());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());
		}

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

		procesoEntity.setBorradoProceso(0);

		proceso.setUsuario(
				CommonUtilities.coalesce(jsonProcesoVacios.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		proceso.setProceso(procesoEntity);

		return proceso;

	}

}
