package com.indra.technical.demo.repository;

import com.indra.technical.demo.dto.SeeShoppingDto;
import com.indra.technical.demo.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ShoppingCartEntity sc WHERE sc.idProduct = :idProduct")
    void deleteByProductId(@Param("idProduct") Long idProduct);

    @Query("SELECT new com.indra.technical.demo.dto.SeeShoppingDto(p.name, p.price, sc.quantity, (p.price * sc.quantity)) " +
            "FROM ShoppingCartEntity sc " +
            "INNER JOIN ProductEntity p on sc.idProduct = p.id")
    List<SeeShoppingDto> getShoppingCart();

    @Query("SELECT sc FROM ShoppingCartEntity sc WHERE sc.idProduct = :idProduct")
    List<ShoppingCartEntity> findProductIntoList(@Param("idProduct") Long idProduct);

}
