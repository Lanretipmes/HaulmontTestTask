package test_task.polyclinic.views;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderRow;
import org.apache.commons.lang3.StringUtils;
import test_task.polyclinic.domain.Recipe;
import test_task.polyclinic.services.DoctorService;
import test_task.polyclinic.services.PatientService;
import test_task.polyclinic.services.RecipeService;

public class RecipeView extends VerticalLayout {

    private Grid<Recipe> grid = new Grid<>(Recipe.class);
    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    private Button add = new Button("Add");
    private Button delete = new Button("Delete");
    private Button edit = new Button("Edit");
    private Recipe selectedRecipe;

    public RecipeView(RecipeService recipeService, PatientService patientService, DoctorService doctorService){

        ListDataProvider<Recipe> dataProvider = new ListDataProvider<>(recipeService.findAll());

        addComponent(grid);

        buttonsLayout.addComponent(add);
        buttonsLayout.addComponent(edit);
        buttonsLayout.addComponent(delete);
        addComponent(buttonsLayout);

        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        grid.setColumnOrder("id", "description", "patient", "doctor", "creationDate", "expireDate", "validity", "priority");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        HeaderRow filters = grid.appendHeaderRow();

        //TODO rework

        TextField descriptionFilter = new TextField();
        descriptionFilter.addValueChangeListener(event -> dataProvider.addFilter(recipe -> StringUtils.containsIgnoreCase(recipe.getDescription(), descriptionFilter.getValue())));
        filters.getCell("description").setComponent(descriptionFilter);
        descriptionFilter.setSizeFull();

        TextField patientFilter = new TextField();
        patientFilter.addValueChangeListener(event -> dataProvider.addFilter(recipe -> StringUtils.containsIgnoreCase(recipe.getPatient().toString(), descriptionFilter.getValue())));
        filters.getCell("patient").setComponent(patientFilter);
        patientFilter.setSizeFull();

        TextField priorityFilter = new TextField();
        priorityFilter.addValueChangeListener(event -> dataProvider.addFilter(recipe -> StringUtils.containsIgnoreCase(String.valueOf(recipe.getPriority()), descriptionFilter.getValue())));
        filters.getCell("priority").setComponent(priorityFilter);
        priorityFilter.setSizeFull();

        add.addClickListener(clickEvent -> {
                    getUI().addWindow(new AddRecipeDialog(dataProvider, recipeService, doctorService, patientService));
                }
        );

        grid.addItemClickListener(itemClick -> {
                    delete.setEnabled(true);
                    selectedRecipe = itemClick.getItem();
                }
        );

        delete.addClickListener(clickEvent -> {

            if (selectedRecipe != null) {
                try {
                    recipeService.delete(selectedRecipe);
                    grid.getDataProvider().refreshAll(); //TODO deal with refresh?
                } catch (Exception exception) {
                    Notification.show("Could not execute statement. " + exception);
                }
            }
        });

        edit.addClickListener(clickEvent -> {

            if (selectedRecipe != null) {
                getUI().addWindow(new EditRecipeDialog(grid, recipeService, doctorService, patientService, selectedRecipe));
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
