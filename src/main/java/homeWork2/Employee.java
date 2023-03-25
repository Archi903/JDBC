package homeWork2;

import homeWork2.DAO.EmployeeDao;

import java.util.Objects;

public class Employee {

    private int id;
    private String firstName;
    private String secondName;
    private String gender;
    private int age;
    private City city_id;

    public Employee(){

    }

    public Employee(int id, String firstName, String secondName, String gender, int age, City city_id) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.age = age;
        this.city_id = city_id;
    }
    public Employee(String firstName, String secondName, String gender, int age, City city_id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.age = age;
        this.city_id = city_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public City getCity_id() {
        return city_id;
    }

    public void setCity_id(City city_id) {
        this.city_id = city_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && city_id == employee.city_id && Objects.equals(firstName, employee.firstName) && Objects.equals(secondName, employee.secondName) && Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, gender, age, city_id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", city_id=" + city_id +
                '}';
    }
}
