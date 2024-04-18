package kz.everset.jdbcshowcase.controller;

import kz.everset.jdbcshowcase.domain.Group;
import kz.everset.jdbcshowcase.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

  private GroupRepository groupRepository;

  @Autowired
  public GroupController(GroupRepository groupRepository) {
    this.groupRepository = groupRepository;
  }

  @PostMapping
  public Group save(@RequestBody Group group) {
    return groupRepository.save(group);
  }

  @GetMapping("/{id}")
  public Group findById(@PathVariable Long id) {
    return groupRepository.findById(id);
  }

}
