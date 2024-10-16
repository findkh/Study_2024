package com.kh.real_world.profile.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kh.real_world.article.entity.ArticleEntity;
import com.kh.real_world.user.entity.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = "user")
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
	
	// ArticleEntity와의 관계 추가 (일대다)
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ArticleEntity> articles; // 사용자의 기사 목록
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	public void changeBio(String bio) {
		this.bio = bio;
	}
}