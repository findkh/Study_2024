package repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.Member;

public class MemoryMemberRepositoryTest {
	MemberRepository repository = new MemoryMemberRepository();
	
	@Test
	public void save() {
		Member  member = new Member();
		member.setName("aa");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		Assertions.assertEquals(member, result);
	}
}
