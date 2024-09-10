package com.kh.ex3.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.ex3.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
//	@EntityGraph(attributePaths = {"images"}, type = EntityGraph.EntityGraphType.FETCH)
//	@Query("select p from ProductEntity p where p.pno = :pno")
//	Optional<ProductEntity> getProduct(@Param("pno") Long pno);
	
	@Query("select p from ProductEntity p join fetch p.images pi where p.pno = :pno")
	Optional<ProductEntity> getProduct(@Param("pno") Long pno);

}
