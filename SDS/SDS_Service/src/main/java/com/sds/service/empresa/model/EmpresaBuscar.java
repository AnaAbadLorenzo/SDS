package com.sds.service.empresa.model;

public class EmpresaBuscar {
	private String cifEmpresa;
	private String nombreEmpresa;
	private String direccionEmpresa;
	private String telefonoEmpresa;

	public EmpresaBuscar(final String cifEmpresa, final String nombreEmpresa, final String direccionEmpresa,
			final String telefonoEmpresa) {
		super();
		this.cifEmpresa = cifEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.direccionEmpresa = direccionEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public String getCifEmpresa() {
		return cifEmpresa;
	}

	public void setCifEmpresa(final String cifEmpresa) {
		this.cifEmpresa = cifEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(final String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}

	public void setDireccionEmpresa(final String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(final String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

}
