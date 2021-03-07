package de.shootloose.utils;

import java.sql.*;
import java.util.Arrays;

public class SQLHelper {
    private static Connection conn = null;

    public static void connect() {
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";//"com.mysql.jdbc.Driver";

        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection("jdbc:mysql://localhost", "Zensiert", "zensiert");
            System.out.println(STATICS.getConsole()[5]);

        } catch (Exception e1) {
            try {
                Class.forName(jdbcDriver);
                conn = DriverManager.getConnection("jdbc:mysql://url", "Zensiert", "zensiert");
                System.out.println("SUCCESSFULLY CONNECTED TO DATABASE!");

            } catch (Exception e) {
                System.out.println(STATICS.getConsole()[6]);
                e1.printStackTrace();
            }
        }
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public static void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
            System.out.println(STATICS.getConsole()[7]);
        } catch (Exception e) {
            System.out.println(STATICS.getConsole()[8]);
            e.printStackTrace();
        }
    }

    public static void insert(String table, String[] fields, Object[] values) throws SQLException {
        String stmtString = "INSERT INTO " + table + Arrays.toString(fields).replace("[", "(").replace("]", ")")
                + " VALUES(";
        for (@SuppressWarnings("unused")
                Object v : values) {
            stmtString += "?,";
        }

        stmtString = stmtString.substring(0, stmtString.length() - 1) + ") ";

        PreparedStatement stmt = prepareStatement(stmtString);
        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String")) {
                stmt.setString(i++, (String) value);
            } else {
                stmt.setLong(i++, (long) value);
            }
        }
        stmt.execute();
    }

    public static void delete(String table, String where, Object[] values) throws SQLException {
        PreparedStatement stmt;
        stmt = prepareStatement("DELETE FROM " + table + " WHERE " + where);
        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String")) {
                stmt.setString(i++, (String) value);
            } else {
                stmt.setLong(i++, (long) value);
            }
        }
        stmt.execute();
    }

    public static ResultSet selectAll(String table, String where, Object[] values)
            throws NumberFormatException, SQLException {
        PreparedStatement stmt;
        stmt = prepareStatement("SELECT * FROM " + table + " WHERE " + where);
        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String")) {
                stmt.setString(i++, (String) value);
            } else {
                stmt.setLong(i++, (long) value);
            }
        }
        return stmt.executeQuery();
    }

    public static ResultSet selectAll(String table) throws SQLException {
        PreparedStatement stmt;
        stmt = prepareStatement("SELECT * FROM " + table);
        System.out.println();
        return stmt.executeQuery();
    }

    public static void update(String fields[], String table, String where, Object[] values) throws SQLException {
        PreparedStatement stmt;
        stmt = prepareStatement("UPDATE " + table + " SET "
                + Arrays.toString(fields).replace("[", "").replace("]", " = ?").replace(",", "= ? ,") + " WHERE " + where);
        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String")) {
                stmt.setString(i++, (String) value);
            } else {
                stmt.setLong(i++, (long) value);
            }
        }
        stmt.execute();
    }

    public static ResultSet select(String fields[], String table, String where, Object[] values) throws SQLException {
        PreparedStatement stmt;
        stmt = prepareStatement("SELECT " + Arrays.toString(fields).replace("[", "").replace("]", "") + " FROM " + table
                + " WHERE " + where);
        int i = 1;
        for (Object value : values) {
            if (value.getClass().getName().equals("java.lang.String")) {
                stmt.setString(i++, (String) value);
            } else {
                stmt.setLong(i++, (long) value);
            }
        }
        return stmt.executeQuery();
    }

    public static ResultSet select(String fields[], String table) throws SQLException {
        PreparedStatement stmt;
        stmt = prepareStatement(
                "SELECT " + Arrays.toString(fields).replace("[", "").replace("]", "") + " FROM " + table);
        return stmt.executeQuery();
    }

    public static ResultSet select(String field, String table) throws SQLException {
        PreparedStatement stmt;
        stmt = prepareStatement("SELECT " + field + " FROM " + table);
        return stmt.executeQuery();
    }

    public static void setActive(Long guildID, String system, boolean isActive) {
        long activeLong = isActive ? 1 : 0;

        try {
            PreparedStatement stmt;
            stmt = prepareStatement("UPDATE activeSystems SET isActive= ? WHERE guildID= ? AND system= ?");
            stmt.setLong(1, activeLong);
            stmt.setLong(2, guildID);
            stmt.setString(3, system);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean getActive(Long guildID, String system) {
        long isActive = 2;

        try {
            PreparedStatement stmt;
            stmt = prepareStatement("SELECT isActive FROM activeSystems WHERE guildID= ? AND system= ?");
            stmt.setLong(1, guildID);
            stmt.setString(2, system);
            stmt.execute();

            ResultSet resultSet = stmt.executeQuery();

            resultSet.next();
            isActive = resultSet.getLong("isActive");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isActive == 1;
    }

    public static void setInitial(Long guildID, String system, boolean isActive) {
        long activeLong = isActive ? 1 : 0;

        try {
            PreparedStatement stmt;
            stmt = prepareStatement("INSERT INTO activeSystems(guildID, system, isActive) VALUES(?, ?, ?)");
            stmt.setLong(1, guildID);
            stmt.setString(2, system);
            stmt.setLong(3, activeLong);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
