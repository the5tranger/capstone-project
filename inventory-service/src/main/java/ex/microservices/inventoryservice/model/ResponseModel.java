package ex.microservices.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel {
    @JsonProperty("uniq_id")
    private String productCode;
    private String sku;
    private boolean inStock = false;
}
