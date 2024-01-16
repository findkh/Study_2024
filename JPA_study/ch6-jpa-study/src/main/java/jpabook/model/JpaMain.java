package jpabook.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Member;
import jpabook.model.entity.Product;

public class JpaMain {
	
	/* 다대다[N:N]
	 * -> 관계형 데이터베이스는 정규화된 테이블 2개로 다대다 관계를 표현할 수 없다.
	 * 그래서 중간에 연결 테이블을 추가해야 한다. 
	 * 하지만 객체는 테이블과 다르게 객체 2개로 다대다 관계를 만들 수 있다.
	 * @ManytoMany를 사용하면 매핑할 수 있다.
	 * 
	 * */
	
	public static void main(String[] args) {

		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

		try {
			tx.begin(); //트랜잭션 시작
			testSave(em);
			find(em);
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
		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품A");
		em.persist(productA);
		
		Member member1 = new Member();
			member1.setId("member1");
			member1.setUsername("회원1");
			member1.getProducts().add(productA);
		em.persist(member1);
	}
	
	public static void find(EntityManager em) {
		Member member = em.find(Member.class, "member1");
		List<Product> products = member.getProducts(); //객체 그래프 탐색
		for(Product product : products) {
			System.out.println("product.name = " + product.getName());
		}
	}
}
