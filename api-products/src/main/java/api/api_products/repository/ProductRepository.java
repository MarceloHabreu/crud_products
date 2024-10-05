package api.api_products.repository;

import api.api_products.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findAll();

    Optional<Product> findById(Long id); // It will just search the database and tell me if it was found or not!
}
