create table expenses (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `amount` DECIMAL(4,2),
  `reason` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`)
  );
  
  
