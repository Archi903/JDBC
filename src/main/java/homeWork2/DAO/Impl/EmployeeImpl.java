package homeWork2.DAO.Impl;

import homeWork2.City;
import homeWork2.DAO.EmployeeDao;
import homeWork2.Employee;

import java.sql.*;
import java.util.ArrayList;
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
            statement.setObject(5, employee.getCity_id());

            statement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id AND emoloyee_id=(?)")) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()) {

                employee.setId(Integer.parseInt(resultSet.getString("id")));
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
        List<Employee> employeeList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                City city = new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name"));

                employeeList.add(new Employee(id, firstName, secondName, gender, age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public void updateAmountById(int id, int age) {
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE empployee SET age=(?) WHERE employee_id=(?)")) {

            statement.setInt(5, age);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM book WHERE employee_id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
