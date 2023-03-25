import homeWork2.DAO.EmployeeDao;
import homeWork2.DAO.Impl.EmployeeImpl;

import java.sql.*;

public class Application2 {
    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "art071093";
        final String url = "jdbc:postgresql://localhost:5432/skypro";


        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            EmployeeDao employeeDao = new EmployeeImpl(connection);
        }

    }
}
