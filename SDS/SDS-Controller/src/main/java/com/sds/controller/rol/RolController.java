package com.sds.controller.rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.model.RolEntity;
import com.sds.service.exception.NoHayRolesException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.rol.RolService;
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

	@RequestMapping(value = "/listarRol", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarRol(@RequestBody final String rolName) {
		final Boolean nombreValido = validaciones.comprobarNombreRolBlank(rolName);

		if (nombreValido) {
			try {
				final RolEntity resultado = rolService.buscarRol(rolName);
				if (resultado == null) {
					return new RespEntity(RespCode.ROL_VACIO, rolName);
				}
				return new RespEntity(RespCode.ROL_ENCONTRADO, resultado);
			} catch (final RolNoExisteException rolNoExists) {
				return new RespEntity(RespCode.ROL_NO_EXISTE_EXCEPTION, rolName);
			}
		}

		return new RespEntity(RespCode.ROL_VACIO, rolName);

	}
	
	@RequestMapping(value="/listarRoles", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarTodos() {
		try {
			final List<RolEntity> resultado = rolService.buscarTodos();
			
			return new RespEntity(RespCode.ROLES_LISTADOS, resultado);
		
		}catch(final NoHayRolesException rolesNoExists) {
			return new RespEntity(RespCode.NO_HAY_ROLES_EXCEPTION);
		}
	}
	
	@RequestMapping(value = "/listarRolesEliminados", method=RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarRolesEliminados() {
		try {
			final List<RolEntity> resultado = rolService.buscarRolesEliminados();
			
			return new RespEntity(RespCode.ROLES_ELIMINADOS_LISTADOS, resultado);
		
		}catch(final NoHayRolesException rolesNoExists) {
			return new RespEntity(RespCode.NO_HAY_ROLES_EXCEPTION);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public RespEntity guardarRol(@RequestBody final RolEntity rol) {

		final Boolean rolValido = validaciones.comprobarRolBlank(rol);

		if (rolValido) {
			try {
				final String resultado = rolService.guardarRol(rol);
				if (CodeMessageErrors.ROL_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.ROL_VACIO, rol);
				}
				return new RespEntity(RespCode.ROL_GUARDADO, resultado);
			} catch (final RolYaExisteException rolAlreadyExists) {
				return new RespEntity(RespCode.ROL_YA_EXISTE_EXCEPTION, rol);
			}
		}

		return new RespEntity(RespCode.ROL_VACIO, rol);
	}

	@RequestMapping(value = "/modificarRol", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity modificarRol(@RequestBody final RolEntity rol) {

		final Boolean rolValido = validaciones.comprobarRolBlank(rol);

		if (rolValido) {
			try {
				final String resultado = rolService.modificarRol(rol);
				if (CodeMessageErrors.ROL_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.ROL_VACIO, rol);
				}
				return new RespEntity(RespCode.ROL_MODIFICADO, resultado);
			} catch (final RolNoExisteException rolNoExists) {
				return new RespEntity(RespCode.ROL_NO_EXISTE_EXCEPTION, rol);
			}
		}

		return new RespEntity(RespCode.ROL_VACIO, rol);
	}

	@RequestMapping(value = "/eliminarRol", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity eliminarRol(@RequestBody final RolEntity rol) {

		try {
			final String resultado = rolService.eliminarRol(rol);
			return new RespEntity(RespCode.ROL_ELIMINADO, resultado);
		} catch (final RolNoExisteException rolNoExists) {
			return new RespEntity(RespCode.ROL_NO_EXISTE_EXCEPTION, rol);
		}

	}

}
