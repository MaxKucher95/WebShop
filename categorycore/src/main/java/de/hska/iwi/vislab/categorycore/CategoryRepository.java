package de.hska.iwi.vislab.categorycore;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long>{

    Category findByName(String name);

    Category findById(int id);

    List<Category> findAll();

    long deleteById(int id);

    long deleteByName(String name);
}