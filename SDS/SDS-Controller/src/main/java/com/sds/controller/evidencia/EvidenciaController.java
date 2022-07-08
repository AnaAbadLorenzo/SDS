package com.sds.controller.evidencia;

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
import com.sds.service.exception.LecturaFicheroErroneaException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
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

	@PostMapping(value = "/evidencia")
	@ResponseBody
	public RespEntity guardaEvidencia(@RequestParam("idProceso") final Integer idProceso,
			@RequestParam("idProcedimientoUsuario") final Integer idProcedimientoUsuario,
			@RequestParam("file") final MultipartFile document, @RequestParam("usuario") final String usuario) {

		String resultado = StringUtils.EMPTY;

		try {
			final Boolean evidenciaValida = validaciones.comprobarEvidenciaBlank(idProceso, idProcedimientoUsuario,
					document);

			if (evidenciaValida) {
				resultado = evidenciaService.guardarEvidencia(idProceso, idProcedimientoUsuario, document, usuario);
				return new RespEntity(RespCode.EVIDENCIA_GUARDADA, resultado);
			}

		} catch (final LecturaFicheroErroneaException e) {
			return new RespEntity(RespCode.LECTURA_FICHERO_ERRONEA, resultado);
		} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, document.getName());
		} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, document.getName());
		}

		return new RespEntity(RespCode.EVIDENCIA_VACIA, resultado);
	}

	@PostMapping(value = "/modificaEvidencia")
	@ResponseBody
	public RespEntity modificaEvidencia(@RequestParam("idProceso") final Integer idProceso,
			@RequestParam("idProcedimientoUsuario") final Integer idProcedimientoUsuario,
			@RequestParam("file") final MultipartFile document, @RequestParam("usuario") final String usuario) {

		String resultado = StringUtils.EMPTY;

		try {
			final Boolean evidenciaValida = validaciones.comprobarEvidenciaBlank(idProceso, idProcedimientoUsuario,
					document);

			if (evidenciaValida) {
				resultado = evidenciaService.modificarEvidencia(idProceso, idProcedimientoUsuario, document, usuario);
				return new RespEntity(RespCode.EVIDENCIA_GUARDADA, resultado);
			}

		} catch (final LecturaFicheroErroneaException e) {
			return new RespEntity(RespCode.LECTURA_FICHERO_ERRONEA, resultado);
		} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
			return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, document.getName());
		} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
			return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, document.getName());
		}

		return new RespEntity(RespCode.EVIDENCIA_VACIA, resultado);
	}

}
