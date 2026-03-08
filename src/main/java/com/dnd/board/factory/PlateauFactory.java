package com.dnd.board.factory;

import com.dnd.board.Case;
import com.dnd.board.CaseVide;
import com.dnd.board.Dragon;
import com.dnd.board.Gobelin;
import com.dnd.board.GrandePotion;
import com.dnd.board.Plateau;
import com.dnd.board.PotionStandard;
import com.dnd.board.Sorcier;
import com.dnd.model.equipment.BouleDeFeu;
import com.dnd.model.equipment.Eclair;
import com.dnd.model.equipment.Epee;
import com.dnd.model.equipment.Massue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public final class PlateauFactory {

    private PlateauFactory() {
    }

    public static Plateau createFixed64() {
        List<Case> cases = new ArrayList<>(Collections.nCopies(64, new CaseVide()));

        // Enemies
        put(cases, 45, new Dragon());
        put(cases, 52, new Dragon());
        put(cases, 56, new Dragon());
        put(cases, 62, new Dragon());

        put(cases, 10, new Sorcier());
        put(cases, 20, new Sorcier());
        put(cases, 25, new Sorcier());
        put(cases, 32, new Sorcier());
        put(cases, 35, new Sorcier());
        put(cases, 36, new Sorcier());
        put(cases, 37, new Sorcier());
        put(cases, 40, new Sorcier());
        put(cases, 44, new Sorcier());
        put(cases, 47, new Sorcier());

        put(cases, 3, new Gobelin());
        put(cases, 6, new Gobelin());
        put(cases, 9, new Gobelin());
        put(cases, 12, new Gobelin());
        put(cases, 15, new Gobelin());
        put(cases, 18, new Gobelin());
        put(cases, 21, new Gobelin());
        put(cases, 24, new Gobelin());
        put(cases, 27, new Gobelin());
        put(cases, 30, new Gobelin());

        // Surprise boxes / items
        put(cases, 2, new Massue());
        put(cases, 5, new Massue());
        put(cases, 11, new Massue());
        put(cases, 22, new Massue());
        put(cases, 38, new Massue());

        put(cases, 19, new Epee());
        put(cases, 26, new Epee());
        put(cases, 42, new Epee());
        put(cases, 53, new Epee());

        put(cases, 1, new Eclair());
        put(cases, 4, new Eclair());
        put(cases, 8, new Eclair());
        put(cases, 17, new Eclair());
        put(cases, 23, new Eclair());

        put(cases, 48, new BouleDeFeu());
        put(cases, 49, new BouleDeFeu());

        put(cases, 7, new PotionStandard());
        put(cases, 13, new PotionStandard());
        put(cases, 31, new PotionStandard());
        put(cases, 33, new PotionStandard());
        put(cases, 39, new PotionStandard());
        put(cases, 43, new PotionStandard());

        put(cases, 28, new GrandePotion());
        put(cases, 41, new GrandePotion());

        return new Plateau(cases);
    }

    public static Plateau createRandom64(Random random) {
        List<Case> cases = new ArrayList<>(Collections.nCopies(64, new CaseVide()));

        List<Integer> positions = new ArrayList<>();
        for (int i = 1; i <= 64; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions, random);

        int idx = 0;

        idx = placeMany(cases, positions, idx, 4, Dragon::new);
        idx = placeMany(cases, positions, idx, 10, Sorcier::new);
        idx = placeMany(cases, positions, idx, 10, Gobelin::new);

        idx = placeMany(cases, positions, idx, 5, Massue::new);
        idx = placeMany(cases, positions, idx, 4, Epee::new);
        idx = placeMany(cases, positions, idx, 5, Eclair::new);
        idx = placeMany(cases, positions, idx, 2, BouleDeFeu::new);
        idx = placeMany(cases, positions, idx, 6, PotionStandard::new);
        placeMany(cases, positions, idx, 2, GrandePotion::new);

        return new Plateau(cases);
    }

    // ---------- helpers

    private static void put(List<Case> cases, int position, Case tile) {
        cases.set(position - 1, tile);
    }

    private static int placeMany(
            List<Case> cases,
            List<Integer> positions,
            int startIdx,
            int count,
            Supplier<? extends Case> supplier
    ) {
        int idx = startIdx;
        for (int i = 0; i < count; i++) {
            int pos = positions.get(idx++);
            cases.set(pos - 1, supplier.get());
        }
        return idx;
    }
}
