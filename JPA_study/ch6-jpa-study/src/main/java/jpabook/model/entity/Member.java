package jpabook.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Member {

	//다대다 양방향
	@Id @Column(name = "member_id")
	private String id;

	private String username;
	
	//역방향
	@OneToMany(mappedBy = "member")
	private List<MemberProduct> memberProducts;

	@Override
	public String toString() {
		return "Member [id=" + id + ", memberProducts=" + memberProducts + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<MemberProduct> getMemberProducts() {
		return memberProducts;
	}

	public void setMemberProducts(List<MemberProduct> memberProducts) {
		this.memberProducts = memberProducts;
	}

	
	
	
	
//	//다대다 단방향
//	@Id @Column(name = "member_id")
//	private String id;
//	
//	private String username;
//	
//	@ManyToMany
//	@JoinTable(name = "member_product", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
//	private List<Product> products = new ArrayList<Product>();
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//	@Override
//	public String toString() {
//		return "Member [id=" + id + ", username=" + username + ", products=" + products + "]";
//	}
//	
//	
//	
	
	
//	@Id @GeneratedValue
//	@Column(name = "member_id")
//	private Long id;
//	
//	private String username;
//	
//	@OneToOne
//	@JoinColumn(name = "locker_id")
//	private Locker locker;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public Locker getLocker() {
//		return locker;
//	}
//
//	public void setLocker(Locker locker) {
//		this.locker = locker;
//	}
//	
	
	
//	@Id
//	@GeneratedValue
//	@Column(name = "member_id")
//	private Long id;
//	
//	private String username;
//
//	@ManyToOne
//	@JoinColumn(name = "team_id", insertable = false, updatable = false)
//	private Team team;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public Team getTeam() {
//		return team;
//	}
//
//	public void setTeam(Team team) {
//		this.team = team;
//	}
//
//	@Override
//	public String toString() {
//		return "Member [id=" + id + ", username=" + username + ", team=" + team + "]";
//	}
}
