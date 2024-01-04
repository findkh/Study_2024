package jpabook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

	@Id
	@Column(name = "member_id")
	private String id;
	
	private String username;
	
	//연관 관계 매핑
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	public Member(String id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public void setTeam(Team team) {
		this.team = team;
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
}
