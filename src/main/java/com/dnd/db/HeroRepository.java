package com.dnd.db;

import java.sql.SQLException;
import java.util.List;

/** Data access interface for hero persistence. */
public interface HeroRepository {

    List<HeroEntity> getHeroes() throws SQLException;

    HeroEntity createHero(HeroEntity hero) throws SQLException;

    void editHero(HeroEntity hero) throws SQLException;

    void changeLifePoints(long heroId, int newLifePoints) throws SQLException;
}
