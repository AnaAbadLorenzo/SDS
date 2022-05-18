package com.sds.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sds.accionService.AccionServiceTest;
import com.sds.empresaService.EmpresaServiceTest;
import com.sds.funcionalidadService.FuncionalidadServiceTest;
import com.sds.loginService.LoginServiceTest;
import com.sds.noticiaService.NoticiaServiceTest;
import com.sds.objetivoService.ObjetivoServiceTest;
import com.sds.personaService.PersonaServiceTest;
import com.sds.registroService.RegistroServiceTest;
import com.sds.respuestaPosibleService.RespuestaPosibleServiceTest;
import com.sds.rolService.RolServiceTest;
import com.sds.usuarioService.UsuarioServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ RegistroServiceTest.class, LoginServiceTest.class, AccionServiceTest.class, EmpresaServiceTest.class,
		FuncionalidadServiceTest.class, PersonaServiceTest.class, RolServiceTest.class, UsuarioServiceTest.class,
		NoticiaServiceTest.class, ObjetivoServiceTest.class, RespuestaPosibleServiceTest.class })
public class TestSuite {
}
