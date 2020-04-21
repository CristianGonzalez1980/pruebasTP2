CREATE TABLE IF NOT EXISTS patogeno (
  id int auto_increment NOT NULL ,
  tipo VARCHAR(255) NOT NULL UNIQUE,
  cantEspecies int NOT NULL, /*quite el auto_increment porque esto se setea desde el modelo al crear una especie*/
  PRIMARY KEY (id)
)
ENGINE = InnoDB;
