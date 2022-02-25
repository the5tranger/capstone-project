package ex.microservices.catalogservice.controller;

import ex.microservices.catalogservice.model.Product;
import ex.microservices.catalogservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Product> getById(@RequestParam("id") List<String> ids) {
        return service.getAllById(ids);
    }

    @GetMapping("/{sku}")
    public List<Product> getAllBySku(@PathVariable("sku") String sku) {
        return service.findAllBySku(sku);
    }
}
