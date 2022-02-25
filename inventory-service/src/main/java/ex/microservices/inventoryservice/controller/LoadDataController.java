package ex.microservices.inventoryservice.controller;

import ex.microservices.inventoryservice.model.InventoryItem;
import ex.microservices.inventoryservice.repository.InventoryItemRepository;
import ex.microservices.inventoryservice.util.ParserCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class LoadDataController {
    @Autowired
    InventoryItemRepository repository;

    @PostMapping("/upload/")
    public String uploadDataFromCsvFile(@RequestParam("file") MultipartFile file) throws Exception {
        List<InventoryItem> items = ParserCsv.parse(file.getInputStream());
        repository.saveAll(items);
        return "Inventory data uploaded successfully!";
    }
}
