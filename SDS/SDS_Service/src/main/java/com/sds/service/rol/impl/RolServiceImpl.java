package com.sds.service.rol.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.RolEntity;
import com.sds.repository.RolRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.NoHayRolesException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.rol.RolService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	RolRepository rolRepository;

	final Validaciones validaciones;

	public RolServiceImpl() {
		this.validaciones = new Validaciones();
	}

	@Override
	public RolEntity buscarRol(final String rolName) throws RolNoExisteException {
		final Boolean rolValido = validaciones.comprobarNombreRolBlank(rolName);
		RolEntity rolUser = null;
		final RolEntity rol;

		if (rolValido) {
			rol = rolRepository.findByRolName(rolName);

			if (rol.equals(null)) {
				throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				rolUser = rol;
			}
		}
		return rolUser;
	}

	@Override
	public List<RolEntity> buscarTodos() throws NoHayRolesException {
		final List<RolEntity> roles = rolRepository.findAll();

		if (roles.isEmpty()) {
			throw new NoHayRolesException(CodeMessageErrors.NO_HAY_ROLES_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.NO_HAY_ROLES_EXCEPTION.getCodigo()));
		}

		return roles;
	}

	@Override
	public String guardarRol(final RolEntity rol) throws RolYaExisteException {

		final Boolean rolValido = validaciones.comprobarRolBlank(rol);
		String resultado = StringUtils.EMPTY;

		if (rolValido) {
			final RolEntity rolUsuario = rolRepository.findByRolName(rol.getRolName());

			resultado = StringUtils.EMPTY;

			if (rolUsuario == null) {
				rolRepository.saveAndFlush(rol);

				resultado = Constantes.OK;

			} else {
				throw new RolYaExisteException(CodeMessageErrors.ROL_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_YA_EXISTE_EXCEPTION.getCodigo()));
			}

			return resultado;
		} else {

			resultado = CodeMessageErrors.ROL_VACIO.name();
		}

		return resultado;

	}

	@Override
	public String eliminarRol(final RolEntity rol) throws RolNoExisteException {
		final Optional<RolEntity> rolUsuario = rolRepository.findById(rol.getIdRol());
		String resultado = StringUtils.EMPTY;

		if (!rolUsuario.isPresent()) {
			throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			rolRepository.delete(rol);
			resultado = Constantes.OK;
		}

		return resultado;
	}

	@Override
	public String modificarRol(final RolEntity rol) throws RolNoExisteException {

		final Boolean rolValido = validaciones.comprobarRolBlank(rol);
		String resultado = StringUtils.EMPTY;

		if (rolValido) {
			final Optional<RolEntity> rolUsuario = rolRepository.findById(rol.getIdRol());

			if (!rolUsuario.isPresent()) {
				throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				rol.setRolName(rol.getRolName());
				rol.setRolDescription(rol.getRolDescription());
				rolRepository.saveAndFlush(rol);
				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.ROL_VACIO.name();
		}

		return resultado;
	}

}