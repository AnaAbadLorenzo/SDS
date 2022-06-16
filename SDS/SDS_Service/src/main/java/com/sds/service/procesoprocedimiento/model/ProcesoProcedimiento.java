package com.sds.service.procesoprocedimiento.model;

import com.sds.model.ProcesoProcedimientoEntity;

public class ProcesoProcedimiento {

	private String usuario;
	private ProcesoProcedimientoEntity procesoProcedimientoEntity;

	public ProcesoProcedimiento() {
		super();
	}

	public ProcesoProcedimiento(final String usuario, final ProcesoProcedimientoEntity procesoProcedimientoEntity) {
		super();
		this.usuario = usuario;
		this.procesoProcedimientoEntity = procesoProcedimientoEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ProcesoProcedimientoEntity getProcesoProcedimientoEntity() {
		return procesoProcedimientoEntity;
	}

	public void setProcesoProcedimientoEntity(final ProcesoProcedimientoEntity procesoProcedimientoEntity) {
		this.procesoProcedimientoEntity = procesoProcedimientoEntity;
	}

	@Override
	public String toString() {
		return "ProcesoProcedimiento [usuario=" + usuario + ", procesoProcedimientoEntity=" + procesoProcedimientoEntity
				+ "]";
	}

}