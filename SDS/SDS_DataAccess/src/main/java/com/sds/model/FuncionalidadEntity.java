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
import javax.persistence.Table;

@Entity
@Table(name = "funcionalidad")
public class FuncionalidadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcionalidad")
	private Integer idFuncionalidad;

	@Column(name = "nombre_funcionalidad")
	private String nombreFuncionalidad;

	@Column(name = "descrip_funcionalidad")
	private String descripFuncionalidad;

	@Column(name = "borrado_funcionalidad")
	private Integer borradoFuncionalidad;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "rolaccionfuncionalidad", joinColumns = {
			@JoinColumn(name = "id_funcionalidad") }, inverseJoinColumns = { @JoinColumn(name = "id_accion") })
	private final Set<AccionEntity> acciones = new HashSet();

	@ManyToMany(mappedBy = "funcionalidades")
	private final Set<RolEntity> roles = new HashSet();

	public FuncionalidadEntity() {
		super();
	}

	public FuncionalidadEntity(final Integer idFuncionalidad, final String nombreFuncionalidad,
			final String descripFuncionalidad, final Integer borradoFuncionalidad) {
		super();
		this.idFuncionalidad = idFuncionalidad;
		this.nombreFuncionalidad = nombreFuncionalidad;
		this.descripFuncionalidad = descripFuncionalidad;
		this.borradoFuncionalidad = borradoFuncionalidad;
	}

	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(final Integer idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

	public String getNombreFuncionalidad() {
		return nombreFuncionalidad;
	}

	public void setNombreFuncionalidad(final String nombreFuncionalidad) {
		this.nombreFuncionalidad = nombreFuncionalidad;
	}

	public String getDescripFuncionalidad() {
		return descripFuncionalidad;
	}

	public void setDescripFuncionalidad(final String descripFuncionalidad) {
		this.descripFuncionalidad = descripFuncionalidad;
	}

	public Integer getBorradoFuncionalidad() {
		return borradoFuncionalidad;
	}

	public void setBorradoFuncionalidad(final Integer borradoFuncionalidad) {
		this.borradoFuncionalidad = borradoFuncionalidad;
	}

	public Set<AccionEntity> getAcciones() {
		return acciones;
	}

	public Set<RolEntity> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "FuncionalidadEntity [idFuncionalidad=" + idFuncionalidad + ", nombreFuncionalidad="
				+ nombreFuncionalidad + ", descripFuncionalidad=" + descripFuncionalidad + ", borradoFuncionalidad="
				+ borradoFuncionalidad + "]";
	}

}
