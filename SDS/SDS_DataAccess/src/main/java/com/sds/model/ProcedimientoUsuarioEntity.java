package com.sds.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "procedimientousuario")
public class ProcedimientoUsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_procedimiento_usuario")
	private Integer idProcedimientoUsuario;

	@Column(name = "puntuacion_procedimiento_usuario")
	private Integer puntuacionProcedimientoUsuario;

	@Column(name = "fecha_procedimiento_usuario")
	private Date fechaProcedimientoUsuario;

	@Column(name = "borrado_procedimiento_usuario")
	private Integer borradoProcedimientoUsuario;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_procedimiento", referencedColumnName = "id_procedimiento")
	private ProcedimientoEntity procedimiento;

	@OneToMany(mappedBy = "procedimientoUsuario")
	private final Set<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesos = new HashSet<>();

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "dni_usuario", referencedColumnName = "dni_usuario")
	private UsuarioEntity usuario;

	public ProcedimientoUsuarioEntity() {
		super();
	}

	public ProcedimientoUsuarioEntity(final Integer idProcedimientoUsuario,
			final Integer puntuacionProcedimientoUsuario, final Date fechaProcedimientoUsuario,
			final Integer borradoProcedimientoUsuario) {
		super();
		this.idProcedimientoUsuario = idProcedimientoUsuario;
		this.puntuacionProcedimientoUsuario = puntuacionProcedimientoUsuario;
		this.fechaProcedimientoUsuario = fechaProcedimientoUsuario;
		this.borradoProcedimientoUsuario = borradoProcedimientoUsuario;
	}

	public Integer getIdProcedimientoUsuario() {
		return idProcedimientoUsuario;
	}

	public void setIdProcedimientoUsuario(final Integer idProcedimientoUsuario) {
		this.idProcedimientoUsuario = idProcedimientoUsuario;
	}

	public Integer getPuntuacionProcedimientoUsuario() {
		return puntuacionProcedimientoUsuario;
	}

	public void setPuntuacionProcedimientoUsuario(final Integer puntuacionProcedimientoUsuario) {
		this.puntuacionProcedimientoUsuario = puntuacionProcedimientoUsuario;
	}

	public Date getFechaProcedimientoUsuario() {
		return fechaProcedimientoUsuario;
	}

	public void setFechaProcedimientoUsuario(final Date fechaProcedimientoUsuario) {
		this.fechaProcedimientoUsuario = fechaProcedimientoUsuario;
	}

	public Integer getBorradoProcedimientoUsuario() {
		return borradoProcedimientoUsuario;
	}

	public void setBorradoProcedimientoUsuario(final Integer borradoProcedimientoUsuario) {
		this.borradoProcedimientoUsuario = borradoProcedimientoUsuario;
	}

	public ProcedimientoEntity getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(final ProcedimientoEntity procedimiento) {
		this.procedimiento = procedimiento;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(final UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public Set<ProcedimientoUsuarioProcesoEntity> getProcedimientoUsuarioProcesos() {
		return procedimientoUsuarioProcesos;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuarioEntity [idProcedimientoUsuario=" + idProcedimientoUsuario
				+ ", puntuacionProcedimientoUsuario=" + puntuacionProcedimientoUsuario + ", fechaProcedimientoUsuario="
				+ fechaProcedimientoUsuario + ", borradoProcedimientoUsuario=" + borradoProcedimientoUsuario + "]";
	}

}
