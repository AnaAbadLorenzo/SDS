package com.sds.controller.plan;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.PlanEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.PlanAsociadoProcedimientoException;
import com.sds.service.exception.PlanNoExisteException;
import com.sds.service.exception.PlanYaExisteException;
import com.sds.service.plan.PlanService;
import com.sds.service.plan.model.Plan;
import com.sds.service.plan.model.PlanBuscar;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/plan")
public class PlanController {

	@Autowired
	PlanService planService;

	private final Validaciones validaciones;

	public PlanController() {
		validaciones = new Validaciones();
	}

	@PostMapping(value = "/listarPlan")
	@ResponseBody
	public RespEntity buscarPlan(@RequestBody final PlanBuscar plan) {

		final ReturnBusquedas<PlanEntity> resultado = planService.buscarPlan(plan.getNombrePlan(),
				plan.getDescripPlan(), plan.getFechaPlan(), plan.getObjetivo(), plan.getInicio(),
				plan.getTamanhoPagina());

		return new RespEntity(RespCode.PLAN_ENCONTRADO, resultado);

	}

	@PostMapping(value = "/listarPlanes")
	@ResponseBody
	public RespEntity buscarTodos(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<PlanEntity> resultado = planService.buscarTodos(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.PLANES_LISTADOS, resultado);
	}

	@GetMapping(value = "/listarPlanesSinP")
	@ResponseBody
	public RespEntity buscarTodos() {
		final List<PlanEntity> resultado = planService.buscarTodosSinP();

		return new RespEntity(RespCode.PLANES_LISTADOS, resultado);
	}

	@PostMapping(value = "/listarPlanesEliminados")
	@ResponseBody
	public RespEntity buscarPlanesEliminados(@RequestBody final Paginacion paginacion) {
		final ReturnBusquedas<PlanEntity> resultado = planService.buscarPlanesEliminados(paginacion.getInicio(),
				paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.PLANES_ELIMINADOS_LISTADOS, resultado);

	}

	@PostMapping(value = "/plan")
	@ResponseBody
	public RespEntity guardarPlan(@RequestBody final Plan plan) {

		try {
			final Boolean planValido = validaciones.comprobarPlanBlank(plan.getPlan());

			if (Boolean.TRUE.equals(planValido)) {
				String resultado;
				try {
					resultado = planService.anadirPlan(plan);
					if (CodeMessageErrors.PLAN_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PLAN_VACIO, plan);
					}
					return new RespEntity(RespCode.PLAN_GUARDADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, plan);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, plan);
				} catch (final FechaAnteriorFechaActualException fechaAnteriorfechaActualException) {
					return new RespEntity(RespCode.FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL, plan);
				} catch (final ObjetivoNoExisteException objetivoNoExists) {
					return new RespEntity(RespCode.OBJETIVO_NO_EXISTE_EXCEPTION, plan);
				}
			}
		} catch (final PlanYaExisteException planExists) {
			return new RespEntity(RespCode.PLAN_YA_EXISTE_EXCEPTION, plan);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, plan);
		}

		return new RespEntity(RespCode.PLAN_VACIO, plan);
	}

	@PostMapping(value = "/modificarPlan")
	@ResponseBody
	public RespEntity modificarPlan(@RequestBody final Plan plan) {
		try {
			final Boolean planValido = validaciones.comprobarPlanBlank(plan.getPlan());

			if (Boolean.TRUE.equals(planValido)) {
				String resultado;
				try {
					resultado = planService.modificarPlan(plan);
					if (CodeMessageErrors.PLAN_VACIO.name().equals(resultado)) {
						return new RespEntity(RespCode.PLAN_VACIO, resultado);
					}
					return new RespEntity(RespCode.PLAN_MODIFICADO, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, plan);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, plan);
				} catch (final FechaAnteriorFechaActualException fechaAnteriorfechaActualException) {
					return new RespEntity(RespCode.FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL, plan);
				} catch (final ObjetivoNoExisteException objetivoNoExists) {
					return new RespEntity(RespCode.OBJETIVO_NO_EXISTE_EXCEPTION, plan);
				}
			}
		} catch (final PlanNoExisteException planNoExists) {
			return new RespEntity(RespCode.PLAN_NO_EXISTE_EXCEPTION, plan);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, plan);
		}

		return new RespEntity(RespCode.PLAN_VACIO, plan);

	}

	@PostMapping(value = "/eliminarPlan")
	@ResponseBody
	public RespEntity eliminarPlan(@RequestBody final Plan plan) {

		try {
			String resultado;
			try {
				resultado = planService.eliminaPlan(plan);
				return new RespEntity(RespCode.PLAN_ELIMINADO, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, plan);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, plan);
			} catch (final PlanAsociadoProcedimientoException planAsociadoProcedimientoException) {
				return new RespEntity(RespCode.PLAN_ASOCIADO_PROCEDIMIENTO_EXCEPTION, plan);
			} catch (final ObjetivoNoExisteException objetivoNoExisteException) {
				return new RespEntity(RespCode.OBJETIVO_NO_EXISTE_EXCEPTION, plan);
			} catch (final FechaAnteriorFechaActualException fechaAnteriorfechaActualException) {
				return new RespEntity(RespCode.FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL, plan);
			}

		} catch (final PlanNoExisteException planNoExists) {
			return new RespEntity(RespCode.PLAN_NO_EXISTE_EXCEPTION, plan);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, plan);
		}
	}

	@PostMapping(value = "/reactivarPlan")
	@ResponseBody
	public RespEntity reactivarPlan(@RequestBody final Plan plan) {

		try {
			String resultado;
			try {
				resultado = planService.reactivarPlan(plan);
				return new RespEntity(RespCode.PLAN_REACTIVADO, resultado);

			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, plan);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, plan);
			}

		} catch (final PlanNoExisteException planNoExists) {
			return new RespEntity(RespCode.PLAN_NO_EXISTE_EXCEPTION, plan);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, plan);
		}

	}

	@PostMapping(value = "/borrarPlan")
	@ResponseBody
	public RespEntity borrarPlan(@RequestBody final PlanEntity plan) {
		try {
			planService.deletePlan(plan);
			return new RespEntity(RespCode.PLAN_BORRADO, plan);
		} catch (final PlanNoExisteException planNoExiste) {
			return new RespEntity(RespCode.PLAN_NO_EXISTE_EXCEPTION, plan);
		} catch (final ParseException parseException) {
			return new RespEntity(RespCode.PARSE_EXCEPTION, plan);
		}
	}
}
