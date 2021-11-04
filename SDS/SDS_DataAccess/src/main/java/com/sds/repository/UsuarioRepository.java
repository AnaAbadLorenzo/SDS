package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sds.model.UsuarioEntity;

@Component("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

	UsuarioEntity findByUsuario(String usuario);
}
