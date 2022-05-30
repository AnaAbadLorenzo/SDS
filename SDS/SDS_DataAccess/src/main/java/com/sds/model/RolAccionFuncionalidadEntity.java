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
		@NamedQuery(name = "RolAccionFuncionalidadEntity.findAccionByIdFuncionality", query = "SELECT r.idAccion FROM RolAccionFuncionalidadEntity r WHERE r.idFuncionalidad =: idFuncionalidad"),
		@NamedQuery(name = "RolAccionFuncionalidadEntity.findAccionByIdFuncionalityAndIdRol", query = "SELECT r.idAccion FROM RolAccionFuncionalidadEntity r WHERE r.idFuncionalidad =: idFuncionalidad AND r.idRol =: idRol"),
		@NamedQuery(name = "RolAccionFuncionalidadEntity.findByAccionId", query = "SELECT r FROM RolAccionFuncionalidadEntity r WHERE r.idAccion =: idAccion"),
		@NamedQuery(name = "RolAccionFuncionalidadEntity.findAllPermissions", query = "SELECT r FROM RolAccionFuncionalidadEntity r"),
		@NamedQuery(name = "RolAccionFuncionalidadEntity.numberFindAllPermissions", query = "SELECT COUNT(r) FROM RolAccionFuncionalidadEntity r"),
		@NamedQuery(name = "RolAccionFuncionalidadEntity.findByFuncionalityId", query = "SELECT r FROM RolAccionFuncionalidadEntity r WHERE r.idFuncionalidad =:idFuncionalidad") })
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

	public RolAccionFuncionalidadEntity(final Integer idAccion, final Integer idFuncionalidad, final Integer idRol) {
		super();
		this.idRol = idRol;
		this.idAccion = idAccion;
		this.idFuncionalidad = idFuncionalidad;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(final Integer idRol) {
		this.idRol = idRol;
	}

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(final Integer idAccion) {
		this.idAccion = idAccion;
	}

	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(final Integer idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

	@Override
	public String toString() {
		return "RolAccionFuncionalidadEntity [idRol=" + idRol + ", idAccion=" + idAccion + ", idFuncionalidad="
				+ idFuncionalidad + "]";
	}

}
