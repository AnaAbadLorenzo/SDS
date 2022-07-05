package com.sds.service.proceso.model;

public class Procedimiento {

	private Integer idProcedimiento;
	private String nombreProcedimiento;

	public Integer getIdProcedimiento() {
		return idProcedimiento;
	}

	public void setIdProcedimiento(final Integer idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
	}

	public String getNombreProcedimiento() {
		return nombreProcedimiento;
	}

	public void setNombreProcedimiento(final String nombreProcedimiento) {
		this.nombreProcedimiento = nombreProcedimiento;
	}

	public Procedimiento(final Integer idProcedimiento, final String nombreProcedimiento) {
		super();
		this.idProcedimiento = idProcedimiento;
		this.nombreProcedimiento = nombreProcedimiento;
	}

	public Procedimiento() {
		super();
	}

	@Override
	public String toString() {
		return "Procedimiento [idProcedimiento=" + idProcedimiento + ", nombreProcedimiento=" + nombreProcedimiento
				+ "]";
	}

}
