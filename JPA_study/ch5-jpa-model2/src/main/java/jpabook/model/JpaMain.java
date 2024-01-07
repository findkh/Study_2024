package jpabook.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Item;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;
import jpabook.model.entity.OrderStatus;
import jpabook.model.entity.User;

public class JpaMain {
	public static void main(String[] args) {

		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

		try {
			tx.begin(); //트랜잭션 시작
			
			//save(em);
			orderAnduserBiDirection(em);
			orderBiDirection(em);
			
			tx.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}
		emf.close(); //엔티티 매니저 팩토리 종료
	}
	
	public static void orderBiDirection(EntityManager em) {
		Order order = em.find(Order.class, 4L);

		if (order != null) {
			System.out.println("Order: " + order);

			List<OrderItem> orderItems = order.getOrderItems();
			if (!orderItems.isEmpty()) {
				OrderItem orderItem = orderItems.get(0);
				System.out.println("OrderItem: " + orderItem);
				
				Item item = orderItem.getItem();
				System.out.println("Item: " + item);
			}
		}
	}
	
	public static void orderAnduserBiDirection(EntityManager em) {
		Order order = em.find(Order.class, 4L);
		System.out.println("order: " + order);
		
		if (order != null) {
			User user = order.getUser();
			System.out.println("user: " + user);
		}
	}
	
	public static void save(EntityManager em) {
		// 아이템 저장
		Item item1 = new Item();
			item1.setName("테스트상품1");
			item1.setPrice(10000);
			item1.setStockQuantity(5);
		em.persist(item1);
		
		Item item2 = new Item();
			item2.setName("테스트상품2");
			item2.setPrice(2000);
			item2.setStockQuantity(3);
		em.persist(item2);
		
		// 사용자 저장
		User user = new User();
			user.setName("테스트유저");
			user.setCity("서울");
			user.setStreet("테스트구 테스트동 33-33");
			user.setZipcode("3434");
		em.persist(user);
		
		// 주문 저장
		Date currentDate = new Date();
		Order order = new Order();
			order.setOrderDate(currentDate);
			order.setStatus(OrderStatus.ORDER);
			order.setUser(user);
		em.persist(order);
		
		// 주문 아이템1 저장
		OrderItem orderItem1 = new OrderItem();
			orderItem1.setCount(1);
			orderItem1.setItem(item1);
			orderItem1.setOrder(order);
			orderItem1.setOrderPrice(item1.getPrice());
		em.persist(orderItem1);
		
		// 주문 아이템2 저장
		OrderItem orderItem2 = new OrderItem();
			orderItem2.setCount(1);
			orderItem2.setItem(item2);
			orderItem2.setOrder(order);
			orderItem2.setOrderPrice(item2.getPrice());
		em.persist(orderItem2);
	}

	
}
