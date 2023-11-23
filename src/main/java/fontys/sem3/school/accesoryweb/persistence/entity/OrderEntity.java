package fontys.sem3.school.accesoryweb.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "orderDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private UserEntity customer;
    @NotNull
    @OneToMany(mappedBy = "order",cascade = CascadeType.MERGE)
    private List<OrderDetailEntity> orderDetails;


}
