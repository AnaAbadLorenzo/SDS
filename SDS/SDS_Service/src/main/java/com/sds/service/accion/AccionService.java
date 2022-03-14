package com.sds.service.accion;

import com.sds.model.AccionEntity;
import com.sds.service.accion.model.Accion;
import com.sds.service.accion.model.AccionAsignar;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.AccionAsociadaRolFuncionalidadException;
import com.sds.service.exception.AccionNoExisteException;
import com.sds.service.exception.AccionYaExisteException;
import com.sds.service.exception.FuncionalidadNoExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolNoExisteException;

public interface AccionService {

	ReturnBusquedas<AccionEntity> buscarAccion(final String nombreAccion, final String descripAccion, final int inicio,
			final int tamanhoPagina);

	ReturnBusquedas<AccionEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<AccionEntity> buscarAccionesEliminadas(final int inicio, final int tamanhoPagina);

	String anadirAccion(final Accion accion)
			throws AccionYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String eliminarAccion(final Accion accion) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			AccionNoExisteException, AccionAsociadaRolFuncionalidadException;

	String modificarAccion(final Accion accion)
			throws LogExcepcionesNoGuardadoException, AccionNoExisteException, LogAccionesNoGuardadoException;

	String reactivarAccion(final Accion accion)
			throws LogExcepcionesNoGuardadoException, AccionNoExisteException, LogAccionesNoGuardadoException;

	String asignarAccciones(final AccionAsignar accion)
			throws LogExcepcionesNoGuardadoException, AccionNoExisteException, LogAccionesNoGuardadoException,
			FuncionalidadNoExisteException, RolNoExisteException;

	void deleteAccion(final AccionEntity accion) throws AccionNoExisteException;

}
