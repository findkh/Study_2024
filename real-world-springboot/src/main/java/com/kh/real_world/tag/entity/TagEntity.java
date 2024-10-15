package com.kh.real_world.tag.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kh.real_world.article.entity.ArticleEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_tag")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = "article")
@EntityListeners(value = {AuditingEntityListener.class})
public class TagEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tagName;
	
	// Tag는 여러 개의 Article에 연결될 수 있으므로 ManyToOne 설정
	@ManyToOne
	@JoinColumn(name = "article_id") // 외래키 컬럼명
	private ArticleEntity article; // Article과의 관계
	
	public void changeTagName(String tagName) {
		this.tagName = tagName;
	}
}