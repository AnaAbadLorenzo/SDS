package com.sds.service.proceso.model;

import java.util.Date;
import java.util.Set;

public class ProcesoReturn {

	private Integer idProceso;
	private String nombreProceso;
	private String descripProceso;
	private Date fechaProceso;
	private Integer borradoProceso;
	private Set<Procedimiento> procedimientos;
	private Set<Objetivo> objetivos;
	private Set<RespuestaPosible> respuestasPosibles;

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(final Integer idProceso) {
		this.idProceso = idProceso;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(final String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getDescripProceso() {
		return descripProceso;
	}

	public void setDescripProceso(final String descripProceso) {
		this.descripProceso = descripProceso;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(final Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Integer getBorradoProceso() {
		return borradoProceso;
	}

	public void setBorradoProceso(final Integer borradoProceso) {
		this.borradoProceso = borradoProceso;
	}

	public ProcesoReturn(final Integer idProceso, final String nombreProceso, final String descripProceso,
			final Date fechaProceso, final Integer borradoProceso, final Set<Procedimiento> procedimientos,
			final Set<Objetivo> objetivos, final Set<RespuestaPosible> respuestasPosibles) {
		super();
		this.idProceso = idProceso;
		this.nombreProceso = nombreProceso;
		this.descripProceso = descripProceso;
		this.fechaProceso = fechaProceso;
		this.borradoProceso = borradoProceso;
		this.procedimientos = procedimientos;
		this.objetivos = objetivos;
		this.respuestasPosibles = respuestasPosibles;
	}

	public ProcesoReturn() {
		super();
	}

	@Override
	public String toString() {
		return "ProcesoReturn [idProceso=" + idProceso + ", nombreProceso=" + nombreProceso + ", descripProceso="
				+ descripProceso + ", fechaProceso=" + fechaProceso + ", borradoProceso=" + borradoProceso
				+ ", procedimientos=" + procedimientos + ", objetivos=" + objetivos + ", respuestasPosibles="
				+ respuestasPosibles + "]";
	}

}
