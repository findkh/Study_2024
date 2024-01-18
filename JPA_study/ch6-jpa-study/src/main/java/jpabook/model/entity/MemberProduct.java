package jpabook.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(MemberProductId.class)
public class MemberProduct {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member; //MemberProductId.memeber와 연결
	
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product; //MemberProductId.product와 연결
	
	private int orderAmout;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderAmout() {
		return orderAmout;
	}

	public void setOrderAmout(int orderAmout) {
		this.orderAmout = orderAmout;
	}

	@Override
	public String toString() {
		return "MemberProduct [member=" + member + ", product=" + product + ", orderAmout=" + orderAmout + "]";
	}

	
}
