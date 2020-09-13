package test_task.polyclinic.domain;

import lombok.*;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@ToString()
@Table(name = "Patient")
//@Proxy(lazy=false)
@RequiredArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue
    private long id;
    @Size(min = 1, max = 50)
//    @Type(type = "char")
    @Column(length = 50, name = "name")
    @NonNull private String name;
    @Size(min = 1, max = 50)
//    @Type(type = "char")
    @Column(length = 50, name = "surname")
    @NonNull private String surname;
    @Size(min = 1, max = 50)
//    @Type(type = "char")
    @Column(length = 50, name = "patronymic")
    @NonNull private String patronymic;
//    @Type(type = "char")
    @Column(length = 11, name = "phone")
    @Pattern(regexp = "[1-9]\\d{10}")
    @NonNull private String phone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Recipe> recipes;

    //TODO rewrite tostring
}
