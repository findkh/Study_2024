package com.kh.real_world.follow.dto;

import com.kh.real_world.follow.entity.FollowEntity;
import com.kh.real_world.user.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowDTO {
	private Long id; // 고유 ID (선택 사항)
	
	private Long followerId; // 팔로워 ID
	private Long followeeId; // 팔로우 당하는 사용자 ID
	
	private UserDTO follower; // 팔로워의 정보
	private UserDTO followee; // 팔로우 당하는 사용자의 정보
	
	public FollowDTO(FollowEntity followEntity) {
		this.id = followEntity.getId();
		this.followerId = followEntity.getFollower().getId();
		this.followeeId = followEntity.getFollowee().getId();
		this.follower = new UserDTO(followEntity.getFollower());
		this.followee = new UserDTO(followEntity.getFollowee());
	}
}
