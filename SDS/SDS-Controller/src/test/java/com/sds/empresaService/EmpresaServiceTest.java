package com.sds.empresaService;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.model.EmpresaEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.empresa.EmpresaService;
import com.sds.service.empresa.model.Empresa;
import com.sds.service.exception.EmpresaAsociadaPersonasException;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.PersonaAnadir;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmpresaServiceTest {

	@Autowired
	EmpresaService empresaService;
	@Autowired
	PersonaService personaService;
	@Autowired
	UsuarioService usuarioService;

	@Test
	public void EmpresaService_buscarEmpresa() throws IOException, ParseException {

		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.BUSCAR_EMPRESA);
		final EmpresaEntity empresaEntity = empresa.getEmpresa();

		final ReturnBusquedas<EmpresaEntity> empresaEncontrada = empresaService.buscarEmpresa(
				empresaEntity.getCifEmpresa(), empresaEntity.getNombreEmpresa(), empresaEntity.getDireccionEmpresa(),
				empresaEntity.getTelefonoEmpresa(), 0, 1);

		assertNotNull(empresaEncontrada.getListaBusquedas());
	}

	@Test
	public void EmpresaService_buscarEmpresaCifVacio() throws IOException, ParseException {

		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.CIFEMPRESA_VACIO_DATA);
		final EmpresaEntity empresaEntity = empresa.getEmpresa();

		final ReturnBusquedas<EmpresaEntity> empresaEncontrada = empresaService.buscarEmpresa(
				empresaEntity.getCifEmpresa(), empresaEntity.getNombreEmpresa(), empresaEntity.getDireccionEmpresa(),
				empresaEntity.getTelefonoEmpresa(), 0, 1);

		assertNotNull(empresaEncontrada.getListaBusquedas());
	}

	@Test
	public void EmpresaService_buscarEmpresaNombreVacio() throws IOException, ParseException {

		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.NOMBREEMPRESA_VACIO_DATA);
		final EmpresaEntity empresaEntity = empresa.getEmpresa();

		final ReturnBusquedas<EmpresaEntity> empresaEncontrada = empresaService.buscarEmpresa(
				empresaEntity.getCifEmpresa(), empresaEntity.getNombreEmpresa(), empresaEntity.getDireccionEmpresa(),
				empresaEntity.getTelefonoEmpresa(), 0, 1);

		assertNotNull(empresaEncontrada.getListaBusquedas());
	}

	@Test
	public void EmpresaService_buscarEmpresaDireccionVacio() throws IOException, ParseException {

		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.DIRECCIONEMPRESA_VACIO_DATA);
		final EmpresaEntity empresaEntity = empresa.getEmpresa();

		final ReturnBusquedas<EmpresaEntity> empresaEncontrada = empresaService.buscarEmpresa(
				empresaEntity.getCifEmpresa(), empresaEntity.getNombreEmpresa(), empresaEntity.getDireccionEmpresa(),
				empresaEntity.getTelefonoEmpresa(), 0, 1);

		assertNotNull(empresaEncontrada.getListaBusquedas());
	}

	@Test
	public void EmpresaService_buscarEmpresaTelefonoVacio() throws IOException, ParseException {

		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.TELEFONOEMPRESA_VACIO_DATA);
		final EmpresaEntity empresaEntity = empresa.getEmpresa();

		final ReturnBusquedas<EmpresaEntity> empresaEncontrada = empresaService.buscarEmpresa(
				empresaEntity.getCifEmpresa(), empresaEntity.getNombreEmpresa(), empresaEntity.getDireccionEmpresa(),
				empresaEntity.getTelefonoEmpresa(), 0, 1);

		assertNotNull(empresaEncontrada.getListaBusquedas());
	}

	@Test
	public void EmpresaService_buscarEmpresaParametrosVacios() throws IOException, ParseException {

		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.PARAMETROS_EMPRESA_VACIOS);
		final EmpresaEntity empresaEntity = empresa.getEmpresa();

		final ReturnBusquedas<EmpresaEntity> empresaEncontrada = empresaService.buscarEmpresa(
				empresaEntity.getCifEmpresa(), empresaEntity.getNombreEmpresa(), empresaEntity.getDireccionEmpresa(),
				empresaEntity.getTelefonoEmpresa(), 0, 1);

		assertNotNull(empresaEncontrada.getListaBusquedas());
	}

	@Test
	public void EmpresaService_buscarTodos() throws IOException, ParseException {

		final ReturnBusquedas<EmpresaEntity> empresaEncontrada = empresaService.buscarTodos(0, 5);

		assertNotNull(empresaEncontrada.getListaBusquedas());
	}

	@Test
	public void EmpresaService_buscarEmpresasEliminadas() throws IOException, ParseException {

		final ReturnBusquedas<EmpresaEntity> empresaEncontrada = empresaService.buscarEmpresasEliminadas(0, 5);

		assertNotNull(empresaEncontrada.getListaBusquedas());
	}

	@Test
	public void EmpresaService_guardarEmpresa()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.GUARDAR_EMPRESA_CORRECTO);

		final String resultado = empresaService.añadirEmpresa(empresa);

		assertEquals(resultado, Constantes.OK);

		final ReturnBusquedas<EmpresaEntity> empresaBD = empresaService.buscarEmpresa(
				empresa.getEmpresa().getCifEmpresa(), empresa.getEmpresa().getNombreEmpresa(),
				empresa.getEmpresa().getDireccionEmpresa(), empresa.getEmpresa().getTelefonoEmpresa(), 0, 1);

		empresaService.deleteEmpresa(empresaBD.getListaBusquedas().get(0));
	}

	@Test(expected = EmpresaYaExisteException.class)
	public void EmpresaService_guardarEmpresaYaExiste()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.EMPRESA_YA_EXISTE);

		empresaService.añadirEmpresa(empresa);

	}

	@Test
	public void EmpresaService_guardarEmpresaCifVacio()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.CIFEMPRESA_VACIO_DATA);

		final String resultado = empresaService.añadirEmpresa(empresa);

		assertEquals(resultado, CodeMessageErrors.EMPRESA_VACIO.name());

	}

	@Test
	public void EmpresaService_guardarEmpresaNombreVacio()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.NOMBREEMPRESA_VACIO_DATA);

		final String resultado = empresaService.añadirEmpresa(empresa);

		assertEquals(resultado, CodeMessageErrors.EMPRESA_VACIO.name());

	}

	@Test
	public void EmpresaService_guardarEmpresaDireccionVacio()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.DIRECCIONEMPRESA_VACIO_DATA);

		final String resultado = empresaService.añadirEmpresa(empresa);

		assertEquals(resultado, CodeMessageErrors.EMPRESA_VACIO.name());

	}

	@Test
	public void EmpresaService_guardarEmpresaTelefonoVacio()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.TELEFONOEMPRESA_VACIO_DATA);

		final String resultado = empresaService.añadirEmpresa(empresa);

		assertEquals(resultado, CodeMessageErrors.EMPRESA_VACIO.name());

	}

	@Test
	public void EmpresaService_modificarEmpresa()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.MODIFICAR_EMPRESA_CORRECTO);

		empresaService.añadirEmpresa(empresa);

		empresa.getEmpresa().setNombreEmpresa("Nuevo nombre");
		empresa.getEmpresa().setDireccionEmpresa("Nueva direccion");

		final String respuesta = empresaService.modificarEmpresa(empresa);

		assertEquals(respuesta, Constantes.OK);

		empresaService.deleteEmpresa(empresa.getEmpresa());

	}

	@Test
	public void EmpresaService_modificarEmpresaCifVacio()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.CIFEMPRESA_VACIO_DATA);

		final String respuesta = empresaService.modificarEmpresa(empresa);

		assertEquals(respuesta, CodeMessageErrors.EMPRESA_VACIO.name());
	}

	@Test
	public void EmpresaService_modificarEmpresaNombreVacio()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.NOMBREEMPRESA_VACIO_DATA);

		final String respuesta = empresaService.modificarEmpresa(empresa);

		assertEquals(respuesta, CodeMessageErrors.EMPRESA_VACIO.name());
	}

	@Test
	public void EmpresaService_modificarEmpresaDireccionVacio()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.DIRECCIONEMPRESA_VACIO_DATA);

		final String respuesta = empresaService.modificarEmpresa(empresa);

		assertEquals(respuesta, CodeMessageErrors.EMPRESA_VACIO.name());
	}

	@Test
	public void EmpresaService_modificarEmpresaTelefonoVacio()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.TELEFONOEMPRESA_VACIO_DATA);

		final String respuesta = empresaService.modificarEmpresa(empresa);

		assertEquals(respuesta, CodeMessageErrors.EMPRESA_VACIO.name());
	}

	@Test(expected = EmpresaNoEncontradaException.class)
	public void EmpresaService_modificarEmpresaNoExiste()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.MODIFICAR_EMPRESA_CORRECTO);

		empresa.getEmpresa().setNombreEmpresa("Nuevo nombre");
		empresa.getEmpresa().setDireccionEmpresa("Nueva direccion");

		empresaService.modificarEmpresa(empresa);

	}

	@Test
	public void EmpresaService_eliminarEmpresa()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.ELIMINAR_EMPRESA_CORRECTO);

		empresaService.añadirEmpresa(empresa);

		final String resultado = empresaService.eliminarEmpresa(empresa);

		assertEquals(resultado, Constantes.OK);

		empresaService.deleteEmpresa(empresa.getEmpresa());
	}

	@Test(expected = EmpresaNoEncontradaException.class)
	public void EmpresaService_eliminarEmpresaNoExiste()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, EmpresaNoEncontradaException,
			EmpresaAsociadaPersonasException, LogAccionesNoGuardadoException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.EMPRESA_NO_EXISTE);

		empresaService.eliminarEmpresa(empresa);
	}

	@Test
	public void EmpresaService_reactivarEmpresa()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaYaExisteException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.REACTIVAR_EMPRESA_CORRECTO);

		empresaService.añadirEmpresa(empresa);

		final String resultado = empresaService.reactivarEmpresa(empresa);

		assertEquals(resultado, Constantes.OK);

		empresaService.deleteEmpresa(empresa.getEmpresa());
	}

	@Test(expected = EmpresaNoEncontradaException.class)
	public void EmpresaService_reactivarEmpresaNoExiste()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, EmpresaNoEncontradaException,
			EmpresaAsociadaPersonasException, LogAccionesNoGuardadoException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA, Constantes.EMPRESA_NO_EXISTE);

		empresaService.eliminarEmpresa(empresa);
	}

	@Test(expected = EmpresaAsociadaPersonasException.class)
	public void EmpresaService_eliminarEmpresaAsociadaPersonas()
			throws IOException, ParseException, LogExcepcionesNoGuardadoException, EmpresaNoEncontradaException,
			EmpresaAsociadaPersonasException, LogAccionesNoGuardadoException, java.text.ParseException,
			EmpresaYaExisteException, PersonaYaExisteException, UsuarioYaExisteException, UsuarioNoEncontradoException,
			PersonaNoExisteException, UsuarioAsociadoPersonaException {
		final Empresa empresa = generateEmpresa(Constantes.URL_JSON_EMPRESA_DATA,
				Constantes.ELIMINAR_EMPRESA_ASOCIADA_PERSONAS);

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		final RolEntity rol = new RolEntity(2, "usuario", "Contendrá a todos los usuarios registrados de la aplicacion",
				0);
		final PersonaEntity persona = new PersonaEntity("34567654R", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0);
		persona.setEmpresa(empresa.getEmpresa());
		final UsuarioEntity usuario = new UsuarioEntity("34567654R", "pepito", "pepito", 0, rol);

		final PersonaAnadir person = new PersonaAnadir(empresa.getUsuario(), persona, usuario);

		personaService.añadirPersona(person);

		try {
			empresaService.eliminarEmpresa(empresa);
		} catch (final EmpresaAsociadaPersonasException exception) {
			throw new EmpresaAsociadaPersonasException(
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_ASOCIADA_PERSONA_EXCEPTION.getCodigo()),
					CodeMessageErrors.EMPRESA_ASOCIADA_PERSONA_EXCEPTION.getCodigo());
		} finally {
			usuarioService.deleteUsuario(usuario);
			personaService.deletePersona(persona);
			empresaService.deleteEmpresa(empresa.getEmpresa());

		}

	}

	private Empresa generateEmpresa(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonEmpresa = new Util().getDatosJson(fichero, nombrePrueba);

		final Empresa empresa = new Empresa();
		final EmpresaEntity empresaEntity = new EmpresaEntity();

		final String idEmpresa = CommonUtilities.coalesce(jsonEmpresa.get(Constantes.ID_EMPRESA).toString(),
				StringUtils.EMPTY);

		if (idEmpresa.equals(StringUtils.EMPTY)) {
			empresaEntity.setIdEmpresa(0);
		} else {
			empresaEntity.setIdEmpresa(Integer.parseInt(idEmpresa));
		}

		empresa.setUsuario(CommonUtilities.coalesce(jsonEmpresa.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		empresaEntity.setCifEmpresa(
				CommonUtilities.coalesce(jsonEmpresa.get(Constantes.CIF_EMPRESA).toString(), StringUtils.EMPTY));
		empresaEntity.setNombreEmpresa(
				CommonUtilities.coalesce(jsonEmpresa.get(Constantes.NOMBRE_EMPRESA).toString(), StringUtils.EMPTY));
		empresaEntity.setDireccionEmpresa(
				CommonUtilities.coalesce(jsonEmpresa.get(Constantes.DIRECCION_EMPRESA).toString(), StringUtils.EMPTY));
		empresaEntity.setTelefonoEmpresa(
				CommonUtilities.coalesce(jsonEmpresa.get(Constantes.TELEFONO_EMPRESA).toString(), StringUtils.EMPTY));

		empresaEntity.setBorradoEmpresa(0);
		empresa.setEmpresa(empresaEntity);

		return empresa;

	}
}
