package com.sds.loginService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginServiceTest {

	@Autowired
	private LoginService loginService;

	@Test
	public void LoginService_loginUserOK() {

		final Login login = new Login();
		login.setPasswdUsuario("1");
		login.setUsuario("usuario");

		// TODO Con lo que nos devuelva la respuesta debemos validar que todo ha ido ok
		loginService.loginUser(login);
	}
}
