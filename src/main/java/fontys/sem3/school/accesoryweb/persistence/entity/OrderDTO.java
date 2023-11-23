package fontys.sem3.school.accesoryweb.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@NoArgsConstructor

public class OrderDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private int amount;
    private ProductEntity product;

    public OrderDTO(Long orderId, LocalDateTime orderDate, int amount,ProductEntity product) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.amount = amount;
        this.product = product;

    }
    /*private OrderEntity orderId;
    private OrderEntity orderDate;
    private OrderDetailEntity amount;
    private ProductEntity product;*/


}
