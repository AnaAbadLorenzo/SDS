package com.sds.service.accion.model;

public class AccionBuscar {

	public String nombreAccion;
	public String descripAccion;

	public AccionBuscar() {

	}

	public AccionBuscar(final String nombreAccion, final String descripAccion) {
		super();
		this.nombreAccion = nombreAccion;
		this.descripAccion = descripAccion;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(final String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getDescripAccion() {
		return descripAccion;
	}

	public void setDescripAccion(final String descripAccion) {
		this.descripAccion = descripAccion;
	}

}
