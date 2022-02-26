package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.RolEntity;

@Component
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

	RolEntity findByRolName(String rolName);

	List<RolEntity> findAllRol();

	Integer numberFindAllRol();

	List<RolEntity> findRol(String rolName, String rolDescription);

	Integer numberFindRol(String rolName, String rolDescription);

	List<RolEntity> findDeleteRol();

	Integer numberFindDeleteRol();

	@Transactional
	@Modifying
	@Query("DELETE FROM RolEntity r WHERE r.idRol = ?1")
	void deleteRol(Integer idRol);

}
