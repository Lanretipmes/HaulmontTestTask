package test_task.polyclinic.views;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.data.validator.LongRangeValidator;
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

import java.time.LocalDate;

public class EditRecipeDialog extends Window {

    private VerticalLayout verticalLayout = new VerticalLayout();
    private final Logger logger = LogManager.getLogger();

    private TextArea description = new TextArea("Description");
    private ComboBox<Patient> patient = new ComboBox<>("Patient");
    private ComboBox<Doctor> doctor = new ComboBox<>("Doctor");
    private DateField creationDate = new DateField("Creation Date");
    private TextField validity = new TextField("Valid for (months)");
    private ComboBox<Priority> priority = new ComboBox<>("Priority");
    private Binder<Recipe> binder = new Binder<>(Recipe.class);

    private Button edit = new Button("Ok");
    private Button cancel = new Button("Cancel", event -> close());

    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    public EditRecipeDialog(Grid<Recipe> grid, RecipeService recipeService, DoctorService doctorService, PatientService patientService, Recipe recipe){

        super("Edit Recipe");
        center();
        setModal(true);

        setContent(verticalLayout);
        verticalLayout.setWidth("300px");
        description.setSizeFull();
        verticalLayout.addComponent(description);

        patient.setItems(patientService.findAll());
        patient.setSizeFull();
        verticalLayout.addComponent(patient);

        doctor.setItems(doctorService.findAll());
        doctor.setSizeFull();
        verticalLayout.addComponent(doctor);

        creationDate.setSizeFull();
        verticalLayout.addComponent(creationDate);

        validity.setSizeFull();
        verticalLayout.addComponent(validity);

        priority.setItems(Priority.values());
        priority.setSizeFull();
        verticalLayout.addComponent(priority);

        buttonsLayout.addComponent(edit);
        buttonsLayout.addComponent(cancel);

        binder.forField(description).withValidator(new BeanValidator(Recipe.class, "description")).bind(Recipe::getDescription, Recipe::setDescription);
        binder.forField(patient).withValidator(new BeanValidator(Recipe.class, "patient")).bind(Recipe::getPatient, Recipe::setPatient);
        binder.forField(doctor).withValidator(new BeanValidator(Recipe.class, "doctor")).bind(Recipe::getDoctor, Recipe::setDoctor);
        binder.forField(creationDate).withValidator(new DateRangeValidator("Invalid Date", LocalDate.now().minusYears(1), LocalDate.now())).
                bind(Recipe::getCreationDate, Recipe::setCreationDate);
        binder.forField(validity).withConverter(new StringToLongConverter("Not a number")).
                withValidator(new LongRangeValidator("Validity is out of range", 1L, 12L)).
                bind(Recipe::getValidity, Recipe::setValidity);
        binder.forField(priority).withValidator(new BeanValidator(Recipe.class, "priority")).bind(Recipe::getPriority, Recipe::setPriority);

        verticalLayout.addComponent(buttonsLayout);

        binder.readBean(recipe);

        edit.addClickListener(clickEvent -> {
            if (binder.isValid())
            {
                try {
                    binder.writeBean(recipe);
                    recipeService.update(recipe.getId(), recipe);
                    logger.info("Edited recipe: \"" + recipe + "\"");
                    grid.setItems(recipeService.findAll());
                    grid.getDataProvider().refreshAll();
                    close();
                } catch (ValidationException e) {
                    Notification.show("Check the correctness of the fields");
                    logger.error("Failed to update recipe: \"" + recipe + "\"", e);
                }

            }
            else {
                Notification.show("Check the correctness of the fields");
                logger.error("Failed to update recipe: \"" + recipe + "\"");
            }
        });
    }

}
