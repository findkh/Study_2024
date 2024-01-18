package jpabook.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class MemberProductId implements Serializable{
	private String member;
	
	private String product;

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(member, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberProductId other = (MemberProductId) obj;
		return Objects.equals(member, other.member) && Objects.equals(product, other.product);
	}

	@Override
	public String toString() {
		return "MemberProductId [member=" + member + ", product=" + product + "]";
	}
}
