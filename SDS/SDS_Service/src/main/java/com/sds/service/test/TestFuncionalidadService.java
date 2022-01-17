package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestFuncionalidadService {

	List<DatosPruebaAtributos> getPruebasAtributoFuncionalidadName()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoFuncionalidadDescription()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesFuncionalidadBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesFuncionalidadGuardar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesFuncionalidadEliminar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesFuncionalidadModificar()
			throws IOException, ParseException, java.text.ParseException;
}
