package com.dnd.db;

import com.dnd.model.CharacterType;
import com.dnd.model.character.Guerrier;
import com.dnd.model.character.Magicien;
import com.dnd.model.character.Personnage;
import com.dnd.model.equipment.Arme;
import com.dnd.model.equipment.Bouclier;
import com.dnd.model.equipment.Philtre;
import com.dnd.model.equipment.Sort;

import java.util.Objects;

public final class HeroMapper {

    public Personnage toDomain(HeroEntity entity) {
        Objects.requireNonNull(entity);

        Personnage p = switch (entity.type()) {
            case WARRIOR -> new Guerrier(entity.name());
            case WIZARD -> new Magicien(entity.name());
        };

        p.setLifePoints(entity.lifePoints());
        p.setBaseAttack(entity.baseAttack());

        // The DB schema stores equipment name + bonus only.
        // We reconstruct the correct class type depending on the hero.
        if (entity.type() == CharacterType.WARRIOR) {
            p.setEquipementOffensif(new Arme(entity.offensiveName(), entity.offensiveAttackBonus()));
            p.setEquipementDefensif(new Bouclier(entity.defensiveName(), entity.defensiveDefenseBonus()));
        } else {
            p.setEquipementOffensif(new Sort(entity.offensiveName(), entity.offensiveAttackBonus()));
            p.setEquipementDefensif(new Philtre(entity.defensiveName(), entity.defensiveDefenseBonus()));
        }

        return p;
    }

    public HeroEntity toEntity(Long id, Personnage personnage) {
        Objects.requireNonNull(personnage);

        return new HeroEntity(
                id,
                personnage.getType(),
                personnage.getName(),
                personnage.getLifePoints(),
                personnage.getBaseAttack(),
                personnage.getEquipementOffensif().getName(),
                personnage.getEquipementOffensif().getAttackBonus(),
                personnage.getEquipementDefensif().getName(),
                personnage.getEquipementDefensif().getDefenseBonus()
        );
    }
}
