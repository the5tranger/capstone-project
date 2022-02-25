package ex.microservices.catalogservice.repository;

import ex.microservices.catalogservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllBySku(String sku);

    List<Product> findAllByIdIn(List<String> ids);
}
