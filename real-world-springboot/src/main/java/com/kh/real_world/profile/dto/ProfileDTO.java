package com.kh.real_world.profile.dto;

import java.time.LocalDateTime;

import com.kh.real_world.profile.entity.ProfileEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
	private Long id;
//	private UserEntity user;
	private String userName;
	private String email;
	private String bio;
	private Boolean following;
	private String image;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public ProfileDTO(ProfileEntity profileEntity) {
		this.id = profileEntity.getId();
//		this.user = profileEntity.getUser();
		this.userName = profileEntity.getUserName();
		this.email = profileEntity.getEmail();
		this.bio = profileEntity.getBio();
		this.following = profileEntity.getFollowing();
		this.image = profileEntity.getImage();
		this.createdDate = profileEntity.getCreatedDate();
		this.modifiedDate = profileEntity.getModifiedDate();
	}
}
