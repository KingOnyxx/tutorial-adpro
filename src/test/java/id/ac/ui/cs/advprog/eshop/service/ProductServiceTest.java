package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        Product product = new Product();
        productService.create(product);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    public void testFindById() {
        String id = UUID.randomUUID().toString();
        productService.findById(id);
        verify(productRepository, times(1)).findProductById(id);
    }

    @Test
    public void testFindAll() {
        Iterator<Product> iterator = Arrays.asList(new Product(), new Product()).iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        assertEquals(2, productService.findAll().size());
    }

    @Test
    public void testEdit() {
        Product product = new Product();
        String id = UUID.randomUUID().toString();
        productService.edit(product, id);
        verify(productRepository, times(1)).edit(product, id);
    }

    @Test
    public void testDelete() {
        String id = UUID.randomUUID().toString();
        productService.delete(id);
        verify(productRepository, times(1)).delete(id);
    }
}
