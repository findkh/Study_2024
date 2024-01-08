package jpabook.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Member;
import jpabook.model.entity.Team;

public class JpaMain {
	
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
