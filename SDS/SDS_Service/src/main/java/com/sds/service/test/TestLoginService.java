package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestLoginService {

	List<DatosPruebaAtributos> getPruebasAtributoUsuario() throws IOException, ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoContrasena() throws IOException, ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesLogin() throws IOException, ParseException;
}
