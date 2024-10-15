package com.kh.real_world.article.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.kh.real_world.article.entity.ArticleEntity;
import com.kh.real_world.tag.dto.TagDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
	private Long id;
	private String title;
	private String body;
	private Long profileId;
	private List<TagDTO> tags;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public ArticleDTO(ArticleEntity articleEntity) {
		this.id = articleEntity.getId();
		this.title = articleEntity.getTitle();
		this.body = articleEntity.getBody();
		this.profileId = articleEntity.getProfile().getId(); // 프로필 ID
		this.tags = articleEntity.getTags().stream()
			.map(tag -> new TagDTO(tag)) // TagEntity를 TagDTO로 변환
			.collect(Collectors.toList());
		this.createdDate = articleEntity.getCreatedDate();
		this.modifiedDate = articleEntity.getModifiedDate();
		
	}
}