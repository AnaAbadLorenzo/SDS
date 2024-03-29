package com.sds.service.empresa;

import java.util.List;

import com.sds.model.EmpresaEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.empresa.model.Empresa;
import com.sds.service.exception.EmpresaAsociadaPersonasException;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;

public interface EmpresaService {

	ReturnBusquedas<EmpresaEntity> buscarEmpresa(final String cifEmpresa, final String nombreEmpresa,
			final String direccionEmpresa, final String telefonoEmpresa, final int inicio, final int tamanhoPagina);

	ReturnBusquedas<EmpresaEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	List<EmpresaEntity> buscarTodos();

	ReturnBusquedas<EmpresaEntity> buscarEmpresasEliminadas(final int inicio, final int tamanhoPagina);

	void deleteEmpresa(final EmpresaEntity empresa)
			throws EmpresaNoEncontradaException, EmpresaAsociadaPersonasException;

	String eliminarEmpresa(final Empresa empresa) throws LogExcepcionesNoGuardadoException,
			EmpresaNoEncontradaException, EmpresaAsociadaPersonasException, LogAccionesNoGuardadoException;

	String añadirEmpresa(final Empresa empresa)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, EmpresaYaExisteException;

	String modificarEmpresa(final Empresa empresa)
			throws EmpresaNoEncontradaException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	String reactivarEmpresa(final Empresa empresa)
			throws EmpresaNoEncontradaException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

}
