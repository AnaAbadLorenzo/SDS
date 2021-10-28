package com.sds.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.ResourceUtils;

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
}
