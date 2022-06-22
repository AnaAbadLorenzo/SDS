package com.sds.service.procesorespuestaposible;

import java.text.ParseException;
import java.util.List;

import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoRespuestaPosibleNoExisteException;
import com.sds.service.exception.ProcesoRespuestaPosibleYaExisteException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.procesorespuestaposible.model.ProcesoRespuestaPosible;

public interface ProcesoRespuestaPosibleService {

	List<ProcesoRespuestaPosibleEntity> buscarProcesoRespuestaPosible(final Integer idProceso,
			final Integer idRespuesta);

	public String anadirProcesoRespuestaPosible(final ProcesoRespuestaPosible procesoRespuestaPosible)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoNoExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException, ParseException;

	String eliminaProcesoRespuestaPosible(final ProcesoRespuestaPosible procesoRespuestaPosible)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoNoExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleNoExisteException, ParseException;
}
