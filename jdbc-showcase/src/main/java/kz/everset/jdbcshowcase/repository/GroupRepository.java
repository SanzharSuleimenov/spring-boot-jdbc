package kz.everset.jdbcshowcase.repository;

import kz.everset.jdbcshowcase.domain.Group;

public interface GroupRepository {

  Group save(Group group);

  Group findById(Long id);
}
