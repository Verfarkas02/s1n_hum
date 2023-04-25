package models;

public class Employee {
    Integer id;
    String name;
    String city;
    Double salary;
    public Employee(Integer id, String name, String city, Double salary) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.salary = salary;
    }
    
    public Employee( String name, String city, Double salary) {
        this.name = name;
        this.city = city;
        this.salary = salary;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
}
