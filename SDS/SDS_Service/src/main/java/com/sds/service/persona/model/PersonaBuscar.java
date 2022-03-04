package com.sds.service.persona.model;

import java.util.Date;

public class PersonaBuscar {
	private String dniP;
	private String nombreP;
	private String apellidosP;
	private Date fechaNacP;
	private String direccionP;
	private String telefonoP;
	private String emailP;
	private int inicio;
	private int tamanhoPagina;

	public PersonaBuscar() {

	}

	public PersonaBuscar(final String dniP, final String nombreP, final String apellidosP, final Date fechaNacP,
			final String direccionP, final String telefonoP, final String emailP, final int inicio,
			final int tamanhoPagina) {
		super();
		this.dniP = dniP;
		this.nombreP = nombreP;
		this.apellidosP = apellidosP;
		this.fechaNacP = fechaNacP;
		this.direccionP = direccionP;
		this.telefonoP = telefonoP;
		this.emailP = emailP;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getDniP() {
		return dniP;
	}

	public void setDniP(final String dniP) {
		this.dniP = dniP;
	}

	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(final String nombreP) {
		this.nombreP = nombreP;
	}

	public String getApellidosP() {
		return apellidosP;
	}

	public void setApellidosP(final String apellidosP) {
		this.apellidosP = apellidosP;
	}

	public Date getFechaNacP() {
		return fechaNacP;
	}

	public void setFechaNacP(final Date fechaNacP) {
		this.fechaNacP = fechaNacP;
	}

	public String getDireccionP() {
		return direccionP;
	}

	public void setDireccionP(final String direccionP) {
		this.direccionP = direccionP;
	}

	public String getTelefonoP() {
		return telefonoP;
	}

	public void setTelefonoP(final String telefonoP) {
		this.telefonoP = telefonoP;
	}

	public String getEmailP() {
		return emailP;
	}

	public void setEmailP(final String emailP) {
		this.emailP = emailP;
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
		return "PersonaBuscar [dnip=" + dniP + ", nombreP=" + nombreP + ", apellidosP=" + apellidosP + ", fechaNacP="
				+ fechaNacP + ", direccionP=" + direccionP + ", telefonoP=" + telefonoP + ", emailP=" + emailP + "]";
	}

}
