package com.kh.real_world.profile.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kh.real_world.user.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_profile")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(value = {AuditingEntityListener.class})
public class ProfileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne // UserEntity와 일대일 관계 설정
	@JoinColumn(name = "user_id", referencedColumnName = "id") // 외래 키 설정
	private UserEntity user; // UserEntity와의 관계를 설정
	
	private String userName;
	
	private String email;
	
	private String bio;
	
	private Boolean following;
	
	private String image;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	public void changeBio(String bio) {
		this.bio = bio;
	}
}