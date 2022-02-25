package ex.microservices.inventoryservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "inventory")
public class InventoryItem {
    @Id
    @Column(name = "uniq_id", nullable = false, unique = true)
    private String productCode;
    @Column(name = "sku")
    private String sku;
    @Column(name = "amount")
    private Integer amount = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InventoryItem that = (InventoryItem) o;
        return productCode != null && Objects.equals(productCode, that.productCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}