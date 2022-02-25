package ex.microservices.productservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse implements Serializable {
    @JsonProperty("uniq_id")
    private String id;
    private String sku;
    @JsonProperty("name_title")
    private String name;
    private String brand;
    @JsonProperty("description")
    private String description;
    @JsonProperty("list_price")
    private String listPrice;
    private String category;
    @JsonProperty("category_tree")
    private String categoryTree;
    @JsonProperty("product_url")
    private String productUrl;
    @JsonProperty("Reviews")
    private String reviews;
    @JsonProperty("total_number_reviews")
    private String reviewsCount;
    @JsonProperty("average_product_rating")
    private String rating;
    @JsonProperty("product_image_urls")
    private String productImageUrl;
    @JsonProperty("sale_price")
    private String salePrice;
    private boolean inStock;
}
