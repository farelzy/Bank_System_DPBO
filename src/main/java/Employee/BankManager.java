/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee;

/**
 *
 * @author LEGION 
 */
public class BankManager extends EmployeeAccount {
    private Employee employee;
    
    public BankManager (Employee employee, double salary, double penalty) {
        super(salary, penalty);
        this.employee = employee;
    }
    public Employee getEmployee() {
        return employee;
    }
    @Override 
    public double calculateNetSalary() {
        return getSalary() - getPenalty();
    }
    public void managerBankOperations() {
        System.out.println("Mengelola operasional bank untuk : " + employee.getName());
    }
}
