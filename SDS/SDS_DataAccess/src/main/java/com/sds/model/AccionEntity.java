package com.sds.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "accion")
@NamedQueries({
		@NamedQuery(name = "AccionEntity.findNombreAccionById", query = "SELECT a.nombreAccion FROM AccionEntity a WHERE a.idAccion =: idAccion"),
		@NamedQuery(name = "AccionEntity.findAccionByName", query = "SELECT a FROM AccionEntity a WHERE a.nombreAccion =: nombreAccion"),
		@NamedQuery(name = "AccionEntity.findAccion", query = "SELECT a FROM AccionEntity a WHERE a.nombreAccion LIKE CONCAT('%', :nombreAccion, '%') AND a.descripAccion LIKE CONCAT('%',:descripAccion, '%')"),
		@NamedQuery(name = "AccionEntity.findAccionesEliminadas", query = "SELECT a FROM AccionEntity a WHERE a.borradoAccion =: borradoAccion") })
public class AccionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_accion")
	private Integer idAccion;

	@Column(name = "nombre_accion")
	private String nombreAccion;

	@Column(name = "descrip_accion")
	private String descripAccion;

	@Column(name = "borrado_accion")
	private Integer borradoAccion;

	@ManyToMany(mappedBy = "acciones")
	private final Set<RolEntity> roles = new HashSet();

	@ManyToMany(mappedBy = "acciones")
	private final Set<FuncionalidadEntity> funcionalidades = new HashSet();

	public AccionEntity() {
		super();
	}

	public AccionEntity(final Integer idAccion, final String nombreAccion, final String descripAccion,
			final Integer borradoAccion) {
		super();
		this.idAccion = idAccion;
		this.nombreAccion = nombreAccion;
		this.descripAccion = descripAccion;
		this.borradoAccion = borradoAccion;
	}

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(final Integer idAccion) {
		this.idAccion = idAccion;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(final String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getDescripAccion() {
		return descripAccion;
	}

	public void setDescripAccion(final String descripAccion) {
		this.descripAccion = descripAccion;
	}

	public Integer getBorradoAccion() {
		return borradoAccion;
	}

	public void setBorradoAccion(final Integer borradoAccion) {
		this.borradoAccion = borradoAccion;
	}

	public Set<RolEntity> getRoles() {
		return roles;
	}

	public Set<FuncionalidadEntity> getFuncionalidades() {
		return funcionalidades;
	}

	@Override
	public String toString() {
		return "AccionEntity [idAccion=" + idAccion + ", nombreAccion=" + nombreAccion + ", descripAccion="
				+ descripAccion + ", borradoAccion=" + borradoAccion + "]";
	}

}
