package com.sds.controller.procesoprocedimiento;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.ProcesoProcedimientoEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoYaExisteException;
import com.sds.service.procesoprocedimiento.ProcesoProcedimientoService;
import com.sds.service.procesoprocedimiento.model.ProcesoProcedimiento;
import com.sds.service.procesoprocedimiento.model.ProcesoProcedimientoBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/procesoProcedimiento")
public class ProcesoProcedimientoController {

	@Autowired
	ProcesoProcedimientoService procesoProcedimientoService;

	private final Validaciones validaciones;

	public ProcesoProcedimientoController() {
		validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarProcesosProcedimiento")
	@ResponseBody
	public RespEntity buscarProcesoProcedimiento(
			@RequestBody final ProcesoProcedimientoBuscar procesoProcedimientoBuscar) {
		final ReturnBusquedas<ProcesoProcedimientoEntity> resultado = procesoProcedimientoService
				.buscarProcesoProcedimiento(procesoProcedimientoBuscar.getIdProceso(),
						procesoProcedimientoBuscar.getIdProcedimiento());

		return new RespEntity(RespCode.PROCESOS_PROCEDIMIENTOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/procesoProcedimiento")
	@ResponseBody
	public RespEntity guardarProcesoProcedimiento(@RequestBody final ProcesoProcedimiento procesoProcedimiento) {
		try {
			final Boolean procesoProcedimientoValido = validaciones
					.comprobarProcesoProcedimientoBlank(procesoProcedimiento.getProcesoProcedimientoEntity());

			if (Boolean.TRUE.equals(procesoProcedimientoValido)) {
				String resultado;
				try {
					resultado = procesoProcedimientoService.anadirProcesoProcedimiento(procesoProcedimiento);
					if (CodeMessageErrors.PROCESO_PROCEDIMIENTO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_VACIO, procesoProcedimiento);
					}
					return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procesoProcedimiento);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procesoProcedimiento);
				} catch (final ProcedimientoNoExisteException procedimientoNoExists) {
					return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procesoProcedimiento);
				} catch (final ProcesoNoExisteException procesoNoExists) {
					return new RespEntity(RespCode.PROCESO_NO_EXISTE_EXCEPTION, procesoProcedimiento);
				}
			}
		} catch (final ProcesoProcedimientoYaExisteException procesoProcedimientoAlreadyExists) {
			return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_YA_EXISTE, procesoProcedimiento);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, procesoProcedimiento);
		}

		return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_VACIO, procesoProcedimiento);
	}

	@PostMapping(value = "/modificarProcesoProcedimiento")
	@ResponseBody
	public RespEntity modificarProcesoProcedimiento(@RequestBody final ProcesoProcedimiento procesoProcedimiento) {
		try {
			final Boolean procesoProcedimientoValido = validaciones
					.comprobarProcesoProcedimientoBlank(procesoProcedimiento.getProcesoProcedimientoEntity());

			if (Boolean.TRUE.equals(procesoProcedimientoValido)) {
				String resultado;
				try {
					resultado = procesoProcedimientoService.modificarProcesoProcedimiento(procesoProcedimiento);
					if (CodeMessageErrors.PROCESO_PROCEDIMIENTO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_VACIO, procesoProcedimiento);
					}
					return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procesoProcedimiento);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procesoProcedimiento);
				} catch (final ProcedimientoNoExisteException procedimientoNoExists) {
					return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procesoProcedimiento);
				} catch (final ProcesoNoExisteException procesoNoExists) {
					return new RespEntity(RespCode.PROCESO_NO_EXISTE_EXCEPTION, procesoProcedimiento);
				}
			}
		} catch (final ProcesoProcedimientoNoExisteException procesoProcedimientoNoExists) {
			return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_NO_EXISTE, procesoProcedimiento);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, procesoProcedimiento);
		}

		return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_VACIO, procesoProcedimiento);
	}

	@PostMapping(value = "/eliminarProcesoProcedimiento")
	@ResponseBody
	public RespEntity eliminarProcesoProcedimiento(@RequestBody final ProcesoProcedimiento procesoProcedimiento) {

		try {
			String resultado;
			try {
				resultado = procesoProcedimientoService.eliminaProcesoProcedimiento(procesoProcedimiento);
				return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_ELIMINADO, resultado);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procesoProcedimiento);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procesoProcedimiento);
			}
		} catch (final ProcesoProcedimientoNoExisteException procesoProcedimientoNoExists) {
			return new RespEntity(RespCode.PROCESO_PROCEDIMIENTO_NO_EXISTE, procesoProcedimiento);
		}
	}
}
