package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        // Set up any necessary test data or configurations
    }

    @Test
    public void testCreateProductPage() {
        // Test the createProductPage method
        String result = productController.createProductPage(model);
        assertEquals("createProduct", result);
    }

    @Test
    public void testCreateProductPost() {
        // Test the createProductPost method
        Product product = new Product();
        //model.addAttribute("product", product);
        String result = productController.createProductPost(product, model);
        assertEquals("redirect:list", result);
        verify(productService, times(1)).create(product);
    }

    @Test
    public void testProductListPage() {
        // Test the productListPage method
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);
        //model.addAttribute("products", productList);
        String result = productController.productListPage(model);
        assertEquals("productList", result);
        verify(model, times(1)).addAttribute("products", productList);
    }

    @Test
    public void testEditProductPage() {
        // Test the editProductPage method
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        when(productService.findById(id)).thenReturn(product);
        //model.addAttribute("product", product);
        String result = productController.editProductPage(id, model);
        assertEquals("editProduct", result);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    public void testEditProductPost() {
        // Test the editProductPost method
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        String result = productController.editProductPost(id, product, model);
        assertEquals("redirect:../list", result);
        verify(productService, times(1)).edit(product, id);
    }

    @Test
    public void testDeleteProduct() {
        // Test the deleteProduct method
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        doNothing().when(productService).delete(id);

        ResponseEntity<Object> result = productController.deleteProduct(id);
        assertEquals(expectedResponse, result);
        verify(productService, times(1)).delete(id);
    }
}