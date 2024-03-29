package com.sds.controller.rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.model.RolEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolAsociadoAccionFuncionalidadException;
import com.sds.service.exception.RolAsociadoUsuarioException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.rol.RolService;
import com.sds.service.rol.model.Rol;
import com.sds.service.rol.model.RolBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/rol")
public class RolController {

	@Autowired
	RolService rolService;

	final Validaciones validaciones;

	public RolController() {
		this.validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarRol")
	@ResponseBody
	public RespEntity buscarRol(@RequestBody final RolBuscar rolBuscar) {

		final ReturnBusquedas<RolEntity> resultado = rolService.buscarRol(rolBuscar.getRolName(),
				rolBuscar.getRolDescription(), rolBuscar.getInicio(), rolBuscar.getTamanhoPagina());

		return new RespEntity(RespCode.ROL_ENCONTRADO, resultado);

	}

	@PostMapping(value = "/listarRoles")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<RolEntity> resultado = rolService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.ROLES_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarRolesEliminados")
	@ResponseBody
	public RespEntity buscarRolesEliminados(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<RolEntity> resultado = rolService.buscarRolesEliminados(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.ROLES_ELIMINADOS_LISTADOS, resultado);

	}

	@GetMapping(value = "/obtenerTodos")
	@ResponseBody
	public RespEntity obtenerTodos() {
		final List<RolEntity> resultado = rolService.obtenerTodos();

		return new RespEntity(RespCode.ROLES_LISTADOS, resultado);

	}

	@PostMapping(value = "/rol")
	@ResponseBody
	public RespEntity guardarRol(@RequestBody final Rol rol) {

		final Boolean rolValido = validaciones.comprobarRolBlank(rol.getRol());

		if (Boolean.TRUE.equals(rolValido)) {
			try {
				String resultado;
				try {
					resultado = rolService.guardarRol(rol);
					if (CodeMessageErrors.ROL_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.ROL_VACIO, rol);
					}
					return new RespEntity(RespCode.ROL_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, rol);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, rol);
				}
			} catch (final RolYaExisteException rolAlreadyExists) {
				return new RespEntity(RespCode.ROL_YA_EXISTE_EXCEPTION, rol);
			}
		}

		return new RespEntity(RespCode.ROL_VACIO, rol);
	}

	@PostMapping(value = "/modificarRol")
	@ResponseBody
	public RespEntity modificarRol(@RequestBody final Rol rol) {

		final Boolean rolValido = validaciones.comprobarRolBlank(rol.getRol());

		if (Boolean.TRUE.equals(rolValido)) {
			try {
				String resultado;
				try {
					resultado = rolService.modificarRol(rol);
					if (CodeMessageErrors.ROL_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.ROL_VACIO, rol);
					}
					return new RespEntity(RespCode.ROL_MODIFICADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, rol);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, rol);
				}
			} catch (final RolNoExisteException rolNoExists) {
				return new RespEntity(RespCode.ROL_NO_EXISTE_EXCEPTION, rol);
			}
		}

		return new RespEntity(RespCode.ROL_VACIO, rol);
	}

	@PostMapping(value = "/eliminarRol")
	@ResponseBody
	public RespEntity eliminarRol(@RequestBody final Rol rol) {

		try {
			String resultado;
			try {
				resultado = rolService.eliminarRol(rol);
				return new RespEntity(RespCode.ROL_ELIMINADO, resultado);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, rol);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, rol);
			}
		} catch (final RolNoExisteException rolNoExists) {
			return new RespEntity(RespCode.ROL_NO_EXISTE_EXCEPTION, rol);
		} catch (final RolAsociadoUsuarioException rolAsociatedUser) {
			return new RespEntity(RespCode.ROL_ASOCIADO_USUARIO_EXCEPTION, rol);
		} catch (final RolAsociadoAccionFuncionalidadException rolAsociadoAccionFuncionalidad) {
			return new RespEntity(RespCode.ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION, rol);
		}

	}

	@PostMapping(value = "/reactivarRol")
	@ResponseBody
	public RespEntity reactivarRol(@RequestBody final Rol rol) {

		try {
			String resultado;
			try {
				resultado = rolService.reactivarRol(rol);
				return new RespEntity(RespCode.ROL_REACTIVADO, resultado);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, rol);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, rol);
			}
		} catch (final RolNoExisteException rolNoExists) {
			return new RespEntity(RespCode.ROL_NO_EXISTE_EXCEPTION, rol);
		}

	}

	@PostMapping(value = "/borradoRol")
	@ResponseBody
	public RespEntity borrarRol(@RequestBody final Rol rol) {
		try {
			rolService.deleteRol(rol);
			return new RespEntity(RespCode.ROL_BORRADO, rol);
		} catch (final RolNoExisteException e) {
			return new RespEntity(RespCode.ROL_NO_EXISTE_EXCEPTION, rol);
		}

	}

}
