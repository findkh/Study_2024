package com.kh.ex3.member;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.kh.ex3.member.entity.MemberEntity;
import com.kh.ex3.member.exception.MemberExceptions;
import com.kh.ex3.member.repository.MemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTests {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Test
	@Transactional
	@Commit
	public void testDelete() {
		String mid = "kkk";
		try {
			Optional<MemberEntity> result = memberRepository.findById(mid);
			MemberEntity memberEntity = result.orElseThrow(() -> MemberExceptions.NOT_FOUND.get());
			memberRepository.delete(memberEntity);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Commit
	public void testUpdate() {
		String mid = "user1";
		Optional<MemberEntity> result = memberRepository.findById(mid);
		try {
			MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);
			memberEntity.changePassword(passwordEncoder.encode("2222"));
			memberEntity.changeName("USER1-1");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRead() {
		String mid = "user4";
		Optional<MemberEntity> result = memberRepository.findById(mid);
		try {		
			MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);
			System.out.println(memberEntity);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsert() {
		for(int i = 0; i <= 100; i++) {
			MemberEntity memberEntity = MemberEntity.builder()
					.mid("user" + i)
					.mpw(passwordEncoder.encode("1111"))
					.mname("USER" + i)
					.memail("user" + i + "@kh.com")
					.role(i <= 80 ? "USER" : "ADMIN")
					.build();
			
			memberRepository.save(memberEntity);
		}
	}
}
