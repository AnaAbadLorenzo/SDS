package com.sds.service.procedimiento.model;

import com.sds.model.ProcedimientoEntity;

public class Procedimiento {

	private String usuario;
	private ProcedimientoEntity procedimientoEntity;

	public Procedimiento() {
		super();
	}

	public Procedimiento(final String usuario, final ProcedimientoEntity procedimientoEntity) {
		super();
		this.usuario = usuario;
		this.procedimientoEntity = procedimientoEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ProcedimientoEntity getProcedimientoEntity() {
		return procedimientoEntity;
	}

	public void setProcedimientoEntity(final ProcedimientoEntity procedimientoEntity) {
		this.procedimientoEntity = procedimientoEntity;
	}

	@Override
	public String toString() {
		return "Procedimiento [usuario=" + usuario + ", procedimientoEntity=" + procedimientoEntity + "]";
	}

}
