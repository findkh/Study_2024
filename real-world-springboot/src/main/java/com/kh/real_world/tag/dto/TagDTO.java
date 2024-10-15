package com.kh.real_world.tag.dto;

import com.kh.real_world.tag.entity.TagEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
	private Long id;         // 태그 ID
	private String tagName;  // 태그 이름
	private Long articleId;  // 관련된 Article ID (foreign key)

	// TagEntity를 기반으로 DTO를 생성하는 생성자
	public TagDTO(TagEntity tagEntity) {
		this.id = tagEntity.getId();
		this.tagName = tagEntity.getTagName();
		this.articleId = tagEntity.getArticle().getId(); // 관련된 Article의 ID
	}
}
