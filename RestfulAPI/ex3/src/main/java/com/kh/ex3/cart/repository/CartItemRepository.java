package com.kh.ex3.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.ex3.cart.entity.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long>{
	
	// 페치 조인 사용
	@Query("select c " +
			" from CartItemEntity c " + 
			" join fetch c.product " + 
			" join fetch c.product.images " +
			" where c.cart.holder = :holder" +
			" order by c.itemNo desc ")
	Optional<List<CartItemEntity>> getCartItemsOfHolder(@Param("holder") String holder);
	
	// 조인 사용
	@Query("select c, p, pi " +
			" from CartItemEntity c " +
			" join c.product p " +
			" join c.product.images pi " +
			" where c.cart.holder = :holder" +
			" and pi.idx = 0 " + 
			" order by c.itemNo desc ")
	List<Object[]> getCartItemsOfHolder2(@Param("holder") String holder);
	
	@Query("select c.cart.holder " + 
			" from CartItemEntity c " +
			" where c.itemNo = :itemNo")
	Optional<String> getHolderOfCartItem(@Param("itemNo") Long itemNo);
	
}
