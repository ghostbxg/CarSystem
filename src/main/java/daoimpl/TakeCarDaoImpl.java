package daoimpl;

import dao.Dao;
import javabean.TakeCar;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import util.Jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TakeCarDaoImpl implements Dao<TakeCar> {


    @Override
    public int insert(TakeCar takeCar) {
        String sql = "insert into takecar values(TC_SEQ.nextval,:stuid,:carid,:type,:starttime,:endtime,:time)" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(takeCar));
    }

    @Override
    public int delete(int id) {
        String sql = "delete from takecar where id = :id";
        TakeCar t = new TakeCar();
        t.setId(id);
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(t));
    }

    @Override
    public int update(TakeCar takeCar) {
        String sql = "update takecar set stuid=:stuid,carid=:carid,type =:type,starttime=:starttime,endtime=:endtime,time=:time where id=:id" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(takeCar));
    }

    @Override
    public List<TakeCar> selectAll() {
        String sql = "select t.*,s.name,s.num,c.lpnumber from takecar t,students s,car c where t.stuid=s.id and c.id=t.carid";
        return Jdbc.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(TakeCar.class));
    }

    @Override
    public List<TakeCar> selectBy(String lpnumber, String stuid) {
        String sql = "select distinct t.*,s.name,s.num,c.lpnumber from takecar t,students s,car c where t.stuid=s.id and c.id=t.carid ";
        Map<String,Object> m = new HashMap<String,Object>();
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");

        if(pattern.matcher(lpnumber).matches()){
            sql+="and c.id=:cid";
            m.put("cid",Integer.parseInt(lpnumber));
        }else{
            sql+="and c.lpnumber=:lpnumber ";
            m.put("lpnumber",lpnumber);
            m.put("cid",0);
        }
        if(stuid.equals("0")){

        }else{
            sql+="and t.stuid=:stuid";
            m.put("stuid",Integer.parseInt(stuid));
        }

        return Jdbc.getJdbcTemplate().query(sql,m,new BeanPropertyRowMapper<>(TakeCar.class));
    }
}
