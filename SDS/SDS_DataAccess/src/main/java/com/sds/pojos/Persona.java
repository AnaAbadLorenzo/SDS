package com.sds.pojos;


import java.util.Date;

public class Persona {

	public String dniP;
	

	public String nombreP;
	
	
	public String apellidosP;
	
	
	public Date fechaNacP;
	
	
	public String direccionP;
	
	
	public String telefonoP;
	
	
	public String emailP;
	
	public Integer borrado_P;
	
	
	public String getDniP() {
		return dniP;
	}

	public void setDniP(String dniP) {
		this.dniP = dniP;
	}

	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}

	public String getApellidosP() {
		return apellidosP;
	}

	public void setApellidosP(String apellidosP) {
		this.apellidosP = apellidosP;
	}

	public Date getFechaNacP() {
		return fechaNacP;
	}

	public void setFechaNacP(Date fechaNacP) {
		this.fechaNacP = fechaNacP;
	}

	public String getDireccionP() {
		return direccionP;
	}

	public void setDireccionP(String direccionP) {
		this.direccionP = direccionP;
	}

	public String getTelefonoP() {
		return telefonoP;
	}

	public void setTelefonoP(String telefonoP) {
		this.telefonoP = telefonoP;
	}

	public String getEmailP() {
		return emailP;
	}

	public void setEmailP(String emailP) {
		this.emailP = emailP;
	}

	public Integer getBorrado_P() {
		return borrado_P;
	}

	public void setBorrado_P(Integer borrado_P) {
		this.borrado_P = borrado_P;
	}

	@Override
	public String toString() {
		return "Persona [dniP=" + dniP + 
				", nombreP=" + nombreP + 
				", apellidosP=" + apellidosP + 
				", fechaNacP=" + fechaNacP + 
				", direccionP=" + direccionP + 
				", telefonoP=" + telefonoP + 
				", emailP=" + emailP + 
				", borrado_P=" + borrado_P + "]";
	}


}

