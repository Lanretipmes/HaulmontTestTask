package test_task.polyclinic.views;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderRow;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import test_task.polyclinic.domain.Recipe;
import test_task.polyclinic.services.DoctorService;
import test_task.polyclinic.services.PatientService;
import test_task.polyclinic.services.RecipeService;

public class RecipeView extends VerticalLayout {

    private Grid<Recipe> grid = new Grid<>(Recipe.class);
    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    private final Logger logger = LogManager.getLogger();

    private Button add = new Button("Add");
    private Button delete = new Button("Delete");
    private Button edit = new Button("Edit");
    private Recipe selectedRecipe;
    private ListDataProvider<Recipe> dataProvider;
    private TextField descriptionFilter = new TextField();
    private TextField patientFilter = new TextField();
    private TextField priorityFilter = new TextField();

    public RecipeView(RecipeService recipeService, PatientService patientService, DoctorService doctorService){

        dataProvider = new ListDataProvider<>(recipeService.findAll());

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

        patientFilter.addValueChangeListener(event -> dataProvider.addFilter(recipe -> StringUtils.containsIgnoreCase(recipe.getPatient().toString(), patientFilter.getValue())));
        descriptionFilter.addValueChangeListener(event -> dataProvider.addFilter(recipe -> StringUtils.containsIgnoreCase(recipe.getDescription(), descriptionFilter.getValue())));
        priorityFilter.addValueChangeListener(event -> dataProvider.addFilter(recipe -> StringUtils.containsIgnoreCase(String.valueOf(recipe.getPriority()), priorityFilter.getValue())));

        filters.getCell("description").setComponent(descriptionFilter);
        descriptionFilter.setSizeFull();

        filters.getCell("patient").setComponent(patientFilter);
        patientFilter.setSizeFull();

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
                    recipeService.delete(selectedRecipe);
                logger.info("Deleted recipe: \"" + selectedRecipe + "\"");
                    grid.setItems(recipeService.findAll());
                    grid.getDataProvider().refreshAll();
            }
        });

        edit.addClickListener(clickEvent -> {

            if (selectedRecipe != null) {
                getUI().addWindow(new EditRecipeDialog(grid, recipeService, doctorService, patientService, selectedRecipe));
            } else {
                Notification.show("Select line");
            }
        });
    }
}
