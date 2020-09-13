package test_task.polyclinic.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_task.polyclinic.domain.Patient;
import test_task.polyclinic.repos.PatientRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PatientService {

    private PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }

    public void save(Patient patient){
        patientRepo.save(patient);
    }

    public void delete(Patient patient){
        patientRepo.delete(patient);
    }

    @Transactional
    public void update(Long id, Patient newPatient){
        Patient patient = patientRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        patient.setName(newPatient.getName());
        patient.setSurname(newPatient.getSurname());
        patient.setPatronymic(newPatient.getPatronymic());
        patient.setPhone(newPatient.getPhone());
    }

    //TODO rewrite

    @Transactional
    public List<Patient> findAll(){
        return patientRepo.findAll();
    }
}
