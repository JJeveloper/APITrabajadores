package com.APISecurity.APITrabajadores.Repository;

import com.APISecurity.APITrabajadores.model.TrabajadorEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorRepository extends JpaRepository<TrabajadorEntity, Id> {
}
