
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import es.uma.informatica.sii.anotaciones.Requisitos;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashMap;
import java.util.Map;

public class LoginTestUsuario {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Requisitos({"RF-02"})
    @Test
    public void login() {
        driver.get("http://localhost:8080/jpa.demo-war");
        driver.manage().window().maximize();
        driver.findElement(By.id("Login-Form:Login-DNI")).click();
        driver.findElement(By.id("Login-Form:Login-DNI")).sendKeys("admin");
        driver.findElement(By.id("Login-Form:Login-NombreCompleto")).click();
        driver.findElement(By.id("Login-Form:Login-NombreCompleto")).sendKeys("admin");
        driver.findElement(By.id("Login-Form:EntrarLoginid")).click();
        driver.findElement(By.id("PaginaPrincipalid")).click();
        assertThat(driver.findElement(By.id("PaginaPrincipalid")).getText(), is("Pagina Principal"));
    }


}
