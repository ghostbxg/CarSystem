package daoimpl;

import dao.Dao;
import javabean.Exam;
import javabean.TakeCar;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import util.Jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamDaoImpl implements Dao<Exam> {
    @Override
    public int insert(Exam exam) {
        String sql = "insert into exam values(EXAM_SEQ.nextval,:subject,:grade,:time,:takecarid)" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(exam));
    }

    @Override
    public int delete(int id) {
        String sql = "delete from exam where id = :id";
        Exam e = new Exam();
        e.setId(id);
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(e));
    }

    @Override
    public int update(Exam exam) {
        String sql = "update exam set time=:time,takecarid=:takecarid,subject =:subject,grade=:grade where id=:id" ;
        return Jdbc.getJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(exam));
    }

    @Override
    public List<Exam> selectAll() {
        String sql = "select e.*,s.name,s.num,t.starttime from students s,exam e,takecar t where s.id=t.stuid and t.id=e.takecarid";
        return Jdbc.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(Exam.class));
    }

    @Override
    public List<Exam> selectBy(String name, String number) {
        String sql = "select e.*,s.name,s.num,t.starttime from students s,exam e,takecar t where s.id=t.stuid and t.id=e.takecarid and s.name=:name";
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("name",name);
        return Jdbc.getJdbcTemplate().query(sql,m,new BeanPropertyRowMapper<>(Exam.class));
    }
}
