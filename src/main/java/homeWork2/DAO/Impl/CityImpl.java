package homeWork2.DAO.Impl;

import homeWork2.City;
import homeWork2.DAO.CityDao;
import homeWork2.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityImpl implements CityDao {

    public Connection connection;

    public CityImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(City city) {
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO city (city_name,city_id) VALUES ((?), (?))")){
            statement.setInt(1, city.getId());
            statement.setString(2, city.getCity());

            statement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public City read(int id) {
        City city = new City();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM city ON city_id=city.city_id AND city_id=(?)")) {

            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()) {

                city.setId(Integer.parseInt(resultSet.getString("id")));
                city.setCity(resultSet.getString("city_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> readAll() {
        List<City> cityList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM city ON city_id=city.city_id")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("city_id"));
                String cityName = resultSet.getString("city_name");

                cityList.add(new City(id, cityName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityList;
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM city WHERE city_id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
