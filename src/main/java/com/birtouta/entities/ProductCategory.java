package com.birtouta.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="prodcut_categories")
@Data
@ToString
@JsonIgnoreProperties({"orderProducts"})
public class ProductCategory implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;
	private String translatedFr;
	private String translatedEn;
	private String translatedKb;

	@OneToMany(mappedBy="productCategory" , fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderProduct> orderProducts;

	public ProductCategory() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductCategory that = (ProductCategory) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
