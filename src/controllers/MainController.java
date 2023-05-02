package controllers;

import javax.swing.JTable;

import models.Database;
import views.CreateFrame;
import views.MainFrame;

public class MainController {
    MainFrame mainFrame;
    Database database;

    public MainController() {
        this.database =new Database();
        this.mainFrame =new MainFrame();
        this.handleEvent();
    }
    private void handleEvent(){
        this.mainFrame.getAddButton().addActionListener(e -> {
            this.startAdd();
        });
        this.mainFrame.getDelButton().addActionListener(e -> {
            this.startDel();
        });
    }
    private void startAdd(){
       CreateController createController = new CreateController(mainFrame);
       CreateFrame createFrame= createController.getCreateFrame();
       createFrame.setVisible(true);
        
    }
    private void startDel(){
        JTable table= this.mainFrame.getTable();
        int row= table.getSelectedRow();

        String value= (String)table.getModel().getValueAt(row, 0);
        int id= Integer.parseInt(value);
        this.mainFrame.getModel().removeRow(row);
        this.database.deleteEmoloyee(id);
    }
    
}
