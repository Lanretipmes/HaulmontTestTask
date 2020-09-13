package test_task.polyclinic.views;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import test_task.polyclinic.domain.Doctor;
import test_task.polyclinic.domain.Specialty;
import test_task.polyclinic.services.DoctorService;

public class EditDoctorDialog extends Window {

    private VerticalLayout verticalLayout = new VerticalLayout();

    private TextField name = new TextField("Name");
    private TextField surname = new TextField("Surname");
    private TextField patronymic = new TextField("Patronymic");
    private ComboBox<Specialty> specialty = new ComboBox<>("Specialty");

    private Button edit = new Button("Ok");
    private Button cancel = new Button("Cancel", event -> close());

    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    public EditDoctorDialog(Grid<Doctor> grid, DoctorService doctorService, Doctor doctor){

        super("Edit Doctor");
        center();
        setModal(true);

        setContent(verticalLayout);
        specialty.setItems(Specialty.values());

        buttonsLayout.addComponent(edit);
        buttonsLayout.addComponent(cancel);

        verticalLayout.addComponent(name);
        verticalLayout.addComponent(surname);
        verticalLayout.addComponent(patronymic);
        verticalLayout.addComponent(specialty);
        verticalLayout.addComponent(buttonsLayout);

        Binder<Doctor> binder = new Binder<>();

        binder.forField(name).withValidator(new BeanValidator(Doctor.class, "name")).bind(Doctor::getName, Doctor::setName);
        binder.forField(surname).withValidator(new BeanValidator(Doctor.class, "surname")).bind(Doctor::getSurname, Doctor::setSurname);
        binder.forField(patronymic).withValidator(new BeanValidator(Doctor.class, "patronymic")).bind(Doctor::getPatronymic, Doctor::setPatronymic);
        binder.forField(specialty).withValidator(new BeanValidator(Doctor.class, "specialty")).bind(Doctor::getSpecialty, Doctor::setSpecialty);

        binder.readBean(doctor);

        edit.addClickListener(clickEvent -> {
            if (binder.isValid())
            {
                try {
                    binder.writeBean(doctor);
                    doctorService.update(doctor.getId(), doctor);
                    grid.setItems(doctorService.findAll());
                    grid.getDataProvider().refreshAll();
                    close();
                } catch (ValidationException e) {
                    Notification.show("Task failed successfully!");
                    e.printStackTrace();
                }
            } else
                Notification.show("Task failed successfully!");
        });
    }
}
