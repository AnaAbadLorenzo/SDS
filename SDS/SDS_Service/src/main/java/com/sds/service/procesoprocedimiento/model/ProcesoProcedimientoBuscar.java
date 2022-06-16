package com.sds.service.procesoprocedimiento.model;

public class ProcesoProcedimientoBuscar {

	private String usuario;
	private Integer idProceso;
	private Integer idProcedimiento;

	public ProcesoProcedimientoBuscar() {
		super();
	}

	public ProcesoProcedimientoBuscar(final String usuario, final Integer idProceso, final Integer idProcedimiento) {
		super();
		this.usuario = usuario;
		this.idProceso = idProceso;
		this.idProcedimiento = idProcedimiento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
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
		return "ProcesoProcedimientoBuscar [usuario=" + usuario + ", idProceso=" + idProceso + ", idProcedimiento="
				+ idProcedimiento + "]";
	}

}
