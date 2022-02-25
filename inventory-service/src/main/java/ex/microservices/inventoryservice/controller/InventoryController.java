package ex.microservices.inventoryservice.controller;

import ex.microservices.inventoryservice.service.InventoryAvailabilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
@Slf4j
public class InventoryController {
    private final InventoryAvailabilityService service;

    @Autowired
    public InventoryController(InventoryAvailabilityService service) {
        this.service = service;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> getAllBySku(@PathVariable String sku) {
        log.info("Finding inventory by sku: " + sku);
        return new ResponseEntity<>(service.getAllBySku(sku), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllByIds(@RequestParam("id") List<String> ids) {
        log.info("Finding inventory for product code :" + ids.toString());
        return new ResponseEntity<>(service.getAllById(ids), HttpStatus.OK);
    }
}
