package com.sds.service.noticias.model;

import com.sds.model.NoticiasEntity;

public class Noticia {

	private String usuario;
	private NoticiasEntity noticiaEntity;

	public Noticia() {
		super();
	}

	public Noticia(final String usuario, final NoticiasEntity noticiaEntity) {
		super();
		this.usuario = usuario;
		this.noticiaEntity = noticiaEntity;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public NoticiasEntity getNoticiaEntity() {
		return noticiaEntity;
	}

	public void setNoticiaEntity(final NoticiasEntity noticiaEntity) {
		this.noticiaEntity = noticiaEntity;
	}

	@Override
	public String toString() {
		return "Noticia [usuario=" + usuario + ", noticiaEntity=" + noticiaEntity + "]";
	}

}
