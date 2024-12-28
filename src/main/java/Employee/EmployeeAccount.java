/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee;

/**
 *
 * @author LEGION
 */
public abstract class EmployeeAccount {
    private double salary;
    private double penalty;
    
    public EmployeeAccount(double salary, double penalty) {
        this.salary = salary;
        this.penalty = penalty;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary() {
        this.salary = salary;
    }
    public double getPenalty() {
        return penalty;
    }
    public void setPenalty(double penalty) {
        this.penalty;
    }
    public abstract double calculateNetSalary();
}
