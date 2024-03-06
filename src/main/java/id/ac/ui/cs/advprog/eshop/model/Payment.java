package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;
import java.util.UUID;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

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
        if (paymentData != null) {
            if (method.equals("BANK")) {
                if (paymentData.get("bankName").isEmpty()) {
                    throw new IllegalArgumentException();
                }
            } else if (method.equals("VOUCHER")) {
                if (paymentData.get("voucherCode").isEmpty() || !   isVoucherValid(paymentData.get("voucherCode"))) {
                    throw new IllegalArgumentException();
                }

            }
            this.order = order;
            this.method = method;
            this.id = UUID.randomUUID().toString();
            this.paymentData = paymentData;
            this.status = PaymentStatus.PENDING.getValue();

        }
    }

    public void setStatus(String status) {
        if (status.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.status = status;
    }
    public void setId(String id) {
        if (id.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.id = id;
    }

    private boolean isVoucherValid(String str) {
        if (str == null || str.length() != 16) {
            return false;
        }
        if (!str.startsWith("ESHOP")) {
            return false;
        }
        int ctr=0;
        for (int i = 5; i < 16; i++) {
            if (Character.isDigit(str.charAt(i))) {
                ctr++;
            }
        }
        return ctr == 8;
    }
}

