package daoimpl;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import dao.Dao;
import javabean.Students;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import util.Jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements Dao<Students> {

    @Override
    public int insert(Students students) {
        String sql = "insert into students values(STU_SEQ.nextval,:name,:num,:sex,:time,:idnumber,:tel,:type,:photo,0)" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(students));
    }

    @Override
    public int delete(int id) {
        String sql = "delete from students where id=:id";
        Students s = new Students();
        s.setId(id);
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(s));
    }

    @Override
    public int update(Students students) {
        String sql = "update students set name=:name,num=:num,sex=:sex,time=:time,idnumber=:idnumber,tel=:tel,type=:type,photo=:photo,send=:send where id=:id" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(students));
    }

    @Override
    public List<Students> selectAll() {
        String sql = "select * from students";
        return Jdbc.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(Students.class));
    }

    @Override
    public List<Students> selectBy(String name,String number) {
        String sql = "select * from students where name=:name or num=:num";
        Map<String,Object> m = new HashMap<String,Object>();
            m.put("name",name);
            m.put("num",number);

        return Jdbc.getJdbcTemplate().query(sql,m,new BeanPropertyRowMapper<>(Students.class));
    }
}
