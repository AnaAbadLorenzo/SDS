package com.sds.service.nivel.impl;

import java.text.ParseException;
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
import com.sds.model.NivelEntity;
import com.sds.model.ObjetivoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.compositekey.NivelKey;
import com.sds.repository.NivelRepository;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NivelNoExisteException;
import com.sds.service.exception.NivelYaExisteException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.log.LogService;
import com.sds.service.nivel.NivelService;
import com.sds.service.nivel.model.Nivel;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class NivelServiceImpl implements NivelService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	NivelRepository nivelRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public NivelServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public List<NivelEntity> buscarNivel(final Integer idObjetivo, final Integer idProceso) {
		final List<NivelEntity> nivelToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<NivelEntity> niveles = entityManager.createNamedQuery(Constantes.NIVEL_QUERY_FINDNIVEL)
				.setParameter(Constantes.OBJETIVO_ID, idObjetivo).setParameter(Constantes.PROCESO_ID, idProceso)
				.getResultList();

		if (!niveles.isEmpty()) {
			for (final NivelEntity nivel : niveles) {
				final NivelEntity nivelEntity = new NivelEntity(nivel.getIdObjetivo(), nivel.getIdProceso(),
						nivel.getNivel(), nivel.getFechaNivel());

				nivelToret.add(nivelEntity);

			}
		}

		datosBusqueda.add(Constantes.OBJETIVO_ID + Constantes.DOS_PUNTOS + idObjetivo);
		datosBusqueda.add(Constantes.PROCESO_ID + Constantes.DOS_PUNTOS + idProceso);

		return nivelToret;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String añadirNivel(final Nivel nivel)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, NivelYaExisteException,
			ObjetivoNoExisteException, ProcesoNoExisteException, ParseException {
		final NivelEntity nivelEntity = nivel.getNivel();
		final Boolean nivelValido = validaciones.comprobarNivelBlank(nivelEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (nivelValido) {
			final NivelKey nivelKey = new NivelKey(nivelEntity.getIdObjetivo(), nivelEntity.getIdProceso());

			final Optional<NivelEntity> nivelBD = nivelRepository.findById(nivelKey);

			if (nivelBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(nivel.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.NIVEL_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.NIVEL_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new NivelYaExisteException(CodeMessageErrors.NIVEL_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.NIVEL_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final Optional<ProcesoEntity> procesoBD = procesoRepository.findById(nivelEntity.getIdProceso());

				if (!procesoBD.isPresent()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(nivel.getUsuario(),
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
					final Optional<ObjetivoEntity> objetivoBD = objetivoRepository
							.findById(nivelEntity.getIdObjetivo());

					if (!objetivoBD.isPresent()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(nivel.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new ObjetivoNoExisteException(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()));
					} else {
						nivelEntity.setFechaNivel(new Date());
						nivelRepository.saveAndFlush(nivelEntity);

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(nivel.getUsuario(),
								Constantes.ACCION_AÑADIR_NIVEL, nivel.getNivel().toString());

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
	public String deleteNivel(final Nivel nivel) throws NivelNoExisteException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, ParseException {
		String resultado = StringUtils.EMPTY;
		final NivelEntity nivelEntity = nivel.getNivel();
		final Boolean nivelValido = validaciones.comprobarNivelBlank(nivelEntity);
		if (nivelValido) {
			final NivelKey nivelKey = new NivelKey(nivelEntity.getIdObjetivo(), nivelEntity.getIdProceso());
			final Optional<NivelEntity> nivelBD = nivelRepository.findById(nivelKey);

			if (!nivelBD.isPresent()) {
				throw new NivelNoExisteException(CodeMessageErrors.NIVEL_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.NIVEL_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				nivelRepository.deleteNivel(nivelEntity.getIdObjetivo(), nivelEntity.getIdProceso());
				nivelRepository.flush();
				resultado = Constantes.OK;

			}
		}

		return resultado;
	}

}
