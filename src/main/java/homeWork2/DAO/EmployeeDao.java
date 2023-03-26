package homeWork2.DAO;

import homeWork2.Employee;

import java.util.List;

public interface EmployeeDao {

    void create (Employee employee);
    Employee readById(int id);
    List<Employee> readAll();
    void updateAmountById (int id, int amount);
    void deleteById (int id);
}
