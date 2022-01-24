package com.sds.controller.accion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.AccionEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.accion.AccionService;
import com.sds.service.accion.model.Accion;
import com.sds.service.accion.model.AccionBuscar;
import com.sds.service.exception.AccionAsociadaRolFuncionalidadException;
import com.sds.service.exception.AccionNoExisteException;
import com.sds.service.exception.AccionYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/accion")
public class AccionController {

	@Autowired
	AccionService accionService;

	final Validaciones validaciones;

	public AccionController() {
		this.validaciones = new Validaciones();
	}

	@RequestMapping(value = "/listarAccion", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarAccion(@RequestBody final AccionBuscar accion) {

		final List<AccionEntity> resultado = accionService.buscarAccion(accion.getNombreAccion(),
				accion.getDescripAccion());

		return new RespEntity(RespCode.ACCION_ENCONTRADA, resultado);

	}

	@RequestMapping(value = "/listarAcciones", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarTodos() {
		final List<AccionEntity> resultado = accionService.buscarTodos();

		return new RespEntity(RespCode.ACCIONES_LISTADAS, resultado);
	}

	@RequestMapping(value = "/listarAccionesEliminadas", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarAccionesEliminadas() {
		final List<AccionEntity> resultado = accionService.buscarAccionesEliminadas();

		return new RespEntity(RespCode.ACCIONES_ELIMINADAS_LISTADAS, resultado);

	}

	@RequestMapping(value = "/accion", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity guardarAccion(@RequestBody final Accion accion) {

		final Boolean accionValido = validaciones.comprobarAccionBlank(accion.getAccion());

		if (accionValido) {
			try {
				String resultado;
				try {
					resultado = accionService.anadirAccion(accion);
					if (CodeMessageErrors.ACCION_VACIA.name().equals(resultado)) {
						return new RespEntity(RespCode.ACCION_VACIA, accion);
					}
					return new RespEntity(RespCode.ACCION_GUARDADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, accion);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, accion);
				}
			} catch (final AccionYaExisteException rolAlreadyExists) {
				return new RespEntity(RespCode.ACCION_YA_EXISTE_EXCEPTION, accion);
			}
		}

		return new RespEntity(RespCode.ACCION_VACIA, accion);
	}

	@RequestMapping(value = "/modificarAccion", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity modificarAccion(@RequestBody final Accion accion) {

		final Boolean accionValida = validaciones.comprobarAccionBlank(accion.getAccion());

		if (accionValida) {
			try {
				String resultado;
				try {
					resultado = accionService.modificarAccion(accion);
					if (CodeMessageErrors.ACCION_VACIA.name().equals(resultado)) {
						return new RespEntity(RespCode.ACCION_VACIA, accion);
					}
					return new RespEntity(RespCode.ACCION_MODIFICADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, accion);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, accion);
				}
			} catch (final AccionNoExisteException accionNoExists) {
				return new RespEntity(RespCode.ACCION_NO_EXISTE_EXCEPTION, accion);
			}
		}

		return new RespEntity(RespCode.ACCION_VACIA, accion);
	}

	@RequestMapping(value = "/eliminarAccion", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity eliminarAccion(@RequestBody final Accion accion) {

		try {
			String resultado;
			try {
				resultado = accionService.eliminarAccion(accion);
				return new RespEntity(RespCode.ACCION_ELIMINADA, resultado);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, accion);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, accion);
			}
		} catch (final AccionNoExisteException accionNoExists) {
			return new RespEntity(RespCode.ACCION_NO_EXISTE_EXCEPTION, accion);
		} catch (final AccionAsociadaRolFuncionalidadException accionAssociatedFuncionalityRol) {
			return new RespEntity(RespCode.ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION, accion);
		}

	}

}
