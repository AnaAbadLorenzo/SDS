package com.sds.service.empresa.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.EmpresaEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PersonaEntity;
import com.sds.repository.EmpresaRepository;
import com.sds.repository.PersonaRepository;
import com.sds.service.common.Constantes;
import com.sds.service.empresa.EmpresaService;
import com.sds.service.empresa.model.Empresa;
import com.sds.service.exception.EmpresaAsociadaPersonasException;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.log.LogService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	LogService logServiceImpl;

	private final Util util;
	private final Validaciones validaciones;

	public EmpresaServiceImpl() {
		util = new Util();
		validaciones = new Validaciones();
	}

	@Override
	public List<EmpresaEntity> buscarEmpresa(final String cifEmpresa, final String nombreEmpresa,
			final String direccionEmpresa, final String telefonoEmpresa) {
		List<EmpresaEntity> empresaBD = new ArrayList();
		final List<EmpresaEntity> toret = new ArrayList();

		empresaBD = empresaRepository.findEmpresa(cifEmpresa, nombreEmpresa, direccionEmpresa, telefonoEmpresa);

		for (final EmpresaEntity empresa : empresaBD) {
			if (empresa.getBorradoEmpresa() == 0) {
				final EmpresaEntity empresaToret = new EmpresaEntity(empresa.getIdEmpresa(), empresa.getCifEmpresa(),
						empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
						empresa.getBorradoEmpresa());

				toret.add(empresaToret);

			}

		}

		return toret;
	}

	@Override
	public List<EmpresaEntity> buscarTodos() {
		List<EmpresaEntity> empresaBD = new ArrayList();
		final List<EmpresaEntity> toret = new ArrayList();

		empresaBD = empresaRepository.findAll();

		for (final EmpresaEntity empresa : empresaBD) {
			final EmpresaEntity empresaToret = new EmpresaEntity(empresa.getIdEmpresa(), empresa.getCifEmpresa(),
					empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
					empresa.getBorradoEmpresa());

		}

		return toret;

	}

	@Override
	public List<EmpresaEntity> buscarEmpresasEliminadas() {
		List<EmpresaEntity> empresaBD = new ArrayList();
		final List<EmpresaEntity> toret = new ArrayList();

		empresaBD = empresaRepository.findEmpresasEliminadas(1);

		for (final EmpresaEntity empresa : empresaBD) {
			final EmpresaEntity empresaToret = new EmpresaEntity(empresa.getIdEmpresa(), empresa.getCifEmpresa(),
					empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
					empresa.getBorradoEmpresa());

		}

		return toret;

	}

	@Override
	public String eliminarEmpresa(final Empresa empresa) throws LogExcepcionesNoGuardadoException,
			EmpresaNoEncontradaException, EmpresaAsociadaPersonasException, LogAccionesNoGuardadoException {
		final EmpresaEntity empresaEntity = empresa.getEmpresa();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		final EmpresaEntity empre = empresaRepository.findByCif(empresaEntity.getCifEmpresa());

		if (empre == null) {

			logExcepciones = util.generarDatosLogExcepciones(empresa.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()),
					CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new EmpresaNoEncontradaException(
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()),
					CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo());
		} else {
			final List<PersonaEntity> personasEmpresa = personaRepository.findAll();

			for (final PersonaEntity person : personasEmpresa) {
				if (person.getEmpresa().getCifEmpresa().equals(empresa.getEmpresa().getCifEmpresa())) {

					logExcepciones = util.generarDatosLogExcepciones(empresa.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.EMPRESA_ASOCIADA_PERSONA_EXCEPTION.getCodigo()),
							CodeMessageErrors.EMPRESA_ASOCIADA_PERSONA_EXCEPTION.getCodigo());

					resultadoLog2 = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog2)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new EmpresaAsociadaPersonasException(
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.EMPRESA_ASOCIADA_PERSONA_EXCEPTION.getCodigo()),

							CodeMessageErrors.EMPRESA_ASOCIADA_PERSONA_EXCEPTION.getCodigo());
				} else {
					empresaEntity.setBorradoEmpresa(1);
					empresa.setEmpresa(empresaEntity);
					modificarEmpresa(empresa);
					resultado = Constantes.OK;
				}
			}

		}

		return resultado;

	}

	@Override
	public String añadirEmpresa(final Empresa empresa)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, EmpresaYaExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final EmpresaEntity empresaEntity = empresa.getEmpresa();
		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		final Boolean empresaValida = validaciones.comprobarEmpresaBlank(empresaEntity);

		if (empresaValida) {
			final EmpresaEntity empresaBD = empresaRepository.findByCif(empresaEntity.getCifEmpresa());

			if (empresaBD != null) {
				logExcepciones = util.generarDatosLogExcepciones(empresa.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new EmpresaYaExisteException(
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo());
			} else {
				empresaRepository.saveAndFlush(empresaEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(empresa.getUsuario(),
						Constantes.ACCION_AÑADIR_EMPRESA, empresa.getEmpresa().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.EMPRESA_VACIO.name();
		}

		return resultado;
	}

	@Override
	public String modificarEmpresa(final Empresa empresa)
			throws EmpresaNoEncontradaException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final EmpresaEntity empresaEntity = empresa.getEmpresa();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		final Boolean empresaValida = validaciones.comprobarEmpresaBlank(empresaEntity);

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		if (empresaValida) {

			final EmpresaEntity empre = empresaRepository.findByCif(empresaEntity.getCifEmpresa());

			if (empre == null) {
				logExcepciones = util.generarDatosLogExcepciones(empresa.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()),
						CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new EmpresaNoEncontradaException(
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()),
						CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo());

			} else {
				empresaEntity.setIdEmpresa(empre.getIdEmpresa());
				empresaEntity.setCifEmpresa(empresaEntity.getCifEmpresa());
				empresaEntity.setNombreEmpresa(empresaEntity.getNombreEmpresa());
				empresaEntity.setDireccionEmpresa(empresaEntity.getDireccionEmpresa());
				empresaEntity.setTelefonoEmpresa(empresaEntity.getTelefonoEmpresa());
				empresaEntity.setBorradoEmpresa(empresaEntity.getBorradoEmpresa());

				empresa.setEmpresa(empresaEntity);

				empresaRepository.saveAndFlush(empresaEntity);

				final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(empresa.getUsuario(),
						Constantes.ACCION_BUSCAR_EMPRESA, empresa.getUsuario());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(empresa.getUsuario(),
						Constantes.ACCION_MODIFICAR_EMPRESA, empresa.getEmpresa().toString());

				resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
						|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.EMPRESA_VACIO.name();
		}

		return resultado;
	}

	@Override
	public void deleteEmpresa(final EmpresaEntity empresa)
			throws EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final EmpresaEntity empre = empresaRepository.findByCif(empresa.getCifEmpresa());

		if (empre == null) {
			throw new EmpresaNoEncontradaException(
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()),
					CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo());
		} else {
			empresaRepository.deleteEmpresa(empre.getCifEmpresa());
			empresaRepository.flush();
		}

	}

}
