package com.sds.service.procesoprocedimiento.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoProcedimientoEntity;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcesoProcedimientoRepository;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoYaExisteException;
import com.sds.service.procesoprocedimiento.ProcesoProcedimientoService;
import com.sds.service.procesoprocedimiento.model.ProcesoProcedimiento;

public class ProcesoProcedimientoServiceImpl implements ProcesoProcedimientoService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	ProcesoProcedimientoRepository procesoProcedimientoRepository;

	@Override
	public ReturnBusquedas<ProcesoProcedimientoEntity> buscarProcedimientoUsuario(final ProcesoEntity proceso,
			final ProcedimientoEntity procedimiento, final Integer ordenProceso, final int inicio,
			final int tamanhoPagina) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnBusquedas<ProcesoProcedimientoEntity> buscarTodos(final int inicio, final int tamanhoPagina) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProcesoProcedimientoEntity> buscarTodosSinP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String anadirProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException, ProcesoNoExisteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modificarProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoNoExisteException, ProcedimientoNoExisteException, ProcesoNoExisteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String eliminaProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProcesoProcedimiento(final ProcesoProcedimientoEntity procesoProcedimientoEntity)
			throws ProcesoProcedimientoNoExisteException {
		// TODO Auto-generated method stub

	}

}
