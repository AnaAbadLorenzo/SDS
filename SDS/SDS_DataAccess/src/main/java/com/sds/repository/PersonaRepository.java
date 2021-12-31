package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.PersonaEntity;

@Component
public interface PersonaRepository extends JpaRepository<PersonaEntity, String> {

	@Transactional
	@Modifying
	@Query("DELETE FROM PersonaEntity p WHERE p.dniP = ?1")
	void deletePersona(String dniPersona);
}
