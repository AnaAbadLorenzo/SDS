package com.sds.service.acl.model;

import java.util.ArrayList;
import java.util.List;

import com.sds.model.FuncionalidadEntity;

public class Menu {

	public List<String> funcionalidades;
	public String usuario;
	
	public Menu() {
		this.funcionalidades = new ArrayList<String>();
	}
	
	public Menu(List<String> funcionalidades, String usuario) {
		this.funcionalidades = funcionalidades;
		this.usuario =usuario;
	}

	public List<String> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<String> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
