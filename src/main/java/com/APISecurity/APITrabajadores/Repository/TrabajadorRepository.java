package com.APISecurity.APITrabajadores.Repository;

import com.APISecurity.APITrabajadores.model.TrabajadorEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabajadorRepository extends JpaRepository<TrabajadorEntity, Id> {

    Optional<TrabajadorEntity> findByCedula(String username);
}
