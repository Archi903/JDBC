import homeWork2.City;
import homeWork2.DAO.CityDao;
import homeWork2.DAO.EmployeeDao;
import homeWork2.DAO.Impl.CityImpl;
import homeWork2.DAO.Impl.EmployeeImpl;
import homeWork2.Employee;

import java.sql.*;

public class Application{



    public static void main(String[] args) throws SQLException  {

        final String user = "postgres";
        final String password = "art071093";
        final String url = "jdbc:postgresql://localhost:5432/skypro";


        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {

            statement.setInt(1, 4);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String first_name = "Имя: " + resultSet.getString("first_name");
                String last_name = "Фамилия: " + resultSet.getString("last_name");
                String gender = "Пол: " + resultSet.getString("gender");
                int age = resultSet.getInt(5);
                int city_id = resultSet.getInt(6);

                System.out.println(first_name);
                System.out.println(last_name);
                System.out.println(gender);
                System.out.println("Возраст: " + age);
                System.out.println("id города: " + city_id);

            }

            final EmployeeDao EMPLOYEE_DAO = new EmployeeImpl(connection);
            final CityDao CITY_DAO = new CityImpl(connection);
            CITY_DAO.create(new City(1,"Moscow"));
            EMPLOYEE_DAO.create(new Employee("eddie", "Murh", "Male", 24, new City(1,"Moscow")));
        }

    }
}
