package kz.everset.jdbcshowcase.starter;

import java.util.List;
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

  @Autowired // field injection
  @Qualifier("myRepo")
  UserRepository userRepository;

  @Autowired // setter injection
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    createUserTable();

    userRepository.save(
        new User("John", "Doe", "1971.05.09", "john.doe@hotmail.ru"));
    userRepository.save(
        new User("Michael", "Jackson", "1963.03.17", "m.jackson@hotmail.ru"));
    userRepository.save(
        new User("Gabe", "Newel", "1963.03.18", "half.life.three@hotmail.ru"));

    List<User> userList = userRepository.findAll();

    for (User user : userList) {
      System.out.printf("Find user %d. %s%n", user.getId(), userRepository.findById(user.getId()));
    }

    System.out.println("Find non existing user: " + userRepository.findById(1000L));
    System.out.println("Number of users: " + userRepository.count());

    userRepository.deleteById(1L);

    System.out.println("After deleting one user: " + userRepository.findAll());
    System.out.println("Number of users after delete: " + userRepository.count());

    userRepository.deleteById(1234234L);

    System.out.println("After deleting one user: " + userRepository.findAll());
    System.out.println("Number of users after delete: " + userRepository.count());

    userRepository.update(new User(2L, "Michael", "Jackson", "1961.04.20", "mj@gmail.com"));

    System.out.println("User 2 after update: " + userRepository.findById(2L));
    System.out.println("Number of users after delete: " + userRepository.count());
  }

  private void createUserTable() {
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
  }
}
