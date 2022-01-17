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
import com.sds.model.RolAccionFuncionalidadEntity;
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
	public FuncionalidadEntity buscarFuncionalidad(final String nombreFuncionalidad)
			throws FuncionalidadNoExisteException {
		final Boolean nombreValido = validaciones.comprobarNombreFuncionalidadBlank(nombreFuncionalidad);
		FuncionalidadEntity toret = new FuncionalidadEntity();

		if (nombreValido) {
			final FuncionalidadEntity funcionalidadBD = funcionalidadRepository
					.findFuncionalityByName(nombreFuncionalidad);

			if (funcionalidadBD == null) {
				throw new FuncionalidadNoExisteException(
						CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.FUNCIONALIDAD_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				toret.setIdFuncionalidad(funcionalidadBD.getIdFuncionalidad());
				toret.setNombreFuncionalidad(funcionalidadBD.getNombreFuncionalidad());
				toret.setDescripFuncionalidad(funcionalidadBD.getDescripFuncionalidad());
				toret.setBorradoFuncionalidad(funcionalidadBD.getBorradoFuncionalidad());
			}
		} else {
			toret = null;
		}
		return toret;
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
		String resultadoLog2 = StringUtils.EMPTY;

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
			final List<RolAccionFuncionalidadEntity> rolAccionFuncionalidad = rolAccionFuncionalidadRepository
					.findAll();

			for (int i = 0; i < rolAccionFuncionalidad.size(); i++) {
				if (rolAccionFuncionalidad.get(i).getIdFuncionalidad() == funcionalidadEntity.getIdFuncionalidad()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							funcionalidad.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION.getCodigo()),
							CodeMessageErrors.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new FuncionalidadAsociadaRolAccionException(
							CodeMessageErrors.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION.getCodigo()));

				}
			}
			funcionalidadEntity.setBorradoFuncionalidad(1);

			funcionalidad.setFuncionalidadEntity(funcionalidadEntity);
			modificarFuncionalidad(funcionalidad);

			final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(funcionalidad.getUsuario(),
					Constantes.ACCION_BUSCAR_ACCION, funcionalidadEntity.toString());

			resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

			final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(funcionalidad.getUsuario(),
					Constantes.ACCION_MODIFICAR_ACCION, funcionalidad.getFuncionalidadEntity().toString());

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
