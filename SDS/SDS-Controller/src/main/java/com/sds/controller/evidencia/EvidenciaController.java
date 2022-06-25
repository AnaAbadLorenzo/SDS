package com.sds.controller.evidencia;

import java.io.IOException;

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
	public RespEntity guardaEvidencia(@RequestParam("idProceso") final String idProceso,
			@RequestParam("idProcedimientoUsuario") final String idProcedimientoUsuario,
			@RequestParam("file") final MultipartFile document) {

		// TODO modificar porque ahora sólo se va a hacer la parte de guardar en una
		// carpeta la evidencia

		try {
			evidenciaService.guardarEvidencia(document);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new RespEntity(RespCode.PROCESO_ENCONTRADO, "");
	}

}
