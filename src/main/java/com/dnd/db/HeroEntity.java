package com.dnd.db;

import com.dnd.model.CharacterType;

public record HeroEntity(
        Long id,
        CharacterType type,
        String name,
        int lifePoints,
        int baseAttack,
        String offensiveName,
        int offensiveAttackBonus,
        String defensiveName,
        int defensiveDefenseBonus
) {
}
