package com.sds.service.registro.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.EmpresaRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.registro.RegistroService;
import com.sds.service.registro.model.Registro;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	RolRepository rolRepository;

	private final Validaciones validaciones;

	public RegistroServiceImpl() {
		this.validaciones = new Validaciones();
	}

	@Override
	public String registrar(final Registro registro)
			throws UsuarioYaExisteException, PersonaYaExisteException, EmpresaYaExisteException {

		String resultado = StringUtils.EMPTY;

		final boolean registroValido = validaciones.comprobarRegistroBlank(registro);

		if (registroValido) {
			if (!existeRegistro(registro)) {

				if (registro.getDatosEmpresa().getIdEmpresa() == null) {
					final EmpresaEntity empresa = empresaRepository
							.findByCif(registro.getDatosEmpresa().getCifEmpresa());

					if (empresa == null) {
						empresaRepository.saveAndFlush(registro.getDatosEmpresa());
					} else {
						throw new EmpresaYaExisteException(CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo()));
					}

				}

				registro.getDatosPersona().setEmpresa(registro.getDatosEmpresa());
				personaRepository.saveAndFlush(registro.getDatosPersona());

				final RolEntity rol = rolRepository.findByRolName(Constantes.USUARIO);
				registro.getDatosUsuario().setRol(rol);
				registro.getDatosUsuario().setPersona(registro.getDatosPersona());
				registro.getDatosUsuario().setDniUsuario(registro.getDatosPersona().getDniP());
				usuarioRepository.saveAndFlush(registro.getDatosUsuario());

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.REGISTRO_VACIO.name();
		}

		return resultado;

	}

	public boolean existeRegistro(final Registro registro)
			throws UsuarioYaExisteException, PersonaYaExisteException, EmpresaYaExisteException {
		final Optional<PersonaEntity> persona = personaRepository.findById(registro.getDatosPersona().getDniP());

		if (!persona.isPresent()) {
			final UsuarioEntity usuario = usuarioRepository.findByUsuario(registro.getDatosUsuario().getUsuario());

			if (usuario == null) {
				return false;
			} else {
				throw new UsuarioYaExisteException(CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo()));
			}
		} else {
			throw new PersonaYaExisteException(CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo()));
		}
	}
}
