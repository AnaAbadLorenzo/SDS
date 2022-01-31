package com.sds.service.persona;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.persona.model.Persona;

public interface PersonaService {

	List<PersonaEntity> buscarTodos();

	List<PersonaEntity> buscarPersonasEliminadas();

	List<PersonaEntity> buscarPersona(String dniP, String nombreP, String apellidosP, Date fechaNacP, String direccionP,
			String telefonoP, String emailP, EmpresaEntity empresa);

	String añadirPersona(final Persona persona) throws PersonaYaExisteException, ParseException,
			LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	void deletePersona(final PersonaEntity persona)
			throws PersonaNoExisteException, UsuarioAsociadoPersonaException, ParseException;

	String eliminarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException, PersonaNoExisteException,
			UsuarioAsociadoPersonaException, ParseException, LogAccionesNoGuardadoException;

	String modificarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException, PersonaNoExisteException,
			ParseException, LogAccionesNoGuardadoException;

}
