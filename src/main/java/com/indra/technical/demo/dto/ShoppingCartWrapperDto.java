package com.indra.technical.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartWrapperDto {

    List<SeeShoppingDto> listProducts;
    Double subTotal = (double) 0;
    Double totalToPay = (double) 0;
}
