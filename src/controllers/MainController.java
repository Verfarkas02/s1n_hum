package controllers;

import views.CreateFrame;
import views.MainFrame;

public class MainController {
    MainFrame mainFrame;
    public MainController() {
        this.mainFrame =new MainFrame();
        this.handleEvent();
    }
    private void handleEvent(){
        this.mainFrame.getAddButton().addActionListener(e -> {
            this.startAdd();
        });
    }
    private void startAdd(){
       CreateController createController = new CreateController(mainFrame);
       CreateFrame createFrame= createController.getCreateFrame();
       createFrame.setVisible(true);
        
    }
    
}
