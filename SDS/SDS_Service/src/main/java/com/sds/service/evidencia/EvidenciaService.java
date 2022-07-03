package com.sds.service.evidencia;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface EvidenciaService {

	String guardarEvidencia(final Integer idProceso, final Integer idProcedimientoUsuario,
			final MultipartFile evidencia) throws IOException;

	String modificarEvidencia(final Integer idProceso, final Integer idProcedimientoUsuario,
			final MultipartFile evidencia) throws IOException;
}
