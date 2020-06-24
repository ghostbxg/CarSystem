package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class Jdbc {
    static NamedParameterJdbcTemplate jdbcTemplate;

    static {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-datasource.xml");
        jdbcTemplate=ac.getBean(NamedParameterJdbcTemplate.class);
    }

    public static NamedParameterJdbcTemplate  getJdbcTemplate() {

        return jdbcTemplate;
    }
}
