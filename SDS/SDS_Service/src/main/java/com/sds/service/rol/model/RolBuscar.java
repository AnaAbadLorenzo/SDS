package com.sds.service.rol.model;

public class RolBuscar {

	private String rolName;
	private String rolDescription;
	private int inicio;
	private int tamanoPagina;

	public RolBuscar() {

	}

	public RolBuscar(final String rolName, final String rolDescription, final int inicio, final int tamanoPagina) {
		super();
		this.rolName = rolName;
		this.rolDescription = rolDescription;
		this.inicio = inicio;
		this.tamanoPagina = tamanoPagina;
	}

	public RolBuscar(final String rolName, final String rolDescription) {
		this.rolName = rolName;
		this.rolDescription = rolDescription;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(final String rolName) {
		this.rolName = rolName;
	}

	public String getRolDescription() {
		return rolDescription;
	}

	public void setRolDescription(final String rolDescription) {
		this.rolDescription = rolDescription;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(final int inicio) {
		this.inicio = inicio;
	}

	public int getTamanoPagina() {
		return tamanoPagina;
	}

	public void setTamanoPagina(final int tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
	}

}
