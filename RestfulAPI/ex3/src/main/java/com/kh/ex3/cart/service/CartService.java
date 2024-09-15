package com.kh.ex3.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex3.cart.dto.AddCartItemDTO;
import com.kh.ex3.cart.dto.CartItemDTO;
import com.kh.ex3.cart.dto.ModifyCartItemDTO;
import com.kh.ex3.cart.entity.CartEntity;
import com.kh.ex3.cart.entity.CartItemEntity;
import com.kh.ex3.cart.exception.CartTaskException;
import com.kh.ex3.cart.repository.CartItemRepository;
import com.kh.ex3.cart.repository.CartRepository;
import com.kh.ex3.product.entity.ProductEntity;
import com.kh.ex3.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional 
public class CartService {
	
	private final CartItemRepository cartItemRepository;
	
	private final CartRepository cartRepository;
	
	private final ProductRepository productRepository;
	
	public List<CartItemDTO> getAllItems(String mid){
		List<CartItemDTO> itemDTOList = new ArrayList<>();
		
		Optional<List<CartItemEntity>> result = cartItemRepository.getCartItemsOfHolder(mid);
		
		if(result.isEmpty()) {
			return itemDTOList;
		}
		
		List<CartItemEntity> cartItemEntityList = result.get();
		
		cartItemEntityList.forEach(cartItemEntity -> {
			itemDTOList.add(entityToDTO(cartItemEntity));
		});
		
		return itemDTOList;
	}
	
	private CartItemDTO entityToDTO(CartItemEntity cartItemEntity) {
		return CartItemDTO.builder()
				.itemNo(cartItemEntity.getItemNo())
				.pname(cartItemEntity.getProduct().getPname())
				.pno(cartItemEntity.getProduct().getPno())
				.price(cartItemEntity.getProduct().getPrice())
				.image(cartItemEntity.getProduct().getImages().first().getFileName())
				.quantity(cartItemEntity.getQuantity())
				.build();
	}
	
	public void registerItem(AddCartItemDTO addCartItemDTO) {
		String mid = addCartItemDTO.getHolder();
		Long pno = addCartItemDTO.getPno();
		int quantity = addCartItemDTO.getQuantity();
		
		Optional<CartEntity> cartResult = cartRepository.findByHolder(mid);
		
		CartEntity cartEntity = cartResult.orElseGet(() -> {
			CartEntity cart = CartEntity.builder().holder(mid).build();
			return cartRepository.save(cart);
		});
		
		ProductEntity productEntity = productRepository.findById(pno)
				.orElseThrow(CartTaskException.Items.NOT_FOUND_PRODUCT::value);
		
		CartItemEntity cartItemEntity = CartItemEntity.builder()
				.cart(cartEntity)
				.product(productEntity)
				.quantity(quantity)
				.build();
		
		try {
			cartItemRepository.save(cartItemEntity);
		} catch(Exception e) {
			log.error(e.getMessage());
			throw CartTaskException.Items.CART_ITEM_REGISTER_FAIL.value();
		}
	}
	
	public void modifyItem(ModifyCartItemDTO modifyCartItemDTO) {
		Long itemNo = modifyCartItemDTO.getItemNo();
		int quantity = modifyCartItemDTO.getQuantity();
		
		Optional<CartItemEntity> result = cartItemRepository.findById(itemNo);
		
		if(result.isEmpty()) {
			throw CartTaskException.Items.NOT_FOUND_CARTITEM.value();
		}
		
		CartItemEntity cartItemEntity = result.get();
		
		if(quantity <= 0) {
			cartItemRepository.delete(cartItemEntity);
			return;
		}
		
		cartItemEntity.changeQuantity(quantity);
	}
	
	public void checkItemHolder(String holder, Long itemNo) {
		System.out.println("holder: " + holder + " itemNo: " + itemNo);
		Optional<String> result = cartItemRepository.getHolderOfCartItem(itemNo);
		
		System.out.println("holder" + holder);
		System.out.println("=============");
		System.out.println(result);
		
		if(result.isEmpty()) {
			throw CartTaskException.Items.NOT_FOUND_CARTITEM.value();
		}
		
		if(!result.get().equals(holder)) {
			throw CartTaskException.Items.NOT_CARTITEM_OWNER.value();
		}
	}
}
