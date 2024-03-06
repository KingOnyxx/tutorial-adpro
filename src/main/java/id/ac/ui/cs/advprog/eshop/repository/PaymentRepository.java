package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class PaymentRepository {
    
    private List<Payment> payments= new ArrayList<>();

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order, method, paymentData);
        payments.add(payment);
        return payment;    
    }

    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if (status.equals(PaymentStatus.SUCCESS.getValue())){
            payment.getOrder().setStatus(OrderStatus.SUCCESS.getValue());
        }
        if (status.equals(PaymentStatus.REJECTED.getValue())){
            payment.getOrder().setStatus(OrderStatus.FAILED.getValue());
        }
        return payment;
    }

    public Payment getPayment(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> getAllPayment() {
        return payments;
    }

    public void setId (Payment payment, String id) {
        if (id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        payment.setId(id);
    }
}
