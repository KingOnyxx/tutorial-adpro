package id.ac.ui.cs.advprog.eshop.functional;

import id.ac.ui.cs.advprog.eshop.model.Product;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import ch.qos.logback.core.util.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
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
    void create_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(String.format("%s:%d/%s/%s", testBaseUrl, serverPort, "product", "create"));
        driver.findElement(By.id("nameInput")).sendKeys("Product 1");
        driver.findElement(By.id("quantityInput")).sendKeys("10000");
        driver.findElement(By.tagName("button")).click();
        WebElement element = driver.findElement(By.xpath("//*[text()='Product 1']"));
        assertEquals("Product 1",element.getText());
    }

    @Test
    void edit_isCorrect(ChromeDriver driver) throws Exception {
        create_isCorrect(driver);
        WebElement productElement = driver.findElement(By.xpath("//*[text()='Product 1']"));
        String id = productElement.getAttribute("id");
        driver.get(String.format("%s:%d/%s/%s/%s", testBaseUrl, serverPort, "product", "edit", id));
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        nameInput.sendKeys("Product 2");
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        quantityInput.sendKeys("100");
        driver.findElement(By.tagName("button")).click();
        WebElement newElement = driver.findElement(By.id(id));
        assertEquals("Product 2", newElement.getText());
    }

    @Test
    void delete_isCorrect(ChromeDriver driver) throws Exception {
        create_isCorrect(driver);
        WebElement productElement = driver.findElement(By.xpath("//*[text()='Product 1']"));
        String id = productElement.getAttribute("id");
        WebElement deleteButton = driver.findElement(By.id(String.format("%s-%s", "delete", id )));
        deleteButton.click();
    }
}
