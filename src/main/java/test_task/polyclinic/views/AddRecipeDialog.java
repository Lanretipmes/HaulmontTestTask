package test_task.polyclinic.views;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.validator.*;
import com.vaadin.ui.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import test_task.polyclinic.domain.Doctor;
import test_task.polyclinic.domain.Patient;
import test_task.polyclinic.domain.Priority;
import test_task.polyclinic.domain.Recipe;
import test_task.polyclinic.services.DoctorService;
import test_task.polyclinic.services.PatientService;
import test_task.polyclinic.services.RecipeService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

public class AddRecipeDialog extends Window {

    private VerticalLayout verticalLayout = new VerticalLayout();
    private final Logger logger = LogManager.getLogger();

    private TextArea description = new TextArea("Description");
    private ComboBox<Patient> patient = new ComboBox<>("Patient");
    private ComboBox<Doctor> doctor = new ComboBox<>("Doctor");
    private DateField creationDate = new DateField("Creation Date");
    private TextField validity = new TextField("Valid for (months)");
    private ComboBox<Priority> priority = new ComboBox<>("Priority");
    private Binder<Recipe> binder = new Binder<>(Recipe.class);
    private Recipe recipe;

    private Button add = new Button("Ok");
    private Button cancel = new Button("Cancel", event -> close());

    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    public AddRecipeDialog(ListDataProvider<Recipe> dataProvider, RecipeService recipeService, DoctorService doctorService, PatientService patientService){

        super("Add Recipe");
        center();
        setModal(true);

        setContent(verticalLayout);
        verticalLayout.addComponent(description);

        patient.setItems(patientService.findAll());
        patient.setItemCaptionGenerator(patient1 -> patient1.getId() + " " + patient1.getSurname() + " " +
                patient1.getName() + " " + patient1.getPatronymic());
        verticalLayout.addComponent(patient);

        doctor.setItems(doctorService.findAll());
        doctor.setItemCaptionGenerator(doctor1 -> doctor1.getId() + " " + doctor1.getSpecialty() + " " +
                doctor1.getSurname() + " " + doctor1.getName());
        verticalLayout.addComponent(doctor);

        verticalLayout.addComponent(creationDate);
        verticalLayout.addComponent(validity);
        priority.setItems(Priority.values());
        verticalLayout.addComponent(priority);

        buttonsLayout.addComponent(add);
        buttonsLayout.addComponent(cancel);

        binder.forField(description).withValidator(new BeanValidator(Recipe.class, "description")).bind(Recipe::getDescription, Recipe::setDescription);
        binder.forField(patient).withValidator(new BeanValidator(Recipe.class, "patient")).bind(Recipe::getPatient, Recipe::setPatient);
        binder.forField(doctor).withValidator(new BeanValidator(Recipe.class, "doctor")).bind(Recipe::getDoctor, Recipe::setDoctor);
        binder.forField(creationDate).withValidator(new DateRangeValidator("Invalid Date", LocalDate.now().minusYears(1), LocalDate.now())).
                bind(Recipe::getCreationDate, Recipe::setCreationDate);
        binder.forField(validity).withConverter(new StringToLongConverter("Not a number")).
                withValidator(new LongRangeValidator("Invalid validity", 1L, 12L)).
                bind(Recipe::getValidity, Recipe::setValidity);
        binder.forField(priority).withValidator(new BeanValidator(Recipe.class, "priority")).bind(Recipe::getPriority, Recipe::setPriority);

        verticalLayout.addComponent(buttonsLayout);

        add.addClickListener(clickEvent -> {
            if (binder.isValid())
            {
                recipe = new Recipe(description.getValue(), patient.getValue(), doctor.getValue(), creationDate.getValue(), Long.parseLong(validity.getValue()), priority.getValue());
                recipeService.save(recipe);
                dataProvider.getItems().add(recipe);
                logger.info("Added Recipe: \"" + recipe + "\"");
                dataProvider.refreshAll();
                description.clear();
                patient.clear();
                doctor.clear();
                creationDate.clear();
                close();
            }
            else {
                Notification.show("Check the correctness of the fields");
                logger.error("Failed to add a recipe");
            }
        });
    }

}
