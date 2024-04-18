package kz.everset.jdbcshowcase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import kz.everset.jdbcshowcase.domain.Group;
import org.springframework.jdbc.core.RowMapper;

public class GroupRowMapper implements RowMapper<Group> {

  @Override
  public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Group(
        rs.getLong("group_id"),
        rs.getString("name")
    );
  }
}
