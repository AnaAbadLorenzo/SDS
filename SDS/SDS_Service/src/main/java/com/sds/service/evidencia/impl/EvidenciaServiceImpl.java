package com.sds.service.evidencia.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sds.model.EvidenciaEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.repository.EvidenciaRepository;
import com.sds.repository.ProcedimientoUsuarioProcesoRepository;
import com.sds.service.common.Constantes;
import com.sds.service.evidencia.EvidenciaService;
import com.sds.service.exception.LecturaFicheroErroneaException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.log.LogService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class EvidenciaServiceImpl implements EvidenciaService {

	@Autowired
	EvidenciaRepository evidenciaRepository;

	@Autowired
	ProcedimientoUsuarioProcesoRepository procedimientoUsuarioProcesoRepository;

	@Autowired
	LogService logServiceImpl;

	public final static Logger LOGGER = LoggerFactory.getLogger(EvidenciaServiceImpl.class);

	private final Util util;
	private final Validaciones validaciones;

	public EvidenciaServiceImpl() {
		util = new Util();
		validaciones = new Validaciones();
	}

	@Override
	public String guardarEvidencia(final Integer idProceso, final Integer idProcedimientoUsuario,
			final MultipartFile evidencia, final String usuario)
			throws LecturaFicheroErroneaException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Boolean evidenciaValida = validaciones.comprobarEvidenciaBlank(idProceso, idProcedimientoUsuario,
				evidencia);

		if (evidenciaValida) {
			final String sSistemaOperativo = System.getProperty("os.name");
			String ubicacionArchivo = StringUtils.EMPTY;
			final Path path = Paths.get("");
			final String directoryName = path.toAbsolutePath().toString();

			if (sSistemaOperativo.toLowerCase().indexOf("windows") != -1) {

				if (directoryName.indexOf("SDS") != -1) {

					ubicacionArchivo = directoryName + File.separator + "evidencias" + File.separator;

					crearDirectorioEvidencias(ubicacionArchivo);

				} else {

					final String rutaTomcat = "webapps" + File.separator + "SDS" + File.separator + "WEB-INF"
							+ File.separator + "classes" + File.separator + "com" + File.separator + "sds";
					ubicacionArchivo = directoryName + File.separator + rutaTomcat + File.separator + "evidencias"
							+ File.separator;

					crearDirectorioEvidencias(ubicacionArchivo);

				}

			} else {
				if (directoryName.indexOf("SDS") != -1) {
					ubicacionArchivo = directoryName + "/evidencias/";
				} else {
					// TODO Pendiente ver la ruta que se genera en Linux para ver cómo añadirla y
					// meter la creación de la carpeta
				}
			}

			LOGGER.debug("Ubicacion archivo '{}'", ubicacionArchivo);

			final String nombreEvidencia = evidencia.getOriginalFilename();

			final File file = new File(ubicacionArchivo, nombreEvidencia);

			try {
				if (!file.exists()) {
					file.createNewFile();
					evidencia.transferTo(file);
				}
			} catch (final IOException ioException) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(usuario,
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ERROR_LECTURA_FICHERO.getCodigo()),
						CodeMessageErrors.ERROR_LECTURA_FICHERO.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new LecturaFicheroErroneaException(CodeMessageErrors.ERROR_LECTURA_FICHERO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ERROR_LECTURA_FICHERO.getCodigo()));
			}

			final List<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProceso = procedimientoUsuarioProcesoRepository
					.findProcedimientoUsuarioProcesoByIdProcedimientoUsuarioAndIdProceso(idProcedimientoUsuario,
							idProceso);

			if (!procedimientoUsuarioProceso.isEmpty()) {
				final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProceso
						.get(0);

				final EvidenciaEntity evidenciaEntity = new EvidenciaEntity(new Date(), 0, file.getName());
				evidenciaEntity.setProcedimientosUsuarioProceso(procedimientoUsuarioProcesoBD);
				evidenciaRepository.saveAndFlush(evidenciaEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(usuario,
						Constantes.ACCION_AÑADIR_EVIDENCIA, "Evidencia subida: " + evidencia.getName());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}
				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.EVIDENCIA_VACIA.name();
		}

		return resultado;

	}

	@Override
	public String modificarEvidencia(final Integer idProceso, final Integer idProcedimientoUsuario,
			final MultipartFile evidencia, final String usuario)
			throws LecturaFicheroErroneaException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Boolean evidenciaValida = validaciones.comprobarEvidenciaBlank(idProceso, idProcedimientoUsuario,
				evidencia);

		if (evidenciaValida) {
			final String sSistemaOperativo = System.getProperty("os.name");
			String ubicacionArchivo = StringUtils.EMPTY;
			final Path path = Paths.get("");
			final String directoryName = path.toAbsolutePath().toString();

			if (sSistemaOperativo.toLowerCase().indexOf("windows") != -1) {

				if (directoryName.indexOf("SDS") != -1) {

					ubicacionArchivo = directoryName + File.separator + "evidencias" + File.separator;

					crearDirectorioEvidencias(ubicacionArchivo);

				} else {

					final String rutaTomcat = "webapps" + File.separator + "SDS" + File.separator + "WEB-INF"
							+ File.separator + "classes" + File.separator + "com" + File.separator + "sds";
					ubicacionArchivo = directoryName + File.separator + rutaTomcat + File.separator + "evidencias"
							+ File.separator;

					crearDirectorioEvidencias(ubicacionArchivo);

				}

			} else {
				if (directoryName.indexOf("SDS") != -1) {
					ubicacionArchivo = directoryName + "/evidencias/";
				} else {
					// TODO Pendiente ver la ruta que se genera en Linux para ver cómo añadirla y
					// meter la creación de la carpeta
				}
			}

			LOGGER.debug("Ubicacion archivo '{}'", ubicacionArchivo);

			final String nombreEvidencia = evidencia.getOriginalFilename();

			final File file = new File(ubicacionArchivo, nombreEvidencia);

			try {
				if (!file.exists()) {
					file.createNewFile();
					evidencia.transferTo(file);

				}
			} catch (final IOException ioException) {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(usuario,
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ERROR_LECTURA_FICHERO.getCodigo()),
						CodeMessageErrors.ERROR_LECTURA_FICHERO.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new LecturaFicheroErroneaException(CodeMessageErrors.ERROR_LECTURA_FICHERO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ERROR_LECTURA_FICHERO.getCodigo()));
			}

			final List<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProceso = procedimientoUsuarioProcesoRepository
					.findProcedimientoUsuarioProcesoByIdProcedimientoUsuarioAndIdProceso(idProcedimientoUsuario,
							idProceso);

			if (!procedimientoUsuarioProceso.isEmpty()) {
				final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProceso
						.get(0);
				final EvidenciaEntity evidenciaBD = evidenciaRepository.findEvidenciaByIdProcedimientoUsuarioProceso(
						procedimientoUsuarioProcesoBD.getIdProcedimientoUsuarioProceso());
				evidenciaBD.setNombreFichero(file.getName());
				evidenciaRepository.saveAndFlush(evidenciaBD);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(usuario,
						Constantes.ACCION_AÑADIR_EVIDENCIA, "Evidencia subida: " + evidencia.getName());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.EVIDENCIA_VACIA.name();
		}

		return resultado;

	}

	private void crearDirectorioEvidencias(final String ubicacionArchivo) {

		final File directorioEvidencias = new File(ubicacionArchivo);

		if (!directorioEvidencias.exists()) {
			if (directorioEvidencias.mkdirs()) {
				LOGGER.debug("El directorio '{}' se ha creado correctamente", ubicacionArchivo);
			} else {
				LOGGER.debug("El directorio '{}' no se ha creado", ubicacionArchivo);
			}
		}
	}

}
