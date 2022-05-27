package com.sds.service.plan;

import java.text.ParseException;
import java.util.Date;

import com.sds.model.PlanEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.PlanNoExisteException;
import com.sds.service.exception.PlanYaExisteException;
import com.sds.service.plan.model.Plan;

public interface PlanService {

	ReturnBusquedas<PlanEntity> buscarPlan(final String nombrePlan, final String descripPlan, final Date fechaPlan,
			final int inicio, final int tamanhoPagina);

	ReturnBusquedas<PlanEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<PlanEntity> buscarPlanesEliminados(final int inicio, final int tamanhoPagina);

	String anadirPlan(Plan plan) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			PlanYaExisteException, ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException;

	String eliminaPlan(Plan plan) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			PlanNoExisteException, ParseException;

	String modificarPlan(final Plan plan) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			PlanNoExisteException, ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException;

	String reactivarPlan(final Plan plan) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			PlanNoExisteException, ParseException;

	void deletePlan(final PlanEntity plan) throws PlanNoExisteException, ParseException;
}
