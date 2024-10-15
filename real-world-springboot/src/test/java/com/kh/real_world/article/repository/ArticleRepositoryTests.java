package com.kh.real_world.article.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.real_world.article.entity.ArticleEntity;
import com.kh.real_world.profile.entity.ProfileEntity;
import com.kh.real_world.profile.repository.ProfileRepository;
import com.kh.real_world.tag.entity.TagEntity;
import com.kh.real_world.tag.repository.TagRepository;
import com.kh.real_world.user.entity.UserEntity;
import com.kh.real_world.user.repository.UserRepository;

@SpringBootTest
public class ArticleRepositoryTests {
	@Autowired
	private ArticleRepository articleRepository; // Article 레포지토리
	
	@Autowired
	private UserRepository userRepository; // User 레포지토리
	
	@Autowired
	private ProfileRepository profileRepository; // Profile 레포지토리
	
	@Autowired
	private TagRepository tagRepository; // Tag 레포지토리
	
	private UserEntity user;
	private ProfileEntity profile;
	
	@Test
	public void testFindAllArticlesByUser() {
		// 사용자 조회
		user = userRepository.findByEmail("user4@test.com").orElseThrow(() -> 
			new RuntimeException("User not found"));
		profile = profileRepository.findById(user.getId()).orElseThrow(() -> 
			new RuntimeException("Profile not found"));
		
		// 사용자가 작성한 모든 아티클 조회
		List<ArticleEntity> articles = articleRepository.findByProfile(profile);
		
		// 콘솔에 아티클 정보 출력
		for (ArticleEntity article : articles) {
			System.out.println("Article ID: " + article.getId());
			System.out.println("Title: " + article.getTitle());
			System.out.println("Body: " + article.getBody());
			System.out.println("Created Date: " + article.getCreatedDate());
			System.out.println("Modified Date: " + article.getModifiedDate());
			
			// 태그 정보 조회
			List<TagEntity> tags = tagRepository.findByArticle(article);
			System.out.println("Tags: ");
			for (TagEntity tag : tags) {
				System.out.println("- " + tag.getTagName());
			}
			System.out.println("------------------------------");
		}
		// Assertions
		assertThat(articles).isNotEmpty(); // 사용자가 작성한 아티클이 존재해야 함
	}
	
	@Test
	public void testInsertArticle() {
		user = userRepository.findByEmail("user4@test.com").orElseThrow(() -> 
			new RuntimeException("User not found"));
		profile = profileRepository.findById(user.getId()).orElseThrow(() -> 
			new RuntimeException("Profile not found"));

		// ArticleEntity 생성
		ArticleEntity article = ArticleEntity.builder()
			.title("Test Article Title33")
			.body("This is a test article body33")
			.profile(profile) // Profile 설정
			.createdDate(LocalDateTime.now())
			.modifiedDate(LocalDateTime.now())
			.build();
		
		// 태그 배열
		String[] tagNames = {"eee", "fff", "ggg"};
		
		// 태그 리스트 생성
		List<TagEntity> tags = new ArrayList<>();
		for (String tagName : tagNames) {
			TagEntity tag = TagEntity.builder()
				.tagName(tagName)
				.article(article) // Article과 연결 (하지만 이 시점에서는 article이 아직 저장되지 않음)
				.build();
			tags.add(tag);
		}
		
		// Article에 태그 추가
		article.setTags(tags); // Article에 태그 추가
		
		// Article 저장
		ArticleEntity savedArticle = articleRepository.save(article);
		
		// 태그 저장 (저장된 Article을 사용)
		for (TagEntity tag : tags) {
			tag.setArticle(savedArticle); // 저장된 Article로 태그의 Article 설정
			tagRepository.save(tag); // 태그를 저장
		}
		
		// Assertions
		assertThat(savedArticle).isNotNull(); // 저장된 Article이 null이 아니어야 함
		assertThat(savedArticle.getTitle()).isEqualTo(article.getTitle()); // 제목 확인
		assertThat(savedArticle.getBody()).isEqualTo(article.getBody()); // 본문 확인
		assertThat(savedArticle.getTags()).hasSize(3); // 저장된 태그 개수 확인
	}
}
