package com.dnd.db;

import java.sql.SQLException;
import java.util.List;

/**
 * Persistence operations for heroes.
 */
public interface HeroRepository {

    /**
     * @return all stored heroes
     * @throws SQLException if the query fails
     */
    List<HeroEntity> getHeroes() throws SQLException;

    /**
     * Inserts a new hero and returns it with its generated id.
     *
     * @param hero the hero to insert (id ignored)
     * @return the stored hero with its generated id
     * @throws SQLException if the insert fails
     */
    HeroEntity createHero(HeroEntity hero) throws SQLException;

    /**
     * Updates an existing hero.
     *
     * @param hero the hero to update (id must be set)
     * @throws SQLException if the update fails
     */
    void editHero(HeroEntity hero) throws SQLException;

    /**
     * Persists only the life points of a hero.
     *
     * @param heroId        the id of the hero to update
     * @param newLifePoints the new life points value
     * @throws SQLException if the update fails
     */
    void changeLifePoints(long heroId, int newLifePoints) throws SQLException;
}
