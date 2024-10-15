package com.kh.real_world.profile.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.real_world.follow.repository.FollowRepository;
import com.kh.real_world.profile.dto.ProfileDTO;
import com.kh.real_world.profile.entity.ProfileEntity;
import com.kh.real_world.profile.exception.ProfileExceptions;
import com.kh.real_world.profile.exception.ProfileTaskException;
import com.kh.real_world.profile.repository.ProfileRepository;
import com.kh.real_world.user.entity.UserEntity;
import com.kh.real_world.user.exception.UserExceptions;
import com.kh.real_world.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProfileService {

	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	private final FollowRepository followRepository;

	public ProfileDTO getProfile(String loggedInUserEmail, String viewedUserEmail) {
		try {
		// 로그인한 사용자를 조회
		UserEntity viewingUser = userRepository.findByEmail(loggedInUserEmail)
				.orElseThrow(() -> ProfileExceptions.NOT_FOUND.get());

		// 조회당하는 사용자를 조회
		UserEntity viewedUser = userRepository.findByEmail(viewedUserEmail)
				.orElseThrow(() -> ProfileExceptions.NOT_FOUND.get());

		// 프로필 정보 가져오기
		ProfileEntity profile = profileRepository.findByUserId(viewedUser.getId())
				.orElseThrow(() -> ProfileExceptions.NOT_FOUND.get());

		// 팔로우 여부 확인
		boolean isFollowing = followRepository.findByFollowerAndFollowee(viewingUser, viewedUser).isPresent();

		// ProfileEntity를 DTO로 변환
		ProfileDTO profileDTO = new ProfileDTO(profile);
		profileDTO.setFollowing(isFollowing);

		return profileDTO;
		} catch(ProfileTaskException e) {
			log.error("Error: {} with code {}", e.getMsg(), e.getCode());
			throw e;
		}
	}
	
	public ProfileDTO updateProfile(ProfileDTO profileDTO) {
		System.out.println("서비스단" + profileDTO.getEmail());
		UserEntity user = userRepository.findByEmail(profileDTO.getEmail())
				.orElseThrow(() -> UserExceptions.NOT_FOUND.get());
		
		String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(!user.getEmail().equals(loggedInUserEmail)) {
			throw ProfileExceptions.INVALID.get();
		}
		
		ProfileEntity profile = profileRepository.findByUserId(user.getId())
				.orElseThrow(() -> ProfileExceptions.NOT_FOUND.get());
		
		System.out.println(user);
		System.out.println(profile);
		
		profile.changeBio(profileDTO.getBio());
		
		profileRepository.save(profile);
		
		return new ProfileDTO(profile);
	}
}
