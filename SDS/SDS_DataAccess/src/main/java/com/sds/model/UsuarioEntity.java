package com.sds.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@NamedQueries({
		@NamedQuery(name = "UsuarioEntity.findByUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.usuario =: usuario and u.borradoUsuario = 0"),
		@NamedQuery(name = "UsuarioEntity.findRolUsuario", query = "SELECT u.rol FROM UsuarioEntity u WHERE u.usuario =: usuario"),
		@NamedQuery(name = "UsuarioEntity.findUsuariosEliminados", query = "SELECT u FROM UsuarioEntity u WHERE u.borradoUsuario = 1"),
		@NamedQuery(name = "UsuarioEntity.numberFindUsuariosEliminados", query = "SELECT COUNT(u) FROM UsuarioEntity u WHERE u.borradoUsuario = 1"),
		@NamedQuery(name = "UsuarioEntity.findByDni", query = "SELECT u.dniUsuario FROM UsuarioEntity u WHERE u.dniUsuario =: dniUsuario"),
		@NamedQuery(name = "UsuarioEntity.findUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.dniUsuario LIKE CONCAT('%', :dniUsuario, '%') AND u.usuario LIKE CONCAT('%', :usuario, '%') AND u.rol LIKE CONCAT('%', :rol, '%') AND u.borradoUsuario = 0"),
		@NamedQuery(name = "UsuarioEntity.numberFindUsuarioWithRol", query = "SELECT COUNT(u) FROM UsuarioEntity u WHERE u.dniUsuario LIKE CONCAT('%', :dniUsuario, '%') AND u.usuario LIKE CONCAT('%', :usuario, '%') AND u.rol LIKE CONCAT('%', :rol, '%') AND u.borradoUsuario = 0"),
		@NamedQuery(name = "UsuarioEntity.numberFindUsuario", query = "SELECT COUNT(u) FROM UsuarioEntity u WHERE u.dniUsuario LIKE CONCAT('%', :dniUsuario, '%') AND u.usuario LIKE CONCAT('%', :usuario, '%') AND u.borradoUsuario = 0"),
		@NamedQuery(name = "UsuarioEntity.findAllUsuarios", query = "SELECT u FROM UsuarioEntity u WHERE u.borradoUsuario = 0"),
		@NamedQuery(name = "UsuarioEntity.numberFindAllUsuarios", query = "SELECT COUNT(u) FROM UsuarioEntity u WHERE u.borradoUsuario = 0"), })
public class UsuarioEntity {

	@Id
	@Column(name = "dni_usuario")
	private String dniUsuario;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "passwd_usuario")
	private String passwdUsuario;

	@Column(name = "borrado_usuario")
	private Integer borradoUsuario;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
	private RolEntity rol;

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private PersonaEntity persona;

	@OneToMany(mappedBy = "usuario")
	private final Set<ProcedimientoUsuarioEntity> procedimientoUsuario = new HashSet<>();

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

	public UsuarioEntity(final String dniUsuario, final String usuario, final String passwdUsuario,
			final Integer borradoUsuario, final RolEntity rol) {
		super();
		this.dniUsuario = dniUsuario;
		this.usuario = usuario;
		this.passwdUsuario = passwdUsuario;
		this.borradoUsuario = borradoUsuario;
		this.rol = rol;
	}

	public UsuarioEntity(final String dniUsuario, final String usuario, final Integer borradoUsuario) {
		super();
		this.dniUsuario = dniUsuario;
		this.usuario = usuario;
		this.borradoUsuario = borradoUsuario;

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

	public Set<ProcedimientoUsuarioEntity> getProcedimientoUsuario() {
		return procedimientoUsuario;
	}

	@Override
	public String toString() {
		return "UsuarioEntity [dniUsuario=" + dniUsuario + ", usuario=" + usuario + ", passwdUsuario=" + passwdUsuario
				+ ", borradoUsuario=" + borradoUsuario + ", rol=" + rol + ", persona=" + persona + "]";
	}

}
