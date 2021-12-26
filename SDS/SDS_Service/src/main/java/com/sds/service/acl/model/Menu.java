package com.sds.service.acl.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	public List<String> funcionalidades;
	public String usuario;

	public Menu() {
		this.funcionalidades = new ArrayList<String>();
	}

	public Menu(final List<String> funcionalidades, final String usuario) {
		this.funcionalidades = funcionalidades;
		this.usuario = usuario;
	}

	public List<String> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(final List<String> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Menu [funcionalidades=" + funcionalidades + ", usuario=" + usuario + "]";
	}
}
