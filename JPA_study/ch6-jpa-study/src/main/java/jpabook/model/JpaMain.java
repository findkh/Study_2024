package jpabook.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.Product;

public class JpaMain {
	
	/* 다대다 연관관계
	 *  다대다 관계를 일대다 다대일 관계로 풀어내기 위해 연결 테이블을 만들 때 식별자를 어떻게 구성할지 선택해야한다.
	 *  식별 관계: 받아온 식별자를 기본 키 + 외래 키로 사용
	 *  비식별 관계: 받아온 식별자는 외래 키로만 사용하고 새로운 식별자를 추가한다.
	 * */
	
	public static void main(String[] args) {

		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

		try {
			tx.begin(); //트랜잭션 시작
			testSave(em);
			testFind(em);
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
			member1.setId("member1");
			member1.setUserName("회원1");
		em.persist(member1);
		
		Product productA = new Product();
			productA.setId("productA");
			productA.setName("상품1");
		em.persist(productA);
		
		Order order = new Order();
			order.setMember(member1);
			order.setProduct(productA);
			order.setOrderAmount(2);
		em.persist(order);
	}
	
	public static void testFind(EntityManager em) {
		Long orderId = 1L;
		Order order = em.find(Order.class, orderId);
		
		
		Member member = order.getMember();
		Product product = order.getProduct();
		
		System.out.println("member = " + member.getUserName());
		System.out.println("product = " + product.getName());
		System.out.println("orderAmount = " + order.getOrderAmount());
	}
}
