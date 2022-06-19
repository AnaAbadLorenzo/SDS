package com.sds.service.procesoprocedimiento.impl;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoProcedimientoEntity;
import com.sds.model.compositekey.ProcesoProcedimientoKey;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcesoProcedimientoRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.procesoprocedimiento.ProcesoProcedimientoService;
import com.sds.service.procesoprocedimiento.model.ProcesoProcedimiento;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class ProcesoProcedimientoServiceImpl implements ProcesoProcedimientoService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	ProcesoProcedimientoRepository procesoProcedimientoRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public ProcesoProcedimientoServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public List<ProcesoProcedimientoEntity> buscarProcesoProcedimiento(final Integer idProceso,
			final Integer idProcedimiento) {
		final List<ProcesoProcedimientoEntity> procesoProcedimientoToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<ProcesoProcedimientoEntity> procesoProcedimientos = entityManager
				.createNamedQuery(Constantes.PROCESOPROCEDIMIENTO_QUERY_FINDPROCESOPROCEDIMIENTO)
				.setParameter(Constantes.PROCEDIMIENTO_ID, idProcedimiento)
				.setParameter(Constantes.PROCESO_ID, idProceso).getResultList();

		if (!procesoProcedimientos.isEmpty()) {
			for (final ProcesoProcedimientoEntity procesoProcedimiento : procesoProcedimientos) {
				final ProcesoProcedimientoEntity procesoProcedimientoEntity = new ProcesoProcedimientoEntity(
						procesoProcedimiento.getIdProceso(), procesoProcedimiento.getIdProcedimiento(),
						procesoProcedimiento.getOrdenProceso());
				procesoProcedimientoToret.add(procesoProcedimientoEntity);

			}
		}

		datosBusqueda.add(Constantes.PROCEDIMIENTO_ID + Constantes.DOS_PUNTOS + idProcedimiento);
		datosBusqueda.add(Constantes.PROCESO_ID + Constantes.DOS_PUNTOS + idProceso);

		return procesoProcedimientoToret;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException, ProcesoNoExisteException,
			ParseException {
		final ProcesoProcedimientoEntity procesoProcedimientoEntity = procesoProcedimiento
				.getProcesoProcedimientoEntity();
		final Boolean procesoProcedimientoValido = validaciones
				.comprobarProcesoProcedimientoBlank(procesoProcedimientoEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procesoProcedimientoValido) {
			final ProcesoProcedimientoKey procesoProcedimientoKey = new ProcesoProcedimientoKey(
					procesoProcedimientoEntity.getIdProceso(), procesoProcedimientoEntity.getIdProcedimiento());

			final Optional<ProcesoProcedimientoEntity> procesoProcedimientoBD = procesoProcedimientoRepository
					.findById(procesoProcedimientoKey);

			if (procesoProcedimientoBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procesoProcedimiento.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCESO_PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcesoProcedimientoYaExisteException(
						CodeMessageErrors.PROCESO_PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final Optional<ProcesoEntity> procesoBD = procesoRepository
						.findById(procesoProcedimientoEntity.getIdProceso());

				if (procesoBD != null) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procesoProcedimiento.getUsuario(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
							CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new ProcesoNoExisteException(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()));
				} else {
					final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
							.findById(procesoProcedimientoEntity.getIdProcedimiento());
					if (procedimientoBD != null) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procesoProcedimiento.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new ProcedimientoNoExisteException(
								CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
					} else {
						procesoProcedimientoRepository.saveAndFlush(procesoProcedimientoEntity);

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(
								procesoProcedimiento.getUsuario(), Constantes.ACCION_AÑADIR_PROCESOPROCEDIMIENTO,
								procesoProcedimiento.getProcesoProcedimientoEntity().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}
					}
				}
			}

		} else {
			resultado = CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoNoExisteException, ProcedimientoNoExisteException, ProcesoNoExisteException,
			ParseException {
		final ProcesoProcedimientoEntity procesoProcedimientoEntity = procesoProcedimiento
				.getProcesoProcedimientoEntity();
		final Boolean procesoProcedimientoValido = validaciones
				.comprobarProcesoProcedimientoBlank(procesoProcedimientoEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procesoProcedimientoValido) {
			final ProcesoProcedimientoKey procesoProcedimientoKey = new ProcesoProcedimientoKey(
					procesoProcedimientoEntity.getIdProceso(), procesoProcedimientoEntity.getIdProcedimiento());

			final Optional<ProcesoProcedimientoEntity> procesoProcedimientoBD = procesoProcedimientoRepository
					.findById(procesoProcedimientoKey);

			if (procesoProcedimientoBD == null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procesoProcedimiento.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcesoProcedimientoNoExisteException(
						CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final Optional<ProcesoEntity> procesoBD = procesoRepository
						.findById(procesoProcedimientoEntity.getIdProceso());

				if (procesoBD != null) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procesoProcedimiento.getUsuario(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()),
							CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new ProcesoNoExisteException(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.PROCESO_NO_EXISTE_EXCEPTION.getCodigo()));
				} else {
					final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
							.findById(procesoProcedimientoEntity.getIdProcedimiento());
					if (procedimientoBD != null) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procesoProcedimiento.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new ProcedimientoNoExisteException(
								CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
					} else {
						procesoProcedimientoBD.get().setOrdenProceso(procesoProcedimientoEntity.getOrdenProceso());
						procesoProcedimientoRepository.saveAndFlush(procesoProcedimientoBD.get());

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(
								procesoProcedimiento.getUsuario(), Constantes.ACCION_MODIFICAR_PROCESOPROCEDIMIENTO,
								procesoProcedimiento.getProcesoProcedimientoEntity().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}
					}
				}
			}

		} else {
			resultado = CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminaProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoNoExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final ProcesoProcedimientoEntity procesoProcedimientoEntity = procesoProcedimiento
				.getProcesoProcedimientoEntity();
		final ProcesoProcedimientoKey procesoProcedimientoKey = new ProcesoProcedimientoKey(
				procesoProcedimientoEntity.getIdProceso(), procesoProcedimientoEntity.getIdProcedimiento());
		final Optional<ProcesoProcedimientoEntity> procesoProcedimientoBD = procesoProcedimientoRepository
				.findById(procesoProcedimientoKey);

		if (!procesoProcedimientoBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
					procesoProcedimiento.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ProcesoProcedimientoNoExisteException(
					CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));

		} else {
			procesoProcedimientoRepository.deleteProcesoProcedimiento(procesoProcedimientoEntity.getIdProceso(),
					procesoProcedimientoEntity.getIdProcedimiento());

			final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(procesoProcedimiento.getUsuario(),
					Constantes.ACCION_ELIMINAR_PROCESOPROCEDIMIENTO, procesoProcedimiento.toString());

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
	public void deleteProcesoProcedimiento(final ProcesoProcedimientoEntity procesoProcedimientoEntity)
			throws ProcesoProcedimientoNoExisteException, ParseException {
		final Boolean procesoProcedimientoValido = validaciones
				.comprobarProcesoProcedimientoBlank(procesoProcedimientoEntity);
		if (procesoProcedimientoValido) {
			final ProcesoProcedimientoKey procesoProcedimientoKey = new ProcesoProcedimientoKey(
					procesoProcedimientoEntity.getIdProceso(), procesoProcedimientoEntity.getIdProcedimiento());
			final Optional<ProcesoProcedimientoEntity> procesoProcedimientoBD = procesoProcedimientoRepository
					.findById(procesoProcedimientoKey);

			if (!procesoProcedimientoBD.isPresent()) {
				throw new ProcesoProcedimientoNoExisteException(
						CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				procesoProcedimientoRepository.deleteProcesoProcedimiento(procesoProcedimientoEntity.getIdProceso(),
						procesoProcedimientoEntity.getIdProcedimiento());
				procesoProcedimientoRepository.flush();
			}
		}

	}

}
