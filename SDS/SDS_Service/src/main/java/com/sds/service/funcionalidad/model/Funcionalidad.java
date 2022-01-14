package com.sds.service.funcionalidad.model;

import com.sds.model.FuncionalidadEntity;

public class Funcionalidad {

	private String usuario;
	private FuncionalidadEntity funcionalidadEntity;

	public Funcionalidad() {

	}

	public Funcionalidad(final String usuario, final FuncionalidadEntity funcionalidadEntity) {
		this.usuario = usuario;
		this.funcionalidadEntity = funcionalidadEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public FuncionalidadEntity getFuncionalidadEntity() {
		return funcionalidadEntity;
	}

	public void setFuncionalidadEntity(final FuncionalidadEntity funcionalidadEntity) {
		this.funcionalidadEntity = funcionalidadEntity;
	}

	@Override
	public String toString() {
		return "Funcionalidad [usuario=" + usuario + ", funcionalidadEntity=" + funcionalidadEntity + "]";
	}

}
