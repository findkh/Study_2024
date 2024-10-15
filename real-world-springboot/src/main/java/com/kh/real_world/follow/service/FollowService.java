package com.kh.real_world.follow.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kh.real_world.follow.entity.FollowEntity;
import com.kh.real_world.follow.exception.FollowExceptions;
import com.kh.real_world.follow.repository.FollowRepository;
import com.kh.real_world.user.entity.UserEntity;
import com.kh.real_world.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FollowService {

	private final FollowRepository followRepository;
	private final UserRepository userRepository;
	
	public void updateFollow(String loggedInUserEmail, String email) {
		// 팔로우 당하는 사용자 조회
		UserEntity followee = userRepository.findByEmail(email)
				.orElseThrow(() -> FollowExceptions.INVALID.get()); // 사용자 미발견 시 예외 발생

		// 팔로워 사용자 조회
		UserEntity follower = userRepository.findByEmail(loggedInUserEmail)
				.orElseThrow(() -> FollowExceptions.BAD_CREDENTIALS.get()); // 인증 실패 시 예외 발생

		// 팔로우 관계 확인
		Optional<FollowEntity> existingFollow = followRepository.findByFollowerAndFollowee(follower, followee);
		
		if (existingFollow.isPresent()) {
			// 팔로우 관계가 이미 존재하면 삭제
			followRepository.deleteByFollowerAndFollowee(follower.getId(), followee.getId());
			log.info("팔로우 삭제");
		} else {
			// 팔로우 관계가 없으면 새로 생성
			FollowEntity newFollow = new FollowEntity();
			newFollow.setFollower(follower);
			newFollow.setFollowee(followee);
			followRepository.save(newFollow);
			log.info("팔로우 저장");
		}
		
	}

}
