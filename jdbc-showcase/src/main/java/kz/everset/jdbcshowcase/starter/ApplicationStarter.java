package kz.everset.jdbcshowcase.starter;

import kz.everset.jdbcshowcase.domain.User;
import kz.everset.jdbcshowcase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStarter implements CommandLineRunner {

  JdbcTemplate jdbcTemplate;

  @Autowired
  @Qualifier("myRepo")
  UserRepository userRepository;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    jdbcTemplate.execute("DROP TABLE IF EXISTS my_user");
    jdbcTemplate.execute("""
        CREATE TABLE my_user(
          id SERIAL,
          first_name varchar(255),
          last_name varchar(255),
          birth_date varchar(255),
          email varchar(255)
        )
        """);

    userRepository.save(
        new User("John", "Doe", "1971.05.09", "john.doe@hotmail.ru"));

    System.out.println("1: "+ userRepository.findAll());

    userRepository.save(
        new User("Michael", "Jackson", "1963.03.17", "m.jackson@hotmail.ru"));
    userRepository.save(
        new User("Gabe", "Newel", "1963.03.18", "half.life.three@hotmail.ru"));

    System.out.println("2: "+ userRepository.findAll());
  }
}
