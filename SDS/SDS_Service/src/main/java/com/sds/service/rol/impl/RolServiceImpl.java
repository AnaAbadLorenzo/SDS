package com.sds.service.rol.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.RolAccionFuncionalidadRepository;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolAsociadoAccionFuncionalidadException;
import com.sds.service.exception.RolAsociadoUsuarioException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.rol.RolService;
import com.sds.service.rol.model.Rol;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class RolServiceImpl implements RolService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RolAccionFuncionalidadRepository rolAccionFuncionalidadRepository;

	@Autowired
	LogService logServiceImpl;

	final Validaciones validaciones;
	private final Util util;

	public RolServiceImpl() {
		this.validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public ReturnBusquedas<RolEntity> buscarRol(final String rolName, final String rolDescription, final int inicio,
			final int tamanhoPagina) {
		RolEntity rolUser = new RolEntity();
		List<RolEntity> rolesBD = new ArrayList<>();
		final List<RolEntity> toret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		rolesBD = entityManager.createNamedQuery("RolEntity.findRol").setParameter("rolName", rolName)
				.setParameter("rolDescription", rolDescription).setFirstResult(inicio).setMaxResults(tamanhoPagina)
				.getResultList();

		final Integer numberTotalResults = rolRepository.numberFindRol(rolName, rolDescription);

		if (!rolesBD.isEmpty()) {
			for (final RolEntity rol : rolesBD) {
				rolUser = new RolEntity(rol.getIdRol(), rol.getRolName(), rol.getRolDescription(), rol.getBorradoRol());
				toret.add(rolUser);

			}

		}
		datosBusqueda.add("rolName: " + rolName);
		datosBusqueda.add("rolDescription: " + rolDescription);

		final ReturnBusquedas<RolEntity> result = new ReturnBusquedas<RolEntity>(toret, datosBusqueda,
				numberTotalResults, toret.size());
		return result;

	}

	@Override
	public ReturnBusquedas<RolEntity> buscarTodos(final int inicio, final int tamanhoPagina) {
		final List<RolEntity> toret = new ArrayList<>();

		final List<RolEntity> roles = entityManager.createNamedQuery("RolEntity.findAllRol").setFirstResult(inicio)
				.setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = rolRepository.numberFindAllRol();

		if (!roles.isEmpty()) {
			for (int i = 0; i < roles.size(); i++) {
				final RolEntity rol = new RolEntity(roles.get(i).getIdRol(), roles.get(i).getRolName(),
						roles.get(i).getRolDescription(), roles.get(i).getBorradoRol());
				toret.add(rol);
			}
		}

		final ReturnBusquedas<RolEntity> result = new ReturnBusquedas<RolEntity>(toret, numberTotalResults,
				toret.size());
		return result;
	}

	@Override
	public ReturnBusquedas<RolEntity> buscarRolesEliminados(final int inicio, final int tamanhoPagina) {
		final List<RolEntity> toret = new ArrayList<>();

		final List<RolEntity> rolesEliminados = entityManager.createNamedQuery("RolEntity.findDeleteRol")
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = rolRepository.numberFindDeleteRol();

		if (!rolesEliminados.isEmpty()) {
			for (int i = 0; i < rolesEliminados.size(); i++) {
				final RolEntity rol = new RolEntity(rolesEliminados.get(i).getIdRol(),
						rolesEliminados.get(i).getRolName(), rolesEliminados.get(i).getRolDescription(),
						rolesEliminados.get(i).getBorradoRol());
				toret.add(rol);
			}
		}

		final ReturnBusquedas<RolEntity> result = new ReturnBusquedas<RolEntity>(toret, numberTotalResults,
				toret.size());
		return result;

	}

	@Override
	public String guardarRol(final Rol rol)
			throws RolYaExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final RolEntity rolEntity = rol.getRol();
		final Boolean rolValido = validaciones.comprobarRolBlank(rolEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		if (rolValido) {
			final RolEntity rolUsuario = rolRepository.findByRolName(rolEntity.getRolName());

			resultado = StringUtils.EMPTY;

			if (rolUsuario == null) {
				rolRepository.saveAndFlush(rolEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(rol.getUsuario(),
						Constantes.ACCION_AÑADIR_ROL, rol.getRol().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;

			} else {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(rol.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.ROL_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

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
	public String eliminarRol(final Rol rol) throws RolNoExisteException, RolAsociadoUsuarioException,
			LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, RolAsociadoAccionFuncionalidadException {
		final RolEntity rolEntity = rol.getRol();
		final Optional<RolEntity> rolUsuario = rolRepository.findById(rolEntity.getIdRol());
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		if (!rolUsuario.isPresent()) {

			logExcepciones = util.generarDatosLogExcepciones(rol.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
		} else {
			final List<UsuarioEntity> usuarios = usuarioRepository.findAll();

			for (final UsuarioEntity usuario : usuarios) {
				if (usuario.getRol().getIdRol().equals(rolEntity.getIdRol())) {
					logExcepciones = util.generarDatosLogExcepciones(rol.getUsuario(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.ROL_ASOCIADO_USUARIO_EXCEPTION.getCodigo()),
							CodeMessageErrors.ROL_ASOCIADO_USUARIO_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new RolAsociadoUsuarioException(CodeMessageErrors.ROL_ASOCIADO_USUARIO_EXCEPTION.getCodigo(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.ROL_ASOCIADO_USUARIO_EXCEPTION.getCodigo()));
				}
			}

			final List<Integer> rolAccionFuncionalidad = rolAccionFuncionalidadRepository
					.findFuncionalityByRolId(rolEntity.getIdRol());

			if (rolAccionFuncionalidad.isEmpty()) {
				rolEntity.setBorradoRol(1);
				rol.setRol(rolEntity);
				modificarRol(rol);
				resultado = Constantes.OK;
			} else {
				logExcepciones = util.generarDatosLogExcepciones(rol.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION.getCodigo()),
						CodeMessageErrors.ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new RolAsociadoAccionFuncionalidadException(
						CodeMessageErrors.ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(
								CodeMessageErrors.ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION.getCodigo()));
			}

		}

		return resultado;
	}

	@Override
	public String modificarRol(final Rol rol)
			throws RolNoExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		final RolEntity rolEntity = rol.getRol();
		final Boolean rolValido = validaciones.comprobarRolBlank(rolEntity);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		if (rolValido) {
			final Optional<RolEntity> rolUsuario = rolRepository.findById(rolEntity.getIdRol());

			if (!rolUsuario.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(rol.getUsuario(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				rolEntity.setRolName(rolEntity.getRolName());
				rolEntity.setRolDescription(rolEntity.getRolDescription());
				rolEntity.setBorradoRol(rolEntity.getBorradoRol());
				rolRepository.saveAndFlush(rolEntity);

				final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(rol.getUsuario(),
						Constantes.ACCION_BUSCAR_ROL, rolUsuario.toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(rol.getUsuario(),
						Constantes.ACCION_MODIFICAR_ROL, rol.getRol().toString());

				resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
						|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.ROL_VACIO.name();
		}

		return resultado;
	}

	@Override
	public String reactivarRol(final Rol rol)
			throws LogExcepcionesNoGuardadoException, RolNoExisteException, LogAccionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final RolEntity rolEntity = rol.getRol();

		final Optional<RolEntity> rolBD = rolRepository.findById(rolEntity.getIdRol());

		if (!rolBD.isPresent()) {
			final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(rol.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));

		} else {
			rolEntity.setBorradoRol(0);
			rol.setRol(rolEntity);
			resultado = modificarRol(rol);
		}

		return resultado;
	}

	@Override
	public void deleteRol(final Rol rol) throws RolNoExisteException {
		final Boolean rolValido = validaciones.comprobarRolBlank(rol.getRol());

		if (rolValido) {
			final Optional<RolEntity> rolBD = rolRepository.findById(rol.getRol().getIdRol());

			if (!rolBD.isPresent()) {
				throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
			} else {
				rolRepository.deleteRol(rol.getRol().getIdRol());
				rolRepository.flush();
			}
		}
	}

}
