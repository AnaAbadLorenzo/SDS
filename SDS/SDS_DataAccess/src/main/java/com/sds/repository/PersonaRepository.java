package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sds.model.PersonaEntity;

@Component
public interface PersonaRepository extends JpaRepository<PersonaEntity, String> {

}
