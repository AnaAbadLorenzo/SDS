package com.sds.service.log.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.sds.service.log.LogService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogExcepcionesRepository logExcepcionesRepository;

	@Autowired
	LogAccionesRepository logAccionesRepository;

	final Validaciones validaciones;

	public LogServiceImpl() {
		this.validaciones = new Validaciones();
	}

	@Override
	public List<LogExcepcionesEntity> buscarTodosLogExcepciones() {
		final List<LogExcepcionesEntity> logExcepciones = logExcepcionesRepository.findAll();
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
	public List<LogAccionesEntity> buscarTodosLogAcciones() {
		final List<LogAccionesEntity> logAcciones = logAccionesRepository.findAll();
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

}
