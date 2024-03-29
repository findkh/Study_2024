package com.kh.mallapi.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kh.mallapi.domain.Cart;
import com.kh.mallapi.domain.CartItem;
import com.kh.mallapi.domain.Member;
import com.kh.mallapi.domain.Product;
import com.kh.mallapi.dto.CartItemListDTO;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CartRepositoryTests {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    @Commit
    @Test
    public void testInsertByProduct() {
        log.info("test1=======");

        String email = "user1@test.com";
        Long pno = 5L;
        int qty = 2;

        // 장바구니 아이템이 있었다면
        CartItem cartItem = cartItemRepository.getItemOfPno(email, pno);
        if (cartItem != null) {
            cartItem.changeQty(qty);
            cartItemRepository.save(cartItem);
            return;
        }

        // 장바구니 아이템이 없었다면 장바구니부터 확인 필요
        Optional<Cart> result = cartRepository.getCartOfMember(email);

        Cart cart = null;

        if (result.isEmpty()) {
            log.info("MemberCart is not exist!");
            Member member = Member.builder().email(email).build();
            Cart tempCart = Cart.builder().owner(member).build();
            cart = cartRepository.save(tempCart);
        } else {
            cart = result.get();
        }

        log.info(cart);

        if (cartItem == null) {
            Product product = Product.builder().pno(pno).build();
            cartItem = cartItem.builder().product(product).cart(cart).qty(qty).build();
        }

        cartItemRepository.save(cartItem);
    }

    @Test
    @Commit
    public void testUpdateByCino() {
        Long cino = 1L;
        int qty = 4;
        Optional<CartItem> result = cartItemRepository.findById(cino);
        CartItem cartItem = result.orElseThrow();
        cartItem.changeQty(qty);
        cartItemRepository.save(cartItem);
    }

    @Test
    public void testListOfMember() {
        String email = "user1@test.com";
        List<CartItemListDTO> cartItemList = cartItemRepository.getItemsOfCartDTOByEmail(email);

        for (CartItemListDTO dto : cartItemList) {
            log.info(dto);
        }
    }

    @Test
    public void testDeleteThenList() {
        Long cino = 1L;

        Long cno = cartItemRepository.getCartFromItem(cino);

        List<CartItemListDTO> cartItemList = cartItemRepository.getItemsOfCartDTOByCart(cno);
        for (CartItemListDTO dto : cartItemList) {
            log.info(dto);
        }
    }

}
