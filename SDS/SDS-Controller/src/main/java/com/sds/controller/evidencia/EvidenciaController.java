package com.sds.controller.evidencia;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.evidencia.EvidenciaService;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/evidencia")
public class EvidenciaController {

	@Autowired
	EvidenciaService evidenciaService;

	final Validaciones validaciones;

	public EvidenciaController() {
		this.validaciones = new Validaciones();
	}

	@PostMapping(value = "/guardaEvidencia")
	@ResponseBody
	public RespEntity guardaEvidencia(@RequestParam("idProceso") final Integer idProceso,
			@RequestParam("idProcedimientoUsuario") final Integer idProcedimientoUsuario,
			@RequestParam("file") final MultipartFile document) {

		String resultado = StringUtils.EMPTY;

		try {
			final Boolean evidenciaValida = validaciones.comprobarEvidenciaBlank(idProceso, idProcedimientoUsuario,
					document);

			if (evidenciaValida) {
				resultado = evidenciaService.guardarEvidencia(idProceso, idProcedimientoUsuario, document);
				return new RespEntity(RespCode.EVIDENCIA_GUARDADA, resultado);
			}

		} catch (final IOException e) {
			return new RespEntity(RespCode.LECTURA_FICHERO_ERRONEA, resultado);
		}

		return new RespEntity(RespCode.EVIDENCIA_VACIA, resultado);
	}

	@PostMapping(value = "/modificaEvidencia")
	@ResponseBody
	public RespEntity modificaEvidencia(@RequestParam("idProceso") final Integer idProceso,
			@RequestParam("idProcedimientoUsuario") final Integer idProcedimientoUsuario,
			@RequestParam("file") final MultipartFile document) {

		String resultado = StringUtils.EMPTY;

		try {
			final Boolean evidenciaValida = validaciones.comprobarEvidenciaBlank(idProceso, idProcedimientoUsuario,
					document);

			if (evidenciaValida) {
				resultado = evidenciaService.modificarEvidencia(idProceso, idProcedimientoUsuario, document);
				return new RespEntity(RespCode.EVIDENCIA_GUARDADA, resultado);
			}

		} catch (final IOException e) {
			return new RespEntity(RespCode.LECTURA_FICHERO_ERRONEA, resultado);
		}

		return new RespEntity(RespCode.EVIDENCIA_VACIA, resultado);
	}

}
