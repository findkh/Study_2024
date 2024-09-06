package com.kh.ex3.member.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.ex3.member.dto.MemberDTO;
import com.kh.ex3.member.entity.MemberEntity;
import com.kh.ex3.member.exception.MemberExceptions;
import com.kh.ex3.member.exception.MemberTaskException;
import com.kh.ex3.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public MemberDTO read(String mid, String mpw) {
		try {
			Optional<MemberEntity> result = memberRepository.findById(mid);
			MemberEntity memberEntity = result.orElseThrow(MemberExceptions.BAD_CREDENTIALS::get);
			
			if(!passwordEncoder.matches(mpw, memberEntity.getMpw())) {
				throw MemberExceptions.BAD_CREDENTIALS.get();
			}
			
			return new MemberDTO(memberEntity);
		} catch(MemberTaskException e) {
			log.error("Error: {} with code {}", e.getMsg(), e.getCode());
			throw e;
		}
	}
	
	public MemberDTO getByMid(String mid) {
		try {
			Optional<MemberEntity> result = memberRepository.findById(mid);
			MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);
			return new MemberDTO(memberEntity);
		} catch(MemberTaskException e) {
			log.error("Error: {} with code {}", e.getMsg(), e.getCode());
			throw e;
		}
	}
}
