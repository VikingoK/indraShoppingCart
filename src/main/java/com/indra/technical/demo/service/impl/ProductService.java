package com.indra.technical.demo.service.impl;

import com.indra.technical.demo.model.ProductEntity;
import com.indra.technical.demo.repository.ProductRepository;
import com.indra.technical.demo.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductEntity> findAllShopping() {
        return productRepository.findAll();
    }

    @Override
    public Boolean addProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);

        return true;
    }
}
