package com.sds.controller.objetivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.ObjetivoEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.ObjetivoYaExisteException;
import com.sds.service.objetivo.ObjetivoService;
import com.sds.service.objetivo.model.Objetivo;
import com.sds.service.objetivo.model.ObjetivoBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/objetivo")
public class ObjetivoController {

	@Autowired
	ObjetivoService objetivoService;

	private final Validaciones validaciones;

	public ObjetivoController() {
		validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarObjetivo")
	@ResponseBody
	public RespEntity buscarObjetivo(@RequestBody final ObjetivoBuscar objetivo) {

		final ReturnBusquedas<ObjetivoEntity> resultado = objetivoService.buscarObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo(), objetivo.getInicio(), objetivo.getTamanhoPagina());

		return new RespEntity(RespCode.OBJETIVO_ENCONTRADO, resultado);

	}

	@PostMapping(value = "/listarObjetivos")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<ObjetivoEntity> resultado = objetivoService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.OBJETIVOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarObjetivosEliminados")
	@ResponseBody
	public RespEntity buscarObjetivosEliminados(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<ObjetivoEntity> resultado = objetivoService
				.buscarObjetivosEliminados(paginacion.getInicio(), paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.OBJETIVOS_ELIMINADOS_LISTADOS, resultado);

	}

	@PostMapping(value = "/objetivo")
	@ResponseBody
	public RespEntity guardarObjetivo(@RequestBody final Objetivo objetivo) {

		final Boolean objetivoValido = validaciones.comprobarObjetivoBlank(objetivo.getObjetivo());

		if (Boolean.TRUE.equals(objetivoValido)) {
			try {
				String resultado;
				try {
					resultado = objetivoService.anadirObjetivo(objetivo);
					if (CodeMessageErrors.OBJETIVO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.OBJETIVO_VACIO, objetivo);
					}
					return new RespEntity(RespCode.OBJETIVO_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, objetivo);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, objetivo);
				}
			} catch (final ObjetivoYaExisteException objetivoExists) {
				return new RespEntity(RespCode.OBJETIVO_YA_EXISTE_EXCEPTION, objetivoExists);
			}
		}

		return new RespEntity(RespCode.OBJETIVO_VACIO, objetivo);
	}

	@PostMapping(value = "/modificarObjetivo")
	@ResponseBody
	public RespEntity modificarObjetivo(@RequestBody final Objetivo objetivo) {

		final Boolean objetivoValido = validaciones.comprobarObjetivoBlank(objetivo.getObjetivo());

		if (Boolean.TRUE.equals(objetivoValido)) {
			try {
				String resultado;
				try {
					resultado = objetivoService.modificarObjetivo(objetivo);
					if (CodeMessageErrors.OBJETIVO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.OBJETIVO_VACIO, objetivo);
					}
					return new RespEntity(RespCode.OBJETIVO_MODIFICADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, objetivo);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, objetivo);
				}
			} catch (final ObjetivoNoExisteException funcionalidadNoExists) {
				return new RespEntity(RespCode.OBJETIVO_NO_EXISTE_EXCEPTION, objetivo);
			}
		}

		return new RespEntity(RespCode.OBJETIVO_VACIO, objetivo);
	}

	@PostMapping(value = "/eliminarObjetivo")
	@ResponseBody
	public RespEntity eliminarObjetivo(@RequestBody final Objetivo objetivo) {

		try {
			String resultado;
			try {
				resultado = objetivoService.eliminaObjetivo(objetivo);
				return new RespEntity(RespCode.OBJETIVO_ELIMINADO, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, objetivo);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, objetivo);
			}
		} catch (final ObjetivoNoExisteException funcionalidadNoExists) {
			return new RespEntity(RespCode.OBJETIVO_NO_EXISTE_EXCEPTION, objetivo);
		}
	}

	@PostMapping(value = "/reactivarObjetivo")
	@ResponseBody
	public RespEntity reactivarObjetivo(@RequestBody final Objetivo objetivo) {

		try {
			String resultado;
			try {
				resultado = objetivoService.reactivarObjetivo(objetivo);
				return new RespEntity(RespCode.OBJETIVO_REACTIVADO, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, objetivo);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, objetivo);
			}
		} catch (final ObjetivoNoExisteException objetivoNoExists) {
			return new RespEntity(RespCode.OBJETIVO_NO_EXISTE_EXCEPTION, objetivo);
		}

	}

	@PostMapping(value = "/borrarObjetivo")
	@ResponseBody
	public RespEntity borrarObjetivo(@RequestBody final ObjetivoEntity objetivo) {
		try {
			objetivoService.deleteObjetivo(objetivo);
			return new RespEntity(RespCode.OBJETIVO_BORRADO, objetivo);
		} catch (final ObjetivoNoExisteException objetivoNoExiste) {
			return new RespEntity(RespCode.OBJETIVO_NO_EXISTE_EXCEPTION, objetivo);
		}
	}

}
