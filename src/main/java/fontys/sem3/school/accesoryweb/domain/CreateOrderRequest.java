package fontys.sem3.school.accesoryweb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderDetailEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private List<CreateOrderDetailRequest> orderDetailRequests;

}