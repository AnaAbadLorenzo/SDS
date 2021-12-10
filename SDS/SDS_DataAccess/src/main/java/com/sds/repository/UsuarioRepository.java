package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;

@Component
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

	UsuarioEntity findByUsuario(String usuario);
	RolEntity findRolUsuario(String usuario);
}
