package com.sds.controller.usuario;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.usuario.model.CambiarContrasena;
import com.sds.service.usuario.model.Usuario;
import com.sds.service.usuario.model.UsuarioBuscar;
import com.sds.service.usuario.model.UsuarioModificar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	private final Validaciones validaciones;

	@Autowired
	UsuarioService usuarioService;

	public UsuarioController() {
		this.validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarUsuarios")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<UsuarioEntity> resultado = usuarioService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());
		return new RespEntity(RespCode.USUARIOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarUsuario")
	@ResponseBody
	public RespEntity buscarUsuario(@RequestBody final UsuarioBuscar usuario) {
		final ReturnBusquedas<UsuarioEntity> resultado = usuarioService.buscarUsuario(usuario.getDniUsuario(),
				usuario.getUsuario(), usuario.getRol(), usuario.getInicio(), usuario.getTamanhoPagina());

		return new RespEntity(RespCode.USUARIOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarUsuariosEliminados")
	@ResponseBody
	public RespEntity buscarUsuariosEliminados(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<UsuarioEntity> resultado = usuarioService.buscarUsuariosEliminados(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.USUARIOS_ELIMINADOS_LISTADOS, resultado);
	}

	@PostMapping(value = "/eliminarUsuario")
	@ResponseBody
	public RespEntity eliminarUsuario(@RequestBody final Usuario usuario) {
		try {
			String resultado;
			try {
				resultado = usuarioService.eliminarUsuario(usuario);
				return new RespEntity(RespCode.USUARIO_ELIMINADO, resultado);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, usuario);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, usuario);
			}
		} catch (final UsuarioNoEncontradoException usuarioNoEncontrado) {
			return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, usuario);
		}
	}

	@PostMapping(value = "/reactivarUsuario")
	@ResponseBody
	public RespEntity reactivarUsuario(@RequestBody final Usuario usuario) {
		try {
			String resultado;
			try {
				resultado = usuarioService.reactivarUsuario(usuario);
				return new RespEntity(RespCode.USUARIO_REACTIVADO, resultado);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, usuario);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, usuario);
			}
		} catch (final UsuarioNoEncontradoException usuarioNoEncontrado) {
			return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, usuario);
		} catch (final PersonaNoExisteException e) {
			return new RespEntity(RespCode.PERSONA_NO_EXISTE, usuario);
		} catch (final ParseException e) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, usuario);
		}
	}

	@PostMapping(value = "/modificarRolUsuario")
	@ResponseBody
	public RespEntity modificarRol(@RequestBody final UsuarioModificar usuarioModificar) {
		Boolean usuarioModificarValido;
		try {
			usuarioModificarValido = validaciones.comprobarUsuarioModificarBlank(usuarioModificar);
			if (Boolean.TRUE.equals(usuarioModificarValido)) {
				String resultado;
				try {
					resultado = usuarioService.modificarRolUsuario(usuarioModificar.getRolEntity(),
							usuarioModificar.getUsuario());
					if (CodeMessageErrors.ROL_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.ROL_VACIO, usuarioModificar);
					}

					if (CodeMessageErrors.USUARIO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.USUARIO_VACIO, usuarioModificar);
					}

					return new RespEntity(RespCode.ROL_USUARIO_MODIFICADO_OK,
							usuarioModificar.getRolEntity().getRolName());

				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, usuarioModificar);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, usuarioModificar);
				}
			}

		} catch (final RolNoExisteException rolNoExiste) {
			return new RespEntity(RespCode.ROL_NO_EXISTE_EXCEPTION, usuarioModificar);
		} catch (final UsuarioNoEncontradoException usuarioNoEncontrado) {
			return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, usuarioModificar);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, usuarioModificar);
		}
		return new RespEntity(RespCode.USUARIO_MODIFICAR_VACIO, usuarioModificar);
	}

	@PostMapping(value = "/cambiarContrasenaUsuario")
	@ResponseBody
	public RespEntity cambiarContrasenaUsuario(@RequestBody final CambiarContrasena cambiarContrasena) {
		String resultado;
		try {
			resultado = usuarioService.cambiarContraseña(cambiarContrasena.getUsuario(),
					cambiarContrasena.getPasswdUsuario());
			return new RespEntity(RespCode.PASSWORD_CAMBIADA, resultado);
		} catch (final UsuarioNoEncontradoException e) {
			return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, cambiarContrasena);
		} catch (final LogExcepcionesNoGuardadoException e) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, cambiarContrasena);
		} catch (final LogAccionesNoGuardadoException e) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, cambiarContrasena);
		}
	}

	@PostMapping(value = "/borrarUsuario")
	@ResponseBody
	public RespEntity borrarUsuario(@RequestBody final UsuarioEntity usuario) {
		try {
			usuarioService.deleteUsuario(usuario);
			return new RespEntity(RespCode.USUARIO_BORRADO, usuario);
		} catch (final UsuarioNoEncontradoException e) {
			return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, usuario);
		}
	}
}