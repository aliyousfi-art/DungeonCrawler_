package com.dnd.combat;

public record CombatOutcome(CombatEndState endState, int fleeSteps) {

    public CombatOutcome {
        if (endState == null) {
            throw new IllegalArgumentException("endState must not be null");
        }
        if (endState != CombatEndState.HERO_FLED && fleeSteps != 0) {
            throw new IllegalArgumentException("fleeSteps must be 0 unless HERO_FLED");
        }
        if (endState == CombatEndState.HERO_FLED && fleeSteps < 1) {
            throw new IllegalArgumentException("fleeSteps must be >= 1 when HERO_FLED");
        }
    }
}
