package ex.microservices.inventoryservice.service;

import ex.microservices.inventoryservice.exception.ItemNotFoundException;
import ex.microservices.inventoryservice.model.InventoryItem;
import ex.microservices.inventoryservice.model.ResponseModel;
import ex.microservices.inventoryservice.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryAvailabilityService {
    @Autowired
    private InventoryItemRepository repository;

    public List<ResponseModel> getAllById(List<String> ids) {
        return mapToResponseModel(repository.findAllByProductCodeIn(ids));
    }

    public List<ResponseModel> getAllBySku(String sku) {
        return mapToResponseModel(repository.findAllBySku(sku));
    }

    private List<ResponseModel> mapToResponseModel(List<InventoryItem> items) {
        if (items.isEmpty()) {
            throw new ItemNotFoundException("Item(s) not found!");
        }
        return items.stream()
                .map(inventoryItem -> new ResponseModel(inventoryItem.getProductCode(),
                        inventoryItem.getSku(),
                        inventoryItem.getAmount() > 0)
                ).collect(Collectors.toList());
    }
}
