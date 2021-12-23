package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestRolService {

	List<DatosPruebaAtributos> getPruebasAtributoRolName() throws IOException, ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoRolDescription() throws IOException, ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesRol() throws IOException, ParseException, java.text.ParseException;

}
