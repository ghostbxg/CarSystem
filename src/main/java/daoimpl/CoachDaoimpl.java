package daoimpl;

import dao.Dao;
import javabean.Coach;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import util.Jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CoachDaoimpl implements Dao<Coach> {

    @Override
    public int insert(Coach coach) {
        String sql = "insert into coach values(COACH_SEQ.nextval,:name,:password,:num,:idnumber,:tel,:salary,:type,:time)" ;
       return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(coach));
    }

    @Override
    public int delete(int id) {
        String sql = "delete from coach where id = :id";
        Coach c = new Coach();
        c.setId(id);
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(c));
    }

    @Override
    public int update(Coach coach) {
        String sql = "update coach set name=:name,password=:password,num =:num,idnumber=:idnumber,tel=:tel,salary=:salary,type=:type,time=:time where id=:id" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(coach));
    }

    @Override
    public List<Coach> selectAll() {
        String sql = "select * from coach";
        return Jdbc.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(Coach.class));
    }

    @Override
    public List<Coach> selectBy(String name, String password ){
       String sql = "select * from coach where name = :name and password = :password";
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("name",name);
        m.put("password",password);
        return Jdbc.getJdbcTemplate().query(sql,m,new BeanPropertyRowMapper<>(Coach.class));
    }


}
