package runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class StepDefMyList {
    private AppiumDriver driver;

    @Given("que tengo abierto a my list app")
    public void queTengoAbiertoAMyListApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "AOSP on IA Emulator");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("appPackage", "com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity", ".ui.HomeActivity");
        capabilities.setCapability("platformName", "Android");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
    }

    @When("yo registro una nueva lista")
    public void yoRegistroUnaNuevaLista(Map<String, String> values) {
        //button +
        driver.findElement(By.xpath(" //android.widget.ImageButton[@resource-id='com.vrproductiveapps.whendo:id/fab']")).click();
        //llenar  title
        driver.findElement(By.xpath("//android.widget.EditText[@text='Title']")).sendKeys(values.get("title"));
        //llenar  Notes
        driver.findElement(By.xpath("//android.widget.EditText[@text='Notes']")).sendKeys(values.get("notes"));
        //button save
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Save']")).click();
    }

    @Then("el listado {string} deberia ser creado")
    public void elListadoDeberiaSerCreado(String expectedResult) {

        String resultadoActual = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']")).getText();
        Assertions.assertEquals(expectedResult, resultadoActual, "ERROR!! El texto creado es incorrecto");

    }
}
