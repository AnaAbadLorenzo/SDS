package com.sds.controller.proceso;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.ProcesoEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoAsociadoProcesoException;
import com.sds.service.exception.ProcesoAsociadoObjetivoException;
import com.sds.service.exception.ProcesoAsociadoRespuestaPosibleException;
import com.sds.service.exception.ProcesoAsociadoUsuarioProcedimientoException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoYaExisteException;
import com.sds.service.proceso.ProcesoService;
import com.sds.service.proceso.model.Proceso;
import com.sds.service.proceso.model.ProcesoBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/proceso")
public class ProcesoController {

	@Autowired
	ProcesoService procesoService;

	private final Validaciones validaciones;

	public ProcesoController() {
		validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarProceso")
	@ResponseBody
	public RespEntity buscarProceso(@RequestBody final ProcesoBuscar proceso) {

		final ReturnBusquedas<ProcesoEntity> resultado = procesoService.buscarProceso(proceso.getNombreProceso(),
				proceso.getDescripProceso(), proceso.getFechaProceso(), proceso.getInicio(),
				proceso.getTamanhoPagina());

		return new RespEntity(RespCode.PROCESO_ENCONTRADO, resultado);

	}

	@PostMapping(value = "/listarProcesos")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<ProcesoEntity> resultado = procesoService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.PROCESOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarProcesosEliminados")
	@ResponseBody
	public RespEntity buscarProcesosEliminados(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<ProcesoEntity> resultado = procesoService.buscarProcesosEliminados(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.PROCESOS_ELIMINADOS_LISTADOS, resultado);

	}

	@PostMapping(value = "/proceso")
	@ResponseBody
	public RespEntity guardarProceso(@RequestBody final Proceso proceso) {

		try {
			final Boolean procesoValido = validaciones.comprobarProcesoBlank(proceso.getProceso());

			if (Boolean.TRUE.equals(procesoValido)) {
				String resultado;
				try {
					resultado = procesoService.anadirProceso(proceso);
					if (CodeMessageErrors.PROCESO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCESO_VACIO, proceso);
					}
					return new RespEntity(RespCode.PROCESO_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, proceso);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, proceso);
				} catch (final FechaAnteriorFechaActualException fechaAnteriorfechaActualException) {
					return new RespEntity(RespCode.FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL, proceso);
				}
			}
		} catch (final ProcesoYaExisteException procesoExists) {
			return new RespEntity(RespCode.PROCESO_YA_EXISTE_EXCEPTION, proceso);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, proceso);
		}

		return new RespEntity(RespCode.PROCESO_VACIO, proceso);
	}

	@PostMapping(value = "/modificarProceso")
	@ResponseBody
	public RespEntity modificarProceso(@RequestBody final Proceso proceso)
			throws ProcesoAsociadoUsuarioProcedimientoException {
		try {
			final Boolean procesoValido = validaciones.comprobarProcesoBlank(proceso.getProceso());

			if (Boolean.TRUE.equals(procesoValido)) {
				String resultado;
				try {
					resultado = procesoService.modificarProceso(proceso);
					if (CodeMessageErrors.PROCESO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCESO_VACIO, resultado);
					}
					return new RespEntity(RespCode.PROCESO_MODIFICADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, proceso);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, proceso);
				} catch (final FechaAnteriorFechaActualException fechaAnteriorfechaActualException) {
					return new RespEntity(RespCode.FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL, proceso);
				} catch (final ProcesoAsociadoUsuarioProcedimientoException procesoAsociadoUsuarioProcedimientoException) {
					return new RespEntity(RespCode.PROCESO_ASOCIADO_USUARIO_PROCEDIMIENTO_EXCEPTION, proceso);
				}
			}
		} catch (final ProcesoNoExisteException procesoNoExists) {
			return new RespEntity(RespCode.PROCESO_NO_EXISTE_EXCEPTION, proceso);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, proceso);
		}

		return new RespEntity(RespCode.PROCESO_VACIO, proceso);

	}

	@PostMapping(value = "/eliminarProceso")
	@ResponseBody
	public RespEntity eliminarProceso(@RequestBody final Proceso proceso) {

		try {
			String resultado;
			try {
				resultado = procesoService.eliminaProceso(proceso);
				return new RespEntity(RespCode.PROCESO_ELIMINADO, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, proceso);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, proceso);
			} catch (final FechaAnteriorFechaActualException fechaAnteriorfechaActualException) {
				return new RespEntity(RespCode.FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL, proceso);
			} catch (final ProcesoAsociadoUsuarioProcedimientoException procesoAsociadoUsuarioProcedimientoException) {
				return new RespEntity(RespCode.PROCESO_ASOCIADO_USUARIO_PROCEDIMIENTO_EXCEPTION, proceso);
			} catch (final ProcesoAsociadoRespuestaPosibleException procesoAsociadoRespuestaPosibleException) {
				return new RespEntity(RespCode.PROCESO_ASOCIADO_RESPUESTA_POSIBLE_EXCEPTION, proceso);
			} catch (final ProcedimientoAsociadoProcesoException procedimientoAsociadoProcesoException) {
				return new RespEntity(RespCode.PROCEDIMIENTO_ASOCIADO_PROCESO_EXCEPTION, proceso);
			} catch (final ProcesoAsociadoObjetivoException procesoAsociadoObjetivoException) {
				return new RespEntity(RespCode.PROCESO_ASOCIADO_OBJETIVO_EXCEPTION, proceso);
			}

		} catch (final ProcesoNoExisteException procesoNoExists) {
			return new RespEntity(RespCode.PROCESO_NO_EXISTE_EXCEPTION, proceso);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, proceso);
		}
	}

	@PostMapping(value = "/reactivarProceso")
	@ResponseBody
	public RespEntity reactivarProceso(@RequestBody final Proceso proceso) {

		try {
			String resultado;
			try {
				resultado = procesoService.reactivarProceso(proceso);
				return new RespEntity(RespCode.PROCESO_REACTIVADO, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, proceso);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, proceso);
			}

		} catch (final ProcesoNoExisteException procesoNoExists) {
			return new RespEntity(RespCode.PROCESO_NO_EXISTE_EXCEPTION, proceso);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, proceso);
		}

	}

	@PostMapping(value = "/borrarProceso")
	@ResponseBody
	public RespEntity borrarProceso(@RequestBody final ProcesoEntity proceso) {
		try {
			procesoService.deleteProceso(proceso);
			return new RespEntity(RespCode.PROCESO_BORRADO, proceso);
		} catch (final ProcesoNoExisteException planNoExiste) {
			return new RespEntity(RespCode.PROCESO_NO_EXISTE_EXCEPTION, proceso);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, proceso);
		}
	}
}
