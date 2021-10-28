package com.sds.service.util;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.login.model.Login;

public class Util {

	public boolean comprobarLogin(final Login login) {
		if (StringUtils.isBlank(login.getUsuario()) || StringUtils.isBlank(login.getPasswdUsuario())) {
			return false;
		}
		return true;
	}

}
