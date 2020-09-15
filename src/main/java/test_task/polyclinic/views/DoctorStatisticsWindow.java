package test_task.polyclinic.views;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import test_task.polyclinic.domain.Doctor;
import test_task.polyclinic.services.DoctorService;

public class DoctorStatisticsWindow extends Window {

    private Grid<Doctor> grid = new Grid<>();
    private ListDataProvider<Doctor> dataProvider;
    private VerticalLayout layout = new VerticalLayout();

    public DoctorStatisticsWindow(DoctorService doctorService){

        super("Doctor Statistics");
        center();
        setModal(true);

        dataProvider = new ListDataProvider<>(doctorService.findAll());

        setContent(layout);
        layout.addComponent(grid);
        layout.setWidth("1000px");
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        grid.addColumn(doctor -> doctor.getId() + ": " + doctor.getSpecialty() + " " + doctor.getSurname() + " " + doctor.getSurname()).setCaption("Doctor");
        grid.addColumn(doctor -> doctor.getRecipes().size()).setCaption("Amount of recipes");

    }
}
