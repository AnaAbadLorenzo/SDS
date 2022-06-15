package com.sds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "noticias")
@NamedQueries({ @NamedQuery(name = "NoticiasEntity.findAllNoticias", query = "SELECT n FROM NoticiasEntity n"),
		@NamedQuery(name = "NoticiasEntity.numberFindAllNoticias", query = "SELECT COUNT(n) FROM NoticiasEntity n"),
		@NamedQuery(name = "NoticiasEntity.findAllNoticiasOrderByFecha", query = "SELECT n FROM NoticiasEntity n ORDER BY n.fechaNoticia DESC"),
		@NamedQuery(name = "NoticiasEntity.findNoticia", query = "SELECT n FROM NoticiasEntity n WHERE LOWER(n.tituloNoticia) LIKE LOWER(CONCAT('%', :tituloNoticia, '%')) AND LOWER(n.textoNoticia) LIKE LOWER(CONCAT('%',:textoNoticia, '%')) AND n.fechaNoticia LIKE CONCAT('%',:fechaNoticia, '%')"),
		@NamedQuery(name = "NoticiasEntity.numberFindNoticia", query = "SELECT COUNT(n) FROM NoticiasEntity n WHERE LOWER(n.tituloNoticia) LIKE LOWER(CONCAT('%', :tituloNoticia, '%')) AND LOWER(n.textoNoticia) LIKE LOWER(CONCAT('%',:textoNoticia, '%')) AND n.fechaNoticia LIKE CONCAT('%',:fechaNoticia, '%')"),
		@NamedQuery(name = "NoticiasEntity.findByTituloNoticia", query = "SELECT n FROM NoticiasEntity n WHERE n.tituloNoticia = :tituloNoticia"),
		@NamedQuery(name = "NoticiasEntity.findNoticiaWithoutDate", query = "SELECT n FROM NoticiasEntity n WHERE LOWER(n.tituloNoticia) LIKE LOWER(CONCAT('%', :tituloNoticia, '%')) AND LOWER(n.textoNoticia) LIKE LOWER(CONCAT('%',:textoNoticia, '%'))"),
		@NamedQuery(name = "NoticiasEntity.numberFindNoticiaWithoutDate", query = "SELECT COUNT(n) FROM NoticiasEntity n WHERE LOWER(n.tituloNoticia) LIKE LOWER(CONCAT('%', :tituloNoticia, '%')) AND LOWER(n.textoNoticia) LIKE LOWER(CONCAT('%',:textoNoticia, '%'))"), })
public class NoticiasEntity {

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

	public NoticiasEntity() {
		super();
	}

	public NoticiasEntity(final Integer idNoticia, final String tituloNoticia, final String textoNoticia,
			final Date fechaNoticia) {
		super();
		this.idNoticia = idNoticia;
		this.tituloNoticia = tituloNoticia;
		this.textoNoticia = textoNoticia;
		this.fechaNoticia = fechaNoticia;
	}

	public NoticiasEntity(final String tituloNoticia, final String textoNoticia, final Date fechaNoticia) {
		super();
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
