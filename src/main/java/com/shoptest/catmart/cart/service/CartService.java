package com.shoptest.catmart.cart.service;

import com.shoptest.catmart.cart.dto.CartItemAddInputDto;

public interface CartService {

  Long addItemInCart(String email, CartItemAddInputDto parameter);

}
