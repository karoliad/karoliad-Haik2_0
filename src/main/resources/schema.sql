CREATE SCHEMA haik;

create table user
(
    user_id int(10) auto_increment unique,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    phone_number varchar(100) not null,
    primary key (user_id)
);
create table ride
(
    ride_id int(10) auto_increment unique,
    created varchar(100) not null,
    createdbyid int(10) not null,
    startdate varchar(20) not null,
    starttime varchar(8) not null,
    seatsavailable int(2) not null,
    startlocation varchar(45) not null,
    destination varchar(100) not null,
    comments varchar(255),
    primary key (ride_id)
);
CREATE TABLE user_ride
(
    user_id      int(10)                       NOT NULL,
    ride_id      int(10)                       NOt NULL,
    PRIMARY KEY (`user_id`, `ride_id`),
    KEY `ride_id` (`ride_id`),
    CONSTRAINT `user_ride_ibfk_1`
        FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `user_ride_ibfk_2`
        FOREIGN KEY (`ride_id`) REFERENCES `ride` (`ride_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci