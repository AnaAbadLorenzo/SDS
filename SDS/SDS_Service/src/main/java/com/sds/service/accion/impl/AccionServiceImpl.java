package com.sds.service.accion.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.sds.service.exception.AccionAsociadaRolFuncionalidadException;
import com.sds.service.exception.AccionNoExisteException;
import com.sds.service.exception.AccionYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.log.impl.LogServiceImpl;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class AccionServiceImpl implements AccionService {

	@Autowired
	AccionRepository accionRepository;

	@Autowired
	RolAccionFuncionalidadRepository rolAccionFuncionalidadRepository;

	@Autowired
	LogServiceImpl logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public AccionServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public AccionEntity buscarAccion(final String nombreAccion) throws AccionNoExisteException {
		final Boolean nombreAccionValido = validaciones.comprobarNombreAccionBlank(nombreAccion);
		final AccionEntity accionToret = new AccionEntity();

		if (nombreAccionValido) {
			final AccionEntity accion = accionRepository.findAccionByName(nombreAccion);

			if (accion == null) {
				throw new AccionNoExisteException(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.ACCION_NO_EXISTE_EXCEPTION.getCodigo()));

			} else {
				accionToret.setIdAccion(accion.getIdAccion());
				accionToret.setNombreAccion(accion.getNombreAccion());
				accionToret.setDescripAccion(accion.getDescripAccion());
				accionToret.setBorradoAccion(accion.getBorradoAccion());
			}

		}

		return accionToret;

	}

	@Override
	public List<AccionEntity> buscarTodos() {
		final List<AccionEntity> acciones = accionRepository.findAll();

		final List<AccionEntity> toret = new ArrayList<>();

		final AccionEntity accionToret = new AccionEntity();

		for (final AccionEntity accion : acciones) {
			accionToret.setIdAccion(accion.getIdAccion());
			accionToret.setNombreAccion(accion.getNombreAccion());
			accionToret.setDescripAccion(accion.getDescripAccion());
			accionToret.setBorradoAccion(accion.getBorradoAccion());

			toret.add(accionToret);
		}

		return toret;
	}

	@Override
	public List<AccionEntity> buscarAccionesEliminadas() {
		final List<AccionEntity> acciones = accionRepository.findAccionesEliminadas(1);

		final List<AccionEntity> toret = new ArrayList<>();

		final AccionEntity accionToret = new AccionEntity();

		for (final AccionEntity accion : acciones) {
			accionToret.setIdAccion(accion.getIdAccion());
			accionToret.setNombreAccion(accion.getNombreAccion());
			accionToret.setDescripAccion(accion.getDescripAccion());
			accionToret.setBorradoAccion(accion.getBorradoAccion());

			toret.add(accionToret);
		}

		return toret;
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
		String resultadoLog2 = StringUtils.EMPTY;

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
					.findAll();

			for (int i = 0; i < rolAccionFuncionalidad.size(); i++) {
				if (rolAccionFuncionalidad.get(i).getIdAccion() == accionEntity.getIdAccion()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(accion.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION.getCodigo()),
							CodeMessageErrors.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new AccionAsociadaRolFuncionalidadException(
							CodeMessageErrors.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION.getCodigo()));

				}
			}
			accionEntity.setBorradoAccion(1);

			accion.setAccion(accionEntity);
			accionRepository.saveAndFlush(accionEntity);

			final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(accion.getUsuario(),
					Constantes.ACCION_BUSCAR_ACCION, accionEntity.toString());

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

}
