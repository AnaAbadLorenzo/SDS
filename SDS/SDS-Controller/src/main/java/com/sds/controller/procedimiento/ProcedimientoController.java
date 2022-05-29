package com.sds.controller.procedimiento;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PlanNoExisteException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcedimientoYaExisteException;
import com.sds.service.procedimiento.ProcedimientoService;
import com.sds.service.procedimiento.model.Procedimiento;
import com.sds.service.procedimiento.model.ProcedimientoBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/procedimiento")
public class ProcedimientoController {

	@Autowired
	ProcedimientoService procedimientoService;

	private final Validaciones validaciones;

	public ProcedimientoController() {
		validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarProcedimiento")
	@ResponseBody
	public RespEntity buscarProcedimiento(@RequestBody final ProcedimientoBuscar procedimiento) {

		final ReturnBusquedas<ProcedimientoEntity> resultado = procedimientoService.buscarProcedimiento(
				procedimiento.getNombreProcedimiento(), procedimiento.getDescripProcedimiento(),
				procedimiento.getFechaProcedimiento(), procedimiento.getCheckUsuario(), procedimiento.getPlan(), 0, 1);

		return new RespEntity(RespCode.PROCEDIMIENTO_ENCONTRADO, resultado);

	}

	@PostMapping(value = "/listarProcedimientos")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<ProcedimientoEntity> resultado = procedimientoService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.PROCEDIMIENTOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarProcedimientosEliminados")
	@ResponseBody
	public RespEntity buscarProcedimientosEliminados(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<ProcedimientoEntity> resultado = procedimientoService
				.buscarProcedimientosEliminados(paginacion.getInicio(), paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.PROCEDIMIENTOS_ELIMINADOS_LISTADOS, resultado);

	}

	@PostMapping(value = "/procedimiento")
	@ResponseBody
	public RespEntity guardarProcedimiento(@RequestBody final Procedimiento procedimiento) {

		try {
			final Boolean procedimientoValido = validaciones
					.comprobarProcedimientoBlank(procedimiento.getProcedimientoEntity());

			if (Boolean.TRUE.equals(procedimientoValido)) {
				String resultado;
				try {
					resultado = procedimientoService.anadirProcedimiento(procedimiento);
					if (CodeMessageErrors.PROCEDIMIENTO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCEDIMIENTO_VACIO, procedimiento);
					}
					return new RespEntity(RespCode.PROCEDIMIENTO_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimiento);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimiento);
				} catch (final FechaAnteriorFechaActualException fechaAnteriorfechaActualException) {
					return new RespEntity(RespCode.FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL, procedimiento);
				} catch (final PlanNoExisteException planNoExists) {
					return new RespEntity(RespCode.PLAN_NO_EXISTE_EXCEPTION, procedimiento);
				}
			}
		} catch (final ProcedimientoYaExisteException procedimientoExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_YA_EXISTE_EXCEPTION, procedimiento);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, procedimiento);
		}

		return new RespEntity(RespCode.PROCEDIMIENTO_VACIO, procedimiento);
	}

	@PostMapping(value = "/modificarProcedimiento")
	@ResponseBody
	public RespEntity modificarProcedimiento(@RequestBody final Procedimiento procedimiento) {
		try {
			final Boolean procedimientoValido = validaciones
					.comprobarProcedimientoBlank(procedimiento.getProcedimientoEntity());

			if (Boolean.TRUE.equals(procedimientoValido)) {
				String resultado;
				try {
					resultado = procedimientoService.modificarProcedimiento(procedimiento);
					if (CodeMessageErrors.PROCEDIMIENTO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCEDIMIENTO_VACIO, resultado);
					}
					return new RespEntity(RespCode.PROCEDIMIENTO_MODIFICADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimiento);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimiento);
				} catch (final FechaAnteriorFechaActualException fechaAnteriorfechaActualException) {
					return new RespEntity(RespCode.FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL, procedimiento);
				} catch (final PlanNoExisteException planNoExists) {
					return new RespEntity(RespCode.PLAN_NO_EXISTE_EXCEPTION, procedimiento);
				}
			}
		} catch (final ProcedimientoNoExisteException procedimientoNoExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procedimiento);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, procedimiento);
		}

		return new RespEntity(RespCode.PROCEDIMIENTO_VACIO, procedimiento);

	}

	@PostMapping(value = "/eliminarProcedimiento")
	@ResponseBody
	public RespEntity eliminarProcedimiento(@RequestBody final Procedimiento procedimiento) {

		try {
			String resultado;
			try {
				resultado = procedimientoService.eliminaProcedimiento(procedimiento);
				return new RespEntity(RespCode.PROCEDIMIENTO_ELIMINADO, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimiento);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimiento);
			}

		} catch (final ProcedimientoNoExisteException planNoExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procedimiento);
		}
	}

	@PostMapping(value = "/reactivarProcedimiento")
	@ResponseBody
	public RespEntity reactivarProcedimiento(@RequestBody final Procedimiento procedimiento) {

		try {
			String resultado;
			try {
				resultado = procedimientoService.reactivarProcedimiento(procedimiento);
				return new RespEntity(RespCode.PROCEDIMIENTO_REACTIVADO, resultado);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimiento);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimiento);
			}

		} catch (final ProcedimientoNoExisteException procedimientoNoExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procedimiento);
		}

	}

	@PostMapping(value = "/borrarProcedimiento")
	@ResponseBody
	public RespEntity borrarProcedimiento(@RequestBody final ProcedimientoEntity procedimiento) {
		try {
			procedimientoService.deleteProcedimiento(procedimiento);
			return new RespEntity(RespCode.PROCEDIMIENTO_BORRADO, procedimiento);
		} catch (final ProcedimientoNoExisteException planNoExiste) {
			return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procedimiento);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, procedimiento);
		}
	}
}
