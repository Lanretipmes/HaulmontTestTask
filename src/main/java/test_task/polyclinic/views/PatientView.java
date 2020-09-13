package test_task.polyclinic.views;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import test_task.polyclinic.domain.Patient;
import test_task.polyclinic.domain.Recipe;
import test_task.polyclinic.repos.PatientRepo;
import test_task.polyclinic.repos.RecipeRepo;
import test_task.polyclinic.services.PatientService;
import test_task.polyclinic.services.RecipeService;


public class PatientView extends VerticalLayout {

    private Grid<Patient> grid = new Grid<>(Patient.class);
    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    private Button add = new Button("Add");
    private Button delete = new Button("Delete");
    private Button edit = new Button("Edit");
    private Patient selectedPatient;

    public PatientView(PatientService patientService, RecipeService recipeService) {

        ListDataProvider<Patient> dataProvider = new ListDataProvider<>(patientService.findAll());

        addComponent(grid);

        buttonsLayout.addComponent(add);
        buttonsLayout.addComponent(edit);
        buttonsLayout.addComponent(delete);
        addComponent(buttonsLayout);

        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        grid.setColumnOrder("id", "surname", "name", "patronymic", "phone");
        grid.removeColumn("recipes");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        add.addClickListener(clickEvent -> {
                    getUI().addWindow(new AddPatientDialog(dataProvider, grid, patientService));
                }
        );
        grid.addItemClickListener(itemClick -> {
            delete.setEnabled(true);
            selectedPatient = itemClick.getItem();
            Notification.show("" + selectedPatient);

            if(selectedPatient != null)
            for(Recipe recipe : recipeService.findAll())
            {
                if (recipe.getPatient().getId() == selectedPatient.getId()) {
                    delete.setEnabled(false);
                    break;
                }
            }
            }
        );
        delete.addClickListener(clickEvent -> {

            if (selectedPatient != null) {
                try {
                    patientService.delete(selectedPatient);
                    grid.getDataProvider().refreshAll();
                } catch (Exception exception) {
                    Notification.show("Could not execute statement. " + exception);
                }
            }
        });

        edit.addClickListener(clickEvent -> {

            if (selectedPatient != null) {
                getUI().addWindow(new EditPatientDialog(grid, patientService, selectedPatient));
                grid.deselectAll();
            } else {
                Notification.show("Select line");
            }
        });
    }

    public void updateTable(){
        grid.getDataProvider().refreshAll();
    }
}
