package test_task.polyclinic.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "Doctor")
@RequiredArgsConstructor
@NoArgsConstructor
public class Doctor {

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
    @Enumerated(EnumType.STRING)
    @Column(length = 50, name = "specialty")
    @NonNull private Specialty specialty;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Recipe> recipes;

    public String toString(){
        return id + ": " + specialty + " " + surname + " " + name + " " + patronymic;
    }

}

