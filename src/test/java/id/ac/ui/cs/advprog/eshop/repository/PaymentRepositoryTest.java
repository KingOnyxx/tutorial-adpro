package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

public class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    
    
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Map<String, String> paymentData = new HashMap<String, String>();

        orders = new ArrayList<>();
        payments = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        paymentData.put("voucherCode", "ESHOP12345678ABC");
        payments.add(new Payment(order1, "VOUCHER", paymentData));
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708560000L, "Safira Sudrajat");
        orders.add(order2);
        paymentData.put("bankName", "abc");
        paymentData.put("referenceCode", "01");
        payments.add(new Payment(order2, "BANK", paymentData));
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697eccbf1e", products, 1708560000L, "Bambang Sudrajat");
        orders.add(order3);
        paymentData.put("bankName", "abc");
        paymentData.put("referenceCode", "01");
        payments.add(new Payment(order3, "BANK", paymentData));
    }

    @Test
    void testAddpayment() {
        Order order = orders.get(1);
        Payment payment = payments.get(1);
        Payment result = paymentRepository.addPayment(order, payment.getMethod(), payment.getPaymentData());
        paymentRepository.setId(result, payment.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
        Order findOrder = result.getOrder();
        assertSame(order, findOrder);

        assertEquals(order.getId(), findOrder.getId());
        assertEquals(order.getOrderTime(), findOrder.getOrderTime());
        assertEquals(order.getAuthor(), findOrder.getAuthor());
        assertEquals(order.getStatus(), findOrder.getStatus());
    }

    @Test
    void testUpdateStatusSuccess() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        paymentRepository.setId(result, payment.getId());
        paymentRepository.setStatus(result, PaymentStatus.SUCCESS.getValue());
        
        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        Order findOrder = findResult.getOrder();
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), findOrder.getStatus());
    }

    @Test
    void testUpdateStatusRejected() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        paymentRepository.setId(result, payment.getId());
        paymentRepository.setStatus(result, PaymentStatus.REJECTED.getValue());

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        Order findOrder = findResult.getOrder();
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), findOrder.getStatus());
    }

    @Test
    void testGetPaymentIfIdFound() {
        for (Payment payment : payments){
            Payment result = paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
            paymentRepository.setId(result, payment.getId());
        }

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        Order findOrder = findResult.getOrder();
        assertEquals(orders.get(1).getId(), findOrder.getId());
        assertEquals(orders.get(1).getOrderTime(), findOrder.getOrderTime());
        assertEquals(orders.get(1).getAuthor(), findOrder.getAuthor());
        assertEquals(orders.get(1).getStatus(), findOrder.getStatus());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        for (Payment payment : payments){
            Payment result = paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
            paymentRepository.setId(result, payment.getId());
        }

        Payment findResult = paymentRepository.getPayment("zczc");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments){
            Payment result = paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
            paymentRepository.setId(result, payment.getId());
        }

        List<Payment> findResult = paymentRepository.getAllPayment();
        assertEquals(3, findResult.size());

        assertEquals(payments.get(0).getMethod(), findResult.get(0).getMethod());
        assertEquals(payments.get(0).getStatus(), findResult.get(0).getStatus());
        assertEquals(payments.get(0).getPaymentData(), findResult.get(0).getPaymentData());
        Order findOrder = findResult.get(0).getOrder();
        assertEquals(orders.get(0).getId(), findOrder.getId());
        assertEquals(orders.get(0).getOrderTime(), findOrder.getOrderTime());
        assertEquals(orders.get(0).getAuthor(), findOrder.getAuthor());
        assertEquals(orders.get(0).getStatus(), findOrder.getStatus());
    }   
}


