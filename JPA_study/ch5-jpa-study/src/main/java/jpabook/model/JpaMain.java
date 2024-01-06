package jpabook.model;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Member;
import jpabook.model.entity.Team;

public class JpaMain {
	/*
	 * 정리
	 * 단방향 매핑만으로 ㅔ이블과 객체의 연관관계 매핑은 이미 완료되었다.
	 * 단방향을 양방향으로 만들면 반대방향으로 객체 그래프 탐색 기능이 추가된다.
	 * 양방향 연관관계를 매핑하려면 객체에서 양쪽 방향을 모두 관리해야 한다.
	 * */
	public static void main(String[] args) {

		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

		try {
			tx.begin(); //트랜잭션 시작
			
			//testSave(em);
			//queryLogicJoin(em);
			//updateReloation(em);
			//deleteRelation(em);
			
			testSave2(em);
			biDirection(em);
			
			tx.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}
		emf.close(); //엔티티 매니저 팩토리 종료
	}
	
	//일대다 방향으로 객체 그래프 탐색
	public static void biDirection(EntityManager em) {
		
		Team team = em.find(Team.class, "team1");
		
		System.out.println("확인: ");
		System.out.println(team);
		
		List<Member> members = team.getMembers();
		System.out.println("member의 size?"+ members.size());
		
		for(Member member:members) {
			System.out.println("member.username = " + member.getUsername());
		}
	}
	
	// 양방향 연관관계 저장
	public static void testSave2(EntityManager em) {
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		//양방향 모두 관계를 설정해줘야함
		
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1); //연관관계 설정 member1 -> team1
		//team1.getMembers().add(member1); //연관관계 설정 team1 -> member1
		em.persist(member1);
		
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);
		//team1.getMembers().add(member2);
		em.persist(member2);
		
		List<Member> members = team1.getMembers();
		System.out.println("사이즈 확인: " + members.size());
	}
	
	
	//연관관계 제거
	private static void deleteRelation(EntityManager em) {
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(null); //연관관계 제거
		
	}
	
	
	//수정
	private static void updateReloation(EntityManager em) {
		Team team2 = new Team("Team2", "팀2");
		em.persist(team2);
		
		Member member = em.find(Member.class, "member1");
		member.setTeam(team2);
	}
	
	
	//조회
	public static void queryLogicJoin(EntityManager em) {
		String jpql = "SELECT m FROM Member m JOIN m.team t WHERE t.name=:teamName";
		
		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();
		
		for(Member member : resultList) {
			System.out.println("[query] member.username=" + member.getUsername());
		}
	}
	
	
	//저장
	public static void testSave(EntityManager em) {
		//팀1 저장
		Team team1 = new Team("Team1", "팀1");
		em.persist(team1);
		
		//회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1);
		em.persist(member1);
		
		//회원2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);
		em.persist(member2);
	}
}
