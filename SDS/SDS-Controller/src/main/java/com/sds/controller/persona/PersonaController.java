package com.sds.controller.persona;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.PersonaEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.Persona;
import com.sds.service.persona.model.PersonaAnadir;
import com.sds.service.persona.model.PersonaAsociarEmpresa;
import com.sds.service.persona.model.PersonaBuscar;
import com.sds.service.persona.model.PersonaUsuarioBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	PersonaService personaService;

	private final Validaciones validaciones;

	public PersonaController() {
		this.validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarPersonas")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		try {
			final ReturnBusquedas<PersonaEntity> personas = personaService.buscarTodos(paginacion.getInicio(),
					paginacion.getTamanhoPagina());

			return new RespEntity(RespCode.PERSONAS_LISTADAS, personas);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, parseException);
		}
	}

	@PostMapping(value = "/listarPersona")
	@ResponseBody
	public RespEntity buscarPersona(@RequestBody final PersonaBuscar persona) {
		try {
			final ReturnBusquedas<PersonaEntity> personas = personaService.buscarPersona(persona.getDniP(),
					persona.getNombreP(), persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
					persona.getTelefonoP(), persona.getEmailP(), persona.getInicio(), persona.getTamanhoPagina());

			return new RespEntity(RespCode.PERSONAS_LISTADAS, personas);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, parseException);
		}
	}

	@PostMapping(value = "/listarPersonaPorUsuario")
	@ResponseBody
	public RespEntity buscarPersonaByUsuario(@RequestBody final PersonaUsuarioBuscar personaUsuario) {
		try {
			final ReturnBusquedas<PersonaEntity> personas = personaService.buscarPersonaByUsuario(
					personaUsuario.getUsuario(), personaUsuario.getInicio(), personaUsuario.getTamanhoPagina());

			return new RespEntity(RespCode.PERSONAS_LISTADAS, personas);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, parseException);
		}
	}

	@PostMapping(value = "/listarPersonasEliminadas")
	@ResponseBody
	public RespEntity buscarPersonasEliminadas(@RequestBody final Paginacion paginacion) {
		try {
			final ReturnBusquedas<PersonaEntity> personas = personaService
					.buscarPersonasEliminadas(paginacion.getInicio(), paginacion.getTamanhoPagina());

			return new RespEntity(RespCode.PERSONAS_LISTADAS, personas);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, parseException);
		}
	}

	@PostMapping(value = "/eliminarPersona")
	@ResponseBody
	public RespEntity eliminarPersona(@RequestBody final Persona persona) {
		try {
			String resultado;
			resultado = personaService.eliminarPersona(persona);
			return new RespEntity(RespCode.PERSONA_ELIMINADA, resultado);
		} catch (final LogAccionesNoGuardadoException logAccionesNoGuardado) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, persona);
		} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardado) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, persona);
		} catch (final PersonaNoExisteException personaNoExiste) {
			return new RespEntity(RespCode.PERSONA_NO_EXISTE, persona);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, persona);
		}

	}

	@PostMapping(value = "/modificarPersona")
	@ResponseBody
	public RespEntity modificarPersona(@RequestBody final Persona persona) {
		try {
			final Boolean personaValida = validaciones.comprobarPersonaBlank(persona.getPersona());

			if (Boolean.TRUE.equals(personaValida)) {
				String resultado;
				resultado = personaService.modificarPersona(persona);

				if (CodeMessageErrors.PERSONA_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.PERSONA_VACIA, persona);
				}

				return new RespEntity(RespCode.PERSONA_MODIFICADA, resultado);
			}
		} catch (final PersonaNoExisteException personaNoExiste) {
			return new RespEntity(RespCode.PERSONA_NO_EXISTE, persona);
		} catch (final LogAccionesNoGuardadoException logAccionesNoGuardado) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, persona);
		} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardado) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, persona);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, persona);
		}

		return new RespEntity(RespCode.PERSONA_VACIA, persona);
	}

	@PostMapping(value = "/anadirPersona")
	@ResponseBody
	public RespEntity anadirPersona(@RequestBody final PersonaAnadir persona) {
		try {
			final Boolean personaValida = validaciones.comprobarPersonaBlank(persona.getPersonaEntity());

			if (Boolean.TRUE.equals(personaValida)) {
				final Boolean usuarioValido = validaciones.comprobarUsuarioBlank(persona.getUsuarioEntity());
				if (usuarioValido) {
					String resultado;
					resultado = personaService.añadirPersona(persona);

					if (CodeMessageErrors.PERSONA_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PERSONA_VACIA, persona);
					}

					if (CodeMessageErrors.USUARIO_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.USUARIO_VACIO, persona);
					}

					return new RespEntity(RespCode.PERSONA_GUARDADA, resultado);
				} else {
					return new RespEntity(RespCode.USUARIO_VACIO, persona);
				}
			}
		} catch (final UsuarioYaExisteException usuarioYaExiste) {
			return new RespEntity(RespCode.USUARIO_YA_EXISTE, persona);
		} catch (final PersonaYaExisteException personaYaExiste) {
			return new RespEntity(RespCode.PERSONA_YA_EXISTE, persona);
		} catch (final LogAccionesNoGuardadoException logAccionesNoGuardado) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, persona);
		} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardado) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, persona);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, persona);
		} catch (final EmpresaNoEncontradaException empresaNoEncontrada) {
			return new RespEntity(RespCode.EMPRESA_NO_ENCONTRADA_EXCEPTION, empresaNoEncontrada);
		} catch (final EmpresaYaExisteException empresaYaExiste) {
			return new RespEntity(RespCode.EMPRESA_YA_EXISTE_EXCEPTION, empresaYaExiste);
		}

		return new RespEntity(RespCode.PERSONA_VACIA, persona);
	}

	@PostMapping(value = "/asociarPersonaEmpresa")
	@ResponseBody
	public RespEntity asociarPersonaEmpresa(@RequestBody final PersonaAsociarEmpresa personaAsociarEmpresa) {
		String resultado = StringUtils.EMPTY;

		try {
			resultado = personaService.asociarEmpresaPersona(personaAsociarEmpresa);
			return new RespEntity(RespCode.PERSONA_ASOCIADA_EMPRESA, resultado);

		} catch (final LogAccionesNoGuardadoException logAccionesNoGuardado) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, personaAsociarEmpresa);
		} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardado) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, personaAsociarEmpresa);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, personaAsociarEmpresa);
		} catch (final EmpresaNoEncontradaException empresaNoEncontrada) {
			return new RespEntity(RespCode.EMPRESA_NO_ENCONTRADA_EXCEPTION, personaAsociarEmpresa);
		} catch (final PersonaNoExisteException personaNoExiste) {
			return new RespEntity(RespCode.PERSONA_NO_EXISTE, personaAsociarEmpresa);
		}
	}

	@PostMapping(value = "/borrarPersona")
	@ResponseBody
	public RespEntity borrarPersona(@RequestBody final PersonaEntity persona) {
		try {
			personaService.deletePersona(persona);
			return new RespEntity(RespCode.PERSONA_BORRADA, persona);
		} catch (final PersonaNoExisteException e) {
			return new RespEntity(RespCode.PERSONA_NO_EXISTE, persona);
		} catch (final UsuarioAsociadoPersonaException e) {
			return new RespEntity(RespCode.USUARIO_ASOCIADO_PERSONA_EXCEPTION, persona);
		} catch (final ParseException e) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, persona);
		}
	}

}
