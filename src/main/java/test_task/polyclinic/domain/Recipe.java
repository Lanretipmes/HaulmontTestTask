package test_task.polyclinic.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Recipe")
//@Proxy(lazy=false)
@RequiredArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue
    private long id;
    @Size(min = 1, max = 200)
    @Column(length = 200, name = "description")
    @NonNull private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "patient_id")
    @NonNull private Patient patient;
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "doctor_id")
    @NonNull private Doctor doctor;
    @NonNull private LocalDate creationDate;
    @NonNull private long validity; //validity in months
    @Enumerated(EnumType.STRING)
//    @Type(type = "char")
    @Column(length = 20, name = "priority")
    @NonNull private Priority priority;

    @Transient
    public LocalDate getExpireDate(){
        return this.getCreationDate().plusMonths(this.getValidity());
    }

}
