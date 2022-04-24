package com.sds.model.compositekey;

import java.io.Serializable;

public class ProcesoProcedimientoKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idProceso;
	private Integer idProcedimiento;

	public ProcesoProcedimientoKey() {
		super();
	}

	public ProcesoProcedimientoKey(final Integer idProceso, final Integer idProcedimiento) {
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
		return "ProcesoProcedimientoKey [idProceso=" + idProceso + ", idProcedimiento=" + idProcedimiento + "]";
	}

}
