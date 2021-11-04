package com.sds.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@NamedQueries({
		@NamedQuery(name = "UsuarioEntity.findByUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.usuario =: usuario") })
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dni_usuario")
	private String dniUsuario;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "passwd_usuario")
	private String passwdUsuario;

	@Column(name = "borrado_usuario")
	private Integer borradoUsuario;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
	private RolEntity rol;

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private PersonaEntity persona;

	public UsuarioEntity() {
		super();
	}

	public UsuarioEntity(final String dniUsuario, final String usuario, final String passwdUsuario,
			final Integer borradoUsuario, final RolEntity rol, final PersonaEntity persona) {
		super();
		this.dniUsuario = dniUsuario;
		this.usuario = usuario;
		this.passwdUsuario = passwdUsuario;
		this.borradoUsuario = borradoUsuario;
		this.rol = rol;
		this.persona = persona;
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

	public Integer getBorradoUsuario() {
		return borradoUsuario;
	}

	public void setBorradoUsuario(final Integer borradoUsuario) {
		this.borradoUsuario = borradoUsuario;
	}

	public RolEntity getRol() {
		return rol;
	}

	public void setRol(final RolEntity rol) {
		this.rol = rol;
	}

	public PersonaEntity getPersona() {
		return persona;
	}

	public void setPersona(final PersonaEntity persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "UsuarioEntity [dniUsuario=" + dniUsuario + ", usuario=" + usuario + ", passwdUsuario=" + passwdUsuario
				+ ", borradoUsuario=" + borradoUsuario + "]";
	}
}
