package com.sds.service.procesorespuestaposible.impl;

import java.text.ParseException;
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
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.model.compositekey.ProcesoRespuestaPosibleKey;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.ProcesoRespuestaPosibleRepository;
import com.sds.repository.RespuestaPosibleRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoRespuestaPosibleNoExisteException;
import com.sds.service.exception.ProcesoRespuestaPosibleYaExisteException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.log.LogService;
import com.sds.service.procesorespuestaposible.ProcesoRespuestaPosibleService;
import com.sds.service.procesorespuestaposible.model.ProcesoRespuestaPosible;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

public class ProcesoRespuestaPosibleServiceImpl implements ProcesoRespuestaPosibleService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	RespuestaPosibleRepository respuestaPosibleRepository;

	@Autowired
	ProcesoRespuestaPosibleRepository procesoRespuestaPosibleRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public ProcesoRespuestaPosibleServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public List<ProcesoRespuestaPosibleEntity> buscarProcesoRespuestaPosible(final Integer idProceso,
			final Integer idRespuesta) {
		final List<ProcesoRespuestaPosibleEntity> procesoRespuestaPosibleToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<ProcesoRespuestaPosibleEntity> procesosRespuestaPosibles = entityManager
				.createNamedQuery(Constantes.PROCESO_RESPUESTA_POSIBLE_FINDPROCESORESPUESTAPOSIBLE)
				.setParameter(Constantes.PROCESO_ID, idProceso)
				.setParameter(Constantes.RESPUESTA_POSIBLE_ID, idRespuesta).getResultList();

		if (!procesosRespuestaPosibles.isEmpty()) {
			for (final ProcesoRespuestaPosibleEntity procesoRespuestaPosible : procesosRespuestaPosibles) {
				final ProcesoRespuestaPosibleEntity procesoRespuestaPosibleEntity = new ProcesoRespuestaPosibleEntity(
						procesoRespuestaPosible.getIdProceso(), procesoRespuestaPosible.getIdRespuesta(),
						procesoRespuestaPosible.getFechaRespuesta());

				procesoRespuestaPosibleToret.add(procesoRespuestaPosibleEntity);

			}
		}

		datosBusqueda.add(Constantes.RESPUESTA_POSIBLE_ID + Constantes.DOS_PUNTOS + idRespuesta);
		datosBusqueda.add(Constantes.PROCESO_ID + Constantes.DOS_PUNTOS + idProceso);

		return procesoRespuestaPosibleToret;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirProcesoRespuestaPosible(final ProcesoRespuestaPosible procesoRespuestaPosible)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoNoExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException, ParseException {
		final ProcesoRespuestaPosibleEntity procesoRespuestaPosibleEntity = procesoRespuestaPosible
				.getProcesoRespuestaPosibleEntity();
		final Boolean procesoRespuestaPosibleValido = validaciones
				.comprobarProcesoRespuestaPosibleBlank(procesoRespuestaPosibleEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procesoRespuestaPosibleValido) {
			final ProcesoRespuestaPosibleKey procesoRespuestaPosibleKey = new ProcesoRespuestaPosibleKey(
					procesoRespuestaPosibleEntity.getIdProceso(), procesoRespuestaPosibleEntity.getIdRespuesta());

			final Optional<ProcesoRespuestaPosibleEntity> procesoRespuestaPosibleBD = procesoRespuestaPosibleRepository
					.findById(procesoRespuestaPosibleKey);

			if (procesoRespuestaPosibleBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procesoRespuestaPosible.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCESO_RESPUESTA_POSIBLE_YA_EXISTE.getCodigo()),
						CodeMessageErrors.PROCESO_RESPUESTA_POSIBLE_YA_EXISTE.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcesoRespuestaPosibleYaExisteException(
						CodeMessageErrors.PROCESO_RESPUESTA_POSIBLE_YA_EXISTE.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_RESPUESTA_POSIBLE_YA_EXISTE.getCodigo()));
			} else {
				final Optional<ProcesoEntity> procesoBD = procesoRepository
						.findById(procesoRespuestaPosibleEntity.getIdProceso());

				if (!procesoBD.isPresent()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procesoRespuestaPosible.getUsuario(),
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
					final Optional<RespuestaPosibleEntity> respuestaPosibleBD = respuestaPosibleRepository
							.findById(procesoRespuestaPosibleEntity.getIdRespuesta());

					if (!respuestaPosibleBD.isPresent()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procesoRespuestaPosible.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new RespuestaPosibleNoExisteException(
								CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()));
					} else {
						procesoRespuestaPosibleEntity.setFechaRespuesta(new Date());
						procesoRespuestaPosibleRepository.saveAndFlush(procesoRespuestaPosibleEntity);

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(
								procesoRespuestaPosible.getUsuario(), Constantes.ACCION_AÑADIR_PROCESO_RESPUESTA,
								procesoRespuestaPosible.getProcesoRespuestaPosibleEntity().toString());

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
			resultado = CodeMessageErrors.NIVEL_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminaProcesoRespuestaPosible(final ProcesoRespuestaPosible procesoRespuestaPosible)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoNoExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleNoExisteException, ParseException {
		String resultado = StringUtils.EMPTY;
		final ProcesoRespuestaPosibleEntity procesoRespuestaPosibleEntity = procesoRespuestaPosible
				.getProcesoRespuestaPosibleEntity();
		final Boolean procesoRespuestaPosibleValido = validaciones
				.comprobarProcesoRespuestaPosibleBlank(procesoRespuestaPosibleEntity);
		if (procesoRespuestaPosibleValido) {
			final ProcesoRespuestaPosibleKey procesoRespuestaPosibleKey = new ProcesoRespuestaPosibleKey(
					procesoRespuestaPosibleEntity.getIdProceso(), procesoRespuestaPosibleEntity.getIdRespuesta());

			final Optional<ProcesoRespuestaPosibleEntity> procesoRespuestaPosibleBD = procesoRespuestaPosibleRepository
					.findById(procesoRespuestaPosibleKey);

			if (!procesoRespuestaPosibleBD.isPresent()) {
				throw new ProcesoRespuestaPosibleNoExisteException(
						CodeMessageErrors.PROCESO_RESPUESTA_POSIBLE_NO_EXISTE.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_RESPUESTA_POSIBLE_NO_EXISTE.getCodigo()));
			} else {
				procesoRespuestaPosibleRepository.deleteProcesoRespuestaPosible(
						procesoRespuestaPosibleEntity.getIdRespuesta(), procesoRespuestaPosibleEntity.getIdProceso());
				procesoRespuestaPosibleRepository.flush();
				resultado = Constantes.OK;

			}
		}

		return resultado;
	}

}
