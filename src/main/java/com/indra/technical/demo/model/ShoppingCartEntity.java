package com.indra.technical.demo.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "in_shopping_cart", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @NotNull
    @Column(name = "create_day", nullable = false)
    private LocalDateTime createDay;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
