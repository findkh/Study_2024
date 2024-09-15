package com.kh.ex3.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.ex3.cart.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long>{
	Optional<CartEntity> findByHolder(String holder);
}
