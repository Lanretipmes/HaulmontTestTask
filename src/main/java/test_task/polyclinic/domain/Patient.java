package test_task.polyclinic.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "Patient")
@RequiredArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 1, max = 50)
    @Column(length = 50, name = "name")
    @NonNull private String name;
    @Size(min = 1, max = 50)
    @Column(length = 50, name = "surname")
    @NonNull private String surname;
    @Size(min = 1, max = 50)
    @Column(length = 50, name = "patronymic")
    @NonNull private String patronymic;
    @Column(length = 11, name = "phone")
    @Pattern(regexp = "[1-9]\\d{10}")
    @NonNull private String phone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Recipe> recipes;

    public String toString(){
        return id + ": " + surname + " " + name + " " + patronymic;
    }
}
