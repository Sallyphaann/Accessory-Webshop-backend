package fontys.sem3.school.accesoryweb.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderListDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    public  OrderListDTO(Long orderId, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;


    }

}

