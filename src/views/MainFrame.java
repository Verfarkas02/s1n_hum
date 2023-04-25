package views;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Database;
import models.Employee;

public class MainFrame extends JFrame{
    DefaultTableModel model;
    JTable table;
    JScrollPane pane;
    Database db;
    JButton addButton;

    public MainFrame() {
        this.initComponent();
        this.setComponent();
        this.setFrame();
    }
    private void initComponent(){
        this.model= new DefaultTableModel();
        this.table =new JTable(model);
        this.pane =new JScrollPane(this.table);
        this.db =new Database();
        this.addButton =new JButton("Hozzáad");
    }
    private void setComponent(){
        Object[] labels= {
            "Az",
            "Név", 
            "Település", 
            "Fizetés"
        };
        this.model.setColumnIdentifiers(labels);
        this.model.addRow(
            new Object[] {"1", "Erős István", "Szeged", "395"}
        );
        ArrayList<Employee> empList= this.db.getEmployee();

        for(Employee emp: empList){
            Vector<String> empStr =new Vector<>();
            empStr.add(emp.getId().toString());
            empStr.add(emp.getName());
            empStr.add(emp.getCity());
            empStr.add(emp.getSalary().toString());
            this.model.addRow(empStr);
        }
    }
    private void setFrame(){
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.add(this.pane);
        this.add(this.addButton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setVisible(true);
    }
    public JButton getAddButton(){
        return addButton;
    }
}
