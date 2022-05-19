package com.sds.service.respuestaposible.impl;

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
import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.repository.ProcesoRespuestaPosibleRepository;
import com.sds.repository.RespuestaPosibleRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RespuestaPosibleAsociadaProcesoException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.exception.RespuestaPosibleYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.respuestaposible.RespuestaPosibleService;
import com.sds.service.respuestaposible.model.RespuestaPosible;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class RespuestaPosibleServiceImpl implements RespuestaPosibleService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	RespuestaPosibleRepository respuestaPosibleRepository;

	@Autowired
	ProcesoRespuestaPosibleRepository procesoRespuestaPosibleRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public RespuestaPosibleServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<RespuestaPosibleEntity> buscarRespuestaPosible(final String textoRespuesta, final int inicio,
			final int tamanhoPagina) {
		final List<RespuestaPosibleEntity> respuestaPosibleToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<RespuestaPosibleEntity> respuestasPosibles = entityManager
				.createNamedQuery(Constantes.RESPUESTA_POSIBLE_QUERY_FINDRESPUESTAPOSIBLE)
				.setParameter(Constantes.TEXTO_RESPUESTA_POSIBLE, textoRespuesta).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = respuestaPosibleRepository.numberFindRespuestaPosible(textoRespuesta);

		if (!respuestasPosibles.isEmpty()) {
			for (final RespuestaPosibleEntity respuestaPosible : respuestasPosibles) {
				final RespuestaPosibleEntity respuesta = new RespuestaPosibleEntity(respuestaPosible.getIdRespuesta(),
						respuestaPosible.getTextoRespuesta(), respuestaPosible.getBorradoRespuesta());

				respuestaPosibleToret.add(respuesta);

			}
		}

		datosBusqueda.add(Constantes.TEXTO_RESPUESTA_POSIBLE + Constantes.DOS_PUNTOS + textoRespuesta);

		final ReturnBusquedas<RespuestaPosibleEntity> result = new ReturnBusquedas<>(respuestaPosibleToret,
				datosBusqueda, numberTotalResults, respuestaPosibleToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<RespuestaPosibleEntity> buscarTodas(final int inicio, final int tamanhoPagina) {
		final List<RespuestaPosibleEntity> respuestasPosiblesToret = new ArrayList<>();

		final List<RespuestaPosibleEntity> respuestasPosibles = entityManager
				.createNamedQuery(Constantes.RESPUESTA_POSIBLE_QUERY_FINDALL).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = respuestaPosibleRepository.numberFindAllRespuestasPosibles();

		if (!respuestasPosibles.isEmpty()) {
			for (final RespuestaPosibleEntity respuestaPosible : respuestasPosibles) {
				final RespuestaPosibleEntity respuesta = new RespuestaPosibleEntity(respuestaPosible.getIdRespuesta(),
						respuestaPosible.getTextoRespuesta(), respuestaPosible.getBorradoRespuesta());

				respuestasPosiblesToret.add(respuesta);
			}
		}
		final ReturnBusquedas<RespuestaPosibleEntity> result = new ReturnBusquedas<RespuestaPosibleEntity>(
				respuestasPosiblesToret, numberTotalResults, respuestasPosiblesToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<RespuestaPosibleEntity> buscarRespuestasPosiblesEliminadas(final int inicio,
			final int tamanhoPagina) {
		final List<RespuestaPosibleEntity> respuestasPosiblesToret = new ArrayList<>();

		final List<RespuestaPosibleEntity> respuestasPosibles = entityManager
				.createNamedQuery(Constantes.RESPUESTA_POSIBLE_QUERY_FINDELIMINADAS).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = respuestaPosibleRepository.numberFindRespuestasPosiblesEliminadas();
		if (!respuestasPosibles.isEmpty()) {
			for (final RespuestaPosibleEntity respuestaPosible : respuestasPosibles) {
				final RespuestaPosibleEntity respuesta = new RespuestaPosibleEntity(respuestaPosible.getIdRespuesta(),
						respuestaPosible.getTextoRespuesta(), respuestaPosible.getBorradoRespuesta());

				respuestasPosiblesToret.add(respuesta);
			}
		}
		final ReturnBusquedas<RespuestaPosibleEntity> result = new ReturnBusquedas<RespuestaPosibleEntity>(
				respuestasPosiblesToret, numberTotalResults, respuestasPosiblesToret.size(), inicio);

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirRespuestaPosible(final RespuestaPosible respuestaPosible)
			throws RespuestaPosibleYaExisteException, LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException {
		final RespuestaPosibleEntity respuestaPosibleEntity = respuestaPosible.getRespuestaPosibleEntity();
		final Boolean respuestaValida = validaciones
				.comprobarTextoRespuestaPosibleBlank(respuestaPosibleEntity.getTextoRespuesta());
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (respuestaValida) {
			final RespuestaPosibleEntity respuestaPosibleBD = respuestaPosibleRepository
					.findRespuestaPosibleByText(respuestaPosibleEntity.getTextoRespuesta());

			if (respuestaPosibleBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						respuestaPosible.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new RespuestaPosibleYaExisteException(
						CodeMessageErrors.RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				respuestaPosibleEntity.setBorradoRespuesta(0);
				respuestaPosibleRepository.saveAndFlush(respuestaPosibleEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(respuestaPosible.getUsuario(),
						Constantes.ACCION_AÑADIR_RESPUESTA, respuestaPosible.getRespuestaPosibleEntity().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			}
		} else {
			resultado = CodeMessageErrors.RESPUESTA_POSIBLE_VACIA.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminarRespuestaPosible(final RespuestaPosible respuestaPosible)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, RespuestaPosibleNoExisteException,
			RespuestaPosibleAsociadaProcesoException {
		final RespuestaPosibleEntity respuestaPosibleEntity = respuestaPosible.getRespuestaPosibleEntity();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<RespuestaPosibleEntity> respuestaPosibleBD = respuestaPosibleRepository
				.findById(respuestaPosibleEntity.getIdRespuesta());

		if (!respuestaPosibleBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(respuestaPosible.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new RespuestaPosibleNoExisteException(
					CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			final List<ProcesoRespuestaPosibleEntity> procesoRespuestaPosible = procesoRespuestaPosibleRepository
					.findProcesoByIdRespuestaPosible(respuestaPosible.getRespuestaPosibleEntity().getIdRespuesta());

			if (!procesoRespuestaPosible.isEmpty()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						respuestaPosible.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.RESPUESTA_POSIBLE_ASOCIADA_PROCESO.getCodigo()),
						CodeMessageErrors.RESPUESTA_POSIBLE_ASOCIADA_PROCESO.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new RespuestaPosibleAsociadaProcesoException(
						CodeMessageErrors.RESPUESTA_POSIBLE_ASOCIADA_PROCESO.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.RESPUESTA_POSIBLE_ASOCIADA_PROCESO.getCodigo()));

			} else {
				respuestaPosibleEntity.setBorradoRespuesta(1);

				respuestaPosible.setRespuestaPosibleEntity(respuestaPosibleEntity);

				modificarRespuestaPosible(respuestaPosible);
				resultado = Constantes.OK;
			}

		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarRespuestaPosible(final RespuestaPosible respuestaPosible)
			throws LogExcepcionesNoGuardadoException, RespuestaPosibleNoExisteException,
			LogAccionesNoGuardadoException {
		final RespuestaPosibleEntity respuestaPosibleEntity = respuestaPosible.getRespuestaPosibleEntity();
		final Boolean respuestaValida = validaciones
				.comprobarTextoRespuestaPosibleBlank(respuestaPosibleEntity.getTextoRespuesta());

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (respuestaValida) {
			final Optional<RespuestaPosibleEntity> respuestaPosibleBD = respuestaPosibleRepository
					.findById(respuestaPosibleEntity.getIdRespuesta());

			if (!respuestaPosibleBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						respuestaPosible.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new RespuestaPosibleNoExisteException(
						CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				respuestaPosibleBD.get().setTextoRespuesta(respuestaPosibleEntity.getTextoRespuesta());
				respuestaPosibleBD.get().setBorradoRespuesta(respuestaPosibleEntity.getBorradoRespuesta());

				respuestaPosibleRepository.saveAndFlush(respuestaPosibleBD.get());

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(respuestaPosible.getUsuario(),
						Constantes.ACCION_MODIFICAR_RESPUESTA, respuestaPosible.getRespuestaPosibleEntity().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			}
		} else {
			resultado = CodeMessageErrors.RESPUESTA_POSIBLE_VACIA.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String reactivarRespuestaPosible(final RespuestaPosible respuestaPosible)
			throws LogExcepcionesNoGuardadoException, RespuestaPosibleNoExisteException,
			LogAccionesNoGuardadoException {
		final RespuestaPosibleEntity respuestaPosibleEntity = respuestaPosible.getRespuestaPosibleEntity();

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<RespuestaPosibleEntity> respuestaPosibleBD = respuestaPosibleRepository
				.findById(respuestaPosibleEntity.getIdRespuesta());

		if (!respuestaPosibleBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(respuestaPosible.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new RespuestaPosibleNoExisteException(
					CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			respuestaPosibleEntity.setBorradoRespuesta(1);
			respuestaPosible.setRespuestaPosibleEntity(respuestaPosibleEntity);
			resultado = modificarRespuestaPosible(respuestaPosible);

		}

		return resultado;

	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void deleteRespuestaPosible(final RespuestaPosibleEntity respuestaPosible)
			throws RespuestaPosibleNoExisteException {
		final Boolean respuestaPosibleValida = validaciones
				.comprobarTextoRespuestaPosibleBlank(respuestaPosible.getTextoRespuesta());

		if (respuestaPosibleValida) {
			final Optional<RespuestaPosibleEntity> respuestaPosibleBD = respuestaPosibleRepository
					.findById(respuestaPosible.getIdRespuesta());

			if (!respuestaPosibleBD.isPresent()) {
				throw new RespuestaPosibleNoExisteException(
						CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosible.getIdRespuesta());
				respuestaPosibleRepository.flush();
			}
		}

	}

}
