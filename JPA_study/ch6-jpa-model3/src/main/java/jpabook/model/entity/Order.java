package jpabook.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderTable")
public class Order {
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	//Order 1 : Member *
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	//Order 1 : OrderItem *
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	//Order 1 : delivery 1
	@OneToOne
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	
	private Date orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	public void setMember(Member member) {
		if(this.member != null) {
			this.member.getOrders().remove(this);
		}
		this.member = member;
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Member getMember() {
		return member;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", member=" + member + ", orderItems=" + orderItems + ", delivery=" + delivery
				+ ", orderDate=" + orderDate + ", status=" + status + "]";
	}
}
