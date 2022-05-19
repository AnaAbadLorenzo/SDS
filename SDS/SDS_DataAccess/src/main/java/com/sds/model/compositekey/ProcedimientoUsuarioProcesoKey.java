package com.sds.model.compositekey;

import java.io.Serializable;
import java.util.Objects;

public class ProcedimientoUsuarioProcesoKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idProcedimientoUsuario;
	private Integer idProceso;

	public ProcedimientoUsuarioProcesoKey() {

	}

	public ProcedimientoUsuarioProcesoKey(final Integer idProcedimientoUsuario, final Integer idProceso) {
		super();
		this.idProcedimientoUsuario = idProcedimientoUsuario;
		this.idProceso = idProceso;
	}

	public Integer getIdProcedimientoUsuario() {
		return idProcedimientoUsuario;
	}

	public void setIdProcedimientoUsuario(final Integer idProcedimientoUsuario) {
		this.idProcedimientoUsuario = idProcedimientoUsuario;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	@Override
	public String toString() {
		return "NivelKey [idProcedimientoUsuario=" + idProcedimientoUsuario + ", idProceso=" + idProceso + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProcedimientoUsuario, idProceso);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProcedimientoUsuarioProcesoKey other = (ProcedimientoUsuarioProcesoKey) obj;
		return Objects.equals(idProcedimientoUsuario, other.idProcedimientoUsuario)
				&& Objects.equals(idProceso, other.idProceso);
	}

}
