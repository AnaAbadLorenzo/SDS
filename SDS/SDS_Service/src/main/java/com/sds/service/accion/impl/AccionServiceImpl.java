package com.sds.service.accion.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.AccionEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.RolAccionFuncionalidadEntity;
import com.sds.repository.AccionRepository;
import com.sds.repository.RolAccionFuncionalidadRepository;
import com.sds.service.accion.AccionService;
import com.sds.service.accion.model.Accion;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.AccionAsociadaRolFuncionalidadException;
import com.sds.service.exception.AccionNoExisteException;
import com.sds.service.exception.AccionYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.log.LogService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class AccionServiceImpl implements AccionService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	AccionRepository accionRepository;

	@Autowired
	RolAccionFuncionalidadRepository rolAccionFuncionalidadRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public AccionServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<AccionEntity> buscarAccion(final String nombreAccion, final String descripAccion,
			final int inicio, final int tamanhoPagina) {
		final List<AccionEntity> accionToret = new ArrayList<>();

		final Integer numberTotalResults = accionRepository.numberFindAccion(nombreAccion, descripAccion);

		final List<AccionEntity> acciones = entityManager.createNamedQuery("AccionEntity.findAccion")
				.setParameter("nombreAccion", nombreAccion).setParameter("descripAccion", descripAccion)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		if (!acciones.isEmpty()) {
			for (final AccionEntity accion : acciones) {
				final AccionEntity acc = new AccionEntity(accion.getIdAccion(), accion.getNombreAccion(),
						accion.getDescripAccion(), accion.getBorradoAccion());
				accionToret.add(acc);

			}
		}

		final ReturnBusquedas<AccionEntity> result = new ReturnBusquedas<AccionEntity>(accionToret, numberTotalResults,
				accionToret.size());

		return result;

	}

	@Override
	public ReturnBusquedas<AccionEntity> buscarTodos(final int inicio, final int tamanhoPagina) {

		final List<AccionEntity> acciones = entityManager.createNamedQuery("AccionEntity.findAllAccion")
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final List<AccionEntity> toret = new ArrayList<>();

		final Integer numberTotalResults = accionRepository.numberFindAllAccion();

		for (final AccionEntity accion : acciones) {
			final AccionEntity accionToret = new AccionEntity(accion.getIdAccion(), accion.getNombreAccion(),
					accion.getDescripAccion(), accion.getBorradoAccion());

			toret.add(accionToret);
		}

		final ReturnBusquedas<AccionEntity> result = new ReturnBusquedas<AccionEntity>(toret, numberTotalResults,
				toret.size());

		return result;
	}

	@Override
	public ReturnBusquedas<AccionEntity> buscarAccionesEliminadas(final int inicio, final int tamanhoPagina) {
		final List<AccionEntity> acciones = entityManager.createNamedQuery("AccionEntity.findAccionesEliminadas")
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final List<AccionEntity> toret = new ArrayList<>();

		final AccionEntity accionToret = new AccionEntity();

		final Integer numberTotalResults = accionRepository.numberFindAccionesEliminadas();

		for (final AccionEntity accion : acciones) {
			accionToret.setIdAccion(accion.getIdAccion());
			accionToret.setNombreAccion(accion.getNombreAccion());
			accionToret.setDescripAccion(accion.getDescripAccion());
			accionToret.setBorradoAccion(accion.getBorradoAccion());

			toret.add(accionToret);
		}

		final ReturnBusquedas<AccionEntity> result = new ReturnBusquedas<AccionEntity>(toret, numberTotalResults,
				toret.size());

		return result;
	}

	@Override
	public String anadirAccion(final Accion accion)
			throws AccionYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		final AccionEntity accionEntity = accion.getAccion();
		final Boolean accionValida = validaciones.comprobarAccionBlank(accionEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (accionValida) {
			final Optional<AccionEntity> accionBD = accionRepository.findById(accionEntity.getIdAccion());

			if (accionBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(accion.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ACCION_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.ACCION_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new AccionYaExisteException(CodeMessageErrors.ACCION_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.ACCION_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				accionRepository.saveAndFlush(accionEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(accion.getUsuario(),
						Constantes.ACCION_AÑADIR_ACCION, accion.getAccion().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			}

		} else {
			resultado = CodeMessageErrors.ACCION_VACIA.name();
		}

		return resultado;
	}

	@Override
	public String eliminarAccion(final Accion accion) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, AccionNoExisteException, AccionAsociadaRolFuncionalidadException {
		final AccionEntity accionEntity = accion.getAccion();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<AccionEntity> accionBD = accionRepository.findById(accionEntity.getIdAccion());

		if (!accionBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(accion.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new AccionNoExisteException(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			final List<RolAccionFuncionalidadEntity> rolAccionFuncionalidad = rolAccionFuncionalidadRepository
					.findByAccionId(accionEntity.getIdAccion());

			if (!rolAccionFuncionalidad.isEmpty()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(accion.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION.getCodigo()),
						CodeMessageErrors.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new AccionAsociadaRolFuncionalidadException(
						CodeMessageErrors.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION.getCodigo()));

			} else {
				accionEntity.setBorradoAccion(1);

				accion.setAccion(accionEntity);

				modificarAccion(accion);

				resultado = Constantes.OK;
			}

		}

		return resultado;

	}

	@Override
	public String modificarAccion(final Accion accion)
			throws LogExcepcionesNoGuardadoException, AccionNoExisteException, LogAccionesNoGuardadoException {
		final AccionEntity accionEntity = accion.getAccion();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		final Boolean accionValida = validaciones.comprobarAccionBlank(accionEntity);

		if (accionValida) {
			final Optional<AccionEntity> accionBD = accionRepository.findById(accionEntity.getIdAccion());

			if (!accionBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(accion.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new AccionNoExisteException(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo()));

			} else {
				accionEntity.setNombreAccion(accionEntity.getNombreAccion());
				accionEntity.setDescripAccion(accionEntity.getDescripAccion());
				accionEntity.setBorradoAccion(accionEntity.getBorradoAccion());
				accion.setAccion(accionEntity);
				accionRepository.saveAndFlush(accionEntity);

				final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(accion.getUsuario(),
						Constantes.ACCION_BUSCAR_ACCION, accionBD.toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(accion.getUsuario(),
						Constantes.ACCION_MODIFICAR_ACCION, accion.getAccion().toString());

				resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
						|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.ACCION_VACIA.name();
		}

		return resultado;

	}

	@Override
	public String reactivarAccion(final Accion accion)
			throws LogExcepcionesNoGuardadoException, AccionNoExisteException, LogAccionesNoGuardadoException {
		final AccionEntity accionEntity = accion.getAccion();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<AccionEntity> accionBD = accionRepository.findById(accionEntity.getIdAccion());

		if (!accionBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(accion.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new AccionNoExisteException(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo()));

		} else {
			accionEntity.setBorradoAccion(0);
			accion.setAccion(accionEntity);
			accionRepository.saveAndFlush(accionEntity);
			resultado = modificarAccion(accion);
		}

		return resultado;
	}

	@Override
	public void deleteAccion(final AccionEntity accion) throws AccionNoExisteException {

		final Boolean accionValida = validaciones.comprobarAccionBlank(accion);

		if (accionValida) {
			final Optional<AccionEntity> accionBD = accionRepository.findById(accion.getIdAccion());

			if (!accionBD.isPresent()) {
				throw new AccionNoExisteException(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				accionRepository.deleteAccion(accion.getIdAccion());
				accionRepository.flush();
			}
		}

	}

}
