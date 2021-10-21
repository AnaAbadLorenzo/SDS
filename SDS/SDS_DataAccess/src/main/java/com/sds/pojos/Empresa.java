package com.sds.pojos;

public class Empresa extends GenericPojo {

	private static final long serialVersionUID = 1L;

	public Integer idEmpresa;
	public String cifEmpresa;
	public String nombreEmpresa;
	public String direccionEmpresa;
	public String telefonoEmpresa;
	public String borradoEmpresa;

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(final Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
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

	public String getBorradoEmpresa() {
		return borradoEmpresa;
	}

	public void setBorradoEmpresa(final String borradoEmpresa) {
		this.borradoEmpresa = borradoEmpresa;
	}

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(final String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", cifEmpresa=" + cifEmpresa + ", nombreEmpresa=" + nombreEmpresa
				+ ", direccionEmpresa=" + direccionEmpresa + ", telefonoEmpresa=" + telefonoEmpresa
				+ ", borradoEmpresa=" + borradoEmpresa + "]";
	}

}
