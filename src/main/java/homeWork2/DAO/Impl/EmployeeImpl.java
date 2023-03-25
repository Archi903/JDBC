package homeWork2.DAO.Impl;

import homeWork2.City;
import homeWork2.DAO.EmployeeDao;
import homeWork2.Employee;

import java.sql.*;
import java.util.List;

public class EmployeeImpl implements EmployeeDao {

    public Connection connection;

    public EmployeeImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {

        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))")){
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getSecondName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity_id());

            statement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM book INNER JOIN employee ON employee.city_id=city.city_id AND emoloyee_id=(?)")) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()) {

                employee.setId(Integer.parseInt(resultSet.getString("employee_id")));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setSecondName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity_id(new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        return null;
    }

    @Override
    public void updateAmountById(int id, int amount) {

    }

    @Override
    public void deleteById(int id) {

    }
}
