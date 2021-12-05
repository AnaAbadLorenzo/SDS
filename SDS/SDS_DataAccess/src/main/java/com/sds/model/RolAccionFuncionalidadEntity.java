package com.sds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sds.model.compositekey.RolAccionFuncionalidadKey;

@Entity
@IdClass(RolAccionFuncionalidadKey.class)
@Table(name = "rolaccionfuncionalidad")
@NamedQueries({
		@NamedQuery(name = "RolAccionFuncionalidadEntity.findFuncionalityByRolId", query = "SELECT r.idFuncionalidad FROM RolAccionFuncionalidadEntity r WHERE r.idRol =: idRol"),
		@NamedQuery(name = "RolAccionFuncionalidadEntity.findAccionByIdFuncionality", query = "SELECT r.idAccion FROM RolAccionFuncionalidadEntity r WHERE r.idFuncionalidad =: idFuncionalidad")})

public class RolAccionFuncionalidadEntity {
	@Id
	@Column(name = "id_rol")
	private Integer idRol;

	@Id
	@Column(name = "id_accion")
	private Integer idAccion;

	@Id
	@Column(name = "id_funcionalidad")
	private Integer idFuncionalidad;

	public RolAccionFuncionalidadEntity() {
		super();
	}

	public RolAccionFuncionalidadEntity(final Integer idRol, final Integer idAccion, final Integer idFuncionalidad) {
		super();
		this.idRol = idRol;
		this.idAccion = idAccion;
		this.idFuncionalidad = idFuncionalidad;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(Integer idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

	@Override
	public String toString() {
		return "RolAccionFuncionalidadEntity [idRol=" + idRol + ", idAccion=" + idAccion + ", idFuncionalidad="
				+ idFuncionalidad + "]";
	}

	
	
}