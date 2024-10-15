package com.kh.real_world.follow.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.real_world.follow.entity.FollowEntity;
import com.kh.real_world.user.entity.UserEntity;
import com.kh.real_world.user.repository.UserRepository;


@SpringBootTest
//@Transactional
public class FollowRepositoryTests {
	
	@Autowired
	private UserRepository userRepository; // 유저 레포지토리

	@Autowired
	private FollowRepository followRepository; // 팔로우 레포지토리
	
	@Test
	
	public void testFollowUser() {
		// 팔로워와 팔로우 당하는 유저 조회
		Optional<UserEntity> followerOpt = userRepository.findByEmail("user4@test.com");
		Optional<UserEntity> followeeOpt = userRepository.findByEmail("user2@test.com");
		
		System.out.println("followerOpt: "+ followerOpt);
		System.out.println("followeeOpt: "+ followeeOpt);

		if (followerOpt.isPresent() && followeeOpt.isPresent()) {
			UserEntity follower = followerOpt.get();
			UserEntity followee = followeeOpt.get();

			// 기존의 팔로우 관계 확인
			Optional<FollowEntity> existingFollowOpt = followRepository.findByFollowerAndFollowee(follower, followee);
			
			System.out.println("여기..");
			System.out.println(existingFollowOpt);
			
			if (existingFollowOpt.isPresent()) {
//				System.out.println("삭제한다.." + follower.getId() + " " + followee.getId());
				// 팔로우 관계가 이미 존재하면 삭제
				followRepository.deleteByFollowerAndFollowee(follower.getId(), followee.getId());

				// 삭제 후 확인
				Optional<FollowEntity> afterDeleteOpt = followRepository.findByFollowerAndFollowee(follower, followee);
				assertThat(afterDeleteOpt).isEmpty(); // 삭제된 후 다시 조회 시 빈 Optional이어야 함
				System.out.println("팔로우 관계가 삭제되었습니다.");
			} else {
//				System.out.println("삽입한다");
				// 팔로우 관계가 존재하지 않으면 새로 생성
				FollowEntity followEntity = FollowEntity.builder()
					.follower(follower) // 팔로워 설정
					.followee(followee) // 팔로우 당하는 사용자 설정
					.build();

				// 팔로우 엔티티를 데이터베이스에 저장
				followRepository.save(followEntity);
				System.out.println("팔로우 관계가 생성되었습니다.");

				// 데이터베이스에서 팔로우 관계가 잘 저장되었는지 확인
				FollowEntity savedFollow = followRepository.findById(followEntity.getId()).orElse(null);
				assertThat(savedFollow).isNotNull(); // 팔로우 엔티티가 null이 아니어야 함
				assertThat(savedFollow.getFollower().getId()).isEqualTo(follower.getId()); // 팔로워 ID 확인
				assertThat(savedFollow.getFollowee().getId()).isEqualTo(followee.getId()); // 팔로우 당하는 사용자 ID 확인
			}
		} else {
			System.out.println("팔로워 또는 팔로우 당하는 사용자를 찾을 수 없습니다.");
		}
	}


}
