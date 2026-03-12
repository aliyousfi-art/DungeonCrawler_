-- Schema for the DungeonCrawler hero persistence layer.
CREATE TABLE IF NOT EXISTS hero (
    id                      BIGINT       NOT NULL AUTO_INCREMENT,
    type                    VARCHAR(16)  NOT NULL,
    name                    VARCHAR(64)  NOT NULL,
    life_points             INT          NOT NULL,
    base_attack             INT          NOT NULL,
    offensive_name          VARCHAR(64)  NOT NULL,
    offensive_attack_bonus  INT          NOT NULL,
    defensive_name          VARCHAR(64)  NOT NULL,
    defensive_defense_bonus INT          NOT NULL,
    PRIMARY KEY (id)
);
