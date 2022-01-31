package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;

@Component
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

	List<UsuarioEntity> findUsuario(String dniUsuario, String usuario, RolEntity rol);

	List<UsuarioEntity> findUsuariosEliminados(Integer borradoUsuario);

	UsuarioEntity findByUsuario(String usuario);

	UsuarioEntity findByDni(String dniUsuario);

	RolEntity findRolUsuario(String usuario);

	@Transactional
	@Modifying
	@Query("DELETE FROM UsuarioEntity u WHERE u.dniUsuario = ?1")
	void deleteUsuario(String dniUsuario);
}
