package dao;

import entities.Hero;
import entities.Item;
import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GameSaveDao {

    public void saveFullGame(Hero hero, List<Item> items) {
        String updateHeroSql = "UPDATE heroes SET name = ?, health = ?, influence = ?, gold = ? WHERE id = ?";
        String deleteItemsSql = "DELETE FROM items WHERE hero_id = ?";
        String insertItemSql = "INSERT INTO items (hero_id, name, type, effect_value) VALUES (?, ?, ?, ?)";

        Connection conn = null;

        try {
            // 1. Получаем одно соединение для всей транзакции
            conn = DatabaseConnection.getConnection();

            // 2. ОТКЛЮЧАЕМ AUTO-COMMIT
            conn.setAutoCommit(false);

            // -----------------------------------------------------------
            // ШАГ 1: Обновляем характеристики героя
            // -----------------------------------------------------------
            try (PreparedStatement heroStmt = conn.prepareStatement(updateHeroSql)) {
                heroStmt.setString(1, hero.getName());
                heroStmt.setInt(2, hero.getHealth());
                heroStmt.setInt(3, hero.getInfluence());
                heroStmt.setInt(4, hero.getGold());
                heroStmt.setInt(5, hero.getId());
                heroStmt.executeUpdate();
            }

            // -----------------------------------------------------------
            // ШАГ 2: Перезаписываем инвентарь (Удаляем старые предметы)
            // -----------------------------------------------------------
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteItemsSql)) {
                deleteStmt.setInt(1, hero.getId());
                deleteStmt.executeUpdate();
            }

            // -----------------------------------------------------------
            // ШАГ 3: Вставляем актуальный список предметов
            // -----------------------------------------------------------
            try (PreparedStatement itemStmt = conn.prepareStatement(insertItemSql)) {
                for (Item item : items) {
                    itemStmt.setInt(1, hero.getId());
                    itemStmt.setString(2, item.getName());
                    itemStmt.setString(3, item.getType().name());
                    itemStmt.setInt(4, item.getValue());
                    itemStmt.addBatch(); // Используем batch для быстрой вставки нескольких предметов
                }
                itemStmt.executeBatch();
            }

            // -----------------------------------------------------------
            // ШАГ 4: ФИКСИРУЕМ ТРАНЗАКЦИЮ (Если все шаг 1-3 прошли без ошибок)
            // -----------------------------------------------------------
            conn.commit();
            System.out.println("✅ Игра успешно и атомарно сохранена в базу данных!");

        } catch (SQLException e) {
            System.err.println("❌ Ошибка при сохранении игры! Откат изменений... " + e.getMessage());

            // -----------------------------------------------------------
            // ШАГ 5: ОТКАТЫВАЕМ ВСЕ ИЗМЕНЕНИЯ при любом сбое
            // -----------------------------------------------------------
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("🔄 Откат (rollback) пройден успешно. Данные в БД не повреждены.");
                } catch (SQLException rollbackEx) {
                    System.err.println("❌ Ошибка при выполнении rollback: " + rollbackEx.getMessage());
                }
            }

        } finally {
            // -----------------------------------------------------------
            // ШАГ 6: Возвращаем соединению базовые настройки и закрываем его
            // -----------------------------------------------------------
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Возвращаем дефолтное поведение
                    conn.close(); // Возвращаем соединение в пул
                } catch (SQLException closeEx) {
                    System.err.println("❌ Ошибка при закрытии соединения: " + closeEx.getMessage());
                }
            }
        }
    }
}