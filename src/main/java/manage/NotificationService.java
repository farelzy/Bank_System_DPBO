/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage;
import Bank.Customer;

/**
 *
 * @author hafizh
 */
public class NotificationService {
    private String serviceName;

    public NotificationService(String serviceName) {
        this.serviceName = serviceName;
    }

    public void sendNotification(Customer customer, String message) {
        System.out.println("Notification to " + customer.getName() + ": " + message);
    }
}
