package com.kh.ex3.cart.entity;

import com.kh.ex3.product.entity.ProductEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_cart_items", indexes = @Index(columnList = "cart_cno"))
@Getter
@ToString(exclude = {"product", "cart"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProductEntity product;
	
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CartEntity cart;
	
	public void changeQuantity(int quantity) {
		this.quantity = quantity;
	}
}
