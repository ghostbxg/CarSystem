package daoimpl;

import dao.Dao;
import javabean.Pay;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import util.Jdbc;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayDaoImpl implements Dao<Pay> {

    @Override
    public int insert(Pay pay) {
        String sql = "insert into pay values(TC_SEQ.nextval,:stuid,:type,:money,:memoney,:summoney,:time)" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(pay));
    }

    @Override
    public int delete(int id) {
        String sql = "delete from pay where id = :id";
        Pay p = new Pay();
        p.setId(id);
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(p));
    }

    @Override
    public int update(Pay pay) {
        String sql = "update pay set stuid=:stuid,type =:type,money=:money,summoney=:summoney,summoney=:summoney,time=:time where id=:id" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(pay));
    }

    @Override
    public List<Pay> selectAll() {
        String sql = "select p.*,s.name,s.num from pay p,students s where p.stuid=s.id";
        return Jdbc.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(Pay.class));
    }

    @Override
    public List<Pay> selectBy(String name, String number) {
        String sql = "select p.*,s.name,s.num from pay p,students s where p.stuid=s.id and s.name=:name";
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("name",name);
        return Jdbc.getJdbcTemplate().query(sql,m,new BeanPropertyRowMapper<>(Pay.class));
    }
}
