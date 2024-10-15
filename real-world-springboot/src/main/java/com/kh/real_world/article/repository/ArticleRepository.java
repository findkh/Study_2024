package com.kh.real_world.article.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.real_world.article.entity.ArticleEntity;
import com.kh.real_world.profile.entity.ProfileEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>{

	List<ArticleEntity> findByProfile(ProfileEntity profile);

}
