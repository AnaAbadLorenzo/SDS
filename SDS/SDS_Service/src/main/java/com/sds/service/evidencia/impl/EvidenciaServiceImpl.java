package com.sds.service.evidencia.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sds.repository.EvidenciaRepository;
import com.sds.service.evidencia.EvidenciaService;
import com.sds.service.log.LogService;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class EvidenciaServiceImpl implements EvidenciaService {

	@Autowired
	EvidenciaRepository evidenciaRepository;

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
	public void guardarEvidencia(final MultipartFile evidencia) throws IOException {

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

		if (!file.exists()) {
			file.createNewFile();
			evidencia.transferTo(file);
		}

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
