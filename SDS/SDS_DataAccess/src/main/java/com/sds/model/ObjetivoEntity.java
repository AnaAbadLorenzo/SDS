package com.sds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "objetivo")
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

	@OneToOne
	@JoinColumn(name = "id_plan")
	private PlanEntity plan;

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

	public PlanEntity getPlan() {
		return plan;
	}

	public void setPlan(final PlanEntity plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "ObjetivoEntity [idObjetivo=" + idObjetivo + ", nombreObjetivo=" + nombreObjetivo + ", descripObjetivo="
				+ descripObjetivo + ", borradoObjetivo=" + borradoObjetivo + "]";
	}

}
