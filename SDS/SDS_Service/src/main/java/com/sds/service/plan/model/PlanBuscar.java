package com.sds.service.plan.model;

import java.util.Date;

import com.sds.model.ObjetivoEntity;

public class PlanBuscar {

	private String nombrePlan;
	private String descripPlan;
	private Date fechaPlan;
	private ObjetivoEntity objetivo;
	private int inicio;
	private int tamanhoPagina;

	public PlanBuscar() {
		super();
	}

	public PlanBuscar(final String nombrePlan, final String descripPlan, final Date fechaPlan,
			final ObjetivoEntity objetivo, final int inicio, final int tamanhoPagina) {
		super();
		this.nombrePlan = nombrePlan;
		this.descripPlan = descripPlan;
		this.fechaPlan = fechaPlan;
		this.objetivo = objetivo;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
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

	public ObjetivoEntity getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(final ObjetivoEntity objetivo) {
		this.objetivo = objetivo;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(final int inicio) {
		this.inicio = inicio;
	}

	public int getTamanhoPagina() {
		return tamanhoPagina;
	}

	public void setTamanhoPagina(final int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	@Override
	public String toString() {
		return "PlanBuscar [nombrePlan=" + nombrePlan + ", descripPlan=" + descripPlan + ", fechaPlan=" + fechaPlan
				+ ", inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
