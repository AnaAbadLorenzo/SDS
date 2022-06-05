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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "funcionalidad")
@NamedQueries({
		@NamedQuery(name = "FuncionalidadEntity.findByIdFuncionalidad", query = "SELECT f FROM FuncionalidadEntity f WHERE f.idFuncionalidad =: idFuncionalidad"),
		@NamedQuery(name = "FuncionalidadEntity.findIdFuncionalidadByName", query = "SELECT f.idFuncionalidad FROM FuncionalidadEntity f WHERE f.nombreFuncionalidad =: nombreFuncionalidad"),
		@NamedQuery(name = "FuncionalidadEntity.findFuncionalityByName", query = "SELECT f FROM FuncionalidadEntity f WHERE f.nombreFuncionalidad =: nombreFuncionalidad"),
		@NamedQuery(name = "FuncionalidadEntity.findAllFuncionalities", query = "SELECT f FROM FuncionalidadEntity f WHERE f.borradoFuncionalidad = 0"),
		@NamedQuery(name = "FuncionalidadEntity.numberFindAllFuncionalities", query = "SELECT COUNT(f) FROM FuncionalidadEntity f WHERE f.borradoFuncionalidad = 0"),
		@NamedQuery(name = "FuncionalidadEntity.findFuncionality", query = "SELECT f FROM FuncionalidadEntity f WHERE LOWER(f.nombreFuncionalidad) LIKE LOWER(CONCAT('%', :nombreFuncionalidad, '%')) AND LOWER(f.descripFuncionalidad) LIKE LOWER(CONCAT('%', :descripFuncionalidad, '%')) AND f.borradoFuncionalidad=0"),
		@NamedQuery(name = "FuncionalidadEntity.numberFindFuncionality", query = "SELECT COUNT(f) FROM FuncionalidadEntity f WHERE LOWER(f.nombreFuncionalidad) LIKE LOWER(CONCAT('%', :nombreFuncionalidad, '%')) AND LOWER(f.descripFuncionalidad) LIKE LOWER(CONCAT('%', :descripFuncionalidad, '%')) AND f.borradoFuncionalidad=0"),
		@NamedQuery(name = "FuncionalidadEntity.findFuncionalidadesEliminadas", query = "SELECT f FROM FuncionalidadEntity f WHERE f.borradoFuncionalidad = 1"),
		@NamedQuery(name = "FuncionalidadEntity.numberFindFuncionalidadesEliminadas", query = "SELECT COUNT(f) FROM FuncionalidadEntity f WHERE f.borradoFuncionalidad = 1") })
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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "rolaccionfuncionalidad", joinColumns = {
			@JoinColumn(name = "id_funcionalidad") }, inverseJoinColumns = { @JoinColumn(name = "id_accion") })
	private final Set<AccionEntity> acciones = new HashSet<>();

	@ManyToMany(mappedBy = "funcionalidades")
	private final Set<RolEntity> roles = new HashSet<>();

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

	public FuncionalidadEntity(final String nombreFuncionalidad, final String descripFuncionalidad,
			final Integer borradoFuncionalidad) {
		super();
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
