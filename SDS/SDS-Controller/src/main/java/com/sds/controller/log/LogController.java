package com.sds.controller.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Paginacion;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.log.LogService;
import com.sds.service.log.model.Log;

@RestController
@RequestMapping(value = "/log")
public class LogController {

	@Autowired
	LogService logServiceImpl;

	@PostMapping(value = "/listarTodosLogsExcepciones")
	@ResponseBody
	public RespEntity buscarTodosLogExcepciones(@RequestBody final Paginacion paginacion) {

		final ReturnBusquedas<LogExcepcionesEntity> resultado = logServiceImpl
				.buscarTodosLogExcepciones(paginacion.getInicio(), paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.LOG_EXCEPCIONES_LISTADOS, resultado);

	}

	@PostMapping(value = "/listarLogsExcepcionesUsuarioFecha")
	@ResponseBody
	public RespEntity buscarLogExcepciones(@RequestBody final Log log) {

		final ReturnBusquedas<LogExcepcionesEntity> resultado = logServiceImpl.buscarPorUsuarioYFechaLogExcepciones(
				log.getUsuario(), log.getFechaInicio(), log.getFechaFin(), log.getInicio(), log.getTamanhoPagina());

		return new RespEntity(RespCode.LOG_EXCEPCIONES_LISTADOS, resultado);

	}

	@PostMapping(value = "/listarTodosLogsAcciones")
	@ResponseBody
	public RespEntity buscarTodosLogAcciones(@RequestBody final Paginacion paginacion) {

		final ReturnBusquedas<LogAccionesEntity> resultado = logServiceImpl
				.buscarTodosLogAcciones(paginacion.getInicio(), paginacion.getTamanhoPagina());

		return new RespEntity(RespCode.LOG_ACCIONES_LISTADOS, resultado);

	}

	@PostMapping(value = "/listarLogsAccionesUsuarioFecha")
	@ResponseBody
	public RespEntity buscarLogAcciones(@RequestBody final Log log) {

		final ReturnBusquedas<LogAccionesEntity> resultado = logServiceImpl.buscarPorUsuarioYFechaLogAcciones(
				log.getUsuario(), log.getFechaInicio(), log.getFechaFin(), log.getInicio(), log.getTamanhoPagina());

		return new RespEntity(RespCode.LOG_ACCIONES_LISTADOS, resultado);

	}
}
