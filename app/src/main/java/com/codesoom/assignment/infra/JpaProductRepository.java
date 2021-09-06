package com.codesoom.assignment.infra;

import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.domain.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

@Primary
public interface JpaProductRepository extends ProductRepository, CrudRepository<Product, Long> {

    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(long id);

    void delete(Product product);

    void deleteAll();

    boolean existsById(Long productId);
}
