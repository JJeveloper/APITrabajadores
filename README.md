### API REST Spring Boot con JWT
## Descripcion
API REST desarrollada con Spring Boot que implementa autenticación y autorización con JWT, control de acceso por roles (ADMIN / USER) y persistencia en MySQL.
Permite la gestión de trabajadores con operaciones CRUD y seguridad basada en roles.

### Tecnologias Implementadas

- Java 21+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- JPA / Hibernate
- MySQL
- Maven

### Seguridad
- Autenticación mediante JWT
- Roles disponibles:
    - ADMIN
    - USER
- Restricciones:
    - Solo el rol ADMIN puede eliminar trabajadores
    - USER y ADMIN puede consultar, actualizar y guardar su información


### Endpoints

#### Autenticacion

**POST: Login**
 ```bash
    http://localhost:8092/login
```
**Body (JSON):**
 ```bash
 {
    "username": "0857412025",
    "password": "123456"
 }
```
**Respuesta:**
```bash
{
    "Mensaje": "Autenticacion correcta",
    "Usuario": "0857412025",
    "Token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwODU3N....."
}
```
### Trabajador

**POST: Crear trabajador**
 ```bash
  http://localhost:8092/api/v1/creartrabajador
```

**Body (JSON):**
 ```bash
{
    "cedula": "0328745561",
    "email": "vbnhgtg@tg.tggg",
    "nombres": "",
    "password": "12345",
    "rol": "USER"   
}
```
Requiere token JWT

**GET: Buscar por cedula**
 ```bash
    http://localhost:8092/api/v1/buscarcedula/{cedula}  
```
**Respuesta:**

 ```bash
{
    "cedula": "2489520014",
    "email": "weq@bhhyu6.tggg",
    "nombres": "mario",
    "password": "$2a$10$C3S0OplEOJtt2cU....",
    "rol": "USER"
}
```
Requiere token JWT

**PUT: Actualizar contrasena**
```bash
  http://localhost:8092/api/v1/actualizarpassword/{id}
```

**Body (JSON):**
```bash
{
    "anteriorpassword": "{anterior contrasena}",
    "nuevapassword": "{nueva contrasena}"
}
```
**Respuesta:**
```bash
  Contrasena actualizada correctamente
```
Requiere token JWT

**DELETE: eliminar trabajador**
```bash
  http://localhost:8092/api/v1/eliminartrabajador/{cedula}
```
**Respuesta:**
```bash
  204 No Content
```

### Base de datos MySQL
<img width="491" height="274" alt="Captura de pantalla 2025-12-28 163708" src="https://github.com/user-attachments/assets/dfabfe86-5d45-414f-9d8c-a7fbaec5d300" />


```bash
  CREATE SCHEMA IF NOT EXISTS `apitrabajadoressecurity`;
    USE `apitrabajadoressecurity` ;
  
  CREATE TABLE IF NOT EXISTS `apitrabajadoressecurity`.`rol` (
    `idrol` INT NOT NULL AUTO_INCREMENT,
    `rol` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idrol`));
  
  CREATE TABLE IF NOT EXISTS `apitrabajadoressecurity`.`trabajador` (
    `idtrabajador` INT NOT NULL AUTO_INCREMENT,
    `cedula` VARCHAR(45) NOT NULL,
    `nombres` VARCHAR(150) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `rol_idrol` INT NOT NULL,
    PRIMARY KEY (`idtrabajador`),
    INDEX `fk_trabajador_rol_idx` (`rol_idrol` ASC) VISIBLE,
    CONSTRAINT `fk_trabajador_rol`
      FOREIGN KEY (`rol_idrol`)
      REFERENCES `apitrabajadoressecurity`.`rol` (`idrol`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);
  
  INSERT INTO `apitrabajadoressecurity`.`rol` (`rol`) VALUES ('ADMIN');
  INSERT INTO `apitrabajadoressecurity`.`rol` (`rol`) VALUES ('USER');
  INSERT INTO `apitrabajadoressecurity`.`trabajador` (`cedula`, `email`, `nombres`, `password`, `rol_idrol`) 
  VALUES ('0928745124', 'jjlioo@gmail.com', 'julio andrade', '$2a$10$J9Jl1L1ee/BU8EDPMhLm1OkSqHnjHomZSR/Q2hxlITf1W8W8tVz96', '1');
```


