package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicAppium {
    private AppiumDriver driver;
    @BeforeEach
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities  = new DesiredCapabilities();
        capabilities.setCapability("deviceName","AOSP on IA Emulator");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
    }

    @Test
    public void listTest() throws InterruptedException{

        //button +
        driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.vrproductiveapps.whendo:id/fab']")).click();
        //llenar  title
        driver.findElement(By.xpath("//android.widget.EditText[@text='Title']")).sendKeys("taller02");
        //llenar  Notes
        driver.findElement(By.xpath("//android.widget.EditText[@text='Notes']")).sendKeys("Desarrollo");
        //button save
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Save']")).click();

        //verificación de resultado
        Thread.sleep(5000);

        String resultadoActual=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']")).getText();
        String resultadoEsperado="taller02";
        Assertions.assertEquals(resultadoEsperado,resultadoActual,"ERROR!! El texto creado es incorrecto");
    }

}
