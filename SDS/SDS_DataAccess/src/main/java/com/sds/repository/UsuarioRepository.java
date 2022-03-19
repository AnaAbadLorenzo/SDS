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

	List<UsuarioEntity> findUsuario(final String usuario, final RolEntity rol);

	Integer numberFindUsuarioWithRol(String usuario, RolEntity rol);

	Integer numberFindUsuario(final String usuario);

	List<UsuarioEntity> findUsuariosEliminados();

	Integer numberFindUsuariosEliminados();

	List<UsuarioEntity> findAllUsuarios();

	Integer numberFindAllUsuarios();

	UsuarioEntity findByUsuario(String usuario);

	UsuarioEntity findByDni(String dniUsuario);

	RolEntity findRolUsuario(String usuario);

	@Transactional
	@Modifying
	@Query("DELETE FROM UsuarioEntity u WHERE u.dniUsuario = ?1")
	void deleteUsuario(String dniUsuario);
}
