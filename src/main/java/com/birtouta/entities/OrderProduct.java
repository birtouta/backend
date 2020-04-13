package com.birtouta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    public OrderProduct() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
