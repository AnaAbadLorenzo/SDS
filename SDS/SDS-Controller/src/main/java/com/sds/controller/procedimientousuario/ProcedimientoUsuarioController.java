package com.sds.controller.procedimientousuario;

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
				.buscarProcedimientoUsuario(procedimientoUsuarioBuscar.getPuntuacionProcedimientoUsuario(),
						procedimientoUsuarioBuscar.getFechaProcedimientoUsuario(),
						procedimientoUsuarioBuscar.getUsuario(), procedimientoUsuarioBuscar.getProcedimiento(),
						procedimientoUsuarioBuscar.getInicio(), procedimientoUsuarioBuscar.getTamanhoPagina());

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
}
