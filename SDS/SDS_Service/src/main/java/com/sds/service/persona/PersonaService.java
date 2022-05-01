package com.sds.service.persona;

import java.text.ParseException;
import java.util.Date;

import com.sds.model.PersonaEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.persona.model.Persona;
import com.sds.service.persona.model.PersonaAnadir;

public interface PersonaService {

	ReturnBusquedas<PersonaEntity> buscarTodos(int inicio, int tamanhoPagina);

	ReturnBusquedas<PersonaEntity> buscarPersonasEliminadas(int inicio, int tamanhoPagina);

	ReturnBusquedas<PersonaEntity> buscarPersona(String dniP, String nombreP, String apellidosP, Date fechaNacP,
			String direccionP, String telefonoP, String emailP, int inicio, int tamanhoPagina);

	ReturnBusquedas<PersonaEntity> buscarPersonaByUsuario(String usuario, int inicio, int tamanhoPagina);

	String añadirPersona(final PersonaAnadir personaAñadir) throws PersonaYaExisteException, UsuarioYaExisteException,
			ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaNoEncontradaException, EmpresaYaExisteException;

	void deletePersona(final PersonaEntity persona)
			throws PersonaNoExisteException, UsuarioAsociadoPersonaException, ParseException;

	String eliminarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException, PersonaNoExisteException,
			ParseException, LogAccionesNoGuardadoException;

	String modificarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException, PersonaNoExisteException,
			ParseException, LogAccionesNoGuardadoException;

	String reactivarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException, PersonaNoExisteException,
			ParseException, LogAccionesNoGuardadoException;

}
