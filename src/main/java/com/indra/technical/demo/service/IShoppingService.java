package com.indra.technical.demo.service;

import com.indra.technical.demo.dto.SeeShoppingDto;
import com.indra.technical.demo.dto.ShoppingCartWrapperDto;
import com.indra.technical.demo.model.ShoppingCartEntity;

import java.util.List;
import java.util.Optional;

public interface IShoppingService {

    ShoppingCartWrapperDto findAllShopping();
    String addToCart(ShoppingCartEntity shoppingCartEntity);
    String deleteToCart(Long idProduct);
    String updateQuantity(Long idProduct, Integer quantity);

    Optional<ShoppingCartEntity> findProduct(Long id);
}
