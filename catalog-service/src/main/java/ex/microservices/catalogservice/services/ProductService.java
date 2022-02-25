package ex.microservices.catalogservice.services;

import ex.microservices.catalogservice.exceptions.ProductNotFoundException;
import ex.microservices.catalogservice.model.Product;
import ex.microservices.catalogservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllById(List<String> ids) {
        List<Product> products = repository.findAllByIdIn(ids);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Product(s) with id: " + ids.toString() + " not found in data!");
        }
        return products;
    }

    public List<Product> findAllBySku(String sku) {
        List<Product> products = repository.findAllBySku(sku);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Product(s) with sku: " + sku + " not found in data!");
        }
        return products;
    }
}
