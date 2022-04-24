package com.sds.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plan")
public class PlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_objetivo")
	private Integer idPlan;

	@Column(name = "nombre_plan")
	private String nombrePlan;

	@Column(name = "descrip_plan")
	private String descripPlan;

	@Column(name = "fecha_plan")
	private Date fechaPlan;

	@Column(name = "borrado_plan")
	private Integer borradoPlan;

	@OneToOne(cascade = CascadeType.ALL)
	private ObjetivoEntity objetivo;

	public PlanEntity() {
		super();
	}

	public PlanEntity(final Integer idPlan, final String nombrePlan, final String descripPlan, final Date fechaPlan,
			final Integer borradoPlan) {
		super();
		this.idPlan = idPlan;
		this.nombrePlan = nombrePlan;
		this.descripPlan = descripPlan;
		this.fechaPlan = fechaPlan;
		this.borradoPlan = borradoPlan;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(final Integer idPlan) {
		this.idPlan = idPlan;
	}

	public String getNombrePlan() {
		return nombrePlan;
	}

	public void setNombrePlan(final String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}

	public String getDescripPlan() {
		return descripPlan;
	}

	public void setDescripPlan(final String descripPlan) {
		this.descripPlan = descripPlan;
	}

	public Date getFechaPlan() {
		return fechaPlan;
	}

	public void setFechaPlan(final Date fechaPlan) {
		this.fechaPlan = fechaPlan;
	}

	public Integer getBorradoPlan() {
		return borradoPlan;
	}

	public void setBorradoPlan(final Integer borradoPlan) {
		this.borradoPlan = borradoPlan;
	}

	public ObjetivoEntity getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(final ObjetivoEntity objetivo) {
		this.objetivo = objetivo;
	}

	@Override
	public String toString() {
		return "Plan [idPlan=" + idPlan + ", nombrePlan=" + nombrePlan + ", descripPlan=" + descripPlan + ", fechaPlan="
				+ fechaPlan + ", borradoPlan=" + borradoPlan + ", objetivo=" + objetivo + "]";
	}

}
