package com.sds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sds.model.compositekey.ProcesoProcedimientoKey;

@Entity
@IdClass(ProcesoProcedimientoKey.class)
@Table(name = "procesoprocedimiento")
@NamedQueries({
		@NamedQuery(name = "ProcesoProcedimientoEntity.findIdProcesoByIdProcedimiento", query = "SELECT p.idProceso FROM ProcesoProcedimientoEntity p WHERE p.idProcedimiento =: idProcedimiento"),
		@NamedQuery(name = "ProcesoProcedimientoEntity.findIdProcedimientoByIdProceso", query = "SELECT p.idProcedimiento FROM ProcesoProcedimientoEntity p WHERE p.idProceso =: idProceso"),
		@NamedQuery(name = "ProcesoProcedimientoEntity.findProcesoProcedimientoByIdProcedimiento", query = "SELECT p FROM ProcesoProcedimientoEntity p WHERE p.idProcedimiento =: idProcedimiento"),
		@NamedQuery(name = "ProcesoProcedimientoEntity.findProcesoProcedimiento", query = "SELECT p FROM ProcesoProcedimientoEntity p WHERE p.idProceso =: idProceso AND p.idProcedimiento =: idProcedimiento"),
		@NamedQuery(name = "ProcesoProcedimientoEntity.numberFindProcesoProcedimiento", query = "SELECT COUNT(p) FROM ProcesoProcedimientoEntity p WHERE p.idProceso =: idProceso AND p.idProcedimiento =: idProcedimiento"),
		@NamedQuery(name = "ProcesoProcedimientoEntity.numberFindProcesoProcedimientoByIdProcedimiento", query = "SELECT COUNT(p) FROM ProcesoProcedimientoEntity p WHERE p.idProcedimiento =: idProcedimiento") })
public class ProcesoProcedimientoEntity {

	@Id
	@Column(name = "id_proceso")
	private Integer idProceso;

	@Id
	@Column(name = "id_procedimiento")
	private Integer idProcedimiento;

	@Column(name = "orden_proceso")
	private Integer ordenProceso;

	public ProcesoProcedimientoEntity() {
		super();
	}

	public ProcesoProcedimientoEntity(final Integer idProceso, final Integer idProcedimiento,
			final Integer ordenProceso) {
		super();
		this.idProceso = idProceso;
		this.idProcedimiento = idProcedimiento;
		this.ordenProceso = ordenProceso;
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

	public Integer getOrdenProceso() {
		return ordenProceso;
	}

	public void setOrdenProceso(final Integer ordenProceso) {
		this.ordenProceso = ordenProceso;
	}

	@Override
	public String toString() {
		return "ProcesoProcedimientoEntity [idProceso=" + idProceso + ", idProcedimiento=" + idProcedimiento
				+ ", ordenProceso=" + ordenProceso + "]";
	}

}
