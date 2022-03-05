package com.sds.service.rol.model;

public class RolBuscar {

	private String rolName;
	private String rolDescription;
	private int inicio;
	private int tamanhoPagina;

	public RolBuscar() {

	}

	public RolBuscar(final String rolName, final String rolDescription, final int inicio, final int tamanhoPagina) {
		super();
		this.rolName = rolName;
		this.rolDescription = rolDescription;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
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

	public int getTamanhoPagina() {
		return tamanhoPagina;
	}

	public void setTamanhoPagina(final int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	@Override
	public String toString() {
		return "RolBuscar [rolName=" + rolName + ", rolDescription=" + rolDescription + ", inicio=" + inicio
				+ ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
