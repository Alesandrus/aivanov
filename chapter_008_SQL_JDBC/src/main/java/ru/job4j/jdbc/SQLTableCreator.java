package ru.job4j.jdbc;

import java.sql.*;

public class SQLTableCreator {
    private int maxCount;
    private String url;
    private String userName;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String serverUrl) {
        this.url = "jdbc:postgresql://" + serverUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    public void insertRowsFunc(Connection connection) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS TEST");
            st.executeUpdate("CREATE TABLE TEST (FIELD INT)");
            st.execute("CREATE OR REPLACE FUNCTION insertCount(n INTEGER) RETURNS VOID AS $$\n" +
                    "DECLARE c INTEGER := 1;\n" +
                    "BEGIN\n" +
                    "  LOOP\n" +
                    "    IF c > n THEN\n" +
                    "      EXIT;\n" +
                    "    END IF;\n" +
                    "    INSERT INTO test (field) VALUES (c);\n" +
                    "    c := c + 1;\n" +
                    "  END LOOP;\n" +
                    "END;\n" +
                    "$$ LANGUAGE plpgsql;");
            PreparedStatement statement = connection.prepareStatement("SELECT insertcount(?)");
            statement.setInt(1, maxCount);
            statement.execute();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void insertRowsBatch(Connection con) {
        try {
            boolean autoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS TEST");
            statement.executeUpdate("CREATE TABLE TEST (FIELD INT)");
            String command = "INSERT INTO test (field) VALUES (%s)";
            for (int i = 1; i <= maxCount; i++) {
                statement.addBatch(String.format(command, i));
            }
            int[] counts = statement.executeBatch();
            if (counts.length != maxCount) {
                con.rollback();
            } else {
                con.commit();
            }
            con.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String myURL = "localhost:5432/sqlite";
        String myUser = "postgres";
        String myPassword = "shelby";
        int count = 1_000_000;

        SQLTableCreator tableCreator = new SQLTableCreator();
        tableCreator.setUrl(myURL);
        tableCreator.setUserName(myUser);
        tableCreator.setPassword(myPassword);
        tableCreator.setMaxCount(count);

        XMLCreator xmlCreator = new XMLCreator();
        xmlCreator.setTableName("test");
        xmlCreator.setFieldName("field");

        XMLTransformer transformer = new XMLTransformer();
        XMLSumParser sumParser = new XMLSumParser();
        try (Connection con = tableCreator.getConnection()) {
            tableCreator.insertRowsFunc(con);
            //tableCreator.insertRowsBatch(con);

            //xmlCreator.createXMLwithDOM(con);
            //xmlCreator.createXMLwithStAX(con);
            //xmlCreator.createXMLwithJDOM(con);
            xmlCreator.createXMLwithDOM4J(con);

            transformer.transformWithXSLT();
            System.out.println(sumParser.sunAndParseStAX("D:\\Java\\aivanov\\chapter_008_SQL_JDBC\\src\\main\\java\\ru\\job4j\\jdbc\\2.xml"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }
}
