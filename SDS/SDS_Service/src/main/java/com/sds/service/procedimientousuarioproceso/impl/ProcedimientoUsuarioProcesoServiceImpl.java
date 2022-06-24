package com.sds.service.procedimientousuarioproceso.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.repository.ProcedimientoUsuarioProcesoRepository;
import com.sds.repository.ProcedimientoUsuarioRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.RespuestaPosibleRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoUsuarioNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoYaExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.log.LogService;
import com.sds.service.procedimientousuarioproceso.ProcedimientoUsuarioProcesoService;
import com.sds.service.procedimientousuarioproceso.model.ProcedimientoUsuarioProceso;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

public class ProcedimientoUsuarioProcesoServiceImpl implements ProcedimientoUsuarioProcesoService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ProcedimientoUsuarioProcesoRepository procedimientoUsuarioProcesoRepository;

	@Autowired
	ProcedimientoUsuarioRepository procedimientoUsuarioRepository;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	RespuestaPosibleRepository respuestaPosibleRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public ProcedimientoUsuarioProcesoServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<ProcedimientoUsuarioProcesoEntity> buscarTodos() {
		final List<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesoToret = new ArrayList<>();

		final List<ProcedimientoUsuarioProcesoEntity> procedimientosUsuariosProcesos = entityManager
				.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIOPROCESO_FINDALLPROCEDIMIENTOUSUARIOPROCESO)
				.getResultList();

		final Integer numberTotalResults = procedimientoUsuarioProcesoRepository
				.numberFindAllProcedimientosUsuariosProcesos();

		if (!procedimientosUsuariosProcesos.isEmpty()) {
			for (final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso : procedimientosUsuariosProcesos) {
				final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoEntity = new ProcedimientoUsuarioProcesoEntity(
						procedimientoUsuarioProceso.getFechaProcedimientoUsuarioProceso(),
						procedimientoUsuarioProceso.getBorradoProcedimientoUsuarioProceso(),
						procedimientoUsuarioProceso.getRespuestaPosible(), procedimientoUsuarioProceso.getProceso(),
						procedimientoUsuarioProceso.getProcedimientoUsuario());

				procedimientoUsuarioProcesoToret.add(procedimientoUsuarioProcesoEntity);
			}
		}
		final ReturnBusquedas<ProcedimientoUsuarioProcesoEntity> result = new ReturnBusquedas<>(
				procedimientoUsuarioProcesoToret, numberTotalResults, procedimientoUsuarioProcesoToret.size(), 0);

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirProcedimientoUsuarioProceso(final ProcedimientoUsuarioProceso procedimientoUsuarioProceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcedimientoUsuarioProcesoYaExisteException, ProcedimientoUsuarioNoExisteException,
			ProcesoNoExisteException, RespuestaPosibleNoExisteException {
		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoEntity = procedimientoUsuarioProceso
				.getProcedimientoUsuarioProceso();
		final Boolean procedimientoUsuarioProcesoValido = validaciones
				.comprobarProcedimientoUsuarioProcesoBlank(procedimientoUsuarioProcesoEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procedimientoUsuarioProcesoValido) {
			final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
					.findProcedimientoUsuarioProceso(procedimientoUsuarioProcesoEntity.getProcedimientoUsuario(),
							procedimientoUsuarioProcesoEntity.getProceso());

			if (procedimientoUsuarioProcesoBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procedimientoUsuarioProceso.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoUsuarioProcesoYaExisteException(
						CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final Optional<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
						.findById(procedimientoUsuarioProcesoEntity.getProcedimientoUsuario()
								.getIdProcedimientoUsuario());

				if (!procedimientoUsuarioBD.isPresent()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procedimientoUsuarioProceso.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()),
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new ProcedimientoUsuarioNoExisteException(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
				} else {
					final Optional<ProcesoEntity> procesoBD = procesoRepository.findById(
							procedimientoUsuarioProceso.getProcedimientoUsuarioProceso().getProceso().getIdProceso());

					if (!procesoBD.isPresent()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procedimientoUsuarioProceso.getUsuario(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new ProcesoNoExisteException(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()));

					} else {
						final Optional<RespuestaPosibleEntity> respuestaPosibleBD = respuestaPosibleRepository
								.findById(procedimientoUsuarioProceso.getProcedimientoUsuarioProceso()
										.getRespuestaPosible().getIdRespuesta());

						if (!respuestaPosibleBD.isPresent()) {
							final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
									procedimientoUsuarioProceso.getUsuario(),
									CodeMessageErrors.getTipoNameByCodigo(
											CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()),
									CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo());

							resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

							if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
								throw new LogExcepcionesNoGuardadoException(
										CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
										CodeMessageErrors.getTipoNameByCodigo(
												CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
							}

							throw new RespuestaPosibleNoExisteException(
									CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo(),
									CodeMessageErrors.getTipoNameByCodigo(
											CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()));
						} else {
							procedimientoUsuarioProcesoEntity.setBorradoProcedimientoUsuarioProceso(0);
							procedimientoUsuarioProcesoEntity.setFechaProcedimientoUsuarioProceso(new Date());
							procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProcesoEntity);

							final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(
									procedimientoUsuarioProceso.getUsuario(),
									Constantes.ACCION_AÑADIR_PROCEDIMIENTOUSUARIO,
									procedimientoUsuarioProceso.getProcedimientoUsuarioProceso().toString());

							resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

							if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
								throw new LogAccionesNoGuardadoException(
										CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(), CodeMessageErrors
												.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
							}

							resultado = Constantes.OK;
						}

					}

				}
			}
		} else {
			resultado = CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarProcedimientoUsuarioProceso(final ProcedimientoUsuarioProceso procedimientoUsuarioProceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcedimientoUsuarioProcesoNoExisteException, ProcedimientoUsuarioNoExisteException,
			ProcesoNoExisteException, RespuestaPosibleNoExisteException {
		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoEntity = procedimientoUsuarioProceso
				.getProcedimientoUsuarioProceso();
		final Boolean procedimientoUsuarioProcesoValido = validaciones
				.comprobarProcedimientoUsuarioProcesoBlank(procedimientoUsuarioProcesoEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procedimientoUsuarioProcesoValido) {
			final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
					.findProcedimientoUsuarioProceso(procedimientoUsuarioProcesoEntity.getProcedimientoUsuario(),
							procedimientoUsuarioProcesoEntity.getProceso());

			if (procedimientoUsuarioProcesoBD == null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procedimientoUsuarioProceso.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoUsuarioProcesoNoExisteException(
						CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final Optional<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
						.findById(procedimientoUsuarioProcesoEntity.getProcedimientoUsuario()
								.getIdProcedimientoUsuario());

				if (!procedimientoUsuarioBD.isPresent()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procedimientoUsuarioProceso.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()),
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new ProcedimientoUsuarioNoExisteException(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
				} else {
					final Optional<ProcesoEntity> procesoBD = procesoRepository.findById(
							procedimientoUsuarioProceso.getProcedimientoUsuarioProceso().getProceso().getIdProceso());

					if (!procesoBD.isPresent()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procedimientoUsuarioProceso.getUsuario(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new ProcesoNoExisteException(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()));

					} else {
						final Optional<RespuestaPosibleEntity> respuestaPosibleBD = respuestaPosibleRepository
								.findById(procedimientoUsuarioProceso.getProcedimientoUsuarioProceso()
										.getRespuestaPosible().getIdRespuesta());

						if (!respuestaPosibleBD.isPresent()) {
							final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
									procedimientoUsuarioProceso.getUsuario(),
									CodeMessageErrors.getTipoNameByCodigo(
											CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()),
									CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo());

							resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

							if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
								throw new LogExcepcionesNoGuardadoException(
										CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
										CodeMessageErrors.getTipoNameByCodigo(
												CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
							}

							throw new RespuestaPosibleNoExisteException(
									CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo(),
									CodeMessageErrors.getTipoNameByCodigo(
											CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()));
						} else {
							procedimientoUsuarioProcesoBD.setProcedimientoUsuario(procedimientoUsuarioBD.get());
							procedimientoUsuarioProcesoBD.setRespuestaPosible(respuestaPosibleBD.get());
							procedimientoUsuarioProcesoBD.setProceso(procesoBD.get());
							procedimientoUsuarioProcesoBD.setBorradoProcedimientoUsuarioProceso(
									procedimientoUsuarioProcesoEntity.getBorradoProcedimientoUsuarioProceso());
							procedimientoUsuarioProcesoEntity.setFechaProcedimientoUsuarioProceso(new Date());
							procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProcesoEntity);

							final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(
									procedimientoUsuarioProceso.getUsuario(),
									Constantes.ACCION_AÑADIR_PROCEDIMIENTOUSUARIO,
									procedimientoUsuarioProceso.getProcedimientoUsuarioProceso().toString());

							resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

							if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
								throw new LogAccionesNoGuardadoException(
										CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(), CodeMessageErrors
												.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
							}

							resultado = Constantes.OK;
						}

					}

				}
			}
		} else {
			resultado = CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminaProcedimientoUsuarioProceso(final ProcedimientoUsuarioProceso procedimientoUsuarioProceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcedimientoUsuarioProcesoNoExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoEntity = procedimientoUsuarioProceso
				.getProcedimientoUsuarioProceso();

		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
				.findProcedimientoUsuarioProceso(procedimientoUsuarioProcesoEntity.getProcedimientoUsuario(),
						procedimientoUsuarioProcesoEntity.getProceso());

		if (procedimientoUsuarioProcesoBD == null) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
					procedimientoUsuarioProceso.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ProcedimientoUsuarioProcesoNoExisteException(
					CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			procedimientoUsuarioProcesoBD.setBorradoProcedimientoUsuarioProceso(1);
			procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProcesoBD);

			final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(procedimientoUsuarioProceso.getUsuario(),
					Constantes.ACCION_ELIMINAR_PROCEDIMIENTOUSUARIOPROCESO, procedimientoUsuarioProceso.toString());

			resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

			if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
			}

			resultado = Constantes.OK;
		}
		return resultado;
	}

	@Override
	public void deleteProcedimientoUsuarioProceso(
			final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoEntity)
			throws ProcedimientoUsuarioProcesoNoExisteException {
		final Boolean procedimientoUsuarioProcesoValido = validaciones
				.comprobarProcedimientoUsuarioProcesoBlank(procedimientoUsuarioProcesoEntity);

		if (procedimientoUsuarioProcesoValido) {
			final Optional<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
					.findById(procedimientoUsuarioProcesoEntity.getIdProcedimientoUsuarioProceso());

			if (!procedimientoUsuarioProcesoBD.isPresent()) {
				throw new ProcedimientoUsuarioProcesoNoExisteException(
						CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
						procedimientoUsuarioProcesoEntity.getIdProcedimientoUsuarioProceso());
				procesoRepository.flush();
			}
		}

	}

}
