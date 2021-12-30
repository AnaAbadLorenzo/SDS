package com.sds.service.persona;

import java.text.ParseException;

import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.persona.model.Persona;

public interface PersonaService {

	String eliminarPersona(Persona persona) throws LogExcepcionesNoGuardadoException, PersonaNoExisteException,
			UsuarioAsociadoPersonaException, ParseException, LogAccionesNoGuardadoException;

	String modificarPersona(Persona persona) throws LogExcepcionesNoGuardadoException, PersonaNoExisteException,
			ParseException, LogAccionesNoGuardadoException;

}
