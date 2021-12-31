package com.sds.service.empresa;

import com.sds.model.EmpresaEntity;
import com.sds.service.empresa.model.Empresa;
import com.sds.service.exception.EmpresaAsociadaPersonasException;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;

public interface EmpresaService {

	void deleteEmpresa(EmpresaEntity empresa) throws EmpresaNoEncontradaException, EmpresaAsociadaPersonasException;

	String eliminarEmpresa(Empresa empresa) throws LogExcepcionesNoGuardadoException, EmpresaNoEncontradaException,
			EmpresaAsociadaPersonasException, LogAccionesNoGuardadoException;

	String modificarEmpresa(final Empresa empresa)
			throws EmpresaNoEncontradaException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

}
