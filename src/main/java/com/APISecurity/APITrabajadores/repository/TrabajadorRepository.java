package com.APISecurity.APITrabajadores.repository;

import com.APISecurity.APITrabajadores.model.entity.TrabajadorEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorRepository extends JpaRepository<TrabajadorEntity, Integer> {

    Optional<TrabajadorEntity> findByCedula(String username);
}
