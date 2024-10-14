package com.kh.real_world.profile.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.real_world.follow.entity.FollowEntity;
import com.kh.real_world.follow.repository.FollowRepository;
import com.kh.real_world.profile.dto.ProfileDTO;
import com.kh.real_world.profile.entity.ProfileEntity;
import com.kh.real_world.user.dto.UserDTO;
import com.kh.real_world.user.entity.UserEntity;
import com.kh.real_world.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ProfileRepositoryTests {
	
	@Autowired
	private UserRepository userRepository; // 유저 레포지토리

	@Autowired
	private ProfileRepository profileRepository; // 프로필 레포지토리

	@Autowired
	private FollowRepository followRepository; // 팔로우 레포지토리
	
//	@Test
//	public void testInsertProfile() {
//		Optional<UserEntity> result = userRepository.findByEmail("user1@test.com");
//
//		if (result.isPresent()) {
//			UserDTO userDTO = new UserDTO(result.get());
//			
//			ProfileEntity pe = ProfileEntity.builder()
//					.bio("test...")                     // bio 설정
//					.email(userDTO.getEmail())          // email 설정
//					.user(result.get())                 // user 설정 (Optional에서 꺼내서 전달)
//					.userName(userDTO.getUserName())    // userName 설정
//					.build();                           // build로 빌드 완료
//			
//			profileRepository.save(pe);                  // 저장
//			System.out.println("저장 완료...");
//		}
//	}
	
	@Test
	@Transactional
	public void testGetProfile() {
		// 조회하는 사람 (user4@test.com)
		Optional<UserEntity> viewingUserOpt = userRepository.findByEmail("user4@test.com");
		// 조회되는 사람 (user1@test.com)
		Optional<UserEntity> viewedUserOpt = userRepository.findByEmail("user2@test.com");
		
		if (viewingUserOpt.isPresent() && viewedUserOpt.isPresent()) {
			UserEntity viewingUser = viewingUserOpt.get();
			UserEntity viewedUser = viewedUserOpt.get();
			
			System.out.println("viewingUser: "+ viewingUser);
			System.out.println("viewedUser: "+ viewedUser);

			System.out.println("viewedUser.getId(): " + viewedUser.getId());
			
			// 프로필 정보 가져오기
			Optional<ProfileEntity> profileOpt = profileRepository.findByUserId(viewedUser.getId());
			
			System.out.println("여기 확인: ");
			System.out.println(profileOpt);
			
			assertThat(profileOpt.isPresent()).isTrue(); // 프로필이 있는지 검증
			ProfileEntity profile = profileOpt.get();
			
			System.out.println("profileOpt" + profileOpt);

			// 팔로우 여부 확인
			Optional<FollowEntity> followOpt = followRepository.findByFollowerAndFollowee(viewingUser, viewedUser);
			boolean isFollowing = followOpt.isPresent();

			// DTO로 변환 (서비스 없이 직접)
			ProfileDTO profileDTO = new ProfileDTO(profile);
			profileDTO.setFollowing(isFollowing); // 팔로우 여부 설정

			// 검증: 프로필 정보
			assertThat(profileDTO.getUserName()).isEqualTo(viewedUser.getUserName());
			assertThat(profileDTO.getEmail()).isEqualTo(viewedUser.getEmail());
			assertThat(profileDTO.getFollowing()).isEqualTo(isFollowing); // 팔로우 여부 검증
			
			System.out.println("==================");
			System.out.println(profileDTO);
		} else {
			System.out.println("조회할 수 없는 사용자입니다.");
		}
	}

}
