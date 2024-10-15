package com.kh.real_world.tag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.real_world.article.entity.ArticleEntity;
import com.kh.real_world.tag.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

	List<TagEntity> findByArticle(ArticleEntity article);

}
