package com.sds.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class RolEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer idRol;

	@Column(name = "rol_name")
	private String rolName;

	@Column(name = "rol_description")
	private String rolDescription;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "rolaccionfuncionalidad", joinColumns = { @JoinColumn(name = "id_rol") }, inverseJoinColumns = {
			@JoinColumn(name = "id_accion") })
	private final Set<AccionEntity> acciones = new HashSet();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "rolaccionfuncionalidad", joinColumns = { @JoinColumn(name = "id_rol") }, inverseJoinColumns = {
			@JoinColumn(name = "id_funcionalidad") })
	private final Set<FuncionalidadEntity> funcionalidades = new HashSet();

	@OneToMany(mappedBy = "rol")
	private final Set<UsuarioEntity> usuarios = new HashSet();

	public RolEntity() {
		super();
	}

	public RolEntity(final Integer idRol, final String rolName, final String rolDescription) {
		super();
		this.idRol = idRol;
		this.rolName = rolName;
		this.rolDescription = rolDescription;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(final Integer idRol) {
		this.idRol = idRol;
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

	public Set<AccionEntity> getAcciones() {
		return acciones;
	}

	public Set<FuncionalidadEntity> getFuncionalidades() {
		return funcionalidades;
	}

	public Set<UsuarioEntity> getUsuarios() {
		return usuarios;
	}

	@Override
	public String toString() {
		return "RolEntity [idRol=" + idRol + ", rolName=" + rolName + ", rolDescription=" + rolDescription + "]";
	}

}
