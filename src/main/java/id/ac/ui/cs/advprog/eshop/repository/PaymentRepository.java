package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {
    
    private List<Order> orders;
    private List<Payment> payments;
    public Payment addPayment(Order order) {
        return null;
    }
    public Payment setStatus(Payment payment, String status) {
        return null;
    }
    public Payment getPayment(String orderId) {
        return null;
    }
    public List<Payment> getAllPayment() {
        return null;
    }

}
