package com.sds.service.procedimiento.model;

import java.util.Date;

import com.sds.model.PlanEntity;

public class ProcedimientoBuscar {

	private String nombreProcedimiento;
	private String descripProcedimiento;
	private Date fechaProcedimiento;
	private Boolean checkUsuario;
	private PlanEntity plan;
	private int inicio;
	private int tamanhoPagina;

	public ProcedimientoBuscar() {
		super();
	}

	public ProcedimientoBuscar(final String nombreProcedimiento, final String descripProcedimiento,
			final Date fechaProcedimiento, final Boolean checkUsuario, final PlanEntity plan, final int inicio,
			final int tamanhoPagina) {
		super();
		this.nombreProcedimiento = nombreProcedimiento;
		this.descripProcedimiento = descripProcedimiento;
		this.fechaProcedimiento = fechaProcedimiento;
		this.checkUsuario = checkUsuario;
		this.plan = plan;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public String getNombreProcedimiento() {
		return nombreProcedimiento;
	}

	public void setNombreProcedimiento(final String nombreProcedimiento) {
		this.nombreProcedimiento = nombreProcedimiento;
	}

	public String getDescripProcedimiento() {
		return descripProcedimiento;
	}

	public void setDescripProcedimiento(final String descripProcedimiento) {
		this.descripProcedimiento = descripProcedimiento;
	}

	public Date getFechaProcedimiento() {
		return fechaProcedimiento;
	}

	public void setFechaProcedimiento(final Date fechaProcedimiento) {
		this.fechaProcedimiento = fechaProcedimiento;
	}

	public Boolean getCheckUsuario() {
		return checkUsuario;
	}

	public void setCheckUsuario(final Boolean checkUsuario) {
		this.checkUsuario = checkUsuario;
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
		return "ProcedimientoBuscar [nombreProcedimiento=" + nombreProcedimiento + ", descripProcedimiento="
				+ descripProcedimiento + ", fechaProcedimiento=" + fechaProcedimiento + ", checkUsuario=" + checkUsuario
				+ ", plan=" + plan + ", inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
