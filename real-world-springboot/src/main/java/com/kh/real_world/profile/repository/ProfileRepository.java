package com.kh.real_world.profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.real_world.profile.entity.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>{
	Optional<ProfileEntity> findByUserId(Long userId);
}
	