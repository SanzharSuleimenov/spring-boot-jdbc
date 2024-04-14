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
  public User findById(Long id) {
    List<User> users = jdbcTemplate.query("""
            select * from my_user m where m.id = ?
            """,
        new UserRowMapper(),
        id);

    if (users.isEmpty()) {
      return null;
    }

    return users.get(0);
  }

  @Override
  public List<User> findAll() {
    return jdbcTemplate.query("select * from my_user", new UserRowMapper());
  }

  @Override
  public int update(User user) {
    return jdbcTemplate.update("""
              update my_user
              set first_name = ?,
                  last_name = ?,
                  birth_date = ?,
                  email = ?
              where id = ?
            """,
        user.getFirstName(), user.getLastName(), user.getBirthDate(),
        user.getEmail(), user.getId()
    );
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("delete my_user where id = ?", id);
  }

  @Override
  public int count() {
    return jdbcTemplate.queryForObject("select count(*) from my_user", Integer.class);
  }
}
