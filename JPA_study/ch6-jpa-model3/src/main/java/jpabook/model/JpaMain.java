package jpabook.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {

	
	public static void main(String[] args) {

		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

		try {
			tx.begin(); //트랜잭션 시작
			tx.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}
		emf.close(); //엔티티 매니저 팩토리 종료
	}
}
