package homeWork2.DAO;

import homeWork2.City;

import java.util.List;

public interface CityDao {
    void create(City city);

    City read(int id);

    List<City> readAll();

    void delete(int id);
}

