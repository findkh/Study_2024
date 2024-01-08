package jpabook.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	@Id
	@Column(name = "team_id")
	private String id;
	
	private String name;

	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<Member>();
	
	public void addMember(Member member) {
		this.members.add(member);
		if(member.getTeam() != this) {
			member.setTeam(this);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	
	
	
}
