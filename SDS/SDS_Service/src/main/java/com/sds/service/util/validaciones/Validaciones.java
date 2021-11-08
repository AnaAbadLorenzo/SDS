package com.sds.service.util.validaciones;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.login.model.Login;

public class Validaciones {

	public boolean comprobarLoginBlank(final Login login) {
		if (StringUtils.isBlank(login.getUsuario()) || StringUtils.isBlank(login.getPasswdUsuario())) {
			return false;
		}
		return true;
	}

}
