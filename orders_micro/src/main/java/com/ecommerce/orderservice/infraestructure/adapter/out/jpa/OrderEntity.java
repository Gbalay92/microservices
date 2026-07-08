package com.ecommerce.orderservice.infraestructure.adapter.out.jpa;


import com.ecommerce.orderservice.domain.model.OrderItem;
import com.ecommerce.orderservice.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private UUID userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Setter
    private List<OrderItemEntity> items;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
