package com.sds.procedimientoUsuario;

import static org.junit.Assert.assertEquals;
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
import com.sds.model.RespuestaPosibleEntity;
import com.sds.model.RolEntity;
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
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException;
import com.sds.service.exception.ProcedimientoUsuarioYaExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.procedimientousuario.ProcedimientoUsuarioService;
import com.sds.service.procedimientousuario.model.ProcedimientoUsuario;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcedimientoUsuarioServiceTest {

	@Autowired
	ProcedimientoUsuarioService procedimientoUsuarioService;

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

	@Test
	public void ProcedimientoUsuarioService_buscarProcedimientoUsuario()
			throws IOException, ParseException, java.text.ParseException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.BUSCAR_PROCEDIMIENTOUSUARIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);
		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		final ReturnBusquedas<ProcedimientoUsuarioEntity> procedimientoUsuarioEncontrado = procedimientoUsuarioService
				.buscarProcedimientoUsuario(new Date(), usuarioBD, procedimientoEncontrado, 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

		assertNotNull(procedimientoUsuarioEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcedimientoUsuarioService_buscarProcedimientoUsuarioUsuarioVacio()
			throws IOException, ParseException, java.text.ParseException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		final ReturnBusquedas<ProcedimientoUsuarioEntity> procedimientoUsuarioEncontrado = procedimientoUsuarioService
				.buscarProcedimientoUsuario(new Date(), null, procedimientoEncontrado, 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

		assertNotNull(procedimientoUsuarioEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcedimientoUsuarioService_buscarProcedimientoUsuarioProcedimientoVacio()
			throws IOException, ParseException, java.text.ParseException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.PROCEDIMIENTO_PROCEDIMIENTOUSUARIO_VACIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		final ReturnBusquedas<ProcedimientoUsuarioEntity> procedimientoUsuarioEncontrado = procedimientoUsuarioService
				.buscarProcedimientoUsuario(new Date(), null, procedimientoEncontrado, 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

		assertNotNull(procedimientoUsuarioEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcedimientoUsuarioService_guardarProcedimientoUsuario() throws IOException, ParseException,
			java.text.ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.GUARDAR_PROCEDIMIENTOUSUARIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);
		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		final String resultado = procedimientoUsuarioService.anadirProcedimientoUsuario(procedimientoUsuario);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);
		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBDNuevo = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(
						procedimientoUsuario.getProcedimientoUsuario().getUsuario(),
						procedimientoUsuario.getProcedimientoUsuario().getProcedimiento());

		procedimientoUsuarioRepository
				.deleteProcedimientoUsuario(procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario());
		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

		assertNotNull(resultado, Constantes.OK);
	}

	@Test
	public void ProcedimientoUsuarioService_guardarProcedimientoUsuarioUsuarioVacio() throws IOException,
			ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		final String resultado = procedimientoUsuarioService.anadirProcedimientoUsuario(procedimientoUsuario);

		assertEquals(resultado, CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name());

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

	}

	@Test
	public void ProcedimientoUsuarioService_guardarProcedimientoUsuarioProcedimientoVacio() throws IOException,
			ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);

		final String resultado = procedimientoUsuarioService.anadirProcedimientoUsuario(procedimientoUsuario);

		assertEquals(resultado, CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name());

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

	}

	@Test
	public void ProcedimientoUsuarioService_guardarProcedimientoUsuarioDatosVacios() throws IOException, ParseException,
			java.text.ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		procedimientoUsuario.getProcedimientoUsuario().setPuntuacionProcedimientoUsuario(null);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		final String resultado = procedimientoUsuarioService.anadirProcedimientoUsuario(procedimientoUsuario);

		assertEquals(resultado, CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name());

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

	}

	@Test
	public void ProcedimientoUsuarioService_modificarProcedimientoUsuario()
			throws IOException, ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException,
			ProcedimientoNoExisteException, ProcedimientoUsuarioNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.GUARDAR_PROCEDIMIENTOUSUARIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);
		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		procedimientoUsuarioService.anadirProcedimientoUsuario(procedimientoUsuario);

		procedimientoUsuario.getProcedimientoUsuario().setPuntuacionProcedimientoUsuario(3);

		final String resultado = procedimientoUsuarioService.modificarProcedimientoUsuario(procedimientoUsuario);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);
		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBDNuevo = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(
						procedimientoUsuario.getProcedimientoUsuario().getUsuario(),
						procedimientoUsuario.getProcedimientoUsuario().getProcedimiento());

		procedimientoUsuarioRepository
				.deleteProcedimientoUsuario(procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario());
		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

		assertNotNull(resultado, Constantes.OK);
	}

	@Test
	public void ProcedimientoUsuarioService_modificarProcedimientoUsuarioUsuarioVacio()
			throws IOException, ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException,
			ProcedimientoNoExisteException, ProcedimientoUsuarioNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		final String resultado = procedimientoUsuarioService.modificarProcedimientoUsuario(procedimientoUsuario);

		assertEquals(resultado, CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name());

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

	}

	@Test
	public void ProcedimientoUsuarioService_modificarProcedimientoUsuarioProcedimientoVacio()
			throws IOException, ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException,
			ProcedimientoNoExisteException, ProcedimientoUsuarioNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);

		final String resultado = procedimientoUsuarioService.modificarProcedimientoUsuario(procedimientoUsuario);

		assertEquals(resultado, CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name());

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

	}

	@Test
	public void ProcedimientoUsuarioService_modificarProcedimientoUsuarioDatosVacios()
			throws IOException, ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException,
			ProcedimientoNoExisteException, ProcedimientoUsuarioNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.USUARIO_PROCEDIMIENTOUSUARIO_VACIO);
		procedimientoUsuario.getProcedimientoUsuario().setPuntuacionProcedimientoUsuario(null);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		final String resultado = procedimientoUsuarioService.modificarProcedimientoUsuario(procedimientoUsuario);

		assertEquals(resultado, CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name());

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

	}

	@Test(expected = ProcedimientoUsuarioNoExisteException.class)
	public void ProcedimientoUsuarioService_modificarProcedimientoUsuarioNoExiste()
			throws IOException, ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException,
			ProcedimientoNoExisteException, ProcedimientoUsuarioNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.PROCEDIMIENTOUSUARIO_NO_EXISTE);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);
		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		try {
			procedimientoUsuarioService.modificarProcedimientoUsuario(procedimientoUsuario);
		} catch (final ProcedimientoUsuarioNoExisteException procedimientoUsuarioNoExiste) {
			throw new ProcedimientoUsuarioNoExisteException(
					CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
		} finally {
			final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
					objetivo.getDescripObjetivo());
			final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					"", objetivo);

			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planEliminar.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
			usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());
		}

	}

	@Test
	public void ProcedimientoUsuarioService_eliminarProcedimientoUsuario() throws IOException, ParseException,
			java.text.ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException,
			ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException, ProcedimientoUsuarioNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.ELIMINAR_PROCEDIMIENTOUSUARIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);
		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		procedimientoUsuarioService.anadirProcedimientoUsuario(procedimientoUsuario);

		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBDNuevo = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(
						procedimientoUsuario.getProcedimientoUsuario().getUsuario(),
						procedimientoUsuario.getProcedimientoUsuario().getProcedimiento());

		final String resultado = procedimientoUsuarioService.eliminaProcedimientoUsuario(
				new ProcedimientoUsuario(procedimientoUsuario.getUsuario(), procedimientoUsuarioBDNuevo.get(0), "No"));

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), "",
				objetivo);

		procedimientoUsuarioRepository
				.deleteProcedimientoUsuario(procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario());
		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
		usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
		personaRepository.deletePersona(persona.getDniP());

		assertNotNull(resultado, Constantes.OK);
	}

	@Test(expected = ProcedimientoUsuarioNoExisteException.class)
	public void ProcedimientoUsuarioService_eliminarProcedimientoUsuarioNoExiste() throws IOException, ParseException,
			java.text.ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException,
			ProcedimientoUsuarioNoExisteException, ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.PROCEDIMIENTOUSUARIO_NO_EXISTE);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);
		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		try {
			procedimientoUsuarioService.eliminaProcedimientoUsuario(procedimientoUsuario);
		} catch (final ProcedimientoUsuarioNoExisteException procedimientoUsuarioNoExiste) {
			throw new ProcedimientoUsuarioNoExisteException(
					CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
		} finally {
			final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
					objetivo.getDescripObjetivo());
			final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					"", objetivo);

			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planEliminar.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
			usuarioRepository.deleteUsuario(usuarioBD.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());
		}

	}

	@Test(expected = ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException.class)
	public void ProcedimientoUsuarioService_eliminarProcedimientoUsuarioAsociadoProceso() throws IOException,
			ParseException, java.text.ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException,
			ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException, ProcedimientoUsuarioNoExisteException {

		final ProcedimientoUsuario procedimientoUsuario = generateProcedimientoUsuario(
				Constantes.URL_JSON_PROCEDIMIENTOUSUARIO_DATA, Constantes.ELIMINAR_PROCEDIMIENTOUSUARIO);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rol = new RolEntity(2, "usuario", "Contentrá a todos los usuarios de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rol);
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

		procedimientoUsuario.getProcedimientoUsuario().setUsuario(usuarioBD);
		procedimientoUsuario.getProcedimientoUsuario().setProcedimiento(procedimientoEncontrado);

		procedimientoUsuarioService.anadirProcedimientoUsuario(procedimientoUsuario);

		final ProcesoEntity proceso = new ProcesoEntity("Nombre proceso", "Descrip proceso", new Date(), 0);
		procesoRepository.saveAndFlush(proceso);
		final ProcesoEntity procesoEncontrado = procesoRepository.findProcesoByName(proceso.getNombreProceso());
		final RespuestaPosibleEntity respuestaPosible = new RespuestaPosibleEntity("Texto", 0);
		respuestaPosibleRepository.saveAndFlush(respuestaPosible);
		final RespuestaPosibleEntity respuestaPosibleEncontrada = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBDNuevo = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(
						procedimientoUsuario.getProcedimientoUsuario().getUsuario(),
						procedimientoUsuario.getProcedimientoUsuario().getProcedimiento());

		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso = new ProcedimientoUsuarioProcesoEntity(
				new Date(), 0, respuestaPosibleEncontrada, procesoEncontrado, procedimientoUsuarioBDNuevo.get(0));
		procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProceso);

		final List<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
				.findProcedimientoUsuarioProcesoByIdProcedimientoUsuarioAndIdProceso(
						procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario(),
						procesoEncontrado.getIdProceso());

		try {
			procedimientoUsuarioService.eliminaProcedimientoUsuario(new ProcedimientoUsuario(
					procedimientoUsuario.getUsuario(), procedimientoUsuarioBDNuevo.get(0), "No"));
		} catch (final ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException procedimientoUsuarioAsociadoProceso) {
			throw new ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException(
					CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO.getCodigo(), CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO.getCodigo()));
		} finally {
			final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
					objetivo.getDescripObjetivo());
			final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					"", objetivo);

			procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
					procedimientoUsuarioProcesoBD.get(0).getIdProcedimientoUsuarioProceso());
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioBDNuevo.get(0).getIdProcedimientoUsuario());
			procesoRepository.deleteProceso(procesoEncontrado.getIdProceso());
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleEncontrada.getIdRespuesta());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planEliminar.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());
		}

	}

	private ProcedimientoUsuario generateProcedimientoUsuario(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonProcedimientoUsuarioVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final ProcedimientoUsuario procedimientoUsuario = new ProcedimientoUsuario();
		final ProcedimientoUsuarioEntity procedimientoUsuarioEntity = new ProcedimientoUsuarioEntity();

		final String idProcedimientoUsuario = CommonUtilities
				.coalesce(jsonProcedimientoUsuarioVacios.get(Constantes.PROCEDIMIENTOUSUARIO_ID).toString(),
						StringUtils.EMPTY)
				.toString();

		if (idProcedimientoUsuario.equals(StringUtils.EMPTY)) {
			procedimientoUsuarioEntity.setIdProcedimientoUsuario(0);
		} else {
			procedimientoUsuarioEntity.setIdProcedimientoUsuario(Integer.parseInt(idProcedimientoUsuario));
		}

		procedimientoUsuarioEntity.setPuntuacionProcedimientoUsuario(Integer.parseInt(CommonUtilities.coalesce(
				jsonProcedimientoUsuarioVacios.get(Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO).toString(),
				StringUtils.EMPTY)));

		procedimientoUsuarioEntity.setBorradoProcedimientoUsuario(0);
		procedimientoUsuarioEntity.setFechaProcedimientoUsuario(new Date());

		procedimientoUsuario.setUsuario(CommonUtilities
				.coalesce(jsonProcedimientoUsuarioVacios.get(Constantes.USER).toString(), StringUtils.EMPTY));
		procedimientoUsuario.setProcedimientoUsuario(procedimientoUsuarioEntity);

		return procedimientoUsuario;

	}

}
