package fontys.sem3.school.accesoryweb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdateProductRequest {
    private Long id;
    private String name;
    private Double price;
    private int quantity;
    private Long categoryId;

}
