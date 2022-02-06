package com.sds.service.test.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.PersonaRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.login.model.RecuperarPass;
import com.sds.service.mail.MailService;
import com.sds.service.mail.model.Mail;
import com.sds.service.test.TestRecuperarPassService;
import com.sds.service.test.impl.atributos.TestAtributoEmailRecuperarPass;
import com.sds.service.test.impl.atributos.TestAtributoUsuarioRecuperarPass;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestRecuperarPassServiceImpl implements TestRecuperarPassService {

	private final TestAtributoEmailRecuperarPass testAtributoEmailRecuperarPass;
	private final TestAtributoUsuarioRecuperarPass testAtributoUsuarioRecuperarPass;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	MailService mailServiceImpl;

	public TestRecuperarPassServiceImpl() {
		testAtributoEmailRecuperarPass = new TestAtributoEmailRecuperarPass();
		testAtributoUsuarioRecuperarPass = new TestAtributoUsuarioRecuperarPass();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoUsuario()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final RecuperarPass datosEntradaLoginUsuarioVacio = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_VACIO_DATA);
		final RecuperarPass datosEntradaLoginUsuarioAlfanumericoEnhe = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ENHE_DATA);
		final RecuperarPass datosEntradaLoginUsuarioAlfanumericoAcentos = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ACENTOS_DATA);
		final RecuperarPass datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales = generarJSON
				.generateRecuperarPass(Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO,
						Constantes.USUARIO_ALFANUMERICO_CARACTERES_ESPECIALES_DATA);
		final RecuperarPass datosEntradaLoginUsuarioAlfanumericoEspacios = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_ESPACIOS_DATA);
		final RecuperarPass datosEntradaLoginUsuarioAlfanumericoMenor3 = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_MENOR_3_DATA);
		final RecuperarPass datosEntradaLoginUsuarioAlfanumericoMayor45 = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_MAYOR_45_DATA);
		final RecuperarPass datosEntradaLoginUsuarioAlfabetico = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFABETICO_DATA);
		final RecuperarPass datosEntradaLoginUsuarioAlfanumerico = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_ALFANUMERICO_DATA);
		final RecuperarPass datosEntradaLoginUsuarioNumerico = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO, Constantes.USUARIO_NUMERICO_DATA);

		datosPruebaAtributos
				.add(testAtributoUsuarioRecuperarPass.getTestLoginUsuarioVacio(datosEntradaLoginUsuarioVacio));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass
				.getTestLoginUsuarioAlfanumericoEnhe(datosEntradaLoginUsuarioAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass
				.getTestLoginUsuarioAlfanumericoAcentos(datosEntradaLoginUsuarioAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass.getTestLoginUsuarioAlfanumericoCaracteresEspeciales(
				datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass
				.getTestLoginUsuarioAlfanumericoEspacios(datosEntradaLoginUsuarioAlfanumericoEspacios));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass
				.getTestLoginUsuarioAlfanumericoMenor3(datosEntradaLoginUsuarioAlfanumericoMenor3));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass
				.getTestLoginUsuarioAlfanumericoMayor45(datosEntradaLoginUsuarioAlfanumericoMayor45));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass
				.getTestLoginUsuarioCorrectoAlfanumerico(datosEntradaLoginUsuarioAlfabetico));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass
				.getTestLoginUsuarioCorrectoAlfanumerico(datosEntradaLoginUsuarioAlfanumerico));
		datosPruebaAtributos.add(testAtributoUsuarioRecuperarPass
				.getTestLoginUsuarioCorrectoAlfanumerico(datosEntradaLoginUsuarioNumerico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoEmail()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final RecuperarPass datosEntradaRegistroEmailPersonaVacio = generarJSON
				.generateRecuperarPass(Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_EMAIL, Constantes.EMAIL_VACIO_DATA);
		final RecuperarPass datosEntradaRegistroEmailPersonaAlfanumericoEnhe = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_EMAIL, Constantes.EMAIL_ALFANUMERICO_ENHE_DATA);
		final RecuperarPass datosEntradaRegistroEmailPersonaAlfanumericoAcentos = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_EMAIL, Constantes.EMAIL_ALFANUMERICO_ACENTOS_DATA);
		final RecuperarPass datosEntradaRegistroEmailPersonaAlfaNumericoEspacios = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_EMAIL, Constantes.EMAIL_ALFANUMERICO_ESPACIOS_DATA);
		final RecuperarPass datosEntradaRegistroEmailPersonaAlfaNumericoMenor4 = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_EMAIL, Constantes.EMAIL_ALFANUMERICO_MENOR_4_DATA);
		final RecuperarPass datosEntradaRegistroEmailPersonaAlfaNumericoMayor128 = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_EMAIL, Constantes.EMAIL_ALFANUMERICO_MAYOR_128_DATA);
		final RecuperarPass datosEntradaRegistroEmailPersonaFormatoCorrecto = generarJSON.generateRecuperarPass(
				Constantes.URL_JSON_RECUPERARPASS_ATRIBUTOS_EMAIL, Constantes.EMAILPERSONA_ALFABETICO_DATA);

		datosPruebaAtributos
				.add(testAtributoEmailRecuperarPass.getTestEmailPersonaVacio(datosEntradaRegistroEmailPersonaVacio));
		datosPruebaAtributos.add(testAtributoEmailRecuperarPass
				.getTestEmailPersonaAlfanumericoEnhe(datosEntradaRegistroEmailPersonaAlfanumericoEnhe));
		datosPruebaAtributos.add(testAtributoEmailRecuperarPass
				.getTestEmailPersonaAlfanumericoAcentos(datosEntradaRegistroEmailPersonaAlfanumericoAcentos));
		datosPruebaAtributos.add(testAtributoEmailRecuperarPass
				.getTestEmailPAlfanumericoEspacios(datosEntradaRegistroEmailPersonaAlfaNumericoEspacios));
		datosPruebaAtributos.add(testAtributoEmailRecuperarPass
				.getTestEmailPAlfanumericoMenor4(datosEntradaRegistroEmailPersonaAlfaNumericoMenor4));
		datosPruebaAtributos.add(testAtributoEmailRecuperarPass
				.getTestEmailPAlfanumericoMayor128(datosEntradaRegistroEmailPersonaAlfaNumericoMayor128));
		datosPruebaAtributos.add(testAtributoEmailRecuperarPass
				.getTestEmailPCorrectoAlfanumerico(datosEntradaRegistroEmailPersonaFormatoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRecuperarPass()
			throws IOException, ParseException, java.text.ParseException, MessagingException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RecuperarPass datosEntradaRecuperarPassCorrecto = generarJSON
				.generateRecuperarPass(Constantes.URL_JSON_RECUPERARPASS_DATA, Constantes.RECUPERAR_PASS_CORRECTO);
		final RecuperarPass datosEntradaRecuperarPassUsuarioNoEncontrado = generarJSON
				.generateRecuperarPass(Constantes.URL_JSON_RECUPERARPASS_DATA, Constantes.USUARIO_NO_EXISTE);
		final RecuperarPass datosEntradaRecuperarPassEmailNoEncontrado = generarJSON
				.generateRecuperarPass(Constantes.URL_JSON_RECUPERARPASS_DATA, Constantes.EMAIL_NO_ENCONTRADO);

		datosPruebaAcciones.add(getTestRecuperarPassCorrecto(datosEntradaRecuperarPassCorrecto));
		datosPruebaAcciones.add(getTestRecuperarPassUsuarioNoEncontrado(datosEntradaRecuperarPassUsuarioNoEncontrado));
		datosPruebaAcciones.add(getTestRecuperarPassEmailNoEncontrado(datosEntradaRecuperarPassEmailNoEncontrado));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestRecuperarPassCorrecto(final RecuperarPass datosEntradaRecuperarPassCorrecto)
			throws java.text.ParseException, MessagingException {

		final String resultadoObtenido = recuperarPass(datosEntradaRecuperarPassCorrecto);

		final String resultadoEsperado = CodigosMensajes.CONTRASEÑA_MODIFICADA_OK + "  - "
				+ Mensajes.CONTRASEÑA_RECUPERADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.RECUPERAR_PASS, Constantes.EXITO,
				getValorRecuperarContrasena(datosEntradaRecuperarPassCorrecto));
	}

	private DatosPruebaAcciones getTestRecuperarPassUsuarioNoEncontrado(
			final RecuperarPass datosEntradaRecuperarPassUsuarioNoEncontrado)
			throws java.text.ParseException, MessagingException {

		final String resultadoObtenido = recuperarPassUsuarioNoEncontrado(datosEntradaRecuperarPassUsuarioNoEncontrado);

		final String resultadoEsperado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.USUARIO_NO_EXISTE, Constantes.EXITO,
				getValorRecuperarContrasena(datosEntradaRecuperarPassUsuarioNoEncontrado));
	}

	private DatosPruebaAcciones getTestRecuperarPassEmailNoEncontrado(
			final RecuperarPass datosEntradaRecuperarPassEmailNoEncontrado)
			throws java.text.ParseException, MessagingException {

		final String resultadoObtenido = recuperarPassEmailNoEncontrado(datosEntradaRecuperarPassEmailNoEncontrado);

		final String resultadoEsperado = CodigosMensajes.MAIL_NO_ENCONTRADO + " - "
				+ Mensajes.MAIL_NO_ENCONTRADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.EMAIL_NO_ENCONTRADO, Constantes.EXITO,
				getValorRecuperarContrasena(datosEntradaRecuperarPassEmailNoEncontrado));
	}

	private String recuperarPass(final RecuperarPass recuperarPass)
			throws MessagingException, java.text.ParseException {
		String resultado = StringUtils.EMPTY;
		String passwdTmp = StringUtils.EMPTY;

		if (!validaciones.comprobarUsuarioBlank(recuperarPass.getUsuario())) {
			resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarEmailUsuarioBlank(recuperarPass.getEmailUsuario())) {
			resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
		} else {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			date = format.parse("2000-12-13");

			final EmpresaEntity empresa = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);
			final PersonaEntity persona = new PersonaEntity("11111111A", "Persona", "Apellidos", date, "Direccion",
					"977777777", "anaa1312@gmail.com", 0, empresa);
			final RolEntity rol = new RolEntity(2, "usuario",
					"Contendrá a todos los usuarios registrados de la aplicación", 0);
			final UsuarioEntity usuario = new UsuarioEntity("11111111A", "usuario", "usuario", 0, rol);

			personaRepository.saveAndFlush(persona);
			usuarioRepository.saveAndFlush(usuario);

			final UsuarioEntity user = usuarioRepository.findByUsuario(recuperarPass.getUsuario());

			if (user != null) {
				final Optional<PersonaEntity> personaBD = personaRepository.findById(usuario.getDniUsuario());

				if (personaBD.isPresent()) {
					if (personaBD.get().getEmailP().equals(recuperarPass.getEmailUsuario())) {
						passwdTmp = generarPasswdAleatoria();

						final Mail email = new Mail(Constantes.EMISOR_EMAIL, recuperarPass.getEmailUsuario(),
								Constantes.ASUNTO_EMAIL_RECU, passwdTmp, Constantes.TIPO_CONTENIDO, null);

						final String result = mailServiceImpl.enviarCorreo(email);

						if (result.equals(StringUtils.EMPTY)) {
							resultado = CodigosMensajes.MAIL_NO_ENVIADO + " - " + Mensajes.ERROR_AL_ENVIAR_MAIL;
						} else {

							final String passEncrypt = DigestUtils.md5Hex(passwdTmp);
							user.setPasswdUsuario(passEncrypt);
							usuarioRepository.saveAndFlush(user);

							resultado = CodigosMensajes.CONTRASEÑA_MODIFICADA_OK + "  - "
									+ Mensajes.CONTRASEÑA_RECUPERADA_CORRECTAMENTE;
						}

					} else {
						resultado = CodigosMensajes.MAIL_NO_ENCONTRADO + " - "
								+ Mensajes.MAIL_NO_ENCONTRADO_CORRECTAMENTE;
					}
				} else {
					resultado = CodigosMensajes.PERSONA_NO_EXISTE + " - " + Mensajes.PERSONA_NO_EXISTE;
				}
			} else {
				resultado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;
			}

			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());

		}

		return resultado;
	}

	private String recuperarPassEmailNoEncontrado(final RecuperarPass recuperarPass)
			throws MessagingException, java.text.ParseException {
		String resultado = StringUtils.EMPTY;
		String passwdTmp = StringUtils.EMPTY;

		if (!validaciones.comprobarUsuarioBlank(recuperarPass.getUsuario())) {
			resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarEmailUsuarioBlank(recuperarPass.getEmailUsuario())) {
			resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
		} else {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			date = format.parse("2000-12-13");

			final EmpresaEntity empresa = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);
			final PersonaEntity persona = new PersonaEntity("22222222A", "Persona", "Apellidos", date, "Direccion",
					"977777777", "anaa1312@gmail.com", 0, empresa);
			final RolEntity rol = new RolEntity(2, "usuario",
					"Contendrá a todos los usuarios registrados de la aplicación", 0);
			final UsuarioEntity usuario = new UsuarioEntity("22222222A", "usuario", "usuario", 0, rol);

			personaRepository.saveAndFlush(persona);
			usuarioRepository.saveAndFlush(usuario);

			final UsuarioEntity user = usuarioRepository.findByUsuario(recuperarPass.getUsuario());

			if (user != null) {
				final Optional<PersonaEntity> personaBD = personaRepository.findById(usuario.getDniUsuario());

				if (personaBD.isPresent()) {
					if (personaBD.get().getEmailP().equals(recuperarPass.getEmailUsuario())) {
						passwdTmp = generarPasswdAleatoria();

						final Mail email = new Mail(Constantes.EMISOR_EMAIL, recuperarPass.getEmailUsuario(),
								Constantes.ASUNTO_EMAIL_RECU, passwdTmp, Constantes.TIPO_CONTENIDO, null);

						final String result = mailServiceImpl.enviarCorreo(email);

						if (result.equals(StringUtils.EMPTY)) {
							resultado = CodigosMensajes.MAIL_NO_ENVIADO + " - " + Mensajes.ERROR_AL_ENVIAR_MAIL;
						} else {

							final String passEncrypt = DigestUtils.md5Hex(passwdTmp);
							user.setPasswdUsuario(passEncrypt);
							usuarioRepository.saveAndFlush(user);

							resultado = CodigosMensajes.CONTRASEÑA_MODIFICADA_OK + "  - "
									+ Mensajes.CONTRASEÑA_RECUPERADA_CORRECTAMENTE;
						}

					} else {
						resultado = CodigosMensajes.MAIL_NO_ENCONTRADO + " - "
								+ Mensajes.MAIL_NO_ENCONTRADO_CORRECTAMENTE;
					}
				} else {
					resultado = CodigosMensajes.PERSONA_NO_EXISTE + " - " + Mensajes.PERSONA_NO_EXISTE;
				}
			} else {
				resultado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;
			}

			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());

		}

		return resultado;
	}

	private String recuperarPassUsuarioNoEncontrado(final RecuperarPass recuperarPass)
			throws MessagingException, java.text.ParseException {
		String resultado = StringUtils.EMPTY;
		String passwdTmp = StringUtils.EMPTY;

		if (!validaciones.comprobarUsuarioBlank(recuperarPass.getUsuario())) {
			resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarEmailUsuarioBlank(recuperarPass.getEmailUsuario())) {
			resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
		} else {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			date = format.parse("2000-12-13");

			final EmpresaEntity empresa = new EmpresaEntity(1, "J26903286", "Prueba", "Prueba", "988212121", 0);
			final PersonaEntity persona = new PersonaEntity("33333333A", "Persona", "Apellidos", date, "Direccion",
					"977777777", "anaa1312@gmail.com", 0, empresa);
			final RolEntity rol = new RolEntity(2, "usuario",
					"Contendrá a todos los usuarios registrados de la aplicación", 0);
			final UsuarioEntity usuario = new UsuarioEntity("33333333A", "usuario", "usuario", 0, rol);

			personaRepository.saveAndFlush(persona);
			usuarioRepository.saveAndFlush(usuario);

			final UsuarioEntity user = usuarioRepository.findByUsuario(recuperarPass.getUsuario());

			if (user != null) {
				final Optional<PersonaEntity> personaBD = personaRepository.findById(usuario.getDniUsuario());

				if (personaBD.isPresent()) {
					if (personaBD.get().getEmailP().equals(recuperarPass.getEmailUsuario())) {
						passwdTmp = generarPasswdAleatoria();

						final Mail email = new Mail(Constantes.EMISOR_EMAIL, recuperarPass.getEmailUsuario(),
								Constantes.ASUNTO_EMAIL_RECU, passwdTmp, Constantes.TIPO_CONTENIDO, null);

						final String result = mailServiceImpl.enviarCorreo(email);

						if (result.equals(StringUtils.EMPTY)) {
							resultado = CodigosMensajes.MAIL_NO_ENVIADO + " - " + Mensajes.ERROR_AL_ENVIAR_MAIL;
						} else {

							final String passEncrypt = DigestUtils.md5Hex(passwdTmp);
							user.setPasswdUsuario(passEncrypt);
							usuarioRepository.saveAndFlush(user);

							resultado = CodigosMensajes.CONTRASEÑA_MODIFICADA_OK + "  - "
									+ Mensajes.CONTRASEÑA_RECUPERADA_CORRECTAMENTE;
						}

					} else {
						resultado = CodigosMensajes.MAIL_NO_ENCONTRADO + " - "
								+ Mensajes.MAIL_NO_ENCONTRADO_CORRECTAMENTE;
					}
				} else {
					resultado = CodigosMensajes.PERSONA_NO_EXISTE + " - " + Mensajes.PERSONA_NO_EXISTE;
				}
			} else {
				resultado = CodigosMensajes.USUARIO_NO_ENCONTRADO + " - " + Mensajes.USUARIO_NO_ENCONTRADO;
			}

			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());

		}

		return resultado;
	}

	private String generarPasswdAleatoria() {
		final int longitudPasswd = 10;
		String passwdTemporal = StringUtils.EMPTY;

		final char[] caract = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'e', 'h', 'i', 'j', 'l', 'k', 'm', 'n', 'o', 'p',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9' };

		final Random rand = new Random();

		for (int i = 0; i < longitudPasswd; i++) {
			passwdTemporal += caract[rand.nextInt(caract.length)];
		}

		return passwdTemporal;
	}

	private Map<String, String> getValorRecuperarContrasena(final RecuperarPass recuperarPass) {

		final Map<String, String> valor = new HashMap<>();
		valor.put(Constantes.USUARIO, recuperarPass.getUsuario());
		valor.put(Constantes.EMAILP, recuperarPass.getEmailUsuario());

		return valor;
	}

}
