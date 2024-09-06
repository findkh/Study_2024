package com.kh.ex3.member.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_members")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = {AuditingEntityListener.class})
public class MemberEntity {
	@Id
	private String mid;
	
	private String mpw;
	
	private String mname;
	
	private String memail;
	
	@CreatedDate
	private LocalDateTime joinDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	private String role;
	
	public void changePassword(String password) {
		this.mpw = password;
	}
	
	public void changeName(String name) {
		this.mname = name;
	}
	
	public void changeEmail(String email) {
		this.memail = email;
	}
	
	public void changeRole(String role) {
		this.role = role;
	}
}

