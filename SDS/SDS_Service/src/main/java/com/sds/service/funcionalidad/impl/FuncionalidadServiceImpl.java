package com.sds.service.funcionalidad.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.FuncionalidadEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.repository.FuncionalidadRepository;
import com.sds.repository.RolAccionFuncionalidadRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.FuncionalidadAsociadaRolAccionException;
import com.sds.service.exception.FuncionalidadNoExisteException;
import com.sds.service.exception.FuncionalidadYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.funcionalidad.FuncionalidadService;
import com.sds.service.funcionalidad.model.Funcionalidad;
import com.sds.service.log.impl.LogServiceImpl;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class FuncionalidadServiceImpl implements FuncionalidadService {

	@Autowired
	FuncionalidadRepository funcionalidadRepository;

	@Autowired
	LogServiceImpl logServiceImpl;

	@Autowired
	RolAccionFuncionalidadRepository rolAccionFuncionalidadRepository;

	private final Validaciones validaciones;
	private final Util util;

	public FuncionalidadServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public List<FuncionalidadEntity> buscarFuncionalidad(final String nombreFuncionalidad,
			final String descripFuncionalidad) {

		final List<FuncionalidadEntity> funcionalidadToret = new ArrayList<>();

		final List<FuncionalidadEntity> funcionalidades = funcionalidadRepository.findFuncionality(nombreFuncionalidad,
				descripFuncionalidad);

		if (!funcionalidades.isEmpty()) {
			for (final FuncionalidadEntity funcionalidad : funcionalidades) {
				if (funcionalidad.getBorradoFuncionalidad() == 0) {
					final FuncionalidadEntity fun = new FuncionalidadEntity(funcionalidad.getIdFuncionalidad(),
							funcionalidad.getNombreFuncionalidad(), funcionalidad.getDescripFuncionalidad(),
							funcionalidad.getBorradoFuncionalidad());
					funcionalidadToret.add(fun);
				}

			}
		}

		return funcionalidadToret;
	}

	@Override
	public List<FuncionalidadEntity> buscarTodos() {
		final List<FuncionalidadEntity> funcionalidadesToret = new ArrayList<>();

		final List<FuncionalidadEntity> funcionalidades = funcionalidadRepository.findAll();

		for (final FuncionalidadEntity funcionalidad : funcionalidades) {
			final FuncionalidadEntity func = new FuncionalidadEntity(funcionalidad.getIdFuncionalidad(),
					funcionalidad.getNombreFuncionalidad(), funcionalidad.getDescripFuncionalidad(),
					funcionalidad.getBorradoFuncionalidad());

			funcionalidadesToret.add(func);
		}
		return funcionalidadesToret;
	}

	@Override
	public List<FuncionalidadEntity> buscarFuncionalidadesEliminadas() {
		final List<FuncionalidadEntity> funcionalidadesToret = new ArrayList<>();

		final List<FuncionalidadEntity> funcionalidades = funcionalidadRepository.findFuncionalidadesEliminadas(1);

		for (final FuncionalidadEntity funcionalidad : funcionalidades) {
			final FuncionalidadEntity func = new FuncionalidadEntity(funcionalidad.getIdFuncionalidad(),
					funcionalidad.getNombreFuncionalidad(), funcionalidad.getDescripFuncionalidad(),
					funcionalidad.getBorradoFuncionalidad());

			funcionalidadesToret.add(func);
		}
		return funcionalidadesToret;
	}

	@Override
	public String anadirFuncionalidad(final Funcionalidad funcionalidad)
			throws FuncionalidadYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		final FuncionalidadEntity funcionalidadEntity = funcionalidad.getFuncionalidadEntity();
		final Boolean funcionalidadValida = validaciones.comprobarFuncionalidadBlank(funcionalidadEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (funcionalidadValida) {
			final Optional<FuncionalidadEntity> funcionalidadBD = funcionalidadRepository
					.findById(funcionalidadEntity.getIdFuncionalidad());

			if (funcionalidadBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(funcionalidad.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.FUNCIONALIDAD_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.FUNCIONALIDAD_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new FuncionalidadYaExisteException(
						CodeMessageErrors.FUNCIONALIDAD_YA_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.FUNCIONALIDAD_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				funcionalidadRepository.saveAndFlush(funcionalidadEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(funcionalidad.getUsuario(),
						Constantes.ACCION_AÑADIR_FUNCIONALIDAD, funcionalidad.getFuncionalidadEntity().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			}
		} else {
			resultado = CodeMessageErrors.FUNCIONALIDAD_VACIA.name();
		}
		return resultado;
	}

	@Override
	public String eliminarFuncionalidad(final Funcionalidad funcionalidad) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, FuncionalidadNoExisteException, FuncionalidadAsociadaRolAccionException {

		final FuncionalidadEntity funcionalidadEntity = funcionalidad.getFuncionalidadEntity();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final String resultadoLog2 = StringUtils.EMPTY;

		final Optional<FuncionalidadEntity> funcionalidadBD = funcionalidadRepository
				.findById(funcionalidadEntity.getIdFuncionalidad());

		if (!funcionalidadBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(funcionalidad.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new FuncionalidadNoExisteException(CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			final List<Integer> rolAccionFuncionalidad = rolAccionFuncionalidadRepository
					.findAccionByIdFuncionality(funcionalidadEntity.getIdFuncionalidad());

			if (!rolAccionFuncionalidad.isEmpty()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(funcionalidad.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION.getCodigo()),
						CodeMessageErrors.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new FuncionalidadAsociadaRolAccionException(
						CodeMessageErrors.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION.getCodigo()));

			} else {
				funcionalidadEntity.setBorradoFuncionalidad(1);

				funcionalidad.setFuncionalidadEntity(funcionalidadEntity);
				modificarFuncionalidad(funcionalidad);
				resultado = Constantes.OK;
			}

		}

		return resultado;

	}

	@Override
	public String modificarFuncionalidad(final Funcionalidad funcionalidad)
			throws LogExcepcionesNoGuardadoException, FuncionalidadNoExisteException, LogAccionesNoGuardadoException {
		final FuncionalidadEntity funcionalidadEntity = funcionalidad.getFuncionalidadEntity();
		final Boolean funcionalidadValida = validaciones.comprobarFuncionalidadBlank(funcionalidadEntity);

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (funcionalidadValida) {
			final Optional<FuncionalidadEntity> funcionalidadBD = funcionalidadRepository
					.findById(funcionalidadEntity.getIdFuncionalidad());

			if (!funcionalidadBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(funcionalidad.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new FuncionalidadNoExisteException(
						CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				funcionalidadEntity.setNombreFuncionalidad(funcionalidadEntity.getNombreFuncionalidad());
				funcionalidadEntity.setDescripFuncionalidad(funcionalidadEntity.getDescripFuncionalidad());
				funcionalidadEntity.setBorradoFuncionalidad(funcionalidadEntity.getBorradoFuncionalidad());

				funcionalidadRepository.saveAndFlush(funcionalidadEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(funcionalidad.getUsuario(),
						Constantes.ACCION_MODIFICAR_FUNCIONALIDAD, funcionalidad.getFuncionalidadEntity().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			}
		} else {
			resultado = CodeMessageErrors.FUNCIONALIDAD_VACIA.name();
		}
		return resultado;
	}

	@Override
	public void deleteFuncionalidad(final FuncionalidadEntity funcionalidad) throws FuncionalidadNoExisteException {
		final Boolean funcionalidadValida = validaciones.comprobarFuncionalidadBlank(funcionalidad);

		if (funcionalidadValida) {
			final Optional<FuncionalidadEntity> funcionalidadBD = funcionalidadRepository
					.findById(funcionalidad.getIdFuncionalidad());

			if (!funcionalidadBD.isPresent()) {
				throw new FuncionalidadNoExisteException(
						CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				funcionalidadRepository.deleteFuncionalidad(funcionalidad.getIdFuncionalidad());
				funcionalidadRepository.flush();
			}
		}

	}

}
