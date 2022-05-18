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
@Table(name = "objetivo")
@NamedQueries({
		@NamedQuery(name = "ObjetivoEntity.findIdObjetivoByName", query = "SELECT o.idObjetivo FROM ObjetivoEntity o WHERE o.nombreObjetivo =: nombreObjetivo"),
		@NamedQuery(name = "ObjetivoEntity.findObjetivoByName", query = "SELECT o FROM ObjetivoEntity o WHERE o.nombreObjetivo =: nombreObjetivo"),
		@NamedQuery(name = "ObjetivoEntity.findAllObjetivos", query = "SELECT o FROM ObjetivoEntity o WHERE o.borradoObjetivo = 0"),
		@NamedQuery(name = "ObjetivoEntity.numberFindAllObjetivos", query = "SELECT COUNT(o) FROM ObjetivoEntity o WHERE o.borradoObjetivo = 0"),
		@NamedQuery(name = "ObjetivoEntity.findObjetivo", query = "SELECT o FROM ObjetivoEntity o WHERE o.nombreObjetivo LIKE CONCAT('%', :nombreObjetivo, '%') AND o.descripObjetivo LIKE CONCAT('%', :descripObjetivo, '%') AND o.borradoObjetivo=0"),
		@NamedQuery(name = "ObjetivoEntity.numberFindObjetivo", query = "SELECT COUNT(o) FROM ObjetivoEntity o WHERE o.nombreObjetivo LIKE CONCAT('%', :nombreObjetivo, '%') AND o.descripObjetivo LIKE CONCAT('%', :descripObjetivo, '%') AND o.borradoObjetivo=0"),
		@NamedQuery(name = "ObjetivoEntity.findObjetivosEliminados", query = "SELECT o FROM ObjetivoEntity o WHERE o.borradoObjetivo = 1"),
		@NamedQuery(name = "ObjetivoEntity.numberFindObjetivosEliminados", query = "SELECT COUNT(o) FROM ObjetivoEntity o WHERE o.borradoObjetivo = 1") })
public class ObjetivoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_objetivo")
	private Integer idObjetivo;

	@Column(name = "nombre_objetivo")
	private String nombreObjetivo;

	@Column(name = "descripcion_objetivo")
	private String descripObjetivo;

	@Column(name = "borrado_objetivo")
	private Integer borradoObjetivo;

	@OneToMany(mappedBy = "objetivo")
	private final Set<PlanEntity> planes = new HashSet<>();

	public ObjetivoEntity() {
		super();
	}

	public ObjetivoEntity(final Integer idObjetivo, final String nombreObjetivo, final String descripObjetivo,
			final Integer borradoObjetivo) {
		super();
		this.idObjetivo = idObjetivo;
		this.nombreObjetivo = nombreObjetivo;
		this.descripObjetivo = descripObjetivo;
		this.borradoObjetivo = borradoObjetivo;
	}

	public Integer getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(final Integer idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public String getNombreObjetivo() {
		return nombreObjetivo;
	}

	public void setNombreObjetivo(final String nombreObjetivo) {
		this.nombreObjetivo = nombreObjetivo;
	}

	public String getDescripObjetivo() {
		return descripObjetivo;
	}

	public void setDescripObjetivo(final String descripObjetivo) {
		this.descripObjetivo = descripObjetivo;
	}

	public Integer getBorradoObjetivo() {
		return borradoObjetivo;
	}

	public void setBorradoObjetivo(final Integer borradoObjetivo) {
		this.borradoObjetivo = borradoObjetivo;
	}

	public Set<PlanEntity> getPlanes() {
		return planes;
	}

	@Override
	public String toString() {
		return "ObjetivoEntity [idObjetivo=" + idObjetivo + ", nombreObjetivo=" + nombreObjetivo + ", descripObjetivo="
				+ descripObjetivo + ", borradoObjetivo=" + borradoObjetivo + "]";
	}

}
