package com.indra.technical.demo.service;

import com.indra.technical.demo.model.ProductEntity;

import java.util.List;

public interface IProductService {

    List<ProductEntity> findAllShopping();
    Boolean addProduct(ProductEntity productEntity);


}
