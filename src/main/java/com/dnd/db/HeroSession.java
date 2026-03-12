package com.dnd.db;

import com.dnd.model.character.Hero;

/**
 * Associates a persisted hero id with its in-memory domain instance.
 *
 * @param id   the database id of the hero
 * @param hero the domain character
 */
public record HeroSession(long id, Hero hero) {
}
