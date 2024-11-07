package com.indra.technical.demo.controller;

import com.indra.technical.demo.model.ProductEntity;
import com.indra.technical.demo.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@Api(value = "Controlador que maneja las operaciones relacionadas con los productos")
public class ProductController {

    @Autowired
    IProductService productService;

    @ApiOperation(
            value = "Obtiene todos los productos disponibles en la tienda",
            notes = "Este endpoint recupera la lista completa de productos disponibles en la tienda")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listado obtenido exitosamente", response = ProductEntity.class, responseContainer = "List") })
    @GetMapping("/getAll")
    public List<ProductEntity> getProducts() {
        return productService.findAllShopping();
    }

}
