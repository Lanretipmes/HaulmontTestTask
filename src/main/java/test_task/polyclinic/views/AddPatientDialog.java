package test_task.polyclinic.views;

import com.vaadin.data.Binder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import test_task.polyclinic.domain.Patient;
import test_task.polyclinic.services.PatientService;

public class AddPatientDialog extends Window {
    private VerticalLayout verticalLayout = new VerticalLayout();
    private final Logger logger = LogManager.getLogger();

    private TextField name = new TextField("Name");
    private TextField surname = new TextField("Surname");
    private TextField patronymic = new TextField("Patronymic");
    private TextField phone = new TextField("Phone");
    private Patient patient;

    private Button add = new Button("Ok");
    private Button cancel = new Button("Cancel", event -> close());

    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    public AddPatientDialog(ListDataProvider<Patient> dataProvider, Grid<Patient> grid, PatientService patientService){

        super("Add Patient");
        center();
        setModal(true);

        setContent(verticalLayout);

        buttonsLayout.addComponent(add);
        buttonsLayout.addComponent(cancel);

        verticalLayout.addComponent(name);
        verticalLayout.addComponent(surname);
        verticalLayout.addComponent(patronymic);
        verticalLayout.addComponent(phone);
        verticalLayout.addComponent(buttonsLayout);

        Binder<Patient> binder = new Binder<>();

        binder.forField(name).withValidator(new BeanValidator(Patient.class, "name")).bind(Patient::getName, Patient::setName);
        binder.forField(surname).withValidator(new BeanValidator(Patient.class, "surname")).bind(Patient::getSurname, Patient::setSurname);
        binder.forField(patronymic).withValidator(new BeanValidator(Patient.class, "patronymic")).bind(Patient::getPatronymic, Patient::setPatronymic);
        binder.forField(phone).withValidator(new BeanValidator(Patient.class, "phone")).bind(Patient::getPhone, Patient::setPhone);

        add.addClickListener(clickEvent -> {
            if (binder.isValid()) {
                patient = new Patient(name.getValue(), surname.getValue(), patronymic.getValue(), phone.getValue());
                patientService.save(patient);
                logger.info("Added Patient: \"" + patient + "\"");
                dataProvider.getItems().add(patient);
                grid.getDataProvider().refreshAll();
                dataProvider.refreshAll();
                name.clear();
                surname.clear();
                patronymic.clear();
                phone.clear();
                close();
            }
            else {
                Notification.show("Check the correctness of the fields");
                logger.error("Failed to add a patient");
            }
        });

    }
}
