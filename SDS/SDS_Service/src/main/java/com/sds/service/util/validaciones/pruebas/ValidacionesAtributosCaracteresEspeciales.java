package com.sds.service.util.validaciones.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosCaracteresEspeciales {

	public String comprobarAtributoCaracteresEspeciales(final String atributo, final Funcionalidad funcionalidad,
			final Atributo atr) {

		String resultado = StringUtils.EMPTY;

		if (atr.equals(Atributo.FECHA_NACIMIENTO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;
			try {
				fecha = format.parse("0000-00-00");
				fechaSql = new java.sql.Date(fecha.getTime());
				if (atributo.equals(fechaSql.toString())) {
					resultado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
							+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

				}
			} catch (final ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < atributo.length(); i++) {

			final String letra = atributo.charAt(i) + "";

			final Pattern patron = Pattern.compile(Constantes.PATRON_CARACTERES_ESPECIALES);
			final Matcher comprobacion = patron.matcher(letra);

			if (comprobacion.matches()) {
				switch (funcionalidad) {
				case LOGIN:
					switch (atr) {
					case USUARIO:
						resultado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case PASSWD_USUARIO:
						resultado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					default:
						break;
					}

				case REGISTRAR:
					switch (atr) {
					case DNI_PERSONA:
						resultado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case NOMBRE:
						resultado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
								+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
						break;
					case APELLIDOS_PERSONA:
						resultado = CodigosMensajes.APELLIDOS_PERSONA_LETRAS_ACENTOS_INCORRECTO + " - "
								+ Mensajes.APELLIDOS_PERSONA_NO_PUEDEN_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
						break;
					case TELEFONO:
						resultado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
								+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;
						break;
					case USUARIO:
						resultado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case PASSWD_USUARIO:
						resultado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case CIF_EMPRESA:
						resultado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					}
				default:
					break;
				}
			}
		}

		return resultado;
	}

}
