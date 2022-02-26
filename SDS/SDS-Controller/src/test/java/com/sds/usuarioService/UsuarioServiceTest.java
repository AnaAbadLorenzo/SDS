package com.sds.usuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.sds.service.exception.EmpresaAsociadaPersonasException;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.PersonaAnadir;
import com.sds.service.rol.RolService;
import com.sds.service.rol.model.Rol;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.usuario.model.Usuario;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioServiceTest {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PersonaService personaService;

	@Autowired
	EmpresaService empresaService;

	@Autowired
	RolService rolService;

	@Test
	public void UsuarioService_buscarUsuario() throws IOException, ParseException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.BUSCAR_USUARIO);
		final UsuarioEntity usuarioEntity = usuario.getUsuarioEntity();

		final ReturnBusquedas<UsuarioEntity> usuarioEncontrado = usuarioService
				.buscarUsuario(usuarioEntity.getDniUsuario(), usuarioEntity.getUsuario(), usuarioEntity.getRol(), 0, 1);

		assertNotNull(usuarioEncontrado.getListaBusquedas());
	}

	@Test
	public void UsuarioService_buscarDniUsuarioVacio() throws IOException, ParseException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.DNIP_VACIO_DATA);
		final UsuarioEntity usuarioEntity = usuario.getUsuarioEntity();

		final ReturnBusquedas<UsuarioEntity> usuarioEncontrado = usuarioService
				.buscarUsuario(usuarioEntity.getDniUsuario(), usuarioEntity.getUsuario(), usuarioEntity.getRol(), 0, 1);

		assertNotNull(usuarioEncontrado.getListaBusquedas());
	}

	@Test
	public void UsuarioService_buscarNombreUsuarioVacio() throws IOException, ParseException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_VACIO_DATA);
		final UsuarioEntity usuarioEntity = usuario.getUsuarioEntity();

		final ReturnBusquedas<UsuarioEntity> usuarioEncontrado = usuarioService
				.buscarUsuario(usuarioEntity.getDniUsuario(), usuarioEntity.getUsuario(), usuarioEntity.getRol(), 0, 1);

		assertNotNull(usuarioEncontrado.getListaBusquedas());
	}

	@Test
	public void UsuarioService_buscarDniNombreUsuarioVacios() throws IOException, ParseException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_DNI_NOMBRE_VACIOS);
		final UsuarioEntity usuarioEntity = usuario.getUsuarioEntity();

		final ReturnBusquedas<UsuarioEntity> usuarioEncontrado = usuarioService
				.buscarUsuario(usuarioEntity.getDniUsuario(), usuarioEntity.getUsuario(), usuarioEntity.getRol(), 0, 1);

		assertNotNull(usuarioEncontrado.getListaBusquedas());
	}

	@Test
	public void UsuarioService_buscarTodos() throws IOException, ParseException {

		final ReturnBusquedas<UsuarioEntity> usuarios = usuarioService.buscarTodos(0, 5);

		assertNotNull(usuarios.getListaBusquedas());

	}

	@Test
	public void UsuarioService_eliminarUsuarioCorrecto()
			throws UsuarioNoEncontradoException, IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, PersonaNoExisteException,
			UsuarioAsociadoPersonaException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException,
			EmpresaYaExisteException, PersonaYaExisteException, UsuarioYaExisteException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.GUARDAR_USUARIO);

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);

		final PersonaEntity persona = new PersonaEntity(usuario.getUsuarioEntity().getDniUsuario(), "Pepe", "Pepe pepe",
				format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, empresa);

		final PersonaAnadir personaAñadir = new PersonaAnadir(usuario.getUsuario(), persona,
				usuario.getUsuarioEntity());

		personaService.añadirPersona(personaAñadir);

		final String respuesta = usuarioService.eliminarUsuario(usuario);

		assertEquals(respuesta, Constantes.OK);

		usuarioService.deleteUsuario(usuario.getUsuarioEntity());
		personaService.deletePersona(persona);
		empresaService.deleteEmpresa(empresa);

	}

	@Test(expected = UsuarioNoEncontradoException.class)
	public void UsuarioService_eliminarUsuarioNoExiste() throws UsuarioNoEncontradoException, IOException,
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_NO_EXISTE);

		usuarioService.eliminarUsuario(usuario);

	}

	@Test
	public void UsuarioService_reactivarUsuarioCorrecto()
			throws UsuarioNoEncontradoException, IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, PersonaNoExisteException,
			UsuarioAsociadoPersonaException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException,
			EmpresaYaExisteException, PersonaYaExisteException, UsuarioYaExisteException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA,
				Constantes.REACTIVAR_USUARIO_CORRECTO);

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);

		final PersonaEntity persona = new PersonaEntity(usuario.getUsuarioEntity().getDniUsuario(), "Pepe", "Pepe pepe",
				format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, empresa);

		final PersonaAnadir personaAñadir = new PersonaAnadir(usuario.getUsuario(), persona,
				usuario.getUsuarioEntity());

		personaService.añadirPersona(personaAñadir);

		final String respuesta = usuarioService.reactivarUsuario(usuario);

		assertEquals(respuesta, Constantes.OK);

		usuarioService.deleteUsuario(usuario.getUsuarioEntity());
		personaService.deletePersona(persona);
		empresaService.deleteEmpresa(empresa);

	}

	@Test(expected = UsuarioNoEncontradoException.class)
	public void UsuarioService_reactivarUsuarioNoExiste() throws UsuarioNoEncontradoException, IOException,
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_NO_EXISTE);

		usuarioService.reactivarUsuario(usuario);

	}

	@Test
	public void UsuarioService_modificarContraseñaCorrecto()
			throws IOException, ParseException, java.text.ParseException, PersonaYaExisteException,
			UsuarioYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, PersonaNoExisteException, UsuarioAsociadoPersonaException,
			EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.GUARDAR_USUARIO);

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);

		final PersonaEntity persona = new PersonaEntity(usuario.getUsuarioEntity().getDniUsuario(), "Pepe", "Pepe pepe",
				format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, empresa);

		final PersonaAnadir personaAñadir = new PersonaAnadir(usuario.getUsuario(), persona,
				usuario.getUsuarioEntity());

		personaService.añadirPersona(personaAñadir);

		final String passwdUsuario = "JYU45sda";

		final String respuesta = usuarioService.cambiarContraseña(usuario, passwdUsuario);

		assertEquals(respuesta, Constantes.OK);

		usuarioService.deleteUsuario(usuario.getUsuarioEntity());
		personaService.deletePersona(persona);
		empresaService.deleteEmpresa(empresa);

	}

	@Test(expected = UsuarioNoEncontradoException.class)
	public void UsuarioService_modificarContraseñaUsuarioNoExiste() throws IOException, ParseException,
			java.text.ParseException, PersonaYaExisteException, UsuarioYaExisteException,
			LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, UsuarioNoEncontradoException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.GUARDAR_USUARIO);

		final String passwdUsuario = "JYU45sda";

		usuarioService.cambiarContraseña(usuario, passwdUsuario);

	}

	@Test
	public void UsuarioService_modificarRolUsuarioCorrecto()
			throws IOException, ParseException, java.text.ParseException, PersonaYaExisteException,
			UsuarioYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			RolYaExisteException, UsuarioNoEncontradoException, RolNoExisteException, PersonaNoExisteException,
			UsuarioAsociadoPersonaException, EmpresaNoEncontradaException, EmpresaAsociadaPersonasException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.GUARDAR_USUARIO);

		final Rol rol = generateRol(Constantes.URL_JSON_USUARIO_DATA, Constantes.ROL_USUARIO);

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);

		final PersonaEntity persona = new PersonaEntity(usuario.getUsuarioEntity().getDniUsuario(), "Pepe", "Pepe pepe",
				format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, empresa);

		final PersonaAnadir personaAñadir = new PersonaAnadir(usuario.getUsuario(), persona,
				usuario.getUsuarioEntity());

		rolService.guardarRol(rol);
		personaService.añadirPersona(personaAñadir);

		final ReturnBusquedas<RolEntity> rolBD = rolService.buscarRol(rol.getRol().getRolName(),
				rol.getRol().getRolDescription(), 0, 1);

		rol.getRol().setIdRol(rolBD.getListaBusquedas().get(0).getIdRol());

		final String respuesta = usuarioService.modificarRolUsuario(rol.getRol(), usuario);

		assertEquals(respuesta, Constantes.OK);

		usuarioService.deleteUsuario(usuario.getUsuarioEntity());
		personaService.deletePersona(persona);
		empresaService.deleteEmpresa(empresa);
		rolService.deleteRol(rol);

	}

	@Test(expected = UsuarioNoEncontradoException.class)
	public void UsuarioService_ModificarRolUsuarioNoExiste()
			throws IOException, ParseException, RolYaExisteException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, RolNoExisteException, UsuarioNoEncontradoException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_NO_EXISTE);

		final Rol rol = generateRol(Constantes.URL_JSON_USUARIO_DATA, Constantes.ROL_USUARIO);

		rolService.guardarRol(rol);

		final ReturnBusquedas<RolEntity> rolBD = rolService.buscarRol(rol.getRol().getRolName(),
				rol.getRol().getRolDescription(), 0, 1);

		rol.getRol().setIdRol(rolBD.getListaBusquedas().get(0).getIdRol());

		try {
			usuarioService.modificarRolUsuario(rol.getRol(), usuario);
		} catch (final UsuarioNoEncontradoException e) {
			throw new UsuarioNoEncontradoException(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
		} finally {
			rolService.deleteRol(rol);
		}

	}

	@Test(expected = RolNoExisteException.class)
	public void UsuarioService_ModificarRolUsuarioRolNoExiste()
			throws IOException, ParseException, java.text.ParseException, PersonaYaExisteException,
			UsuarioYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, UsuarioNoEncontradoException,
			PersonaNoExisteException, UsuarioAsociadoPersonaException, EmpresaNoEncontradaException,
			EmpresaAsociadaPersonasException, RolNoExisteException {

		final Usuario usuario = generateUsuario(Constantes.URL_JSON_USUARIO_DATA, Constantes.USUARIO_NO_EXISTE);

		final EmpresaEntity empresa = new EmpresaEntity(2, "R56789865", "Empresa", "Direccion", "988526352", 0);

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

		final PersonaEntity persona = new PersonaEntity(usuario.getUsuarioEntity().getDniUsuario(), "Pepe", "Pepe pepe",
				format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, empresa);

		final PersonaAnadir personaAñadir = new PersonaAnadir(usuario.getUsuario(), persona,
				usuario.getUsuarioEntity());

		personaService.añadirPersona(personaAñadir);

		final Rol rol = generateRol(Constantes.URL_JSON_USUARIO_DATA, Constantes.ROL_USUARIO);

		try {
			usuarioService.modificarRolUsuario(rol.getRol(), usuario);
		} catch (final RolNoExisteException e) {
			throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
		} finally {
			usuarioService.deleteUsuario(usuario.getUsuarioEntity());
			personaService.deletePersona(persona);
			empresaService.deleteEmpresa(empresa);
		}

	}

	private Usuario generateUsuario(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonUsuario = new Util().getDatosJson(fichero, nombrePrueba);

		final Usuario usuario = new Usuario();
		final UsuarioEntity usuarioEntity = new UsuarioEntity();
		final RolEntity rolEntity = new RolEntity(2, "usuario",
				"Contendrá a todos los usuarios registrados de la aplicación", 0);

		usuario.setUsuario(CommonUtilities.coalesce(jsonUsuario.get(Constantes.USER).toString(), StringUtils.EMPTY));

		usuarioEntity.setDniUsuario(
				CommonUtilities.coalesce(jsonUsuario.get(Constantes.USUARIO_DNI).toString(), StringUtils.EMPTY));
		usuarioEntity.setUsuario(
				CommonUtilities.coalesce(jsonUsuario.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		usuarioEntity.setPasswdUsuario(
				CommonUtilities.coalesce(jsonUsuario.get(Constantes.PASSWD_USUARIO).toString(), StringUtils.EMPTY));

		usuarioEntity.setBorradoUsuario(0);
		usuarioEntity.setRol(rolEntity);

		usuario.setUsuarioEntity(usuarioEntity);

		return usuario;

	}

	private Rol generateRol(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonRol = new Util().getDatosJson(fichero, nombrePrueba);

		final Rol rol = new Rol();
		final RolEntity rolEntity = new RolEntity();

		final String idRol = CommonUtilities.coalesce(jsonRol.get(Constantes.ROL_ID).toString(), StringUtils.EMPTY);

		if (idRol.equals(StringUtils.EMPTY)) {
			rolEntity.setIdRol(0);
		} else {
			rolEntity.setIdRol(Integer.parseInt(idRol));
		}

		rol.setUsuario(CommonUtilities.coalesce(jsonRol.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));

		rolEntity.setRolName(CommonUtilities.coalesce(jsonRol.get(Constantes.ROL_NAME).toString(), StringUtils.EMPTY));
		rolEntity.setRolDescription(
				CommonUtilities.coalesce(jsonRol.get(Constantes.ROL_DESCRIPTION).toString(), StringUtils.EMPTY));
		rolEntity.setBorradoRol(0);

		rol.setRol(rolEntity);

		return rol;

	}

}