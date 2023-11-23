package fontys.sem3.school.accesoryweb.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orderDetail")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "orderId")
    private OrderEntity order;
    @NotNull
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "amount")
    private int amount;
}
