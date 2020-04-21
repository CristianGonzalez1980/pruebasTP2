CREATE TABLE IF NOT EXISTS patogeno (
  id int ,
  cantidadDeEspecies int auto_increment NOT NULL,
  tipo varchar(100) NOT NULL UNIQUE,
  PRIMARY KEY (id)
)

ENGINE = InnoDB;