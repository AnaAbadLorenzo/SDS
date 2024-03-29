package com.sds.controller.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping(value = "/listarEmpresa")
	@ResponseBody
	public RespEntity buscarEmpresa(@RequestBody final EmpresaBuscar empresa) {
		final ReturnBusquedas<EmpresaEntity> resultado = empresaService.buscarEmpresa(empresa.getCifEmpresa(),
				empresa.getNombreEmpresa(), empresa.getDireccionEmpresa(), empresa.getTelefonoEmpresa(),
				empresa.getInicio(), empresa.getTamanhoPagina());

		return new RespEntity(RespCode.EMPRESA_ENCONTRADA, resultado);

	}

	@PostMapping(value = "/listarEmpresas")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<EmpresaEntity> resultado = empresaService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.EMPRESAS_LISTADAS, resultado);
	}

	@GetMapping(value = "/listarEmpresasSinP")
	@ResponseBody
	public RespEntity buscarTodos() {
		final List<EmpresaEntity> resultado = empresaService.buscarTodos();

		return new RespEntity(RespCode.EMPRESAS_LISTADAS, resultado);
	}

	@PostMapping(value = "/listarEmpresasEliminadas")
	@ResponseBody
	public RespEntity buscarEmpresasEliminadas(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<EmpresaEntity> resultado = empresaService.buscarEmpresasEliminadas(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.EMPRESAS_LISTADAS_ELIMINADAS, resultado);
	}

	@PostMapping(value = "/empresa")
	@ResponseBody
	public RespEntity guardarEmpresa(@RequestBody final Empresa empresa) {

		final Boolean empresaValido = validaciones.comprobarEmpresaBlank(empresa.getEmpresa());

		if (Boolean.TRUE.equals(empresaValido)) {
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

	@PostMapping(value = "/modificarEmpresa")
	@ResponseBody
	public RespEntity modificarEmpresa(@RequestBody final Empresa empresa) {

		final Boolean empresaValido = validaciones.comprobarEmpresaBlank(empresa.getEmpresa());

		if (Boolean.TRUE.equals(empresaValido)) {
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

	@PostMapping(value = "/eliminarEmpresa")
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

	@PostMapping(value = "/reactivarEmpresa")
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

	@PostMapping(value = "/borrarEmpresa")
	@ResponseBody
	public RespEntity borrarEmpresa(@RequestBody final EmpresaEntity empresa) {
		try {
			empresaService.deleteEmpresa(empresa);
			return new RespEntity(RespCode.EMPRESA_BORRADA, empresa);
		} catch (final EmpresaNoEncontradaException e) {
			return new RespEntity(RespCode.EMPRESA_NO_ENCONTRADA_EXCEPTION, empresa);
		} catch (final EmpresaAsociadaPersonasException e) {
			return new RespEntity(RespCode.EMPRESA_ASOCIADA_PERSONAS_EXCEPTION, empresa);
		}
	}

}
