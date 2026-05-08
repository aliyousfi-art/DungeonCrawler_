package com.dnd.db;

import com.dnd.model.CharacterType;
import com.dnd.model.character.Hero;
import com.dnd.model.character.Warrior;
import com.dnd.model.character.Wizard;
import com.dnd.model.equipment.Elixir;
import com.dnd.model.equipment.Shield;
import com.dnd.model.equipment.Spell;
import com.dnd.model.equipment.Weapon;

import java.util.Objects;

/** Converts between {@link Hero} domain objects and {@link HeroEntity} DB records. */
public final class HeroMapper {

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
