package com.sds.service.util.validaciones.pruebas;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosCaracteresEspecialesSinSignosPuntuacion {

	public String comprobarAtributoCaracteresEspeciales(final String atributo, final Funcionalidad funcionalidad,
			final Atributo atr) throws ParseException {

		String resultado = StringUtils.EMPTY;

		for (int i = 0; i < atributo.length(); i++) {

			final String letra = atributo.charAt(i) + "";

			final Pattern patron = Pattern.compile(Constantes.PATRON_CARACTERES_ESPECIALES);
			final Matcher comprobacion = patron.matcher(letra);

			if (comprobacion.matches()) {
				switch (funcionalidad) {

				case GESTION_NOTICIAS:
					switch (atr) {
					case TEXTO_NOTICIA:
						resultado = CodigosMensajes.TEXTO_NOTICIA_ALFANMERICO_SIGNOS_PUNTUACION_INCORRECTO + " - "
								+ Mensajes.TEXTO_NOTICIA_PUEDE_CONTENER_LETRAS_NUMEROS_SIGNOS_PUNTUACION;
						break;
					default:
						break;
					}
					break;

				case GESTION_RESPUESTA_POSIBLE:
					switch (atr) {
					case TEXTO_RESPUESTA_POSIBLE:
						resultado = CodigosMensajes.TEXTO_RESPUESTA_ALFANUMERICO_SIGNOS_PUNTUACION_INCORRECTO + " - "
								+ Mensajes.TEXTO_RESPUESTA_SOLO_PUEDE_SER_ALFANUMERICO_CON_SIGNOS_PUNTUACION;
						break;
					default:
						break;
					}
					break;

				default:
					break;
				}

			}
		}

		return resultado;
	}

}
