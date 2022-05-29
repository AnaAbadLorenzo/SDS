package com.sds.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "respuesta_posible")
@NamedQueries({
		@NamedQuery(name = "RespuestaPosibleEntity.findAllRespuestasPosibles", query = "SELECT r FROM RespuestaPosibleEntity r WHERE r.borradoRespuesta = 0"),
		@NamedQuery(name = "RespuestaPosibleEntity.numberFindAllRespuestasPosibles", query = "SELECT COUNT(r) FROM RespuestaPosibleEntity r WHERE r.borradoRespuesta = 0"),
		@NamedQuery(name = "RespuestaPosibleEntity.findRespuestaPosible", query = "SELECT r FROM RespuestaPosibleEntity r WHERE r.textoRespuesta LIKE UPPER(CONCAT('%', :textoRespuesta, '%'))"),
		@NamedQuery(name = "RespuestaPosibleEntity.numberFindRespuestaPosible", query = "SELECT COUNT(r) FROM RespuestaPosibleEntity r WHERE r.textoRespuesta LIKE UPPER(CONCAT('%', :textoRespuesta, '%'))"),
		@NamedQuery(name = "RespuestaPosibleEntity.findRespuestasPosiblesEliminadas", query = "SELECT r FROM RespuestaPosibleEntity r WHERE r.borradoRespuesta = 1"),
		@NamedQuery(name = "RespuestaPosibleEntity.numberFindRespuestasPosiblesEliminadas", query = "SELECT  COUNT(r) FROM RespuestaPosibleEntity r WHERE r.borradoRespuesta = 1"),
		@NamedQuery(name = "RespuestaPosibleEntity.findRespuestaPosibleByText", query = " SELECT r FROM RespuestaPosibleEntity r WHERE r.textoRespuesta LIKE UPPER(CONCAT('%', :textoRespuesta, '%'))") })
public class RespuestaPosibleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_respuesta")
	private Integer idRespuesta;

	@Column(name = "texto_respuesta")
	private String textoRespuesta;

	@Column(name = "borrado_respuesta")
	private Integer borradoRespuesta;

	@OneToMany(mappedBy = "respuestaPosible")
	private final Set<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesos = new HashSet<>();

	public RespuestaPosibleEntity() {
		super();
	}

	public RespuestaPosibleEntity(final Integer idRespuesta, final String textoRespuesta,
			final Integer borradoRespuesta) {
		super();
		this.idRespuesta = idRespuesta;
		this.textoRespuesta = textoRespuesta;
		this.borradoRespuesta = borradoRespuesta;
	}

	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(final Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(final String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	public Integer getBorradoRespuesta() {
		return borradoRespuesta;
	}

	public void setBorradoRespuesta(final Integer borradoRespuesta) {
		this.borradoRespuesta = borradoRespuesta;
	}

	public Set<ProcedimientoUsuarioProcesoEntity> getProcedimientoUsuarioProcesos() {
		return procedimientoUsuarioProcesos;
	}

	@Override
	public String toString() {
		return "RespuestaPosibleEntity [idRespuesta=" + idRespuesta + ", textoRespuesta=" + textoRespuesta
				+ ", borradoRespuesta=" + borradoRespuesta + "]";
	}

}
