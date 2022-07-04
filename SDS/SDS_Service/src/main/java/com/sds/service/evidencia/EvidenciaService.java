package com.sds.service.evidencia;

import org.springframework.web.multipart.MultipartFile;

import com.sds.service.exception.LecturaFicheroErroneaException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;

public interface EvidenciaService {

	String guardarEvidencia(final Integer idProceso, final Integer idProcedimientoUsuario,
			final MultipartFile evidencia, final String usuario)
			throws LecturaFicheroErroneaException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	String modificarEvidencia(final Integer idProceso, final Integer idProcedimientoUsuario,
			final MultipartFile evidencia, final String usuario)
			throws LecturaFicheroErroneaException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;
}
