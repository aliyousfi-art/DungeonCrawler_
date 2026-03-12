package com.dnd.db;

import com.dnd.model.CharacterType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class JdbcHeroRepository implements HeroRepository {

    private final ConnectionProvider connectionProvider;

    public JdbcHeroRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = Objects.requireNonNull(connectionProvider);
    }

    @Override
    public List<HeroEntity> getHeroes() throws SQLException {
        String sql = "SELECT id, type, name, life_points, base_attack, offensive_name, offensive_attack_bonus, defensive_name, defensive_defense_bonus FROM hero";

        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<HeroEntity> heroes = new ArrayList<>();
            while (rs.next()) {
                heroes.add(mapRow(rs));
            }
            return heroes;
        }
    }

    @Override
    public HeroEntity createHero(HeroEntity hero) throws SQLException {
        Objects.requireNonNull(hero);

        String sql = "INSERT INTO hero (type, name, life_points, base_attack, offensive_name, offensive_attack_bonus, defensive_name, defensive_defense_bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            bindWithoutId(ps, hero);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("No generated key returned for hero insert");
                }
                long id = keys.getLong(1);
                return new HeroEntity(
                        id,
                        hero.type(),
                        hero.name(),
                        hero.lifePoints(),
                        hero.baseAttack(),
                        hero.offensiveName(),
                        hero.offensiveAttackBonus(),
                        hero.defensiveName(),
                        hero.defensiveDefenseBonus()
                );
            }
        }
    }

    @Override
    public void editHero(HeroEntity hero) throws SQLException {
        Objects.requireNonNull(hero);
        if (hero.id() == null) {
            throw new IllegalArgumentException("hero.id must not be null for editHero");
        }

        String sql = "UPDATE hero SET type=?, name=?, life_points=?, base_attack=?, offensive_name=?, offensive_attack_bonus=?, defensive_name=?, defensive_defense_bonus=? WHERE id=?";

        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            bindWithoutId(ps, hero);
            ps.setLong(9, hero.id());

            ps.executeUpdate();
        }
    }

    @Override
    public void changeLifePoints(long heroId, int newLifePoints) throws SQLException {
        String sql = "UPDATE hero SET life_points=? WHERE id=?";

        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newLifePoints);
            ps.setLong(2, heroId);
            ps.executeUpdate();
        }
    }

    private static HeroEntity mapRow(ResultSet rs) throws SQLException {
        return new HeroEntity(
                rs.getLong("id"),
                CharacterType.valueOf(rs.getString("type")),
                rs.getString("name"),
                rs.getInt("life_points"),
                rs.getInt("base_attack"),
                rs.getString("offensive_name"),
                rs.getInt("offensive_attack_bonus"),
                rs.getString("defensive_name"),
                rs.getInt("defensive_defense_bonus")
        );
    }

    private static void bindWithoutId(PreparedStatement ps, HeroEntity hero) throws SQLException {
        ps.setString(1, hero.type().name());
        ps.setString(2, hero.name());
        ps.setInt(3, hero.lifePoints());
        ps.setInt(4, hero.baseAttack());
        ps.setString(5, hero.offensiveName());
        ps.setInt(6, hero.offensiveAttackBonus());
        ps.setString(7, hero.defensiveName());
        ps.setInt(8, hero.defensiveDefenseBonus());
    }
}
