package com.sds.controller.respuestaposible;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RespuestaPosibleAsociadaProcesoException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.exception.RespuestaPosibleYaExisteException;
import com.sds.service.respuestaposible.RespuestaPosibleService;
import com.sds.service.respuestaposible.model.RespuestaPosible;
import com.sds.service.respuestaposible.model.RespuestaPosibleBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/respuestaPosible")
public class RespuestaPosibleController {

	@Autowired
	RespuestaPosibleService respuestaPosibleService;

	private final Validaciones validaciones;

	public RespuestaPosibleController() {
		validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarRespuestaPosible")
	@ResponseBody
	public RespEntity buscarRespuestaPosible(@RequestBody final RespuestaPosibleBuscar respuestaPosible) {

		final ReturnBusquedas<RespuestaPosibleEntity> resultado = respuestaPosibleService.buscarRespuestaPosible(
				respuestaPosible.getTextoRespuesta(), respuestaPosible.getFechaRespuesta(),
				respuestaPosible.getInicio(), respuestaPosible.getTamanhoPagina());

		return new RespEntity(RespCode.RESPUESTA_POSIBLE_ECONTRADA, resultado);

	}

	@PostMapping(value = "/listarRespuestasPosibles")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<RespuestaPosibleEntity> resultado = respuestaPosibleService
				.buscarTodas(paginacion.getInicio(), paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.RESPUESTAS_POSIBLES_LISTADAS, resultado);
	}

	@PostMapping(value = "/listarRespuestasPosiblesEliminadas")
	@ResponseBody
	public RespEntity buscarRespuestasPosiblesEliminadas(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<RespuestaPosibleEntity> resultado = respuestaPosibleService
				.buscarRespuestasPosiblesEliminadas(paginacion.getInicio(), paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.RESPUESTAS_POSIBLES_ELIMINADAS_LISTADAS, resultado);

	}

	@PostMapping(value = "/respuestaPosible")
	@ResponseBody
	public RespEntity guardarRespuestaPosible(@RequestBody final RespuestaPosible respuestaPosible) {

		final Boolean respuestaPosibleValida = validaciones
				.comprobarTextoRespuestaPosibleBlank(respuestaPosible.getRespuestaPosibleEntity().getTextoRespuesta());

		if (Boolean.TRUE.equals(respuestaPosibleValida)) {
			try {
				String resultado;
				try {
					resultado = respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);
					if (CodeMessageErrors.RESPUESTA_POSIBLE_VACIA.name().equals(resultado)) {
						return new RespEntity(RespCode.RESPUESTA_POSIBLE_VACIA, respuestaPosible);
					}
					return new RespEntity(RespCode.RESPUESTA_POSIBLE_GUARDADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, respuestaPosible);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, respuestaPosible);
				}
			} catch (final RespuestaPosibleYaExisteException respuestaPosibleAlreadyExists) {
				return new RespEntity(RespCode.RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION, respuestaPosibleAlreadyExists);
			}
		}

		return new RespEntity(RespCode.RESPUESTA_POSIBLE_VACIA, respuestaPosible);
	}

	@PostMapping(value = "/modificarRespuestaPosible")
	@ResponseBody
	public RespEntity modificarRespuestaPosible(@RequestBody final RespuestaPosible respuestaPosible) {

		final Boolean respuestaPosibleValida = validaciones
				.comprobarTextoRespuestaPosibleBlank(respuestaPosible.getRespuestaPosibleEntity().getTextoRespuesta());

		if (Boolean.TRUE.equals(respuestaPosibleValida)) {
			try {
				String resultado;
				try {
					resultado = respuestaPosibleService.modificarRespuestaPosible(respuestaPosible);
					if (CodeMessageErrors.RESPUESTA_POSIBLE_VACIA.name().equals(resultado)) {
						return new RespEntity(RespCode.RESPUESTA_POSIBLE_VACIA, respuestaPosible);
					}
					return new RespEntity(RespCode.RESPUESTA_POSIBLE_MODIFICADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, respuestaPosible);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, respuestaPosible);
				}
			} catch (final RespuestaPosibleNoExisteException respuestaPosibleNoExists) {
				return new RespEntity(RespCode.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION, respuestaPosible);
			}
		}

		return new RespEntity(RespCode.RESPUESTA_POSIBLE_VACIA, respuestaPosible);
	}

	@PostMapping(value = "/eliminarRespuestaPosible")
	@ResponseBody
	public RespEntity eliminarRespuestaPosible(@RequestBody final RespuestaPosible respuestaPosible) {

		try {
			String resultado;
			try {
				resultado = respuestaPosibleService.eliminarRespuestaPosible(respuestaPosible);
				return new RespEntity(RespCode.RESPUESTA_POSIBLE_ELIMINADA, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, respuestaPosible);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, respuestaPosible);
			}
		} catch (final RespuestaPosibleNoExisteException respuestaPosibleNoExists) {
			return new RespEntity(RespCode.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION, respuestaPosible);
		} catch (final RespuestaPosibleAsociadaProcesoException respuestaPosibleAssociatedProceso) {
			return new RespEntity(RespCode.RESPUESTA_ASOCIADA_PROCESO_EXCEPTION, respuestaPosible);
		}

	}

	@PostMapping(value = "/reactivarRespuestaPosible")
	@ResponseBody
	public RespEntity reactivarRespuestaPosible(@RequestBody final RespuestaPosible respuestaPosible) {

		try {
			String resultado;
			try {
				resultado = respuestaPosibleService.reactivarRespuestaPosible(respuestaPosible);
				return new RespEntity(RespCode.RESPUESTA_POSIBLE_REACTIVADA, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, respuestaPosible);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, respuestaPosible);
			}
		} catch (final RespuestaPosibleNoExisteException respuestaPosibleNoExists) {
			return new RespEntity(RespCode.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION, respuestaPosible);
		}

	}

	@PostMapping(value = "/borrarRespuestaPosible")
	@ResponseBody
	public RespEntity borrarRespuestaPosible(@RequestBody final RespuestaPosibleEntity respuestaPosible) {
		try {
			respuestaPosibleService.deleteRespuestaPosible(respuestaPosible);
			return new RespEntity(RespCode.RESPUESTA_POSIBLE_BORRADA, respuestaPosible);
		} catch (final RespuestaPosibleNoExisteException funcionalidadNoExiste) {
			return new RespEntity(RespCode.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION, respuestaPosible);
		}
	}

}
