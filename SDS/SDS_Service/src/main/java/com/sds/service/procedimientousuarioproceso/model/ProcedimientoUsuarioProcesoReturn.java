package com.sds.service.procedimientousuarioproceso.model;

import java.util.List;

import com.sds.model.EvidenciaEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.RespuestaPosibleEntity;

public class ProcedimientoUsuarioProcesoReturn {

	private List<ProcesoEntity> procesos;
	private List<RespuestaPosibleEntity> respuestaPosible;
	private List<EvidenciaEntity> evidencia;

	public ProcedimientoUsuarioProcesoReturn() {
		super();
	}

	public ProcedimientoUsuarioProcesoReturn(final List<ProcesoEntity> procesos,
			final List<RespuestaPosibleEntity> respuestaPosible, final List<EvidenciaEntity> evidencia) {
		super();
		this.procesos = procesos;
		this.respuestaPosible = respuestaPosible;
		this.evidencia = evidencia;
	}

	public List<ProcesoEntity> getProcesos() {
		return procesos;
	}

	public void setProcesos(final List<ProcesoEntity> procesos) {
		this.procesos = procesos;
	}

	public List<RespuestaPosibleEntity> getRespuestaPosible() {
		return respuestaPosible;
	}

	public void setRespuestaPosible(final List<RespuestaPosibleEntity> respuestaPosible) {
		this.respuestaPosible = respuestaPosible;
	}

	public List<EvidenciaEntity> getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(final List<EvidenciaEntity> evidencia) {
		this.evidencia = evidencia;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuarioProcesoReturn [procesos=" + procesos + ", respuestaPosible=" + respuestaPosible
				+ ", evidencia=" + evidencia + "]";
	}

}
