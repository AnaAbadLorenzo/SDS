package com.sds.service.procedimientousuario.model;

import com.sds.model.ProcedimientoUsuarioEntity;

public class ProcedimientoUsuario {

	private String usuario;
	private ProcedimientoUsuarioEntity procedimientoUsuario;

	public ProcedimientoUsuario() {
		super();
	}

	public ProcedimientoUsuario(final String usuario, final ProcedimientoUsuarioEntity procedimientoUsuario) {
		super();
		this.usuario = usuario;
		this.procedimientoUsuario = procedimientoUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ProcedimientoUsuarioEntity getProcedimientoUsuario() {
		return procedimientoUsuario;
	}

	public void setProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuario) {
		this.procedimientoUsuario = procedimientoUsuario;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuario [usuario=" + usuario + ", procedimientoUsuario=" + procedimientoUsuario + "]";
	}
}
