package main.java;

import java.sql.*;

public class Replication {

    private static final String SCHEMA_TABLE_FOR_HISTORY_TABLE_NAME = "logging.history";
    private static final String SCHEMA_TABLE_FOR_MAIN_TABLE_NAME = "logging.main";
    private static final String SCHEMA_TABLE_FOR_COPY_TABLE_NAME = "logging.copy";
    private static final String GET_NOT_EXECUTED_QUERIES = "SELECT * FROM " + SCHEMA_TABLE_FOR_HISTORY_TABLE_NAME + " WHERE flag = false ORDER BY id_time ASC";

    private static void updateQuery() throws SQLException, ClassNotFoundException, InterruptedException {
        String query;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        while (true) {
            ps = connection.prepareStatement(GET_NOT_EXECUTED_QUERIES);
            rs = ps.executeQuery();
            while (rs.next()) {
                query = rs.getString(2).replace(SCHEMA_TABLE_FOR_MAIN_TABLE_NAME, SCHEMA_TABLE_FOR_COPY_TABLE_NAME);
                ps = connection.prepareStatement(query);
                ps.executeUpdate();
                Timestamp id_time = rs.getTimestamp(1);
                ps = connection.prepareStatement("UPDATE " + SCHEMA_TABLE_FOR_HISTORY_TABLE_NAME + " SET flag = true WHERE id_time = ?");
                ps.setTimestamp(1, id_time);
                ps.executeUpdate();
            }
            Thread.sleep(10000);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        updateQuery();
    }
}
