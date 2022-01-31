package com.sds.service.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.ResourceUtils;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;

public class Util {

	public JSONObject getDatosJson(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONParser parser = new JSONParser();
		final File file = ResourceUtils.getFile(fichero);
		final FileReader reader = new FileReader(file);
		final Object obj = parser.parse(reader);
		final JSONObject pJsonObj = (JSONObject) obj;
		final JSONArray array = new JSONArray();
		array.add(pJsonObj.get(nombrePrueba));

		return (JSONObject) array.get(0);
	}

	public LogExcepcionesEntity generarDatosLogExcepciones(final String usuario, final String tipo,
			final String descripcion) {

		final LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();
		logExcepciones.setUsuario(usuario);
		logExcepciones.setTipoExcepcion(tipo);
		logExcepciones.setDescripcionExcepcion(descripcion);
		logExcepciones.setFecha(getFechaHoraActual());

		return logExcepciones;

	}

	public LogAccionesEntity generarDatosLogAcciones(final String usuario, final String accion, final String datos) {

		final LogAccionesEntity logAcciones = new LogAccionesEntity();
		logAcciones.setUsuario(usuario);
		logAcciones.setAccion(accion);
		logAcciones.setDatos(datos);
		logAcciones.setFecha(getFechaHoraActual());

		return logAcciones;
	}

	private Date getFechaHoraActual() {

		final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		final Date date = calendar.getTime();

		return date;
	}
}
