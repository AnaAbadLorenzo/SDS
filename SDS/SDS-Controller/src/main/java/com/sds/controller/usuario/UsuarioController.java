package com.sds.controller.usuario;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.usuario.model.Usuario;
import com.sds.service.usuario.model.UsuarioAñadir;
import com.sds.service.usuario.model.UsuarioBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final Validaciones validaciones;

	@Autowired
	UsuarioService usuarioService;

	public UsuarioController() {
		this.validaciones = new Validaciones();
	}

	@RequestMapping(value = "/listarUsuarios", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarTodos() {
		final List<UsuarioEntity> resultado = usuarioService.buscarTodos();
		return new RespEntity(RespCode.USUARIOS_LISTADOS, resultado);
	}

	@RequestMapping(value = "/listarUsuario", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarUsuario(@RequestBody final UsuarioBuscar usuario) {
		final List<UsuarioEntity> resultado = usuarioService.buscarUsuario(usuario.getDniUsuario(),
				usuario.getUsuario(), usuario.getRol());

		return new RespEntity(RespCode.USUARIOS_LISTADOS, resultado);
	}

	@RequestMapping(value = "/listarUsuariosEliminados", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarUsuariosEliminados() {
		final List<UsuarioEntity> resultado = usuarioService.buscarUsuariosEliminados();

		return new RespEntity(RespCode.USUARIOS_LISTADOS, resultado);
	}

	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity insertarUsuario(@RequestBody final UsuarioAñadir usuarioAñadir) {
		Boolean usuarioValido;
		try {
			usuarioValido = validaciones.comprobarUsuarioAñadirBlank(usuarioAñadir);
			if (usuarioValido) {
				String resultado;
				try {
					resultado = usuarioService.añadirUsuario(usuarioAñadir);

					if (CodeMessageErrors.USUARIO_AÑADIR_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.USUARIO_AÑADIR_VACIO, usuarioAñadir);
					}

					return new RespEntity(RespCode.USUARIO_GUARDADO, usuarioAñadir);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, usuarioAñadir);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, usuarioAñadir);
				}
			}
		} catch (final UsuarioYaExisteException usuarioYaExiste) {
			return new RespEntity(RespCode.USUARIO_YA_EXISTE, usuarioAñadir);
		} catch (final PersonaYaExisteException personaYaExiste) {
			return new RespEntity(RespCode.PERSONA_YA_EXISTE, usuarioAñadir);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, usuarioAñadir);
		}

		return new RespEntity(RespCode.USUARIO_AÑADIR_VACIO, usuarioAñadir);

	}

	@RequestMapping(value = "/eliminarUsuario", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity eliminarUsuario(@RequestBody final Usuario usuario) {
		try {
			String resultado;
			resultado = usuarioService.eliminarUsuario(usuario);
			return new RespEntity(RespCode.USUARIO_ELIMINADO, resultado);
		} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, usuario);
		} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, usuario);
		} catch (final UsuarioNoEncontradoException usuarioNoEncontrado) {
			return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, usuario);
		}
	}

	@RequestMapping(value = "/modificarUsuario", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity modificarUsuario(@RequestBody final Usuario usuario) {
		final Boolean usuarioValido = validaciones.comprobarUsuarioBlank(usuario.getUsuarioEntity());

		if (usuarioValido) {
			try {
				String resultado;
				try {

					resultado = usuarioService.modificarUsuario(usuario);

					if (CodeMessageErrors.USUARIO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.USUARIO_VACIO, usuario);

					}
					return new RespEntity(RespCode.USUARIO_MODIFICADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, usuario);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, usuario);
				}
			} catch (final UsuarioNoEncontradoException usuarioNoEncontrado) {
				return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, usuario);
			}
		}

		return new RespEntity(RespCode.USUARIO_VACIO, usuario);

	}

}
