package com.sds.controller.persona;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.PersonaEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.Persona;
import com.sds.service.persona.model.PersonaAnadir;
import com.sds.service.persona.model.PersonaBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping("/persona")
public class PersonaController {

	private final Validaciones validaciones;

	@Autowired
	PersonaService personaService;

	public PersonaController() {
		this.validaciones = new Validaciones();
	}

	@RequestMapping(value = "/listarPersonas", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarTodos() {
		final List<PersonaEntity> personas = personaService.buscarTodos();

		return new RespEntity(RespCode.PERSONAS_LISTADAS, personas);
	}


	@RequestMapping(value = "/listarPersona", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarPersona(@RequestBody final PersonaBuscar persona) {
		final List<PersonaEntity> personas = personaService.buscarPersona(persona.getDnip(), persona.getNombreP(),
				persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(), persona.getTelefonoP(),
				persona.getEmailP(), persona.getEmpresaP());

		return new RespEntity(RespCode.PERSONAS_LISTADAS, personas);
	}

	@RequestMapping(value = "/listarPersonasEliminadas", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarPersonasEliminadas() {
		final List<PersonaEntity> personas = personaService.buscarPersonasEliminadas();

		return new RespEntity(RespCode.PERSONAS_LISTADAS, personas);
	}

	@RequestMapping(value = "/eliminarPersona", method = RequestMethod.POST)
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
		} catch (final UsuarioAsociadoPersonaException usuarioAsociadoPersona) {
			return new RespEntity(RespCode.USUARIO_ASOCIADO_PERSONA_EXCEPTION, persona);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, persona);
		}

	}

	@RequestMapping(value = "/persona", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity añadirPersona(@RequestBody final PersonaAnadir persona) {
		try {
			final Boolean personaValida = validaciones.comprobarPersonaBlank(persona.getPersonaEntity());

			if (personaValida) {
				String resultado;
				resultado = personaService.añadirPersona(persona);

				if (CodeMessageErrors.PERSONA_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.PERSONA_VACIA, persona);
				}

				if (CodeMessageErrors.USUARIO_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.USUARIO_VACIO, persona);
				}

				return new RespEntity(RespCode.PERSONA_GUARDADA, resultado);
			}
		} catch (final PersonaYaExisteException personaYaExiste) {
			return new RespEntity(RespCode.PERSONA_YA_EXISTE, personaYaExiste);
		} catch (final LogAccionesNoGuardadoException logAccionesNoGuardado) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, persona);
		} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardado) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, persona);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, persona);
		} catch (final UsuarioYaExisteException usuarioYaExiste) {
			return new RespEntity(RespCode.USUARIO_YA_EXISTE, persona);
		}

		return new RespEntity(RespCode.PERSONA_VACIA, persona);
	}

	@RequestMapping(value = "/modificarPersona", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity modificarPersona(@RequestBody final Persona persona) {
		try {
			final Boolean personaValida = validaciones.comprobarPersonaBlank(persona.getPersona());

			if (personaValida) {
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

	@RequestMapping(value = "/anadirPersona", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity anadirPersona(@RequestBody final PersonaAnadir persona) {
		try {
			final Boolean personaValida = validaciones.comprobarPersonaBlank(persona.getPersonaEntity());

			if (personaValida) {
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
		}

		return new RespEntity(RespCode.PERSONA_VACIA, persona);
	}

}
