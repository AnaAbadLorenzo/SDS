package com.sds.controller.funcionalidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.FuncionalidadEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FuncionalidadAsociadaRolAccionException;
import com.sds.service.exception.FuncionalidadNoExisteException;
import com.sds.service.exception.FuncionalidadYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.funcionalidad.FuncionalidadService;
import com.sds.service.funcionalidad.model.Funcionalidad;
import com.sds.service.funcionalidad.model.FuncionalidadBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/funcionalidad")
public class FuncionalidadController {

	private final Validaciones validaciones;

	@Autowired
	FuncionalidadService funcionalidadService;

	public FuncionalidadController() {
		validaciones = new Validaciones();
	}

	@RequestMapping(value = "/listarFuncionalidad", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity buscarFuncionalidad(@RequestBody final FuncionalidadBuscar funcionalidad) {

		final ReturnBusquedas<FuncionalidadEntity> resultado = funcionalidadService.buscarFuncionalidad(
				funcionalidad.getNombreFuncionalidad(), funcionalidad.getDescripFuncionalidad(),
				funcionalidad.getInicio(), funcionalidad.getTamanhoPagina());

		return new RespEntity(RespCode.FUNCIONALIDAD_ENCONTRADA, resultado);

	}

	@RequestMapping(value = "/listarFuncionalidades", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<FuncionalidadEntity> resultado = funcionalidadService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.FUNCIONALIDADES_LISTADAS, resultado);
	}

	@RequestMapping(value = "/listarFuncionalidadesEliminadas", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity buscarFuncionalidadesEliminadas(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<FuncionalidadEntity> resultado = funcionalidadService
				.buscarFuncionalidadesEliminadas(paginacion.getInicio(), paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.FUNCIONALIDADES_ELIMINADAS_LISTADAS, resultado);

	}

	@RequestMapping(value = "/funcionalidad", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity guardarAccion(@RequestBody final Funcionalidad funcionalidad) {

		final Boolean funcionalidadValido = validaciones
				.comprobarFuncionalidadBlank(funcionalidad.getFuncionalidadEntity());

		if (funcionalidadValido) {
			try {
				String resultado;
				try {
					resultado = funcionalidadService.anadirFuncionalidad(funcionalidad);
					if (CodeMessageErrors.FUNCIONALIDAD_VACIA.name().equals(resultado)) {
						return new RespEntity(RespCode.FUNCIONALIDAD_VACIA, funcionalidad);
					}
					return new RespEntity(RespCode.FUNCIONALIDAD_GUARDADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, funcionalidad);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, funcionalidad);
				}
			} catch (final FuncionalidadYaExisteException rolAlreadyExists) {
				return new RespEntity(RespCode.FUNCIONALIDAD_YA_EXISTE_EXCEPTION, funcionalidad);
			}
		}

		return new RespEntity(RespCode.FUNCIONALIDAD_VACIA, funcionalidad);
	}

	@RequestMapping(value = "/modificarFuncionalidad", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity modificarFuncionalidad(@RequestBody final Funcionalidad funcionalidad) {

		final Boolean funcionalidadValida = validaciones
				.comprobarFuncionalidadBlank(funcionalidad.getFuncionalidadEntity());

		if (funcionalidadValida) {
			try {
				String resultado;
				try {
					resultado = funcionalidadService.modificarFuncionalidad(funcionalidad);
					if (CodeMessageErrors.FUNCIONALIDAD_VACIA.name().equals(resultado)) {
						return new RespEntity(RespCode.FUNCIONALIDAD_VACIA, funcionalidad);
					}
					return new RespEntity(RespCode.FUNCIONALIDAD_MODIFICADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, funcionalidad);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, funcionalidad);
				}
			} catch (final FuncionalidadNoExisteException funcionalidadNoExists) {
				return new RespEntity(RespCode.FUNCIONALIDAD_NO_EXISTE_EXCEPTION, funcionalidad);
			}
		}

		return new RespEntity(RespCode.FUNCIONALIDAD_VACIA, funcionalidad);
	}

	@RequestMapping(value = "/eliminarFuncionalidad", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity eliminarFuncionalidad(@RequestBody final Funcionalidad funcionalidad) {

		try {
			String resultado;
			try {
				resultado = funcionalidadService.eliminarFuncionalidad(funcionalidad);
				return new RespEntity(RespCode.FUNCIONALIDAD_ELIMINADA, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, funcionalidad);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, funcionalidad);
			}
		} catch (final FuncionalidadNoExisteException funcionalidadNoExists) {
			return new RespEntity(RespCode.FUNCIONALIDAD_NO_EXISTE_EXCEPTION, funcionalidad);
		} catch (final FuncionalidadAsociadaRolAccionException accionAssociatedFuncionalityRol) {
			return new RespEntity(RespCode.FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION, funcionalidad);
		}

	}

	@RequestMapping(value = "/reactivarFuncionalidad", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity reactivarFuncionalidad(@RequestBody final Funcionalidad funcionalidad) {

		try {
			String resultado;
			try {
				resultado = funcionalidadService.reactivarFuncionalidad(funcionalidad);
				return new RespEntity(RespCode.FUNCIONALIDAD_REACTIVADA, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, funcionalidad);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, funcionalidad);
			}
		} catch (final FuncionalidadNoExisteException funcionalidadNoExists) {
			return new RespEntity(RespCode.FUNCIONALIDAD_NO_EXISTE_EXCEPTION, funcionalidad);
		}

	}

}
