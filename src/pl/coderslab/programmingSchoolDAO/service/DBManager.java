package pl.coderslab.programmingSchoolDAO.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager {
    private static Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop_2?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    private static Connection createConn() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        return connection;
    }


    /**
     * Execute insert query and return id of created element, if not then null
     *
     * @param query
     * @param params
     * @return id or null
     * @throws SQLException
     */
    public static Integer insertIntoDatabase(String query, List<String> params) throws Exception {

        try (Connection conn = createConn()) {

            String[] generatedColumns = {"id"};
            PreparedStatement pst = conn.prepareStatement(query, generatedColumns);
            if (params != null) {
                int i = 1;
                for (String p : params) {
                    pst.setString(i++, p);
                }
            }
            pst.executeUpdate();

            ResultSet res = pst.getGeneratedKeys();

            if (res.next())
                return res.getInt(1);
            else
                return null;
        } catch (Exception e) {
            throw e;
        }

    }

    public static List<Map<String, String>> getData(String query, List<String> params) throws Exception {

        try (Connection conn = createConn()) {

            //prepare query
            PreparedStatement st = getPreparedStatement(query, params, conn);

            //execute and get results
            ResultSet rs = st.executeQuery();

            //get columns from query
            ResultSetMetaData columns = rs.getMetaData();

            //prepare list of results
            List<Map<String, String>> result = new ArrayList<>();

            while (rs.next()) {

                //New String array for row data
                Map<String, String> row = new HashMap<>();

                for (int j = 1; j <= columns.getColumnCount(); j++) {
                    row.put(columns.getColumnName(j), rs.getString(columns.getColumnName(j)));
                }

                result.add(row);
            }

            return result;
        } catch (Exception e) {
            throw e;
        }

    }

    public static int executeQuery(String query, List<String> params) throws Exception {
        try (Connection conn = createConn()) {
            PreparedStatement st = getPreparedStatement(query, params, conn);
            return st.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    private static PreparedStatement getPreparedStatement(String query, List<String> params, Connection conn) throws Exception {
        //prepare query
        PreparedStatement st = conn.prepareStatement(query);
        if (params != null) {
            int i = 1;
            for (String p : params) {
                st.setString(i++, p);
            }
        }
        return st;
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


}
