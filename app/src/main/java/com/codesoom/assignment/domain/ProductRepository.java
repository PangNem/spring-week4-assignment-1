package com.codesoom.assignment.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * 상품 저장소.
 */
@RepositoryDefinition(domainClass = Product.class, idClass = Long.class)
public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(long id);

    void delete(Product product);

    void deleteAll();

    boolean existsById(Long productId);
}
