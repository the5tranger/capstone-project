package ex.microservices.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(unique = true, name = "uniq_id")
    @JsonProperty("uniq_id")
    private String id;
    private String sku;
    @Column(name = "name_title")
    private String name;
    private String brand;
    @Column(name = "description", length = 10000)
    private String description;
    @Column(name = "list_price")
    private String listPrice;
    private String category;
    @Column(name = "category_tree")
    private String categoryTree;
    @Column(name = "product_url", length = 500)
    private String productUrl;
    @Column(name = "Reviews", length = 11000)
    private String reviews;
    @Column(name = "total_number_reviews")
    private String reviewsCount;
    @Column(name = "average_product_rating")
    private String rating;
    @Column(name = "product_image_urls", length = 5000)
    private String productImageUrl;
    @Column(name = "sale_price")
    private String salePrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

