package com.sds.service.procedimientousuarioproceso.model;

import com.sds.model.ProcedimientoUsuarioProcesoEntity;

public class ProcedimientoUsuarioProceso {

	private String usuario;
	private ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso;

	public ProcedimientoUsuarioProceso() {
		super();
	}

	public ProcedimientoUsuarioProceso(final String usuario,
			final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso) {
		super();
		this.usuario = usuario;
		this.procedimientoUsuarioProceso = procedimientoUsuarioProceso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ProcedimientoUsuarioProcesoEntity getProcedimientoUsuarioProceso() {
		return procedimientoUsuarioProceso;
	}

	public void setProcedimientoUsuarioProceso(final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso) {
		this.procedimientoUsuarioProceso = procedimientoUsuarioProceso;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuarioProceso [usuario=" + usuario + ", procedimientoUsuarioProcesoEntity="
				+ procedimientoUsuarioProceso + "]";
	}

}
