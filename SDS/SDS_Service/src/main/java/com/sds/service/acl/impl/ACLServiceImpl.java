package com.sds.service.acl.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.FuncionalidadEntity;
import com.sds.model.RolEntity;
import com.sds.repository.AccionRepository;
import com.sds.repository.FuncionalidadRepository;
import com.sds.repository.RolAccionFuncionalidadRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.acl.ACLService;
import com.sds.service.acl.model.Menu;

@Service
public class ACLServiceImpl implements ACLService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RolAccionFuncionalidadRepository rolAccionFuncionalidadRepository;

	@Autowired
	FuncionalidadRepository funcionalidadRepository;

	@Autowired
	AccionRepository accionRepository;

	@Override
	public Menu funcionesUsuario(final String usuario) {
		final Menu menu = new Menu();
		final List<String> funcionalidadesMenu = new ArrayList<String>();

		final RolEntity rolUsuario = usuarioRepository.findRolUsuario(usuario);

		final List<Integer> id_funcionalidades = rolAccionFuncionalidadRepository
				.findFuncionalityByRolId(rolUsuario.getIdRol());

		for (final Integer funcion : id_funcionalidades) {
			final FuncionalidadEntity funcionalidad = funcionalidadRepository.findByIdFuncionalidad(funcion);
			funcionalidadesMenu.add(funcionalidad.getNombreFuncionalidad());
		}

		menu.setUsuario(usuario);
		menu.setFuncionalidades(funcionalidadesMenu);

		return menu;
	}

	@Override
	public List<String> accionesUsuarioFuncionalidad(final String usuario, final String funcionalidad) {
		final List<String> acciones = new ArrayList<String>();
		final List<Integer> idAcciones;

		final Integer idFuncionalidad = funcionalidadRepository.findIdFuncionalidadByName(funcionalidad);

		idAcciones = rolAccionFuncionalidadRepository.findAccionByIdFuncionality(idFuncionalidad);

		for (final Integer accion : idAcciones) {
			acciones.add(accionRepository.findNombreAccionById(accion));
		}

		return acciones;
	}

}
