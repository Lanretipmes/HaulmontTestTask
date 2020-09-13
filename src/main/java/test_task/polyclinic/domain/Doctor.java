package test_task.polyclinic.domain;

import lombok.*;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "Doctor")
//@Proxy(lazy=false)
@RequiredArgsConstructor
@NoArgsConstructor
public class Doctor {

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
    @Enumerated(EnumType.STRING)
//    @Type(type = "char")
    @Column(length = 50, name = "specialty")
    @NonNull private Specialty specialty;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Recipe> recipes;

    //TODO rewrite tostring

}

