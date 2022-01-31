package com.sds.service.persona.model;

import java.util.Date;

import com.sds.model.EmpresaEntity;

public class PersonaBuscar {
	private String dnip;
	private String nombreP;
	private String apellidosP;
	private Date fechaNacP;
	private String direccionP;
	private String telefonoP;
	private String emailP;
	private EmpresaEntity empresaP;
	
	public PersonaBuscar() {
		
	}
	
	public PersonaBuscar(String dnip, String nombreP, String apellidosP, Date fechaNacP, String direccionP,
			String telefonoP, String emailP, EmpresaEntity empresaP) {
		super();
		this.dnip = dnip;
		this.nombreP = nombreP;
		this.apellidosP = apellidosP;
		this.fechaNacP = fechaNacP;
		this.direccionP = direccionP;
		this.telefonoP = telefonoP;
		this.emailP = emailP;
		this.empresaP = empresaP;
	}
	public String getDnip() {
		return dnip;
	}
	public void setDnip(String dnip) {
		this.dnip = dnip;
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
	public EmpresaEntity getEmpresaP() {
		return empresaP;
	}
	public void setEmpresaP(EmpresaEntity empresaP) {
		this.empresaP = empresaP;
	}
	
	@Override
	public String toString() {
		return "PersonaBuscar [dnip=" + dnip + ", nombreP=" + nombreP + ", apellidosP=" + apellidosP + ", fechaNacP="
				+ fechaNacP + ", direccionP=" + direccionP + ", telefonoP=" + telefonoP + ", emailP=" + emailP
				+ ", empresaP=" + empresaP + "]";
	}

}
