package com.kh.real_world.follow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.real_world.follow.entity.FollowEntity;
import com.kh.real_world.user.entity.UserEntity;

import jakarta.transaction.Transactional;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
	// 팔로우 여부를 확인하는 메서드
	Optional<FollowEntity> findByFollowerAndFollowee(UserEntity follower, UserEntity followee);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM FollowEntity f WHERE f.follower.id = :followerId AND f.followee.id = :followeeId")
	void deleteByFollowerAndFollowee(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

}
