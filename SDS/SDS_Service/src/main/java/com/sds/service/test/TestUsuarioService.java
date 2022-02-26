package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestUsuarioService {

	List<DatosPruebaAtributos> getPruebasAtributoUsuarioDniUsuario()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoUsuarioDniUsuarioBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoUsuarioNombreUsuario()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoUsuarioNombreUsuarioBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoUsuarioPasswdUsuario()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesUsuarioBuscar() throws IOException, ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesUsuarioEliminar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesUsuarioModificarRol()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesUsuarioCambiarContraseña()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesUsuarioReactivar()
			throws IOException, ParseException, java.text.ParseException;
}
