package de.hska.iwi.vislab.rolecore;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findById(int id);

    Role findByType(String type);

    List<Role> findAll();

    int deleteById(int id);

    int deleteByType(String type);
}