package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sds.model.RolEntity;

@Component
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

	RolEntity findByRolName(String rolName);

	List<RolEntity> findDeleteRol(int borradoRol);
}
