package jpabook.start;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
	public static void main(String[] args) {

		/*엔티티 매니저 팩토리 생성
		 * META-INF/persistence.xml의 설정 정보를 사용해서 엔테테 매니저 팩토리 생성
		 * 애플리케이션 전체에서 딱 한번만 생성하고 공유해서 사용
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		
		/*엔티티 매니저 생성
		 * 엔티티 매니저를 통해 엔티티를 데이터베이스에 등록/수정/삭제/조회 할 수 있다.
		 * 데이터베이스 커넥션과 관계가 있으므로 스레드간에 공유하거나 재사용하면 안 된다.
		 * */
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

		try {
			tx.begin(); //트랜잭션 시작
			logic(em);  //비즈니스 로직
			tx.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}

		emf.close(); //엔티티 매니저 팩토리 종료
	}

	public static void logic(EntityManager em) {
		String id = "id2";
		Member member = new Member();
		member.setId(id);
		member.setUsername("고양이");
		member.setAge(34);
		
		//등록
		em.persist(member);
		
		//수정
		member.setAge(20);
		
		//한 건 조회
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
		
		//목록 조회
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("members.size=" + members.size());
		
		
		
		//삭제
		//em.remove(member);
    }
}
