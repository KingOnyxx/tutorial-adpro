package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {
    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        // Set up any necessary test data or configurations
    }

    @Test
    public void testHome() {
        model.addAttribute("message", "Welcome");
        String viewName = homeController.home(model);

        assertEquals("homePage", viewName);
    }
}
