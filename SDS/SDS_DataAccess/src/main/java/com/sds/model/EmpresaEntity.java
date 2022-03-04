package com.sds.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
@NamedQueries({ @NamedQuery(name = "EmpresaEntity.findAllEmpresas", query = "SELECT e FROM EmpresaEntity e"),
		@NamedQuery(name = "EmpresaEntity.numberFindAllEmpresas", query = "SELECT COUNT(e) FROM EmpresaEntity e"),
		@NamedQuery(name = "EmpresaEntity.findEmpresa", query = "SELECT e FROM EmpresaEntity e WHERE e.cifEmpresa LIKE CONCAT('%', :cifEmpresa, '%') AND e.nombreEmpresa LIKE CONCAT('%', :nombreEmpresa, '%') AND e.direccionEmpresa LIKE CONCAT('%', :direccionEmpresa, '%') AND e.telefonoEmpresa LIKE CONCAT('%',:telefonoEmpresa, '%') AND e.borradoEmpresa=0"),
		@NamedQuery(name = "EmpresaEntity.numberFindEmpresa", query = "SELECT COUNT(e) FROM EmpresaEntity e WHERE e.cifEmpresa LIKE CONCAT('%', :cifEmpresa, '%') AND e.nombreEmpresa LIKE CONCAT('%', :nombreEmpresa, '%') AND e.direccionEmpresa LIKE CONCAT('%', :direccionEmpresa, '%') AND e.telefonoEmpresa LIKE CONCAT('%',:telefonoEmpresa, '%') AND e.borradoEmpresa=0"),
		@NamedQuery(name = "EmpresaEntity.findByCif", query = "SELECT e FROM EmpresaEntity e WHERE e.cifEmpresa =: cifEmpresa"),
		@NamedQuery(name = "EmpresaEntity.findEmpresasEliminadas", query = "SELECT e FROM EmpresaEntity e WHERE e.borradoEmpresa = 1"),
		@NamedQuery(name = "EmpresaEntity.numberFindEmpresasEliminadas", query = "SELECT COUNT(e) FROM EmpresaEntity e WHERE e.borradoEmpresa = 1") })
public class EmpresaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa")
	private Integer idEmpresa;

	@Column(name = "cif_empresa")
	private String cifEmpresa;

	@Column(name = "nombre_empresa")
	private String nombreEmpresa;

	@Column(name = "direccion_empresa")
	private String direccionEmpresa;

	@Column(name = "telefono_empresa")
	private String telefonoEmpresa;

	@Column(name = "borrado_empresa")
	private Integer borradoEmpresa;

	@OneToMany(mappedBy = "empresa")
	private final Set<PersonaEntity> personas = new HashSet();

	public EmpresaEntity() {
		super();
	}

	public EmpresaEntity(final Integer idEmpresa, final String cifEmpresa, final String nombreEmpresa,
			final String direccionEmpresa, final String telefonoEmpresa, final Integer borradoEmpresa) {
		super();
		this.idEmpresa = idEmpresa;
		this.cifEmpresa = cifEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.direccionEmpresa = direccionEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
		this.borradoEmpresa = borradoEmpresa;
	}

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

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(final String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public Integer getBorradoEmpresa() {
		return borradoEmpresa;
	}

	public void setBorradoEmpresa(final Integer borradoEmpresa) {
		this.borradoEmpresa = borradoEmpresa;
	}

	public Set<PersonaEntity> getPersonas() {
		return personas;
	}

	@Override
	public String toString() {
		return "EmpresaEntity [idEmpresa=" + idEmpresa + ", cifEmpresa=" + cifEmpresa + ", nombreEmpresa="
				+ nombreEmpresa + ", direccionEmpresa=" + direccionEmpresa + ", telefonoEmpresa=" + telefonoEmpresa
				+ ", borradoEmpresa=" + borradoEmpresa + "]";
	}

}
