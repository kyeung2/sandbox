package io.flyingnimbus;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static final String CONNECTION_STRING = "jdbc:postgresql://127.0.0.1:5432/postgres";

    public static void main(String[] args) {
        // https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description
        // auto java.sql.Driver discovery -- no longer need to load a java.sql.Driver class via Class.forName

        // register JDBC driver, optional, since java 1.6
        /*try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // auto close connection
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, "postgres", "example")) {

            insertExample(conn);
            List<Long> ids = selectExample(conn);
            updateExample(conn, ids.get(ids.size() - 1));
            selectExample(conn);
            deleteExample(conn, ids.get(ids.size() - 1));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s%n", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Long> selectExample(Connection conn) throws SQLException {

        System.out.println("SELECT rows");
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Account");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Long> ids = new ArrayList<>();
        while (resultSet.next()) {

            long id = resultSet.getLong("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            Timestamp createdDate = resultSet.getTimestamp("created_on");
            System.out.println(id);
            System.out.println(username);
            System.out.println(password);
            System.out.println(email);
            System.out.println(createdDate);
            ids.add(id);
        }
        System.out.println();
        return ids;
    }

    private static void insertExample(Connection conn) throws SQLException {
        System.out.println("INSERT a row");
        // PreparedStatement, compiled once, db caching, protects by escaping values. In comparison to Statement
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Account (username,password, email, created_on) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, "my name");
        preparedStatement.setString(2, "my password");
        preparedStatement.setString(3, "my@email.com");
        preparedStatement.setTimestamp(4, new Timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
        int rowsUpdated = preparedStatement.executeUpdate();
        System.out.printf("Rows updated: %s%n%n", rowsUpdated);
    }

    private static void updateExample(Connection conn, Long id) throws SQLException {
        System.out.println("UPDATE a row");
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Account SET username = ? WHERE user_id = ?");
        preparedStatement.setString(1, "!!UPDATED USERNAME!!");
        preparedStatement.setLong(2, id);
        int rowsUpdated = preparedStatement.executeUpdate();
        System.out.printf("Rows updated: %s%n%n", rowsUpdated);
    }

    private static void deleteExample(Connection conn, Long id) throws SQLException {
        System.out.println("DELETE a row");
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Account WHERE user_id = ?");
        preparedStatement.setLong(1, id);
        int rowsUpdated = preparedStatement.executeUpdate();
        System.out.printf("Rows updated: %s%n%n", rowsUpdated);

    }
}