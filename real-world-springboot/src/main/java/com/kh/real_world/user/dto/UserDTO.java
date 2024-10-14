package com.kh.real_world.user.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.kh.real_world.user.entity.UserEntity;

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
public class UserDTO {
	private Long id;
	private String email;
	private String password;
	private String userName;
	private String role;
	private LocalDateTime joinDate;
	private LocalDateTime modifiedDate;
	
	public Map<String, Object> getDataMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("email", email);
		map.put("role", role);
		return map;
	}
	
	public UserDTO(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.email = userEntity.getEmail();
		this.password = userEntity.getPassword();
		this.userName = userEntity.getUserName();
		this.role = userEntity.getRole();
		this.joinDate = userEntity.getJoinDate();
		this.modifiedDate = userEntity.getModifiedDate();
	}
}
