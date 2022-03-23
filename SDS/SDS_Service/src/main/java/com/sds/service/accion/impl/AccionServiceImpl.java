package com.sds.service.accion.impl;

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

import com.sds.model.AccionEntity;
import com.sds.model.FuncionalidadEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.RolAccionFuncionalidadEntity;
import com.sds.model.RolEntity;
import com.sds.model.compositekey.RolAccionFuncionalidadKey;
import com.sds.repository.AccionRepository;
import com.sds.repository.FuncionalidadRepository;
import com.sds.repository.RolAccionFuncionalidadRepository;
import com.sds.repository.RolRepository;
import com.sds.service.accion.AccionService;
import com.sds.service.accion.model.Accion;
import com.sds.service.accion.model.AccionAsignar;
import com.sds.service.accion.model.RolAccionFuncionalidad;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.AccionAsociadaRolFuncionalidadException;
import com.sds.service.exception.AccionNoExisteException;
import com.sds.service.exception.AccionYaExisteException;
import com.sds.service.exception.FuncionalidadNoExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PermisoNoExisteException;
import com.sds.service.exception.RolNoExisteException;
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
	RolRepository rolRepository;

	@Autowired
	FuncionalidadRepository funcionalidadRepository;

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
		final List<String> datosBusqueda = new ArrayList<>();

		final List<AccionEntity> acciones = entityManager.createNamedQuery(Constantes.ACCION_QUERY_FINDACCION)
				.setParameter(Constantes.ACCION_NAME, nombreAccion)
				.setParameter(Constantes.ACCION_DESCRIPTION, descripAccion).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = accionRepository.numberFindAccion(nombreAccion, descripAccion);

		if (!acciones.isEmpty()) {
			for (final AccionEntity accion : acciones) {
				final AccionEntity acc = new AccionEntity(accion.getIdAccion(), accion.getNombreAccion(),
						accion.getDescripAccion(), accion.getBorradoAccion());
				accionToret.add(acc);

			}
		}

		datosBusqueda.add(Constantes.ACCION_NAME + ": " + nombreAccion);
		datosBusqueda.add(Constantes.ACCION_DESCRIPTION + ": " + descripAccion);

		final ReturnBusquedas<AccionEntity> result = new ReturnBusquedas<AccionEntity>(accionToret, datosBusqueda,
				numberTotalResults, accionToret.size());

		return result;

	}

	@Override
	public ReturnBusquedas<AccionEntity> buscarTodos(final int inicio, final int tamanhoPagina) {

		final List<AccionEntity> toret = new ArrayList<>();

		final List<AccionEntity> acciones = entityManager.createNamedQuery(Constantes.ACCION_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = accionRepository.numberFindAllAccion();

		if (!acciones.isEmpty()) {
			for (final AccionEntity accion : acciones) {
				final AccionEntity accionToret = new AccionEntity(accion.getIdAccion(), accion.getNombreAccion(),
						accion.getDescripAccion(), accion.getBorradoAccion());

				toret.add(accionToret);
			}
		}

		final ReturnBusquedas<AccionEntity> result = new ReturnBusquedas<AccionEntity>(toret, numberTotalResults,
				toret.size());

		return result;
	}

	@Override
	public ReturnBusquedas<AccionEntity> buscarAccionesEliminadas(final int inicio, final int tamanhoPagina) {
		final List<AccionEntity> toret = new ArrayList<>();

		final List<AccionEntity> acciones = entityManager.createNamedQuery(Constantes.ACCION_QUERY_FINDELIMINADAS)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = accionRepository.numberFindAccionesEliminadas();

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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirAccion(final Accion accion)
			throws AccionYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		final AccionEntity accionEntity = accion.getAccion();
		final Boolean accionValida = validaciones.comprobarAccionBlank(accionEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (accionValida) {
			final AccionEntity accionBD = accionRepository.findAccionByName(accionEntity.getNombreAccion());

			if (accionBD != null) {
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
				accionEntity.setBorradoAccion(0);
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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String reactivarAccion(final Accion accion)
			throws LogExcepcionesNoGuardadoException, AccionNoExisteException, LogAccionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final AccionEntity accionEntity = accion.getAccion();

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
			resultado = modificarAccion(accion);
		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String asignarAcciones(final AccionAsignar accion)
			throws LogExcepcionesNoGuardadoException, AccionNoExisteException, LogAccionesNoGuardadoException,
			FuncionalidadNoExisteException, RolNoExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<AccionEntity> accionBD = accionRepository.findById(accion.getAccion().getIdAccion());

		if (accionBD.isPresent() && accionBD.get().getBorradoAccion() == 0) {
			final Optional<RolEntity> rolBD = rolRepository.findById(accion.getRol().getIdRol());

			if (rolBD.isPresent() && rolBD.get().getBorradoRol() == 0) {
				final Optional<FuncionalidadEntity> funcionalidadBD = funcionalidadRepository
						.findById(accion.getFuncionalidad().getIdFuncionalidad());

				if (funcionalidadBD.isPresent() && funcionalidadBD.get().getBorradoFuncionalidad() == 0) {
					final RolAccionFuncionalidadEntity rolAccionFuncionalidad = new RolAccionFuncionalidadEntity(
							accion.getAccion().getIdAccion(), accion.getFuncionalidad().getIdFuncionalidad(),
							accion.getRol().getIdRol());
					rolAccionFuncionalidadRepository.saveAndFlush(rolAccionFuncionalidad);

					final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(accion.getUsuario(),
							Constantes.ACCION_ASIGNAR_ACCION, accion.toString());

					resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

					if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
					}

					resultado = Constantes.OK;
				} else {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(accion.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo()),
							CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new FuncionalidadNoExisteException(
							CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo()));
				}
			} else {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(accion.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
			}

		} else {
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
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String desasignarAccion(final RolAccionFuncionalidad rolAccionFuncionalidad)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, PermisoNoExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final Optional<RolAccionFuncionalidadEntity> rolAccionFuncionalidadBD = rolAccionFuncionalidadRepository
				.findById(new RolAccionFuncionalidadKey(rolAccionFuncionalidad.getRolAccionFuncionalidad().getIdRol(),
						rolAccionFuncionalidad.getRolAccionFuncionalidad().getIdAccion(),
						rolAccionFuncionalidad.getRolAccionFuncionalidad().getIdFuncionalidad()));

		if (!rolAccionFuncionalidadBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
					rolAccionFuncionalidad.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERMISO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PERMISO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new PermisoNoExisteException(CodeMessageErrors.PERMISO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERMISO_NO_EXISTE_EXCEPTION.getCodigo()));

		} else {
			rolAccionFuncionalidadRepository.delete(rolAccionFuncionalidad.getRolAccionFuncionalidad());

			final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(rolAccionFuncionalidad.getUsuario(),
					Constantes.ACCION_REVOCAR_ACCION, rolAccionFuncionalidad.toString());

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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
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
