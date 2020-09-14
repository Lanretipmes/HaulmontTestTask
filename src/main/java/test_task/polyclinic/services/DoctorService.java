package test_task.polyclinic.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_task.polyclinic.domain.Doctor;
import test_task.polyclinic.repos.DoctorRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DoctorService {

    private DoctorRepo doctorRepo;

    public DoctorService(DoctorRepo patientRepo){
        this.doctorRepo = patientRepo;
    }

    public void save(Doctor doctor){
        if (doctor != null)
        doctorRepo.save(doctor);
    }

    @Transactional
    public void delete(Doctor doctor){

        doctorRepo.delete(doctor);

    }

    @Transactional
    public void update(Long id, Doctor newDoctor){
        Doctor doctor = doctorRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        doctor.setName(newDoctor.getName());
        doctor.setSurname(newDoctor.getSurname());
        doctor.setPatronymic(newDoctor.getPatronymic());
        doctor.setSpecialty(newDoctor.getSpecialty());
    }

    @Transactional
    public List<Doctor> findAll(){
        return doctorRepo.findAll();
    }
}
