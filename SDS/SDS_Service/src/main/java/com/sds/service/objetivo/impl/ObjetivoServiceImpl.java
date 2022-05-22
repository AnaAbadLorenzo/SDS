package com.sds.service.objetivo.impl;

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
import com.sds.model.ObjetivoEntity;
import com.sds.model.PlanEntity;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PlanRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ObjetivoAsociadoPlanException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.ObjetivoYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.objetivo.ObjetivoService;
import com.sds.service.objetivo.model.Objetivo;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class ObjetivoServiceImpl implements ObjetivoService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public ObjetivoServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<ObjetivoEntity> buscarObjetivo(final String nombreObjetivo, final String descripObjetivo,
			final int inicio, final int tamanhoPagina) {
		final List<ObjetivoEntity> objetivoToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<ObjetivoEntity> objetivos = entityManager.createNamedQuery(Constantes.OBJETIVO_QUERY_FINDOBJETIVO)
				.setParameter(Constantes.NOMBRE_OBJETIVO, nombreObjetivo)
				.setParameter(Constantes.DESCRIPCION_OBJETIVO, descripObjetivo).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = objetivoRepository.numberFindObjetivo(nombreObjetivo, descripObjetivo);

		if (!objetivos.isEmpty()) {
			for (final ObjetivoEntity objetivo : objetivos) {
				final ObjetivoEntity ob = new ObjetivoEntity(objetivo.getIdObjetivo(), objetivo.getNombreObjetivo(),
						objetivo.getDescripObjetivo(), objetivo.getBorradoObjetivo());
				objetivoToret.add(ob);

			}
		}

		datosBusqueda.add(Constantes.NOMBRE_OBJETIVO + Constantes.DOS_PUNTOS + nombreObjetivo);
		datosBusqueda.add(Constantes.DESCRIPCION_OBJETIVO + Constantes.DOS_PUNTOS + descripObjetivo);

		final ReturnBusquedas<ObjetivoEntity> result = new ReturnBusquedas<>(objetivoToret, datosBusqueda,
				numberTotalResults, objetivoToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<ObjetivoEntity> buscarTodos(final int inicio, final int tamanhoPagina) {
		final List<ObjetivoEntity> objetivoToret = new ArrayList<>();
		final List<ObjetivoEntity> objetivos = entityManager.createNamedQuery(Constantes.OBJETIVO_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = objetivoRepository.numberFindAllObjetivos();

		if (!objetivos.isEmpty()) {
			for (final ObjetivoEntity objetivo : objetivos) {
				final ObjetivoEntity ob = new ObjetivoEntity(objetivo.getIdObjetivo(), objetivo.getNombreObjetivo(),
						objetivo.getDescripObjetivo(), objetivo.getBorradoObjetivo());
				objetivoToret.add(ob);

			}
		}

		final ReturnBusquedas<ObjetivoEntity> result = new ReturnBusquedas<>(objetivoToret, numberTotalResults,
				objetivoToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<ObjetivoEntity> buscarObjetivosEliminados(final int inicio, final int tamanhoPagina) {
		final List<ObjetivoEntity> objetivoToret = new ArrayList<>();
		final List<ObjetivoEntity> objetivos = entityManager.createNamedQuery(Constantes.OBJETIVO_QUERY_FINDELIMINADOS)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = objetivoRepository.numberFindObjetivosEliminados();

		if (!objetivos.isEmpty()) {
			for (final ObjetivoEntity objetivo : objetivos) {
				final ObjetivoEntity ob = new ObjetivoEntity(objetivo.getIdObjetivo(), objetivo.getNombreObjetivo(),
						objetivo.getDescripObjetivo(), objetivo.getBorradoObjetivo());
				objetivoToret.add(ob);

			}
		}

		final ReturnBusquedas<ObjetivoEntity> result = new ReturnBusquedas<>(objetivoToret, numberTotalResults,
				objetivoToret.size(), inicio);

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirObjetivo(final Objetivo objetivo)
			throws ObjetivoYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		final ObjetivoEntity objetivoEntity = objetivo.getObjetivo();
		final Boolean objetivoValido = validaciones.comprobarObjetivoBlank(objetivoEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (objetivoValido) {
			final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivoEntity.getNombreObjetivo());

			if (objetivoBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(objetivo.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.OBJETIVO_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ObjetivoYaExisteException(CodeMessageErrors.OBJETIVO_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				objetivoEntity.setBorradoObjetivo(0);
				objetivoRepository.saveAndFlush(objetivoEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(objetivo.getUsuario(),
						Constantes.ACCION_AÑADIR_OBJETIVO, objetivo.getObjetivo().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			}
		} else {
			resultado = CodeMessageErrors.OBJETIVO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminaObjetivo(final Objetivo objetivo) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, ObjetivoNoExisteException, ObjetivoAsociadoPlanException {
		final ObjetivoEntity objetivoEntity = objetivo.getObjetivo();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		Boolean eliminarObjetivo = Boolean.FALSE;

		final Optional<ObjetivoEntity> objetivoBD = objetivoRepository.findById(objetivoEntity.getIdObjetivo());

		if (!objetivoBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(objetivo.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ObjetivoNoExisteException(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {

			final List<PlanEntity> planes = planRepository.findAll();

			if (!planes.isEmpty()) {
				for (final PlanEntity planEntity : planes) {
					if (!planEntity.getObjetivo().getIdObjetivo().equals(objetivoEntity.getIdObjetivo())) {
						eliminarObjetivo = true;
					} else {
						eliminarObjetivo = false;
						break;
					}
				}
			}

			if (Boolean.TRUE.equals(eliminarObjetivo)) {
				objetivoEntity.setBorradoObjetivo(1);
				objetivo.setObjetivo(objetivoEntity);
				modificarObjetivo(objetivo);
				resultado = Constantes.OK;
			} else {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(objetivo.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_ASOCIADO_PLAN_EXCEPTION.getCodigo()),
						CodeMessageErrors.OBJETIVO_ASOCIADO_PLAN_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ObjetivoAsociadoPlanException(CodeMessageErrors.OBJETIVO_ASOCIADO_PLAN_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_ASOCIADO_PLAN_EXCEPTION.getCodigo()));
			}
		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarObjetivo(final Objetivo objetivo)
			throws LogExcepcionesNoGuardadoException, ObjetivoNoExisteException, LogAccionesNoGuardadoException {
		final ObjetivoEntity objetivoEntity = objetivo.getObjetivo();
		final Boolean objetivoValido = validaciones.comprobarObjetivoBlank(objetivoEntity);

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (objetivoValido) {
			final Optional<ObjetivoEntity> objetivoBD = objetivoRepository.findById(objetivoEntity.getIdObjetivo());

			if (!objetivoBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(objetivo.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ObjetivoNoExisteException(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				objetivoBD.get().setNombreObjetivo(objetivoEntity.getNombreObjetivo());
				objetivoBD.get().setDescripObjetivo(objetivoEntity.getDescripObjetivo());
				objetivoBD.get().setBorradoObjetivo(objetivoEntity.getBorradoObjetivo());

				objetivoRepository.saveAndFlush(objetivoBD.get());

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(objetivo.getUsuario(),
						Constantes.ACCION_MODIFICAR_OBJETIVO, objetivo.getObjetivo().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			}
		} else {
			resultado = CodeMessageErrors.OBJETIVO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String reactivarObjetivo(final Objetivo objetivo)
			throws LogExcepcionesNoGuardadoException, ObjetivoNoExisteException, LogAccionesNoGuardadoException {
		final ObjetivoEntity objetivoEntity = objetivo.getObjetivo();

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<ObjetivoEntity> objetivoBD = objetivoRepository.findById(objetivoEntity.getIdObjetivo());

		if (!objetivoBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(objetivo.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ObjetivoNoExisteException(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {

			objetivoEntity.setBorradoObjetivo(0);
			objetivo.setObjetivo(objetivoEntity);
			resultado = modificarObjetivo(objetivo);

		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void deleteObjetivo(final ObjetivoEntity objetivo) throws ObjetivoNoExisteException {
		final Boolean objetivoValido = validaciones.comprobarObjetivoBlank(objetivo);

		if (objetivoValido) {
			final Optional<ObjetivoEntity> objetivoBD = objetivoRepository.findById(objetivo.getIdObjetivo());

			if (!objetivoBD.isPresent()) {
				throw new ObjetivoNoExisteException(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				objetivoRepository.deleteObjetivo(objetivoBD.get().getIdObjetivo());
				objetivoRepository.flush();
			}
		}

	}

}
