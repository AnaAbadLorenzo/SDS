package com.sds.service.proceso.model;

import java.util.List;

public class DatosProcesoReturn {

	private List<Procedimiento> procedimientos;
	private List<Objetivo> objetivos;
	private List<RespuestaPosible> respuestasPosibles;
	private List<Integer> niveles;
	private List<Integer> ordenProceso;

	public DatosProcesoReturn() {
		super();
	}

	public DatosProcesoReturn(final List<Procedimiento> procedimientos, final List<Objetivo> objetivos,
			final List<RespuestaPosible> respuestasPosibles, final List<Integer> niveles,
			final List<Integer> ordenProceso) {
		super();
		this.procedimientos = procedimientos;
		this.objetivos = objetivos;
		this.respuestasPosibles = respuestasPosibles;
		this.niveles = niveles;
		this.ordenProceso = ordenProceso;
	}

	public List<Procedimiento> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(final List<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public List<Objetivo> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(final List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}

	public List<RespuestaPosible> getRespuestasPosibles() {
		return respuestasPosibles;
	}

	public void setRespuestasPosibles(final List<RespuestaPosible> respuestasPosibles) {
		this.respuestasPosibles = respuestasPosibles;
	}

	public List<Integer> getNiveles() {
		return niveles;
	}

	public void setNiveles(final List<Integer> niveles) {
		this.niveles = niveles;
	}

	public List<Integer> getOrdenProceso() {
		return ordenProceso;
	}

	public void setOrdenProceso(final List<Integer> ordenProceso) {
		this.ordenProceso = ordenProceso;
	}

	@Override
	public String toString() {
		return "DatosProcesoReturn [procedimientos=" + procedimientos + ", objetivos=" + objetivos
				+ ", respuestasPosibles=" + respuestasPosibles + ", niveles=" + niveles + ", ordenProceso="
				+ ordenProceso + "]";
	}

}
