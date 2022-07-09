package com.sds.controller.procedimientousuario;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException;
import com.sds.service.exception.ProcedimientoUsuarioYaExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.procedimientousuario.ProcedimientoUsuarioService;
import com.sds.service.procedimientousuario.model.ProcedimientoUsuario;
import com.sds.service.procedimientousuario.model.ProcedimientoUsuarioBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/procedimientoUsuario")
public class ProcedimientoUsuarioController {

	@Autowired
	ProcedimientoUsuarioService procedimientoUsuarioService;

	private final Validaciones validaciones;

	public ProcedimientoUsuarioController() {
		validaciones = new Validaciones();
	}

	@GetMapping(value = "/listarProcedimientosUsuario")
	@ResponseBody
	public RespEntity buscarTodos() {
		final ReturnBusquedas<ProcedimientoUsuarioEntity> resultado = procedimientoUsuarioService.buscarTodos();

		return new RespEntity(RespCode.PROCEDIMIENTOS_USUARIO_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarProcedimientoUsuario")
	@ResponseBody
	public RespEntity listarProcedimientoUsuario(
			@RequestBody final ProcedimientoUsuarioBuscar procedimientoUsuarioBuscar) {
		final ReturnBusquedas<ProcedimientoUsuarioEntity> resultado = procedimientoUsuarioService
				.buscarProcedimientoUsuario(procedimientoUsuarioBuscar.getFechaProcedimientoUsuario(),
						procedimientoUsuarioBuscar.getUsuario(), procedimientoUsuarioBuscar.getProcedimiento(),
						procedimientoUsuarioBuscar.getInicio(), procedimientoUsuarioBuscar.getTamanhoPagina());

		return new RespEntity(RespCode.PROCEDIMIENTOS_USUARIO_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarProcedimientoUsuarioById")
	@ResponseBody
	public RespEntity listarProcedimientoUsuarioById(@RequestBody final String idProcedimientoUsuario) {
		final ProcedimientoUsuarioEntity resultado = procedimientoUsuarioService
				.buscarProcedimientoUsuarioById(Integer.parseInt(idProcedimientoUsuario));

		return new RespEntity(RespCode.PROCEDIMIENTOS_USUARIO_LISTADOS, resultado);
	}

	@PostMapping(value = "/procedimientoUsuario")
	@ResponseBody
	public RespEntity guardarProcedimientoUsuario(@RequestBody final ProcedimientoUsuario procedimientoUsuario) {
		try {
			final Boolean procedimientoUsuarioValido = validaciones
					.comprobarProcedimientoUsuarioBlank(procedimientoUsuario.getProcedimientoUsuario());

			if (Boolean.TRUE.equals(procedimientoUsuarioValido)) {
				String resultado;
				try {
					resultado = procedimientoUsuarioService.anadirProcedimientoUsuario(procedimientoUsuario);
					if (CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCEDIMIENTO_VACIO, procedimientoUsuario);
					}
					return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimientoUsuario);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimientoUsuario);
				}
			}
		} catch (final ProcedimientoUsuarioYaExisteException procedimientoUsuarioYaExiste) {
			return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_YA_EXISTE_EXCEPTION, procedimientoUsuario);
		} catch (final ProcedimientoNoExisteException procedimientoDontExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procedimientoUsuario);
		} catch (final UsuarioNoEncontradoException usuarioNoEncontrado) {
			return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, procedimientoUsuario);
		}

		return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_VACIO, procedimientoUsuario);
	}

	@PostMapping(value = "/modificaProcedimientoUsuario")
	@ResponseBody
	public RespEntity modificarProcedimientoUsuario(@RequestBody final ProcedimientoUsuario procedimientoUsuario) {
		try {
			final Boolean procedimientoUsuarioValido = validaciones
					.comprobarProcedimientoUsuarioBlankModificar(procedimientoUsuario.getProcedimientoUsuario());

			if (Boolean.TRUE.equals(procedimientoUsuarioValido)) {
				String resultado;
				try {
					resultado = procedimientoUsuarioService.modificarProcedimientoUsuario(procedimientoUsuario);
					if (CodeMessageErrors.PROCEDIMIENTOUSUARIO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PROCEDIMIENTO_VACIO, procedimientoUsuario);
					}
					return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_MODIFICADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimientoUsuario);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimientoUsuario);
				}
			}
		} catch (final ProcedimientoUsuarioNoExisteException procedimientoUsuarioNoExiste) {
			return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_NO_EXISTE_EXCEPTION, procedimientoUsuario);
		} catch (final ProcedimientoNoExisteException procedimientoDontExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procedimientoUsuario);
		} catch (final UsuarioNoEncontradoException usuarioNoEncontrado) {
			return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, procedimientoUsuario);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, procedimientoUsuario);
		}

		return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_VACIO, procedimientoUsuario);
	}

	@PostMapping(value = "/eliminarProcedimientoUsuario")
	@ResponseBody
	public RespEntity eliminarProcedimientoUsuario(@RequestBody final ProcedimientoUsuario procedimientoUsuario) {

		try {
			String resultado;
			try {
				resultado = procedimientoUsuarioService.eliminaProcedimientoUsuario(procedimientoUsuario);
				return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_ELIMINADO, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, procedimientoUsuario);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, procedimientoUsuario);
			} catch (final ProcedimientoNoExisteException procedimientoNoExiste) {
				return new RespEntity(RespCode.PROCEDIMIENTO_NO_EXISTE_EXCEPTION, procedimientoUsuario);
			} catch (final ParseException parseException) {
				return new RespEntity(RespCode.PARSE_EXCEPTION, procedimientoUsuario);
			} catch (final UsuarioNoEncontradoException usuarioNoEncontradoException) {
				return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, procedimientoUsuario);
			} catch (final ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException procedimientoUsuarioProcesoException) {
				return new RespEntity(RespCode.PROCESO_ASOCIADO_USUARIO_PROCEDIMIENTO_EXCEPTION, procedimientoUsuario);
			}

		} catch (final ProcedimientoUsuarioNoExisteException planNoExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_NO_EXISTE_EXCEPTION, procedimientoUsuario);
		}
	}

	@PostMapping(value = "/borrarProcedimientoUsuario")
	@ResponseBody
	public RespEntity borrarProcedimientoUsuario(@RequestBody final ProcedimientoUsuarioEntity procedimientoUsuario) {
		try {
			procedimientoUsuarioService.deleteProcedimientoUsuario(procedimientoUsuario);
			return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_ELIMINADO, procedimientoUsuario);
		} catch (final ProcedimientoUsuarioNoExisteException planNoExists) {
			return new RespEntity(RespCode.PROCEDIMIENTO_USUARIO_NO_EXISTE_EXCEPTION, procedimientoUsuario);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, procedimientoUsuario);
		}
	}
}
