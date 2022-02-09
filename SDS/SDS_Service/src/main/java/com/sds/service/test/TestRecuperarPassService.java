package com.sds.service.test;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.json.simple.parser.ParseException;

import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

public interface TestRecuperarPassService {

	List<DatosPruebaAtributos> getPruebasAtributoUsuario() throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAtributos> getPruebasAtributoEmail() throws IOException, ParseException, java.text.ParseException;

	List<DatosPruebaAcciones> getPruebasAccionesRecuperarPass()
			throws IOException, ParseException, java.text.ParseException, MessagingException;
}
