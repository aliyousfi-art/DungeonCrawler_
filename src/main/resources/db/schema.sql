CREATE DATABASE IF NOT EXISTS DonjonsEtDragons;
USE DonjonsEtDragons;

CREATE TABLE IF NOT EXISTS hero (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    life_points INT NOT NULL,
    base_attack INT NOT NULL,

    offensive_name VARCHAR(100) NOT NULL,
    offensive_attack_bonus INT NOT NULL,

    defensive_name VARCHAR(100) NOT NULL,
    defensive_defense_bonus INT NOT NULL
);
