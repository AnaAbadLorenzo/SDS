package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestNoticiaService {

	List<DatosPruebaAtributos> getPruebasAtributoTituloNoticia()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoTituloNoticiaBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoTextoNoticia()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoTextoNoticiaBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasNoticiasAccionBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasNoticiasAccionAñadir()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasNoticiasAccionModificar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasNoticiasAccionEliminar()
			throws IOException, ParseException, java.text.ParseException;

}
