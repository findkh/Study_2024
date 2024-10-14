package com.kh.real_world.user.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.kh.real_world.profile.entity.ProfileEntity;
import com.kh.real_world.profile.repository.ProfileRepository;
import com.kh.real_world.user.entity.UserEntity;
import com.kh.real_world.user.exception.UserExceptions;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProfileRepository profileRepository;

	@Test
	@Transactional
	@Commit
	public void testUpdate() {
		Optional <UserEntity> result = userRepository.findByEmail("user0@test.com");
		try {
			UserEntity userEntity = result.orElseThrow(UserExceptions.NOT_FOUND::get);
			userEntity.changePassword(passwordEncoder.encode("1111"));
			userEntity.changeUsername("change-user");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearch() {
		Optional <UserEntity> result = userRepository.findByEmail("user2@test.com");
		
		try {
			UserEntity userEntity = result.orElseThrow(UserExceptions.NOT_FOUND::get);
			log.info(userEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsert() {
		for(int i = 0; i <= 50; i++) {
			UserEntity userEntity = UserEntity.builder()
					.email("user"+i +"@test.com")
					.password(passwordEncoder.encode("1234"))
					.userName("user" + i)
					.role("USER")
					.build();
			userRepository.save(userEntity);
			
			ProfileEntity profileEntity = ProfileEntity.builder()
					.bio(null)
					.user(userEntity)
					.email(userEntity.getEmail())
					.userName(userEntity.getUserName())
					.build();
			profileRepository.save(profileEntity);
			
			log.info("{} 계정 생성", userEntity.getUserName());
		}
	}
	
}
