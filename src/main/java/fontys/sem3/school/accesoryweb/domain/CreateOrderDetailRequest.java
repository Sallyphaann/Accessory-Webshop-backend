package fontys.sem3.school.accesoryweb.domain;

import fontys.sem3.school.accesoryweb.persistence.entity.OrderEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDetailRequest {
    private Long productId;
    private int amount;
}
