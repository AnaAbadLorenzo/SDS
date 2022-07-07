package com.sds.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proceso")
@NamedQueries({
		@NamedQuery(name = "ProcesoEntity.findIdProcesoByName", query = "SELECT p.idProceso FROM ProcesoEntity p WHERE p.nombreProceso =: nombreProceso"),
		@NamedQuery(name = "ProcesoEntity.findProcesoByName", query = "SELECT p FROM ProcesoEntity p WHERE p.nombreProceso =: nombreProceso"),
		@NamedQuery(name = "ProcesoEntity.findAllProcesos", query = "SELECT p FROM ProcesoEntity p WHERE p.borradoProceso = 0"),
		@NamedQuery(name = "ProcesoEntity.numberFindAllProcesos", query = "SELECT COUNT(p) FROM ProcesoEntity p WHERE p.borradoProceso = 0"),
		@NamedQuery(name = "ProcesoEntity.findProceso", query = "SELECT p FROM ProcesoEntity p WHERE LOWER(p.nombreProceso) LIKE LOWER(CONCAT('%', :nombreProceso, '%')) AND LOWER(p.descripProceso) LIKE LOWER(CONCAT('%', :descripProceso, '%')) AND p.fechaProceso LIKE CONCAT ('%', :fechaProceso, '%') AND p.borradoProceso=0"),
		@NamedQuery(name = "ProcesoEntity.numberFindProceso", query = "SELECT COUNT(p) FROM ProcesoEntity p WHERE LOWER(p.nombreProceso) LIKE LOWER(CONCAT('%', :nombreProceso, '%')) AND LOWER(p.descripProceso) LIKE LOWER(CONCAT('%', :descripProceso, '%')) AND p.fechaProceso LIKE CONCAT ('%', :fechaProceso, '%') AND p.borradoProceso=0"),
		@NamedQuery(name = "ProcesoEntity.findProcesosEliminados", query = "SELECT p FROM ProcesoEntity p WHERE p.borradoProceso = 1"),
		@NamedQuery(name = "ProcesoEntity.numberFindProcesosEliminados", query = "SELECT COUNT(p) FROM ProcesoEntity p WHERE p.borradoProceso = 1") })
public class ProcesoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proceso")
	private Integer idProceso;

	@Column(name = "nombre_proceso")
	private String nombreProceso;

	@Column(name = "descrip_proceso")
	private String descripProceso;

	@Column(name = "fecha_proceso")
	private Date fechaProceso;

	@Column(name = "borrado_proceso")
	private Integer borradoProceso;

	@ManyToMany(mappedBy = "procesos")
	private Set<ProcedimientoEntity> procedimientos = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "nivel", joinColumns = { @JoinColumn(name = "id_proceso") }, inverseJoinColumns = {
			@JoinColumn(name = "id_objetivo") })
	private Set<ObjetivoEntity> objetivos = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "procesorespuesta_posible", joinColumns = {
			@JoinColumn(name = "id_proceso") }, inverseJoinColumns = { @JoinColumn(name = "id_respuesta") })
	private Set<RespuestaPosibleEntity> respuestasPosibles = new HashSet<>();

	@OneToMany(mappedBy = "proceso")
	private final Set<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesos = new HashSet<>();

	public ProcesoEntity() {
		super();
	}

	public ProcesoEntity(final Integer idProceso, final String nombreProceso, final String descripProceso,
			final Date fechaProceso, final Integer borradoProceso) {
		super();
		this.idProceso = idProceso;
		this.nombreProceso = nombreProceso;
		this.descripProceso = descripProceso;
		this.fechaProceso = fechaProceso;
		this.borradoProceso = borradoProceso;
	}

	public ProcesoEntity(final String nombreProceso, final String descripProceso, final Date fechaProceso,
			final Integer borradoProceso) {
		super();
		this.nombreProceso = nombreProceso;
		this.descripProceso = descripProceso;
		this.fechaProceso = fechaProceso;
		this.borradoProceso = borradoProceso;
	}

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

	public void setProcedimientos(final Set<ProcedimientoEntity> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public Set<ProcedimientoEntity> getProcedimientos() {
		return procedimientos;
	}

	public Set<ObjetivoEntity> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(final Set<ObjetivoEntity> objetivos) {
		this.objetivos = objetivos;
	}

	public Set<RespuestaPosibleEntity> getRespuestasPosibles() {
		return respuestasPosibles;
	}

	public void setRespuestasPosibles(final Set<RespuestaPosibleEntity> respuestasPosibles) {
		this.respuestasPosibles = respuestasPosibles;
	}

	public Set<ProcedimientoUsuarioProcesoEntity> getProcedimientoUsuarioProcesos() {
		return procedimientoUsuarioProcesos;
	}

	@Override
	public String toString() {
		return "ProcesoEntity [idProceso=" + idProceso + ", nombreProceso=" + nombreProceso + ", descripProceso="
				+ descripProceso + ", fechaProceso=" + fechaProceso + ", borradoProceso=" + borradoProceso + "]";
	}

}
