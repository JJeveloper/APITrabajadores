package com.APISecurity.APITrabajadores.repository;

import com.APISecurity.APITrabajadores.model.entity.RolEntity;
import com.APISecurity.APITrabajadores.model.entity.TrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

    Optional<RolEntity> findByRol(String rol);
}
