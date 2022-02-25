package ex.microservices.inventoryservice.repository;

import ex.microservices.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findAllBySku(String sku);
    List<InventoryItem> findAllByProductCodeIn(List<String> ids);
}
