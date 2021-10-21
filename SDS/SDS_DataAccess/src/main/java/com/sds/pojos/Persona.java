package com.sds.pojos;

import java.util.Date;

public class Persona extends GenericPojo {

	private static final long serialVersionUID = 1L;

	public String dniP;
	public String nombreP;
	public String apellidosP;
	public Date fechaNacP;
	public String direccionP;
	public String telefonoP;
	public String emailP;
	public Integer borrado_P;
	public Empresa empresa;

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

	public Integer getBorrado_P() {
		return borrado_P;
	}

	public void setBorrado_P(final Integer borrado_P) {
		this.borrado_P = borrado_P;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(final Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Persona [dniP=" + dniP + ", nombreP=" + nombreP + ", apellidosP=" + apellidosP + ", fechaNacP="
				+ fechaNacP + ", direccionP=" + direccionP + ", telefonoP=" + telefonoP + ", emailP=" + emailP
				+ ", borrado_P=" + borrado_P + ", empresa=" + empresa + "]";
	}

}
