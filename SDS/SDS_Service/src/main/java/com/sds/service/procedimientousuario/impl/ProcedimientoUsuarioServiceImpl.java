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
import com.sds.model.PlanEntity;
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
	public ReturnBusquedas<ProcedimientoUsuarioEntity> buscarProcedimientoUsuario(final Date fechaProcedimientoUsuario,
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

		if (fechaProcedimientoUsuario == null && procedimiento == null) {
			final UsuarioEntity usuarioBD = usuarioRepository.findByUsuario(usuario.getUsuario());
			procedimientosUsuario = entityManager
					.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOSUSUARIOBYUSUARIO)
					.setParameter(Constantes.USUARIO, usuarioBD).setFirstResult(inicio).setMaxResults(tamanhoPagina)
					.getResultList();
			numberTotalResults = procedimientoUsuarioRepository.numberFindProcedimientoUsuarioByUsuario(usuarioBD);
		}

		else {
			if (usuario == null || usuario.getUsuario().equals(StringUtils.EMPTY)) {
				if (procedimiento == null || procedimiento.getNombreProcedimiento().equals(StringUtils.EMPTY)) {
					procedimientosUsuario = entityManager
							.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOUSUARIO)
							.setParameter(Constantes.FECHA_PROCEDIMIENTO_USUARIO, fecha)
							.setParameter(Constantes.USUARIO, StringUtils.EMPTY)
							.setParameter(Constantes.PROCEDIMIENTO, StringUtils.EMPTY).setFirstResult(inicio)
							.setMaxResults(tamanhoPagina).getResultList();
					numberTotalResults = procedimientoUsuarioRepository
							.numberFindProcedimientoUsuarioWithoutProcedimientoAndUsuario(fecha);
				} else {
					final ProcedimientoEntity procedimientoBD = procedimientoRepository
							.findProcedimientoByName(procedimiento.getNombreProcedimiento());
					procedimientosUsuario = entityManager
							.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOUSUARIO)
							.setParameter(Constantes.FECHA_PROCEDIMIENTO_USUARIO, fecha)
							.setParameter(Constantes.USUARIO, StringUtils.EMPTY)
							.setParameter(Constantes.PROCEDIMIENTO, procedimientoBD).setFirstResult(inicio)
							.setMaxResults(tamanhoPagina).getResultList();
					numberTotalResults = procedimientoUsuarioRepository
							.numberFindProcedimientoUsuarioWithoutUsuario(fecha, procedimientoBD);
				}
			} else {
				final UsuarioEntity usuarioBD = usuarioRepository.findByUsuario(usuario.getUsuario());
				if (procedimiento == null || procedimiento.getNombreProcedimiento().equals(StringUtils.EMPTY)) {
					procedimientosUsuario = entityManager
							.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOUSUARIO)
							.setParameter(Constantes.FECHA_PROCEDIMIENTO_USUARIO, fecha)
							.setParameter(Constantes.USUARIO, usuarioBD)
							.setParameter(Constantes.PROCEDIMIENTO, StringUtils.EMPTY).setFirstResult(inicio)
							.setMaxResults(tamanhoPagina).getResultList();
					numberTotalResults = procedimientoUsuarioRepository
							.numberFindProcedimientoUsuarioWithoutProcedimiento(fecha, usuarioBD);
				} else {
					final ProcedimientoEntity procedimientoBD = procedimientoRepository
							.findProcedimientoByName(procedimiento.getNombreProcedimiento());
					procedimientosUsuario = entityManager
							.createNamedQuery(Constantes.PROCEDIMIENTOUSUARIO_QUERY_FINDPROCEDIMIENTOUSUARIO)
							.setParameter(Constantes.FECHA_PROCEDIMIENTO_USUARIO, fecha)
							.setParameter(Constantes.USUARIO, usuarioBD)
							.setParameter(Constantes.PROCEDIMIENTO, procedimientoBD).setFirstResult(inicio)
							.setMaxResults(tamanhoPagina).getResultList();
					numberTotalResults = procedimientoUsuarioRepository.numberFindProcedimientoUsuario(fecha, usuarioBD,
							procedimientoBD);
				}
			}
		}

		if (!procedimientosUsuario.isEmpty()) {
			for (final ProcedimientoUsuarioEntity procedimientoUsuario : procedimientosUsuario) {
				final PlanEntity plan = new PlanEntity(procedimientoUsuario.getProcedimiento().getPlan().getIdPlan(),
						procedimientoUsuario.getProcedimiento().getPlan().getNombrePlan(),
						procedimientoUsuario.getProcedimiento().getPlan().getDescripPlan(),
						procedimientoUsuario.getProcedimiento().getPlan().getFechaPlan(),
						procedimientoUsuario.getProcedimiento().getPlan().getBorradoPlan());
				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimientoUsuario.getProcedimiento().getIdProcedimiento(),
						procedimientoUsuario.getProcedimiento().getNombreProcedimiento(),
						procedimientoUsuario.getProcedimiento().getDescripProcedimiento(),
						procedimientoUsuario.getProcedimiento().getFechaProcedimiento(),
						procedimientoUsuario.getProcedimiento().getBorradoProcedimiento(),
						procedimientoUsuario.getProcedimiento().getCheckUsuario(), plan);
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

		datosBusqueda.add(Constantes.FECHA_PROCEDIMIENTO_USUARIO + Constantes.DOS_PUNTOS + fechaProcedimientoUsuario);

		if (procedimiento != null) {
			datosBusqueda
					.add(Constantes.PROCEDIMIENTO + Constantes.DOS_PUNTOS + procedimiento.getNombreProcedimiento());
		}
		if (usuario != null) {
			datosBusqueda.add(Constantes.USER + Constantes.DOS_PUNTOS + usuario.getUsuario());
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
				final PlanEntity plan = new PlanEntity(procedimientoUsuario.getProcedimiento().getPlan().getIdPlan(),
						procedimientoUsuario.getProcedimiento().getPlan().getNombrePlan(),
						procedimientoUsuario.getProcedimiento().getPlan().getDescripPlan(),
						procedimientoUsuario.getProcedimiento().getPlan().getFechaPlan(),
						procedimientoUsuario.getProcedimiento().getPlan().getBorradoPlan());

				final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
						procedimientoUsuario.getProcedimiento().getIdProcedimiento(),
						procedimientoUsuario.getProcedimiento().getNombreProcedimiento(),
						procedimientoUsuario.getProcedimiento().getDescripProcedimiento(),
						procedimientoUsuario.getProcedimiento().getFechaProcedimiento(),
						procedimientoUsuario.getProcedimiento().getBorradoProcedimiento(),
						procedimientoUsuario.getProcedimiento().getCheckUsuario(), plan);

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
	public ProcedimientoUsuarioEntity buscarProcedimientoUsuarioById(final Integer idProcedimientoUsuario) {
		ProcedimientoUsuarioEntity procedimientoUsuarioEntity = new ProcedimientoUsuarioEntity();

		final Optional<ProcedimientoUsuarioEntity> procedimientoUsuario = procedimientoUsuarioRepository
				.findById(idProcedimientoUsuario);

		if (procedimientoUsuario.isPresent()) {
			final PlanEntity plan = new PlanEntity(procedimientoUsuario.get().getProcedimiento().getPlan().getIdPlan(),
					procedimientoUsuario.get().getProcedimiento().getPlan().getNombrePlan(),
					procedimientoUsuario.get().getProcedimiento().getPlan().getDescripPlan(),
					procedimientoUsuario.get().getProcedimiento().getPlan().getFechaPlan(),
					procedimientoUsuario.get().getProcedimiento().getPlan().getBorradoPlan());

			final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity(
					procedimientoUsuario.get().getProcedimiento().getIdProcedimiento(),
					procedimientoUsuario.get().getProcedimiento().getNombreProcedimiento(),
					procedimientoUsuario.get().getProcedimiento().getDescripProcedimiento(),
					procedimientoUsuario.get().getProcedimiento().getFechaProcedimiento(),
					procedimientoUsuario.get().getProcedimiento().getBorradoProcedimiento(),
					procedimientoUsuario.get().getProcedimiento().getCheckUsuario(), plan);

			final UsuarioEntity usuarioEntity = new UsuarioEntity(
					procedimientoUsuario.get().getUsuario().getDniUsuario(),
					procedimientoUsuario.get().getUsuario().getUsuario(),
					procedimientoUsuario.get().getUsuario().getBorradoUsuario());
			procedimientoUsuarioEntity = new ProcedimientoUsuarioEntity(
					procedimientoUsuario.get().getIdProcedimientoUsuario(),
					procedimientoUsuario.get().getPuntuacionProcedimientoUsuario(),
					procedimientoUsuario.get().getFechaProcedimientoUsuario(),
					procedimientoUsuario.get().getBorradoProcedimientoUsuario(), procedimientoEntity, usuarioEntity);

		}

		return procedimientoUsuarioEntity;

	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String anadirProcedimientoUsuario(final ProcedimientoUsuario procedimientoUsuario)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, UsuarioNoEncontradoException,
			ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException {
		final ProcedimientoUsuarioEntity procedimientoUsuarioEntity = procedimientoUsuario.getProcedimientoUsuario();
		final Boolean procedimientoUsuarioValido = validaciones
				.comprobarProcedimientoUsuarioBlank(procedimientoUsuarioEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procedimientoUsuarioValido) {
			final UsuarioEntity usuario = procedimientoUsuario.getProcedimientoUsuario().getUsuario();
			final UsuarioEntity usuarioBD = usuarioRepository.findByUsuario(usuario.getUsuario());

			if (Constantes.NO.equals(procedimientoUsuario.getVolverGuardar())) {
				final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
						.findProcedimientoUsuarioByProcedimientoAndUsuario(usuarioBD,
								procedimientoUsuario.getProcedimientoUsuario().getProcedimiento());
				if (procedimientoUsuarioBD.size() != 0) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procedimientoUsuario.getUsuario(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo()),
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new ProcedimientoUsuarioYaExisteException(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.PROCEDIMIENTOUSUARIO_YA_EXISTE_EXCEPTION.getCodigo()));
				}
			}

			final ProcedimientoEntity procedimiento = procedimientoUsuario.getProcedimientoUsuario().getProcedimiento();
			final Optional<ProcedimientoEntity> procedimientoBD = procedimientoRepository
					.findById(procedimiento.getIdProcedimiento());

			if (!procedimientoBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procedimientoUsuario.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoNoExisteException(
						CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				if (usuarioBD == null) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
							procedimientoUsuario.getUsuario(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
							CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new UsuarioNoEncontradoException(
							CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
				} else {
					procedimientoUsuarioEntity.setProcedimiento(procedimientoBD.get());
					procedimientoUsuarioEntity.setUsuario(usuarioBD);
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
				.comprobarProcedimientoUsuarioBlankModificar(procedimientoUsuarioEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (procedimientoUsuarioValido) {
			final List<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByNombreProcedimientoAndLoginUsuario(
							procedimientoUsuario.getProcedimientoUsuario().getUsuario().getUsuario(),
							procedimientoUsuario.getProcedimientoUsuario().getProcedimiento().getNombreProcedimiento());

			if (procedimientoUsuarioBD.size() == 0) {
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
				final ProcedimientoEntity procedimientoBD = procedimientoRepository
						.findProcedimientoByName(procedimiento.getNombreProcedimiento());
				final UsuarioEntity usuarioBD = usuarioRepository.findByUsuario(usuario.getUsuario());

				if (procedimientoBD == null) {
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
					if (usuarioBD == null) {
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
						procedimientoUsuarioBD.get(0).setProcedimiento(procedimientoBD);
						procedimientoUsuarioBD.get(0).setUsuario(usuarioBD);
						procedimientoUsuarioBD.get(0).setBorradoProcedimientoUsuario(
								procedimientoUsuarioEntity.getBorradoProcedimientoUsuario());
						procedimientoUsuarioBD.get(0).setPuntuacionProcedimientoUsuario(
								procedimientoUsuarioEntity.getPuntuacionProcedimientoUsuario());
						procedimientoUsuarioBD.get(0).setFechaProcedimientoUsuario(
								procedimientoUsuarioEntity.getFechaProcedimientoUsuario());
						procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuarioBD.get(0));

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
			ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException, ProcedimientoUsuarioNoExisteException,
			UsuarioNoEncontradoException, ParseException, ProcedimientoNoExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Optional<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
				.findById(procedimientoUsuario.getProcedimientoUsuario().getIdProcedimientoUsuario());

		final ProcedimientoEntity procedimientoBD = procedimientoRepository.findProcedimientoByName(
				procedimientoUsuario.getProcedimientoUsuario().getProcedimiento().getNombreProcedimiento());
		final UsuarioEntity usuarioBD = usuarioRepository
				.findByUsuario(procedimientoUsuario.getProcedimientoUsuario().getUsuario().getUsuario());

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
							procedimientoUsuario.getProcedimientoUsuario().getIdProcedimientoUsuario());

			if (!procedimientoUsuarioProcesoBD.isEmpty()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
						procedimientoUsuario.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO.getCodigo()),
						CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException(
						CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTOUSUARIO.getCodigo()));
			} else {
				procedimientoUsuarioBD.get().setProcedimiento(procedimientoBD);
				procedimientoUsuarioBD.get().setUsuario(usuarioBD);
				procedimientoUsuarioBD.get().setBorradoProcedimientoUsuario(1);
				procedimientoUsuario.setProcedimientoUsuario(procedimientoUsuarioBD.get());
				modificarProcedimientoUsuario(procedimientoUsuario);
				resultado = Constantes.OK;
			}
		}

		return resultado;
	}

	@Override
	public void deleteProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuarioEntity)
			throws ParseException, ProcedimientoUsuarioNoExisteException {

		final Optional<ProcedimientoUsuarioEntity> procedimientoUsuarioBD = procedimientoUsuarioRepository
				.findById(procedimientoUsuarioEntity.getIdProcedimientoUsuario());

		if (!procedimientoUsuarioBD.isPresent()) {
			throw new ProcedimientoUsuarioNoExisteException(
					CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCEDIMIENTOUSUARIO_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			procedimientoUsuarioRepository.delete(procedimientoUsuarioEntity);
			procedimientoUsuarioRepository.flush();
		}

	}

}
