package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;
import java.util.UUID;

@Getter 
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment (Order order, String method, Map<String,String> paymentData) {
        if (order == null || method.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (paymentData == null) {
            throw new IllegalArgumentException();
        } else {
            this.order = order;
            this.method = method;
            this.id = UUID.randomUUID().toString();
            this.paymentData = paymentData;
            this.status = "PENDING";
        }
    }

    public void setStatus(String status) {
        if (status.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.status = status;
    }
}

