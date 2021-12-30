package com.sds.service.empresa.model;

import com.sds.model.EmpresaEntity;

public class Empresa {

	public String usuario;
	public EmpresaEntity empresa;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public EmpresaEntity getEmpresa() {
		return empresa;
	}

	public void setEmpresa(final EmpresaEntity empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Empresa [usuario=" + usuario + ", empresa=" + empresa + "]";
	}

}
