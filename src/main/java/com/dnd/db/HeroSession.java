package com.dnd.db;

import com.dnd.model.character.Personnage;

public record HeroSession(long id, Personnage hero) {
}
