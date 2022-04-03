package com.sds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "noticias")
public class NoticiaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_noticia")
	private Integer idNoticia;

	@Column(name = "titulo_noticia")
	private String tituloNoticia;

	@Column(name = "texto_noticia")
	private String textoNoticia;

	@Column(name = "fecha_noticia")
	private Date fechaNoticia;

	public NoticiaEntity() {
		super();
	}

	public NoticiaEntity(final Integer idNoticia, final String tituloNoticia, final String textoNoticia,
			final Date fechaNoticia) {
		super();
		this.idNoticia = idNoticia;
		this.tituloNoticia = tituloNoticia;
		this.textoNoticia = textoNoticia;
		this.fechaNoticia = fechaNoticia;
	}

	public Integer getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(final Integer idNoticia) {
		this.idNoticia = idNoticia;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(final String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public String getTextoNoticia() {
		return textoNoticia;
	}

	public void setTextoNoticia(final String textoNoticia) {
		this.textoNoticia = textoNoticia;
	}

	public Date getFechaNoticia() {
		return fechaNoticia;
	}

	public void setFechaNoticia(final Date fechaNoticia) {
		this.fechaNoticia = fechaNoticia;
	}

	@Override
	public String toString() {
		return "NoticiaEntity [idNoticia=" + idNoticia + ", tituloNoticia=" + tituloNoticia + ", textoNoticia="
				+ textoNoticia + ", fechaNoticia=" + fechaNoticia + "]";
	}

}
