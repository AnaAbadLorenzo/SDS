package com.sds.controller.noticia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.NoticiasEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NoticiaNoExisteException;
import com.sds.service.exception.NoticiaYaExisteException;
import com.sds.service.noticias.NoticiasService;
import com.sds.service.noticias.model.Noticia;
import com.sds.service.noticias.model.NoticiaBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/noticia")
public class NoticiaController {

	@Autowired
	NoticiasService noticiasService;

	final Validaciones validaciones;

	public NoticiaController() {
		this.validaciones = new Validaciones();
	}

	@GetMapping(value = "/listarNoticias")
	@ResponseBody
	public RespEntity buscarTodos() {
		final ReturnBusquedas<NoticiasEntity> resultado = noticiasService.buscarTodos();

		return new RespEntity(RespCode.NOTICIAS_LISTADAS, resultado);
	}

	@PostMapping(value = "/listarNoticia")
	@ResponseBody
	public RespEntity buscarAccion(@RequestBody final NoticiaBuscar noticia) {

		final ReturnBusquedas<NoticiasEntity> resultado = noticiasService.buscarNoticia(noticia.getTituloNoticia(),
				noticia.getTextoNoticia(), noticia.getFechaNoticia(), noticia.getInicio(), noticia.getTamanhoPagina());

		return new RespEntity(RespCode.NOTICIA_ENCONTRADA, resultado);

	}

	@PostMapping(value = "/noticia")
	@ResponseBody
	public RespEntity guardarNoticia(@RequestBody final Noticia noticia) {

		final Boolean noticiaValida = validaciones.comprobarNoticiaBlank(noticia.getNoticiaEntity());

		if (Boolean.TRUE.equals(noticiaValida)) {
			try {
				String resultado;
				try {
					resultado = noticiasService.añadirNoticia(noticia);
					if (CodeMessageErrors.NOTICIA_VACIA.name().equals(resultado)) {
						return new RespEntity(RespCode.NOTICIA_VACIA, noticia);
					}
					return new RespEntity(RespCode.NOTICIA_GUARDADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, noticia);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, noticia);
				}
			} catch (final NoticiaYaExisteException noticiaAlreadyExists) {
				return new RespEntity(RespCode.NOTICIA_YA_EXISTE_EXCEPTION, noticia);
			}
		}

		return new RespEntity(RespCode.NOTICIA_VACIA, noticia);
	}

	@PostMapping(value = "/modificarNoticia")
	@ResponseBody
	public RespEntity modificarNoticia(@RequestBody final Noticia noticia) {

		final Boolean noticiaValida = validaciones.comprobarNoticiaBlank(noticia.getNoticiaEntity());

		if (Boolean.TRUE.equals(noticiaValida)) {
			try {
				String resultado;
				try {
					resultado = noticiasService.modificarNoticia(noticia);
					if (CodeMessageErrors.NOTICIA_VACIA.name().equals(resultado)) {
						return new RespEntity(RespCode.NOTICIA_VACIA, noticia);
					}
					return new RespEntity(RespCode.NOTICIA_MODIFICADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, noticia);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, noticia);
				}
			} catch (final NoticiaNoExisteException noticiaDontExists) {
				return new RespEntity(RespCode.NOTICIA_NO_EXISTE_EXCEPTION, noticia);
			}
		}

		return new RespEntity(RespCode.NOTICIA_VACIA, noticia);
	}

	@PostMapping(value = "/borrarNoticia")
	@ResponseBody
	public RespEntity borrarNoticia(@RequestBody final NoticiasEntity noticia) {
		try {
			noticiasService.deleteNoticia(noticia);
			return new RespEntity(RespCode.NOTICIA_BORRADA, noticia);
		} catch (final NoticiaNoExisteException e) {
			return new RespEntity(RespCode.NOTICIA_NO_EXISTE_EXCEPTION, noticia);
		}
	}

}
