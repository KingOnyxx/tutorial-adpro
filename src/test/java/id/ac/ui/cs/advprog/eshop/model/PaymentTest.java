package id.ac.ui.cs.advprog.eshop.model;

import org.checkerframework.checker.units.qual.t;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PaymentTest {
    private List<Product> products;
    private List<Order> orders;
    private Order order;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat");
        this.orders = new ArrayList<>();
        this.orders.add(this.order);
    }
    
    @Test
    void testCreatePaymentEmptyOrder() {
        this.order = null;
        
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order,"VOUCHER", null);
        });
    }
    
    //Voucher
    @Test
    void testCreatePaymentInvalidMethod() {
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "01");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order,"", paymentData);
        });
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP12345678");
        Payment payment = new Payment(this.order,"VOUCHER", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
        assertSame(this.order, payment.getOrder());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP12345678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order,"", paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidVoucher() {
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("", "");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order,"VOUCHER", paymentData);
        });
    }

    //BANK
    @Test
    void testCreatePaymentEmptyOrderBank() {
        this.order = null;
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "01");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order, "BANK", paymentData);
        });
    }
    @Test
    void testCreatePaymentInvalidMethodBank() {
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "01");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order, "", paymentData);
        });
    }
 
    @Test
    void testCreatePaymentSuccessStatusBank() {
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "01");
        Payment payment = new Payment(this.order, "BANK", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
        assertSame(this.order, payment.getOrder());
    }

    @Test
    void testCreatePaymentInvalidStatusBank() {
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order, "", paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidBank() {
        Map <String, String> paymentData = new HashMap<String, String>();
        paymentData.put("", "");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(this.order, "BANK", paymentData);
        });
    }
}
