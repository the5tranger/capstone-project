package ex.microservices.catalogservice.controller;

import ex.microservices.catalogservice.model.Product;
import ex.microservices.catalogservice.repository.ProductRepository;
import ex.microservices.catalogservice.util.ParserCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class LoadDataController {
    @Autowired
    ProductRepository repository;

    @PostMapping("/upload/")
    public String uploadDataFromCsvFile(@RequestParam("file") MultipartFile file) throws Exception {
        List<Product> products = ParserCsv.parse(file.getInputStream());
        repository.saveAll(products);
        return "Catalog data uploaded successfully!";
    }
}
