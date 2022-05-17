package com.sds.service.noticias.impl;

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
import com.sds.model.NoticiasEntity;
import com.sds.repository.NoticiasRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NoticiaNoExisteException;
import com.sds.service.exception.NoticiaYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.noticias.NoticiasService;
import com.sds.service.noticias.model.Noticia;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class NoticiasServiceImpl implements NoticiasService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	NoticiasRepository noticiasRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public NoticiasServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<NoticiasEntity> buscarTodos() {

		final List<NoticiasEntity> toret = new ArrayList<>();

		final List<NoticiasEntity> noticias = entityManager
				.createNamedQuery(Constantes.NOTICIA_FINDNOTICIA_ORDER_BY_FECHA_LIMIT_20).setMaxResults(20)
				.getResultList();

		if (!noticias.isEmpty()) {
			for (final NoticiasEntity noticia : noticias) {
				final NoticiasEntity noticiaToret = new NoticiasEntity(noticia.getIdNoticia(),
						noticia.getTituloNoticia(), noticia.getTextoNoticia(), noticia.getFechaNoticia());

				toret.add(noticiaToret);
			}
		}

		final ReturnBusquedas<NoticiasEntity> result = new ReturnBusquedas<NoticiasEntity>(toret, toret.size(),
				noticias.size(), 0);

		return result;
	}

	@Override
	public ReturnBusquedas<NoticiasEntity> buscarTodosPaginacion(final int inicio, final int tamanhoPagina) {

		final List<NoticiasEntity> toret = new ArrayList<>();

		final List<NoticiasEntity> noticias = entityManager.createNamedQuery(Constantes.NOTICIA_FINDALL_QUERY)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = noticiasRepository.numberFindAllNoticias();

		if (!noticias.isEmpty()) {
			for (final NoticiasEntity noticia : noticias) {
				final NoticiasEntity noticiaToret = new NoticiasEntity(noticia.getIdNoticia(),
						noticia.getTituloNoticia(), noticia.getTextoNoticia(), noticia.getFechaNoticia());

				toret.add(noticiaToret);
			}
		}

		final ReturnBusquedas<NoticiasEntity> result = new ReturnBusquedas<NoticiasEntity>(toret, numberTotalResults,
				toret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<NoticiasEntity> buscarNoticia(final String tituloNoticia, final String textoNoticia,
			final Date fechaNoticia, final int inicio, final int tamanhoPagina) {

		final List<NoticiasEntity> toret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();
		String fecha = StringUtils.EMPTY;

		if (fechaNoticia != null) {
			java.sql.Date fechaSql;
			fechaSql = new java.sql.Date(fechaNoticia.getTime());
			fecha = fechaSql.toString();
		} else {
			fecha = StringUtils.EMPTY;
		}

		final List<NoticiasEntity> noticias = entityManager.createNamedQuery(Constantes.NOTICIA_FINDNOTICIA_QUERY)
				.setParameter(Constantes.TITULO_NOTICIA, tituloNoticia)
				.setParameter(Constantes.TEXTO_NOTICIA, textoNoticia).setParameter(Constantes.FECHA_NOTICIA, fecha)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = noticiasRepository.numberFindNoticia(tituloNoticia, textoNoticia, fecha);

		if (!noticias.isEmpty()) {
			for (final NoticiasEntity noticia : noticias) {
				final NoticiasEntity noticiaToret = new NoticiasEntity(noticia.getIdNoticia(),
						noticia.getTituloNoticia(), noticia.getTextoNoticia(), noticia.getFechaNoticia());

				toret.add(noticiaToret);
			}
		}

		datosBusqueda.add(Constantes.TITULO_NOTICIA + Constantes.DOS_PUNTOS + tituloNoticia);
		datosBusqueda.add(Constantes.TEXTO_NOTICIA + Constantes.DOS_PUNTOS + textoNoticia);
		datosBusqueda.add(Constantes.FECHA_NOTICIA + Constantes.DOS_PUNTOS + fechaNoticia);

		final ReturnBusquedas<NoticiasEntity> result = new ReturnBusquedas<NoticiasEntity>(toret, datosBusqueda,
				numberTotalResults, toret.size(), inicio);

		return result;
	}

	@Override
	public ReturnBusquedas<NoticiasEntity> buscarNoticiaSinFecha(final String tituloNoticia, final String textoNoticia,
			final int inicio, final int tamanhoPagina) {

		final List<NoticiasEntity> toret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		final List<NoticiasEntity> noticias = entityManager
				.createNamedQuery(Constantes.NOTICIA_FINDNOTICIA_WITHOUTDATE_QUERY)
				.setParameter(Constantes.TITULO_NOTICIA, tituloNoticia)
				.setParameter(Constantes.TEXTO_NOTICIA, textoNoticia).setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = noticiasRepository.numberFindNoticiaWithoutDate(tituloNoticia, textoNoticia);

		if (!noticias.isEmpty()) {
			for (final NoticiasEntity noticia : noticias) {
				final NoticiasEntity noticiaToret = new NoticiasEntity(noticia.getIdNoticia(),
						noticia.getTituloNoticia(), noticia.getTextoNoticia(), noticia.getFechaNoticia());

				toret.add(noticiaToret);
			}
		}

		datosBusqueda.add(Constantes.TITULO_NOTICIA + Constantes.DOS_PUNTOS + tituloNoticia);
		datosBusqueda.add(Constantes.TEXTO_NOTICIA + Constantes.DOS_PUNTOS + textoNoticia);

		final ReturnBusquedas<NoticiasEntity> result = new ReturnBusquedas<NoticiasEntity>(toret, datosBusqueda,
				numberTotalResults, toret.size(), inicio);

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String añadirNoticia(final Noticia noticia)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, NoticiaYaExisteException {
		final NoticiasEntity noticiaEntity = noticia.getNoticiaEntity();
		final Boolean noticiaValida = validaciones.comprobarNoticiaBlank(noticiaEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (noticiaValida) {
			final NoticiasEntity noticiaBD = noticiasRepository.findByTituloNoticia(noticiaEntity.getTituloNoticia());

			if (noticiaBD != null) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(noticia.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.NOTICIA_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.NOTICIA_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new NoticiaYaExisteException(CodeMessageErrors.NOTICIA_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.NOTICIA_YA_EXISTE_EXCEPTION.getCodigo()));
			} else {
				final java.util.Date fechaActual = new Date();
				noticiaEntity.setFechaNoticia(fechaActual);
				noticiasRepository.saveAndFlush(noticiaEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(noticia.getUsuario(),
						Constantes.ACCION_AÑADIR_NOTICIA, noticia.getNoticiaEntity().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			}

		} else {
			resultado = CodeMessageErrors.NOTICIA_VACIA.name();
		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarNoticia(final Noticia noticia)
			throws LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, NoticiaNoExisteException {
		final NoticiasEntity noticiaEntity = noticia.getNoticiaEntity();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		final Boolean noticiaValida = validaciones.comprobarNoticiaBlank(noticiaEntity);

		if (noticiaValida) {
			final Optional<NoticiasEntity> noticiaBD = noticiasRepository.findById(noticiaEntity.getIdNoticia());

			if (!noticiaBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(noticia.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.NOTICIA_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.NOTICIA_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new NoticiaNoExisteException(CodeMessageErrors.NOTICIA_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.NOTICIA_NO_EXISTE_EXCEPTION.getCodigo()));

			} else {
				noticiaBD.get().setTituloNoticia(noticiaEntity.getTituloNoticia());
				noticiaBD.get().setTextoNoticia(noticiaEntity.getTextoNoticia());
				final java.util.Date fechaModificacion = new Date();
				noticiaBD.get().setFechaNoticia(fechaModificacion);
				noticiasRepository.saveAndFlush(noticiaBD.get());

				final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(noticia.getUsuario(),
						Constantes.ACCION_BUSCAR_NOTICIA, noticiaBD.toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(noticia.getUsuario(),
						Constantes.ACCION_MODIFICAR_NOTICIA, noticia.getNoticiaEntity().toString());

				resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
						|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.NOTICIA_VACIA.name();
		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String deleteNoticia(final Noticia noticia)
			throws NoticiaNoExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;
		final Boolean noticiaValida = validaciones.comprobarNoticiaBlank(noticia.getNoticiaEntity());

		if (noticiaValida) {
			final Optional<NoticiasEntity> noticiaBD = noticiasRepository
					.findById(noticia.getNoticiaEntity().getIdNoticia());

			if (!noticiaBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(noticia.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.NOTICIA_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.NOTICIA_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new NoticiaNoExisteException(CodeMessageErrors.NOTICIA_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.NOTICIA_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				noticiasRepository.deleteNoticia(noticia.getNoticiaEntity().getIdNoticia());
				noticiasRepository.flush();
				resultado = Constantes.OK;

				final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(noticia.getUsuario(),
						Constantes.ACCION_ELIMINAR_NOTICIA, noticiaBD.toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(noticia.getUsuario(),
						Constantes.ACCION_ELIMINAR_NOTICIA, noticia.getNoticiaEntity().toString());

				resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
						|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		}

		return resultado;
	}
}
