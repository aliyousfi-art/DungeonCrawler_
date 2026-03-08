package com.dnd.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Plateau {

    private final List<Case> cases;

    public Plateau(List<Case> cases) {
        Objects.requireNonNull(cases);
        if (cases.isEmpty()) {
            throw new IllegalArgumentException("cases must not be empty");
        }
        this.cases = new ArrayList<>(cases);
    }

    public int size() {
        return cases.size();
    }

    public Case getCaseAt(int position) {
        validatePosition(position);
        return cases.get(position - 1);
    }

    public void setCaseAt(int position, Case newCase) {
        validatePosition(position);
        cases.set(position - 1, Objects.requireNonNull(newCase));
    }

    private void validatePosition(int position) {
        if (position < 1 || position > size()) {
            throw new IllegalArgumentException("position must be between 1 and " + size());
        }
    }

    public static Plateau plateauDemo4Cases(Case case1, Case case2, Case case3, Case case4) {
        List<Case> cases = new ArrayList<>();
        cases.add(Objects.requireNonNull(case1));
        cases.add(Objects.requireNonNull(case2));
        cases.add(Objects.requireNonNull(case3));
        cases.add(Objects.requireNonNull(case4));
        return new Plateau(cases);
    }
}
