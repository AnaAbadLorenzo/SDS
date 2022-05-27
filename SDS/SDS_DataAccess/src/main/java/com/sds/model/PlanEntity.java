package com.sds.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "plan")
@NamedQueries({
		@NamedQuery(name = "PlanEntity.findIdPlanByName", query = "SELECT p.idPlan FROM PlanEntity p WHERE p.nombrePlan =: nombrePlan"),
		@NamedQuery(name = "PlanEntity.findPlanByName", query = "SELECT p FROM PlanEntity p WHERE p.nombrePlan =: nombrePlan"),
		@NamedQuery(name = "PlanEntity.findAllPlanes", query = "SELECT p FROM PlanEntity p WHERE p.borradoPlan = 0"),
		@NamedQuery(name = "PlanEntity.numberFindAllPlanes", query = "SELECT COUNT(p) FROM PlanEntity p WHERE p.borradoPlan = 0"),
		@NamedQuery(name = "PlanEntity.findPlan", query = "SELECT p FROM PlanEntity p WHERE p.nombrePlan LIKE CONCAT('%', :nombrePlan, '%') AND p.descripPlan LIKE CONCAT('%', :descripPlan, '%') AND p.fechaPlan LIKE CONCAT ('%', :fechaPlan, '%') AND p.borradoPlan=0"),
		@NamedQuery(name = "PlanEntity.numberFindPlan", query = "SELECT COUNT(p) FROM PlanEntity p WHERE p.nombrePlan LIKE CONCAT('%', :nombrePlan, '%') AND p.descripPlan LIKE CONCAT('%', :descripPlan, '%') AND p.fechaPlan LIKE CONCAT ('%', :fechaPlan, '%') AND p.borradoPlan=0"),
		@NamedQuery(name = "PlanEntity.findPlanesEliminados", query = "SELECT p FROM PlanEntity p WHERE p.borradoPlan = 1"),
		@NamedQuery(name = "PlanEntity.numberFindPlanesEliminados", query = "SELECT COUNT(p) FROM PlanEntity p WHERE p.borradoPlan = 1 ") })
public class PlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plan")
	private Integer idPlan;

	@Column(name = "nombre_plan")
	private String nombrePlan;

	@Column(name = "descrip_plan")
	private String descripPlan;

	@Column(name = "fecha_plan")
	private Date fechaPlan;

	@Column(name = "borrado_plan")
	private Integer borradoPlan;

	@ManyToOne
	@JoinColumn(name = "id_objetivo", referencedColumnName = "id_objetivo", updatable = false)
	private ObjetivoEntity objetivo;

	@OneToMany(mappedBy = "plan")
	private final Set<ProcedimientoEntity> procedimientos = new HashSet<>();

	public PlanEntity() {
		super();
	}

	public PlanEntity(final String nombrePlan, final String descripPlan, final Date fechaPlan,
			final Integer borradoPlan) {
		super();
		this.nombrePlan = nombrePlan;
		this.descripPlan = descripPlan;
		this.fechaPlan = fechaPlan;
		this.borradoPlan = borradoPlan;
	}

	public PlanEntity(final Integer idPlan, final String nombrePlan, final String descripPlan, final Date fechaPlan,
			final Integer borradoPlan, final ObjetivoEntity objetivo) {
		super();
		this.idPlan = idPlan;
		this.nombrePlan = nombrePlan;
		this.descripPlan = descripPlan;
		this.fechaPlan = fechaPlan;
		this.borradoPlan = borradoPlan;
		this.objetivo = objetivo;
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

	public Set<ProcedimientoEntity> getProcedimientos() {
		return procedimientos;
	}

	@Override
	public String toString() {
		return "PlanEntity [idPlan=" + idPlan + ", nombrePlan=" + nombrePlan + ", descripPlan=" + descripPlan
				+ ", fechaPlan=" + fechaPlan + ", borradoPlan=" + borradoPlan + "]";
	}

}
