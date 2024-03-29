package com.sds.service.empresa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.EmpresaEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PersonaEntity;
import com.sds.repository.EmpresaRepository;
import com.sds.repository.PersonaRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
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

	@PersistenceContext
	EntityManager entityManager;

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
	public ReturnBusquedas<EmpresaEntity> buscarEmpresa(final String cifEmpresa, final String nombreEmpresa,
			final String direccionEmpresa, final String telefonoEmpresa, final int inicio, final int tamanhoPagina) {

		final List<EmpresaEntity> toret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<EmpresaEntity> empresaBD = entityManager.createNamedQuery(Constantes.EMPRESA_QUERY_FINDEMPRESA)
				.setParameter(Constantes.CIF_EMPRESA, cifEmpresa).setParameter(Constantes.NOMBRE_EMPRESA, nombreEmpresa)
				.setParameter(Constantes.DIRECCION_EMPRESA, direccionEmpresa)
				.setParameter(Constantes.TELEFONO_EMPRESA, telefonoEmpresa).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = empresaRepository.numberFindEmpresa(cifEmpresa, nombreEmpresa,
				direccionEmpresa, telefonoEmpresa);

		if (!empresaBD.isEmpty()) {
			for (final EmpresaEntity empresa : empresaBD) {
				final EmpresaEntity empresaToret = new EmpresaEntity(empresa.getIdEmpresa(), empresa.getCifEmpresa(),
						empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
						empresa.getBorradoEmpresa());

				toret.add(empresaToret);

			}
		}

		datosBusqueda.add(Constantes.CIF_EMPRESA + Constantes.DOS_PUNTOS + cifEmpresa);
		datosBusqueda.add(Constantes.NOMBRE_EMPRESA + Constantes.DOS_PUNTOS + nombreEmpresa);
		datosBusqueda.add(Constantes.DIRECCION_EMPRESA + Constantes.DOS_PUNTOS + direccionEmpresa);
		datosBusqueda.add(Constantes.TELEFONO_EMPRESA + Constantes.DOS_PUNTOS + telefonoEmpresa);

		final ReturnBusquedas<EmpresaEntity> result = new ReturnBusquedas<EmpresaEntity>(toret, datosBusqueda,
				numberTotalResults, toret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<EmpresaEntity> buscarTodos(final int inicio, final int tamanhoPagina) {

		final List<EmpresaEntity> toret = new ArrayList<>();

		final List<EmpresaEntity> empresaBD = entityManager.createNamedQuery(Constantes.EMPRESA_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = empresaRepository.numberFindAllEmpresas();

		if (!empresaBD.isEmpty()) {
			for (final EmpresaEntity empresa : empresaBD) {
				final EmpresaEntity empresaToret = new EmpresaEntity(empresa.getIdEmpresa(), empresa.getCifEmpresa(),
						empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
						empresa.getBorradoEmpresa());

				toret.add(empresaToret);

			}
		}

		final ReturnBusquedas<EmpresaEntity> result = new ReturnBusquedas<EmpresaEntity>(toret, numberTotalResults,
				toret.size(), inicio);

		return result;

	}

	@Override
	public List<EmpresaEntity> buscarTodos() {
		final List<EmpresaEntity> toret = new ArrayList<>();

		final List<EmpresaEntity> empresaBD = empresaRepository.findAll();

		if (!empresaBD.isEmpty()) {
			for (final EmpresaEntity empresa : empresaBD) {
				final EmpresaEntity empresaToret = new EmpresaEntity(empresa.getIdEmpresa(), empresa.getCifEmpresa(),
						empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
						empresa.getBorradoEmpresa());

				toret.add(empresaToret);

			}
		}

		return toret;
	}

	@Override
	public ReturnBusquedas<EmpresaEntity> buscarEmpresasEliminadas(final int inicio, final int tamanhoPagina) {

		final List<EmpresaEntity> toret = new ArrayList<>();

		final List<EmpresaEntity> empresaBD = entityManager.createNamedQuery(Constantes.EMPRESA_QUERY_FINDELIMINADAS)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = empresaRepository.numberFindEmpresasEliminadas();

		if (!empresaBD.isEmpty()) {
			for (final EmpresaEntity empresa : empresaBD) {
				final EmpresaEntity empresaToret = new EmpresaEntity(empresa.getIdEmpresa(), empresa.getCifEmpresa(),
						empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
						empresa.getBorradoEmpresa());

				toret.add(empresaToret);
			}
		}

		final ReturnBusquedas<EmpresaEntity> result = new ReturnBusquedas<EmpresaEntity>(toret, numberTotalResults,
				toret.size(), inicio);

		return result;

	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
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
				if (person.getEmpresa() != null) {
					if (person.getEmpresa().getCifEmpresa().equals(empresa.getEmpresa().getCifEmpresa())) {

						logExcepciones = util.generarDatosLogExcepciones(empresa.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.EMPRESA_ASOCIADA_PERSONA_EXCEPTION.getCodigo()),
								CodeMessageErrors.EMPRESA_ASOCIADA_PERSONA_EXCEPTION.getCodigo());

						resultadoLog2 = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog2)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
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

		}

		return resultado;

	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
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
				empre.setCifEmpresa(empresaEntity.getCifEmpresa());
				empre.setNombreEmpresa(empresaEntity.getNombreEmpresa());
				empre.setDireccionEmpresa(empresaEntity.getDireccionEmpresa());
				empre.setTelefonoEmpresa(empresaEntity.getTelefonoEmpresa());
				empre.setBorradoEmpresa(empresaEntity.getBorradoEmpresa());

				empresa.setEmpresa(empre);

				empresaRepository.saveAndFlush(empre);

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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String reactivarEmpresa(final Empresa empresa)
			throws EmpresaNoEncontradaException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final EmpresaEntity empresaEntity = empresa.getEmpresa();

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
			empresaEntity.setBorradoEmpresa(0);
			empresa.setEmpresa(empresaEntity);
			resultado = modificarEmpresa(empresa);
		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
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
