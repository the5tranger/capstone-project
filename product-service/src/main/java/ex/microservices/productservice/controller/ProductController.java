package ex.microservices.productservice.controller;

import ex.microservices.productservice.exceptions.NotFoundException;
import ex.microservices.productservice.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@CircuitBreaker(name = "productService", fallbackMethod = "getException")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping

    public ResponseEntity<?> getAllByIdIn(@RequestParam("id") List<String> ids) {
        return service.getAllByIdIn(ids);
    }

    public ResponseEntity<?> getException (Exception e) {
        return e instanceof NotFoundException ? new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> getAllBySku(@PathVariable String sku) {
        return service.getAllBySku(sku);
    }

}
