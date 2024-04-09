package kz.everset.jdbcshowcase.repository;

import java.util.List;
import kz.everset.jdbcshowcase.domain.User;

public interface UserRepository {

  int save(User user);

  List<User> findAll();
}
