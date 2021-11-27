package src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String SERVER = "quamar.servegame.com";
    private static final String PORT = "3306";
    private static final String DATA_BASE = "Departamento";
    private static final String USER = "USJT_user_default";
    private static final String PASSWORD = "adminUSJT";
//    private static final String SERVER = "localhost";
//    private static final String PORT = "3306";
//    private static final String DATA_BASE = "Departamento";
//    private static final String USER = "root";
//    private static final String PASSWORD = "alexrock007";
    private static Connection conn;

    private ConexaoBD() {}

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null || conn.isClosed()) {
            conn = connect();
        }
        return conn;
    }

    private static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATA_BASE + "?user=" + USER + "&password=" + PASSWORD + "&useTimezone=true&serverTimezone=America/Sao_Paulo";
        System.out.println(connectionURL);
        return DriverManager.getConnection(connectionURL);
    }

}