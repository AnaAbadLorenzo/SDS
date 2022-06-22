package com.sds.service.nivel;

import java.text.ParseException;
import java.util.List;

import com.sds.model.NivelEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NivelNoExisteException;
import com.sds.service.exception.NivelYaExisteException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.nivel.model.Nivel;

public interface NivelService {

	List<NivelEntity> buscarNivel(final Integer idObjetivo, final Integer idProceso);

	String añadirNivel(final Nivel nivel) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			NivelYaExisteException, ObjetivoNoExisteException, ProcesoNoExisteException, ParseException;

	String deleteNivel(final Nivel nivel) throws NivelNoExisteException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, ParseException;
}
