package com.kh.real_world.user.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.real_world.user.dto.UserDTO;
import com.kh.real_world.user.entity.UserEntity;
import com.kh.real_world.user.exception.UserExceptions;
import com.kh.real_world.user.exception.UserTaskException;
import com.kh.real_world.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserDTO login(String email, String password) {
		try {
			Optional<UserEntity> result = userRepository.findByEmail(email);
			UserEntity userEntity = result.orElseThrow(() -> UserExceptions.NOT_FOUND.get());
			
			if(!passwordEncoder.matches(password, userEntity.getPassword())) {
				throw UserExceptions.BAD_CREDENTIALS.get();
			}
			
			return new UserDTO(userEntity);
			
		} catch(UserTaskException e) {
			log.error("Error: {} with code {}", e.getMsg(), e.getCode());
			throw e;
		}
	}
	
	public UserDTO getByEmail(String email) {
		try {
			Optional<UserEntity> result = userRepository.findByEmail(email);
			UserEntity userEntity = result.orElseThrow(UserExceptions.NOT_FOUND::get);
			return new UserDTO(userEntity);
		} catch(UserTaskException e) {
			log.error("Error: {} with code {}", e.getMsg(), e.getCode());
			throw e;
		}
	}

	public void registerUser(UserDTO userDTO) {
		log.info("registerUser 호출");
		
		// 사용자 데이터 검증 및 회원가입 로직
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userEntity.setUserName(userDTO.getUserName());
		userEntity.setRole(userDTO.getRole());
		userEntity.setJoinDate(LocalDateTime.now());
		log.info("userEntity: {}", userEntity);
		
		userRepository.save(userEntity);
	}
	
	public void updatePwd(UserDTO userDTO) {
		log.info("updatePwd 호출");
		
		Optional <UserEntity> result = userRepository.findByEmail(userDTO.getEmail());
		
		try {
			UserEntity userEntity = result.orElseThrow(UserExceptions.NOT_FOUND::get);
			userEntity.changePassword(passwordEncoder.encode(userDTO.getPassword()));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
