package fontys.sem3.school.accesoryweb.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;
    @NotBlank
    @Length(min = 2 ,max = 40)
    @Column(name = "firstName")
    private String firstname;
    @NotBlank
    @Length(min = 2 ,max = 40)
    @Column(name = "lastName")
    private String lastname;
    @NotBlank
    @Length(min = 2 ,max = 40)
    @Email
    @Column(name = "email")
    private String email;
    @NotBlank
    @Length(min = 2 ,max = 40)
    @Column(name = "address")
    private String address;
    @NotBlank
    @Length(min = 2 ,max = 40)
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @NotNull
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
