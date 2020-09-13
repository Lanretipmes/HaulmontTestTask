package test_task.polyclinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test_task.polyclinic.domain.Patient;

@Repository
public interface PatientRepo extends JpaRepository <Patient, Long> {

}
