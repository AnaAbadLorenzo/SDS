package com.sds.service.procesoprocedimiento.model;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcesoEntity;

public class ProcesoProcedimiento {

	private String usuario;
	private ProcesoEntity proceso;
	private ProcedimientoEntity procedimiento;
	private Integer ordenProceso;

	public ProcesoProcedimiento() {
		super();
	}

	public ProcesoProcedimiento(final String usuario, final ProcesoEntity proceso,
			final ProcedimientoEntity procedimiento, final Integer ordenProceso) {
		super();
		this.usuario = usuario;
		this.proceso = proceso;
		this.procedimiento = procedimiento;
		this.ordenProceso = ordenProceso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ProcesoEntity getProceso() {
		return proceso;
	}

	public void setProceso(final ProcesoEntity proceso) {
		this.proceso = proceso;
	}

	public ProcedimientoEntity getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(final ProcedimientoEntity procedimiento) {
		this.procedimiento = procedimiento;
	}

	public Integer getOrdenProceso() {
		return ordenProceso;
	}

	public void setOrdenProceso(final Integer ordenProceso) {
		this.ordenProceso = ordenProceso;
	}

	@Override
	public String toString() {
		return "ProcesoProcedimiento [usuario=" + usuario + ", proceso=" + proceso + ", procedimiento=" + procedimiento
				+ ", ordenProceso=" + ordenProceso + "]";
	}
}
