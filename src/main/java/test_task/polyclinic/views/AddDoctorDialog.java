package test_task.polyclinic.views;

import com.vaadin.data.Binder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;

import test_task.polyclinic.domain.Doctor;
import test_task.polyclinic.domain.Specialty;
import test_task.polyclinic.services.DoctorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDoctorDialog extends Window {

    private final Logger logger = LogManager.getLogger();

    private VerticalLayout verticalLayout = new VerticalLayout();
    private Doctor doctor;

    private TextField name = new TextField("Name");
    private TextField surname = new TextField("Surname");
    private TextField patronymic = new TextField("Patronymic");
    private ComboBox<Specialty> specialty = new ComboBox<>("Specialty");

    private Button add = new Button("Ok");
    private Button cancel = new Button("Cancel", event -> close());

    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    private Binder<Doctor> binder = new Binder<>(Doctor.class);

    public AddDoctorDialog(ListDataProvider<Doctor> dataProvider, DoctorService doctorService){

        super("Add Doctor");
        center();
        setModal(true);

        setContent(verticalLayout);
        specialty.setItems(Specialty.values());

        buttonsLayout.addComponent(add);
        buttonsLayout.addComponent(cancel);

        verticalLayout.addComponent(name);
        verticalLayout.addComponent(surname);
        verticalLayout.addComponent(patronymic);
        verticalLayout.addComponent(specialty);
        verticalLayout.addComponent(buttonsLayout);

        binder.forField(name).withValidator(new BeanValidator(Doctor.class, "name")).bind(Doctor::getName, Doctor::setName);
        binder.forField(surname).withValidator(new BeanValidator(Doctor.class, "surname")).bind(Doctor::getSurname, Doctor::setSurname);
        binder.forField(patronymic).withValidator(new BeanValidator(Doctor.class, "patronymic")).bind(Doctor::getPatronymic, Doctor::setPatronymic);
        binder.forField(specialty).withValidator(new BeanValidator(Doctor.class, "specialty")).bind(Doctor::getSpecialty, Doctor::setSpecialty);

        add.addClickListener(clickEvent -> {
            if (binder.isValid()) {
                doctor = new Doctor(name.getValue(), surname.getValue(), patronymic.getValue(), specialty.getValue());
                doctorService.save(doctor);
                dataProvider.getItems().add(doctor);
                logger.info("Added Doctor: \"" + doctor + "\"");
                dataProvider.refreshAll();
                name.clear();
                surname.clear();
                patronymic.clear();
                specialty.clear();
                close();
            } else {
                Notification.show("Check the correctness of the fields");
                logger.error("Failed to add a doctor");
        }
        });
    }
}
