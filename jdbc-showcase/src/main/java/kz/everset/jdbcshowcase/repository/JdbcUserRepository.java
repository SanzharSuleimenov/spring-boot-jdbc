package kz.everset.jdbcshowcase.repository;

import java.util.List;
import kz.everset.jdbcshowcase.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("myRepo")
public class JdbcUserRepository implements UserRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired // DI
  public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int save(User user) {
    return jdbcTemplate.update("""
        insert into my_user(first_name, last_name, birth_date, email)
        values(?,?,?,?)
        """, user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getEmail());
  }

  @Override
  public List<User> findAll() {
    return jdbcTemplate.query("select * from my_user", new UserRowMapper());
  }
}
