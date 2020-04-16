package com.birtouta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_products")
@NamedQueries({
        @NamedQuery(name = "OrderProduct.findAllProductsByOrder", query = "SELECT p FROM OrderProduct p where p.order.id =:id_order")
})
@JsonIgnoreProperties({"order", "productCategory"})
@Data
public class OrderProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String details;

    @Column(name = "quantity", precision = 24, scale = 4)
    private BigDecimal quantity;

    private String note;

    @Column(name = "photo", length = 255)
    private String photo;
    
    @ManyToOne
    @JoinColumn(name = "id_order")
    @JsonBackReference(value="order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_Product_category")
    @JsonBackReference(value="productCategory")
    private ProductCategory productCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
