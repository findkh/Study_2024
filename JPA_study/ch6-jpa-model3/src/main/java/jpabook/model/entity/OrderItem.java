package jpabook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice;
	private int count;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public int getOrderPrice() {
		return orderPrice;
	}
	
	public void setOrderPrice(int buyPrice) {
		this.orderPrice = buyPrice;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", item=" + item + ", order=" + order + ", orderPrice=" + orderPrice + ", count="
				+ count + "]";
	}
}
