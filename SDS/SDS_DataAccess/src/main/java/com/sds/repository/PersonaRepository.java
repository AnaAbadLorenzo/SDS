package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.PersonaEntity;

@Component
public interface PersonaRepository extends JpaRepository<PersonaEntity, String> {

	Integer numberFindAll();

	Integer numberFindPersona(String dniP, String nombreP, String apellidosP, String fechaNacP, String direccionP,
			String telefonoP, String emailP);

	Integer numberPersonasEliminadas();

	List<PersonaEntity> findAllPerson();

	List<PersonaEntity> findPersona(String dniP, String nombreP, String apellidosP, String fechaNacP, String direccionP,
			String telefonoP, String emailP);

	List<PersonaEntity> findPersonasEliminadas();

	@Transactional
	@Modifying
	@Query("DELETE FROM PersonaEntity p WHERE p.dniP = ?1")
	void deletePersona(String dniPersona);
}
