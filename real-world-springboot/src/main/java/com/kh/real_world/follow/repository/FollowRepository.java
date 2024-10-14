package com.kh.real_world.follow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kh.real_world.follow.entity.FollowEntity;
import com.kh.real_world.user.entity.UserEntity;

import jakarta.transaction.Transactional;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
	// 팔로우 여부를 확인하는 메서드
	Optional<FollowEntity> findByFollowerAndFollowee(UserEntity follower, UserEntity followee);
	
	@Modifying // 이 메서드는 데이터베이스에 영향을 미치는 메서드임을 나타냄
	@Transactional // 트랜잭션을 사용하여 메서드를 수행
	@Query("DELETE FROM FollowEntity f WHERE f.follower.id = :followerId AND f.followee.id = :followeeId")
	void deleteByFollowerAndFollowee(Long followerId, Long followeeId);
}
