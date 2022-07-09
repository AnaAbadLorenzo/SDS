package com.sds.service.procedimiento.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import com.sds.model.NoticiasEntity;
import com.sds.model.ObjetivoEntity;
import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.repository.NoticiasRepository;
import com.sds.repository.PlanRepository;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcedimientoUsuarioRepository;
import com.sds.repository.ProcesoProcedimientoRepository;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PlanNoExisteException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcedimientoYaExisteException;
import com.sds.service.exception.ProcesoAsociadoProcedimientoException;
import com.sds.service.exception.UsuarioAsociadoProcedimientoException;
import com.sds.service.log.LogService;
import com.sds.service.procedimiento.ProcedimientoService;
import com.sds.service.procedimiento.model.Procedimiento;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class ProcedimientoServiceImpl implements ProcedimientoService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ProcesoProcedimientoRepository procesoProcedimientoRepository;

	@Autowired
	ProcedimientoUsuarioRepository procedimientoUsuarioRepository;

	@Autowired
	NoticiasRepository noticiasRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public ProcedimientoServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<ProcedimientoEntity> buscarProcedimiento(final String nombreProcedimiento,
			final String descripProcedimiento, final Date fechaProcedimiento, final Boolean checkUsuario,
			final PlanEntity plan, final int inicio, final int tamanhoPagina) {
		final List<ProcedimientoEntity> procedimientoToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();
		List<ProcedimientoEntity> procedimientos = new ArrayList<>();
		Integer numberTotalResults = 0;
		String fecha = StringUtils.EMPTY;

		if (fechaProcedimiento != null) {
			java.sql.Date fechaSql;
			fechaSql = new java.sql.Date(fechaProcedimiento.getTime());
			fecha = fechaSql.toString();
		} else {
			fecha = StringUtils.EMPTY;
		}

		if (plan == null) {
			if (checkUsuario == null) {
				procedimientos = entityManager
						.createNamedQuery(Constantes.PROCEDIMIENTO_QUERY_FINDPROCEDIMIENTOWITHOUTCHECK)
						.setParameter(Constantes.NOMBRE_PROCEDIMIENTO, nombreProcedimiento)
						.setParameter(Constantes.DESCRIPCION_PROCEDIMIENTO, descripProcedimiento)
						.setParameter(Constantes.FECHA_PROCEDIMIENTO, fecha)
						.setParameter(Constantes.PLAN, StringUtils.EMPTY).setFirstResult(inicio)
						.setMaxResults(tamanhoPagina).getResultList();
				numberTotalResults = procedimientoRepository.numberFindProcedimientoWithoutCheck(nombreProcedimiento,
						descripProcedimiento, fecha);
			} else {
				procedimientos = entityManager.createNamedQuery(Constantes.PROCEDIMIENTO_QUERY_FINDPROCEDIMIENTO)
						.setParameter(Constantes.NOMBRE_PROCEDIMIENTO, nombreProcedimiento)
						.setParameter(Constantes.DESCRIPCION_PROCEDIMIENTO, descripProcedimiento)
						.setParameter(Constantes.FECHA_PROCEDIMIENTO, fecha)
						.setParameter(Constantes.CHECK_USUARIO, checkUsuario)
						.setParameter(Constantes.PLAN, StringUtils.EMPTY).setFirstResult(inicio)
						.setMaxResults(tamanhoPagina).getResultList();
				numberTotalResults = procedimientoRepository.numberFindProcedimiento(nombreProcedimiento,
						descripProcedimiento, fecha, checkUsuario);
			}

		} else {
			if (checkUsuario == null) {
				final Optional<PlanEntity> planBD = planRepository.findById(plan.getIdPlan());
				procedimientos = entityManager
						.createNamedQuery(Constantes.PROCEDIMIENTO_QUERY_FINDPROCEDIMIENTOWITHOUTCHECK)
						.setParameter(Constantes.NOMBRE_PROCEDIMIENTO, nombreProcedimiento)
						.setParameter(Constantes.DESCRIPCION_PROCEDIMIENTO, descripProcedimiento)
						.setParameter(Constantes.FECHA_PROCEDIMIENTO, fecha).setParameter(Constantes.PLAN, planBD.get())
						.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();
				numberTotalResults = procedimientoRepository.numberFindProcedimientoWithoutCheckAndPlan(
						nombreProcedimiento, descripProcedimiento, fecha, plan);
			} else {
				final Optional<PlanEntity> planBD = planRepository.findById(plan.getIdPlan());
				procedimientos = entityManager.createNamedQuery(Constantes.PROCEDIMIENTO_QUERY_FINDPROCEDIMIENTO)
						.setParameter(Constantes.NOMBRE_PROCEDIMIENTO, nombreProcedimiento)
						.setParameter(Constantes.DESCRIPCION_PROCEDIMIENTO, descripProcedimiento)
						.setParameter(Constantes.FECHA_PROCEDIMIENTO, fecha)
						.setParameter(Constantes.CHECK_USUARIO, checkUsuario)
						.setParameter(Constantes.PLAN, planBD.get()).setFirstResult(inicio).setMaxResults(tamanhoPagina)
						.getResultList();
				numberTotalResults = procedimientoRepository.numberFindProcedimientoWithPlan(nombreProcedimiento,
						descripProcedimiento, fecha, checkUsuario, plan);
			}

		}

		if (!procedimientos.isEmpty()) {
			for (final ProcedimientoEntity procedimiento : procedimientos) {
				final ObjetivoEntity objetivoEntity = new ObjetivoEntity(
						procedimiento.getPlan().getObjetivo().getIdObjetivo(),
						procedimiento.getPlan().getObjetivo().getNombreObjetivo(),
						procedimiento.getPlan().getObjetivo().getDescripObjetivo(),
						procedimiento.getPlan().getObjetivo().getBorradoObjetivo());
				final PlanEntity planEntity = new PlanEntity(procedimiento.getPlan().getIdPlan(),
						procedimiento.getPlan().getNombrePlan(), procedimiento.getPlan().getDescripPlan(),
						procedimiento.getPlan().getFechaPlan(), procedimiento.getPlan().getBorradoPlan(),
						objetivoEntity);
				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimiento.getIdProcedimiento(), procedimiento.getNombreProcedimiento(),
						procedimiento.getDescripProcedimiento(), procedimiento.getFechaProcedimiento(),
						procedimiento.getBorradoProcedimiento(), procedimiento.getCheckUsuario(), planEntity);
				procedimientoToret.add(procedimientoEntity);

			}
		}

		datosBusqueda.add(Constantes.NOMBRE_PROCEDIMIENTO + Constantes.DOS_PUNTOS + nombreProcedimiento);
		datosBusqueda.add(Constantes.DESCRIPCION_PROCEDIMIENTO + Constantes.DOS_PUNTOS + descripProcedimiento);
		datosBusqueda.add(Constantes.FECHA_PROCEDIMIENTO + Constantes.DOS_PUNTOS + fecha);
		if (checkUsuario != null) {
			datosBusqueda.add(Constantes.CHECK_USUARIO + Constantes.DOS_PUNTOS + checkUsuario.toString());
		}
		if (plan != null) {
			datosBusqueda.add(Constantes.PLAN + Constantes.DOS_PUNTOS + plan.getIdPlan());
		}

		final ReturnBusquedas<ProcedimientoEntity> result = new ReturnBusquedas<>(procedimientoToret, datosBusqueda,
				numberTotalResults, procedimientoToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<ProcedimientoEntity> buscarProcedimientoByPlan(final PlanEntity plan, final int inicio,
			final int tamanhoPagina) {
		final List<ProcedimientoEntity> procedimientoToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();
		List<ProcedimientoEntity> procedimientos = new ArrayList<>();
		Integer numberTotalResults = 0;

		final Optional<PlanEntity> planBD = planRepository.findById(plan.getIdPlan());
		procedimientos = entityManager.createNamedQuery(Constantes.PROCEDIMIENTO_QUERY_FINDPROCEDIMIENTOSBYPLAN)
				.setParameter(Constantes.PLAN, planBD.get()).setFirstResult(inicio).setMaxResults(tamanhoPagina)
				.getResultList();
		numberTotalResults = procedimientoRepository.numberFindProcedimientosByPlan(planBD.get());

		if (!procedimientos.isEmpty()) {
			for (final ProcedimientoEntity procedimiento : procedimientos) {
				final ObjetivoEntity objetivoEntity = new ObjetivoEntity(
						procedimiento.getPlan().getObjetivo().getIdObjetivo(),
						procedimiento.getPlan().getObjetivo().getNombreObjetivo(),
						procedimiento.getPlan().getObjetivo().getDescripObjetivo(),
						procedimiento.getPlan().getObjetivo().getBorradoObjetivo());
				final PlanEntity planEntity = new PlanEntity(procedimiento.getPlan().getIdPlan(),
						procedimiento.getPlan().getNombrePlan(), procedimiento.getPlan().getDescripPlan(),
						procedimiento.getPlan().getFechaPlan(), procedimiento.getPlan().getBorradoPlan(),
						objetivoEntity);
				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimiento.getIdProcedimiento(), procedimiento.getNombreProcedimiento(),
						procedimiento.getDescripProcedimiento(), procedimiento.getFechaProcedimiento(),
						procedimiento.getBorradoProcedimiento(), procedimiento.getCheckUsuario(), planEntity);
				procedimientoToret.add(procedimientoEntity);

			}
		}

		datosBusqueda.add(Constantes.PLAN + Constantes.DOS_PUNTOS + plan.getIdPlan());

		final ReturnBusquedas<ProcedimientoEntity> result = new ReturnBusquedas<>(procedimientoToret, datosBusqueda,
				numberTotalResults, procedimientoToret.size(), inicio);

		return result;

	}

	@Override
	public ReturnBusquedas<ProcedimientoEntity> buscarTodos(final int inicio, final int tamanhoPagina) {
		final List<ProcedimientoEntity> procedimientoToret = new ArrayList<>();
		final List<ProcedimientoEntity> procedimientos = entityManager
				.createNamedQuery(Constantes.PROCEDIMIENTO_QUERY_FINDALL).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = procedimientoRepository.numberFindAllProcedimientos();

		if (!procedimientos.isEmpty()) {
			for (final ProcedimientoEntity procedimiento : procedimientos) {
				final ObjetivoEntity objetivoEntity = new ObjetivoEntity(
						procedimiento.getPlan().getObjetivo().getIdObjetivo(),
						procedimiento.getPlan().getObjetivo().getNombreObjetivo(),
						procedimiento.getPlan().getObjetivo().getDescripObjetivo(),
						procedimiento.getPlan().getObjetivo().getBorradoObjetivo());
				final PlanEntity planEntity = new PlanEntity(procedimiento.getPlan().getIdPlan(),
						procedimiento.getPlan().getNombrePlan(), procedimiento.getPlan().getDescripPlan(),
						procedimiento.getPlan().getFechaPlan(), procedimiento.getPlan().getBorradoPlan(),
						objetivoEntity);
				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimiento.getIdProcedimiento(), procedimiento.getNombreProcedimiento(),
						procedimiento.getDescripProcedimiento(), procedimiento.getFechaProcedimiento(),
						procedimiento.getBorradoProcedimiento(), procedimiento.getCheckUsuario(), planEntity);
				procedimientoToret.add(procedimientoEntity);

			}
		}

		final ReturnBusquedas<ProcedimientoEntity> result = new ReturnBusquedas<>(procedimientoToret,
				numberTotalResults, procedimientoToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<ProcedimientoEntity> buscarTodos() {
		final List<ProcedimientoEntity> procedimientoToret = new ArrayList<>();
		final List<ProcedimientoEntity> procedimientos = entityManager
				.createNamedQuery(Constantes.PROCEDIMIENTO_QUERY_FINDALL).getResultList();

		final Integer numberTotalResults = procedimientoRepository.numberFindAllProcedimientos();

		if (!procedimientos.isEmpty()) {
			for (final ProcedimientoEntity procedimiento : procedimientos) {
				final ObjetivoEntity objetivoEntity = new ObjetivoEntity(
						procedimiento.getPlan().getObjetivo().getIdObjetivo(),
						procedimiento.getPlan().getObjetivo().getNombreObjetivo(),
						procedimiento.getPlan().getObjetivo().getDescripObjetivo(),
						procedimiento.getPlan().getObjetivo().getBorradoObjetivo());
				final PlanEntity planEntity = new PlanEntity(procedimiento.getPlan().getIdPlan(),
						procedimiento.getPlan().getNombrePlan(), procedimiento.getPlan().getDescripPlan(),
						procedimiento.getPlan().getFechaPlan(), procedimiento.getPlan().getBorradoPlan(),
						objetivoEntity);
				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimiento.getIdProcedimiento(), procedimiento.getNombreProcedimiento(),
						procedimiento.getDescripProcedimiento(), procedimiento.getFechaProcedimiento(),
						procedimiento.getBorradoProcedimiento(), procedimiento.getCheckUsuario(), planEntity);
				procedimientoToret.add(procedimientoEntity);

			}
		}

		final ReturnBusquedas<ProcedimientoEntity> result = new ReturnBusquedas<>(procedimientoToret,
				numberTotalResults, procedimientoToret.size(), 0);

		return result;
	}

	@Override
	public ReturnBusquedas<ProcedimientoEntity> buscarProcedimientosEliminados(final int inicio,
			final int tamanhoPagina) {
		final List<ProcedimientoEntity> procedimientoToret = new ArrayList<>();
		final List<ProcedimientoEntity> procedimientos = entityManager
				.createNamedQuery(Constantes.PROCEDIMIENTO_QUERY_FINDELIMINADOS).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = procedimientoRepository.numberFindProcedimientosEliminados();

		if (!procedimientos.isEmpty()) {
			for (final ProcedimientoEntity procedimiento : procedimientos) {
				final ObjetivoEntity objetivoEntity = new ObjetivoEntity(
						procedimiento.getPlan().getObjetivo().getIdObjetivo(),
						procedimiento.getPlan().getObjetivo().getNombreObjetivo(),
						procedimiento.getPlan().getObjetivo().getDescripObjetivo(),
						procedimiento.getPlan().getObjetivo().getBorradoObjetivo());
				final PlanEntity planEntity = new PlanEntity(procedimiento.getPlan().getIdPlan(),
						procedimiento.getPlan().getNombrePlan(), procedimiento.getPlan().getDescripPlan(),
						procedimiento.getPlan().getFechaPlan(), procedimiento.getPlan().getBorradoPlan(),
						objetivoEntity);
				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimiento.getIdProcedimiento(), procedimiento.getNombreProcedimiento(),
						procedimiento.getDescripProcedimiento(), procedimiento.getFechaProcedimiento(),
						procedimiento.getBorradoProcedimiento(), procedimiento.getCheckUsuario(), planEntity);
				procedimientoToret.add(procedimientoEntity);

			}
		}

		final ReturnBusquedas<ProcedimientoEntity> result = new ReturnBusquedas<>(procedimientoToret,
				numberTotalResults, procedimientoToret.size(), inicio);

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirProcedimiento(final Procedimiento procedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcedimientoYaExisteException,
			FechaAnteriorFechaActualException, PlanNoExisteException, ParseException {
		final ProcedimientoEntity procedimientoEntity = procedimiento.getProcedimientoEntity();
		final Boolean procedimientoValido = validaciones.comprobarProcedimientoBlank(procedimientoEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String fechaIntroducidaUsuario = StringUtils.EMPTY;
		String fechaActualString = StringUtils.EMPTY;
		String fechaDescripNoticia = StringUtils.EMPTY;

		if (procedimientoValido) {
			final ProcedimientoEntity procedimientoBD = procedimientoRepository
					.findProcedimientoByName(procedimientoEntity.getNombreProcedimiento());

			if (procedimientoBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(procedimiento.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoYaExisteException(
						CodeMessageErrors.PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final LocalDate fechaActual = LocalDate.now();
				final LocalDate dateIntroducidaUsuario = procedimientoEntity.getFechaProcedimiento().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();

				if (CommonUtilities.countDigit(dateIntroducidaUsuario.getDayOfMonth()) == 1) {
					fechaIntroducidaUsuario = dateIntroducidaUsuario.getYear() + "-0"
							+ dateIntroducidaUsuario.getMonthValue() + "-0" + dateIntroducidaUsuario.getDayOfMonth();
				} else {
					fechaIntroducidaUsuario = dateIntroducidaUsuario.getYear() + "-0"
							+ dateIntroducidaUsuario.getMonthValue() + "-" + dateIntroducidaUsuario.getDayOfMonth();
				}

				if (CommonUtilities.countDigit(fechaActual.getDayOfMonth()) == 1) {
					fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-0"
							+ fechaActual.getDayOfMonth();
				} else {
					fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
							+ fechaActual.getDayOfMonth();
				}

				if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procedimiento.getUsuario(),
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
					final PlanEntity plan = procedimientoEntity.getPlan();
					final Optional<PlanEntity> planBD = planRepository.findById(plan.getIdPlan());

					if (!planBD.isPresent()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procedimiento.getUsuario(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new PlanNoExisteException(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()));
					} else {
						procedimientoEntity.setPlan(planBD.get());
						procedimientoEntity.setBorradoProcedimiento(0);
						procedimientoRepository.saveAndFlush(procedimientoEntity);

						final String[] arrayNoticia = fechaIntroducidaUsuario.split("-");
						fechaDescripNoticia = arrayNoticia[2] + "-" + arrayNoticia[1] + "-" + arrayNoticia[0];

						if (Boolean.TRUE.equals(procedimientoEntity.getCheckUsuario())) {
							final NoticiasEntity noticia = new NoticiasEntity(
									String.format(Constantes.TITULO_ANADIR_NOTICIA_PROCEDIMIENTO,
											procedimientoEntity.getNombreProcedimiento()),
									String.format(Constantes.TEXTO_ANADIR_NOTICIA_PROCEDIMIENTO,
											procedimientoEntity.getPlan().getNombrePlan(),
											procedimientoEntity.getNombreProcedimiento(),
											procedimientoEntity.getDescripProcedimiento(), fechaDescripNoticia),
									new Date());
							noticiasRepository.saveAndFlush(noticia);
						}

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(procedimiento.getUsuario(),
								Constantes.ACCION_AÑADIR_PROCEDIMIENTO,
								procedimiento.getProcedimientoEntity().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}

						resultado = Constantes.OK;
					}

				}

			}
		} else {
			resultado = CodeMessageErrors.PROCEDIMIENTO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminaProcedimiento(final Procedimiento procedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcedimientoNoExisteException,
			ProcesoAsociadoProcedimientoException, UsuarioAsociadoProcedimientoException {
		final ProcedimientoEntity procedimientoEntity = procedimiento.getProcedimientoEntity();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
				.findById(procedimientoEntity.getIdProcedimiento());

		if (!procedimientoBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(procedimiento.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ProcedimientoNoExisteException(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			final List<Integer> idsProcesos = procesoProcedimientoRepository
					.findIdProcesoByIdProcedimiento(procedimientoEntity.getIdProcedimiento());

			if (!idsProcesos.isEmpty()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(procedimiento.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcesoAsociadoProcedimientoException(
						CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()));
			} else {
				final List<ProcedimientoUsuarioEntity> procedimientoUsuario = procedimientoUsuarioRepository
						.findProcedimientoUsuarioByProcedimiento(procedimientoEntity);

				if (!procedimientoUsuario.isEmpty()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procedimiento.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.USUARIO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()),
							CodeMessageErrors.USUARIO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new UsuarioAsociadoProcedimientoException(
							CodeMessageErrors.USUARIO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.USUARIO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()));
				} else {
					procedimientoBD.get().setBorradoProcedimiento(1);
					procedimientoRepository.saveAndFlush(procedimientoBD.get());
					procedimiento.setProcedimientoEntity(procedimientoBD.get());
					resultado = Constantes.OK;
				}

			}
		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarProcedimiento(final Procedimiento procedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcedimientoNoExisteException,
			FechaAnteriorFechaActualException, PlanNoExisteException, ParseException {
		final ProcedimientoEntity procedimientoEntity = procedimiento.getProcedimientoEntity();
		final Boolean procedimientoValido = validaciones.comprobarProcedimientoBlank(procedimientoEntity);

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String fechaIntroducidaUsuario = StringUtils.EMPTY;
		String fechaActualString = StringUtils.EMPTY;
		String fechaProcedimientoString = StringUtils.EMPTY;

		if (procedimientoValido) {
			final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
					.findById(procedimientoEntity.getIdProcedimiento());

			if (!procedimientoBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(procedimiento.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoNoExisteException(
						CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {

				final LocalDate fechaActual = LocalDate.now();
				final LocalDate dateIntroducidaUsuario = procedimiento.getProcedimientoEntity().getFechaProcedimiento()
						.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				final LocalDate dateProcedimientoBD = procedimientoBD.get().getFechaProcedimiento().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();

				if (CommonUtilities.countDigit(dateIntroducidaUsuario.getDayOfMonth()) == 1) {
					fechaIntroducidaUsuario = dateIntroducidaUsuario.getYear() + "-0"
							+ dateIntroducidaUsuario.getMonthValue() + "-0" + dateIntroducidaUsuario.getDayOfMonth();
				} else {
					fechaIntroducidaUsuario = dateIntroducidaUsuario.getYear() + "-0"
							+ dateIntroducidaUsuario.getMonthValue() + "-" + dateIntroducidaUsuario.getDayOfMonth();
				}

				if (CommonUtilities.countDigit(fechaActual.getDayOfMonth()) == 1) {
					fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-0"
							+ fechaActual.getDayOfMonth();
				} else {
					fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
							+ fechaActual.getDayOfMonth();
				}

				if (CommonUtilities.countDigit(dateProcedimientoBD.getDayOfMonth()) == 1) {
					fechaProcedimientoString = dateProcedimientoBD.getYear() + "-0"
							+ dateProcedimientoBD.getMonthValue() + "-0" + dateProcedimientoBD.getDayOfMonth();
				} else {
					fechaProcedimientoString = dateProcedimientoBD.getYear() + "-0"
							+ dateProcedimientoBD.getMonthValue() + "-" + dateProcedimientoBD.getDayOfMonth();
				}

				if (!fechaIntroducidaUsuario.equals(fechaProcedimientoString)) {
					if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procedimiento.getUsuario(),
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
					final PlanEntity plan = procedimientoEntity.getPlan();
					final Optional<PlanEntity> planBD = planRepository.findById(plan.getIdPlan());

					if (!planBD.isPresent()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procedimiento.getUsuario(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new PlanNoExisteException(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.PLAN_NO_EXISTE_EXCEPTION.getCodigo()));
					} else {
						procedimientoBD.get().setNombreProcedimiento(procedimientoEntity.getNombreProcedimiento());
						procedimientoBD.get().setDescripProcedimiento(procedimientoEntity.getDescripProcedimiento());
						procedimientoBD.get().setFechaProcedimiento(procedimientoEntity.getFechaProcedimiento());
						procedimientoBD.get().setBorradoProcedimiento(procedimientoEntity.getBorradoProcedimiento());
						procedimientoBD.get().setCheckUsuario(procedimientoEntity.getCheckUsuario());
						procedimientoBD.get().setPlan(procedimientoEntity.getPlan());

						procedimientoRepository.saveAndFlush(procedimientoBD.get());

						if (Boolean.FALSE.equals(procedimientoEntity.getCheckUsuario())) {
							final NoticiasEntity noticiaBD = noticiasRepository
									.findByTituloNoticia(String.format(Constantes.TITULO_ANADIR_NOTICIA_PROCEDIMIENTO,
											procedimientoEntity.getNombreProcedimiento()));

							if (noticiaBD != null) {
								noticiasRepository.deleteNoticia(noticiaBD.getIdNoticia());
							}
						}

						if (Boolean.TRUE.equals(procedimientoEntity.getCheckUsuario())) {
							final NoticiasEntity noticia = new NoticiasEntity(
									String.format(Constantes.TITULO_ANADIR_NOTICIA_PROCEDIMIENTO,
											procedimientoEntity.getNombreProcedimiento()),
									String.format(Constantes.TEXTO_ANADIR_NOTICIA_PROCEDIMIENTO,
											planBD.get().getNombrePlan(), procedimientoEntity.getNombreProcedimiento(),
											procedimientoEntity.getDescripProcedimiento(), fechaIntroducidaUsuario),
									new Date());
							noticiasRepository.saveAndFlush(noticia);
						}

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(procedimiento.getUsuario(),
								Constantes.ACCION_MODIFICAR_PROCEDIMIENTO,
								procedimiento.getProcedimientoEntity().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}

						resultado = Constantes.OK;
					}
				}

			}
		} else {
			resultado = CodeMessageErrors.PROCEDIMIENTO_VACIO.name();
		}
		return resultado;
	}

	@Override
	public String reactivarProcedimiento(final Procedimiento procedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcedimientoNoExisteException {
		final ProcedimientoEntity procedimientoEntity = procedimiento.getProcedimientoEntity();

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
				.findById(procedimientoEntity.getIdProcedimiento());

		if (!procedimientoBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(procedimiento.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ProcedimientoNoExisteException(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {

			procedimientoBD.get().setBorradoProcedimiento(0);
			procedimientoRepository.saveAndFlush(procedimientoBD.get());
			procedimiento.setProcedimientoEntity(procedimientoBD.get());
			resultado = Constantes.OK;
		}

		return resultado;
	}

	@Override
	public void deleteProcedimiento(final ProcedimientoEntity procedimientoEntity)
			throws ProcedimientoNoExisteException, ParseException {
		final Boolean procedimientoValido = validaciones.comprobarProcedimientoBlank(procedimientoEntity);

		if (procedimientoValido) {
			final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
					.findById(procedimientoEntity.getIdProcedimiento());

			if (!procedimientoBD.isPresent()) {
				throw new ProcedimientoNoExisteException(
						CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				procedimientoRepository.deleteProcedimiento(procedimientoEntity.getIdProcedimiento());
				procedimientoRepository.flush();
			}
		}
	}

}
