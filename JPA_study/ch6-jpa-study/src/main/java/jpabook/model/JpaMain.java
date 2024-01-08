package jpabook.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Member;
import jpabook.model.entity.Team;

public class JpaMain {
	
	/*
	 * 일대다 단방향 매핑보다는 다대일 양방향 매핑을 사용하는것이 좋다.
	 * 편의성 및 유지보수성: 다대일 양방향 매핑은 양쪽 엔티티 간의 관계를 더 쉽게 탐색할 수 있다. 
	 * 성능 향상: 단방향 매핑에서는 양방향 관계를 탐색하기 위해 추가적인 쿼리가 필요합니다. 다대일 양방향 매핑에서는 양쪽 엔티티 간의 관계를 바로 탐색할 수 있기 때문에 성능 향상을 기대할 수 있다.
	 * 객체 그래프 탐색: 다대일 양방향 매핑을 사용하면 객체 그래프를 더 효과적으로 탐색할 수 있다. 
	 * 					 단방향 매핑에서는 연결된 객체를 얻기 위해 여러 번의 쿼리를 실행해야 할 수 있지만 양방향 매핑에서는 한 번의 쿼리로 두 엔티티 간의 관계를 쉽게 확인할 수 있다.
	 * JPA 생명주기 특성: JPA에서 관계의 주인(owner)은 관계를 주도하고 변경하는 주체다. 
	 * 					  다대일 양방향 매핑에서는 주인과 mappedBy 속성을 사용하여 관계를 명시적으로 설정할 수 있다. 이는 데이터베이스에 더 적합한 스키마를 설계하고 효율적인 쿼리를 수행하는 데 도움이 된다.
	 * */
	
	public static void main(String[] args) {

		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

		try {
			tx.begin(); //트랜잭션 시작
			testSave(em);
			tx.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}
		emf.close(); //엔티티 매니저 팩토리 종료
	}
	
	public static void testSave(EntityManager em) {
		Member member1 = new Member();
			member1.setUsername("memeber1");
			
		Member member2 = new Member();
			member2.setUsername("member2");
			
		Team team1 = new Team();
			team1.setName("team1");
		team1.getMembers().add(member1);
		team1.getMembers().add(member2);
		
		em.persist(member1);
		em.persist(member2);
		em.persist(team1);
	}
}
