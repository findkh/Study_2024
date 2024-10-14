package com.kh.real_world.follow.entity;

import com.kh.real_world.user.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_follow")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FollowEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne // 팔로워
	@JoinColumn(name = "follower_id", referencedColumnName = "id", nullable = false)
	private UserEntity follower; // 팔로워 유저

	@ManyToOne // 팔로우 당하는 사용자
	@JoinColumn(name = "followee_id", referencedColumnName = "id", nullable = false)
	private UserEntity followee; // 팔로우 대상 유저
}