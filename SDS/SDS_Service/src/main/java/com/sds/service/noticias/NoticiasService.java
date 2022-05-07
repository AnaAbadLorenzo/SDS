package com.sds.service.noticias;

import java.util.Date;

import com.sds.model.NoticiasEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NoticiaNoExisteException;
import com.sds.service.exception.NoticiaYaExisteException;
import com.sds.service.noticias.model.Noticia;

public interface NoticiasService {

	ReturnBusquedas<NoticiasEntity> buscarNoticia(final String tituloNoticia, final String textoNoticia,
			final Date fechaNoticia, final int inicio, final int tamanhoPagina);

	ReturnBusquedas<NoticiasEntity> buscarTodos();

	ReturnBusquedas<NoticiasEntity> buscarTodosPaginacion(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<NoticiasEntity> buscarNoticiaSinFecha(final String tituloNoticia, final String textoNoticia,
			final int inicio, final int tamanhoPagina);

	String añadirNoticia(final Noticia noticia)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, NoticiaYaExisteException;

	String modificarNoticia(final Noticia noticia)
			throws LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, NoticiaNoExisteException;

	String deleteNoticia(final Noticia noticia)
			throws NoticiaNoExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;
}
