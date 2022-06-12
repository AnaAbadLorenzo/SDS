package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestProcesoService {

	List<DatosPruebaAtributos> getPruebasAtributoNombreProceso()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoNombreProcesoBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoDescripProceso()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoDescripProcesoBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoFechaProceso()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoFechaProcesoBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesProcesoBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesProcesoGuardar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesProcesoEliminar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesProcesoModificar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesProcesoReactivar()
			throws IOException, ParseException, java.text.ParseException;
}
