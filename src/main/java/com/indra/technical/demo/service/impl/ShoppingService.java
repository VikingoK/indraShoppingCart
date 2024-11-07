package com.indra.technical.demo.service.impl;

import com.indra.technical.demo.dto.SeeShoppingDto;
import com.indra.technical.demo.dto.ShoppingCartWrapperDto;
import com.indra.technical.demo.model.ShoppingCartEntity;
import com.indra.technical.demo.repository.ShoppingCartRepository;
import com.indra.technical.demo.service.IShoppingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class ShoppingService implements IShoppingService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCartWrapperDto findAllShopping() {

        ShoppingCartWrapperDto shoppingCartWrapperDto = new ShoppingCartWrapperDto();

        List<SeeShoppingDto> listProducts = shoppingCartRepository.getShoppingCart();

        if(listProducts.isEmpty()) {
            return new ShoppingCartWrapperDto();
        }

        shoppingCartWrapperDto.setListProducts(listProducts);

        AtomicReference<Double> totalToPay = new AtomicReference<>((double) 0);
        listProducts.forEach(product -> {
            totalToPay.updateAndGet(v -> v + product.getTotalProduct());
        });

        shoppingCartWrapperDto.setSubTotal(totalToPay.get());
        shoppingCartWrapperDto.setTotalToPay(totalToPay.get());

        return shoppingCartWrapperDto;
    }

    @Override
    public String addToCart(ShoppingCartEntity shoppingCartEntity) {

        Long idProduct = shoppingCartEntity.getIdProduct();
        Integer quantity = shoppingCartEntity.getQuantity();

        if(findProduct(idProduct).isPresent()) {
            return updateQuantity(idProduct, quantity);
        }

         shoppingCartRepository.save(shoppingCartEntity);

        return "El producto añadito exitosamente";
    }

    @Override
    public String deleteToCart(Long idProduct) {

        if(findProduct(idProduct).isPresent()) {
            shoppingCartRepository.deleteByProductId(idProduct);

            return "El producto fue eliminado exitosamente";
        }

        return "El producto no existe";
    }

    @Override
    public String updateQuantity(Long idProduct, Integer quantity) {
        Optional<ShoppingCartEntity> productsInCart = findProduct(idProduct);

        if (productsInCart.isEmpty()) {
            return "Producto no encontrado";
        }

        ShoppingCartEntity productToUpdate = productsInCart.get();

        if (quantity <= 0) {
            return deleteToCart(productToUpdate.getIdProduct());
        }

        productToUpdate.setQuantity(quantity);
        shoppingCartRepository.save(productToUpdate);

        return "El producto ha sido actualizado exitosamente!";
    }

    @Override
    public Optional<ShoppingCartEntity> findProduct(Long id) {
        List<ShoppingCartEntity> products = shoppingCartRepository.findProductIntoList(id);
        if (products == null || products.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(products.get(0));
    }
}
