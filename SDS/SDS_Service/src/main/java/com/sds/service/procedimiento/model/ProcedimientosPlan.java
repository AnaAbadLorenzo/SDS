package com.sds.service.procedimiento.model;

import com.sds.model.PlanEntity;

public class ProcedimientosPlan {

	private String usuario;
	private PlanEntity plan;
	private int inicio;
	private int tamanhoPagina;

	public ProcedimientosPlan() {
		super();
	}

	public ProcedimientosPlan(final String usuario, final PlanEntity plan, final int inicio, final int tamanhoPagina) {
		super();
		this.usuario = usuario;
		this.plan = plan;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
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
		return "ProcedimientosPlan [usuario=" + usuario + ", plan=" + plan + ", inicio=" + inicio + ", tamanhoPagina="
				+ tamanhoPagina + "]";
	}

}
