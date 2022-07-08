package com.sds.service.procesoprocedimiento.model;

public class ProcesoProcedimientoPaginacion {

	private Integer idProcedimiento;
	private int inicio;
	private int tamanhoPagina;

	public ProcesoProcedimientoPaginacion() {
		super();
	}

	public ProcesoProcedimientoPaginacion(final Integer idProcedimiento, final int inicio, final int tamanhoPagina) {
		super();
		this.idProcedimiento = idProcedimiento;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public Integer getIdProcedimiento() {
		return idProcedimiento;
	}

	public void setIdProcedimiento(final Integer idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
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

	public void setTamanhoPaginacion(final int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	@Override
	public String toString() {
		return "ProcesoProcedimientoPaginacion [idProcedimiento=" + idProcedimiento + ", inicio=" + inicio
				+ ", tamanhoPagina=" + tamanhoPagina + "]";
	}
}
