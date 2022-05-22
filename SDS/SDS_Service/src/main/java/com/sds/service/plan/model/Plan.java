package com.sds.service.plan.model;

import com.sds.model.PlanEntity;

public class Plan {

	private String usuario;
	private PlanEntity plan;

	public Plan() {
		super();
	}

	public Plan(final String usuario, final PlanEntity plan) {
		super();
		this.usuario = usuario;
		this.plan = plan;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public PlanEntity getPlan() {
		return plan;
	}

	public void setPlan(final PlanEntity plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "Plan [usuario=" + usuario + ", plan=" + plan + "]";
	}

}
