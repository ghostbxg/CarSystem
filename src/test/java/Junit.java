import javabean.Coach;
import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class Junit {


    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-datasource.xml");
        NamedParameterJdbcTemplate jdbcTemplate=ac.getBean(NamedParameterJdbcTemplate.class);
        String sql = "select * from coach";

        System.out.println(jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Coach.class)));
    }

}
