package com.sds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.sds.model.compositekey.ProcesoProcedimientoKey;

@Entity
@IdClass(ProcesoProcedimientoKey.class)
@Table(name = "procesoprocedimiento")
public class ProcesoProcedimientoEntity {

	@Id
	@Column(name = "id_proceso")
	private Integer idProceso;

	@Id
	@Column(name = "id_procedimiento")
	private Integer idProcedimiento;

	public ProcesoProcedimientoEntity() {
		super();
	}

	public ProcesoProcedimientoEntity(final Integer idProceso, final Integer idProcedimiento) {
		super();
		this.idProceso = idProceso;
		this.idProcedimiento = idProcedimiento;
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

	@Override
	public String toString() {
		return "ProcesoProcedimientoEntity [idProceso=" + idProceso + ", idProcedimiento=" + idProcedimiento + "]";
	}

}
