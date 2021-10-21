package com.sds.pojos;

public class Usuario extends GenericPojo {

	private static final long serialVersionUID = 1L;

	public Integer idUsuario;
	public String dniUsuario;
	public String usuario;
	public String passwdUsuario;
	public int borradoUsuario;
	private Rol rol;
	private Persona persona;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(final Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(final String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getPasswdUsuario() {
		return passwdUsuario;
	}

	public void setPasswdUsuario(final String passwdUsuario) {
		this.passwdUsuario = passwdUsuario;
	}

	public int getBorradoUsuario() {
		return borradoUsuario;
	}

	public void setBorradoUsuario(final int borradoUsuario) {
		this.borradoUsuario = borradoUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(final Rol rol) {
		this.rol = rol;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(final Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", dniUsuario=" + dniUsuario + ", usuario=" + usuario
				+ ", passwdUsuario=" + passwdUsuario + ", borradoUsuario=" + borradoUsuario + ", rol=" + rol
				+ ", persona=" + persona + "]";
	}

}