package id.ac.ui.cs.advprog.eshop.functional;

import id.ac.ui.cs.advprog.eshop.model.Product;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    
    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d/%s/%s", testBaseUrl, serverPort, "product", "list");
    }

    @Test
    @Order(1)
    void create_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(String.format("%s:%d/%s/%s", testBaseUrl, serverPort, "product", "create"));
        driver.findElement(By.id("nameInput")).sendKeys("Product 1");
        driver.findElement(By.id("quantityInput")).sendKeys("10000");
        driver.findElement(By.tagName("button")).click();
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.xpath("//*[text()='Product 1']"));
        assertEquals("Product 1",element.getText());
    }

    @Test
    @Order(2)
    void edit_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        driver.get(String.format("%s:%d/%s/%s", testBaseUrl, serverPort, "product", "create"));
        driver.findElement(By.id("nameInput")).sendKeys("Product 2");
        driver.findElement(By.id("quantityInput")).sendKeys("20000");
        driver.findElement(By.tagName("button")).click();
        driver.get(baseUrl);
        WebElement productElement = driver.findElement(By.xpath("//*[text()='Product 2']"));
        String id = productElement.getAttribute("id");
        driver.get(String.format("%s:%d/%s/%s/%s", testBaseUrl, serverPort, "product", "edit", id));
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        nameInput.sendKeys("Product 3");
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        quantityInput.sendKeys("100");
        driver.findElement(By.tagName("button")).click();
        WebElement newElement = driver.findElement(By.id(id));
        assertEquals("Product 3", newElement.getText());
    }

    @Test
    @Order(3)
    void delete_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement productElement = driver.findElement(By.xpath("//*[text()='Product 1']"));
        String id = productElement.getAttribute("id");
        WebElement deleteButton = driver.findElement(By.id(String.format("%s-%s", "delete", id )));
        deleteButton.click();
        
        // Switch to the alert window
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals("Are you sure you want to delete this product?", alertText);
        alert.accept();

        driver.get(baseUrl);
        List<WebElement> rows = driver.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        assertEquals(2, rowCount); //header and Product 3
    }
}
