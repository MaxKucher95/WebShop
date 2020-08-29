package de.hska.iwi.vislab.productcore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Long>{

    Product findByName(String name);

    Product findById(int id);

    List<Product> findAll();

    long deleteById(int id);

    long deleteByName(String name);

}