create table expenses (
  `id` int not null auto_increment,
  `date` date not null,
  `amount` decimal(4,2),
  `reason` varchar(250) not null,
  primary key (`id`)
  );
  
  
