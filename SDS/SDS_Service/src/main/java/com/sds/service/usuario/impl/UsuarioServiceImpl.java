package com.sds.service.usuario.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.log.impl.LogServiceImpl;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final Util util;
	private final Validaciones validaciones;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	LogServiceImpl logServiceImpl;

	public UsuarioServiceImpl() {
		util = new Util();
		validaciones = new Validaciones();
	}

	@Override
	public String eliminarUsuario(final UsuarioEntity user)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(user.getDniUsuario());

		if (!usuarioBD.isPresent()) {
			logExcepciones = util.generarDatosLogExcepciones(user.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
					CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new UsuarioNoEncontradoException(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
		} else {
			user.setBorradoUsuario(1);
			modificarUsuario(user);
			resultado = Constantes.OK;
		}

		return resultado;
	}

	@Override
	public String modificarUsuario(final UsuarioEntity user)
			throws LogExcepcionesNoGuardadoException, UsuarioNoEncontradoException, LogAccionesNoGuardadoException {
		final Boolean usuarioValido = validaciones.comprobarUsuarioBlank(user);
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		if (usuarioValido) {
			final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(user.getDniUsuario());

			if (!usuarioBD.isPresent()) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(user.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
						CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new UsuarioNoEncontradoException(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
			} else {

				user.setUsuario(user.getUsuario());
				user.setPasswdUsuario(user.getPasswdUsuario());
				user.setBorradoUsuario(user.getBorradoUsuario());

				usuarioRepository.saveAndFlush(user);

				final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(user.getUsuario(),
						Constantes.ACCION_MODIFICAR_USUARIO, user.toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(user.getUsuario(),
						Constantes.ACCION_MODIFICAR_USUARIO, user.toString());

				resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
						|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.USUARIO_VACIO.name();
		}

		return resultado;
	}

}
