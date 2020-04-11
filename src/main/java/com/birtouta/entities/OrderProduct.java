package com.birtouta.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="order_products")
@Data
@ToString
public class OrderProduct implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;

	private String details;

	@Column(name = "quantity", precision = 24, scale = 4)
	private BigDecimal quantity;

	@ManyToOne
	@JoinColumn(name="id_order", referencedColumnName = "id")
	private Order order;

	@ManyToOne
	@JoinColumn(name="id_Product_category", referencedColumnName = "id")
	private ProductCategory productCategory;

	private String note;

	@Column(name = "photo", length = 255)
	private String photo;

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
}
