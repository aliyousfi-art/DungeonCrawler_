package com.dnd.db;

import com.dnd.model.CharacterType;
import com.dnd.model.character.Hero;
import com.dnd.model.character.Warrior;
import com.dnd.model.character.Wizard;
import com.dnd.model.equipment.Shield;
import com.dnd.model.equipment.Spell;
import com.dnd.model.equipment.Elixir;
import com.dnd.model.equipment.Weapon;

import java.util.Objects;

/**
 * Converts between the {@link HeroEntity} persistence record and the
 * {@link Hero} domain model.
 */
public final class HeroMapper {

    /**
     * Builds a domain character from a persistence entity.
     *
     * @param entity the stored hero
     * @return the reconstructed domain character
     */
    public Hero toDomain(HeroEntity entity) {
        Objects.requireNonNull(entity);

        Hero hero = switch (entity.type()) {
            case WARRIOR -> new Warrior(entity.name());
            case WIZARD -> new Wizard(entity.name());
        };

        hero.setLifePoints(entity.lifePoints());
        hero.setBaseAttack(entity.baseAttack());

        if (entity.type() == CharacterType.WARRIOR) {
            hero.setOffensiveEquipment(new Weapon(entity.offensiveName(), entity.offensiveAttackBonus()));
            hero.setDefensiveEquipment(new Shield(entity.defensiveName(), entity.defensiveDefenseBonus()));
        } else {
            hero.setOffensiveEquipment(new Spell(entity.offensiveName(), entity.offensiveAttackBonus()));
            hero.setDefensiveEquipment(new Elixir(entity.defensiveName(), entity.defensiveDefenseBonus()));
        }

        return hero;
    }

    /**
     * Builds a persistence entity from a domain character.
     *
     * @param id   the database id, or null when not yet persisted
     * @param hero the domain character
     * @return the persistence entity
     */
    public HeroEntity toEntity(Long id, Hero hero) {
        Objects.requireNonNull(hero);

        return new HeroEntity(
                id,
                hero.getType(),
                hero.getName(),
                hero.getLifePoints(),
                hero.getBaseAttack(),
                hero.getOffensiveEquipment().getName(),
                hero.getOffensiveEquipment().getAttackBonus(),
                hero.getDefensiveEquipment().getName(),
                hero.getDefensiveEquipment().getDefenseBonus()
        );
    }
}
