package com.sds.noticiaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.model.NoticiasEntity;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.AccionAsociadaRolFuncionalidadException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NoticiaNoExisteException;
import com.sds.service.exception.NoticiaYaExisteException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.noticias.NoticiasService;
import com.sds.service.noticias.model.Noticia;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoticiaServiceTest {

	@Autowired
	NoticiasService noticiasService;

	@Test
	public void NoticiasService_buscarNoticia() throws IOException, ParseException {

		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.BUSCAR_NOTICIA);
		final NoticiasEntity noticiaEntity = noticia.getNoticiaEntity();

		final ReturnBusquedas<NoticiasEntity> noticiaEncontrada = noticiasService.buscarNoticia(
				noticiaEntity.getTituloNoticia(), noticiaEntity.getTextoNoticia(), noticiaEntity.getFechaNoticia(), 0,
				1);

		assertNotNull(noticiaEncontrada.getListaBusquedas());
	}

	@Test
	public void NoticiasService_buscarNoticiaTituloVacio() throws IOException, ParseException {

		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TITULO_NOTICIA_VACIO);
		final NoticiasEntity noticiaEntity = noticia.getNoticiaEntity();

		final ReturnBusquedas<NoticiasEntity> noticiaEncontrada = noticiasService.buscarNoticia(
				noticiaEntity.getTituloNoticia(), noticiaEntity.getTextoNoticia(), noticiaEntity.getFechaNoticia(), 0,
				1);

		assertNotNull(noticiaEncontrada.getListaBusquedas());
	}

	@Test
	public void NoticiasService_buscarNoticiaTextoVacio() throws IOException, ParseException {

		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TEXTO_NOTICIA_VACIO);
		final NoticiasEntity noticiaEntity = noticia.getNoticiaEntity();

		final ReturnBusquedas<NoticiasEntity> noticiaEncontrada = noticiasService.buscarNoticia(
				noticiaEntity.getTituloNoticia(), noticiaEntity.getTextoNoticia(), noticiaEntity.getFechaNoticia(), 0,
				1);

		assertNotNull(noticiaEncontrada.getListaBusquedas());
	}

	@Test
	public void NoticiasService_buscarDatosNoticiaVacios() throws IOException, ParseException {

		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.DATOS_NOTICIA_VACIOS);
		final NoticiasEntity noticiaEntity = noticia.getNoticiaEntity();

		final ReturnBusquedas<NoticiasEntity> noticiaEncontrada = noticiasService.buscarNoticia(
				noticiaEntity.getTituloNoticia(), noticiaEntity.getTextoNoticia(), noticiaEntity.getFechaNoticia(), 0,
				1);

		assertNotNull(noticiaEncontrada.getListaBusquedas());
	}

	@Test
	public void NoticiasService_guardarNoticia() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, NoticiaYaExisteException, NoticiaNoExisteException {
		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.GUARDAR_NOTICIA);

		String respuesta = StringUtils.EMPTY;

		respuesta = noticiasService.añadirNoticia(noticia);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<NoticiasEntity> noticiaEliminar = noticiasService.buscarNoticiaSinFecha(
				noticia.getNoticiaEntity().getTituloNoticia(), noticia.getNoticiaEntity().getTextoNoticia(), 0, 1);

		noticiasService.deleteNoticia(new Noticia(noticia.getUsuario(), noticiaEliminar.getListaBusquedas().get(0)));
	}

	@Test
	public void NoticiasService_guardarTituloNoticiaVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			NoticiaYaExisteException, NoticiaNoExisteException {
		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TITULO_NOTICIA_VACIO);
		String respuesta = StringUtils.EMPTY;

		respuesta = noticiasService.añadirNoticia(noticia);

		assertEquals(CodeMessageErrors.NOTICIA_VACIA.name(), respuesta);

	}

	@Test
	public void NoticiasService_guardarTextoNoticiaVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, NoticiaYaExisteException {
		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TEXTO_NOTICIA_VACIO);
		String respuesta = StringUtils.EMPTY;

		respuesta = noticiasService.añadirNoticia(noticia);

		assertEquals(CodeMessageErrors.NOTICIA_VACIA.name(), respuesta);
	}

	@Test
	public void NoticiasService_guardarTituloTextoNoticiaVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, NoticiaYaExisteException {
		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.DATOS_NOTICIA_VACIOS);
		String respuesta = StringUtils.EMPTY;

		respuesta = noticiasService.añadirNoticia(noticia);

		assertEquals(CodeMessageErrors.NOTICIA_VACIA.name(), respuesta);
	}

	@Test(expected = NoticiaYaExisteException.class)
	public void NoticiasService_guardarNoticiaYaExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			NoticiaYaExisteException, NoticiaNoExisteException {

		final Noticia noticia = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.NOTICIA_YA_EXISTE);

		noticiasService.añadirNoticia(noticia);

		try {
			noticiasService.añadirNoticia(noticia);
		} catch (final NoticiaYaExisteException noticiaYaExisteException) {
			throw new NoticiaYaExisteException(CodeMessageErrors.NOTICIA_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.NOTICIA_YA_EXISTE_EXCEPTION.getCodigo()));
		} finally {

			final ReturnBusquedas<NoticiasEntity> noticiaDelete = noticiasService.buscarNoticia(
					noticia.getNoticiaEntity().getTituloNoticia(), noticia.getNoticiaEntity().getTextoNoticia(),
					new Date(), 0, 1);
			noticiasService.deleteNoticia(new Noticia(noticia.getUsuario(), noticiaDelete.getListaBusquedas().get(0)));
		}
	}

	@Test
	public void NoticiasService_modificarNoticia() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, NoticiaYaExisteException, NoticiaNoExisteException {

		final Noticia noticiaGuardar = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.MODIFICAR_NOTICIA);

		String respuesta = StringUtils.EMPTY;

		noticiasService.añadirNoticia(noticiaGuardar);

		final ReturnBusquedas<NoticiasEntity> noticiaModificar = noticiasService.buscarNoticiaSinFecha(
				noticiaGuardar.getNoticiaEntity().getTituloNoticia(),
				noticiaGuardar.getNoticiaEntity().getTextoNoticia(), 0, 1);

		noticiaModificar.getListaBusquedas().get(0).setTextoNoticia("Texto Modificado");

		noticiaGuardar.setNoticiaEntity(noticiaModificar.getListaBusquedas().get(0));

		respuesta = noticiasService.modificarNoticia(noticiaGuardar);

		assertEquals(Constantes.OK, respuesta);

		noticiasService.deleteNoticia(new Noticia(noticiaGuardar.getUsuario(), noticiaGuardar.getNoticiaEntity()));

	}

	@Test
	public void NoticiasService_modificarNoticiaTituloVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, NoticiaNoExisteException {

		final Noticia noticiaGuardar = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA,
				Constantes.TITULO_NOTICIA_VACIO);

		String respuesta = StringUtils.EMPTY;

		respuesta = noticiasService.modificarNoticia(noticiaGuardar);

		assertEquals(CodeMessageErrors.NOTICIA_VACIA.name(), respuesta);
	}

	@Test
	public void NoticiasService_modificarNoticiaTextoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, NoticiaNoExisteException {

		final Noticia noticiaGuardar = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA,
				Constantes.TEXTO_NOTICIA_VACIO);

		String respuesta = StringUtils.EMPTY;

		respuesta = noticiasService.modificarNoticia(noticiaGuardar);

		assertEquals(CodeMessageErrors.NOTICIA_VACIA.name(), respuesta);
	}

	@Test
	public void NoticiasService_modificarNoticiaDatosVacios() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, NoticiaNoExisteException {
		final Noticia noticiaGuardar = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA,
				Constantes.DATOS_NOTICIA_VACIOS);

		String respuesta = StringUtils.EMPTY;

		respuesta = noticiasService.modificarNoticia(noticiaGuardar);

		assertEquals(CodeMessageErrors.NOTICIA_VACIA.name(), respuesta);
	}

	@Test(expected = NoticiaNoExisteException.class)
	public void NoticiasService_modificarNoticiaNoExiste() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, NoticiaNoExisteException {

		final Noticia noticiaGuardar = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.NOTICIA_NO_EXISTE);

		noticiasService.modificarNoticia(noticiaGuardar);

	}

	@Test
	public void NoticiasService_eliminarNoticiaCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			NoticiaNoExisteException, NoticiaYaExisteException {
		final Noticia noticiaGuardar = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.ELIMINAR_NOTICIA);

		noticiasService.añadirNoticia(noticiaGuardar);

		final ReturnBusquedas<NoticiasEntity> noticiaEliminar = noticiasService.buscarNoticiaSinFecha(
				noticiaGuardar.getNoticiaEntity().getTituloNoticia(),
				noticiaGuardar.getNoticiaEntity().getTextoNoticia(), 0, 1);

		final String respuesta = noticiasService
				.deleteNoticia(new Noticia(noticiaGuardar.getUsuario(), noticiaEliminar.getListaBusquedas().get(0)));

		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = NoticiaNoExisteException.class)
	public void NoticiasService_eliminarNoticiaNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			NoticiaNoExisteException, AccionAsociadaRolFuncionalidadException {

		final Noticia noticiaGuardar = generateNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.NOTICIA_NO_EXISTE);

		noticiasService.deleteNoticia(new Noticia(noticiaGuardar.getUsuario(), noticiaGuardar.getNoticiaEntity()));

	}

	private Noticia generateNoticia(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonNoticiaVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Noticia noticia = new Noticia();
		final NoticiasEntity noticiaEntity = new NoticiasEntity();
		final String idNoticia = CommonUtilities
				.coalesce(jsonNoticiaVacios.get(Constantes.NOTICIA_ID).toString(), StringUtils.EMPTY).toString();

		if (idNoticia.equals(StringUtils.EMPTY)) {
			noticiaEntity.setIdNoticia(0);
		} else {
			noticiaEntity.setIdNoticia(Integer.parseInt(idNoticia));
		}

		noticiaEntity.setTituloNoticia(CommonUtilities
				.coalesce(jsonNoticiaVacios.get(Constantes.TITULO_NOTICIA).toString(), StringUtils.EMPTY));
		noticiaEntity.setTextoNoticia(CommonUtilities
				.coalesce(jsonNoticiaVacios.get(Constantes.TEXTO_NOTICIA).toString(), StringUtils.EMPTY));
		noticiaEntity.setFechaNoticia(new Date());
		noticia.setUsuario(
				CommonUtilities.coalesce(jsonNoticiaVacios.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		noticia.setNoticiaEntity(noticiaEntity);

		return noticia;

	}

}
