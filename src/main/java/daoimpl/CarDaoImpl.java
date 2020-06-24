package daoimpl;

import dao.Dao;
import javabean.Car;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import util.Jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarDaoImpl implements Dao<Car> {
    @Override
    public int insert(Car car) {
        String sql = "insert into car values(CAR_SEQ.nextval,:lpnumber,:mtype,:brand,:enumber,:time)" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(car));
    }

    @Override
    public int delete(int id) {
        String sql = "delete from car where id = :id";
        Car c = new Car();
        c.setId(id);
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(c));
    }

    @Override
    public int update(Car car) {
        String sql = "update car set lpnumber=:lpnumber,mtype=:mtype,brand =:brand,enumber=:enumber,time=:time where id=:id";
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(car));
    }

    @Override
    public List<Car> selectAll() {
        String sql = "select * from car";
        return Jdbc.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(Car.class));
    }

    @Override
    public List<Car> selectBy(String lpnumber, String number) {
        String sql = "select * from car where lpnumber=:lpnumber";
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("lpnumber",lpnumber);
        return Jdbc.getJdbcTemplate().query(sql,m,new BeanPropertyRowMapper<>(Car.class));
    }
}
