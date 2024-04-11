package kz.everset.jdbcshowcase.repository;

import java.util.List;
import kz.everset.jdbcshowcase.domain.User;

public interface UserRepository {

  int save(User user);

  int update(User user);

  int deleteById(Long id);

  int count();

  User findById(Long id);

  List<User> findAll();
}
