package kz.everset.jdbcshowcase.controller;

import java.util.List;
import kz.everset.jdbcshowcase.domain.User;
import kz.everset.jdbcshowcase.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final JdbcUserRepository jdbcUserRepository;

  @Autowired
  public UserController(JdbcUserRepository jdbcUserRepository) {
    this.jdbcUserRepository = jdbcUserRepository;
  }

  @PostMapping("/save")
  public User save(@RequestBody User user) {
    jdbcUserRepository.save(user);
    return user;
  }

  @GetMapping("/all")
  public List<User> findAll() {
    return jdbcUserRepository.findAll();
  }

  @PutMapping("/update")
  public User update(@RequestBody User user) {
    System.out.println("Updated rows number: " + jdbcUserRepository.update(user));
    return user;
  }

  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable Long id) {
    System.out.println("Delete rows number: " + jdbcUserRepository.deleteById(id));
  }
}
