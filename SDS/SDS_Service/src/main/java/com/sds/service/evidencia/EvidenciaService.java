package com.sds.service.evidencia;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface EvidenciaService {

	void guardarEvidencia(final MultipartFile evidencia) throws IOException;
}
