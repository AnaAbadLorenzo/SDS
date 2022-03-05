package com.sds.service.accion.model;

import com.sds.model.AccionEntity;

public class Accion {

	public String usuario;
	public AccionEntity accion;

	public Accion() {

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public AccionEntity getAccion() {
		return accion;
	}

	public void setAccion(final AccionEntity accion) {
		this.accion = accion;
	}

	@Override
	public String toString() {
		return "Accion [usuario=" + usuario + ", accion=" + accion + "]";
	}

}
