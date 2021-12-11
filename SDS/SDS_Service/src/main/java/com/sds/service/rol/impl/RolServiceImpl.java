package com.sds.service.rol.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.NoHayRolesException;
import com.sds.service.exception.RolAsociadoUsuarioException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.rol.RolService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	RolRepository rolRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

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
				rolUser = new RolEntity(rol.getIdRol(), rol.getRolName(), rol.getRolDescription(), rol.getBorradoRol());
			}
		}
		return rolUser;
	}

	@Override
	public List<RolEntity> buscarTodos() throws NoHayRolesException {
		final List<RolEntity> roles = rolRepository.findAll();
		final List<RolEntity> toret = new ArrayList<RolEntity>();
		
		if (roles.isEmpty()) {
			throw new NoHayRolesException(CodeMessageErrors.NO_HAY_ROLES_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.NO_HAY_ROLES_EXCEPTION.getCodigo()));
		}
		
		for(int i =0; i< roles.size(); i++) {
			RolEntity rol = new RolEntity(roles.get(i).getIdRol(), roles.get(i).getRolName(), roles.get(i).getRolDescription(), roles.get(i).getBorradoRol());
			toret.add(rol);
		}
		

		return toret;
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
	public String eliminarRol(final RolEntity rol) throws RolNoExisteException, RolAsociadoUsuarioException {
		final Optional<RolEntity> rolUsuario = rolRepository.findById(rol.getIdRol());
		String resultado = StringUtils.EMPTY;

		if (!rolUsuario.isPresent()) {
			throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			final List<UsuarioEntity> usuarios = usuarioRepository.findAll();
			
			for(UsuarioEntity usuario : usuarios) {
				if(usuario.getRol().getIdRol().equals(rol.getIdRol())) {
					throw new RolAsociadoUsuarioException(CodeMessageErrors.ROL_ASOCIADO_USUARIO_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_ASOCIADO_USUARIO_EXCEPTION.getCodigo()));
				}
			}
			
			rol.setBorradoRol(1);
			modificarRol(rol);
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
				rol.setBorradoRol(rol.getBorradoRol());
				rolRepository.saveAndFlush(rol);
				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.ROL_VACIO.name();
		}

		return resultado;
	}
	
	@Override
	public List<RolEntity> buscarRolesEliminados() throws NoHayRolesException{
		List<RolEntity> rolesEliminados = rolRepository.findDeleteRol(1);
		final List<RolEntity> toret = new ArrayList<RolEntity>();
		
		if(rolesEliminados == null) {
			throw new NoHayRolesException(CodeMessageErrors.NO_HAY_ROLES_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.NO_HAY_ROLES_EXCEPTION.getCodigo()));
			
		}
		
		for(int i = 0; i<rolesEliminados.size(); i++) {
			RolEntity rol = new RolEntity(rolesEliminados.get(i).getIdRol(), rolesEliminados.get(i).getRolName(), rolesEliminados.get(i).getRolDescription(),rolesEliminados.get(i).getBorradoRol());
			toret.add(rol);
		}
		
		return toret;

	}

}
