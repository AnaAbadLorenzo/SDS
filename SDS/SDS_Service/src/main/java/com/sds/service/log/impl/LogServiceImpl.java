package com.sds.service.log.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.repository.LogAccionesRepository;
import com.sds.repository.LogExcepcionesRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.log.LogService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class LogServiceImpl implements LogService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	LogExcepcionesRepository logExcepcionesRepository;

	@Autowired
	LogAccionesRepository logAccionesRepository;

	final Validaciones validaciones;

	public LogServiceImpl() {
		this.validaciones = new Validaciones();
	}

	@Override
	public ReturnBusquedas<LogExcepcionesEntity> buscarTodosLogExcepciones(final int inicio, final int tamanhoPagina) {

		final List<LogExcepcionesEntity> toret = new ArrayList<>();
		final List<LogExcepcionesEntity> logExcepciones = entityManager
				.createNamedQuery(Constantes.LOGEXCEPCIONES_QUERY_FINDALL).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = logExcepcionesRepository.numberFindAllLogExcepciones();

		for (int i = 0; i < logExcepciones.size(); i++) {
			final LogExcepcionesEntity log = new LogExcepcionesEntity(logExcepciones.get(i).getIdLogExcepciones(),
					logExcepciones.get(i).getUsuario(), logExcepciones.get(i).getTipoExcepcion(),
					logExcepciones.get(i).getDescripcionExcepcion(), logExcepciones.get(i).getFecha());
			toret.add(log);
		}

		final ReturnBusquedas<LogExcepcionesEntity> result = new ReturnBusquedas<LogExcepcionesEntity>(toret,
				numberTotalResults, toret.size(), inicio);
		return result;
	}

	@Override
	public List<LogExcepcionesEntity> buscarPorUsuarioLogExcepciones(final String usuario) {
		final List<LogExcepcionesEntity> logExcepciones = logExcepcionesRepository.findByUsuario(usuario);
		final List<LogExcepcionesEntity> toret = new ArrayList<>();

		for (int i = 0; i < logExcepciones.size(); i++) {
			final LogExcepcionesEntity log = new LogExcepcionesEntity(logExcepciones.get(i).getIdLogExcepciones(),
					logExcepciones.get(i).getUsuario(), logExcepciones.get(i).getTipoExcepcion(),
					logExcepciones.get(i).getDescripcionExcepcion(), logExcepciones.get(i).getFecha());
			toret.add(log);
		}

		return toret;
	}

	@Override
	public ReturnBusquedas<LogExcepcionesEntity> buscarPorUsuarioYFechaLogExcepciones(final String usuario,
			final Date fechaInicio, final Date fechaFin, final int inicio, final int tamanhoPagina) {
		final List<LogExcepcionesEntity> toret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<LogExcepcionesEntity> logExcepciones = entityManager
				.createNamedQuery(Constantes.LOGEXCEPCIONES_QUERY_FINDLOG).setParameter(Constantes.USUARIO, usuario)
				.setParameter(Constantes.FECHA_INICIO, fechaInicio).setParameter(Constantes.FECHA_FIN, fechaFin)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = logExcepcionesRepository.numberFindLogExcepciones(usuario, fechaInicio,
				fechaFin);

		for (int i = 0; i < logExcepciones.size(); i++) {
			final LogExcepcionesEntity log = new LogExcepcionesEntity(logExcepciones.get(i).getIdLogExcepciones(),
					logExcepciones.get(i).getUsuario(), logExcepciones.get(i).getTipoExcepcion(),
					logExcepciones.get(i).getDescripcionExcepcion(), logExcepciones.get(i).getFecha());
			toret.add(log);
		}

		datosBusqueda.add(Constantes.USUARIO_LOG + Constantes.DOS_PUNTOS + usuario);
		datosBusqueda.add(Constantes.FECHA_INICIO + Constantes.DOS_PUNTOS + fechaInicio);
		datosBusqueda.add(Constantes.FECHA_FIN + Constantes.DOS_PUNTOS + fechaFin);

		final ReturnBusquedas<LogExcepcionesEntity> busqueda = new ReturnBusquedas<>(logExcepciones, datosBusqueda,
				numberTotalResults, logExcepciones.size(), inicio);

		return busqueda;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String guardarLogExcepciones(final LogExcepcionesEntity logExcepciones) {
		final Boolean logValido = validaciones.comprobarLogExcepcionesBlank(logExcepciones);
		String resultado = StringUtils.EMPTY;

		if (logValido) {
			logExcepcionesRepository.saveAndFlush(logExcepciones);
			return Constantes.OK;
		} else {
			resultado = CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name();
		}

		return resultado;
	}

	@Override
	public ReturnBusquedas<LogAccionesEntity> buscarTodosLogAcciones(final int inicio, final int tamanhoPagina) {

		final List<LogAccionesEntity> toret = new ArrayList<>();
		final List<LogAccionesEntity> logAcciones = entityManager.createNamedQuery(Constantes.LOGACCIONES_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = logAccionesRepository.numberFindAllLogAcciones();

		for (int i = 0; i < logAcciones.size(); i++) {
			final LogAccionesEntity log = new LogAccionesEntity(logAcciones.get(i).getIdLogAcciones(),
					logAcciones.get(i).getUsuario(), logAcciones.get(i).getAccion(), logAcciones.get(i).getDatos(),
					logAcciones.get(i).getFecha());
			toret.add(log);
		}

		final ReturnBusquedas<LogAccionesEntity> result = new ReturnBusquedas<LogAccionesEntity>(toret,
				numberTotalResults, toret.size(), inicio);

		return result;
	}

	@Override
	public List<LogAccionesEntity> buscarPorUsuarioLogAcciones(final String usuario) {
		final List<LogAccionesEntity> logAcciones = logAccionesRepository.findByUsuario(usuario);
		final List<LogAccionesEntity> toret = new ArrayList<>();

		for (int i = 0; i < logAcciones.size(); i++) {
			final LogAccionesEntity log = new LogAccionesEntity(logAcciones.get(i).getIdLogAcciones(),
					logAcciones.get(i).getUsuario(), logAcciones.get(i).getAccion(), logAcciones.get(i).getDatos(),
					logAcciones.get(i).getFecha());
			toret.add(log);
		}

		return toret;
	}

	@Override
	public List<LogAccionesEntity> buscarPorAccionLogAcciones(final String accion) {
		final List<LogAccionesEntity> logAcciones = logAccionesRepository.findByAccion(accion);
		final List<LogAccionesEntity> toret = new ArrayList<>();

		for (int i = 0; i < logAcciones.size(); i++) {
			final LogAccionesEntity log = new LogAccionesEntity(logAcciones.get(i).getIdLogAcciones(),
					logAcciones.get(i).getUsuario(), logAcciones.get(i).getAccion(), logAcciones.get(i).getDatos(),
					logAcciones.get(i).getFecha());
			toret.add(log);
		}

		return toret;
	}

	@Override
	public List<LogAccionesEntity> buscarPorUsuarioAccionLogAcciones(final String usuario, final String accion) {
		final List<LogAccionesEntity> logAcciones = logAccionesRepository.findByUsuarioAccion(usuario, accion);
		final List<LogAccionesEntity> toret = new ArrayList<>();

		for (int i = 0; i < logAcciones.size(); i++) {
			final LogAccionesEntity log = new LogAccionesEntity(logAcciones.get(i).getIdLogAcciones(),
					logAcciones.get(i).getUsuario(), logAcciones.get(i).getAccion(), logAcciones.get(i).getDatos(),
					logAcciones.get(i).getFecha());
			toret.add(log);
		}

		return toret;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String guardarLogAcciones(final LogAccionesEntity logAcciones) {
		final Boolean logValido = validaciones.comprobarLogAccionesBlank(logAcciones);
		String resultado = StringUtils.EMPTY;

		if (logValido) {
			logAccionesRepository.saveAndFlush(logAcciones);
			return Constantes.OK;
		} else {
			resultado = CodeMessageErrors.LOG_ACCIONES_VACIO.name();
		}

		return resultado;
	}

	@Override
	public ReturnBusquedas<LogAccionesEntity> buscarPorUsuarioYFechaLogAcciones(final String usuario,
			final Date fechaInicio, final Date fechaFin, final int inicio, final int tamanhoPagina) {
		final List<LogAccionesEntity> toret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<LogAccionesEntity> logAcciones = entityManager.createNamedQuery(Constantes.LOGACCIONES_QUERY_FINDLOG)
				.setParameter(Constantes.USUARIO, usuario).setParameter(Constantes.FECHA_INICIO, fechaInicio)
				.setParameter(Constantes.FECHA_FIN, fechaFin).setFirstResult(inicio).setMaxResults(tamanhoPagina)
				.getResultList();

		final Integer numberTotalResults = logAccionesRepository.numberFindLogAcciones(usuario, fechaInicio, fechaFin);

		for (int i = 0; i < logAcciones.size(); i++) {
			final LogAccionesEntity log = new LogAccionesEntity(logAcciones.get(i).getIdLogAcciones(),
					logAcciones.get(i).getUsuario(), logAcciones.get(i).getAccion(), logAcciones.get(i).getDatos(),
					logAcciones.get(i).getFecha());
			toret.add(log);
		}

		datosBusqueda.add(Constantes.USUARIO_LOG + Constantes.DOS_PUNTOS + usuario);
		datosBusqueda.add(Constantes.FECHA_INICIO + Constantes.DOS_PUNTOS + fechaInicio);
		datosBusqueda.add(Constantes.FECHA_FIN + Constantes.DOS_PUNTOS + fechaFin);

		final ReturnBusquedas<LogAccionesEntity> busqueda = new ReturnBusquedas<>(logAcciones, datosBusqueda,
				numberTotalResults, logAcciones.size(), inicio);

		return busqueda;
	}

}
