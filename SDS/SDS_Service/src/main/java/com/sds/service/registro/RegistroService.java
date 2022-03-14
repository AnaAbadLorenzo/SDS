package com.sds.service.registro;

import java.text.ParseException;

import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.registro.model.Registro;

public interface RegistroService {

	String registrar(final Registro registro) throws UsuarioYaExisteException, PersonaYaExisteException,
			EmpresaYaExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ParseException,
			EmpresaNoEncontradaException;
}
