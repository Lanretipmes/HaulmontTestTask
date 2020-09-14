package test_task.polyclinic.views;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import test_task.polyclinic.domain.Doctor;
import test_task.polyclinic.domain.Patient;
import test_task.polyclinic.domain.Recipe;
import test_task.polyclinic.services.DoctorService;
import test_task.polyclinic.services.RecipeService;

public class DoctorView extends VerticalLayout {

    private Grid<Doctor> grid = new Grid<>(Doctor.class);
    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    private Button add = new Button("Add");
    private Button delete = new Button("Delete");
    private Button edit = new Button("Edit");
    private Button statistics = new Button("Statistics");
    private Doctor selectedDoctor;

    public DoctorView(DoctorService doctorService, RecipeService recipeService){

        ListDataProvider<Doctor> dataProvider = new ListDataProvider<>(doctorService.findAll());

        addComponent(grid);

        buttonsLayout.addComponent(add);
        buttonsLayout.addComponent(edit);
        buttonsLayout.addComponent(delete);
        buttonsLayout.addComponent(statistics);
        addComponent(buttonsLayout);

        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        grid.setColumnOrder("id", "surname", "name", "patronymic", "specialty");
        grid.removeColumn("recipes");

        statistics.addClickListener(clickEvent -> {
            getUI().addWindow(new DoctorStatisticsWindow(doctorService));
        });

        add.addClickListener(clickEvent -> {
                    getUI().addWindow(new AddDoctorDialog(dataProvider, doctorService));
                }
        );

        grid.addItemClickListener(itemClick -> {
            delete.setEnabled(true);
            selectedDoctor = itemClick.getItem();

            if (selectedDoctor != null)
            for(Recipe recipe : recipeService.findAll())
            {
                if (recipe.getDoctor().getId() == selectedDoctor.getId()) {
                    delete.setEnabled(false);
                    break;
                }
            }
        }
        );

        delete.addClickListener(clickEvent -> {

            if (selectedDoctor != null) {
                    doctorService.delete(selectedDoctor);
                    grid.setItems(doctorService.findAll());
                    grid.getDataProvider().refreshAll();
            }
        });

        edit.addClickListener(clickEvent -> {

            Doctor doctor = selectedDoctor;
            if (doctor != null) {
                getUI().addWindow(new EditDoctorDialog(grid, doctorService, doctor));
            } else {
                Notification.show("Select line");
            }
        });
    }
}
