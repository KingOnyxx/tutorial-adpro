package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @Spy
    @InjectMocks
    PaymentServiceImpl paymentService;
    
    @Mock
    PaymentRepository paymentRepository;

    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708560000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP12345678ABC");
        Payment payment1 = new Payment( order1, PaymentMethod.VOUCHER.getValue() , paymentData );
        payments.add(payment1);
        paymentData = new HashMap<>();
        paymentData.put("bankName","abc");
        paymentData.put("referenceCode","0");
        Payment payment2 = new Payment(order2, PaymentMethod.BANK.getValue(), paymentData);
        payments.add(payment2);
    }

    @Test
    void testCreatePayment() {
        Order order = orders.get(1);
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());

        Payment result = paymentService.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        verify(paymentRepository, times(1)).addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        assertEquals(payment.getId(), result.getId());
    }

     @Test
    void testSetStatusSuccessful(){
        Payment payment = payments.get(0);
        when(paymentRepository.setStatus(payment, PaymentStatus.SUCCESS.getValue())).thenReturn(payment);

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        verify(paymentRepository).setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment, result);

        when(paymentRepository.setStatus(payment, PaymentStatus.REJECTED.getValue())).thenReturn(payment);
        result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        verify(paymentRepository).setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment, result);
    }

    @Test
    void testGetPaymentIfFound(){
        Payment payment1 = payments.get(0);
        doReturn(payment1).when(paymentRepository).getPayment(payment1.getId());
        Payment paymentFound = paymentService.getPayment(payment1.getId());
        assertEquals(payment1.getId(), paymentFound.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(),paymentFound.getMethod());
        assertEquals(payment1.getStatus(), paymentFound.getStatus());
    }

    @Test
    void testGetPaymentIfNotFound(){
        doReturn(null).when(paymentRepository).getPayment("zczc");
        Payment payment = paymentService.getPayment("zczc");
        assertNull(payment);
    }

    @Test
    void testGetAllPayment(){
        doReturn(payments).when(paymentRepository).getAllPayment();
        List<Payment> payment = paymentService.getAllPayment();
        assertSame(payments,payment);
    }

}