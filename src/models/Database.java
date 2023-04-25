package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    public Database() {
       /* Employee emp =new Employee(
            1,
            "Marduk Árpád",
            "Miskolc",
            395.
        );*/
        //this.insertEmployee(emp);
        ArrayList<Employee> empList= this.getEmployee();
        empList.forEach((employee) -> {
            System.out.println(employee.name);
        });
    }

    public Connection connectDb() throws ClassNotFoundException, SQLException{
        Connection con =null;
        String url ="jdbc:mariadb://localhost:3306/hum";
        Class.forName("org.mariadb.jdbc.Driver");

        con =DriverManager.getConnection(url, "hum", "titok");
        System.out.println("kapcsolódva");
        return con;
    }
    public void closeDb(Connection con) throws SQLException{
        con.close();
    }

    //Hibakezelő metodus
    public void insertEmployee(Employee emp){
        try {
            tryInsertEmployee(emp);

        }catch(ClassNotFoundException e){
            System.err.println("Hiba! Nincs Mariadb driver betöltve!");
            System.err.println(e.getMessage());
        }
         catch (SQLException ex) {
            System.err.println("Hiba! Az adatázishoz a kapcsolat sikertelen!");
            System.err.println(ex.getMessage());
         }
    }
    //Iparikód (hasznos kód)
    public void  tryInsertEmployee(Employee emp) throws SQLException, ClassNotFoundException{
        
        Connection con =this.connectDb();
        String sql ="insert into employees" +
            "(name, city, salary) values"+
            "(?, ?, ?)";
        //'Pali', 'Szeged', 347
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setString(1, emp.name);
        pstmt.setString(2, emp.city);
        pstmt.setDouble(3, emp.salary);
        System.out.println(pstmt.toString());
        pstmt.execute();
        this.closeDb(con); //con.close();
    }

    public ArrayList<Employee> getEmployee(){
        ArrayList<Employee> empList;
        try {
            empList = tryGetEmployee();
        } catch (Exception e) {
            System.err.println("Hiba! A dolgozók lekérdeése sikertelen!");
            empList= null;
        }
        return empList;
    }
    
    public ArrayList<Employee> tryGetEmployee() throws ClassNotFoundException, SQLException{
        ArrayList<Employee> empList= new ArrayList<>();

       Connection con= connectDb();

        String sql ="select * from employees";
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery(sql);
        empList = convertResToList(rs);
        closeDb(con);
        return empList;
    }
    public ArrayList<Employee> convertResToList(ResultSet rs) throws SQLException{
        ArrayList<Employee> empList =new ArrayList<>();
        while(rs.next()){
            Employee emp= new Employee(
                rs.getInt("id"),
                rs.getString("name"), 
                rs.getString("city"), 
                rs.getDouble("salary")
            );
            empList.add(emp);
        }
        return empList;
    }
}
