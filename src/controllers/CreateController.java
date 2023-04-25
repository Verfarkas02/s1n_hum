package controllers;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import models.Database;
import models.Employee;
import views.CreateFrame;
import views.MainFrame;

public class CreateController {
    CreateFrame createFrame;
    MainFrame mainFrame;
    public CreateController(MainFrame mainFrame) {
        this.createFrame =new CreateFrame(this.mainFrame);
        this.habdleEvents();
    }
    public CreateFrame getCreateFrame() {
        return createFrame;
    }
    private void habdleEvents(){
        JButton addButton= this.createFrame.getAddButton();
        addButton.addActionListener(e -> {
            startAdding();
        });
    }
    private void startAdding(){
        System.out.println("Hozzáadás árnyékeljáeás");
        String nameStr= createFrame.getNamePanel().getValue();
        String cityStr= createFrame.getCityPanel().getValue();
        String salaryStr= createFrame.getSalaryPanel().getValue();
        Double salary = Double.parseDouble(salaryStr);
        Employee emp =new Employee( nameStr, cityStr, salary);
        new Database().insertEmployee(emp);
        JOptionPane.showMessageDialog(createFrame, "A hozzáadás megtörtént");
    }
}
