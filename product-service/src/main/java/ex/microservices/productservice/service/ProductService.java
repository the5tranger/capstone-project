package ex.microservices.productservice.service;

import com.ctc.wstx.util.StringUtil;
import ex.microservices.productservice.exceptions.NotFoundException;
import ex.microservices.productservice.model.ProductResponse;
import ex.microservices.productservice.service.handler.CallsTypes;
import ex.microservices.productservice.service.handler.UrlHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private final RestTemplate restTemplate;
    //TODO: get urls from properties
    private final String CATALOG_URL = "http://catalog-service/api/v1/products";
    private final String INVENTORY_URL = "http://inventory-service/api/v1/inventory";

    private ResponseEntity<ProductResponse[]> items;
    private ResponseEntity<ProductResponse[]> products;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> getAllByIdIn(List<String> ids) {
        if (ids.isEmpty()) {
            throw new RuntimeException("id(s) list is empty");
        }

        String params =  ids.toString().replace("[", "")
                .replace("]", "").trim();
        try {
            products = doCall(UrlHandler.handle(CATALOG_URL, params, CallsTypes.ID));
            items = doCall(UrlHandler.handle(INVENTORY_URL, params, CallsTypes.ID));
            return getResponseEntity();
        } catch (HttpClientErrorException notFoundException) {
            log.error("Unable to get inventory level for id(s): " + ids +
                    ", StatusCode: " + products.getStatusCode());
            throw new NotFoundException("Can't found products in service(s)!");
        }
    }

    public ResponseEntity<?> getAllBySku(String sku) {
        if (sku == null || sku.isEmpty()) {
            throw new RuntimeException("id(s) list is empty");
        }

        try {
            products = doCall(UrlHandler.handle(CATALOG_URL, sku, CallsTypes.SKU));
            items = doCall(UrlHandler.handle(INVENTORY_URL, sku, CallsTypes.SKU));
            return getResponseEntity();
        } catch (HttpClientErrorException notFoundException) {
            log.error("Unable to get inventory level for sku: " + sku +
                    ", StatusCode: " + products.getStatusCode());
            throw new NotFoundException("Can't found products in service(s)!");
        }

    }

    private ResponseEntity<ProductResponse[]> doCall(String url) {
        return restTemplate.getForEntity(url,
                ProductResponse[].class);
    }

    private ResponseEntity<?> getResponseEntity() {
        return new ResponseEntity<>(buildResponse(Arrays.asList(Objects.requireNonNull(items.getBody())),
                Arrays.asList(Objects.requireNonNull(products.getBody()))), HttpStatus.OK);
    }

    private List<ProductResponse> buildResponse(List<ProductResponse> items, List<ProductResponse> products) {
        List<ProductResponse> result = new ArrayList<>();

        for (ProductResponse item : items) {
            Optional<ProductResponse> first = products.stream()
                    .filter(product -> item.isInStock() && item.getId().equals(product.getId()))
                    .findFirst();
            first.ifPresent(e -> {
                e.setInStock(true);
                result.add(e);
            });
        }

        return result;
    }
}
