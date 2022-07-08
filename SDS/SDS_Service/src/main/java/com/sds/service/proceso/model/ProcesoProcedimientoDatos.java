package com.sds.service.proceso.model;

public class ProcesoProcedimientoDatos {

	private Integer idProceso;
	private Integer idProcedimiento;

	public ProcesoProcedimientoDatos() {
		super();

	}

	public ProcesoProcedimientoDatos(final Integer idProceso, final Integer idProcedimiento) {
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
		return "ProcesoProcedimiento [idProceso=" + idProceso + ", idProcedimiento=" + idProcedimiento + "]";
	}

}
