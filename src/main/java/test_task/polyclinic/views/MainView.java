package test_task.polyclinic.views;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import test_task.polyclinic.services.DoctorService;
import test_task.polyclinic.services.PatientService;
import test_task.polyclinic.services.RecipeService;

@SpringUI
@Theme(ValoTheme.THEME_NAME)
public class MainView extends UI {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private RecipeService recipeService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);
        setContent(verticalLayout);
        PatientView patientView = new PatientView(patientService, recipeService);
        DoctorView doctorView = new DoctorView(doctorService, recipeService);
        RecipeView recipeView = new RecipeView(recipeService, patientService, doctorService);

        TabSheet tabSheet = new TabSheet();
        verticalLayout.addComponent(tabSheet);

        tabSheet.addTab(patientView, "Patients");
        tabSheet.addTab(doctorView, "Doctors");
        tabSheet.addTab(recipeView, "Recipes");
    }
}
