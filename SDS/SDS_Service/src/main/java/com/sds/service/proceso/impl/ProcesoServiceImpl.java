package com.sds.service.proceso.impl;

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
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcedimientoUsuarioProcesoRepository;
import com.sds.repository.ProcesoProcedimientoRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.ProcesoRespuestaPosibleRepository;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoAsociadoProcesoException;
import com.sds.service.exception.ProcesoAsociadoRespuestaPosibleException;
import com.sds.service.exception.ProcesoAsociadoUsuarioProcedimientoException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.proceso.ProcesoService;
import com.sds.service.proceso.model.Proceso;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class ProcesoServiceImpl implements ProcesoService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	ProcesoProcedimientoRepository procesoProcedimientoRepository;

	@Autowired
	ProcesoRespuestaPosibleRepository procesoRespuestaPosibleRepository;

	@Autowired
	ProcedimientoUsuarioProcesoRepository procedimientoUsuarioProcesoRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public ProcesoServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<ProcesoEntity> buscarProceso(final String nombreProceso, final String descripProceso,
			final Date fechaProceso, final int inicio, final int tamanhoPagina) {
		final List<ProcesoEntity> procesoToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();
		List<ProcesoEntity> procesos = new ArrayList<>();
		Integer numberTotalResults = 0;
		String fecha = StringUtils.EMPTY;

		if (fechaProceso != null) {
			java.sql.Date fechaSql;
			fechaSql = new java.sql.Date(fechaProceso.getTime());
			fecha = fechaSql.toString();
		} else {
			fecha = StringUtils.EMPTY;
		}

		procesos = entityManager.createNamedQuery(Constantes.PROCESO_QUERY_FINDPROCESO)
				.setParameter(Constantes.NOMBRE_PROCESO, nombreProceso)
				.setParameter(Constantes.DESCRIP_PROCESO, descripProceso).setParameter(Constantes.FECHA_PROCESO, fecha)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();
		numberTotalResults = procesoRepository.numberFindProceso(nombreProceso, descripProceso, fecha);

		if (!procesos.isEmpty()) {
			for (final ProcesoEntity proceso : procesos) {
				final ProcesoEntity procesoEntity = new ProcesoEntity(proceso.getIdProceso(),
						proceso.getNombreProceso(), proceso.getDescripProceso(), proceso.getFechaProceso(),
						proceso.getBorradoProceso());

				procesoToret.add(procesoEntity);

			}
		}

		datosBusqueda.add(Constantes.NOMBRE_PROCESO + Constantes.DOS_PUNTOS + nombreProceso);
		datosBusqueda.add(Constantes.DESCRIP_PROCESO + Constantes.DOS_PUNTOS + descripProceso);
		datosBusqueda.add(Constantes.FECHA_PROCESO + Constantes.DOS_PUNTOS + fecha);

		final ReturnBusquedas<ProcesoEntity> result = new ReturnBusquedas<>(procesoToret, datosBusqueda,
				numberTotalResults, procesoToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<ProcesoEntity> buscarTodos(final int inicio, final int tamanhoPagina) {
		final List<ProcesoEntity> procesoToret = new ArrayList<>();
		final List<ProcesoEntity> procesos = entityManager.createNamedQuery(Constantes.PROCESO_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = procesoRepository.numberFindAllProcesos();

		if (!procesos.isEmpty()) {
			for (final ProcesoEntity proceso : procesos) {
				final ProcesoEntity procesoEntity = new ProcesoEntity(proceso.getIdProceso(),
						proceso.getNombreProceso(), proceso.getDescripProceso(), proceso.getFechaProceso(),
						proceso.getBorradoProceso());
				procesoToret.add(procesoEntity);

			}
		}

		final ReturnBusquedas<ProcesoEntity> result = new ReturnBusquedas<>(procesoToret, numberTotalResults,
				procesoToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<ProcesoEntity> buscarProcesosEliminados(final int inicio, final int tamanhoPagina) {
		final List<ProcesoEntity> procesoToret = new ArrayList<>();
		final List<ProcesoEntity> procesos = entityManager.createNamedQuery(Constantes.PROCESO_QUERY_FINDELIMINADOS)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = procesoRepository.numberFindAllProcesos();

		if (!procesos.isEmpty()) {
			for (final ProcesoEntity proceso : procesos) {
				final ProcesoEntity procesoEntity = new ProcesoEntity(proceso.getIdProceso(),
						proceso.getNombreProceso(), proceso.getDescripProceso(), proceso.getFechaProceso(),
						proceso.getBorradoProceso());
				procesoToret.add(procesoEntity);

			}
		}

		final ReturnBusquedas<ProcesoEntity> result = new ReturnBusquedas<>(procesoToret, numberTotalResults,
				procesoToret.size(), inicio);

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirProceso(final Proceso proceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoYaExisteException,
			ParseException, FechaAnteriorFechaActualException {
		final ProcesoEntity procesoEntity = proceso.getProceso();
		final Boolean procesoValido = validaciones.comprobarProcesoBlank(procesoEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String fechaIntroducidaUsuario = StringUtils.EMPTY;
		String fechaActualString = StringUtils.EMPTY;

		if (procesoValido) {
			final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(procesoEntity.getNombreProceso());

			if (procesoBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(proceso.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCESO_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCESO_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcesoYaExisteException(CodeMessageErrors.PROCESO_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCESO_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final LocalDate fechaActual = LocalDate.now();
				final LocalDate dateIntroducidaUsuario = proceso.getProceso().getFechaProceso().toInstant()
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
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(proceso.getUsuario(),
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
					procesoEntity.setBorradoProceso(0);
					procesoRepository.saveAndFlush(procesoEntity);

					final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(proceso.getUsuario(),
							Constantes.ACCION_AÑADIR_PROCESO, proceso.getProceso().toString());

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
			resultado = CodeMessageErrors.PROCESO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminaProceso(final Proceso proceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoNoExisteException,
			ParseException, FechaAnteriorFechaActualException, ProcedimientoAsociadoProcesoException,
			ProcesoAsociadoRespuestaPosibleException, ProcesoAsociadoUsuarioProcedimientoException {
		final ProcesoEntity procesoEntity = proceso.getProceso();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<ProcesoEntity> procesoBD = procesoRepository.findById(procesoEntity.getIdProceso());

		if (!procesoBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(proceso.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ProcesoNoExisteException(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {

			final List<Integer> procesosProcedimientos = procesoProcedimientoRepository
					.findIdProcedimientoByIdProceso(procesoEntity.getIdProceso());

			if (!procesosProcedimientos.isEmpty()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(proceso.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoAsociadoProcesoException(
						CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()));
			} else {
				final List<ProcesoRespuestaPosibleEntity> procesoRespuestaPosible = procesoRespuestaPosibleRepository
						.findRespuestaPosibleByIdProceso(procesoEntity.getIdProceso());

				if (!procesoRespuestaPosible.isEmpty()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(proceso.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCESO_ASOCIADO_RESPUESTA_POSIBLE_EXCEPTION.getCodigo()),
							CodeMessageErrors.PROCESO_ASOCIADO_RESPUESTA_POSIBLE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new ProcesoAsociadoRespuestaPosibleException(
							CodeMessageErrors.PROCESO_ASOCIADO_RESPUESTA_POSIBLE_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCESO_ASOCIADO_RESPUESTA_POSIBLE_EXCEPTION.getCodigo()));
				} else {
					final List<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProceso = procedimientoUsuarioProcesoRepository
							.findProcedimientoUsuarioProcesoByIdProceso(procesoEntity.getIdProceso());

					if (!procedimientoUsuarioProceso.isEmpty()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								proceso.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo()),
								CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new ProcesoAsociadoUsuarioProcedimientoException(
								CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION
												.getCodigo()));
					} else {
						procesoBD.get().setBorradoProceso(1);
						proceso.setProceso(procesoEntity);
						resultado = modificarProceso(proceso);
					}
				}

			}

		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarProceso(final Proceso proceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoNoExisteException,
			ParseException, FechaAnteriorFechaActualException {
		final ProcesoEntity procesoEntity = proceso.getProceso();
		final Boolean procesoValido = validaciones.comprobarProcesoBlank(procesoEntity);

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String fechaIntroducidaUsuario = StringUtils.EMPTY;
		String fechaActualString = StringUtils.EMPTY;
		String fechaProcesoString = StringUtils.EMPTY;

		if (procesoValido) {
			final Optional<ProcesoEntity> procesoBD = procesoRepository.findById(procesoEntity.getIdProceso());

			if (!procesoBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(proceso.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcesoNoExisteException(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {

				final LocalDate fechaActual = LocalDate.now();
				final LocalDate dateIntroducidaUsuario = proceso.getProceso().getFechaProceso().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();
				final LocalDate dateProcesoBD = procesoBD.get().getFechaProceso().toInstant()
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

				if (CommonUtilities.countDigit(dateProcesoBD.getDayOfMonth()) == 1) {
					fechaProcesoString = dateProcesoBD.getYear() + "-0" + dateProcesoBD.getMonthValue() + "-0"
							+ dateProcesoBD.getDayOfMonth();
				} else {
					fechaProcesoString = dateProcesoBD.getYear() + "-0" + dateProcesoBD.getMonthValue() + "-"
							+ dateProcesoBD.getDayOfMonth();
				}

				if (!fechaIntroducidaUsuario.equals(fechaProcesoString)) {
					if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								proceso.getUsuario(),
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
					procesoBD.get().setNombreProceso(procesoEntity.getNombreProceso());
					procesoBD.get().setDescripProceso(procesoEntity.getDescripProceso());
					procesoBD.get().setFechaProceso(procesoEntity.getFechaProceso());
					procesoBD.get().setBorradoProceso(procesoEntity.getBorradoProceso());

					procesoRepository.saveAndFlush(procesoBD.get());

					final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(proceso.getUsuario(),
							Constantes.ACCION_MODIFICAR_PROCESO, proceso.getProceso().toString());

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
			resultado = CodeMessageErrors.PROCESO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String reactivarProceso(final Proceso proceso) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, ProcesoNoExisteException, ParseException {
		final ProcesoEntity procesoEntity = proceso.getProceso();

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<ProcesoEntity> procesoBD = procesoRepository.findById(procesoEntity.getIdProceso());

		if (!procesoBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(proceso.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ProcesoNoExisteException(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			procesoBD.get().setBorradoProceso(0);
			procesoRepository.saveAndFlush(procesoBD.get());
			proceso.setProceso(procesoBD.get());
			resultado = Constantes.OK;
		}

		return resultado;
	}

	@Override
	public void deleteProceso(final ProcesoEntity proceso) throws ProcesoNoExisteException, ParseException {
		final Boolean procesoValido = validaciones.comprobarProcesoBlank(proceso);

		if (procesoValido) {
			final Optional<ProcesoEntity> procesoBD = procesoRepository.findById(proceso.getIdProceso());

			if (!procesoBD.isPresent()) {
				throw new ProcesoNoExisteException(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				procesoRepository.deleteProceso(proceso.getIdProceso());
				procesoRepository.flush();
			}
		}
	}

}
