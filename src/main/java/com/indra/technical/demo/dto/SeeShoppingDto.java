package com.indra.technical.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeeShoppingDto {

    String name;
    Double priceUnitary;
    Integer quantity;
    Double totalProduct;
}
