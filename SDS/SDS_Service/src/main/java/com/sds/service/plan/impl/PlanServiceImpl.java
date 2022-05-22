package com.sds.service.plan.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PlanEntity;
import com.sds.repository.PlanRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PlanNoExisteException;
import com.sds.service.exception.PlanYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.plan.PlanService;
import com.sds.service.plan.model.Plan;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class PlanServiceImpl implements PlanService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public PlanServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<PlanEntity> buscarPlan(final String nombrePlan, final String descripPlan,
			final Date fechaPlan, final int inicio, final int tamanhoPagina) {
		final List<PlanEntity> planToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();
		String fecha = StringUtils.EMPTY;

		if (fechaPlan != null) {
			java.sql.Date fechaSql;
			fechaSql = new java.sql.Date(fechaPlan.getTime());
			fecha = fechaSql.toString();
		} else {
			fecha = StringUtils.EMPTY;
		}

		final List<PlanEntity> planes = entityManager.createNamedQuery(Constantes.PLAN_QUERY_FINDPLAN)
				.setParameter(Constantes.NOMBRE_PLAN, nombrePlan).setParameter(Constantes.DESCRIPCION_PLAN, descripPlan)
				.setParameter(Constantes.FECHA_PLAN, fecha).setFirstResult(inicio).setMaxResults(tamanhoPagina)
				.getResultList();

		final Integer numberTotalResults = planRepository.numberFindPlan(nombrePlan, descripPlan, fecha);

		if (!planes.isEmpty()) {
			for (final PlanEntity plan : planes) {
				final PlanEntity planEntity = new PlanEntity(plan.getIdPlan(), plan.getNombrePlan(),
						plan.getDescripPlan(), plan.getFechaPlan(), plan.getBorradoPlan());
				planToret.add(planEntity);

			}
		}

		datosBusqueda.add(Constantes.NOMBRE_PLAN + Constantes.DOS_PUNTOS + nombrePlan);
		datosBusqueda.add(Constantes.DESCRIPCION_PLAN + Constantes.DOS_PUNTOS + descripPlan);

		final ReturnBusquedas<PlanEntity> result = new ReturnBusquedas<>(planToret, datosBusqueda, numberTotalResults,
				planToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<PlanEntity> buscarTodos(final int inicio, final int tamanhoPagina) {
		final List<PlanEntity> planToret = new ArrayList<>();
		final List<PlanEntity> planes = entityManager.createNamedQuery(Constantes.PLAN_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = planRepository.numberFindAllPlanes();

		if (!planes.isEmpty()) {
			for (final PlanEntity plan : planes) {
				final PlanEntity planEntity = new PlanEntity(plan.getIdPlan(), plan.getNombrePlan(),
						plan.getDescripPlan(), plan.getFechaPlan(), plan.getBorradoPlan());
				planToret.add(planEntity);

			}
		}

		final ReturnBusquedas<PlanEntity> result = new ReturnBusquedas<>(planToret, numberTotalResults,
				planToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<PlanEntity> buscarPlanesEliminados(final int inicio, final int tamanhoPagina) {
		final List<PlanEntity> planToret = new ArrayList<>();
		final List<PlanEntity> planes = entityManager.createNamedQuery(Constantes.PLAN_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = planRepository.numberFindPlanesEliminados();

		if (!planes.isEmpty()) {
			for (final PlanEntity plan : planes) {
				final PlanEntity planEntity = new PlanEntity(plan.getIdPlan(), plan.getNombrePlan(),
						plan.getDescripPlan(), plan.getFechaPlan(), plan.getBorradoPlan());
				planToret.add(planEntity);

			}
		}

		final ReturnBusquedas<PlanEntity> result = new ReturnBusquedas<>(planToret, numberTotalResults,
				planToret.size(), inicio);

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirPlan(final Plan plan) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			PlanYaExisteException, ParseException, FechaAnteriorFechaActualException {
		final PlanEntity planEntity = plan.getPlan();
		final Boolean planValido = validaciones.comprobarPlanBlank(planEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (planValido) {
			final PlanEntity planBD = planRepository.findPlanByName(planEntity.getNombrePlan());

			if (planBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(plan.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PLAN_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new PlanYaExisteException(CodeMessageErrors.PLAN_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final LocalDate fechaActual = LocalDate.now();
				final String fechaIntroducidaUsuario = plan.getPlan().getFechaPlan().toString();
				final String fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
						+ fechaActual.getDayOfMonth();
				if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(plan.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()),
							CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new FechaAnteriorFechaActualException(
							CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()));
				} else {
					planEntity.setBorradoPlan(0);
					planRepository.saveAndFlush(planEntity);

					final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(plan.getUsuario(),
							Constantes.ACCION_AÑADIR_PLAN, plan.getPlan().toString());

					resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

					if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
					}

					resultado = Constantes.OK;
				}

			}
		} else {
			resultado = CodeMessageErrors.PLAN_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminaPlan(final Plan plan) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			PlanNoExisteException, ParseException {
		final PlanEntity planEntity = plan.getPlan();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<PlanEntity> planBD = planRepository.findById(planEntity.getIdPlan());

		if (!planBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(plan.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new PlanNoExisteException(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {

			planBD.get().setBorradoPlan(1);
			planRepository.saveAndFlush(planBD.get());
			plan.setPlan(planBD.get());
			resultado = Constantes.OK;
		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarPlan(final Plan plan) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, PlanNoExisteException, ParseException, FechaAnteriorFechaActualException {
		final PlanEntity planEntity = plan.getPlan();
		final Boolean planValido = validaciones.comprobarPlanBlank(planEntity);

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (planValido) {
			final Optional<PlanEntity> planBD = planRepository.findById(planEntity.getIdPlan());

			if (!planBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(plan.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new PlanNoExisteException(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {

				final LocalDate fechaActual = LocalDate.now();
				final String fechaIntroducidaUsuario = plan.getPlan().getFechaPlan().toString();
				final String fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
						+ fechaActual.getDayOfMonth();

				if (!fechaIntroducidaUsuario.equals(planBD.get().getFechaPlan().toString())) {
					if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(plan.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()),
								CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new FechaAnteriorFechaActualException(
								CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()));
					}

				} else {
					planBD.get().setNombrePlan(planEntity.getNombrePlan());
					planBD.get().setDescripPlan(planEntity.getDescripPlan());
					planBD.get().setFechaPlan(planEntity.getFechaPlan());
					planBD.get().setBorradoPlan(planEntity.getBorradoPlan());

					planRepository.saveAndFlush(planBD.get());

					final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(plan.getUsuario(),
							Constantes.ACCION_MODIFICAR_PLAN, plan.getPlan().toString());

					resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

					if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
					}

					resultado = Constantes.OK;
				}

			}
		} else {
			resultado = CodeMessageErrors.PLAN_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String reactivarPlan(final Plan plan) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, PlanNoExisteException, ParseException {
		final PlanEntity planEntity = plan.getPlan();

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<PlanEntity> planBD = planRepository.findById(planEntity.getIdPlan());

		if (!planBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(plan.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new PlanNoExisteException(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {

			planBD.get().setBorradoPlan(0);
			planRepository.saveAndFlush(planBD.get());
			plan.setPlan(planBD.get());
			resultado = Constantes.OK;
		}

		return resultado;
	}

	@Override
	public void deletePlan(final PlanEntity plan) throws PlanNoExisteException, ParseException {
		final Boolean planValido = validaciones.comprobarPlanBlank(plan);

		if (planValido) {
			final Optional<PlanEntity> planBD = planRepository.findById(plan.getIdPlan());

			if (!planBD.isPresent()) {
				throw new PlanNoExisteException(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				planRepository.deletePlan(plan.getIdPlan());
				planRepository.flush();
			}
		}
	}
}
