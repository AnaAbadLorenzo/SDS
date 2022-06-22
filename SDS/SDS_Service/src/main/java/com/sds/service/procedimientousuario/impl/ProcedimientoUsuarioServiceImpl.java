package com.sds.service.procedimientousuario.impl;

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
import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcedimientoUsuarioProcesoRepository;
import com.sds.repository.ProcedimientoUsuarioRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException;
import com.sds.service.exception.ProcedimientoUsuarioYaExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.log.LogService;
import com.sds.service.procedimientousuario.ProcedimientoUsuarioService;
import com.sds.service.procedimientousuario.model.ProcedimientoUsuario;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class ProcedimientoUsuarioServiceImpl implements ProcedimientoUsuarioService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	ProcedimientoUsuarioRepository procedimientoUsuarioRepository;

	@Autowired
	ProcedimientoUsuarioProcesoRepository procedimientoUsuarioProcesoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public ProcedimientoUsuarioServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<ProcedimientoUsuarioEntity> buscarProcedimientoUsuario(
			final Integer puntuacionProcedimientoUsuario, final Date fechaProcedimientoUsuario,
			final UsuarioEntity usuario, final ProcedimientoEntity procedimiento, final int inicio,
			final int tamanhoPagina) {

		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();
		List<ProcedimientoUsuarioEntity> procedimientosUsuario = new ArrayList<>();
		Integer numberTotalResults = 0;
		String fecha = StringUtils.EMPTY;

		if (fechaProcedimientoUsuario != null) {
			java.sql.Date fechaSql;
			fechaSql = new java.sql.Date(fechaProcedimientoUsuario.getTime());
			fecha = fechaSql.toString();
		} else {
			fecha = StringUtils.EMPTY;
		}

		if (usuario == null) {
			if (procedimiento == null) {
				procedimientosUsuario = entityManager
						.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOUSUARIO)
						.setParameter(Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO, puntuacionProcedimientoUsuario)
						.setParameter(Constantes.FECHA_PROCEDIMIENTO_USUARIO, fecha)
						.setParameter(Constantes.USUARIO, StringUtils.EMPTY)
						.setParameter(Constantes.PROCEDIMIENTO, StringUtils.EMPTY).setFirstResult(inicio)
						.setMaxResults(tamanhoPagina).getResultList();
				numberTotalResults = procedimientoUsuarioRepository
						.numberFindProcedimientoUsuarioWithoutProcedimientoAndUsuario(puntuacionProcedimientoUsuario,
								fecha);
			} else {
				final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
						.findById(procedimiento.getIdProcedimiento());
				procedimientosUsuario = entityManager
						.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOUSUARIO)
						.setParameter(Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO, puntuacionProcedimientoUsuario)
						.setParameter(Constantes.FECHA_PROCEDIMIENTO_USUARIO, fecha)
						.setParameter(Constantes.USUARIO, StringUtils.EMPTY)
						.setParameter(Constantes.PROCEDIMIENTO, procedimientoBD.get()).setFirstResult(inicio)
						.setMaxResults(tamanhoPagina).getResultList();
				numberTotalResults = procedimientoUsuarioRepository.numberFindProcedimientoUsuarioWithoutUsuario(
						puntuacionProcedimientoUsuario, fecha, procedimientoBD.get());
			}
		} else {
			final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(usuario.getDniUsuario());
			if (procedimiento == null) {
				procedimientosUsuario = entityManager
						.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOUSUARIO)
						.setParameter(Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO, puntuacionProcedimientoUsuario)
						.setParameter(Constantes.FECHA_PROCEDIMIENTO_USUARIO, fecha)
						.setParameter(Constantes.USUARIO, usuarioBD.get())
						.setParameter(Constantes.PROCEDIMIENTO, StringUtils.EMPTY).setFirstResult(inicio)
						.setMaxResults(tamanhoPagina).getResultList();
				numberTotalResults = procedimientoUsuarioRepository.numberFindProcedimientoUsuarioWithoutProcedimiento(
						puntuacionProcedimientoUsuario, fecha, usuarioBD.get());
			} else {
				final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
						.findById(procedimiento.getIdProcedimiento());
				procedimientosUsuario = entityManager
						.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOUSUARIO)
						.setParameter(Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO, puntuacionProcedimientoUsuario)
						.setParameter(Constantes.FECHA_PROCEDIMIENTO_USUARIO, fecha)
						.setParameter(Constantes.USUARIO, usuarioBD.get())
						.setParameter(Constantes.PROCEDIMIENTO, procedimientoBD.get()).setFirstResult(inicio)
						.setMaxResults(tamanhoPagina).getResultList();
				numberTotalResults = procedimientoUsuarioRepository.numberFindProcedimientoUsuario(
						puntuacionProcedimientoUsuario, fecha, usuarioBD.get(), procedimientoBD.get());
			}
		}

		if (!procedimientosUsuario.isEmpty()) {
			for (final ProcedimientoUsuarioEntity procedimientoUsuario : procedimientosUsuario) {
				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimientoUsuario.getProcedimiento().getIdProcedimiento(),
						procedimientoUsuario.getProcedimiento().getNombreProcedimiento(),
						procedimientoUsuario.getProcedimiento().getDescripProcedimiento(),
						procedimientoUsuario.getProcedimiento().getFechaProcedimiento(),
						procedimientoUsuario.getProcedimiento().getBorradoProcedimiento(),
						procedimientoUsuario.getProcedimiento().getCheckUsuario());
				final UsuarioEntity usuarioEntity = new UsuarioEntity(procedimientoUsuario.getUsuario().getDniUsuario(),
						procedimientoUsuario.getUsuario().getUsuario(),
						procedimientoUsuario.getUsuario().getBorradoUsuario());
				final ProcedimientoUsuarioEntity procedimientoUsuarioEntity = new ProcedimientoUsuarioEntity(
						procedimientoUsuario.getIdProcedimientoUsuario(),
						procedimientoUsuario.getPuntuacionProcedimientoUsuario(),
						procedimientoUsuario.getFechaProcedimientoUsuario(),
						procedimientoUsuario.getBorradoProcedimientoUsuario(), procedimientoEntity, usuarioEntity);
				procedimientoUsuarioToret.add(procedimientoUsuarioEntity);

			}
		}

		datosBusqueda.add(
				Constantes.PUNTUACION_PROCEDIMIENTOUSUARIO + Constantes.DOS_PUNTOS + puntuacionProcedimientoUsuario);
		datosBusqueda.add(Constantes.FECHA_PROCEDIMIENTO_USUARIO + Constantes.DOS_PUNTOS + fechaProcedimientoUsuario);

		if (procedimiento != null) {
			datosBusqueda.add(Constantes.PROCEDIMIENTO + Constantes.DOS_PUNTOS + procedimiento.toString());
		}
		if (usuario != null) {
			datosBusqueda.add(Constantes.USUARIO + Constantes.DOS_PUNTOS + usuario.toString());
		}

		final ReturnBusquedas<ProcedimientoUsuarioEntity> result = new ReturnBusquedas<>(procedimientoUsuarioToret,
				datosBusqueda, numberTotalResults, procedimientoUsuarioToret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<ProcedimientoUsuarioEntity> buscarTodos(final int inicio, final int tamanhoPagina) {
		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();
		List<ProcedimientoUsuarioEntity> procedimientosUsuario = new ArrayList<>();
		Integer numberTotalResults = 0;

		procedimientosUsuario = entityManager.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();
		numberTotalResults = procedimientoUsuarioRepository.numberFindAllProcedimientosUsuario();

		if (!procedimientosUsuario.isEmpty()) {
			for (final ProcedimientoUsuarioEntity procedimientoUsuario : procedimientosUsuario) {
				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimientoUsuario.getProcedimiento().getIdProcedimiento(),
						procedimientoUsuario.getProcedimiento().getNombreProcedimiento(),
						procedimientoUsuario.getProcedimiento().getDescripProcedimiento(),
						procedimientoUsuario.getProcedimiento().getFechaProcedimiento(),
						procedimientoUsuario.getProcedimiento().getBorradoProcedimiento(),
						procedimientoUsuario.getProcedimiento().getCheckUsuario());
				final UsuarioEntity usuarioEntity = new UsuarioEntity(procedimientoUsuario.getUsuario().getDniUsuario(),
						procedimientoUsuario.getUsuario().getUsuario(),
						procedimientoUsuario.getUsuario().getBorradoUsuario());
				final ProcedimientoUsuarioEntity procedimientoUsuarioEntity = new ProcedimientoUsuarioEntity(
						procedimientoUsuario.getIdProcedimientoUsuario(),
						procedimientoUsuario.getPuntuacionProcedimientoUsuario(),
						procedimientoUsuario.getFechaProcedimientoUsuario(),
						procedimientoUsuario.getBorradoProcedimientoUsuario(), procedimientoEntity, usuarioEntity);
				procedimientoUsuarioToret.add(procedimientoUsuarioEntity);

			}
		}

		final ReturnBusquedas<ProcedimientoUsuarioEntity> result = new ReturnBusquedas<>(procedimientoUsuarioToret,
				datosBusqueda, numberTotalResults, procedimientoUsuarioToret.size(), inicio);

		return result;

	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirProcedimientoUsuario(final ProcedimientoUsuario procedimientoUsuario)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, UsuarioNoEncontradoException,
			ParseException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException {
		final ProcedimientoUsuarioEntity procedimientoUsuarioEntity = procedimientoUsuario.getProcedimientoUsuario();
		final Boolean procedimientoUsuarioValido = validaciones
				.comprobarProcedimientoUsuarioBlank(procedimientoUsuarioEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procedimientoUsuarioValido) {
			final ProcedimientoUsuarioEntity procedimientoUsuarioBD = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByProcedimientoAndUsuario(
							procedimientoUsuario.getProcedimientoUsuario().getUsuario(),
							procedimientoUsuario.getProcedimientoUsuario().getProcedimiento());

			if (procedimientoUsuarioBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procedimientoUsuario.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoUsuarioYaExisteException(
						CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final ProcedimientoEntity procedimiento = procedimientoUsuario.getProcedimientoUsuario()
						.getProcedimiento();
				final UsuarioEntity usuario = procedimientoUsuario.getProcedimientoUsuario().getUsuario();
				final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
						.findById(procedimiento.getIdProcedimiento());
				final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(usuario.getDniUsuario());

				if (!procedimientoBD.isPresent()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procedimientoUsuario.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
							CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new ProcedimientoNoExisteException(
							CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
				} else {
					if (!usuarioBD.isPresent()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procedimientoUsuario.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
								CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new UsuarioNoEncontradoException(
								CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
					} else {
						procedimientoUsuarioEntity.setProcedimiento(procedimientoBD.get());
						procedimientoUsuarioEntity.setUsuario(usuarioBD.get());
						procedimientoUsuarioEntity.setBorradoProcedimientoUsuario(0);
						procedimientoUsuarioEntity.setPuntuacionProcedimientoUsuario(0);
						procedimientoUsuarioEntity.setFechaProcedimientoUsuario(new Date());

						procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuarioEntity);

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(
								procedimientoUsuario.getUsuario(), Constantes.ACCION_AÑADIR_PROCEDIMIENTOUSUARIO,
								procedimientoUsuario.getProcedimientoUsuario().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}

						resultado = Constantes.OK;
					}

				}

			}
		} else {
			resultado = CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name();
		}
		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarProcedimientoUsuario(final ProcedimientoUsuario procedimientoUsuario)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, UsuarioNoEncontradoException,
			ParseException, ProcedimientoUsuarioNoExisteException, ProcedimientoNoExisteException {
		final ProcedimientoUsuarioEntity procedimientoUsuarioEntity = procedimientoUsuario.getProcedimientoUsuario();
		final Boolean procedimientoUsuarioValido = validaciones
				.comprobarProcedimientoUsuarioBlank(procedimientoUsuarioEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procedimientoUsuarioValido) {
			final ProcedimientoUsuarioEntity procedimientoUsuarioBD = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByProcedimientoAndUsuario(
							procedimientoUsuario.getProcedimientoUsuario().getUsuario(),
							procedimientoUsuario.getProcedimientoUsuario().getProcedimiento());

			if (procedimientoUsuarioBD == null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procedimientoUsuario.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoUsuarioNoExisteException(
						CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final ProcedimientoEntity procedimiento = procedimientoUsuario.getProcedimientoUsuario()
						.getProcedimiento();
				final UsuarioEntity usuario = procedimientoUsuario.getProcedimientoUsuario().getUsuario();
				final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
						.findById(procedimiento.getIdProcedimiento());
				final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(usuario.getDniUsuario());

				if (!procedimientoBD.isPresent()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procedimientoUsuario.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
							CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new ProcedimientoNoExisteException(
							CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
				} else {
					if (!usuarioBD.isPresent()) {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								procedimientoUsuario.getUsuario(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
								CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new UsuarioNoEncontradoException(
								CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
					} else {
						procedimientoUsuarioBD.setProcedimiento(procedimientoBD.get());
						procedimientoUsuarioBD.setUsuario(usuarioBD.get());
						procedimientoUsuarioBD.setBorradoProcedimientoUsuario(
								procedimientoUsuarioEntity.getBorradoProcedimientoUsuario());
						procedimientoUsuarioBD.setPuntuacionProcedimientoUsuario(
								procedimientoUsuarioEntity.getPuntuacionProcedimientoUsuario());
						procedimientoUsuarioBD.setFechaProcedimientoUsuario(
								procedimientoUsuarioEntity.getFechaProcedimientoUsuario());
						procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuarioBD);

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(
								procedimientoUsuario.getUsuario(), Constantes.ACCION_MODIFICAR_PROCEDIMIENTOUSUARIO,
								procedimientoUsuario.getProcedimientoUsuario().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}

						resultado = Constantes.OK;

					}
				}
			}

		} else {
			resultado = CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name();
		}

		return resultado;
	}

	@Override
	public String eliminaProcedimientoUsuario(final ProcedimientoUsuario procedimientoUsuario)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException, ProcedimientoUsuarioNoExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
				.findById(procedimientoUsuario.getProcedimientoUsuario().getIdProcedimientoUsuario());

		if (!procedimientoUsuarioBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
					procedimientoUsuario.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new ProcedimientoUsuarioNoExisteException(
					CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			final List<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
					.findProcedimientoUsuarioProcesoByIdProcedimientoUsuario(
							procedimientoUsuario.getProcedimientoUsuario());

			if (!procedimientoUsuarioProcesoBD.isEmpty()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procedimientoUsuario.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO
										.getCodigo()),
						CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException(
						CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO
										.getCodigo()));
			} else {
				procedimientoUsuarioBD.get().setBorradoProcedimientoUsuario(1);
				procedimientoUsuario.setProcedimientoUsuario(procedimientoUsuarioBD.get());
				procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuarioBD.get());
				resultado = Constantes.OK;
			}
		}

		return resultado;
	}

	@Override
	public void deleteProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuarioEntity)
			throws ParseException, ProcedimientoUsuarioNoExisteException {
		final Boolean procedimientoUsuarioValido = validaciones
				.comprobarProcedimientoUsuarioBlank(procedimientoUsuarioEntity);

		if (procedimientoUsuarioValido) {
			final Optional<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
					.findById(procedimientoUsuarioEntity.getIdProcedimientoUsuario());

			if (!procedimientoUsuarioBD.isPresent()) {
				throw new ProcedimientoUsuarioNoExisteException(
						CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				procedimientoUsuarioRepository
						.deleteProcedimientoUsuario(procedimientoUsuarioEntity.getIdProcedimientoUsuario());
				procedimientoUsuarioRepository.flush();
			}
		}

	}

}
