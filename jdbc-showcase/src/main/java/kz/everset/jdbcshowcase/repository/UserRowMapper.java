package kz.everset.jdbcshowcase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import kz.everset.jdbcshowcase.domain.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new User(
        rs.getLong("id"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        rs.getString("birth_date"),
        rs.getString("email")
    );
  }
}
