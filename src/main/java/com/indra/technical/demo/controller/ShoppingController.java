package com.indra.technical.demo.controller;

import com.indra.technical.demo.dto.SeeShoppingDto;
import com.indra.technical.demo.dto.ShoppingCartWrapperDto;
import com.indra.technical.demo.dto.ShoppingDto;
import com.indra.technical.demo.model.ShoppingCartEntity;
import com.indra.technical.demo.service.IShoppingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shopping")
@Api(value = "Controlador que maneja las operaciones relacionadas con el carrito de compras")
public class ShoppingController {

    @Autowired
    private IShoppingService shoppingService;

    @ApiOperation(
            value = "Obtener el carrito de compras del usuario",
            notes = "Devuelve los detalles de todos los productos agregados al carrito de compras del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Detalles del carrito de compras", response = ShoppingCartWrapperDto.class),
    })
    @GetMapping("/getCart")
    public ShoppingCartWrapperDto getShoppingCart() {
        return shoppingService.findAllShopping();
    }

    @ApiOperation(
            value = "Agregar un producto al carrito de compras",
            notes = "Agrega un producto al carrito de compras del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Detalles del producto a agregar al carrito de compras", response = ShoppingDto.class) })
    @PostMapping("/addToCart")
    public String addToCart(@RequestBody ShoppingDto shoppingCartDto) {

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();

        shoppingCartEntity.setCreateDay(LocalDateTime.now());
        shoppingCartEntity.setIdProduct(shoppingCartDto.getIdProduct());
        shoppingCartEntity.setQuantity(shoppingCartDto.getQuantity());

        return shoppingService.addToCart(shoppingCartEntity);
    }

    @ApiOperation(
            value = "Actualizar la cantidad de un producto en el carrito de compras",
            notes = "Actualiza la cantidad de un producto existente en el carrito de compras del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cantidad del producto actualizada exitosamente"),
    })
    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody ShoppingDto shoppingCartDto) {

        Long idProduct = shoppingCartDto.getIdProduct();
        Integer quantity = shoppingCartDto.getQuantity();

        return shoppingService.updateQuantity(idProduct, quantity);
    }


    @ApiOperation(
            value = "Eliminar un producto del carrito de compras",
            notes = "Elimina un producto específico del carrito de compras del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Producto eliminado exitosamente del carrito de compras"),
    })
    @DeleteMapping("/{idProduct}")
    public String deleteProductToCart(@PathVariable(name = "idProduct") Long idProduct){
        return shoppingService.deleteToCart(idProduct);
    }

    @ApiOperation(
            value = "Buscar un producto en el carrito de compras",
            notes = "Busca un producto específico en el carrito de compras del usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Producto encontrado en el carrito de compras", response = ShoppingCartEntity.class),
    })
    @GetMapping("/{idProduct}")
    public Optional<ShoppingCartEntity> findProductToCart(@PathVariable(name = "idProduct") Long idProduct){
        return shoppingService.findProduct(idProduct);
    }


}
