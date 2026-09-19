package services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();

        // 1. Параметры подключения к PostgreSQL
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/palace_game");
        config.setUsername("postgres");
        config.setPassword("your_password");
        config.setDriverClassName("org.postgresql.Driver");

        // 2. Настройки производительности и размера пула
        config.setMaximumPoolSize(10);        // Максимум 10 одновременно открытых соединений
        config.setMinimumIdle(2);             // Минимум 2 фоновых готовых соединения
        config.setIdleTimeout(300000);        // 5 минут на закрытие простаивающего соединения
        config.setConnectionTimeout(30000);   // Максимум 30 сек ожидания свободного соединения из пула
        config.setMaxLifetime(1800000);       // 30 минут максимальное время жизни соединения в пуле

        // Optimizations recommended for PostgreSQL
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    /**
     * Возвращает свободное соединение из пула HikariCP.
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Закрывает сам пул соединений при завершении работы приложения.
     */
    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("🔌 Пул соединений HikariCP успешно закрыт.");
        }
    }
}