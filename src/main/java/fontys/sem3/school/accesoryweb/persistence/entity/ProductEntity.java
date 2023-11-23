package fontys.sem3.school.accesoryweb.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name ="product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Length(min = 2 ,max = 50)
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "price")
    private Double price;
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
