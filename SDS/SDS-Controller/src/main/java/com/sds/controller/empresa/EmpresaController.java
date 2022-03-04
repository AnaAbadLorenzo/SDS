package com.sds.controller.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.EmpresaEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.empresa.EmpresaService;
import com.sds.service.empresa.model.Empresa;
import com.sds.service.empresa.model.EmpresaBuscar;
import com.sds.service.exception.EmpresaAsociadaPersonasException;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

	@Autowired
	EmpresaService empresaService;

	final Validaciones validaciones;

	public EmpresaController() {
		this.validaciones = new Validaciones();
	}

	@RequestMapping(value = "/listarEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity buscarEmpresa(@RequestBody final EmpresaBuscar empresa) {
		final ReturnBusquedas<EmpresaEntity> resultado = empresaService.buscarEmpresa(empresa.getCifEmpresa(),
				empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
				empresa.getInicio(), empresa.getTamanhoPagina());

		return new RespEntity(RespCode.EMPRESA_ENCONTRADA, resultado);

	}

	@RequestMapping(value = "/listarEmpresas", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<EmpresaEntity> resultado = empresaService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.EMPRESAS_LISTADAS, resultado);
	}

	@RequestMapping(value = "/listarEmpresasEliminadas", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity buscarEmpresasEliminadas(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<EmpresaEntity> resultado = empresaService.buscarEmpresasEliminadas(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.EMPRESAS_LISTADAS_ELIMINADAS, resultado);
	}

	@RequestMapping(value = "/empresa", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity guardarEmpresa(@RequestBody final Empresa empresa) {

		final Boolean empresaValido = validaciones.comprobarEmpresaBlank(empresa.getEmpresa());

		if (empresaValido) {
			try {
				String resultado;
				try {
					resultado = empresaService.añadirEmpresa(empresa);
					if (CodeMessageErrors.EMPRESA_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.EMPRESA_VACIA, empresa);
					}
					return new RespEntity(RespCode.EMPRESA_GUARDADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, empresa);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, empresa);
				}
			} catch (final EmpresaYaExisteException empresaYaExiste) {
				return new RespEntity(RespCode.EMPRESA_YA_EXISTE, empresa);
			}
		}

		return new RespEntity(RespCode.EMPRESA_VACIA, empresa);
	}

	@RequestMapping(value = "/modificarEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity modificarEmpresa(@RequestBody final Empresa empresa) {

		final Boolean empresaValido = validaciones.comprobarEmpresaBlank(empresa.getEmpresa());

		if (empresaValido) {
			try {
				String resultado;
				try {
					resultado = empresaService.modificarEmpresa(empresa);
					if (CodeMessageErrors.EMPRESA_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.EMPRESA_VACIA, empresa);
					}
					return new RespEntity(RespCode.EMPRESA_MODIFICADA, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, empresa);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, empresa);
				}
			} catch (final EmpresaNoEncontradaException empresaNoEncontradaException) {
				return new RespEntity(RespCode.EMPRESA_NO_ENCONTRADA_EXCEPTION, empresa);
			}
		}

		return new RespEntity(RespCode.EMPRESA_VACIA, empresa);
	}

	@RequestMapping(value = "/eliminarEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity eliminarEmpresa(@RequestBody final Empresa empresa) {

		try {
			String resultado;
			try {
				resultado = empresaService.eliminarEmpresa(empresa);
				return new RespEntity(RespCode.EMPRESA_ELIMINADA, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, empresa);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, empresa);
			}
		} catch (final EmpresaNoEncontradaException empresaNoEncontradaException) {
			return new RespEntity(RespCode.EMPRESA_NO_ENCONTRADA_EXCEPTION, empresa);
		} catch (final EmpresaAsociadaPersonasException empresaAsociadaPersonasException) {
			return new RespEntity(RespCode.EMPRESA_ASOCIADA_PERSONAS_EXCEPTION, empresa);
		}

	}

	@RequestMapping(value = "/reactivarEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity reactivarEmpresa(@RequestBody final Empresa empresa) {
		try {
			String resultado;
			try {
				resultado = empresaService.reactivarEmpresa(empresa);
				return new RespEntity(RespCode.EMPRESA_REACTIVADA, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, empresa);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, empresa);
			}
		} catch (final EmpresaNoEncontradaException empresaNoEncontradaException) {
			return new RespEntity(RespCode.EMPRESA_NO_ENCONTRADA_EXCEPTION, empresa);
		}

	}

}
