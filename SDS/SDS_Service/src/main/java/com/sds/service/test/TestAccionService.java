package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestAccionService {

	List<DatosPruebaAtributos> getPruebasAtributoAccionName()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoAccionNameBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoAccionDescription()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesAccionBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesAccionGuardar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesAccionEliminar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesAccionModificar()
			throws IOException, ParseException, java.text.ParseException;
}
