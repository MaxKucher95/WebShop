package de.hska.iwi.vislab.usercore;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    List<User> findAll();

    User findById(int id);

    long deleteById(int id);

    long deleteByUsername(String username);
}