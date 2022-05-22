package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestPlanService {

	List<DatosPruebaAtributos> getPruebasAtributoNombrePlan()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoNombrePlanBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoDescripPlan()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoDescripPlanBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoFechaPlan()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoFechaPlanBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesPlanBuscar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesPlanGuardar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesPlanEliminar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesPlanModificar()
			throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesPlanReactivar()
			throws IOException, ParseException, java.text.ParseException;

}
