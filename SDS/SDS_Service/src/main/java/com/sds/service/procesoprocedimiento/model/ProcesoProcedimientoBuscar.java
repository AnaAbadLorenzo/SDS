package com.sds.service.procesoprocedimiento.model;

public class ProcesoProcedimientoBuscar {

	private Integer idProceso;
	private Integer idProcedimiento;

	public ProcesoProcedimientoBuscar() {
		super();
	}

	public ProcesoProcedimientoBuscar(final Integer idProceso, final Integer idProcedimiento) {
		super();
		this.idProceso = idProceso;
		this.idProcedimiento = idProcedimiento;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getIdProcedimiento() {
		return idProcedimiento;
	}

	public void setIdProcedimiento(final Integer idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
	}

	@Override
	public String toString() {
		return "ProcesoProcedimientoBuscar [idProceso=" + idProceso + ", idProcedimiento=" + idProcedimiento + "]";
	}

}
