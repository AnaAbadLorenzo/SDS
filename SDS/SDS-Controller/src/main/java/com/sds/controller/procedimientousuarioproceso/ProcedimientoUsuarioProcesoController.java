package com.sds.controller.procedimientousuarioproceso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoUsuarioNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoYaExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.procedimientousuarioproceso.ProcedimientoUsuarioProcesoService;
import com.sds.service.procedimientousuarioproceso.model.ProcedimientoUsuarioProceso;
import com.sds.service.procedimientousuarioproceso.model.ProcedimientoUsuarioProcesoReturn;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/procedimientoUsuarioProceso")
public class ProcedimientoUsuarioProcesoController {

	@Autowired
	ProcedimientoUsuarioProcesoService procedimientoUsuarioProcesoService;

	private final Validaciones validaciones;

	public ProcedimientoUsuarioProcesoController() {
		validaciones = new Validaciones();
	}

	@GetMapping(value = "/listarProcedimientosUsuariosProcesosSinP")
	@ResponseBody
	public RespEntity buscarTodos() {
		final ReturnBusquedas<ProcedimientoUsuarioProcesoEntity> resultado = procedimientoUsuarioProcesoService
				.buscarTodos();

		return new RespEntity(RespCode.PROCEDIMIENTOS_USUARIOS_PROCESOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarProcedimientosUsuariosProcesosOfProcedimientoUsuario")
	@ResponseBody
	public RespEntity buscarProcedimientosUsuariosProcesosOfProcedimientoUsuario(
			@RequestBody final String idProcedimientoUsuario) {
		final ProcedimientoUsuarioProcesoReturn resultado = procedimientoUsuarioProcesoService
				.buscarProcesosOfProcedimientoUsuario(Integer.parseInt(idProcedimientoUsuario));

		return new RespEntity(RespCode.PROCEDIMIENTOS_USUARIOS_PROCESOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/procedimientoUsuarioProceso")
	@ResponseBody
	public RespEntity guardarProcedimientoUsuarioProceso(
			@RequestBody final ProcedimientoUsuarioProceso procedimientoUsuarioProceso) {
		try {
			final Boolean procedimientoUsuarioProcesoValido = validaciones.comprobarProcedimientoUsuarioProcesoBlank(
					procedimientoUsuarioProceso.getProcedimientoUsuarioProceso());
			if (Boolean.TRUE.equals(procedimientoUsuarioProcesoValido)) {
				String resultado;
				try {
					resultado = procedimientoUsuarioProcesoService
							.anadirProcedimientoUsuarioProceso(procedimientoUsuarioProceso);
					if (CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_VACIO,
								procedimientoUsuarioProceso);
					}
					return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimientoUsuarioProceso);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimientoUsuarioProceso);
				} catch (final ProcesoNoExisteException procesoNoExists) {
					return new RespEntity(RespCode.PROCESO_NO_EXISTE_EXCEPTION, procedimientoUsuarioProceso);
				} catch (final RespuestaPosibleNoExisteException respuestaPosibleNoExists) {
					return new RespEntity(RespCode.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION, procedimientoUsuarioProceso);
				} catch (final ProcedimientoUsuarioNoExisteException procedimientoUsuarioNoExisteException) {
					return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_NO_EXISTE_EXCEPTION,
							procedimientoUsuarioProceso);
				}
			}
		} catch (final ProcedimientoUsuarioProcesoYaExisteException procedimientoUsuarioProcesoAlreadyExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_YA_EXISTE_EXCEPTION,
					procedimientoUsuarioProceso);
		}

		return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_VACIO, procedimientoUsuarioProceso);
	}

	@PostMapping(value = "/modificarProcesoProcedimiento")
	@ResponseBody
	public RespEntity modificarProcedimientoUsuarioProceso(
			@RequestBody final ProcedimientoUsuarioProceso procedimientoUsuarioProceso) {
		try {
			final Boolean procedimientoUsuarioProcesoValido = validaciones.comprobarProcedimientoUsuarioProcesoBlank(
					procedimientoUsuarioProceso.getProcedimientoUsuarioProceso());
			if (Boolean.TRUE.equals(procedimientoUsuarioProcesoValido)) {
				String resultado;
				try {
					resultado = procedimientoUsuarioProcesoService
							.modificarProcedimientoUsuarioProceso(procedimientoUsuarioProceso);
					if (CodeMessageErrors.PROCEDIMIENTOUSUARIOPROCESO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_VACIO,
								procedimientoUsuarioProceso);
					}
					return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimientoUsuarioProceso);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimientoUsuarioProceso);
				} catch (final ProcesoNoExisteException procesoNoExists) {
					return new RespEntity(RespCode.PROCESO_NO_EXISTE_EXCEPTION, procedimientoUsuarioProceso);
				} catch (final RespuestaPosibleNoExisteException respuestaPosibleNoExists) {
					return new RespEntity(RespCode.RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION, procedimientoUsuarioProceso);
				} catch (final ProcedimientoUsuarioNoExisteException procedimientoUsuarioNoExisteException) {
					return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_NO_EXISTE_EXCEPTION,
							procedimientoUsuarioProceso);
				}
			}
		} catch (final ProcedimientoUsuarioProcesoNoExisteException procedimientoUsuarioProcesoNoExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_NO_EXISTE_EXCEPTION,
					procedimientoUsuarioProceso);
		}

		return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_VACIO, procedimientoUsuarioProceso);
	}

	@PostMapping(value = "/eliminarProcedimientoUsuarioProceso")
	@ResponseBody
	public RespEntity eliminarProcedimientoUsuarioProceso(
			@RequestBody final ProcedimientoUsuarioProceso procedimientoUsuarioProceso) {

		try {
			String resultado;
			try {
				resultado = procedimientoUsuarioProcesoService
						.eliminaProcedimientoUsuarioProceso(procedimientoUsuarioProceso);
				return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_ELIMINADO, resultado);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimientoUsuarioProceso);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimientoUsuarioProceso);
			}
		} catch (final ProcedimientoUsuarioProcesoNoExisteException procesoProcedimientoNoExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_PROCESO_NO_EXISTE_EXCEPTION,
					procedimientoUsuarioProceso);
		}
	}

}
