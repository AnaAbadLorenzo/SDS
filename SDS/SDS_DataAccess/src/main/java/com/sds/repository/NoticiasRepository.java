package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.NoticiasEntity;

@Component
public interface NoticiasRepository extends JpaRepository<NoticiasEntity, Integer> {

	List<NoticiasEntity> findAllNoticias();

	Integer numberFindAllNoticias();

	List<NoticiasEntity> findAllNoticiasOrderByFecha();

	List<NoticiasEntity> findNoticia(final String tituloNoticia, final String textoNoticia, final String fechaNoticia);

	Integer numberFindNoticia(final String tituloNoticia, final String textoNoticia, final String fechaNoticia);

	NoticiasEntity findByTituloNoticia(final String tituloNoticia);

	List<NoticiasEntity> findNoticiaWithoutDate(final String tituloNoticia, final String textoNoticia);

	Integer numberFindNoticiaWithoutDate(final String tituloNoticia, final String textoNoticia);

	@Transactional
	@Modifying
	@Query("DELETE FROM NoticiasEntity n WHERE n.idNoticia = ?1")
	void deleteNoticia(Integer idNoticia);

}
