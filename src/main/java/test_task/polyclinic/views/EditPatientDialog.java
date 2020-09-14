package test_task.polyclinic.views;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import test_task.polyclinic.domain.Patient;
import test_task.polyclinic.services.PatientService;

public class EditPatientDialog extends Window {
    private VerticalLayout verticalLayout = new VerticalLayout();

    private TextField name = new TextField("Name");
    private TextField surname = new TextField("Surname");
    private TextField patronymic = new TextField("Patronymic");
    private TextField phone = new TextField("Phone");

    private Button edit = new Button("Ok");
    private Button cancel = new Button("Cancel", event -> close());

    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    public EditPatientDialog(Grid<Patient> grid, PatientService patientService, Patient patient){

        super("Edit Patient");
        center();
        setModal(true);

        setContent(verticalLayout);

        verticalLayout.addComponent(name);
        verticalLayout.addComponent(surname);
        verticalLayout.addComponent(patronymic);
        verticalLayout.addComponent(phone);
        verticalLayout.addComponent(buttonsLayout);

        buttonsLayout.addComponent(edit);
        buttonsLayout.addComponent(cancel);

        Binder<Patient> binder = new Binder<>();

        binder.forField(name).withValidator(new BeanValidator(Patient.class, "name")).bind(Patient::getName, Patient::setName);
        binder.forField(surname).withValidator(new BeanValidator(Patient.class, "surname")).bind(Patient::getSurname, Patient::setSurname);
        binder.forField(patronymic).withValidator(new BeanValidator(Patient.class, "patronymic")).bind(Patient::getPatronymic, Patient::setPatronymic);
        binder.forField(phone).withValidator(new BeanValidator(Patient.class, "phone")).bind(Patient::getPhone, Patient::setPhone);

        binder.readBean(patient);

        edit.addClickListener(clickEvent -> {
            if (binder.isValid())
            {
                try {
                    binder.writeBean(patient);
                    patientService.update(patient.getId(), patient);
                    grid.setItems(patientService.findAll());
                    grid.getDataProvider().refreshAll();
                    close();
                } catch (ValidationException e) {
                    Notification.show("Check the correctness of the fields");
                    e.printStackTrace();
                }
            } else
                Notification.show("Check the correctness of the fields");
        });

    }
}
